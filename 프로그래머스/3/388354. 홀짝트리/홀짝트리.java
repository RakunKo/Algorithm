import java.util.*;

class Solution {

    boolean[] vis;
    int oddEvenCnt, reverseCnt;
        
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        int maxNode = 0;
        for (int n : nodes) maxNode = Math.max(maxNode, n);
        
        int n = nodes.length;
        
        List<Integer>[] adj = new ArrayList[maxNode+1];
        for(int i=0; i<maxNode+1; i++) adj[i] = new ArrayList<>();
        
        for(int[] edge: edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        
        vis = new boolean[maxNode+1];
        for(int node: nodes) {
            if(vis[node]) continue;
            oddEvenCnt = 0; reverseCnt = 0;
            decision(node, adj);
            
            if (oddEvenCnt == 1) answer[0]++;
            if (reverseCnt == 1) answer[1]++;
        }
        
        return answer;
    }
    
    public void decision(int n, List<Integer>[] adj) {  
        vis[n] = true;  
        
        // value 홀짝 체크(T -> 홀, F -> 짝)
        boolean v = n % 2 == 1;
        
        // child.size() 홀짝 체크
        boolean c = adj[n].size() % 2 == 1;
        
        int type;
        if(v == c) oddEvenCnt++;
        else reverseCnt++;
        
        for(Integer chlid: adj[n]) {
            if(vis[chlid]) continue;
            decision(chlid, adj);
        }
    }
}