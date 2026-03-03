import java.util.*;

class Solution {
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    
    class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        int h = maps.length;
        int w = maps[0].length();
        
        Pos start = null;
        Pos laber = null;
        Pos exit = null;
        
        for(int i=0;i<h;i++) {
            for(int j=0;j<w;j++) {
                if(maps[i].charAt(j) == 'S') start = new Pos(i,j);
                if(maps[i].charAt(j) == 'L') laber = new Pos(i,j);
                if(maps[i].charAt(j) == 'E') exit = new Pos(i,j);
            }
        }
        
        int dis1 = bfs(maps, start.x, start.y, laber.x, laber.y, h, w);
        int dis2 = bfs(maps, laber.x, laber.y, exit.x, exit.y, h, w);
        
        if(dis1 == -1 || dis2 == -1) return -1;
        else return dis1+dis2;
    }
    
    public int bfs(String[] maps, int x1, int y1, int x2, int y2, int h, int w) {
        int[][] vis = new int[h][w];
        Deque<Pos> dq = new ArrayDeque<>();
        dq.offer(new Pos(x1, y1));
        vis[x1][y1] = 0;
        
        while(!dq.isEmpty()) {
            Pos p = dq.poll();
            
            for(int i=0;i<4;i++) {
                int dx = p.x + dir[i][0];
                int dy = p.y + dir[i][1];
                
                if(dx < 0 || dx >= h || dy < 0 || dy >= w) continue;
                if(vis[dx][dy] > 0) continue;
                if(maps[dx].charAt(dy) == 'X') continue;
                
                vis[dx][dy] = vis[p.x][p.y] + 1;
                if(dx == x2 && dy == y2) return vis[dx][dy];
                dq.offer(new Pos(dx, dy));
            }
        }
        
        return -1;
    }
}