import java.util.*;
import java.lang.Math.*;

class Solution {
    public int solution(int n, int[][] wires) {
        // n-1개의 간선은 한개 끊으면 무조건 2개로 나눠짐
        // n개의 vertex와 n-1개의 간선일 경우 무조건 트리
        List<Integer>[] adj = new ArrayList[n+1];
        
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for(int[] wire: wires) {
            adj[wire[0]].add(wire[1]);
            adj[wire[1]].add(wire[0]);
        }
        
        int answer = 101;
        for(int[] wire: wires) {
            int cnt = explore(adj, wire[0], wire[1], n);
            answer = Math.min(answer, Math.abs(cnt - Math.abs(cnt-n)));
        }
        
        return answer;
    }
    
    public int explore(List<Integer>[] adj, int v1, int v2, int n) {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] vis = new boolean[n+1];
        dq.offer(v1);
        vis[v1] = true;
        
        int cnt = 0;
        while(!dq.isEmpty()) {
            cnt++;
            int vertex = dq.poll();

            for(Integer i: adj[vertex]) {
                if(vertex == v1 && i == v2) continue;
                if(vertex == v2 && i == v1) continue;
                if(vis[i]) continue;
                vis[i] = true;
                dq.offer(i);
            }
        }
        
        return cnt;
    }
    
    
}