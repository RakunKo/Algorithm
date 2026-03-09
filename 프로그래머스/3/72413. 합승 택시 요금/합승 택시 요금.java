import java.util.*;
import java.lang.Math.*;

class Solution {
    class Node {
        int v; int w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    
    List<Node>[] adj;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        adj = new ArrayList[n+1];
        for(int i=1;i<=n;i++) adj[i] = new ArrayList<>();
        
        for(int[] fare: fares) {
            adj[fare[0]].add(new Node(fare[1], fare[2]));
            adj[fare[1]].add(new Node(fare[0], fare[2]));
        }
    
        int[] acc_dist = accompany(s, a, b, n);
        
        for(int i=1;i<=n;i++) {
            if(acc_dist[i] == Integer.MAX_VALUE) continue;
            if(acc_dist[i] >= answer) continue;
            int[] home_dist = gohome(i, a, b, n);
            
            //for(int j=1;j<=n;j++) System.out.print(home_dist[j] + " ");
            //System.out.print("\n");
            
            answer = Math.min(answer, home_dist[a] + home_dist[b] + acc_dist[i]);
        }
        
        return answer;
    }
    
    public int[] accompany(int s, int a, int b, int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a_v,b_v)->a_v.w-b_v.w);
        pq.offer(new Node(s, 0));
        dist[s] = 0;
        
        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            
            for(Node next: adj[curr.v]) {
                if(dist[next.v] <= curr.w + next.w) continue;
                
                dist[next.v] = curr.w + next.w;
                pq.offer(new Node(next.v, dist[next.v]));
            }
        }
        
        return dist;
    }
    
    public int[] gohome(int s, int a, int b, int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a_v,b_v)->a_v.w-b_v.w);
        pq.offer(new Node(s, 0));
        dist[s] = 0;
        
        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            
            if(dist[curr.v] < curr.w) continue;
            
            for(Node next: adj[curr.v]) {
                if(dist[next.v] <= curr.w + next.w) continue;
                
                dist[next.v] = curr.w + next.w;
                pq.offer(new Node(next.v, dist[next.v]));
            }
        }
        
        return dist;
    }
}