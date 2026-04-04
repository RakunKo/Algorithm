import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Main main = new Main();
        int test = Integer.parseInt(br.readLine());

        for(int i=0;i<test;i++) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);

            int[] costs = new int[n+1];
            String[] cost = br.readLine().split(" ");
            for(int j=1;j<=n;j++) costs[j] = Integer.parseInt(cost[j-1]);

            int[] degree = new int[n+1];
            List<Integer>[] adj = new ArrayList[n+1];
            for(int j=0;j<=n;j++) adj[j] = new ArrayList<>();

            for(int j=0;j<k;j++) {
                String[] edges = br.readLine().split(" ");
                int e1 = Integer.parseInt(edges[0]);
                int e2 = Integer.parseInt(edges[1]);

                adj[e1].add(e2);
                degree[e2]++;
            }

            int target = Integer.parseInt(br.readLine());

            System.out.println(main.solution(n, k, costs, adj, degree, target));
        }
    }

    public int solution(int n, int k, int[] costs, List<Integer>[] adj, int[] degree, int target) {
        return find(n, costs, adj, degree, target);
    }

    public int find(int n, int[] costs, List<Integer>[] adj, int[] degree, int target) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] max_time = new int[n+1];
        for(int i=1;i<=n;i++) if(degree[i] == 0) {
            max_time[i] = costs[i];
            dq.offer(i);
        }

        while(!dq.isEmpty()) {
            int curr = dq.poll();

            for(int next: adj[curr]) {
                max_time[next] = Math.max(max_time[next], max_time[curr] + costs[next]);
                if(--degree[next] == 0) dq.offer(next);
            }
        }

        return max_time[target];
    }
}
