package leetcode;

/**
 * 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 * Created by debug on 2018/11/06.
 */
public class M338_CountingBits {

    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).countBits(2));
        tools.println((new Solution()).countBits(5));
    }

    // 动态规划
    static class Solution {
        // 动态规划 时间复杂度 N
        public int[] countBits(int num) {
            if (num == 0) {
                return new int[]{0};
            }
            if (num == 1) {
                return new int[]{0, 1};
            }
            int[] result = new int[num + 1];
            result[0] = 0;
            result[1] = 1;
            for (int i = 2, idx = 1; i <= num; i++) {
                if (i == idx << 1) {
                    // 若当前数字是比特位计数为1的索引的后继，比特位计数为1的索引左移1位
                    idx <<= 1;
                }
                // 当前比特位计数=去掉最高位1的剩余比特位计数+1
                result[i] = 1 + result[i - idx];
            }
            return result;
        }
    }
}
