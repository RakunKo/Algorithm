import java.util.*;

class Solution {
    int MAX = 5;
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    
    class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[MAX];
        
        for(int k=0;k<MAX;k++) {
            int bit = 1;
            
            for(int i=0;i<MAX;i++) {
                for(int j=0;j<MAX;j++) {
                    if(places[k][i].charAt(j)=='P') {
                        int result = bfs(places[k], i, j);
                        //System.out.println("dd: " + result);
                        bit = result & bit;
                    }
                }
            }    
            
            answer[k] = bit;
        }
        
        return answer;
    }
    
    public int bfs(String[] place, int x, int y) {
        Deque<Pos> dq = new ArrayDeque<>();
        int[][] vis = new int[MAX][MAX];
        
        vis[x][y] = 1;
        dq.offer(new Pos(x, y));
        
        while(!dq.isEmpty()) {
            Pos p = dq.poll();
            
            if(vis[p.x][p.y]-1 >= 2) continue;
            
            for(int i=0;i<4;i++) {
                int dx = p.x + dir[i][0];
                int dy = p.y + dir[i][1];
                
                if(dx<0 || dx>=MAX || dy<0 || dy>=MAX) continue;
                if(vis[dx][dy] > 0) continue;
                if(place[dx].charAt(dy)=='X') continue;
                if(vis[p.x][p.y] <= 2 && place[dx].charAt(dy)=='P') return 0;
                
                vis[dx][dy] = vis[p.x][p.y] + 1;
                dq.offer(new Pos(dx, dy));
            }
        }
        
        return 1;
    }
}