package com.example.hellodocker.common;

import com.alibaba.fastjson.JSON;
import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.example.examples.Constant;
import com.aliyun.datahub.client.exception.*;
import com.aliyun.datahub.client.http.HttpConfig;
import com.aliyun.datahub.client.model.*;
import com.aliyun.datahub.clientlibrary.config.ConsumerConfig;
import com.aliyun.datahub.clientlibrary.consumer.Consumer;
import com.example.hellodocker.bean.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Thread.sleep;

/**
 * @author yjf
 * @date 2021/10/15
 * @description
 */
public class DateHub {
    // Endpoint以Region: 华东1为例，其他Region请按实际情况填写
    String endpoint = "http://dh-cn-hangzhou.aliyuncs.com";
    String accessId = "LTAI5tHDTB11FvaXBKBrJkK6";
    String accessKey = "Nz8qLauXjxNToyt0lkoh24pyi8VZld";
    String projectName = "deamo1015";
    String topicName = "student";

    // 创建DataHubClient实例
    public DatahubClient getDatahubClient() {
        return DatahubClientBuilder.newBuilder()
                .setDatahubConfig(
                        new DatahubConfig(endpoint,
                                // 是否开启二进制传输，服务端2.12版本开始支持
                                new AliyunAccount(accessId, accessKey), true))
                //专有云使用出错尝试将参数设置为           false
                // HttpConfig可不设置，不设置时采用默认值
                .setHttpConfig(new HttpConfig()
                        .setCompressType(HttpConfig.CompressType.LZ4) // 读写数据推荐打开网络传输 LZ4压缩
                        .setConnTimeout(10000))
                .build();
    }

    // 写入Tuple型数据
    public void tupleExample(String project, String topic, int retryTimes,int n) {
        DatahubClient datahubClient = this.getDatahubClient();
        // 获取schema
        RecordSchema recordSchema = datahubClient.getTopic(project, topic).getRecordSchema();
        // 生成十条数据
        List<RecordEntry> recordEntries = new ArrayList<>();
        for (int i = 0+n; i < 7+n; ++i) {
            RecordEntry recordEntry = new RecordEntry();
            // 对每条数据设置额外属性，例如ip 机器名等。可以不设置额外属性，不影响数据写入
            recordEntry.addAttribute("key1", String.valueOf(i));

            TupleRecordData data = new TupleRecordData(recordSchema);
            data.setField("name", "HelloWorld:" + i);
            data.setField("age", i);
            recordEntry.setRecordData(data);
            recordEntries.add(recordEntry);
        }
        try {
            PutRecordsResult result = datahubClient.putRecords(project, topic, recordEntries);
            int i = result.getFailedRecordCount();
            if (i > 0) {
                retry(datahubClient, result.getFailedRecords(), retryTimes, project, topic);
            }
        } catch (DatahubClientException e) {
            System.out.println("requestId:" + e.getRequestId() + "\tmessage:" + e.getErrorMessage());
        }
    }

    //重试机制
    public static void retry(DatahubClient client, List<RecordEntry> records, int retryTimes, String project, String topic) {
        boolean suc = false;
        while (retryTimes != 0) {
            retryTimes = retryTimes - 1;
            PutRecordsResult recordsResult = client.putRecords(project, topic, records);
            if (recordsResult.getFailedRecordCount() > 0) {
                retry(client, recordsResult.getFailedRecords(), retryTimes, project, topic);
            }
            suc = true;
            break;
        }
        if (!suc) {
            System.out.println("retryFailure");
        }
    }

    //点位消费示例，并在消费过程中进行点位的提交
    public void example(String subId) {
        DatahubClient datahubClient = this.getDatahubClient();
        String shardId = "0";
        List<String> shardIds = Arrays.asList("0", "1","2","3");
        OpenSubscriptionSessionResult openSubscriptionSessionResult = datahubClient.openSubscriptionSession(projectName, topicName, subId, shardIds);
        SubscriptionOffset subscriptionOffset = openSubscriptionSessionResult.getOffsets().get(shardId);
        // 1、获取当前点位的cursor，如果当前点位已过期则获取生命周期内第一条record的cursor，未消费同样获取生命周期内第一条record的cursor
        String cursor = null;
        //sequence < 0说明未消费
        if (subscriptionOffset.getSequence() < 0) {
            // 获取生命周期内第一条record的cursor
            cursor = datahubClient.getCursor(projectName,topicName, shardId, CursorType.OLDEST).getCursor();
        } else {
            // 获取下一条记录的Cursor
            long nextSequence = subscriptionOffset.getSequence() + 1;
            try {
                //按照SEQUENCE getCursor可能报SeekOutOfRange错误，表示当前cursor的数据已过期
                cursor = datahubClient.getCursor(projectName, topicName, shardId, CursorType.SEQUENCE, nextSequence).getCursor();
            } catch (SeekOutOfRangeException e) {
                // 获取生命周期内第一条record的cursor
                cursor = datahubClient.getCursor(projectName, topicName, shardId, CursorType.OLDEST).getCursor();
            }
        }
        // 2、读取并保存点位，这里以读取Tuple数据为例，并且每10条记录保存一次点位
        long recordCount = 0L;
        // 每次读取10条record
        int fetchNum = 10;
        RecordSchema schema = datahubClient.getTopic(projectName, topicName).getRecordSchema();
        while (true) {
            try {
                GetRecordsResult getRecordsResult = datahubClient.getRecords(projectName,
                        topicName, shardId, schema, cursor, fetchNum);
                if (getRecordsResult.getRecordCount() <= 0) {
                    // 无数据，sleep后读取
                    Thread.sleep(1000);
                    continue;
                }
                for (RecordEntry recordEntry : getRecordsResult.getRecords()) {
                    //消费数据
                    TupleRecordData data = (TupleRecordData) recordEntry.getRecordData();
                    Map<String, Object> map = Utils.tupleRecordDataToMap(data);
                    User user = JSON.parseObject(JSON.toJSONString(map), User.class);
                    System.out.println(user);
                    // 处理数据完成后，设置点位
                    recordCount++;
                    subscriptionOffset.setSequence(recordEntry.getSequence());
                    subscriptionOffset.setTimestamp(recordEntry.getSystemTime());
                    if (recordCount % 10 == 0) {
                        //提交点位点位
                        Map<String, SubscriptionOffset> offsetMap = new HashMap<>();
                        offsetMap.put(shardId, subscriptionOffset);
                        datahubClient.commitSubscriptionOffset(projectName, topicName, subId, offsetMap);
                        System.out.println("commit offset successful");
                    }
                }
                cursor = getRecordsResult.getNextCursor();
            } catch (SubscriptionOfflineException | SubscriptionSessionInvalidException e) {
                // 退出. Offline: 订阅下线; SubscriptionSessionInvalid: 表示订阅被其他客户端同时消费
                break;
            } catch (SubscriptionOffsetResetException e) {
                // 表示点位被重置，重新获取SubscriptionOffset信息，这里以Sequence重置为例
                // 如果以Timestamp重置，需要通过CursorType.SYSTEM_TIME获取cursor
                subscriptionOffset = datahubClient.getSubscriptionOffset(projectName, topicName, subId, shardIds).getOffsets().get(shardId);
                long nextSequence = subscriptionOffset.getSequence() + 1;
                cursor = datahubClient.getCursor(projectName, topicName, shardId, CursorType.SEQUENCE, nextSequence).getCursor();
            } catch (DatahubClientException e) {
                // TODO: 针对不同异常决定是否退出
            } catch (Exception e) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        DateHub dateHub = new DateHub();
//        dateHub.tupleExample("deamo1015","student_msg",5);
        dateHub.example("1634521990414ES6J7");
    }
}
