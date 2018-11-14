package leetcode;

/**
 * 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * Created by debug on 2018/11/13.
 */
public class E136_SingleNumber {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).singleNumber(new int[]{2, 2, 1}));
        tools.println((new Solution()).singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    // 布尔运算 时间复杂度O(n) 空间复杂度O(1)
    static class Solution {
        public int singleNumber(int[] nums) {
            int result = 0;
            for (int i : nums) {
                // 累计异或 相同的数字偶数次异或为0 最终结果为出现奇数次的数字
                result = result ^ i;
            }
            return result;
        }
    }
}
