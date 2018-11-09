package leetcode;

/**
 * 数字1的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * <p>
 * 示例:
 * <p>
 * 输入: 13
 * 输出: 6
 * 解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
 * Created by debug on 2018/11/09.
 */
public class H233_NumberOfDigitOne {
    public static void main(String[] args) {
        Tools tools = new Tools();
        tools.println((new Solution()).countDigitOne(13));
        tools.println((new Solution()).countDigitOne(18));
    }

    // 递归 时间复杂度 log(n)
    static class Solution {
        public int countDigitOne(int n) {
            if (n <= 0) {
                return 0;
            }
            int len = length(n);
            if (len == 1) {
                return 1;
            }
            int pow = pow(len - 1, 10);
            int left = n / pow;
            int mod = n % pow;
            return countLength(len - 1) * left // 最高位(0~n)*(除去最高位出现1的排列组合次数)
                    + (left == 1 ? mod + 1 : pow) // 最高位的1出现次数
                    + countDigitOne(mod);// 剩余几位出现1的次数
        }

        private int countLength(int l) {
            if (l == 0) {
                return 0;
            }
            if (l == 1) {
                return 1;
            }
            int count = 0;
            for (int i = 1; i <= l; i++) {
                // i个1的个数=i*C_l_i*9^(l-i)
                count += i * c(l, i) * pow(l - i, 9);
            }
            return count;
        }

        /** 计算10进制数字的位数 */
        private int length(int n) {
            int len = 0;
            while (n > 0) {
                n = n / 10;
                len += 1;
            }
            return len;
        }

        /** 指数 */
        private int pow(int l, int base) {
            int result = 1;
            while (l > 0) {
                result *= base;
                l -= 1;
            }
            return result;
        }

        /** C_N_M 组合数公式 n!/(m!*(n-m)!) */
        private int c(int n, int m) {
            int result = 1;
            for (int i = n; i - m > 0; i--) {
                result *= i;
            }
            for (int i = n - m; i > 0; i--) {
                result /= i;
            }
            return result;
        }
    }
}
