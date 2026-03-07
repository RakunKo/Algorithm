import java.util.*;

class Solution {
    boolean[][] vis;
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    int n, m;
    
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        
        n = maps.length;
        m = maps[0].length();
        vis = new boolean[n][m];
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) if(maps[i].charAt(j) != 'X' && !vis[i][j]) {
                vis[i][j] = true;
                answer.add(dfs(maps, i, j));
            }
        }
        
        if(answer.size() == 0) answer.add(-1);
        Collections.sort(answer);
        
        return answer.stream().mapToInt(i->i).toArray();
    }
    
    public int dfs(String[] maps, int x, int y) {
        int cnt = 0;
        
        for(int i=0;i<4;i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
        
            if(dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
            if(maps[dx].charAt(dy) == 'X' || vis[dx][dy]) continue;
            
            vis[dx][dy] = true;
            cnt += dfs(maps, dx, dy);
        }
        
        return (int) (maps[x].charAt(y) - '0') + cnt;
    }
}