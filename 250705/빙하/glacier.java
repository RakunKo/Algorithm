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
        vis = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j]==0) {
                    q.add(new Pair(i,j));
                }
            }
        }
        // Please write your code here.
        int sec = bfs(grid,n,m);
        System.out.print(sec-1 + " " + answer);
        return;
    }

    public static int bfs(int[][] grid, int n, int m) {
        int sec = 0;
        while(!q.isEmpty()) {
            sec++;
            //한 페이즈 모두 처리
            int size= q.size();
            answer = size;
            for(int i=0;i<size;i++) {
                Pair p = q.poll();
                if(p == null) break;
            
                int x = p.x;
                int y = p.y;
                vis[x][y] = 1;
                grid[x][y] = 0;

                List<Pair> temp = new ArrayList<>();
                for(int j=0;j<4;j++) {
                    int dx = x + pos[j][0];
                    int dy = y + pos[j][1];

                    if(dx>=n||dx<0||dy>=m||dy<0) continue;      //범위 초과
                    if(grid[dx][dy]==0) continue;       //이미 물이거나 방문했음.

                    temp.add(new Pair(dx, dy));
                }

                if(temp.size() == 4) {  //4방향 모두 얼음인 경우
                    q.add(new Pair(x,y)); //나중에 다시 진행하기 위해 저장
                } else {
                    for(Pair pair: temp) {
                        if(vis[pair.x][pair.y]==0) {
                            q.add(new Pair(pair.x, pair.y));   
                            vis[pair.x][pair.y]=1;
                        }
                    }   
                }
            }
        }
        return sec;
    }
        
}