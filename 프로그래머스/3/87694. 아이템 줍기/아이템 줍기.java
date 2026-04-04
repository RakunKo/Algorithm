import java.util.*;
import java.lang.Math.*;

class Solution {
    int MAX = 102;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        int[][] map = new int[MAX][MAX];
        
        for(int[] rect: rectangle) fill_map(map, rect);
        for(int[] rect: rectangle) remove_map(map, rect);
        
        int cnt = find_route(map, characterX*2, characterY*2, itemX*2, itemY*2);
        //System.out.print(cnt + " ");
        //for(int i=1;i<=20;i++) {
        //    for(int j=1;j<=20;j++) System.out.print(map[i][j] + " ");
        //    System.out.print("\n");   
        //}
        
        return cnt/2;
    }
    
    public void fill_map(int[][] map, int[] rect) {
        int sx = rect[0]*2;
        int sy = rect[1]*2;
        int dx = rect[2]*2;
        int dy = rect[3]*2;
        
        for(int i=sx;i<=dx;i++) 
            for(int j=sy;j<=dy;j++)
                map[i][j] = 1;
    }
    
    public void remove_map(int[][] map, int[] rect) {
        int sx = rect[0]*2;
        int sy = rect[1]*2;
        int dx = rect[2]*2;
        int dy = rect[3]*2;
        
        for(int i=sx+1;i<dx;i++) 
            for(int j=sy+1;j<dy;j++)
                map[i][j] = 0;
    }
    
    boolean[][] vis = new boolean[MAX][MAX];
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    
    public int find_route(int[][] map, int cx, int cy, int ix, int iy) {
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] dist = new int[MAX][MAX];
        
        for(int i=0;i<MAX;i++)
            for(int j=0;j<MAX;j++)
                dist[i][j] = Integer.MAX_VALUE;
        
        dq.offer(new int[]{cx, cy});
        dist[cx][cy] = 0;
        
        while(!dq.isEmpty()) {
            int[] curr = dq.poll();
            
            if(curr[0] == ix && curr[1] == iy) return dist[ix][iy];
            
            for(int i=0;i<4;i++) {
                int dx = curr[0] + dir[i][0];
                int dy = curr[1] + dir[i][1];
                
                if(map[dx][dy] == 0 || dist[dx][dy] != Integer.MAX_VALUE) continue;
                
                dq.offer(new int[]{dx, dy});
                dist[dx][dy] = dist[curr[0]][curr[1]] + 1;
            }
        }
        
        return 0;
    }   
}