import java.util.*;
import java.lang.Math.*;

class Solution {
    int answer = 0;
    
    
    public int solution(int n, int infection, int[][] edges, int k) {
        
        Map<Integer, Integer>[] adj = new HashMap[n+1];
        for(int i=1;i<=n;i++) adj[i] = new HashMap<>();
        
        for(int[] edge: edges) {
            adj[edge[0]].put(edge[1], edge[2]);
            adj[edge[1]].put(edge[0], edge[2]);
        }
        
        boolean[] infect = new boolean[n+1];
        infect[infection] = true;
        
        dfs(0, k, adj, n, 0, infect);
        
        return answer;
    }
    
    public void dfs(int d, int k, Map<Integer, Integer>[] adj, int n, int type, boolean[] infect) {
        if(d == k) {
            int cnt = 0;
            for(int i=1;i<=n;i++) if(infect[i]) cnt++;
            
            answer = Math.max(answer, cnt);
            return;
        }
        
        for(int i=1;i<=3;i++) {
            if(i == type) continue;
            boolean[] newInfect = bfs(adj, k, i, infect, n);
            dfs(d+1, k, adj, n, i, newInfect);
        }
    }
    
    public boolean[] bfs(Map<Integer, Integer>[] adj, int k, int type, boolean[] infect, int n) {
        boolean[] vis = new boolean[n+1];
        boolean[] newInfect = infect.clone();
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int i = 1; i <= n; i++) {
            if(infect[i]) {
                dq.offer(i);
                vis[i] = true;
            }
        }
        
        while(!dq.isEmpty()) {
            int v = dq.poll();
            
            for(Map.Entry<Integer, Integer> e : adj[v].entrySet()) {
                if(vis[e.getKey()] || e.getValue() != type) continue;
                
                newInfect[e.getKey()] = true;
                dq.offer(e.getKey());
                vis[e.getKey()] = true;
            }
        }
        
        //System.out.print(type+"\n");
        //for(int i=1;i<=n;i++) System.out.print(newInfect[i] + " ");
        //System.out.print("\n");
        
        return newInfect;
    }
}