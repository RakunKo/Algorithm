import java.util.*;
public class Main {
    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Queue<Pair> q = new LinkedList<>();
    static int[][] vis;
    static int[][] pos = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        int sec = bfs(grid, n,m);
        System.out.print(sec + " " + answer);
        return;
    }

    public static int bfs(int[][] grid, int n, int m) {
        int sec = 0;
        while(true) {
            // 외부 물 갱신
            vis = new int[n][m];
            q.clear();
            q.add(new Pair(0,0));
            while(!q.isEmpty()) {
                Pair p = q.poll();
                vis[p.x][p.y] = 1;
                for(int i=0;i<4;i++) {
                    int dx = p.x + pos[i][0];
                    int dy = p.y + pos[i][1];
                    if(dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
                    if(grid[dx][dy] == 1 || vis[dx][dy] == 1) continue;
                    q.add(new Pair(dx, dy));
                }
            }

            List<Pair> toMelt = new ArrayList<>();
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    if(grid[i][j] == 1) {
                        for(int d=0; d<4; d++) {
                            int nx = i + pos[d][0];
                            int ny = j + pos[d][1];
                            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                            if(vis[nx][ny] == 1) { // 외부 물과 연결된 물
                                toMelt.add(new Pair(i,j));
                                break;
                            }
                        }
                    }
                }
            }

            if(toMelt.size() == 0) break; // 더 이상 녹일 얼음 없음
            for(Pair p : toMelt) grid[p.x][p.y] = 0;
            
            answer = toMelt.size();
            sec++;
        }
        return sec;
    }

    
        
}