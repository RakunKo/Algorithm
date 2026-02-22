import java.util.*;

class Solution {
    
    class Pos{
        int x; int y; int dest; int idx;
        public Pos(int x, int y, int dest, int idx) {
            this.x = x;
            this.y = y;
            this.dest = dest;
            this.idx = idx;
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int n = routes.length;
        int rc = routes[0].length;
        
        int move = 1;
        int[][] vis = new int[101][101];
        boolean[][] crash = new boolean[101][101];
        Pos[] robots = new Pos[n];
        for(int i=0;i<n;i++) {
            int start = routes[i][0];
            int end = routes[i][1];
            int x = points[start-1][0];
            int y = points[start-1][1];
            robots[i] = new Pos(x, y, end, 1);
            
            if(vis[x][y] == move && !crash[x][y]) {
                crash[x][y] = true;
                answer++;
            }
            vis[x][y] = move;
        }
        
        //System.out.print(answer);
        
        int out = 0;
        while(out < n) {
            move++;
            crash = new boolean[101][101];
            
            //로봇별 움직임
            for(int i=0;i<n;i++) {
                if(robots[i].idx == rc) continue;
                
                int destX = points[robots[i].dest-1][0];
                int destY = points[robots[i].dest-1][1];
                
                //이동
                int afterX = robots[i].x;
                int afterY = robots[i].y;
                if(robots[i].x - destX != 0) {
                    if(robots[i].x > destX) afterX--;
                    else afterX++;
                } else {
                    if(robots[i].y > destY) afterY--;
                    else afterY++;
                }
                
                // 충돌 확인
                if(vis[afterX][afterY] == move && !crash[afterX][afterY]) {
                    crash[afterX][afterY] = true;
                    answer++;
                }
                vis[afterX][afterY] = move;
                
                // 목적지 도착
                if(destX == afterX && destY == afterY) {
                    robots[i].idx++;
                    if(robots[i].idx < rc) robots[i].dest = routes[i][robots[i].idx];
                    else out++;
                } 
                
                robots[i].x = afterX;
                robots[i].y = afterY;
            }
            
            //for(int i=1;i<=8;i++) {
            //    for(int j=1;j<=8;j++) System.out.print(vis[i][j] + " ");
            //    System.out.print("\n");
            //}
            //System.out.print("\n");
        }
        
        return answer;
    }
}