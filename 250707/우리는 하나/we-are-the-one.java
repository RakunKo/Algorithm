import java.util.*;
import java.lang.Math.*;
public class Main {
    static int[][] vis;
    static int[][] vis_temp;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static class Pair {
        int x,y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<List<Pair>> countryList = new ArrayList<>();
    static int index = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int u = sc.nextInt();
        int d = sc.nextInt();
        vis_temp = new int[n][n];
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bfs(grid, n, i, j, u, d);
            }
        }

        List<Integer> sizeList = new ArrayList<>();
        for(List<Pair> l: countryList) sizeList.add(l.size());
        Collections.sort(sizeList, Collections.reverseOrder());

        int result = 0;
        for(int i=0;i<k;i++) result += sizeList.get(i);

        System.out.print(result);
    }

    public static void bfs(int[][] grid, int n, int x, int y, int u, int d) {
        if(vis_temp[x][y]==1) return;
        vis = new int[n][n];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        vis[x][y] = 1;
        vis_temp[x][y] = 1;
        countryList.add(new ArrayList<>());

        while(!q.isEmpty()) {
            Pair p = q.poll();
            countryList.get(index).add(new Pair(p.x,p.y));
            vis_temp[p.x][p.y] = 1;

            for(int i=0;i<4;i++) {
                int dx = p.x + dir[i][0];
                int dy = p.y + dir[i][1];

                if(dx>=n||dx<0||dy>=n||dy<0) continue;
                if(vis[dx][dy] == 1) continue;
                if(Math.abs(grid[p.x][p.y]-grid[dx][dy]) < u || Math.abs(grid[p.x][p.y]-grid[dx][dy]) > d) continue;

                vis[dx][dy] = 1;
                q.add(new Pair(dx, dy));
            }
        }
        index++;
    }
}