import java.util.*;
import java.lang.Math;

public class Main {

    static int[][][] range = {
        {{0,0},{1,0},{2,0},{-1,0},{-2,0}},
        {{0,0},{0,1},{1,0},{0,-1},{-1,0}},
        {{0,0},{1,1},{-1,1},{1,-1},{-1,-1}}
    };
    static int result = 0;
    static List<int[]> ones = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j]==1) ones.add(new int[]{i,j});
            }
        }
        // Please write your code here.
        int[][] vis = new int[n][n];
        dfs(0, 0, grid, n, vis);
        System.out.println(result);
        return;
    }

    public static void dfs(int d, int bomb, int[][] grid, int n, int[][] vis) {
        if(d == ones.size()) {    //모두 폭발
            result = Math.max(result, bomb);
            return;
        }
        int x = ones.get(d)[0];
        int y = ones.get(d)[1];
        
        for(int k=0;k<3;k++) {
            List<int[]> marked = new ArrayList<>();
            int cnt =0;
            for(int h=0;h<5;h++) {
                int dx = x+range[k][h][0];
                int dy = y+range[k][h][1];
                if(isRange(dx, dy, n, vis)) {
                    cnt++;
                    marked.add(new int[]{dx, dy});
                    vis[dx][dy]=1;
                }    
            }
            
            dfs(d+1, bomb+cnt, grid, n, vis);
            for (int[] pos : marked) {
                vis[pos[0]][pos[1]] = 0; 
            }
        }
        return;
    }

    public static boolean isRange(int dx, int dy, int n, int[][] vis) {
        if(dx<n && dx>=0 && dy<n && dy>=0 && vis[dx][dy]==0) return true;
        else return false;
    }
}