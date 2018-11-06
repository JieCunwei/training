package leetcode;

/**
 * 朋友圈
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * <p>
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 示例 2:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 * <p>
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 * Created by debug on 2018/11/06.
 */
public class M547_FriendCircles {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        tools.println((new Solution()).findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }

    // 图计算 并查集 深度优先搜索
    static class Solution {
        // 并查集法 带路径压缩的按size合并有根树 时间复杂度O(M.length * A(n) + M.length^2 / 2)  A(n) <= 4
        public int findCircleNum(int[][] M) {
            if (M.length <= 1) {
                return 1;
            }
            int[] setLeader = new int[M.length];// 集合代表
            int[] setSize = new int[M.length];// 集合大小
            int count = M.length;
            for (int i = 0; i < setLeader.length; i++) {
                setLeader[i] = i;
                setSize[i] = 1;
            }
            for (int i = 0; i < M.length; i++) {
                for (int j = i + 1; j < M.length; j++) {
                    // 便利对称数组的右上半区
                    if (M[i][j] == 1) {
                        int li = findLeader(setLeader, i);
                        int lj = findLeader(setLeader, j);
                        // 对每一对朋友关系检测是否在同一集合中
                        if (li != lj) {
                            union(setLeader, setSize, li, lj);
                            count -= 1; // 合并后集合数减1
                        }
                    }
                }
            }
            return count;
        }

        // 查找集合代表
        public int findLeader(int[] setLeader, int member) {
            if (member != setLeader[member]) {
                setLeader[member] = findLeader(setLeader, setLeader[member]);// 更新leaf的父节点为root,路径压缩
            }
            return setLeader[member];
        }

        // 合并集合 启发式
        public void union(int[] setLeader, int[] setSize, int i, int j) {
            if (setSize[i] >= setSize[j]) {
                // 集合I元素多于集合J时 将集合J并入集合I
                setLeader[j] = i;
                setSize[i] += setSize[j];
                // setSize[j] = 0;
            } else {
                setLeader[i] = j;// 否则 将集合I并入集合J
                setSize[j] += setSize[i];
                // setSize[i] = 0;
            }
        }


//        // 合并关系矩阵法 时间复杂度 (N^2)*(N-L)
//        public int findCircleNum(int[][] M) {
//            if (M.length <= 1) {
//                return 1;
//            }
//            int result = M.length;
//            int lastResult;
//            do {
//                lastResult = result;
//                for (int i = 0; i < M.length; i++) {
//                    if (M[i].length == 1) {
//                        // 跳过已被合并的行
//                        continue;
//                    }
//                    for (int j = 0; j < M[i].length; j++) {
//                        if (M[i][j] == 1 && j != i) {
//                            // 两个不同节点想等时，将两节点的关系合并，将其中一行指向另一行
//                            int p = j;
//                            while (M[p].length == 1) {
//                                p = M[p][0];
//                            }
//                            if (p == i) {
//                                continue;
//                            }
//                            for (int x = 0; x < M[i].length; x++) {
//                                // 合并两行
//                                if (M[p][x] == 1) {
//                                    M[i][x] = 1;
//                                }
//                            }
//                            M[p] = new int[]{i};
//                            result--;
//                        }
//                    }
//                }
//            } while (result < lastResult);// 迭代过程没有新的合并行时退出
//            return result;
//        }
    }
}
