
import com.example.hellodocker.TreeNode.*;
import org.junit.Test;

import java.util.*;

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

//    Set<String> words;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        Deque<String> b = new ArrayDeque<>(), e = new ArrayDeque<>();
        Map<String, Integer> bMap = new HashMap<>(), eMap = new HashMap<>();
        b.add(beginWord);
        e.add(endWord);
        bMap.put(beginWord, 0);
        eMap.put(endWord, 0);
        while (!b.isEmpty() && !e.isEmpty()) {
            int t = 0;
            if (b.size() < e.size()) t = update(b, bMap, eMap);
            else t = update(e, eMap, bMap);
            if (t != 0) return t;
        }
        return 0;
    }

    int update(Deque<String> deque, Map<String, Integer> cur, Map<String, Integer> other) {
        int size = deque.size();
        while (size-- > 0) {
            String now = deque.pop();
            char[] chars = now.toCharArray();
            int n = chars.length;
            int step = cur.get(now);
            for (int i = 0; i < n; i++) {
                char temp = chars[i];
                for (int j = 0; j < 26; j++) {
                    chars[i] = (char) (j + 'a');
                    String newWord = new String(chars);
                    if (words.contains(newWord) && !cur.containsKey(newWord)) {
                        if (other.containsKey(newWord)) return other.get(newWord) + step + 1;
                        cur.put(newWord, step + 1);
                    }
                }
                chars[i] = temp;
            }
        }
        return 0;
    }

    int get(String key, String[] pizza) {
        if (!map.containsKey(key)) {
            String[] nums = key.split(",");
            int[] cur = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i].equals("0")) continue;
                cur[i] = Integer.parseInt(nums[i]);
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
                        ways = (ways + get((k + 1) + "," + cur[1] + "," + cur[2] + "," + cur[3] + "," + (cur[4] - 1), pizza) + 1) % mod;
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
                        ways = (ways + get(cur[0] + "," + (i + 1) + "," + cur[2] + "," + cur[3] + "," + (cur[4] - 1), pizza) + 1) % mod;
                }
            }
            map.put(key, ways);
        }
        return map.get(key);
    }
    List<List<String>> lists = new ArrayList<>();
    Set<String> words;
    Set<String> visited;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> res = new ArrayList<>();
        int cnt = 0;
        boolean f = true;
        words = new HashSet<>(wordList);
        Set<String> v = new HashSet<>();
        if (!words.contains(endWord)) return lists;
        Deque<String> deque = new ArrayDeque<>();
        v.add(beginWord);
        deque.add(beginWord);
        while (!deque.isEmpty() && f) {
            int size = deque.size();
            while (size-- > 0) {
                String now = deque.pop();
                char[] chars = now.toCharArray();
                int n = chars.length;
                for (int i = 0; i < n; i++) {
                    char temp = chars[i];
                    for (int j = 0; j < 26; j++) {
                        chars[i] = (char) (j + 'a');
                        String newWord = new String(chars);
                        if (newWord.equals(endWord)) {
                            res.add(now);
                            f = false;
                        }
                        if (words.contains(newWord) && !v.contains(newWord)) {
                            deque.add(newWord);
                            v.add(newWord);
                        }
                    }
                    chars[i] = temp;
                }
                cnt++;
            }
        }
        visited.add(endWord);
        for (String re : res) {
            List<String> search=new ArrayList<>();
            search.add(endWord);
            result(0,cnt,re,beginWord,search);
        }
        for (List<String> list : lists) {
            Collections.reverse(list);
        }
        return lists;
    }

    void result(int index,int length,String begin,String end,List<String> list) {
        if (index==length){
         if (begin.equals(end))  lists.add(new ArrayList<>(list));
            return;
        }
        char[] chars = begin.toCharArray();
        int n=chars.length;
        for (int i = 0; i < n; i++) {
            char temp=chars[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (temp==j) continue;
                chars[i]=j;
                String newWord=new String(chars);
                if(words.contains(newWord)&&!visited.contains(newWord)){
                    list.add(newWord);
                    visited.add(newWord);
                    result(index+1,length,newWord,end,list);
                    list.remove(list.size()-1);
                    visited.remove(newWord);
                }
            }
            chars[i]=temp;
        }
    }
    public boolean canReceiveAllSignals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
       int n=intervals.length;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0]<intervals[i-1][1]) return false;
        }
        return true;
    }
    public int buildTransferStation(int[][] area) {
        int m=area.length,n=area[0].length;
        int min=Integer.MAX_VALUE;
        List<int[]> aims=new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (area[i][j]==1)aims.add(new int[]{i,j});
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                min=Math.min(min,getMin(aims,i,j));
            }
        }
        return min;
    }
    int getMin(List<int[]>aims,int x,int y){
        int cost=0;
        for (int[] aim : aims) {
            cost+=Math.abs(x-aim[0])+Math.abs(y-aim[1]);
        }
        return cost;
    }
    public int minSwaps(int[] chess) {
        //前缀和加滑动窗口
        int ans=Integer.MAX_VALUE,n=chess.length;
       int[] sum=new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i]=sum[i-1]+chess[i-1];
        }
        for (int i = 0; i+sum[n] <= n; i++) {
            ans=Math.min(ans,sum[n]-sum[i+sum[n]]-sum[i]);
        }
        return ans;
    }
    int ans=Integer.MAX_VALUE;
    public int minTransfers(int[][] distributions) {
        int[] records=new int[12];
        for (int[] distribution : distributions) {
            records[distribution[0]]-=distribution[2];
            records[distribution[1]]+=distribution[2];
        }
        dfs(0,0,records);
        return ans;
    }
    void dfs(int start,int cnt,int[]records){

    }
    @Test
    public void test() {
      minSwaps(new int[]{1,0,1,0,1,0,0,1,1,0,1});
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        TreeWord root = new TreeWord();
        for (String s : dictionary) {
            char[] chars = s.toCharArray();
            TreeWord now = root;
            for (char c : chars) {
                if (now.d[c - 'a'] == null) now.d[c - 'a'] = new TreeWord();
                now = now.d[c - 'a'];
            }
            now.isWord = true;
            now.word = s;
        }
        String[] words = sentence.split(" ");
        List<String> list = new ArrayList<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            TreeWord now = root;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (now.d[c - 'a'] == null || i == chars.length - 1) {
                    list.add(word);
                    break;
                }
                now = now.d[c - 'a'];
                if (now.isWord) {
                    list.add(now.word);
                    break;
                }
            }
        }
        return String.join(" ", list);
    }

    class TreeWord {
        TreeWord[] d = new TreeWord[26];
        String word;
        boolean isWord;
    }
}
