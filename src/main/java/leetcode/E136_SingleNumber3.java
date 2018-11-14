package leetcode;

/**
 * 只出现一次的数字 III
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 * <p>
 * 示例 :
 * <p>
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 * <p>
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * 在真实的面试中遇到过这道题？
 * Created by debug on 2018/11/13.
 */
public class E136_SingleNumber3 {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).singleNumber(new int[]{1, 2, 1, 3, 2, 5}));
    }

    // 布尔运算 时间复杂度O(n) 空间复杂度O(1)
    static class Solution {
        public int[] singleNumber(int[] nums) {
            int test = 0;
            for (int i : nums) {
                // 累计异或 相同的数字偶数次异或为0 最终结果为2个不同数字的异或值
                test = test ^ i;
            }
            // 计算2个数字不同的标志位
            int fistBit = test & (~(test - 1));// x&(~(x-1))为从低位起第一个为1的位
            int[] result = new int[]{0, 0};
            for (int i : nums) {
                if ((i & fistBit) == 0) {// 对标志位进行分组计算
                    result[0] = result[0] ^ i;
                } else {
                    result[1] = result[1] ^ i;
                }
            }
            return result;
        }
    }
}
