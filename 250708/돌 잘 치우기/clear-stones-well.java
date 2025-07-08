import java.util.*;
import java.lang.Math.*;

public class Main {
    static class Pair{
        int x,y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Pair> q = new LinkedList<>();
    static int[][] vis_bfs;
    static int[][] vis_dfs;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][n];
        int stone = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j]==1) stone++;
            }
        }
        int[][] startPoints = new int[k][2];
        for (int i = 0; i < k; i++) {
            startPoints[i][0] = sc.nextInt()-1;
            startPoints[i][1] = sc.nextInt()-1;
        }
        // Please write your code here.
        if(stone > 8) m = 8;
        vis_dfs = new int[n][n];
        dfs(0,grid, n,m,startPoints,k,0,0);
        System.out.print(answer);
    }

    public static void bfs(int[][] grid, int n, int x, int y) {
        while(!q.isEmpty()) {
            Pair p = q.poll();
            vis_bfs[p.x][p.y] = 1;
            for(int i=0;i<4;i++) {
                int dx = p.x + dir[i][0];
                int dy = p.y + dir[i][1];

                if(dx>=n||dx<0||dy>=n||dy<0) continue;
                if(grid[dx][dy] == 1 && vis_dfs[dx][dy]==0) continue;
                if(vis_bfs[dx][dy] == 1)continue;

                q.add(new Pair(dx, dy));
            }
        }
    }

    public static int countMove(int[][] vis, int n) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(vis[i][j]==1) cnt++;
            }
        }
        return cnt;
    }

    public static void dfs(int depth, int[][] grid, int n, int m, int[][] startPoints, int k, int x, int y) {
        if(depth == m) {
            vis_bfs = new int[n][n];
            for(int i=0;i<k;i++) {
                if(vis_bfs[startPoints[i][0]][startPoints[i][1]]==1)continue;
                q.add(new Pair(startPoints[i][0], startPoints[i][1]));
                bfs(grid, n, startPoints[i][0], startPoints[i][1]);
            }
            answer = Math.max(countMove(vis_bfs,n), answer);
        }
        for(int i=x;i<n;i++) {
            int j;
            if(i==x) j = y+1;
            else j=0;
            for(;j<n;j++) {
                if(grid[i][j] == 0) continue;
                vis_dfs[i][j] = 1;
                dfs(depth+1, grid, n,m,startPoints,k,i,j);
                vis_dfs[i][j] = 0;
            }
        }
        return;
    }


}