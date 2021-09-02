import java.util.ArrayList;

/**
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
 * 每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 */


public class Solution_003 {

    public int cutRope1(int target) {
        if(target == 0 || target == 1) {
            return target;
        }
        if(target == 2) {
            return 1;
        }
        int result = 1;
        int left = target % 3;
        int cut = (target - left) / 3;
        if(left == 1) {
            cut -= 1;
            for (int i = 0; i < cut; i++) {
                result *= 3;
            }
            result *= 4;
        } else if(left == 2) {
            for (int i = 0; i < cut; i++) {
                result *= 3;
            }
            result *= 2;
        } else {
            for (int i = 0; i < cut; i++) {
                result *= 3;
            }
        }

        return result;
    }

    public int cutRope2(int target) {
        if(target == 1) {
            return 0;
        } else if(target == 2) {
            return 1;
        } else if(target == 3) {
            return 3;
        } else if(target == 4) {
            return 4;
        }

        int[] arr = new int[target + 1];

        int result = 0;

        for (int i = 1; i < target; i++) {
            result = Math.max(result, i * cutRope2(target - i));
        }

        return result;
    }

    public int cutRope3(int target) {
        if(target == 1) {
            return 0;
        }
        if(target == 2) {
            return 1;
        }
        if(target == 3) {
            return 2;
        }
        int[] arr = new int[target + 1];
        arr[1] = 0;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;
        return support(target, arr);
    }

    public int cutRope4(int target) {
        if(target <= 3) {
            return target -1;
        }

        int[] arr = new int[target + 1];
        for (int i = 0; i <= 4; i++) {
            arr[i] = i;
        }

        for (int i = 5; i <= target; i++) {
            for (int j = 1; j < i; j++) {
                arr[i] = Math.max(arr[i], j * arr[i - j]);
            }
        }

        return arr[target];
    }

    public int support(int target, int[] arr) {
        // 方法三 优化暴力算法
        if(arr[target] != 0 || target == 0) {
            return arr[target];
        }

        int result = 0;

        for (int i = 1; i < target; i++) {
            result = Math.max(result, i * support(target - i, arr));
        }

        arr[target] = result;

        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Solution_003 solution_003 = new Solution_003();
        System.out.println(solution_003.cutRope4(10));
        long end = System.currentTimeMillis();
        System.out.println("TimeCost: " + (end - start));
    }
}


/**
 * 思路：
 *  1.最简单的是用数学的方式去论证，数值相差越小表明乘积越大，最后证明全部分成3的时候是最大的。
 *
 *  2.使用暴力递归求解：n长的绳子，最大乘积一定是 1*cutRope(n-1), 2*cutRope(n-2)等等中的最大值
 *
 *  3.优化上述暴力递归： 将每一个获取到的cutRope进行存取，下一次用的之后直接读取就可以了。
 *
 *  4.整合方法： 动态规划
 */