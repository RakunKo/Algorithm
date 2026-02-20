import java.util.*;
import java.lang.Math.*;

class Solution {
    class Node implements Comparable<Node>{
        int to;
        int weight; 

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        List<Node>[] adj = new ArrayList[n];        
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        
        for(int[] cost: costs) {
            adj[cost[0]].add(new Node(cost[1], cost[2]));
            adj[cost[1]].add(new Node(cost[0], cost[2]));
        }
        
        boolean[] visited = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        pq.offer(new Node(0, 0));

        int edgesFound = 0;
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            
            if (visited[current.to]) continue;

            visited[current.to] = true;
            answer += current.weight;
            edgesFound++;

            if (edgesFound == n) break; 

            for (Node next : adj[current.to]) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }
        
        return answer;
    }
}