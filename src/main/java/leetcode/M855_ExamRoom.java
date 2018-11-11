package leetcode;

/**
 * 考场就做
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 * <p>
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 * <p>
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。请确保每次调用 ExamRoom.leave(p) 时都有学生坐在座位 p 上。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * 输出：[null,0,9,4,2,null,5]
 * 解释：
 * ExamRoom(10) -> null
 * seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。
 * seat() -> 9，学生最后坐在 9 号座位上。
 * seat() -> 4，学生最后坐在 4 号座位上。
 * seat() -> 2，学生最后坐在 2 号座位上。
 * leave(4) -> null
 * seat() -> 5，学生最后坐在 5 号座位上。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 10^9
 * 在所有的测试样例中 ExamRoom.seat() 和 ExamRoom.leave() 最多被调用 10^4 次。
 * 调用 ExamRoom.leave(p) 时需要确保当前有学生坐在座位 p 上。
 * Created by debug on 2018/11/10.
 */
public class M855_ExamRoom {
    public static void main(String[] args) {
        Tools tools = new Tools();
        ExamRoom examRoom = new ExamRoom(10);
        tools.println("null");
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        examRoom.leave(4);
        tools.println("null");
        tools.println(examRoom.seat());
        examRoom = new ExamRoom(100);
        tools.println("null");
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        examRoom.leave(49);
        tools.println("null");
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        tools.println(examRoom.seat());
        examRoom = new ExamRoom(100);
        for (int i = 0; i < 100; i++) {
            if (i == 45) {
                examRoom.leave(47);
            }
            tools.print(examRoom.seat());
            tools.print(',');
        }
        tools.println();
    }

    // 优先级堆+Map 时间复杂度lg(n)
    static class ExamRoom {
        private IndexMap seatHeapMap;
        private Node[] leftHeap;
        private Node[] rightHeap;
        private int heapSize;
        private int n;

        public ExamRoom(int N) {
            n = N;
            int buf = N > 10000 ? 10000 : N;
            seatHeapMap = new IndexMap(buf);
            leftHeap = new Node[buf];
            rightHeap = new Node[buf];
            heapSize = 0;
        }

        public int seat() {
            if (heapSize == n) {
                return -1;
            }
            if (heapSize == 0) {
                return add(new Node(0, 0, (n - 1) * 2 - 1, 0, 0));
            } else {
                // 计算两堆最优座位号
                int leftHeapMin = (leftHeap[0].seatNo * 2 - leftHeap[0].leftSlot - 1) / 2;
                int rightHeapMin = (rightHeap[0].seatNo * 2 + rightHeap[0].rightSlot + 1) / 2;
                // 计算两堆最大间距
                int smallSlot = leftHeap[0].leftSlot > rightHeap[0].rightSlot ? rightHeap[0].rightSlot : leftHeap[0].leftSlot;
                int bigSlot = leftHeap[0].leftSlot > rightHeap[0].rightSlot ? leftHeap[0].leftSlot : rightHeap[0].rightSlot;
                // (堆间距+1)/2相等时，采用较小的座位号，否则采用较大堆间距的最优座位号
                boolean checkLeft = (bigSlot + 1) / 2 == (smallSlot + 1) / 2 ? leftHeapMin < rightHeapMin : leftHeap[0].leftSlot > rightHeap[0].rightSlot;
                Node node;
                if (checkLeft) {
                    node = new Node(leftHeapMin,
                            leftHeap[0].seatNo - leftHeap[0].leftSlot < 0 ? leftHeapMin * 2 - 1 : (leftHeap[0].leftSlot - 1) / 2, leftHeap[0].seatNo - leftHeapMin - 1,
                            heapSize, heapSize);
                } else {
                    node = new Node(rightHeapMin,
                            rightHeapMin - rightHeap[0].seatNo - 1, rightHeap[0].seatNo + rightHeap[0].rightSlot >= n ? (n - 1 - rightHeapMin) * 2 - 1 : rightHeap[0].rightSlot / 2,
                            heapSize, heapSize);
                }
                if (node.seatNo >= 0) {
                    fix(node, true);
                    add(node);
                }
                return node.seatNo;
            }
        }

        public void leave(int p) {
            Node node = heapSize > 0 ? seatHeapMap.get(p) : null;
            if (node != null) {
                remove(node);
                fix(node, false);
            }
        }

        public void fix(Node node, boolean add) {
            int left = node.seatNo - node.leftSlot - 1;
            int right = node.seatNo + node.rightSlot + 1;
            Node leftNode = left >= 0 ? seatHeapMap.get(left) : null;
            Node rightNode = right < n ? seatHeapMap.get(right) : null;
            if (add) {
                if (leftNode != null) {
                    leftNode.rightSlot = node.seatNo - left - 1;
                    if (leftNode.rightSlot < 0) {
                        leftNode.rightSlot = 0;
                    }
                    heapDown(rightHeap, leftNode.rightIndex, heapSize, false);
                }
                if (rightNode != null) {
                    rightNode.leftSlot = right - node.seatNo - 1;
                    if (rightNode.leftSlot < 0) {
                        rightNode.leftSlot = 0;
                    }
                    heapDown(leftHeap, rightNode.leftIndex, heapSize, true);
                }
            } else {
                if (leftNode != null) {
                    leftNode.rightSlot = rightNode != null ? leftNode.rightSlot + node.rightSlot + 1 : (n - 1 - leftNode.seatNo) * 2 - 1;
                    heapUp(rightHeap, leftNode.rightIndex, false);
                }
                if (rightNode != null) {
                    rightNode.leftSlot = leftNode != null ? rightNode.leftSlot + node.leftSlot + 1 : (rightNode.seatNo) * 2 - 1;
                    heapUp(leftHeap, rightNode.leftIndex, true);
                }
            }
        }

        private int add(Node node) {
            if (node.leftSlot < 0) {
                node.leftSlot = 0;
            }
            if (node.rightSlot < 0) {
                node.rightSlot = 0;
            }
            if (node.seatNo < 0) {
                return -1;
            }
            seatHeapMap.put(node.seatNo, node);
            leftHeap[heapSize] = node;
            rightHeap[heapSize] = node;
            heapSize += 1;
            heapUp(leftHeap, heapSize - 1, true);
            heapUp(rightHeap, heapSize - 1, false);
            return node.seatNo;
        }

        private void remove(Node node) {
            heapSize -= 1;
            seatHeapMap.remove(node.seatNo);
            int index = node.leftIndex;
            swap(leftHeap, index, heapSize, true);
            leftHeap[heapSize] = null;
            heapDown(leftHeap, index, heapSize, true);
            index = node.rightIndex;
            swap(rightHeap, index, heapSize, false);
            rightHeap[heapSize] = null;
            heapDown(rightHeap, index, heapSize, false);
        }

        /** l=2*(i+1)-1 r=2*(i+1) p=(i+1)/2-1 */
        private void heapDown(Node[] heap, int i, int size, boolean checkLeft) {
            for (int l = 2 * (i + 1) - 1, r = 2 * (i + 1), c; l < size; i = c, l = 2 * (i + 1) - 1, r = 2 * (i + 1)) {
                c = r >= size || Node.needSwap(heap[r], heap[l], checkLeft) ? l : r;
                if (Node.needSwap(heap[i], heap[c], checkLeft)) {
                    swap(heap, i, c, checkLeft);
                } else {
                    break;
                }
            }
        }

        private void heapUp(Node[] heap, int i, boolean checkLeft) {
            for (int p = (i + 1) / 2 - 1; p >= 0; i = p, p = (i + 1) / 2 - 1) {
                if (Node.needSwap(heap[p], heap[i], checkLeft)) {
                    swap(heap, i, p, checkLeft);
                } else {
                    break;
                }
            }
        }

        private void swap(Node[] heap, int a, int b, boolean checkLeft) {
            // 交换堆中节点
            Node tmpNode = heap[a];
            heap[a] = heap[b];
            heap[b] = tmpNode;
            // 交换堆位索引
            if (checkLeft) {
                int tmp = heap[a].leftIndex;
                heap[a].leftIndex = heap[b].leftIndex;
                heap[b].leftIndex = tmp;
            } else {
                int tmp = heap[a].rightIndex;
                heap[a].rightIndex = heap[b].rightIndex;
                heap[b].rightIndex = tmp;
            }
        }

        static class Node {
            int seatNo;
            int leftSlot;
            int rightSlot;
            int leftIndex;
            int rightIndex;
            Node next;

            public Node(int seatNo, int leftSlot, int rightSlot, int leftIndex, int rightIndex) {
                this.seatNo = seatNo;
                this.leftSlot = leftSlot;
                this.rightSlot = rightSlot;
                this.leftIndex = leftIndex;
                this.rightIndex = rightIndex;
            }

            public static boolean needSwap(Node p, Node c, boolean checkLeft) {
                int smallSlot, bigSlot;
                if (checkLeft) {
                    smallSlot = p.leftSlot < c.leftSlot ? p.leftSlot : c.leftSlot;
                    bigSlot = p.leftSlot > c.leftSlot ? p.leftSlot : c.leftSlot;
                } else {
                    smallSlot = p.rightSlot < c.rightSlot ? p.rightSlot : c.rightSlot;
                    bigSlot = p.rightSlot > c.rightSlot ? p.rightSlot : c.rightSlot;
                }
                // (间距+1)/2相等时，采用较小的座位号，否则采用较大间距
                return (bigSlot + 1) / 2 == (smallSlot + 1) / 2 ? c.seatNo < p.seatNo : (checkLeft ? p.leftSlot < c.leftSlot : p.rightSlot < c.rightSlot);
            }

            @Override
            public String toString() {
                return "Node{" +
                        "seatNo=" + seatNo +
                        ", leftSlot=" + leftSlot +
                        ", rightSlot=" + rightSlot +
                        ", leftIndex=" + leftIndex +
                        ", rightIndex=" + rightIndex +
                        '}';
            }
        }

        static class IndexMap {
            Node[] map;

            public IndexMap(int n) {
                this.map = new Node[n];
            }

            public void put(int k, Node node) {
                int x = k % map.length;
                if (map[x] == null) {
                    map[x] = node;
                } else if (get(k) == null) {
                    node.next = map[x];
                    map[x] = node;
                }
            }

            public Node get(int k) {
                int x = k % map.length;
                for (Node v = map[x]; v != null; v = v.next) {
                    if (v.seatNo == k) {
                        return v;
                    }
                }
                return null;
            }

            public void remove(int k) {
                int x = k % map.length;
                for (Node v = map[x], pre = null; v != null; pre = v, v = v.next) {
                    if (v.seatNo == k) {
                        if (pre == null) {
                            map[x] = v.next;
                        } else {
                            pre.next = v.next;
                        }
                        break;
                    }
                }
            }
        }
    }

/**
 [],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
}
