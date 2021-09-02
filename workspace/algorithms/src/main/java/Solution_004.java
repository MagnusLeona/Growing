import java.util.LinkedList;
import java.util.Queue;

/**
 * 地上有一个rows行和cols列的方格。
 * 坐标从 [0,0] 到 [rows-1,cols-1]。
 * 一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于threshold的格子。
 * 例如，当threshold为18时，机器人能够进入方格[35,37]，因为3+5+3+7 = 18。但是，它不能进入方格[35,38]，因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 *
 * 范围:
 * 1 <= rows, cols<= 100
 * 0 <= threshold <= 20
 */


public class Solution_004 {

    public boolean check(int threshold, int r, int c) {
        int sum = 0;
        sum += (int) r % 10;
        sum += r / 10;
        sum += c % 10;
        sum += c / 10;
        if (sum <= threshold) return true;
        return false;
    }

    public int moving(int threshold, int r, int c, int rows, int cols, int[][] arr) {

        if (r < 0 || c < 0 || r >= rows || c >= cols || arr[r][c] == 1 || !check(threshold, r, c)) {
            return 0;
        }

        int result = 1;
        arr[r][c] = 1;
        if (r - 1 >= 0) {
            result += moving(threshold, r - 1, c, rows, cols, arr);
        }
        if (c - 1 >= 0) {
            result += moving(threshold, r, c - 1, rows, cols, arr);
        }
        if (r + 1 <= rows) {
            result += moving(threshold, r + 1, c, rows, cols, arr);
        }
        if (c + 1 <= cols) {
            result += moving(threshold, r, c + 1, rows, cols, arr);
        }

        return result;
    }

    public int movingCount(int threshold, int rows, int cols) {
        int[][] arr = new int[rows][cols];

        return moving(threshold, 0, 0, rows, cols, arr);
    }

    public int bfs(int threshold, int rows, int cols) {
        Queue queue = new LinkedList<>();

        int[][] arr = new int[rows][cols];

        int result = 0;
        queue.add(new int[]{0, 0});
        arr[0][0] = 1;
        while (queue.size() > 0) {
            // 从队列中取出一个点
            int[] poll = (int[]) queue.poll();
            // 取出之后，累加，表示多遍历了一个点
            result++;
            int r = poll[0];
            int c = poll[1];
            // 搜索上下左右四个点，符合的话就入队列： 符合条件是： 一个是点在格子里面，一个是格子的和必须满足小于等于threshold，另一个是这个点之前没有被遍历过。
            // 如果符合条件，就给队列增加一个点。
            if (r - 1 >= 0 && check(threshold, r - 1, c) && arr[r - 1][c] != 1) {
                // 这里需要重点关注的是，只要往队列里面插入了值，就表示这个值已经被遍历过了，所以应该在插入的时候就设置这个值的访问为1；
                arr[r - 1][1] = 1;
                queue.add(new int[]{r - 1, c});
            }
            if (c - 1 >= 0 && check(threshold, r, c - 1) && arr[r][c - 1] != 1) {
                arr[r][c - 1] = 1;
                queue.add(new int[]{r, c - 1});
            }
            if (r + 1 < rows && check(threshold, r + 1, c) && arr[r + 1][c] != 1) {
                arr[r + 1][c] = 1;
                queue.add(new int[]{r + 1, c});
            }
            if (c + 1 < cols && check(threshold, r, c + 1) && arr[r][c + 1] != 1) {
                arr[r][c + 1] = 1;
                queue.add(new int[]{r, c + 1});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution_004 solution_004 = new Solution_004();
//        int i = solution_004.movingCount(1, 2, 3);
        int i = solution_004.bfs(5, 10, 10);
        System.out.println("Result: " + i);
    }
}

/**
 * DFS和BFS遍历的思想： DFS采用优先一条路搜到底，如果没有后路了再回溯的原则，采用类似栈的结构（递归方式）获取最终结果。BFS则是优先遍历最近的节点，采用的是队列的方式：即遍历周边节点，符合条件就入队列，然后遍历这个队列直到为空。
 */
