package leetcode;

/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * <p>
 * Created by debug on 2018/11/06.
 */
public class E014_LongestCommonPrefix {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        tools.println((new Solution()).longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    // 字符串
    static class Solution {
        // 暴力查找 时间复杂度 3*L
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }
            if (strs.length == 1) {
                return strs[0];
            }
            int len = 1;
            for (; len <= strs[0].length(); len++) {
                int idx = len - 1;
                char c = strs[0].charAt(idx);
                for (int i = 0; i < strs.length; i++) {
                    if (strs[i].length() < len || strs[i].charAt(idx) != c) {
                        return strs[0].substring(0, Math.max(0, len - 1));
                    }
                }
            }
            return strs[0].substring(0, Math.max(0, len - 1));
        }
    }
}
