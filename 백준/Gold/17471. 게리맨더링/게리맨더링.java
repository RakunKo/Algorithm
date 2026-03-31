import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] part = br.readLine().split(" ");
        int[] populations = new int[n];

        for(int i=0;i<n;i++) populations[i] = Integer.parseInt(part[i]);

        List<Integer>[] adj = new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj[i] = new ArrayList<>();

            String[] p = br.readLine().split(" ");
            for(int j=1;j<p.length;j++) adj[i].add(Integer.parseInt(p[j])-1);
        }

        int result = solution(n, populations, adj);

        System.out.print(result);
    }

    public static int solution(int n, int[] populations, List<Integer>[] adj) {
        int answer = Integer.MAX_VALUE;
        int total = 0;
        for(int p: populations) total += p;

        for(int i=1;i<(1<<n)-1;i++) {
            if(is_valid(i, n, adj)) {

                int sum_a = 0;
                for(int j=0;j<n;j++) if((i & (1<<j)) != 0) sum_a += populations[j];

                int sum_b = total - sum_a;
                answer = Math.min(answer, Math.abs(sum_a-sum_b));
            }
        }

        if(answer == Integer.MAX_VALUE) return -1;
        else return answer;
    }

    public static boolean is_valid(int bit, int n, List<Integer>[] adj) {
        int a = 0;
        int b = 0;

        for(int i=0;i<n;i++) {
            if((bit & (1<<i)) != 0) a = i;
            else b = i;
        }

        return is_connected(bit, a, adj, n, 1) && is_connected(bit, b, adj, n, 0);
    }

    public static boolean is_connected(int bit, int start, List<Integer>[] adj, int n, int target) {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        int cnt = 1;

        dq.offer(start);
        vis[start] = true;

        while(!dq.isEmpty()) {
            int curr = dq.poll();

            for(int next: adj[curr]) {
                if(vis[next]) continue;
                if(((bit>>next) & 1) != target) continue;

                cnt++;
                vis[next] = true;
                dq.offer(next);
            }
        }

        int total = 0;
        for(int i=0;i<n;i++) {
            if(((bit>>i) & 1) == target) total++;
        }

        return total == cnt;
    }

}