package com.example.hellodocker.common;

import com.aliyun.datahub.client.model.Field;
import com.aliyun.datahub.client.model.RecordSchema;
import com.aliyun.datahub.client.model.TupleRecordData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yjf
 * @date 2021/10/15
 * @description
 */
public class Utils {
    public static Map<String,Object> tupleRecordDataToMap(TupleRecordData data){
        Map<String,Object> map = new HashMap<>();
        if (null == data){
            return null;
        }
        RecordSchema schema = data.getRecordSchema();
        List<Field> list = schema.getFields();
        list.forEach(field->{
            String name = field.getName();
            map.put(name,data.getField(name));
        });
        return map;
    }
}
