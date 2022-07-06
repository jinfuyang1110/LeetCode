package com.example.hellodocker;


import com.alibaba.fastjson.JSONObject;
import com.aliyun.datahub.client.http.common.HttpRequest;
import com.example.hellodocker.TreeNode.Node;
import com.example.hellodocker.TreeNode.TreeNode;

import com.example.hellodocker.utils.HttpUtil;
import org.junit.Test;

import java.text.DateFormat;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Eric
 * @date 2021/9/14
 * @description
 */
public class LeetCodeTest {

    @Test
    public void findLongestWord() {

    }

    public String findLongestWord(String s, List<String> dictionary) {
        Stream<String> sorted = dictionary.stream().sorted
                ((s1, s2) -> s2.length() - s1.length() == 0 ? s1.compareTo(s2) : s2.length() - s1.length()
                );
        Object[] strings = sorted.toArray();
        for (Object string : strings) {
            System.out.println(string);
        }
        for (Object o : strings) {
            boolean flag = true;
            String string = (String) o;
            for (int i = 0; i < string.length(); i++) {
                if (!isExist(string.charAt(i), s)) flag = false;
            }
            if (flag) return string;
        }
        return "";
    }

    public boolean isExist(Character c, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (c == s.charAt(i)) return true;
        }
        return false;
    }

    /**
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * <p>
     * 给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    @Test
    public void findPeakElement() {
        int[] nums = {1, 5, 7, 4};
        int i = findPeakElement(nums);
        System.out.println(i);
    }

    @Test
    public void findMin() {
        int[] nums = {3, 3, 1, 3};
        int i = findMin(nums);
        System.out.println(i);
    }

    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                r--;
            }
        }
        return nums[l];
    }

    /**
     * 36.有效数独
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        //判断九宫格
        char[] chars = new char[9];
        for (int y = 0; y < 9; y += 3) {
            for (int x = 0; x < 9; x += 3) {
                for (int i = 0; i < chars.length; i++) {
                    chars[i] = board[y + i / 3][x + i % 3];
                }
                if (isExist(chars)) return false;
            }
        }
        //判断横向
        for (char[] c : board) {
            if (isExist(c)) return false;
        }
        //判断纵向

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                chars[j] = board[j][i];
            }
            if (isExist(chars)) return false;
        }
        return true;
    }

    public boolean isExist(char[] c) {
        Set set = new HashSet();
        for (char c1 : c) {
            if (c1 != '.') {
                if (set.contains(c1)) return true;
                set.add(c1);
            }
        }
        return false;
    }

    @Test
    public void isValidSudoku() {
        char[][] c = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        boolean validSudoku = isValidSudoku(c);
        System.out.println(validSudoku);
    }

    /**
     * 1. 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return new int[0];
    }

    @Test
    public void twoSum() {
        int[] arr = {1, 2, 5, 54, 8, 41, 3, 2, 3};
        int[] ints = twoSum(arr, 7);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 326. 3的幂次方
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        while (n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }

    @Test
    public void isisPowerOfThree() {
        int target = 81 * 2;
        System.out.println(isPowerOfThree(target));
    }

    /**
     * @param digits 加一
     * @return
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i] + 1;
            if (digits[i] != 10) {
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] arr = new int[digits.length + 1];
        arr[0] = 1;
        System.arraycopy(digits, 0, arr, 1, arr.length - 1);
        return arr;
    }

    @Test
    public void plusOne() {
        int[] arr = {3, 9, 9};
        System.out.println(Arrays.toString(plusOne(arr)));
    }

    /**
     * 517
     *
     * @param machines
     * @return
     */
    public int findMinMoves(int[] machines) {
        int sum = Arrays.stream(machines).sum();
        if (sum % machines.length != 0) return -1;
        int per = sum / machines.length;
        return -1;
    }

    @Test
    public void findRepeatedDnaSequences() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> list = findRepeatedDnaSequences(s);
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * 187 重复DNA序列
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<>();
        int l = 10;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - l; i++) {
            String str = s.substring(i, l + i);
            map.put(str, map.getOrDefault(str, 0) + 1);
            if (map.get(str) == 2) list.add(str);
        }
        return list;
    }

    /**
     * 223
     *
     * @param ax1
     * @param ay1
     * @param ax2
     * @param ay2
     * @param bx1
     * @param by1
     * @param bx2
     * @param by2
     * @return
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int s = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
//        不相交
        if (ax2 <= bx1 || bx2 <= ax1 || by2 <= ay1 || ay2 <= by1) return s;
//        包含
        if (ax2 <= bx2 && ay2 <= by2 && ax1 >= bx1 && ay1 >= by1) {
            return (bx2 - bx1) * (by2 - by1);
        }
        if (ax2 >= bx2 && ay2 >= by2 && ax1 <= bx1 && ay1 <= by1) {
            return (ax2 - ax1) * (ay2 - ay1);
        }
        int are = (by2 - ay1) * (ax2 - bx1);
        return s - are;
    }

    @Test
    public void licenseKeyFormatting() {
        String s = "--a-a-a-a--";
        System.out.println(licenseKeyFormatting(s, 2));
    }

    /**
     * 482
     *
     * @param s
     * @param k
     * @return
     */
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                sb.append(s.charAt(i));
                cur++;
                if (cur % k == 0) sb.append("-");
            }
        }
        if (sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.reverse().toString().toUpperCase();
    }

    @Test
    public void third() {
        int[] arr = {1, 2, 2, 3};
        int a = thirdMax(arr);
        System.out.println(a);
    }

    /**
     * 414
     *
     * @param nums
     * @return
     */
    private int thirdMax(int[] nums) {
        long INF = (long) -1e18;
        long first = INF, second = INF, third = INF;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > first) {
                third = second;
                second = first;
                first = nums[i];
            } else if (nums[i] > second && nums[i] < first) {
                third = second;
                second = nums[i];
            } else if (nums[i] > third && nums[i] < second) {
                third = nums[i];
            }
        }
        return third != INF ? (int) third : (int) first;
    }

    /**
     * 383
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        for (int i = 0; i < ransomNote.length(); i++) {
            char r = ransomNote.charAt(i);
        }
        return false;
    }

    @Test
    public void peakIndexInMountainArray() {
        int[] arr = {0, 1, 2, 1};
        int i = peakIndexInMountainArray(arr);
        System.out.println(i);
    }

    /**
     * 069
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1, hand, ans = -1;
        while (l < r) {
            hand = (r - l) / 2 + l;
            if (arr[hand] > arr[hand - 1]) {
                ans = hand;
                l = hand + 1;
            } else r = hand;
        }
        return ans;
    }

    @Test
    public void countAndSay() {
        for (int i = 1; i < 11; i++) {
            String s = countAndSay(i);
            System.out.println(s);
        }
    }

    /**
     * 38
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        int i = 0;
        String ans = "1";
        while (i < n - 1) {
            i++;
            char c = ans.charAt(0);
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < ans.length(); j++) {
                if (ans.charAt(j) == c) {
                    count++;
                } else {
                    c = ans.charAt(j);
                    sb.append(count).append(ans.charAt(j - 1));
                    count = 0;
                    j--;
                }
            }
            sb.append(count).append(ans.charAt(ans.length() - count));
            ans = sb.toString();
        }
        return ans;
    }

    @Test
    public void minMoves() {
        int[] nums = {-3, 8, 9};
        int i = minMoves(nums);
        System.out.println(i);
    }

    /**
     * 453
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int count = 0;
        while (true) {
            int sum = Arrays.stream(nums).sum();
            if (sum % nums.length == 0) {
                boolean equal = isEqual(nums);
                if (equal) return count;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 1; i++) {
                nums[i] = nums[i] + 1;
            }
            count++;
        }
    }

    public boolean isEqual(int[] nums) {
        for (int num : nums) {
            if (num != nums[0]) return false;
        }
        return true;
    }

    @Test
    public void major() {
        int[] nums = {1, 2, 2, 2, 1};
        int aim = majorityElement(nums);
        System.out.println(aim);
        Date now = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        System.out.println(dateFormat.format(now));
    }

    /**
     * @param nums 169
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > nums.length / 2) return num;
        }
        return -1;
    }

    /**
     * 575
     *
     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {
        int max = candyType.length / 2;
        Set candy = new HashSet();
        for (int i = 0; i < candyType.length; i++) {
            candy.add(candyType[i]);
            if (candy.size() == max) return max;
        }

        return candy.size();
    }

    /**
     * @param node
     */
    public void deleteNode(ListNode node) {
        while (node.next != null) {
            node.val = node.next.val;
            node = node.next;
            if (node.next.next != null) {
                node = node.next;
            } else {
                node.next = null;
            }
        }
    }

    @Test
    public void longestSubsequence() {
        int[] arr = {6, -2, 0, 3, -7, 6, -5, -8};
        int a = longestSubsequence(arr, -5);
        System.out.println(a);
    }

    /**
     * 1218
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        int length = arr.length;
        if (length == 1) return 1;
        int sum = 1;
        for (int i = 0; i < arr.length; i++) {
            int bef = arr[i];
            int count = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] - bef == difference) {
                    count++;
                    bef = arr[j];
                }
            }
            if (count > sum) {
                sum = count;
            }
        }
        return sum;
    }

    @Test
    public void getHint() {
        String secret = "1", guess = "0";
        System.out.println(getHint(secret, guess));
    }

    /**
     * 299
     *
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        // secret = "1123", guess = "0111"
        StringBuilder sb = new StringBuilder();
        int Bulls = 0;
        int Cows = 0;
        Map<Character, Integer> s = new HashMap<>(1024);
        Map<Character, Integer> g = new HashMap<>(1024);
        for (int i = 0; i < secret.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) Bulls++;
            else {
                s.put(secret.charAt(i), s.getOrDefault(secret.charAt(i), 0) + 1);
                g.put(guess.charAt(i), g.getOrDefault(guess.charAt(i), 0) + 1);
            }
        }
        for (Character character : g.keySet()) {
            Cows = Cows + Math.min(g.get(character), s.getOrDefault(character, 0));
        }
        return sb.append(Bulls).append("A").append(Cows).append("B").toString();
    }

    /**
     * 268
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) return i;
        }
        return -1;
    }

    /**
     * 495
     *
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (duration == 0) return 0;
        if (timeSeries.length == 1) return duration;
        int count = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            int before = timeSeries[i];
            int behind = timeSeries[i + 1];
            if (behind - before >= duration) {
                count = duration + count;
            } else {
                count = count + behind - before;
            }
        }
        count += duration;
        return count;
    }

    /**
     * 375
     *
     * @param n 1,2,3,4,5,6,7,8,9
     * @return
     */
    public int getMoneyAmount(int n) {
        int i = 0;
        int count = 0;
        while (i < n) {

            i++;
        }
        return 0;
    }

    /**
     * 520
     *
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        if (word.charAt(0) < 97) {
            return oneMethod(word);
        } else return towMethod(word);
    }

    boolean oneMethod(String word) {
        int Acount = 0;
        int acount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) >= 97) {
                acount++;
                if (Acount > 1) return false;
            } else {
                Acount++;
                if (acount >= 1) return false;
            }
        }
        return true;
    }

    boolean towMethod(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) < 97) return false;
        }
        return true;
    }

    @Test
    public void mapTest() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Eric");
        map.put("age", "21");
        map.forEach((key, value) ->
                System.out.println(key + ":" + value)
        );
    }

    /**
     * 400
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int len = 1;
        while (len * 9 * Math.pow(10, len - 1) < n) {
            n -= len * 9 * Math.pow(10, len - 1);
            len++;
        }
        int x = n / len;
        int y = n % len;
        if (y == 0) return (int) (Math.pow(10, len - 1) + x - 1) % 10;
        return (int) ((Math.pow(10, len - 1) + x) % (Math.pow(10, len - y + 1)) / (Math.pow(10, len - y)));
    }

    /**
     * 1446
     *
     * @param s
     * @return
     */
    public int maxPower(String s) {
        int max = 1;
        int count = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 1;
            }
        }
        return max;
    }

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     *
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if (null == root) return new int[0];
        Queue<TreeNode> queue = new LinkedList() {
            {
                add(root);
            }
        };
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            ans.add(treeNode.val);
            if (treeNode.right != null) queue.add(treeNode.right);
            if (treeNode.left != null) queue.add(treeNode.left);
        }
        int[] arr = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            arr[i] = ans.get(i);
        }
        return arr;
    }

    /**
     * 506
     *
     * @param score
     * @return
     */
    public String[] findRelativeRanks(int[] score) {
        String[] s = new String[score.length];
        int min = 0;
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score.length; j++) {
                if (score[i] < score[j]) min++;
            }
            switch (min) {
                case 0:
                    s[i] = "Gold Medal";
                    break;
                case 1:
                    s[i] = "Silver Medal";
                    break;
                case 2:
                    s[i] = "Bronze Medal";
                    break;
                default:
                    s[i] = String.valueOf((min - 1));
            }
            min = 0;
        }
        return s;
    }

    /**
     * 794.有效的井字游戏
     *
     * @param board
     * @return x-1=o或o=x
     * 3有且只有一个
     */
    public boolean validTicTacToe(String[] board) {
        int x = 0, o = 0, t = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                char c = board[i].charAt(j);
                //纵
                if (board.length - i >= 3) {
                    if (c != ' ' && c == board[i + 1].charAt(j) && c == board[i + 2].charAt(j)) {
                        t++;
                    }
                }
                //横
                if (board[i].length() - j >= 3) {
                    if (c != ' ' && c == board[i].charAt(j + 1) && c == board[i].charAt(j + 2)) {
                        t++;
                    }
                }
                //斜
                if (board.length - i >= 3 && board[i].length() - j >= 3) {
                    if (c != ' ' && c == board[i + 1].charAt(j + 1) && c == board[i + 2].charAt(j + 2)) {
                        t++;
                    }
                }
                if (c == 'X') x++;
                if (c == 'O') o++;
            }
        }
        if (t == 1) return o == x;
        return false;
    }

    @Test
    public void validTicTacToe() {
        String[] s = {"XXX", "OOX", "OOX"};
        boolean b = validTicTacToe(s);
    }

    /**
     * 851
     *
     * @param richer
     * @param quiet
     * @return
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int[] ans = new int[quiet.length];
        //map get方法，源码用迭代器遍历，时间复杂度增加
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] ints : richer) {
            int a = ints[0];
            int b = ints[1];
            if (null == map.get(b)) {
                List<Integer> list = new ArrayList<>();
                list.add(a);
                map.put(b, list);
            } else {
                map.get(b).add(a);
            }
        }
        Arrays.fill(ans, -1);
        for (int i = 0; i < ans.length; i++) {
            minLoud(map, ans, i, quiet);
        }
        return ans;
    }

    void minLoud(Map<Integer, List<Integer>> map, int[] ans, int i, int[] quiet) {
        if (ans[i] != -1) {
            return;
        }
        ans[i] = i;
        List<Integer> list = map.get(i);
        if (list != null) {
            for (int integer : list) {
                minLoud(map, ans, integer, quiet);
                int q = quiet[ans[integer]];
                int a = quiet[ans[i]];
                if (q < a) {
                    ans[i] = ans[integer];
                }
            }
        }
    }

    @Test
    public void loudAndRich() {
        int[][] arr = new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = new int[]{3, 2, 5, 4, 6, 1, 7, 0};
        loudAndRich(arr, quiet);
    }

    /**
     * 463
     *
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //为陆地开始搜索所有岛屿并计算周长
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return -1;
    }

    int dfs(int[][] grid, int i, int j) {
        //左上右下
        int[] y = new int[]{1, 0, -1, 0};
        int[] x = new int[]{0, -1, 0, 1};
        //水域边长+1
        if (grid[i][j] == 0) return 1;
        //边界+1
        if (j >= grid[0].length || i < 0 || i >= grid.length || j < 0) return 1;
        if (grid[i][j] == 2) return 0;
        //标记已遍历
        grid[i][j] = 2;
        int res = 0;
        for (int k = 0; k < 4; k++) {
            res += dfs(grid, i + x[k], j + y[k]);
        }
        return res;
    }

    /**
     * 1518
     *
     * @param numBottles
     * @param numExchange
     * @return
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int sum = numBottles;
        int empty = numBottles;
        while (empty >= numExchange) {
            int num = empty / numExchange;
            sum += num;
            empty = empty - num * numExchange + num;
        }
        return sum;
    }

    /**
     * 475
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        Arrays.sort(houses);
        int maxR = 0;
        int cur = 0;
        for (int house : houses) {
            int minR = Integer.MAX_VALUE;
            for (int j = cur; j < heaters.length; j++) {
                if (heaters[j] >= house) {
                    minR = Math.min(minR, Math.abs(house - heaters[j]));
                    cur = Math.max(0, j - 1);
                    break;
                }
                minR = Math.min(minR, Math.abs(house - heaters[j]));
            }
            maxR = Math.max(maxR, minR);
        }
        return maxR;
    }

    @Test
    public void findR() {
        int[] houses = {282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923};
        int[] heaters = {823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612};
        int radius = findRadius(houses, heaters);
    }

    /**
     * 1154
     *
     * @param date
     * @return"2021-01-12"
     */
    public int dayOfYear(String date) {
        String[] s = date.split("-");
        date.substring(1, 4);
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int day = 0;
        int y = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int d = Integer.parseInt(s[2]);
        if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) days[1]++;
        for (int i = 0; i < m - 1; i++) {
            day += days[i];
        }
        return day + d;
    }

    /**
     * 15
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length < 3) return lists;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            int first = nums[i];
            if (first > 0) return lists;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                int c = nums.length - 1;
                int second = nums[j];
                if (j > 1 && nums[j] == nums[j - 1]) continue;
                while (c > j) {
                    int third = nums[c];
                    if (first + second + third == 0) {
                        list.add(first);
                        list.add(second);
                        list.add(third);
                        lists.add(list);
                        break;
                    }
                    c--;
                }
            }
        }
        return lists;
    }

    /**
     * 686
     *
     * @param a
     * @param b
     * @return
     */
    public int repeatedStringMatch(String a, String b) {
        boolean flag = true;
        int i = 0, j = 0, s = 0;
        int count = 1;
        while (flag) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
                j++;
                s++;
            } else {
                i = i - s + 1;
                while (i < 0) {
                    i = i + a.length();
                    count--;
                }
                j = 0;
                s = 0;
            }
            if (j == b.length()) flag = false;
            if (i == a.length()) {
                if (flag) {
                    if ((count - 1) * a.length() >= b.length()) return -1;
                    count++;
                }
                i = 0;
            }
        }
        return count;
    }

    @Test
    public void repeatedStringMatch() {
        String ans = "/home//foo/";
        String[] split = ans.split("/");
        String a = "abcd";
        String b = "cdabcdacdabcda";
        int i = repeatedStringMatch(a, b);
        System.out.println(i);
    }

    /**
     * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
     * age[y] <= 0.5 * age[x] + 7
     * age[y] > age[x]
     * age[y] > 100 && age[x] < 100
     * 否则，x 将会向 y 发送一条好友请求。
     * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
     *
     * @param ages
     * @return
     */
    public int numFriendRequests(int[] ages) {
        //排序
        Arrays.sort(ages);
        return -1;
    }

    /**
     * 846
     *
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            queue.add(i);
        }
        while (!queue.isEmpty()) {
            Integer top = queue.poll();
            if (map.get(top) == 0) continue;
            for (int i = 0; i < groupSize; i++) {
                int cur = map.getOrDefault(top + i, 0);
                if (cur == 0) return false;
                map.put(top + i, cur - 1);
            }
        }

        return true;
    }

    /**
     * 53 二分
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int count = 0;
        int index = index(nums, target);
        if (index == -1) return count;
        if (nums[index] == target) {
            count++;
            int n = index;
            while (n - 1 >= 0 && nums[n - 1] == target) {
                count++;
                n = n - 1;
            }
            int m = index;
            while (m + 1 < nums.length && nums[m + 1] == target) {
                count++;
                m = m + 1;
            }
        }
        return count;
    }

    public int index(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int hand = l + (r - l) / 2;
            if (nums[hand] > target) {
                r = hand - 1;
            } else if (nums[hand] == target) {
                return hand;
            } else {
                l = hand + 1;
            }
        }
        return -1;
    }

    /**
     * 53
     *
     * @param nums
     * @return
     */
    public int missingNumber1(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int hand = l + (r - l) / 2;
            if (nums[hand] > hand) {
                r = hand - 1;
            } else if (nums[hand] == hand) {
                l = hand + 1;
            }
        }
        return l;
    }

    /**
     * 172
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += count(i);
        }
        return sum;
    }

    public int count(int n) {
        if (n % 3125 == 0) return 5;
        if (n % 625 == 0) return 4;
        if (n % 125 == 0) return 3;
        if (n % 25 == 0) return 2;
        if (n % 5 == 0) return 1;
        return 0;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1;
        int j = 0;
        while (i > -1 && j < matrix[i].length) {
            if (matrix[i][j] < target) {
                i--;
            } else if (matrix[i][j] > target) {
                j++;
            } else return true;
        }
        return false;
    }

    @Test
    public void searchMatrix() {
        int[][] ans = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        boolean b = searchMatrix(ans, 3);
    }

    public char firstUniqChar(String s) {
        int[] ans = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ans[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (ans[s.charAt(i) - 'a'] == 1) return (char) (s.charAt(i) + 'a');
        }
        return ' ';
    }

    @Test
    public void firstUniqChar() {
        System.out.println((char) ('c' + 'a'));
    }

    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        ;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (numbers[m] < numbers[r]) {
                r = m;
            } else if (numbers[m] > numbers[r]) {
                l = m + 1;
            } else {
                //TODO 去重，难点
                r--;
            }
        }
        return l;
    }

    @Test
    public void minArray() {
        int[] nums = {3, 1, 3};
        minArray(nums);
    }

    /**
     * 334.解法：贪心
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < first) {
                first = num;
                continue;
            } else if (num < second) {
                second = num;
                continue;
            }
            return true;
        }
        return false;
    }

    /**
     * 747.贪心
     *
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        boolean flag = true;
        int first = -1;
        int second = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > second) {
                first = second;
                second = nums[i];
                index = i;
                flag = second - first >= first;
            } else if (nums[i] > first) {
                first = nums[i];
                flag = second - first >= first;
            }
        }
        return flag ? index : -1;
    }

    @Test
    public void dominantIndex() {
        int[] arr = {1, 2, 3, 4};
        int i = dominantIndex(arr);
    }

    public int[] levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public List<List<Integer>> levelOrder01(TreeNode root) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        if (null == root) return lists;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            lists.add(list);
        }
        return lists;
    }

    public List<List<Integer>> levelOrder02(TreeNode root) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        if (null == root) return lists;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            if (i / 2 == 0) {
                Stack<Integer> stack = new Stack<>();
                for (int size = queue.size(); size > 0; size--) {
                    TreeNode node = queue.poll();
                    stack.add(node.val);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
                while (!stack.empty()) {
                    list.add(stack.pop());
                }
            } else {
                for (int size = queue.size(); size > 0; size--) {
                    TreeNode node = queue.poll();
                    list.add(node.val);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }
            lists.add(list);
            i++;
        }
        return lists;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(left);
        return root;
    }

    @Test
    public void mirrorTree() {
        TreeNode node = new TreeNode(4);
        TreeNode left = new TreeNode(1);
        left.left = new TreeNode(2);
        left.right = new TreeNode(8);
        node.left = left;
        TreeNode right = new TreeNode(7);
        right.left = new TreeNode(3);
        right.right = new TreeNode(9);
        node.right = right;
        TreeNode node1 = mirrorTree(node);
    }

    /**
     * 斐波那契
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) return 0;
        int fn = 0, fn1 = 1;
        int i = 2;
        while (i < n) {
            i++;
            int temp = fn1;
            fn1 = fn1 + fn;
            fn = temp;
        }
        return fn1 % 1000000007;
    }

    /**
     * 121
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - min > max) {
                max = prices[i] - min;
            } else min = prices[i];
        }
        return max;
    }

    /**
     * 1201
     *
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        int[] ans = new int[n + 1];
        ans[0] = 1;
        for (int i = 1, x1 = 0, x2 = 0, x3 = 0; i < n; i++) {
            int x = a * ans[x1];
            int y = a * ans[x2];
            int z = a * ans[x3];
            int k = Math.min(z, Math.min(x, y));
            if (x == k) x1++;
            if (y == k) x2++;
            if (z == k) x3++;
            ans[i] = k;
        }
        return ans[n];
    }

    public int translateNum(int num) {
        String s = Integer.toString(num);
        int x = 0, y = 0;
        if (s.charAt(0) == '1' || s.charAt(0) == '2') {
            x = 1;
        } else y = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '1' || s.charAt(i) == '2') {
                int temp = x;
                x = x + y;
                y = temp;
            } else if (s.charAt(i) > '2' && s.charAt(i) < '6' || s.charAt(i) == '0') {
                y = y + 2 * x;
                x = 0;
            } else {
                if (s.charAt(i - 1) == '1') {
                    y = y + 2 * x;
                } else {
                    y = y + x;
                }
                x = 0;
            }
        }
        return x + y;
    }

    @Test
    public void translateNum() {
        int num = 3412612;
        translateNum(num);
    }

    public int lengthOfLongestSubstring(String s) {
        //dp[i]
        int temp = 0, max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int j = map.getOrDefault(s.charAt(i), -1);
            map.put(s.charAt(i), i);//更新索引
            temp = temp < i - j ? temp + 1 : i - j;//dp[i-1]->dp[i] 状态转移方程
            max = Math.max(max, temp);
        }
        return max;
    }

    @Test
    public void lengthOfLongestSubstring() {
        String s = "sssqwertyuiopasdfghjklzxcvbnmm";
//        s="abcabcbb";
        lengthOfLongestSubstring(s);
    }

    public int countVowelPermutation(int n) {
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        int mod = (int) 1e9 + 7;
        for (int j = 1; j < n; j++) {
            long a1 = (e + u + i) % mod;
            long e1 = (a + i) % mod;
            long i1 = (e + o) % mod;
            long o1 = i % mod;
            long u1 = (o + i) % mod;
            a = a1;
            e = e1;
            i = i1;
            o = o1;
            u = u1;
        }
        return (int) (a + e + i + o + u) % mod;
    }

    /**
     * abacoocab
     * aabbaaa
     * abaab
     * aaaaaaaaaa
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int[] dp = new int[s.length()];
        dp[0] = 1;
        int max = 0;
        String maxS = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) dp[i] = dp[i - 1] + 1;
        }
        return maxS;
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode cur = head;
        ListNode next = head.next;
        while (next != null) {
            if (next.val == val) {
                cur.next = next.next;
                break;
            }
            cur = next;
            next = next.next;
        }
        return head;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;
        int[][] dp = new int[r][c];
        dp[0][0] = 1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 && j > 0) {
                    if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                    else dp[i][j] = dp[i][j - 1];
                } else if (i > 0 && j == 0) {
                    if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                    else dp[i][j] = dp[i - 1][j];
                } else if (i > 0) {
                    if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                    else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[r - 1][c - 1];
    }

    @Test
    public void uniquePathsWithObstacles() {
        int[][] ans = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        uniquePathsWithObstacles(ans);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode first = l1;
        ListNode second = l2;
        ListNode head = new ListNode(0, new ListNode(2));
        ListNode cur = head;
        while (first != null && second != null) {
            if (first.val > second.val) {
                cur.next = new ListNode(second.val, new ListNode(2));
                first = first.next;
            } else {
                cur.next = new ListNode(first.val, new ListNode(2));
                second = second.next;
            }
            cur = cur.next;
        }
        if (first == null) {
            cur.next = second;
        } else cur.next = first;
        return head.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        while (A != B) {
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return headA;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) return true;
            if (i > k) {
                set.remove((long) nums[i - k - 1]);
            }
            set.add((long) nums[i]);
        }
        return false;
    }

    public int minSteps(int n) {
        int count = 0;
        for (int i = 2; i < n * n; i++) {
            while (n % i == 0) {
                count = count + i;
                n = n / i;
            }
        }
        if (n > 1) return n;
        return count;
    }

    public String reverseWords(String s) {
        String ans = s.trim();
        return null;
    }

    @Test
    public void reverseWords() {
        String s = "a good   example";
        reverseWords(s);
    }

    public int minSubArrayLen(int target, int[] nums) {
        int r = 0, min = Integer.MAX_VALUE, sum = 0, l = 0;
        while (r < nums.length) {
            sum += nums[r];
            while (sum >= target && l < nums.length) {
                sum -= nums[l];
                min = Math.min(min, r - l + 1);
                l++;
            }
            r++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    @Test
    public void minSubArrayLen() {
        int[] ans = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        List<Integer> a = new ArrayList<>();
        a = Arrays.stream(ans).boxed().collect(Collectors.toList());
        Collections.sort(a);
        int i = minSubArrayLen(213, ans);
    }

    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, chars, 0, i, j)) return true;
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] word, int k, int i, int j) {
        //边界
        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word[k]) return false;
        //污染
        board[i][j] = '1';
        //dfs
        boolean flag = dfs(board, word, k + 1, i - 1, j) || dfs(board, word, k + 1, i + 1, j)
                || dfs(board, word, k + 1, i, j - 1) || dfs(board, word, k + 1, i, j + 1);
        //治理
        board[i][j] = word[k];
        return flag;
    }

    /**
     * bfs
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] grid = new boolean[m][n];
        return dfsMovingCount(grid, 0, 0, k);
    }

    int dfsMovingCount(boolean[][] grid, int i, int j, int k) {
        int ans = i / 100 + (i % 100) / 10 + i % 10 + j / 100 + (j % 100) / 10 + j % 10;
        if (i >= grid.length || j >= grid[i].length || grid[i][j] || k < ans) return 0;
        grid[i][j] = true;
        return 1 + dfsMovingCount(grid, i + 1, j, k) + dfsMovingCount(grid, i, j + 1, k);
    }


    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }

    public List<List<Integer>> pathSum1(TreeNode root, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        dfs(root, lists, list);
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> integers = lists.get(i);
            int sum = 0;
            for (Integer integer : integers) {
                sum += integer;
            }
            if (sum != target) {
                lists.remove(i);
                i--;
            }
        }
        return lists;
    }

    void dfs(TreeNode root, List<List<Integer>> lists, List<Integer> list) {
        if (root != null) {
            List<Integer> nList = new ArrayList<>(list);
            nList.add(root.val);
            if (root.right == null && root.left == null) lists.add(nList);
            dfs(root.left, lists, nList);
            dfs(root.right, lists, nList);
        }
    }

    public int uniquePaths1(int m, int n) {
        n = m + n - 2;
        m = m - 1;
        long n1 = 1L, m1 = 1L;
        for (int i = 1; i <= m; i++) {
            m1 *= i;
            n1 *= n - i + 1;
        }
        return (int) (n1 / m1);
    }

    @Test
    public void movingCount() {
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(0);
        TreeNode root = new TreeNode(6, new TreeNode(2, q,
                new TreeNode(4, new TreeNode(3), p))
                , new TreeNode(8, new TreeNode(7), new TreeNode(9)));
        lowestCommonAncestor(root, p, q);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    void dfs(TreeNode root, LinkedList<TreeNode> list, TreeNode t, LinkedList<TreeNode> temp) {
        if (root != null) {
            if (root == t) {
                list.add(root);
                temp.addAll(list);
            }
            list.add(root);
            dfs(root.left, list, t, temp);
            dfs(root.right, list, t, temp);
            list.removeLast();
        }
    }

    public String addBinary(String a, String b) {
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        char[] res = new char[Math.max(a1.length, b1.length)];
        Arrays.fill(res, '0');
        int flag = 0;
        int i = res.length - 1, j = a1.length - 1, k = b1.length - 1;
        for (; i >= 0 && j >= 0 && k >= 0; i--, j--, k--) {
            if (a1[j] == '0' && b1[k] == '0') {
                res[i] = (char) ('0' + flag);
                flag = 0;
            } else if (a1[j] == '1' && b1[k] == '1') {
                res[i] = (char) ('0' + flag);
                flag = 1;
            } else {
                if (flag == 1) res[i] = '0';
                else {
                    res[i] = '1';
                    flag = 0;
                }
            }
        }
        while (j >= 0 && k < 0) {
            res[i] = (char) (a1[j] + flag);
            if (res[i] == '2') {
                res[i] = '0';
                flag = 1;
            }
            j--;
            i--;
        }
        while (j < 0 && k >= 0) {
            res[i] = (char) (b1[k] + flag);
            if (res[i] == '2') {
                res[i] = '0';
                flag = 1;
            }
            k--;
            i--;
        }
        return res[0] == '0' ? "1" + String.valueOf(res) : String.valueOf(res);
    }

    public int maxProduct(String[] words) {
        int max = 0;
        int[] num = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                num[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if ((num[i] & num[j]) == 0) max = Math.max(words[i].length() * words[j].length(), max);
            }
        }
        return max;
    }

    public int[] twoSum1(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) return new int[]{l, r};
            else if (numbers[l] + numbers[r] > target) r--;
            else l++;
        }
        return null;
    }

    public int singleNumber(int[] nums) {
        int[] res = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                res[i] += (num >> (31 - i)) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans += (ans << 1) + res[i] % 3;
        }
        return ans;
    }

    public int[] countBits(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = OneNum(i);
        }
        return res;
    }

    int OneNum(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) - 'a' > 2 || s.charAt(i - 1) - 'a' == 0) dp[i] = dp[i - 1];
            else if (s.charAt(i - 1) - 'a' == 1) dp[i] = dp[i - 1] * 2;
            else dp[i] = s.charAt(i) - 'a' > 6 ? dp[i - 1] : dp[i - 1] * 2;
        }
        return dp[s.length() - 1];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        int n = triangle.size();
        int[] res = new int[triangle.get(n - 1).size()];
        res[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = list.size() - 1; j >= 0; j--) {
                if (j == list.size() - 1) res[j] = res[j - 1] + list.get(j);
                else if (j == 0) res[j] = res[j] + list.get(j);
                else res[j] = list.get(j) + Math.min(res[j], res[j - 1]);
            }
        }
        for (int re : res) {
            min = Math.min(re, min);
        }
        return min;
    }

    @Test
    public void mini() {
        boolean a = validPalindrome("assas");
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        //[-4,-1,-1,0,1,2]
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            //减少遍历
            if (first > 0) return lists;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int k = nums.length - 1;
            for (int j = i + 1; j < nums.length; j++) {
                int second = nums[j];
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                while (k > j && first + second > -nums[k]) k--;
                if (k == j) break;
                if (first + second + nums[k] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(first);
                    list.add(second);
                    list.add(nums[k]);
                    lists.add(list);
                }
            }
        }
        return lists;
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length, min = Integer.MAX_VALUE;
        if (n == 1) return matrix[0][0];
        int[][] res = new int[2][n];
        res[0] = matrix[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) res[i & 1][j] = matrix[i][j] + Math.min(res[(i - 1) & 1][j], res[(i - 1) & 1][j + 1]);
                else if (j == n - 1)
                    res[i & 1][j] = matrix[i][j] + Math.min(res[(i - 1) & 1][j], res[(i - 1) & 1][j - 1]);
                else
                    res[i & 1][j] = matrix[i][j] + Math.min(Math.min(res[(i - 1) & 1][j], res[(i - 1) & 1][j + 1]), res[(i - 1) & 1][j - 1]);
            }
            //地址引用
        }
        for (int re : res[(n - 1) & 1]) {
            min = Math.min(min, re);
        }
        return min;
    }

    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int al = s.charAt(i);
            if (al >= 65 && al <= 90) {
                sb.append((char) (s.charAt(i) + 32));
            } else if (al >= 97 && al <= 122 || al >= 48 && al <= 57) {
                sb.append(s.charAt(i));
            }
        }
        int l = 0, r = sb.length() - 1;
        while (l < r) {
            if (sb.charAt(l++) != sb.charAt(r--)) return false;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s, l, r - 1) || isPalindrome(s, l + 1, r);
            }
            l++;
            r--;
        }
        return true;
    }

    boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    public int countSubstrings(String s) {

        return 1;
    }

    public String minWindow(String s, String t) {
        int[] res = new int[60];
        String ans = "";
        int min = 100001, left = 0;
        for (int i = 0; i < t.length(); i++) {
            res[t.charAt(i) - 'A']--;
        }
        for (int right = 0; right < s.length(); right++) {
            res[s.charAt(right) - 'A']++;
            while (isOk(res) && left <= right) {
                if (min > right - left + 1) {
                    min = right - left + 1;
                    ans = s.substring(left, right + 1);
                }
                res[s.charAt(left++) - 'A']--;
            }
        }
        return ans;
    }

    boolean isOk(int[] res) {
        for (int num : res) {
            if (num < 0) return false;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        ListNode node = new ListNode(0, new ListNode(2));

        return reverse(head, node).next;
    }

    public ListNode reverse(ListNode head, ListNode temp) {
        if (head != null) {
            ListNode node = reverse(head.next, temp);
            node.next = head;
        }
        return temp.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> a = new LinkedList<>();
        Deque<Integer> b = new LinkedList<>();
        int g, s = 0;
        ListNode dummy = null;
        while (l1 != null) {
            a.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            b.push(l2.val);
            l2 = l2.next;
        }
        while (!a.isEmpty() || !b.isEmpty() || s != 0) {
            int x = a.isEmpty() ? 0 : a.pop();
            int y = b.isEmpty() ? 0 : b.pop();
            g = (x + y + s) % 10;
            s = (x + y + s) / 10;
            ListNode node = new ListNode(g, new ListNode(2));
            node.next = dummy;
            dummy = node;
        }
        return dummy;
    }

    public void reorderList(ListNode head) {
        ListNode temp = head;
        ListNode mid = mid(head);
        ListNode l2 = reverse(mid.next);
        ListNode l1 = head;
//        掐断
        mid.next = null;
        while (l1 != null && l2 != null) {
            ListNode t1 = l1.next;
            ListNode t2 = l2.next;

            l1.next = l2;
            l1 = t1;

            l2.next = l1;
            l2 = t2;
        }
    }

    public ListNode mid(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        ListNode per = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = per;
            per = cur;
            cur = next;
        }
        return per;
    }

    void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_temp;
        ListNode l2_temp;
        while (l1 != null && l2 != null) {
            l1_temp = l1.next;
            l2_temp = l2.next;

            l1.next = l2;
            l1 = l1_temp;

            l2.next = l1;
            l2 = l2_temp;
        }
    }

    @Test
    public void re() {
        double a = 1.0;
        System.out.println(a / 8);
    }

    public Node flatten(Node head) {
        if (head == null) return null;
        List<Node> list = new ArrayList<>();
        dfs(list, head);
        Node temp = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            //切掉孩子节点
            temp.child = null;
            Node node = list.get(i);
            temp.next = node;
            node.prev = temp;
            //指针下移
            temp = node;
        }
        return list.get(0);
    }

    void dfs(List<Node> list, Node head) {
        if (head != null) {
            list.add(head);
            dfs(list, head.child);
            dfs(list, head.next);
        }
    }

    public Node insert(Node head, int insertVal) {
        Set<Node> set = new HashSet<>();
        List<Node> list = new ArrayList<>();
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node temp = head;
        while (!set.contains(temp)) {
            set.add(temp);
            list.add(temp);
            temp = temp.next;
        }
        Node per = list.get(0);
        if (list.size() == 1) {
            Node node = new Node(insertVal);
            per.next = node;
            node.next = per;
            return head;
        }
        for (int i = 1; i < list.size(); i++) {
            Node cur = list.get(i);
            //头尾
            if (per.val > cur.val) {
                if (insertVal >= per.val || insertVal <= cur.val) {
                    Node node = new Node(insertVal);
                    per.next = node;
                    node.next = cur;
                    return head;
                }
            } else {
                if (insertVal > per.val && insertVal < cur.val || per.val == insertVal) {
                    Node node = new Node(insertVal);
                    per.next = node;
                    node.next = cur;
                    return head;
                }
            }
            per = cur;
        }
        Node node = new Node(insertVal);
        per.next = node;
        node.next = list.get(0);
        return head;
    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int left = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.right != null) queue.offer(node.right);
            if (node.left != null) {
                queue.offer(node.left);
                left = node.left.val;
            }
        }
        return left;
    }

    int sum = 0;


    void dfs(TreeNode root) {
        //中序遍历
        if (root == null) return;
        dfs(root.left);
        System.out.println(root.val);
        dfs(root.right);
    }

    List<List<Integer>> lists;

    public int pathSum(TreeNode root, int targetSum) {
        lists = new ArrayList<>();
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        dfs(list, root);
        for (List<Integer> list1 : lists) {
            sum += count(list1, targetSum);
        }
        return sum;
    }

    int count(List<Integer> list, int target) {
        int count = 0;
        int[] sums = new int[list.size() + 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + list.get(i - 1);
            if (map.containsKey(sums[i] - target)) {
                count += map.get(sums[i] - target);
            }
            map.put(sums[i], map.getOrDefault(sums[i], 0) + 1);
        }
        return count;
    }

    void dfs(List<Integer> list, TreeNode root) {
        if (root == null) return;
        if (root.right == null && root.left == null) {
            lists.add(new ArrayList<>(list));
            System.out.println(Arrays.toString(list.toArray()));
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        dfs(list, root.left);
        dfs(list, root.right);
        list.remove(list.size() - 1);
    }

    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        Map<Integer, Integer> map = new HashMap<>();

        int[] res = new int[k];
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer integer : map.keySet()) {
            queue.add(new int[]{integer, map.get(integer)});
        }
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }
        return res;
    }

    public String convert(String s, int numRows) {
        int n = s.length(), j = 0, i = 0;
        if (n == 1 || n <= numRows) return s;
        StringBuilder[] res = new StringBuilder[numRows];
        for (int k = 0; k < res.length; k++) {
            res[k] = new StringBuilder();
        }
        int flag = 1;
        while (i < n) {
            if (j == 0 || j == numRows - 1) {
                flag = -flag;
            }
            res[j].append(s.charAt(i));
            j = j + flag;
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder re : res) sb.append(re);
        return sb.toString();
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        int l = 1, r = max;
        while (l < r) {
            int m = l + (r - l >> 1);
            if (count(m) > h) l = m + 1;
            else r = m - 1;
        }
        return r;
    }

    int count(int[] piles, int t) {
        int count = 0;
        for (int pile : piles) {
            count += (pile + t - 1) / t;
        }
        return count;
    }

    public int[] platesBetweenCandles(String s, int[][] queries) {
        List<Integer> list = new ArrayList<>();
        int[] ans = new int[queries.length];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') list.add(i);
        }
        if (list.size() == 0) return new int[]{};
        int[] res = new int[list.size()];
        res[0] = 0;
        for (int i = 1; i < res.length; i++) {
            res[i] = list.get(i) - list.get(i - 1) - 1 + res[i - 1];
        }
        for (int i = 0; i < ans.length; i++) {
            int[] query = queries[i];
            int l = left(list, query[0]);
            int r = right(list, query[1]);
            ans[i] = l >= r ? 0 : res[r] - res[l];
        }
        return ans;
    }

    int left(List<Integer> list, int t) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int m = l + (r - l >> 1);
            if (list.get(m) < t) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    int right(List<Integer> list, int t) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int m = l + (r - l >> 1);
            if (list.get(m) <= t) l = m + 1;
            else r = m - 1;
        }
        return r;
    }

    int[][] res;
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m;
    int n;

    public int[][] updateMatrix(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
        res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, mat);
            }
        }
        return res;
    }

    int dfs(int x, int y, int[][] mat) {
        if (x < 0 || x >= m || y < 0 || y >= n || mat[x][y] == -1) return 100000001;
        if (mat[x][y] == 0) return 0;
        if (res[x][y] != 0) return res[x][y];
        int min = Integer.MAX_VALUE;
        mat[x][y] = -1;
        for (int[] d : dir) {
            int a = dfs(x + d[0], y + d[1], mat);
            min = Math.min(a + 1, min);
        }
        mat[x][y] = 1;
        if (min < 100000001) res[x][y] = min;
        return min;
    }

    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String s : ops) {
            if (s.equals("C")) {
                stack.pop();
            } else if (s.equals("D")) {
                int t = stack.pop();
                stack.push(t);
            } else if (s.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return (int) stack.stream().count();
    }

    public String toGoatLatin(String sentence) {
        String[] str = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            if (isOk(str[i])) {
                sb.append(str[i]).append("ma").append(get(i)).append(" ");
            } else {
                String s = str[i].substring(1);
                sb.append(s).append(str[i].charAt(0)).append(get(i)).append(" ");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    boolean isOk(String str) {
        char c = str.charAt(0);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    StringBuilder get(int t) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= t; i++) {
            sb.append('a');
        }
        return sb;
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]));
        for (int i = 0; i < nums1.length; i++) {
            queue.add(new int[]{i, 0});
        }
        while (!queue.isEmpty() && lists.size() < k) {
            int[] poll = queue.poll();
            lists.add(new ArrayList() {
                {
                    add(nums1[poll[0]]);
                    add(nums2[poll[1]]);
                }
            });
            if (poll[1] + 1 < nums2.length) queue.add(new int[]{poll[0], poll[1] + 1});
        }
        return lists;
    }

    int count;
    int max;

    public int maxProduct1(String[] words) {
        TrieNode trieNode = new TrieNode();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            word = new String(chars);
            TrieNode temp = trieNode;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (temp.children[index] == null) temp.children[index] = new TrieNode();
                temp = temp.children[index];
            }
            temp.word = word;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (TrieNode child : trieNode.children) {
            if (child == null) continue;
            count = 1;
            max = 0;
            d(child);
            pq.add(max);
        }
        if (pq.size() < 2) return 0;
        return pq.poll() * pq.peek();
    }

    boolean isOk(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child == null) continue;
            return false;
        }
        return true;
    }

    void d(TrieNode node) {
        if (isOk(node)) {
            max = Math.max(count, max);
            return;
        }
        for (TrieNode child : node.children) {
            if (child == null) continue;
            count++;
            d(child);
            count--;
        }
    }

    public int maxConsecutiveAnswers(String answerKey, int k, char t) {
        int n = answerKey.length(), max = 0;
        int[] res = new int[n];
        int l = 0, r = 0, i = 0, j = 0;
        while (r < n) {
            if (answerKey.charAt(r) == t) r++;
            else if (k > 0) {
                res[i++] = r;
                k--;
            } else if (k == 0) {
                max = Math.max(r - l, max);
                l = res[j++];
                k++;
            }
        }
        max = Math.max(r - l, max);
        return max;
    }

    public boolean isValid(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') deque.push(c);
            else {
                if (deque.isEmpty()) return false;
                if (c == ')' && deque.pop() != '(') return false;
                if (c == ']' && deque.pop() != '[') return false;
                if (c == '}' && deque.pop() != '{') return false;
            }
        }
        return true;
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] res = new boolean[rooms.size()];
        res[0] = true;
        int count = 1;
        deque.add(0);
        while (!deque.isEmpty()) {
            int index = deque.poll();
            for (Integer integer : rooms.get(index)) {
                if (!res[integer]) {
                    deque.add(integer);
                    count++;
                    res[integer] = true;
                }
            }
        }
        return count == rooms.size();
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int k = i;
            boolean flag = true;
            while (k > 0) {
                if (k % 10 == 0) {
                    flag = false;
                    break;
                }
                if (i % (k % 10) != 0) {
                    flag = false;
                    break;
                }
                k = k / 10;
            }
            if (flag) list.add(i);
        }
        return list;
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n + 1];
        Set<Integer> set = new HashSet<>();
        res[1] = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> res[a[0]] * primes[a[1]]));
        for (int i = 0; i < primes.length; i++) {
            pq.add(new int[]{1, i});
        }
        for (int i = 1; i < n; i++) {
            int[] poll = pq.poll();
            int ans = primes[poll[1]] * res[poll[0]];
            while (set.contains(ans)) {
                poll = pq.poll();
                ans = primes[poll[1]] * res[poll[0]];
            }
            set.add(ans);
            res[i + 1] = ans;
            if (poll[0] + 1 < res.length) {
                pq.add(new int[]{poll[0] + 1, poll[1]});
            }
        }
        return res[n];
    }

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, count = 0;
        if (n < 3) return 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int l = maxL(nums, nums[i] + nums[j]);
                if (l <= j) continue;
                count += l - j;
            }
        }
        return count;
    }

    int maxL(int[] arr, int t) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) >> 1;
            if (arr[m] >= t) r = m - 1;
            else l = m + 1;
        }
        return r;
    }

    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long[] res = new long[n];
        res[0] = chalk[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + chalk[i];
        }
        long sum = res[n - 1] % k;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l >> 1);
            if (res[m] > sum) r = m - 1;
            else l = m + 1;
        }
        return l;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        List<Integer> list = new ArrayList<>();
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l >> 1);
            if (arr[m] < x) l = m + 1;
            else r = m - 1;
        }
        r = l;
        l = l - 1;
        while (k-- > 0) {
            if (l < 0) r++;
            else if (r >= n) l--;
            else {
                if (x - arr[l] < arr[r] - x || x - arr[l] == arr[r] - x && arr[l] < arr[r]) l--;
                else r++;
            }
        }
        for (int i = l + 1; i <= r - 1; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int count = 0;
        int n = grid.length, m = grid[0].length;
        Deque<int[]> deque = new ArrayDeque<>();
        if (grid[0][0] != 1) {
            deque.add(new int[]{0, 0});
            grid[0][0] = 1;
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            count++;
            for (int i = 0; i < size; i++) {
                int[] pop = deque.pop();
                int x = pop[0], y = pop[1];
//                if (x==n-1&&y==m-1) return count;
                for (int[] dir : dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 0) {
                        deque.add(new int[]{newX, newY});
                        grid[newX][newX] = 1;
                    }
                }
            }
        }
        return -1;
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        int[] res = new int[n];
        int max = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] road : roads) {
            res[road[0]]++;
            res[road[1]]++;
            Set set = map.getOrDefault(road[0], new HashSet<>());
            set.add(road[1]);
            map.put(road[0], set);
            Set set1 = map.getOrDefault(road[1], new HashSet<>());
            set1.add(road[1]);
            map.put(road[1], set1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set set = map.getOrDefault(i, new HashSet<>());
                max = Math.max(max, res[i] + res[j] - (set.contains(j) ? 1 : 0));
            }
        }
        return max;
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == 0 || y == 0) return z == 0 || x + y == z;
        return z % gcd(x, y) == 0;
    }

    boolean flag;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] colors = new int[n + 1];
        flag = true;
        List<Integer>[] lists = new List[n + 1];
        for (int i = 1; i <= n; i++) lists[i] = new ArrayList<>();
        for (int[] dislike : dislikes) {
            lists[dislike[0]].add(dislike[1]);
            lists[dislike[1]].add(dislike[0]);
        }
        for (int i = 1; i <= n; i++) {
            if (colors[i] == 0) {
                colors[i] = 1;
                dfs(colors, lists, i, 1);
            }
        }
        return flag;
    }

    void dfs(int[] colors, List<Integer>[] lists, int index, int color) {
        if (!flag) return;
        for (Integer integer : lists[index]) {
            if (colors[integer] == color) {
                flag = false;
                return;
            }
            if (colors[integer] == 0) {
                colors[integer] = -color;
                dfs(colors, lists, integer, colors[integer]);
            }
        }
    }

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        List<Integer>[][] lists = new List[2][n];
        for (int i = 0; i < n; i++) {
            lists[0][i] = new ArrayList<>();
            lists[1][i] = new ArrayList<>();
        }
        Deque<int[]> deque = new ArrayDeque<>();
        int[][] visited = new int[2][n];
        int count = -1;
        for (int[] redEdge : redEdges) lists[0][redEdge[0]].add(redEdge[1]);
        for (int[] blueEdge : blueEdges) lists[1][blueEdge[0]].add(blueEdge[1]);
        deque.add(new int[]{0, 0});
        deque.add(new int[]{0, 1});
        visited[0][0] = 1;
        visited[1][0] = 1;
        while (!deque.isEmpty()) {
            count++;
            for (int size = deque.size(); size > 0; size--) {
                int[] pop = deque.pop();
                int num = pop[0];
                int color = pop[1];
                if (ans[num] == -1) ans[num] = count;
                for (Integer integer : lists[~color][num]) {
                    if (visited[~color][integer] == 0) {
                        deque.add(new int[]{integer, ~color});
                        visited[~color][integer] = 1;
                    }
                }
            }
        }
        return ans;
    }

    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            dp[0] += i * nums[i];
            sum += nums[i];
        }
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + sum - nums[n - i] * n;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int countLatticePoints(int[][] circles) {
        Set<Integer> set = new HashSet<>();
        for (int[] circle : circles) {
            int x = circle[0];
            int y = circle[1];
            int r = circle[2];
            for (int i = x - r; i <= x + r; i++) {
                for (int j = y - r; j <= y + r; j++) {
                    if (set.contains(i * 1000 + j))
                        continue;
                    if (Math.pow(Math.pow(x - i, 2) + Math.pow(y - j, 2), 0.5) <= r) set.add(i * 1000 + j);
                }
            }
        }
        return set.size();
    }

    public int projectionArea(int[][] grid) {
        int sum = 0;
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            int max = 0;
            int may = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    sum++;
                    max = Math.max(max, grid[i][j]);
                }
                may = Math.max(may, grid[j][i]);
            }
            sum += max + may;
        }
        return sum;
    }

    public int minimumSize(int[] nums, int maxOperations) {
        int l = 1, r = 1000000000;
        while (l <= r) {
            int m = l + (r - l >> 1);
            if (isOK(nums, maxOperations, m)) r = m - 1;
            else l = m + 1;
        }
        return l;
    }

    boolean isOK(int[] nums, int maxOperations, int t) {
        for (int num : nums) maxOperations -= num / (t + 1);
        return maxOperations >= 0;
    }

    public int[] sortArrayByParity(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if ((nums[l] & 1) != 0) {
                while (r > l && (nums[r] & 1) != 0) r--;
                int temp = nums[r];
                nums[r] = nums[l];
                nums[l] = temp;
            }
            l++;
        }
        return nums;
    }

    public int findTheWinner(int n, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int cur = 0;
        for (int i = 1; i <= n; i++) {
            deque.add(i);
        }
        while (!deque.isEmpty()) {
            k = k % deque.size() == 0 ? deque.size() : k % deque.size();
            for (int i = 0; i < k; i++) {
                Integer pop = deque.pop();
                deque.add(pop);
            }
            cur = deque.pop();
        }
        return cur;
    }

    public int[] diStringMatch(String s) {
        int n = s.length();
        int i = 0, d = n;
        int[] res = new int[n + 1];
        for (int j = 0; j < n; j++) {
            if (s.charAt(j) == 'I') res[j] = i++;
            else res[j] = d--;
        }
        res[n] = i;
        return res;
    }

    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        int[] ans = new int[max + 1];
        for (int num : nums) ans[num] += num;
        int a = 0, c = ans[1];
        for (int i = 2; i < 10001; i++) {
            int t = Math.max(a + ans[i], c);
            a = c;
            c = t;
        }
        return c;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.val <= p.val) return inorderSuccessor(root.right, p);
        TreeNode ans = inorderSuccessor(root.left, p);
        return ans == null ? root : ans;

    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean dp[] = new boolean[n];
        dp[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j <= i + nums[i]; j++) {
                dp[i] = dp[i] || dp[j];
            }
        }
        return dp[0];
    }

    public int minMoves2(int[] nums) {
        int n = nums.length, min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        int[] p = new int[n];
        int[] b = new int[n];
        for (int i = 1; i < n; i++) {
            p[i] = p[i - 1] + nums[i - 1];
            b[n - 1 - i] = b[n - i] + nums[n - i];
        }
        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums[i] * (2 * i - n + 1) - p[0] + b[0]);
        }
        return min;
    }

    public List<String> removeAnagrams(String[] words) {
        List<String> list = new ArrayList<>();
        int n = words.length;
        list.add(words[0]);
        for (int i = 1; i < n; i++) {
            if (!ok(list.get(list.size() - 1), words[i])) {
                list.add(words[i]);
            }
        }
        return list;
    }

    public boolean ok(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] ans = new int[26];
        for (int i = 0; i < a.length(); i++) {
            ans[a.charAt(i) - 'a']--;
            ans[b.charAt(i) - 'a']++;
        }
        return Arrays.stream(ans).sum() == 0;
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length, m = queries.length;
        int[] res = new int[n + 1];
        int[] ans = new int[m];
        for (int i = 1; i <= n; i++) {
            res[i] = res[i - 1] ^ arr[i - 1];
        }
        for (int i = 0; i < m; i++) {
            ans[i] = res[queries[i][1] + 1] ^ res[queries[i][0]];
        }
        return ans;
    }

    @Test
    public void test() {
        xorQueries(new int[]{1, 3, 4, 8}, new int[][]{{0, 1}, {1, 2}, {0, 3}, {3, 3}});
    }

    int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    public int minimumLengthEncoding(String[] words) {
        TrieNode trieNode = new TrieNode();
        for (String word : words) {
            TrieNode temp = trieNode;
            for (int length = word.length() - 1; length >= 0; length--) {
                int index = word.charAt(length) - 'a';
                if (temp.children[index] == null) temp.children[index] = new TrieNode();
                temp = temp.children[index];
            }
            temp.word = word;
        }
        return dfs(trieNode);
    }

    int dfs(TrieNode node) {
        int sum = 0;
        boolean flag = false;
        if (node == null) return sum;
        for (int i = 0; i < node.children.length; i++) {
            if (node.children[i] != null) {
                sum += dfs(node.children[i]);
                flag = true;
            }
        }
        if (!flag) return node.word.length() + 1;
        return sum;
    }

    class TrieNode {
        TrieNode[] children;
        String word;
        boolean isEnd;

        TrieNode() {
            children = new TrieNode[26];
        }
    }

    @Test
    public void sout() {
        for (int i = 0; i < 96; i++) {
            System.out.println("AVG(point" + i + ") as point" + i + ",");
        }
        int[] a = {1};
    }

    @Test
    public void sout1() {
        for (int i = 0; i < 96; i++) {
            System.out.println(
                    "GREATEST(t.point" +
                            i +
                            ",IFNULL(a.point" +
                            i + ",0)" +
                            ") as point" + i +
                            " , ");
        }
    }

    @Test
    public void sout3() {
        for (int i = 0; i < 96; i++) {
            System.out.println("alter table t_cons_96 modify point" +
                    i +
                    " double(10,3);");
        }
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }

    public List<Integer> findSubstring(String s, String[] words) {
        int n = words.length, m = words[0].length(), l = s.length();
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        Map<String, Integer> cur = new HashMap<>();
        for (int i = 0, j = 0; i + n * m <= l; i += m) {
            while (j < l && j - i + 1 <= n * m) {
                String sub = s.substring(j, j + m);
                cur.put(sub, cur.getOrDefault(sub, 0) + 1);
                j += m;
            }
            if (cur.equals(map)) res.add(i);
            String per = s.substring(i, i + m);
            if (cur.get(per) == 1) cur.remove(per);
            else cur.put(per, cur.get(per) - 1);
        }
        return res;
    }

    public int longestSubsequence(String s, int k) {
        String bs = Integer.toBinaryString(k);
        int n = s.length(), m = bs.length(), cnt = n;
        if (n < m) return cnt;
        for (int i = 0; i < n - m; i++) {
            cnt -= s.charAt(i) - '0';
        }
        if (s.charAt(n - m) == '1') {
            for (int i = n - m + 1; i < n; i++) {
                int f = s.charAt(i) - bs.charAt(i - n + m);
                if (f > 0) {
                    cnt--;
                    break;
                } else if (f < 0) break;
            }
        }
        return cnt;
    }

    public String largestTimeFromDigits(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] res = new int[n];
        for (int i = 3; i <= 0; i--) {
            Set<Integer> set = new HashSet<>();
            res[0] = arr[i];
            set.add(i);
            for (int j = 3; j <= 0; j--) {
                if (set.contains(j)) continue;
                set.add(j);
                res[1] = arr[j];
                for (int k = 3; k <= 0; k--) {
                    if (set.contains(k)) continue;
                    set.add(k);
                    res[2] = arr[k];
                    for (int l = 3; l <= 0; l--) {
                        if (set.contains(l)) continue;
                        set.add(l);
                        res[3] = arr[l];
                    }
                }
            }
            if (isOK(res)) {
                return new StringBuilder().append(res[0])
                        .append(res[1]).append(":").append(res[2]).append(res[3])
                        .toString();
            }
        }
        return "";
    }

    boolean isOK(int[] res) {
        return res[0] <= 2
                && (res[0] != 2 || res[1] <= 4)
                && res[2] <= 6
                && (res[2] != 6 || res[3] == 0);
    }

    public int findMaxForm(String[] strs, int m, int n) {
        //m:0,n:1;
        int[][] dp = new int[m + 1][n + 1];
        int l = strs.length;
        for (String str : strs) {
            int[] res = cnt(str);
            int a = res[0], b = res[1];
            for (int j = m; j >= a; j--) {
                for (int k = n; k >= b; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - a][k - b] + 1);
                }
            }
        }
        return dp[m][n];
    }

    int[] cnt(String str) {
        int m = 0, n = 0;
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (aChar == '0') m++;
            else n++;
        }
        return new int[]{m, n};
    }

    public void wiggleSort(int[] nums) {
        int[] clone = nums.clone();
        int n = nums.length;
        int t = n - 1;
        for (int i = 1; i < n; i += 2) {
            nums[i] = clone[t--];
        }
        for (int i = 0; i < n; i += 2) {
            nums[i] = clone[t--];
        }
    }

    public int numWays(int n, int[][] relation, int k) {
        List<Integer>[] lists = new List[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int[] ints : relation) {
            lists[ints[0]].add(ints[1]);
        }
        deque.add(0);
        while (!deque.isEmpty() && k > 0) {
            k--;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Integer pop = deque.poll();
                for (Integer o : lists[pop]) {
                    deque.add(o);
                }
            }
        }
        while (!deque.isEmpty()) {
            if (deque.pop() == n - 1) count++;
        }
        return count;
    }

    public int jump(int[] nums) {
        int max = nums[0], n = nums.length, cnt = 1, l = 0;
        while (max < n - 1) {
            cnt++;
            int t = max;
            for (int i = l + 1; i <= t; i++) {
                max = Math.max(i + nums[i], max);
            }
            l = t;
        }
        return cnt;
    }

    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        boolean res = false;
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int d = stones[i] - stones[j];
                if (d > n - 1) continue;
                dp[i][d] = dp[i][d] || dp[j][d + 1] || dp[j][d - 1] || dp[j][d];
            }
        }
        for (int i = 0; i < n; i++) {
            res = res || dp[n - 1][i];
        }
        return res;
    }

    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                    dp[i][j] = Math.max(dp[i][j], 1);
                }
            }
        }
        return dp[m][n];
    }

    public int maxEvents(int[][] events) {
        int cnt = 0, i = 0, begin = 1, n = events.length;
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        while (i < n || !pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek() < begin) pq.poll();
            while (i < n && events[i][0] <= begin) {
                pq.add(events[i][1]);
                i++;
            }
            if (!pq.isEmpty()) {
                pq.poll();
                cnt++;
            }
            begin++;
        }
        return cnt;
    }

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;
        //dp[i][j] 前i个会，参数k个的最大值
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            int l = 1, r = i - 1, t = events[i - 1][1];
            while (l <= r) {
                int m = l + (r - l >> 1), cur = events[m - 1][1];
                if (cur >= t) r = m - 1;
                else l = m + 1;
            }
            int index = r > 0 ? r : 0;
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[index][j - 1] + events[i - 1][2]);
            }
        }
        return dp[n][k];
    }

    public List<Integer> diffWaysToCompute(String expression) {
        int n = expression.length();
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int a = 0;
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                a = a * 10 + c - '0';
            } else {
                nums.add(a);
                a = 0;
                ops.add(c);
            }
        }
        nums.add(a);
        int s = nums.size();
        //dp[i][j] 区间[i,j] 结果数 dp[i,j]=dp[i,k]+dp[k+1,j] 枚举k;
        List<Integer>[][] dp = new List[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                dp[i][j] = new ArrayList<>();
            }
        }
        for (int i = s - 1; i >= 0; i--) {
            for (int j = i; j < s; j++) {
                List<Integer> list = dp[i][j];
                if (i == j) list.add(nums.get(i));
                else {
                    for (int k = i; k < j; k++) {
                        List<Integer> one = dp[i][k];
                        List<Integer> two = dp[k + 1][j];
                        Character c = ops.get(k);
                        for (Integer per : one) {
                            for (Integer be : two) {
                                int ans;
                                if (c == '+') ans = per + be;
                                else if (c == '-') ans = per - be;
                                else ans = per * be;
                                list.add(ans);
                            }
                        }
                    }
                }
            }
        }

        return dp[0][s - 1];
    }

    public int maxProfit02(int[] prices) {
        int n = prices.length, INF = Integer.MIN_VALUE;
        int[][][] dp = new int[n][2][3];
        dp[0][1][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][1][0] + prices[i]);
            dp[i][0][2] = Math.max(dp[i - 1][0][2], (dp[i - 1][1][1] == 0 ? INF : dp[i - 1][1][1]) + prices[i]);
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][0] - prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][1] - prices[i]);
            dp[i][1][2] = Math.max(dp[i - 1][1][2], dp[i - 1][0][2] - prices[i]);
        }
        return Math.max(dp[n - 1][0][2], dp[n - 1][0][1]);
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (target <= startFuel) return 0;
        int n = stations.length;
        int begin = startFuel, cnt = 0, d = begin;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < n; i++) {
            while (i < n && begin >= stations[i][0]) {
                pq.add(stations[i]);
                i++;
            }
            while (!pq.isEmpty() && pq.peek()[0] < begin) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                int x = pq.poll()[1];
                d += x;
                cnt++;
                begin = x;
                if (d >= target) return cnt;
            }
        }
        return -1;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        List<String>[] dp = new List[n + 1];
        boolean[] b = new boolean[n + 1];
        b[0] = true;
        for (int i = 0; i <= n; i++) {
            dp[i] = new ArrayList<>();
        }
        dp[0].add(" ");
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String sb = s.substring(j, i);
                if (b[j] && set.contains(sb)) {
                    b[i] = true;
                    dp[i].add(String.join(" ", dp[j]));
                }
            }
        }
        return dp[n];
    }

    public int integerReplacement(int n) {
        int cnt = 0;
        if (n == Integer.MAX_VALUE) {
            cnt += 2;
            n /= 2;
        }
        while (n != 1) {
            if ((n & 1) == 0) n /= 2;
            else {
                if (isOK(n + 1)) n += 1;
                else n -= 1;
            }
            cnt++;
        }
        return cnt;
    }

    boolean isOK(int x) {
        while ((x & 1) == 0) {
            x >>= 1;
        }
        return x == 1;
    }
    public int[][] bicycleYard(int[] position, int[][] terrain, int[][] obstacle) {
        int m=terrain.length,n=terrain[0].length;
        int[][]dirs={{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][][] visited=new boolean[m][n][101];
        visited[position[0]][position[1]][1]=true;
        Deque<int[]> deque=new ArrayDeque<>();
        deque.add(new int[]{position[0],position[1],1});
        while (!deque.isEmpty()){
            int[] cur = deque.pop();
            for (int[] dir : dirs) {
                int x=cur[0]+dir[0];
                int y=cur[1]+dir[1];
                if (x>=0&&x<m&&y>=0&&y<n){
                    int s=cur[2]+terrain[cur[0]][cur[1]]-terrain[x][y]-obstacle[x][y];
                    if (s>0&&!visited[x][y][s]){
                        visited[x][y][s]=true;
                        deque.add(new int[]{x,y,s});
                    }
                }
            }
        }
        visited[position[0]][position[1]][1]=false;
        List<int[]> list=new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j][1]) list.add(new int[]{i,j});
            }
        }
        int[][]res=new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i]=list.get(i);
        }
        return res;
    }
    public String largestNumber(int[] cost, int target) {
        int[] dp=new int[target+1];
        //完全背包(严格等于所以初始化),先算长度，后贪心数字大小
        Arrays.fill(dp,Integer.MIN_VALUE);
        dp[0]=0;
        for (int k : cost) {
            for (int j = k; j <= target; j++) {
                dp[j] = Math.max(dp[j], dp[j - k] + 1);
            }
        }
        if (dp[target]<0) return "0";
        StringBuilder sb=new StringBuilder();
        for (int i=9,j=target;i>=1;i--){
            int c=cost[i-1];
            while (j>=c&&dp[j]==dp[j-c]+1){
                sb.append(i);
                j-=c;
            }
        }
        return sb.toString();
    }
    @Test
    public void sout2() {
        int[] b = {1};
        int[]a= {1};
      Map<int[],Integer> map=new HashMap<>();
      map.put(a,10);

        System.out.println(map.get(b));
    }

    int[][] tr = new int[2010][2];

    int lowbit(int x) {
        return x & -x;
    }

    int[] query(int x) {
        int len = 0, cnt = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            if (len == tr[i][0]) {
                cnt += tr[i][1];
            } else if (len < tr[i][0]) {
                len = tr[i][0];
                cnt = tr[i][1];
            }
        }
        return new int[]{len, cnt};
    }

    void add(int x, int[] info) {
        for (int i = x; i <= n; i += lowbit(i)) {
            int len = tr[i][0], cnt = tr[i][1];
            if (len == info[0]) {
                cnt += info[1];
            } else if (len < info[0]) {
                len = info[0];
                cnt = info[1];
            }
            tr[i][0] = len;
            tr[i][1] = cnt;
        }
    }

    public int findNumberOfLIS(int[] nums) {
        n = nums.length;
        // 离散化
        int[] tmp = nums.clone();
        Arrays.sort(tmp);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, idx = 1; i < n; i++) {
            if (!map.containsKey(tmp[i])) map.put(tmp[i], idx++);
        }
        // 树状数组维护 (len, cnt) 信息
        for (int i = 0; i < n; i++) {
            int x = map.get(nums[i]);
            int[] info = query(x - 1);
            int len = info[0], cnt = info[1];
            add(x, new int[]{len + 1, Math.max(cnt, 1)});
        }
        int[] ans = query(n);
        return ans[1];
    }
}
