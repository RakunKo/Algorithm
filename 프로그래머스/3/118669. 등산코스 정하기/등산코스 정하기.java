import java.util.*;
import java.lang.Math.*;

class Solution {
    class Vertex {
        int v; int w;
        public Vertex(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    
    Set<Integer> summit;
    List<Vertex>[] adj;
    int[] dist;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        adj = new ArrayList[n+1];
        for(int i=1;i<=n;i++) adj[i] = new ArrayList<>();
        
        for(int[] path: paths) {
            adj[path[0]].add(new Vertex(path[1], path[2]));
            adj[path[1]].add(new Vertex(path[0], path[2]));
        }
        
        Arrays.sort(summits);
        summit = new HashSet<>();
        for(int s: summits) summit.add(s);
        
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        bfs(gates);
        
        //for(int i=1;i<=n;i++) System.out.print(dist[i] + " ");
        //System.out.println(" ");
        
        int[] answer = new int[2];
        int min = Integer.MAX_VALUE;
        for(int s: summits) {
            if(min > dist[s]) {
                answer[0] = s;
                answer[1] = dist[s];
                min = dist[s];
            }
        }
        
        return answer;
    }
    
    public void bfs(int[] gates) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        for(int g: gates) {
            pq.offer(new Vertex(g, 0));
            dist[g] = 0;
        }
        
        while(!pq.isEmpty()) {
            Vertex curr = pq.poll();
            
            if(dist[curr.v] < curr.w) continue;
            if(summit.contains(curr.v)) continue;
            
            for(Vertex next: adj[curr.v]) {
                int intensity = Math.max(dist[curr.v], next.w);
                if(dist[next.v] <= intensity) continue;
                
                dist[next.v] = intensity;
                pq.offer(new Vertex(next.v, intensity));
            }
        }
    }
}