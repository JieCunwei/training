package leetcode;

/**
 * N 皇后 II
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 0,0,0,1,0,0,0,0
 * 0,0,0,0,0,0,1,0
 * 0,0,1,0,0,0,0,0
 * 0,0,0,0,0,0,0,1
 * 0,1,0,0,0,0,0,0
 * 0,0,0,0,1,0,0,0
 * 1,0,0,0,0,0,0,0
 * 0,0,0,0,0,1,0,0
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * Created by debug on 2018/11/09.
 */
public class H052_NQueens2 {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).totalNQueens(8));
    }

    // 回溯 时间复杂度 n^3
    static class Solution {
        public int totalNQueens(int n) {
            int[] queens = new int[n];
            for (int i = 0; i < n; i++) {
                queens[i] = 0;
            }
            return count(queens, 0);
        }

        private int count(int[] queens, int line) {
            int count = 0;
            boolean isLastLine = line == queens.length - 1;
            for (int i = 0; i < queens.length; i++) {
                if (check(queens, line, i)) {
                    queens[line] = i;
                    count += isLastLine ? 1 : count(queens, line + 1);
                }
            }
            return count;
        }

        private boolean check(int[] queens, int line, int index) {
            for (int i = 1, x; i <= line; i++) {
                x = queens[line - i];
                if (x == index || x == index - i || x == index + i) {
                    return false;
                }
            }
            return true;
        }
    }
}
