import java.util.Scanner;
public class Main {

    static int[][] dir = {
        {1,0},{-1,0},{0,1},{0,-1},{0,0}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        int[] bombCols = new int[m];
        for (int i = 0; i < m; i++)
            bombCols[i] = sc.nextInt();
        // Please write your code here.
        for(int i=0;i<m;i++) {
            gravity(grid,bombCols[i],n,m);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) System.out.print(grid[i][j] + " ");
            System.out.print("\n");
        }
        return;
    }

    public static void gravity(int[][] grid, int col, int n, int m) {
        int x = 0; int y = col-1;
        if(grid[n-1][y] == 0) return; //변화 X
        for(int i=n-1;i>=1;i--) {
            if(grid[i-1][y] == 0) {
                x = i;
                break;
            }
        }
        //bomb
        int colValue = grid[x][y];
        for(int i=0;i<5;i++) {
            for(int j=0;j<colValue;j++) {
                int dx = x+dir[i][0]*j;
                int dy = y+dir[i][1]*j;
                if(dx>=n||dx<0||dy>=n||dy<0) break;

                grid[dx][dy] = 0;
            }
        }
        //gravity
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j >= 0; j--) {
                if(grid[j][i] == 0) {
                    for(int k=j-1;k>=0;k--) {
                        if(grid[k][i] != 0) {
                            grid[j][i] = grid[k][i];
                            grid[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
        return;
    }
}

