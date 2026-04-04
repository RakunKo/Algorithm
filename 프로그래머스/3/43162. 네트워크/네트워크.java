import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        List<Integer>[] adj = new ArrayList[n];
        for(int i=0;i<n;i++) adj[i] = new ArrayList<>();
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i==j) continue;
                if(computers[i][j] == 1) adj[i].add(j);
            }
        }
        
        boolean[] vis = new boolean[n];
        for(int i=0;i<n;i++) {
            if(vis[i]) continue;
            answer++;
            bfs(vis, adj, i);
        }
        
        return answer;
    }
    
    public void bfs(boolean[] vis, List<Integer>[] adj, int v) {
        Deque<Integer> dq = new ArrayDeque<>();
        
        vis[v] = true;
        dq.offer(v);
        
        while(!dq.isEmpty()) {
            int curr = dq.poll();
            
            for(int next: adj[curr]) {
                if(vis[next]) continue;
                
                vis[next] = true;
                dq.offer(next);
            }
        }
    }
}