package com.example.hellodocker;

import com.example.hellodocker.mapper.PowerUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class HellodockerApplicationTests {
    @Resource
    private PowerUserMapper powerUserMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void select() {
        System.out.println(powerUserMapper.selectByPrimaryKey("2021-10-28"));
    }

    @Test
    void replace() {
        System.out.println(replaceBlank("  "));
    }

    @Test
    void booking() {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        System.out.println(Arrays.toString(corpFlightBookings(bookings, 5)));
    }

    @Test
    void compareVersion() {
        String version1 = "7.5.2.4", version2 = "7.5.3";
        int i = compareVersion(version1, version2);
        System.out.println(i);
    }

    @Test
    void isNumber() {
        System.out.println(isNumber("    .eddad  "));
    }

    String replaceBlank(String str) {
        return str.replaceAll(" ", "%20");
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] arr = new int[n];
        for (int[] booking : bookings) {
            if (booking[0] == booking[1]) {
                arr[booking[0] - 1] = arr[booking[0] - 1] + booking[2];
            } else {
                for (int i = booking[0]; i <= booking[1]; i++) {
                    arr[i - 1] = arr[i - 1] + booking[2];
                }
            }
        }
        return arr;
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        if (v1.length <= v2.length) {
            for (int i = 0; i < v1.length; i++) {
                if (!Integer.valueOf(v1[i]).equals(Integer.valueOf(v2[i]))) {
                    return Integer.parseInt(v1[i]) > Integer.parseInt(v2[i]) ? 1 : -1;
                }
            }
            for (int j = v1.length; j < v2.length; j++) {
                if (Integer.parseInt(v2[j]) != 0) {
                    return -1;
                }
            }
        } else {
            for (int i = 0; i < v2.length; i++) {
                if (!Integer.valueOf(v1[i]).equals(Integer.valueOf(v2[i]))) {
                    return Integer.parseInt(v1[i]) > Integer.parseInt(v2[i]) ? 1 : -1;
                }
            }
            for (int j = v2.length; j < v1.length; j++) {
                if (Integer.parseInt(v1[j]) != 0) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                return true;
            }
        }
        return false;
    }

    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] smallestK = new int[k];
        System.arraycopy(arr, 0, smallestK, 0, smallestK.length);
        return smallestK;
    }

    @Test
    public void search() {
        int[] nums = {1, 3, 7, 9, 11};
        int search = search(nums, 7);
        System.out.println(search);
    }

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int hand = l + (r - l) >> 1;
            if (nums[hand] > target) r = hand - 1;
            if (nums[hand] < target) l = hand + 1;
            if (nums[hand] == target) return hand;
        }
        return -1;
    }

    public int balancedStringSplit(String s) {
        char[] chars = s.toCharArray();
        int p = 0, l = 0, count = 0;
        for (char c : chars) {
            if (c == 'P') {
                p++;
            } else {
                l++;
            }
            if (p == l) {
                p = 0;
                l = 0;
                count++;
            }
        }
        return count;
    }

    @Test
    public void reverseLeftWords() {
        System.out.println(reverseLeftWords("abcdefg", 2));
    }

    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c);
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            sb.append(c);
        }
        return sb.toString();
    }

    @Test
    public void findRepeatNumber() {
        int[] arr = {0, 1, 4, 2, 5, 4};
        System.out.println(findRepeatNumber(arr));
    }

    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) continue;
            if (nums[nums[i]] == nums[i]) return nums[i];
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
            i--;
        }
        return -1;
    }

    @Test
    public void fullJustify() {
        String[] words = {"Science", "is", "what", "we", "understand",
                "well", "enough", "to", "explain", "to", "a",
                "computer.", "Art", "is", "everything", "else", "we", "do"};
        List<String> list = fullJustify(words, 20);
        list.forEach(s -> {
            System.out.println(s);
            System.out.println("length: " + s.length());
        });
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        int size = 0;
        int wordCount = 0;
        for (int i = 0; i < words.length; i++) {
            int length = words[i].length();
            size = length + size;
            wordCount++;
            if (size + wordCount - 1 > maxWidth) {
                i--;
                int blankCount = maxWidth - size + length;
                wordCount = wordCount - 1;
                int blank;
                int blankPer = blankCount;
                int per = 0;
                if (wordCount != 1) {
                    blank = wordCount - 1;
                    blankPer = blankCount / blank;
                    per = blankCount % blank;
                }
                StringBuilder sb = new StringBuilder();
                for (int j = i - wordCount + 1; j < i + 1; j++) {
                    sb.append(words[j]);
                    if (j != i) {
                        for (int k = 0; k < blankPer; k++) {
                            sb.append(" ");
                        }
                        if (per > 0) {
                            sb.append(" ");
                            per--;
                        }
                    } else if (wordCount == 1) {
                        for (int k = 0; k < blankPer; k++) {
                            sb.append(" ");
                        }
                    }
                }
                list.add(sb.toString());
                size = 0;
                wordCount = 0;
            } else if (size + wordCount - 1 == maxWidth) {
                if (wordCount == 1) {
                    list.add(words[i]);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int j = i - wordCount + 1; j < i; j++) {
                        sb.append(words[j]);
                        sb.append(" ");
                    }
                    sb.append(words[i]);
                    list.add(sb.toString());
                }
                wordCount = 0;
                size = 0;
            } else if (i == words.length - 1) {
                StringBuilder sb = new StringBuilder();
                int blankCount = maxWidth - size;
                for (int j = i - wordCount + 1; j < i + 1; j++) {
                    sb.append(words[j]);
                    if (j != i) {
                        sb.append(" ");
                    }
                }
                for (int k = 0; k < blankCount - wordCount + 1; k++) {
                    sb.append(" ");
                }
                list.add(sb.toString());
            }
        }
        return list;
    }

    @Test
    public void missingNumber() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6};
        int number = missingNumber(arr);
        System.out.println(number);
    }

    /**
     * 二分法查找排序数组缺失数字
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1, hand = 0;
        while (l <= r) {
            hand = l + (r - l) / 2;
            if (nums[hand] > hand) {
                r = hand - 1;
            } else {
                l = hand + 1;
            }
        }
        return l;
    }

    @Test
    public void findNumberIn2DArray() {
        int[][] arr = {{1, 3, 5, 7, 9}, {2, 4, 6, 8, 10}};
        boolean flag = findNumberIn2DArray(arr, 8);
        System.out.println(flag);
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int y = matrix.length - 1;
        int x = 0;
        int xMax = matrix[0].length - 1;
        while (y >= 0 && x <= xMax) {
            if (matrix[y][x] > target) {
                y--;
            } else if (matrix[y][x] == target) {
                return true;
            } else {
                x++;
            }
        }
        return false;
    }

    public boolean searchIndex(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int hand = l + (r - l) / 2;
            if (nums[hand] > target) r = hand - 1;
            if (nums[hand] < target) l = hand + 1;
            if (nums[hand] == target) return true;
        }
        return false;
    }

    /**
     * 在字符串 s 中找出第一个只出现一次的字符。
     * 如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Character character : map.keySet()) {
            if (map.get(character) == 1) return character;
        }
        return ' ';
    }

    @Test
    public void chalkReplacer() {
        int[] arr = {22, 86, 96, 35, 62, 69, 56, 33, 95, 10, 38, 53, 33, 90, 29,
                68, 85, 58, 11, 49, 81, 18, 32, 96, 40, 75, 49, 26, 60, 71, 15, 94,
                31, 99, 12, 81, 10, 19, 7, 73, 35, 56, 100, 15, 37, 89, 58, 17, 55,
                62, 4, 30, 68, 68, 89, 62, 39, 35, 16, 18, 63, 73, 100, 22, 46, 58,
                80, 77, 23, 5, 52, 96, 98, 21, 33, 86, 81, 71, 69, 72, 71,
                58, 17, 85, 70, 22, 84, 94, 75, 51, 60, 81, 12, 22, 13, 33, 53, 58
        };
        System.out.println(arr.length);
        System.out.println(chalkReplacer(arr, 134221332));
    }

    /**
     * 1894. 找到需要补充粉笔的学生编号（力扣）
     *
     * @param chalk
     * @param k
     * @return
     */
    public int chalkReplacer(int[] chalk, int k) {
//        暴力求解
//        int i = 0;
//        while (k >= chalk[i]) {
//            k = k - chalk[i];
//            if (i == chalk.length - 1) {
//                i = 0;
//            } else {
//                i++;
//            }
//        }
//        return i ;
        Long sum = 0L;
        int smallK;
        int i = 0;
        for (int num : chalk) {
            sum += num;
        }
        smallK = (int) (k % sum);
        while (smallK >= chalk[i]) {
            smallK = smallK - chalk[i];
            i++;
        }
        return i;
    }

    @Test
    public void fizzBuzz() {
        List<String> list = fizzBuzz(15);
        list.forEach(System.out::println);
    }

    /**
     * 412
     *
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                if (i % 5 == 0) list.add("FizzBuzz");
                else list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
}
