package leetcode;

/**
 * 只出现一次的数字 II
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 * Created by debug on 2018/11/13.
 */
public class E136_SingleNumber2 {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).singleNumber(new int[]{2, 2, 3, 2}));
        tools.println((new Solution()).singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    // 逐位相加 时间复杂度O(n) 空间复杂度O(1)
    static class Solution {
        public int singleNumber(int[] nums) {
            int[] bits = new int[32];
            for (int i = 0; i < bits.length; i++) {
                bits[i] = 0;
            }
            for (int x : nums) {
                for (int i = 0; i < bits.length; i++) {
                    bits[i] += (x >>> i) & 1;
                }
            }
            int count = 0;
            for (int i = 0; i < bits.length; i++) {
                count += (bits[i] % 3) << i;
            }
            return count;
        }
    }
}
