import java.util.*;

public class Main {
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Pair> q = new LinkedList<>();
    static int[][] map;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] grid = new int[n][n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j] == 2) {
                    q.add(new Pair(i,j));
                    map[i][j]= 1;
                }
                else if(grid[i][j] == 0) map[i][j] = -1;
            }
        }
        // Please write your code here.
        bfs(grid, n);
    }

    public static void bfs(int[][] grid, int n) {
        while(!q.isEmpty()) {
            Pair p = q.poll();
            
            for(int i=0;i<4;i++) {
                int dx = p.x + dir[i][0];
                int dy = p.y + dir[i][1];

                if(dx>=n||dx<0||dy>=n||dy<0) continue;
                if(map[dx][dy] == -1 || map[dx][dy] > 0) continue; //아무것도 없을때 or 방문

                q.add(new Pair(dx,dy));
                map[dx][dy] = map[p.x][p.y] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] >= 1) System.out.print((map[i][j]-1) + " ");
                else if (map[i][j] ==0 ) System.out.print(-2 + " ");
                else System.out.print(map[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

}