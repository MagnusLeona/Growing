/**
 * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 *
 * 给定 target = 3，返回 false。
 *
 * 0 <= array.length <= 500
 * 0 <= array[0].length <= 500
 */

public class Solution_001 {

    public int[][] array = {{1,2,8,9}, {2,4,9,12}, {4,7,10,13}, {6,8,11,15}};

    public int[][] arr2 = {{1,1}};

    public boolean Find(int target, int[][] array) {
        // 如果数组为空，则直接返回
        if(array.length == 0 || array[0].length == 0) {
            return false;
        }

        int col = array.length;
        int row = array[0].length;

        int i = col - 1;
        int j = 0;

        while(i >=0 && j <= row - 1) {
            if(array[i][j] == target) {
                return true;
            } else if(array[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution_001 solution_001 = new Solution_001();
        boolean find = solution_001.Find(7 ,solution_001.array);
        System.out.println(find);
    }
}
