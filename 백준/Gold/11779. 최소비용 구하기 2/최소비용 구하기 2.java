import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<int[]>[] adj = new ArrayList[n+1];
        for(int i=0;i<=n;i++) adj[i] = new ArrayList<>();

        for(int i=0;i<m;i++) {
            String[] p = br.readLine().split(" ");
            int v1 = Integer.parseInt(p[0]);
            int v2 = Integer.parseInt(p[1]);
            int w = Integer.parseInt(p[2]);

            adj[v1].add(new int[]{v2, w});
        }

        String[] part = br.readLine().split(" ");
        int start = Integer.parseInt(part[0]);
        int end = Integer.parseInt(part[1]);

        Main main = new Main();
        main.solution(n, m, adj, start, end);
    }

    long[] dist;

    public void solution(int n, int m, List<int[]>[] adj, int start, int end) {
        dist = new long[n+1];
        List<Integer> result = dijkstra(adj, start, end, n);

        System.out.println(dist[end]);
        System.out.println(result.size());
        for(int i: result) System.out.print(i +" ");
    }

    public List<Integer> dijkstra(List<int[]>[] adj, int start, int end, int n) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b)->Long.compare(a[1], b[1]));
        int[] prev = new int[n+1];

        for(int i=0;i<=n;i++) {
            prev[i] = -1;
            dist[i] = Long.MAX_VALUE;
        }

        pq.offer(new long[]{start, 0});
        dist[start] = 0;

        while(!pq.isEmpty()) {
            long[] curr = pq.poll();
            int cv = (int) curr[0];
            long cw = curr[1];

            if(dist[cv] < cw) continue;

            for(int[] next: adj[cv]) {
                int nv = next[0];
                int nw = next[1];

                if(dist[nv] > cw + nw) {
                    dist[nv] = cw + nw;
                    prev[nv] = cv;
                    pq.offer(new long[]{nv, dist[nv]});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for(int i=end; i != -1; i=prev[i]) path.add(i);
        Collections.reverse(path);

        return path;
    }

}