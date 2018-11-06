package leetcode;

/**
 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 * <p>
 * 注意：1 ≤ k ≤ n ≤ 109。
 * <p>
 * 示例 :
 * <p>
 * 输入:
 * n: 13   k: 2
 * <p>
 * 输出:
 * 10
 * <p>
 * 解释:
 * 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * Created by debug on 2018/11/06.
 */
public class H440_KthSmallestInLexicographicalOrder {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).findKthNumber(13, 2));
    }

    // 其他
    static class Solution {
        // 时间复杂度 对9个完全10叉树的先序遍历计数法 log(n)*log(n)
        public int findKthNumber(int n, int k) {
            int leaf = 1;
            while (k > 1) { // 剩余step>1
                int step = 0;
                long start = leaf;
                long end = leaf + 1;
                while (start <= n) { // 计算子树有多少节点（不超过n的层级含有多少叶子节点）
                    step += Math.min(end, n + 1) - start;// 当前层节点数=Min(end, n+1) - start；n+1>end说明n与当前end不在同一层
                    start *= 10;
                    end *= 10;
                }
                if (k > step) {// 若剩余step>子树节点数，剩余step-=子树节点数，切换至另一科树
                    k -= step;
                    leaf += 1;
                } else {// 若剩余step<=子树节点数，剩余step-=1，切换至第一课子树
                    k -= 1;
                    leaf *= 10;
                }
            }
            return leaf;
        }
    }
}
