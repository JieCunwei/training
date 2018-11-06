package leetcode;

/**
 * 鸡蛋掉落
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * <p>
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * <p>
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * <p>
 * 你的目标是确切地知道 F 的值是多少。
 * <p>
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 * <p>
 * 输入：K = 2, N = 6
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：K = 3, N = 14
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= 100
 * 1 <= N <= 10000
 * Created by debug on 2018/11/05.
 */
public class H887_SuperEggDrop {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).superEggDrop(1, 2));
        tools.println((new Solution()).superEggDrop(2, 6));
        tools.println((new Solution()).superEggDrop(3, 14));
        tools.println((new Solution()).superEggDrop(2, 100));
        tools.println((new Solution()).superEggDrop(4, 400));
        tools.println((new Solution()).superEggDrop(7, 10000));
    }

    // 动态规划
    static class Solution {
//        // 动态规划 复杂度 K*N*N
//        public int superEggDrop(int K, int N) {
//            // 循环存储
//            int[] preDropTimes = new int[N];
//            int[] dropTimes = new int[N];
//            int[] tmp;
//            for (int k = 1; k <= K; k++) {
//                tmp = preDropTimes;
//                preDropTimes = dropTimes;
//                dropTimes = tmp;
//                for (int n = 1; n <= N; n++) {
////                    int minTimesX = 1;
//                    if (n <= 2 || k == 1) {
//                        // 只有1个蛋时，测试次数=楼层数，楼层数小于等于2层时，测试次数=楼层数
//                        dropTimes[n - 1] = n;
//                    } else {
//                        int minTimes = N; // 最差的测试次数=楼层数
//                        for (int x = 1; x < n; x++) {
//                            // 若在x层测试, 测试次数=Max(D[k,n-x](成功还需测试剩余层),D[k-1,x-1](失败需测试x-1层并损失一个蛋)) + 1
//                            int times = 1 + (x > 1 && preDropTimes[x - 1 - 1] > dropTimes[n - x - 1] ? preDropTimes[x - 1 - 1] : dropTimes[n - x - 1]);
//                            if (times <= minTimes) {
//                                minTimes = times;
////                                minTimesX = x;
//                            }
//                        }
//                        // 最优测试点为探查的最小次数
//                        dropTimes[n - 1] = minTimes;
//                    }
////                    boolean isMaxDown = minTimesX > 1 && preDropTimes[minTimesX - 1 - 1] > dropTimes[n - minTimesX - 1];
////                    System.out.println(String.format("(K=%d, N=%d, 最佳测试点=%d, 最坏测试次数=%d, 下轮测试次数=Max([K=%d, N=%d]:%d, [K=%d, N=%d]:%d), 测试方向=%s",
////                            k, n, minTimesX, dropTimes[n - 1], minTimesX > 1 ? k - 1 : 0, minTimesX > 1 ? minTimesX - 1 : 0, minTimesX > 1 ? preDropTimes[minTimesX - 1 - 1] : 0, k,
////                            n - minTimesX, minTimesX < n ? dropTimes[n - minTimesX - 1] : 0,
////                            isMaxDown ? "损失一个蛋,向下测试" : "向上测试"));
//                }
//            }
//            return dropTimes[N - 1];
//        }

        // 动态规划 逆向思考给定K个鸡蛋 T次测试 最多可以测出多少楼 复杂度 K*T
        public int superEggDrop(int K, int N) {
            // 只有1个蛋时 或只有1层楼时 需要N次测试
            if (N == 1 || K == 1) {
                return N;
            }
            // 循环存储
            int[] preMaxFloor = new int[K];
            int[] maxFloor = new int[K];
            int[] tmp;
            int t = 1;
            while (true) {
                for (int k = 1; k <= K; k++) {
                    if (t == 1 || k == 1) {
                        // 测试次数为1时 或者只有1个蛋时 最多可测t层楼
                        maxFloor[k - 1] = t;
                    } else {
                        // 最多可测楼层T[k, t] = 1 + T[k-1, t-1] + T[k, t-1]
                        maxFloor[k - 1] = preMaxFloor[k - 1 - 1] + preMaxFloor[k - 1] + 1;
                    }
                    // 最多可测楼层达到待测楼层时，检测结束
                    if (maxFloor[k - 1] >= N) {
                        return t;
                    }
                }
                t += 1;
                tmp = preMaxFloor;
                preMaxFloor = maxFloor;
                maxFloor = tmp;
            }
        }
    }
}
