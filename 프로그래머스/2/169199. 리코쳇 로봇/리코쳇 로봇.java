import java.lang.Math.*;
import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    int[][] vis;
    
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        
        vis = new int[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) Arrays.fill(vis[i], Integer.MAX_VALUE);
        }
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) if(board[i].charAt(j)=='R') {
                vis[i][j] = 1;
                dfs(i,j, board, 0,n, m);
            }
        }
        
        if(answer == Integer.MAX_VALUE) return -1;
        else return answer;
    }
    
    public void dfs(int x, int y, String[] board, int move, int n, int m) {
        //for(int i=0;i<n;i++) {
         //   for(int j=0;j<m;j++) System.out.print(vis[i][j] + " ");
         //   System.out.print("\n");
        //}
         //   System.out.print("\n");
        
        if (move >= answer) return;

        if (board[x].charAt(y) == 'G') {
            answer = move;
            return;
        }
        
        for(int i=0;i<4;i++) {
            int dx = x;
            int dy = y;
            
            while (true) {
                int nx = dx + dir[i][0];
                int ny = dy + dir[i][1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx].charAt(ny) == 'D') break;

                dx = nx;
                dy = ny;
            }

            if (dx == x && dy == y) continue;
            if (vis[dx][dy] <= move + 1) continue;
            
            vis[dx][dy] = move+1;
            dfs(dx, dy, board, move+1, n, m);
        }
    }
}