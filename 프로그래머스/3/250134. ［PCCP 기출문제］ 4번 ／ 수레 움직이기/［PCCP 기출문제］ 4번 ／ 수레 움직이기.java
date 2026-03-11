class Solution {
    int[][][] dir = {
        {{1,0}, {1,0}},{{1,0}, {-1,0}},{{1,0}, {0,1}},{{1,0}, {0,-1}},
        {{-1,0}, {1,0}},{{-1,0}, {-1,0}},{{-1,0}, {0,1}},{{-1,0}, {0,-1}},
        {{0,1}, {1,0}},{{0,1}, {-1,0}},{{0,1}, {0,1}},{{0,1}, {0,-1}},
        {{0,-1}, {1,0}},{{0,-1}, {-1,0}},{{0,-1}, {0,1}},{{0,-1}, {0,-1}},
    };
    
    boolean[][][] vis;
    int n, m;
    int t_rx, t_ry, t_bx, t_by;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        vis = new boolean[2][n][m];
        
        int rx = 0, ry = 0, bx = 0, by = 0;        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(maze[i][j] == 1) {
                    rx = i;
                    ry = j;
                }
                if(maze[i][j] == 2) {
                    bx = i;
                    by = j;
                }
                if(maze[i][j] == 3) {
                    t_rx = i;
                    t_ry = j;
                }
                if(maze[i][j] == 4) {
                    t_bx = i;
                    t_by = j;
                }
            }
        }
        
        vis[0][rx][ry] = true;
        vis[1][bx][by] = true;
        dfs(0, rx, ry, bx, by, false, false, maze);
        
        if(answer == Integer.MAX_VALUE) return 0;
        else return answer;
    }
    
    public void dfs(int trun, int rx, int ry, int bx, int by, boolean red, boolean blue, int[][] maze) {
        //System.out.println(rx +" "+ry+" "+bx+" "+by);
        if (trun >= answer) return;
        if(red && blue) {
            if(answer > trun) answer = trun;
            return;
        }
        
        for(int i=0;i<16;i++) {
            int drx = rx; int dry = ry;
            if(!red) {
                drx = rx + dir[i][0][0];
                dry = ry + dir[i][0][1];
                if(!isValid(drx, dry, maze, 0)) continue;
            }
            
            int dbx = bx; int dby = by;
            if(!blue) {
                dbx = bx + dir[i][1][0];
                dby = by + dir[i][1][1];
                if(!isValid(dbx, dby, maze, 1)) continue;       
            }
            
            //충돌
            if(drx == dbx && dry == dby) continue;
            //자리바꿈
            if(drx == bx && dry == by && dbx == rx && dby == ry) continue;
            
            boolean t_red = red; boolean t_blue = blue;
            if(drx == t_rx && dry == t_ry) t_red = true;            
            if(dbx == t_bx && dby == t_by) t_blue = true;

            
            if (!t_red) vis[0][drx][dry] = true;
            if (!t_blue) vis[1][dbx][dby] = true;
            
            dfs(trun+1, drx, dry, dbx, dby, t_red, t_blue, maze);
            
            if (!t_red) vis[0][drx][dry] = false;
            if (!t_blue) vis[1][dbx][dby] = false;
        }
    }
    
    public boolean isValid(int x, int y, int[][] maze, int type) {
        if (x < 0 || x >= n || y < 0 || y >= m) return false;
        if (maze[x][y] == 5) return false;
        if (vis[type][x][y]) return false;

        return true;
    }
}