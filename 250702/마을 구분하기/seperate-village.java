import java.util.*;
import java.lang.Math.*;

public class Main {
    static int[][] pos = {
        {1,0}, {-1,0}, {0,1}, {0, -1}
    };

    static int[][] vis;
    static int cnt = 0;
    static int result = 0;
    static int village = 0;
    static List<Integer> cntList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        vis = new int[n][n];

        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
                
        // Please write your code here.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 0 || vis[i][j] == 1) continue;
                village++;
                cnt = 1;
                result = 0;
                dfs(grid, 1, n, i, j);
                cntList.add(result);
            }
        }

        Collections.sort(cntList);
        System.out.println(village);
        for(Integer element : cntList) System.out.println(element);
        return;
    }

    public static void dfs(int[][] grid, int depth, int n, int x, int y) {
        result = Math.max(cnt, depth);
        vis[x][y] = 1;

        for(int k=0;k<4;k++) {
            int dx = x + pos[k][0];
            int dy = y + pos[k][1];

            if(dx >=n || dx <0 || dy >=n || dy <0) continue; //범위를 넘을 경우
            if(grid[dx][dy] == 0 || vis[dx][dy] == 1) continue; //벽일 경우
            cnt++;
            dfs(grid, depth+1, n, dx, dy);
        }
        return;
    }
}