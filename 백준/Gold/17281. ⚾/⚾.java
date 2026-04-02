import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] innings = new int[n][9];

        for(int i=0;i<n;i++) {
            String[] part = br.readLine().split(" ");
            for(int j=0;j<9;j++) innings[i][j] = Integer.parseInt(part[j]);
        }

        Main main = new Main();
        int result = main.solution(n, innings);

        System.out.print(result);
    }

    boolean[] vis = new boolean[9];
    int answer = 0;

    public int solution(int n, int[][] innings) {
        dfs(1, innings, new ArrayList<>(), n);

        return answer;
    }

    public void dfs(int depth, int[][] innings, List<Integer> list, int n) {
        if(depth == 9) {
            list.add(3, 0);

            int sum = 0;
            int start = 0;
            //System.out.println(list);
            for(int i=0;i<n;i++) {
                int[] result = calculate(innings[i], list, start);
                sum += result[0];
                start = result[1];
            }
            answer = Math.max(answer, sum);

            list.remove(3);
            return;
        }

        for(int i=1;i<9;i++) {
            if(vis[i]) continue;

            vis[i] = true;
            list.add(i);
            dfs(depth+1, innings, list, n);
            vis[i]= false;
            list.remove(list.size()-1);
        }
    }

    public int[] calculate(int[] inning, List<Integer> list, int start) {
        int score = 0;
        int out = 0;
        boolean[] status = new boolean[3];

        int index = start;
        while(out < 3) {
            int batter = list.get(index);

            //System.out.println(out + " " + inning[batter]);
            if(inning[batter] == 0) out++;
            else if (inning[batter] == 4) {
                int cnt = 1;

                for(int i=0;i<3;i++) {
                    if(status[i]) {
                        cnt++;
                        status[i] = false;
                    }
                }

                score += cnt;
            } else score += move(status, inning[batter]);

            index = (index + 1) % 9;
        }

        return new int[]{score, index};
    }

    public int move(boolean[] status, int size) {
        int cnt = 0;
        boolean[] new_status = new boolean[3];

        for(int i=0;i<3;i++) {
            if(!status[i]) continue;

            int next = i + size;
            if(next >= 3) cnt++;
            else new_status[next] = true;
        }

        new_status[size-1] = true;

        for(int i=0;i<3;i++) {
            status[i] = new_status[i];
        }

        return cnt;
    }

}