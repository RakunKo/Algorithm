import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] p = br.readLine().split(" ");
        int n = Integer.parseInt(p[0]);
        int l = Integer.parseInt(p[1]);

        int[][] maps = new int[n][n];
        for(int i=0;i<n;i++) {
            String[] part = br.readLine().split(" ");
            for(int j=0;j<n;j++) maps[i][j] = Integer.parseInt(part[j]);
        }

        Main main = new Main();
        long answer = main.solution(n, l, maps);

        System.out.print(answer);
    }

    public int solution(int n, int l, int[][] maps) {
        int answer = 0;

        for(int i=0;i<n;i++) if(is_valid(n, l, maps[i])) answer++;
        for(int i=0;i<n;i++) {
            int[] line = new int[n];
            for(int j=0;j<n;j++) line[j] = maps[j][i];

            if(is_valid(n, l, line)) answer++;
        }

        return answer;
    }

    public boolean is_valid(int n, int l, int[] line) {
        boolean[] vis = new boolean[n];

        for(int i=0;i<n-1;i++) {
            int diff = line[i] - line[i+1];

            if(diff == 1) {
                // 감소
                if(i+l >= n) return false;

                int std = line[i+1];
                for(int j=1;j<=l;j++) if(std != line[i+j] || vis[i+j]) return false;
                for(int j=1;j<=l;j++) vis[i+j] = true;

                i += l-1;

            } else if (diff == -1) {
                //증가
                if(i-l+1 < 0) return false;

                int std = line[i];
                for(int j=0;j<l;j++) if(std != line[i-j] || vis[i-j]) return false;
                for(int j=0;j<l;j++) vis[i-j] = true;

            } else if (diff == 0) continue;
            else return false;
        }


        return true;
    }
}
