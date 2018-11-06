package leetcode;

/**
 * 醉长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 * <p>
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * Created by debug on 2018/11/05.
 */
public class M718_MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
    }

    // 动态规划 二分查找？ 哈希表？
    static class Solution {
        // 连续子串暴力法
//        public int findLength(int[] A, int[] B) {
//            int maxLength = 0;
//            for (int i = 0; i < A.length; i++) {
//                for (int j = 0; j < B.length; j++) {
//                    int length = 0;
//                    for (int k = 0; j + k < B.length && i + k < A.length && B[j + k] == A[i + k]; k++) {
//                        length += 1;
//                    }
//                    if (length > maxLength) {
//                        maxLength = length;
//                    }
//                }
//            }
//            return maxLength;
//        }
        // 连续子序列最长对角线法
        public int findLength(int[] A, int[] B) {
            // 较长的数组
            int[] L = A.length > B.length ? A : B;
            // 较短的数组
            int[] S = A.length > B.length ? B : A;
            int maxLength = 0;
            // 循环存储
            int[] lastMatch = new int[S.length];
            int[] match = new int[S.length];
            int[] tmp;
            for (int j = S.length - 1; j >= 0; j--) {
                match[j] = 0;
            }
            for (int i = 0; i < L.length; i++) {
                tmp = lastMatch;
                lastMatch = match;
                match = tmp;
                for (int j = 0; j < S.length; j++) {
                    if (L[i] == S[j]) {
                        // 当前字符相等，子串长度为子串(L[0~i-1],S[0~j-1])+1
                        match[j] = (j > 0 ? lastMatch[j - 1] : 0) + 1;
                        if (match[j] > maxLength) {
                            maxLength = match[j];
                        }
                    } else {
                        match[j] = 0;
                    }
                }
            }
            return maxLength;
        }

//        // 不连续子序列(LCS)最长对角线法
//        public int findLength(int[] A, int[] B) {
//            // 较长的数组
//            int[] L = A.length > B.length ? A : B;
//            // 较短的数组
//            int[] S = A.length > B.length ? B : A;
//            int maxLength = 0;
//            // 循环存储
//            int[] lastMatch = new int[S.length];
//            int[] match = new int[S.length];
//            int[] tmp;
//            for (int j = S.length - 1; j >= 0; j--) {
//                match[j] = 0;
//            }
//            for (int i = 0; i < L.length; i++) {
//                tmp = lastMatch;
//                lastMatch = match;
//                match = tmp;
//                for (int j = 0; j < S.length; j++) {
//                    if (L[i] == S[j]) {
//                        // 当前字符相等，LCS长度为LCS(L[0~i-1],S[0~j-1])+1
//                        match[j] = (j > 0 ? lastMatch[j - 1] : 0) + 1;
//                        if (match[j] > maxLength) {
//                            maxLength = match[j];
//                        }
//                    } else {
//                        // 当前字符不等，LCS长度为Max(LCS(L[0~i],S[0~j-1]),LCS(L[0~i-1],S[0~j]))
//                        match[j] = j == 0 || lastMatch[j] > match[j - 1] ? lastMatch[j] : match[j - 1];
//                    }
//                }
//            }
//            return maxLength;
//        }
    }
}
