import java.util.*;
import java.lang.Math.*;

class Solution {
    public int solution(int n, int[][] wires) {
        // n-1개의 간선은 한개 끊으면 무조건 2개로 나눠짐
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for(int[] wire: wires) {
            Set<Integer> s1 = graph.getOrDefault(wire[0], new HashSet<>());
            s1.add(wire[1]);
            graph.put(wire[0], s1);
            
            Set<Integer> s2 = graph.getOrDefault(wire[1], new HashSet<>());
            s2.add(wire[0]);
            graph.put(wire[1], s2);
        }
        
        int answer = 101;
        for(int[] wire: wires) {
            int cnt = explore(graph, wire[0], wire[1], n);
            answer = Math.min(answer, Math.abs(cnt - Math.abs(cnt-n)));
        }
        
        return answer;
    }
    
    public int explore(Map<Integer, Set<Integer>> graph, int v1, int v2, int n) {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] vis = new boolean[n+1];
        dq.offer(v1);
        vis[v1] = true;
        
        int cnt = 0;
        while(!dq.isEmpty()) {
            cnt++;
            int vertex = dq.poll();
            
            Set<Integer> set = graph.get(vertex);
            for(Integer i: set) {
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