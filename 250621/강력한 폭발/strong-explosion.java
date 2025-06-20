import java.util.Scanner;
import java.lang.Math;

public class Main {

    static int[][][] range = {
        {{0,0},{1,0},{2,0},{-1,0},{-2,0}},
        {{0,0},{0,1},{1,0},{0,-1},{-1,0}},
        {{0,0},{1,1},{-1,1},{1,-1},{-1,-1}}
    };
    static int result = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j]==1) cnt++;
            }
        }
        // Please write your code here.
        int[][] vis = new int[n][n];
        dfs(0, 0, grid, n, cnt, vis);
        System.out.println(result);
        return;
    }

    public static void dfs(int d, int bomb, int[][] grid, int n, int c, int[][] vis) {
        if(d == c) {    //모두 폭발
            result = Math.max(result, bomb);
            return;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(grid[i][j]==0) continue;
                if(vis[i][j]==1) continue;
                for(int k=0;k<3;k++) {
                    int[][] current = new int[n][n];
                    for (int a = 0; a < n; a++) {
                        for (int b = 0; b < n; b++) {
                            current[a][b] = vis[a][b];
                        }
                    }
                    int cnt =0;
                    for(int h=0;h<5;h++) {
                        int dx = i+range[k][h][0];
                        int dy = j+range[k][h][1];
                        if(isRange(dx, dy, n, current)) {
                            cnt++;
                            current[dx][dy]=1;
                        }    
                    }
                    dfs(d+1, bomb+cnt, grid, n, c, current);
                }
            }
        }
        return;
    }

    public static boolean isRange(int dx, int dy, int n, int[][] current) {
        if(dx<n && dx>=0 && dy<n && dy>=0 && current[dx][dy]==0) return true;
        else return false;
    }
}