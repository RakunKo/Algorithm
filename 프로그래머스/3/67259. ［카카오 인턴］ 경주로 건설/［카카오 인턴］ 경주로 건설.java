import java.lang.Math.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    
    int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    int[][][] vis;
    int n;
    
    public int solution(int[][] board) {
        n = board.length;
        vis = new int[n][n][4];
        
        for(int i=0;i<n;i++) 
            for(int j=0;j<n;j++) 
                for(int k=0;k<4;k++) vis[i][j][k] = Integer.MAX_VALUE;
        
        for(int i=0;i<4;i++) vis[0][0][i] = 0;
        dfs(board, 0, 0, 0, 0);
        
        return answer;
    }
    
    public void dfs(int[][] board, int x, int y, int prev, int cost) {
        //System.out.println(x + " " + y);
        if(answer <= cost) return;
        
        if(x == n-1 && y == n-1) {
            answer = Math.min(answer, cost);
            return;
        }
        
        for(int i=0;i<4;i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
            
            if(dx >= n || dx < 0 || dy >= n || dy < 0) continue;
            if(board[dx][dy] == 1) continue;
            
            int curr = 100;
            if(!(x == 0 && y == 0) && prev != i) curr += 500;
            
            if(vis[dx][dy][i] < cost + curr) continue;
            
            vis[dx][dy][i] = cost + curr;
            dfs(board, dx, dy, i, cost + curr);
        }
    }
}