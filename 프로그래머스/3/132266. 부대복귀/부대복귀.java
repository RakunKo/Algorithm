import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        int k = roads.length;
        List<Integer>[] adj = new ArrayList[n+1];
        for(int i=0;i<n+1;i++) adj[i] = new ArrayList<>();
        
        for(int[] road: roads) {
            adj[road[0]].add(road[1]);
            adj[road[1]].add(road[0]);
        }
        
        int[] vis = bfs(adj, destination, n);
        
        for(int i=0;i<sources.length;i++) {
            answer[i] = vis[sources[i]] - 1;
        }
        
        return answer;
    }
    
    public int[] bfs(List<Integer>[] adj, int destination, int n) {
        Queue<Integer> q = new LinkedList<>();
        int[] vis = new int[n+1];
        vis[destination] = 1;
        q.offer(destination);
        
        while(!q.isEmpty()) {
            Integer region = q.poll();
            
            for(Integer next: adj[region]) {
                if(vis[next] > 0) continue;
                vis[next] = vis[region] + 1;
                q.offer(next);
            }
        }
        
        return vis;
    }
}