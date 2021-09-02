import java.util.ArrayList;

/**
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 *
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 *
 * 窗口大于数组长度的时候，返回空
 */


public class Solution_006 {

    public ArrayList<Integer> maxInWindows(int [] num, int size) {

        int len = num.length;
        ArrayList<Integer> objects = new ArrayList<>();
        if(size > len || size <= 0) {
            return objects;
        }
        for (int i = 0; i <= len - size; i++) {
            int t = getMax(i, num, size);
            objects.add(t);
        }

        return objects;
    }

    public int getMax(int p, int[] num, int size) {
        int max = 0;
        for (int i = p; i < p + size; i++) {
            max = Math.max(max, num[i]);
        }
        return max;
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size, int type) {
        ArrayList<Integer> objects = new ArrayList<Integer>();



        return objects;
    }

    public static void main(String[] args) {
        Solution_006 solution_006 = new Solution_006();
        int[] ints = new int[]{2,3,4,2,6,2,5,1};
        ArrayList<Integer> integers = solution_006.maxInWindows(ints, 0);
    }
}
