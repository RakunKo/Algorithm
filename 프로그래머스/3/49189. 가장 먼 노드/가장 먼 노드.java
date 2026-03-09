import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[] vis;
    
    public int solution(int n, int[][] edge) {
        vis = new int[n+1];
        adj = new ArrayList[n+1];
        for(int i=1;i<=n;i++) adj[i] = new ArrayList<>();
        
        for(int[] e: edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        int answer = bfs();
        
        return answer;
    }
    
    public int bfs() {
        int cnt = 0;
        int max_dist = 0;
        
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(1);
        vis[1] = 1;
        
        while(!dq.isEmpty()) {
            int curr = dq.poll();
            
            if(vis[curr] > max_dist) {
                max_dist = vis[curr];
                cnt = 1;
            }else if(vis[curr] == max_dist) cnt++;
            
            for(int next: adj[curr]) {
                if(vis[next] > 0) continue;
                
                vis[next] = vis[curr] + 1;
                dq.offer(next);
            }
        }
        
        return cnt;
    }
}