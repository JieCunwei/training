package leetcode;

/**
 * 跳跃游戏II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * Created by debug on 2018/11/09.
 */
public class H045_JumpGame2 {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).jump(new int[]{2, 3, 1, 1, 4}));
        tools.println((new Solution()).jump(new int[]{3, 2, 1}));
        tools.println((new Solution()).jump(new int[]{2, 3, 1}));
    }

    // 贪心 时间复杂度 n^2
    static class Solution {
        public int jump(int[] nums) {
            int count = 0;
            for (int i = 0, max, j; i < nums.length - 1; i += max, count++) {
                if (i + nums[i] >= nums.length - 1) {
                    // 若当前位置可最远触达数组末尾，结束查找
                    return count + 1;
                }
                // 循环查找可使下一步可触达最远距离的跳跃值
                for (j = 1, max = 1; j <= nums[i] && i + j < nums.length; j++) {
                    if (j + nums[i + j] >= max + nums[i + max]) {
                        max = j;
                    }
                }
            }
            return count;
        }
    }
}
