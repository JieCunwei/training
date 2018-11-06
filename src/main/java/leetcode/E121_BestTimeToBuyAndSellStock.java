package leetcode;

/**
 * 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * Created by debug on 2018/11/05.
 */
public class E121_BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    // 动态规划
    static class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[] minArray = new int[prices.length];
            int[] maxArray = new int[prices.length];
            minArray[0] = prices[0];
            for (int i = 1; i < prices.length - 1; i++) {
                // 最佳买入点为卖出点之前最低价日期
                minArray[i] = (prices[i] < minArray[i - 1]) ? prices[i] : minArray[i - 1];
            }
            maxArray[prices.length - 1] = prices[prices.length - 1];
            for (int i = prices.length - 2; i > 0; i--) {
                // 最佳卖出点为买入点之后最高价日期
                maxArray[i] = (prices[i] > maxArray[i + 1]) ? prices[i] : maxArray[i + 1];
            }
            int result = 0;
            // 循环切分日期查找最高收益
            for (int i = 0; i < prices.length - 1; i++) {
                if (maxArray[i + 1] - minArray[i] > result) {
                    result = maxArray[i + 1] - minArray[i];
                }
            }
            return result;
        }
    }
}

