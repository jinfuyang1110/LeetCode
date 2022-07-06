import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yjf
 * @date 2022/7/5
 * @description
 */
public class MyTest {
    Map<String, Integer> map = new HashMap<>();
    int mod = (int) 1e9 + 7;

    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        int i = get("0,0," + (m - 1) + "," + (n - 1) + "," + k, pizza);
        return i;
    }

    int get(String key, String[] pizza) {
        if (!map.containsKey(key)) {
            String[] nums = key.split(",");
            int[] cur = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i].equals("0")) continue;
                 cur[i]=Integer.parseInt(nums[i]);
            }
            int ways = 0;
            if (cur[4] != 0) {
                //横切
                for (int k = cur[0]; k < cur[2]; k++) {
                    //是否能切
                    boolean flag = false;
                    for (int l = cur[0]; l <= k; l++) {
                        for (int i = cur[1]; i <= cur[3]; i++) {
                            if (pizza[l].charAt(i) == 'A') {
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (flag)
                        ways = (ways + get((k + 1) + "," + cur[1] + "," + cur[2] + "," + cur[3] + "," + (cur[4] - 1),pizza)+1) % mod;
                }
                //纵切
                for (int i = cur[1]; i < cur[3]; i++) {
                    boolean flag = false;
                    for (int j = cur[0]; j <= cur[2]; j++) {
                        for (int k = cur[1]; k <= i; k++) {
                            if (pizza[k].charAt(j) == 'A') {
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (flag)
                        ways = (ways + get(cur[0] + "," + (i + 1) + "," + cur[2] + "," + cur[3] + "," + (cur[4] - 1),pizza)+1) % mod;
                }
            }
            map.put(key, ways);
        }
        return map.get(key);
    }
    @Test
    public void test(){
        ways(new String[]{"A..","AAA","..."},3);
    }
}
