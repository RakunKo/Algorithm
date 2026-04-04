import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Main main = new Main();
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<int[]>[] adj = new ArrayList[n+1];
        for(int i=0;i<=n;i++) adj[i] = new ArrayList<>();

        int[] degree = new int[n+1];

        for(int i=0;i<m;i++) {
            String[] p = br.readLine().split(" ");
            int u = Integer.parseInt(p[0]);
            int v = Integer.parseInt(p[1]);
            int w = Integer.parseInt(p[2]);

            adj[u].add(new int[]{v, w});
            degree[v]++;
        }

        String[] part = br.readLine().split(" ");

        int[] result = main.solution(n, m, adj, degree, Integer.parseInt(part[0]), Integer.parseInt(part[1]));

        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public int[] solution(int n, int m, List<int[]>[] adj, int[] degree, int start, int end) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] dist = new int[n+1];

        List<Integer>[] prev = new ArrayList[n+1];
        for(int i=0;i<=n;i++) prev[i] = new ArrayList<>();

        dq.offer(start);

        while(!dq.isEmpty()) {
            int curr = dq.poll();

            for(int[] next: adj[curr]) {
                if(dist[next[0]] < dist[curr] + next[1]) {
                    dist[next[0]] = dist[curr] + next[1];
                    prev[next[0]].clear();
                    prev[next[0]].add(curr);
                }else if(dist[next[0]] == dist[curr] + next[1]) prev[next[0]].add(curr);

                if(--degree[next[0]] == 0) dq.offer(next[0]);
            }
        }

        int cnt = bfs(end, prev, n);

        return new int[]{dist[n], cnt};
    }

    public int bfs(int end, List<Integer>[] prev, int n) {
        Deque<Integer> dq = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        boolean[] visited = new boolean[n+1];

        dq.offer(end);
        visited[end] = true;

        while(!dq.isEmpty()) {
            int curr = dq.poll();

            for(int next: prev[curr]) {
                set.add(next * 100_001 + curr);
                
                if(!visited[next]) {
                    visited[next] = true;
                    dq.offer(next);
                }
            }
        }

        return set.size();
    }
}
