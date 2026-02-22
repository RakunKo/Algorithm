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
        
        Map<String, Integer> crash = new HashMap<>();
        Pos[] robots = new Pos[n];
        for(int i=0;i<n;i++) {
            int start = routes[i][0];
            int end = routes[i][1];
            int x = points[start-1][0];
            int y = points[start-1][1];
            robots[i] = new Pos(x, y, end, 1);
            
            String key = x+":"+y;
            crash.put(key, crash.getOrDefault(key, 0) + 1);
        }
        
        for(int cnt : crash.values()) {
            if(cnt >= 2) answer++;
        }
        
        int out = 0;
        while(out < n) {
            crash = new HashMap<>();
            
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
                String key = afterX+":"+afterY;
                crash.put(key, crash.getOrDefault(key, 0) + 1);
                
                // 목적지 도착
                if(destX == afterX && destY == afterY) {
                    robots[i].idx++;
                    if(robots[i].idx < rc) robots[i].dest = routes[i][robots[i].idx];
                    else out++;
                } 
                
                robots[i].x = afterX;
                robots[i].y = afterY;
            }
            
            for(int cnt : crash.values()) {
                if(cnt >= 2) answer++;
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