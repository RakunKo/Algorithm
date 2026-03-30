import java.lang.Math.*;
import java.util.*;

class Solution {
    class Pos {
        int x; int y; int cost; int dir;
        public Pos(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }
    
    int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    int[][][] vis;
    
    public int solution(int[][] board) {
        int n = board.length;
        vis = new int[n][n][4];
        
        for(int i=0;i<n;i++) 
            for(int j=0;j<n;j++) 
                for(int k=0;k<4;k++) vis[i][j][k] = Integer.MAX_VALUE;
        
        return dijkstra(board, n);
    }
    
    public int dijkstra(int[][] board, int n) {
        PriorityQueue<Pos> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);   
        
        for(int i=0;i<4;i++) vis[0][0][i] = 0;
        pq.offer(new Pos(0,0,0,0));
        
        while(!pq.isEmpty()) {
            Pos p = pq.poll();
            
            if(vis[p.x][p.y][p.dir] < p.cost) continue;
            if(p.x == n-1 && p.y == n-1) return p.cost;
            
            for(int i=0;i<4;i++) {
                int dx = p.x + dir[i][0];
                int dy = p.y + dir[i][1];
                
                if(dx >= n || dx < 0 || dy >= n || dy < 0) continue;
                if(board[dx][dy] == 1) continue;
                
                int curr = 100;
                if(!(p.x == 0 && p.y == 0) && p.dir != i) curr += 500;
                
                if(vis[dx][dy][i] < p.cost + curr) continue;
                
                vis[dx][dy][i] = p.cost + curr;
                pq.offer(new Pos(dx, dy, p.cost + curr, i));
            }
        }
    
        return 0;
    }
}