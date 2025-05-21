import java.util.*;
import java.lang.Math.*;
public class Main {
    static int[][] dir = {{1,1},{-1,1},{-1,-1},{1,-1}};  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        // 가로,세로를 각각 row, col이라고 하고, 
        // row, col은 nxn격자 안에서 최대 n-1까지 가능(1~n-1)
        int anwser = 0;
        for(int row=2;row<=n-1;row++) {
            for(int col=2;col<=n-1;col++) {
                int result = _search(grid, row, col, n);
                anwser = Math.max(anwser, result);
            }
        }
        System.out.print(anwser);
    }

    //순회
    public static int _search(int[][] grid, int row, int col, int n) {
        int result = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                int sum = sumOfDiamond(grid, row, col, n, i, j);
                result = Math.max(result, sum);
            }
        }
        return result;
    }

    public static int sumOfDiamond(int[][] grid, int row, int col, int n, int cx, int cy) {
        int sum = 0;
        int dx = cx; int dy = cy;
        for(int k=0;k<4;k++) {
            int idx = (k%2==0) ? row : col;
            for(int h=1;h<idx;h++) {
                dx += dir[k][0];
                dy += dir[k][1];
                if(dx >= n || dx < 0 || dy >= n || dy < 0) {
                    return 0;
                }
                sum += grid[dx][dy];
            }
        }
        return sum;
    }
}