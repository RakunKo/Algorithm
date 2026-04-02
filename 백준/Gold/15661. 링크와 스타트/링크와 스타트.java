import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] states = new int[n][n];

        for(int i=0;i<n;i++) {
            String[] part = br.readLine().split(" ");
            for(int j=0;j<n;j++) states[i][j] = Integer.parseInt(part[j]);
        }

        Main main = new Main();
        int result = main.solution(n, states);

        System.out.print(result);
    }

    public int solution(int n, int[][] states) {
        int answer = Integer.MAX_VALUE;

        for (int bit=1; bit<(1<<n); bit++) {
            List<Integer> link = new ArrayList<>();
            List<Integer> start = new ArrayList<>();

            for(int i=0;i<n;i++) {
                int std = 1<<i;
                if((bit & std) != 0) link.add(i);
                else start.add(i);
            }

            if (link.size() > n / 2) continue;

            int s1 = calculate(states, link);
            int s2 = calculate(states, start);

            //System.out.println(s1 + " " + s2);

            answer = Math.min(answer, Math.abs(s1-s2));
        }

        return answer;
    }

    public int calculate(int[][] states, List<Integer> list) {
        int state = 0;

        for(int i=0;i<list.size();i++) {
            for(int j=i+1;j<list.size();j++) state += states[list.get(i)][list.get(j)] + states[list.get(j)][list.get(i)];
        }

        return state;
    }




}