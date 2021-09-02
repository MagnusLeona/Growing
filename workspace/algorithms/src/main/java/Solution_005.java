import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * [[a,b,c,e],[s,f,c,s],[a,d,e,e]]
 * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */
public class Solution_005 {

    public boolean bfs(char[][] matrix, String word) {
        //bfs
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] isVisited = new boolean[rows][cols];

        char c = word.charAt(0);
        Queue<Object> objects = new LinkedList<>();
        Queue<Object> target = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == c) {
                    objects.add(new int[]{i, j});
                    target.add(0);
                    isVisited[i][j] = true;
                }
            }
        }

        while (!objects.isEmpty()) {
            int[] poll = (int[]) objects.poll();
            int pivot = (int) target.poll();
            int row = poll[0];
            int col = poll[1];
            if (pivot - 1 == word.length() - 1) {
                return true;
            }
            if (row - 1 >= 0 && !isVisited[row - 1][col] && matrix[row - 1][col] == word.charAt(pivot + 1)) {
                objects.add(new int[]{row - 1, col});
                target.add(pivot + 1);
                isVisited[row - 1][col] = true;
            }
            if (row + 1 < rows && !isVisited[row + 1][col] && matrix[row + 1][col] == word.charAt(pivot + 1)) {
                objects.add(new int[]{row + 1, col});
                target.add(pivot + 1);
                isVisited[row + 1][col] = true;
            }
            if (col - 1 >= 0 && !isVisited[row][col - 1] && matrix[row][col - 1] == word.charAt(pivot + 1)) {
                objects.add(new int[]{row, col - 1});
                target.add(pivot + 1);
                isVisited[row][col - 1] = true;
            }
            if (col + 1 < cols && !isVisited[row][col + 1] && matrix[row][col + 1] == word.charAt(pivot + 1)) {
                objects.add(new int[]{row, col + 1});
                target.add(pivot + 1);
                isVisited[row][col + 1] = true;
            }
        }

        return false;
    }

    public boolean dfs(char[][] matrix, String word) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] isVisited = new boolean[rows][cols];

        char c = word.charAt(0);
        Queue<int []> objects = new LinkedList<int[]>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == c) {
                    objects.add(new int[]{i, j});
                }
            }
        }

        boolean result = false;

        while(!objects.isEmpty()) {
            isVisited = new boolean[rows][cols];
            int[] object = objects.poll();
            isVisited[object[0]][object[1]] = true;
            result = result || dfscheck(object[0], object[1], rows, cols, 0, matrix, word, isVisited);
        }

        return result;
    }

    public boolean dfscheck(int row, int col, int rows, int cols, int idxC, char[][] matrix, String word, boolean[][] isVisited) {
       if(matrix[row][col] != word.charAt(idxC)) {
           isVisited[row][col] = false;
           return false;
       }
        if (idxC == word.length() - 1) {
            return true;
        }

        boolean result = false;

        if(row - 1 >= 0 && !isVisited[row - 1][col]) {
            isVisited[row - 1][col] = true;
            result = result || dfscheck(row - 1, col, rows, cols, idxC + 1, matrix, word, isVisited);
            if(!result) {
                isVisited[row - 1][col] = false;
            }
        }
        if(row + 1 < rows && !isVisited[row + 1][col]) {
            isVisited[row + 1][col] = true;
            result = result || dfscheck(row + 1, col, rows, cols, idxC + 1, matrix, word, isVisited);
            if(!result) {
                isVisited[row + 1][col] = false;
            }
        }
        if(col - 1 >= 0 && !isVisited[row][col - 1]) {
            isVisited[row][col - 1] = true;
            result = result || dfscheck(row, col - 1, rows, cols, idxC + 1, matrix, word, isVisited);
            isVisited[row][col - 1] = false;
        }
        if(col + 1 < cols && !isVisited[row][col + 1]) {
            isVisited[row][col + 1] = true;
            result = result || dfscheck(row, col + 1, rows, cols, idxC + 1, matrix, word, isVisited);
            isVisited[row][col + 1] = false;
        }
        return result;
    }

    public boolean hasPath(char[][] matrix, String word) {

        boolean bfs = bfs(matrix, word);
        return bfs;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'A', 'B', 'C'}, {'B', 'E', 'D'}, {'F', 'G', 'G'}};
        String word = "ABCDEBF";

        Solution_005 solution_005 = new Solution_005();
        System.out.println(solution_005.dfs(matrix, word));
    }
}
