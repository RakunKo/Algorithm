import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[][] homeworks = new long[n][2];


        for(int i=0;i<n;i++) {
            String[] part = br.readLine().split(" ");
            for(int j=0;j<2;j++) homeworks[i][j] = Long.parseLong(part[j]);
        }

        Main main = new Main();

        long result = main.solution(homeworks);
        System.out.print(result);
    }

    public long solution(long[][] homeworks) {
        Arrays.sort(homeworks, (a, b) -> Long.compare(a[0], b[0]));
        PriorityQueue<Long> pq = new PriorityQueue<>();

        long answer = 0;

        for(long[] homework: homeworks) {
            long deadline = homework[0];
            long cnt = homework[1];

            pq.offer(cnt);
            if(pq.size() > deadline) pq.poll();
        }

        for(long cnt: pq) answer += cnt;

        return answer;
    }
}
