import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] part = br.readLine().split(" ");
        int n = Integer.parseInt(part[0]);
        int m = Integer.parseInt(part[1]);
        int k = Integer.parseInt(part[2]);

        List<Node>[] adj = new ArrayList[n+1];
        for(int i=0;i<=n;i++) adj[i] = new ArrayList<>();

        for(int i=0;i<m;i++) {
            String[] p = br.readLine().split(" ");
            int v1 = Integer.parseInt(p[0]);
            int v2 = Integer.parseInt(p[1]);
            int weight = Integer.parseInt(p[2]);

            adj[v1].add(new Node(v2, weight, 0));
            adj[v2].add(new Node(v1, weight, 0));
        }

        Main main = new Main();
        long result = main.solution(n, m, k, adj);

        System.out.print(result);
    }

    static class Node {
        int v; long w; int cnt;
        public Node(int v, long w, int cnt) {
            this.v = v;
            this.w = w;
            this.cnt = cnt;
        }
    }

    public long solution(int n, int m, int k, List<Node>[] adj) {
        long answer = Long.MAX_VALUE;

        long[][] result = dijkstra(adj, n, k);
        for(int i=0;i<=k;i++) answer = Math.min(answer, result[n][i]);

        return answer;
    }

    public long[][] dijkstra(List<Node>[] adj, int n, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Long.compare(a.w, b.w));
        long[][] dist = new long[n+1][k+1];
        for(int i=0;i<=n;i++)
            for(int j=0;j<=k;j++) dist[i][j] = Long.MAX_VALUE;

        pq.offer(new Node(1, 0, 0));
        dist[1][0] = 0;

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(dist[curr.v][curr.cnt] < curr.w) continue;

            for(Node next: adj[curr.v]) {
                long weight = curr.w + next.w;

                if(dist[next.v][curr.cnt] > weight) {
                    pq.offer(new Node(next.v, weight, curr.cnt));
                    dist[next.v][curr.cnt] = weight;
                }

                if(curr.cnt+1 > k) continue;

                if(dist[next.v][curr.cnt+1] > curr.w) {
                    pq.offer(new Node(next.v, curr.w, curr.cnt+1));
                    dist[next.v][curr.cnt+1] = curr.w;
                }
            }
        }

        return dist;
    }

}