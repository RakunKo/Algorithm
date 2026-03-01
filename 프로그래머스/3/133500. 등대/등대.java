import java.util.*;
import java.lang.Math.*;

class Solution {
    boolean[] vis;
    int[][] dp;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        int k = lighthouse.length;
        List<Integer>[] adj = new ArrayList[n+1];
        
        for(int i=0;i<=n;i++) adj[i] = new ArrayList<>();
        
        for(int[] edge: lighthouse) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        
        dp = new int[n+1][2];
        vis = new boolean[n+1];
        traversal(1, n, lighthouse, adj);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    public void traversal(int node, int n, int[][] lighthouse, List<Integer>[] adj) {
        vis[node] = true;
        
        for(Integer next: adj[node]) {
            if(vis[next]) continue;
            traversal(next, n, lighthouse, adj);
            
            // 자신이 끄려면 다음 노드는 켜야함
            dp[node][0] += dp[next][1]; 
            
            // 자신이 키면 다음 노드는 상관없음, 근데 최소를 선택
            dp[node][1] += Math.min(dp[next][0], dp[next][1]); 
        }
        
        dp[node][1] += 1;
    }
}