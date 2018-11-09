package leetcode;

/**
 * 距震中的最长递增路径
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 示例 2:
 * <p>
 * 输入: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * Created by debug on 2018/11/09.
 */
public class H329_LongestIncreasingPathInMatrix {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        tools.println((new Solution()).longestIncreasingPath(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}));
        tools.println((new Solution()).longestIncreasingPath(new int[][]{
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {19, 18, 17, 16, 15, 14, 13, 12, 11, 10},
                {20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
                {39, 38, 37, 36, 35, 34, 33, 32, 31, 30},
                {40, 41, 42, 43, 44, 45, 46, 47, 48, 49},
                {59, 58, 57, 56, 55, 54, 53, 52, 51, 50},
                {60, 61, 62, 63, 64, 65, 66, 67, 68, 69},
                {79, 78, 77, 76, 75, 74, 73, 72, 71, 70},
                {80, 81, 82, 83, 84, 85, 86, 87, 88, 89},
                {99, 98, 97, 96, 95, 94, 93, 92, 91, 90},
                {100, 101, 102, 103, 104, 105, 106, 107, 108, 109},
                {119, 118, 117, 116, 115, 114, 113, 112, 111, 110},
                {120, 121, 122, 123, 124, 125, 126, 127, 128, 129},
                {139, 138, 137, 136, 135, 134, 133, 132, 131, 130},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}));
    }

    // 回溯 + 缓存 时间复杂度 O(n)
    static class Solution {
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }
            int max = 1;
            int[][] maxNum = new int[matrix.length][];// 记录从当前位置开始的最大长度
            for (int i = 0; i < matrix.length; i++) {
                maxNum[i] = new int[matrix[i].length];
                for (int j = 0; j < maxNum[i].length; j++) {
                    maxNum[i][j] = 0;
                }
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0, count; j < matrix[i].length; j++) {
                    // 遍历每个开始位置,计算最大长度
                    count = maxNum[i][j] > 0 ? maxNum[i][j] : count(matrix, maxNum, i, j);
                    if (count > max) {
                        max = count;
                    }
                }
            }
            return max;
        }

        private int count(int[][] matrix, int[][] maxNum, int line, int index) {
            int max = 1;
            for (int i = 0, j = 0, n = 0, count; n < 4; n++) {
                // 遍历4个方向
                switch (n) {
                    case 0:
                        i = 0;
                        j = -1;
                        break;
                    case 1:
                        i = 0;
                        j = 1;
                        break;
                    case 2:
                        i = -1;
                        j = 0;
                        break;
                    case 3:
                        i = 1;
                        j = 0;
                        break;
                }

                if (line + i >= 0 && line + i < matrix.length && index + j >= 0 && index + j < matrix[line].length
                        && matrix[line + i][index + j] > matrix[line][index]) {
                    // 探查位置大于当前位置时计算探查位置的最大长度
                    count = 1 + (maxNum[line + i][index + j] > 0 ? maxNum[line + i][index + j] : count(matrix, maxNum, line + i, index + j));
                    if (count > max) {
                        max = count;
                    }
                }
            }
            maxNum[line][index] = max;
            return max;
        }
    }
}
