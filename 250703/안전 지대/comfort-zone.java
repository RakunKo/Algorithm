import java.util.*;
import java.lang.Math.*;

public class Main {
    static int answer = 0;
    static int result_k = 1;
    static int result_value = 0;
    static int[][] pos = {{1,0},{-1,0},{0,1},{0,-1}};
    static int[][] vis;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        int max_value =0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) { 
                grid[i][j] = sc.nextInt();
                if(max_value < grid[i][j]) max_value = grid[i][j];
            }
        }
            
        int k = 1;
        while(k < max_value) {
            vis = new int[n][m];
            
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(grid[i][j] <= k || vis[i][j] == 1) continue;
                    answer++;
                    search_safe_area(grid,k,n,m,i,j);
                }
            }
            
            if(result_value < answer) {
                result_value = answer;
                result_k = k;
            }
            k++;
            answer = 0;
        }
        System.out.print(result_k + " " + result_value);
    }

    public static void search_safe_area(int[][] grid, int k, int n, int m, int x, int y) {
        vis[x][y] = 1;
        for(int i=0;i<4;i++) {
            int dx = x + pos[i][0];
            int dy = y + pos[i][1];
            if(dx>=n||dx<0||dy>=m||dy<0) continue;
            if(grid[dx][dy] <= k) continue;
            if(vis[dx][dy] == 1) continue;
            search_safe_area(grid,k,n,m,dx,dy);
        }
        return;
    }
}