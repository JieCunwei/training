package leetcode;

/**
 * H 指数
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 * <p>
 * h 指数的定义: “一位有 h 指数的学者，代表他（她）的 N 篇论文中至多有 h 篇论文，分别被引用了至少 h 次，其余的 N - h 篇论文每篇被引用次数不多于 h 次。”
 * <p>
 * 示例:
 * <p>
 * 输入: citations = [3,0,6,1,5]
 * 输出: 3
 * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 * 说明: 如果 h 有多种可能的值，h 指数是其中最大的那个。
 * Created by debug on 2018/11/13.
 */
public class M274_HIndex {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).hIndex(new int[]{3, 0, 6, 1, 5}));
        tools.println((new Solution()).hIndex(new int[]{0}));
        tools.println((new Solution()).hIndex(new int[]{1, 1}));
    }

    // 基数排序 时间复杂度O(n)
    static class Solution {
        public int hIndex(int[] citations) {
            int n = citations.length;
            int[] hCount = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                hCount[i] = 0;
            }
            // 记录每种被引用次数的文章数
            for (int i : citations) {
                if (i >= n) {
                    hCount[n] += 1;
                } else {
                    hCount[i] += 1;
                }
            }
            for (int i = n, count = 0; i >= 0; i--) {
                // 从最高引用次数开始累积文章数
                count += hCount[i];
                // 当累积文章数达到引用次数时 输出当前引用次数
                if (count >= i) {
                    return i;
                }
            }
            return 0;
        }
    }
}
