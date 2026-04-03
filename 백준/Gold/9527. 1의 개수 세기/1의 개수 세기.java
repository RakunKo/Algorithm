import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] p = br.readLine().split(" ");
        long n = Long.parseLong(p[0]);
        long m = Long.parseLong(p[1]);

        Main main = new Main();
        long answer = main.solution(n, m);

        System.out.print(answer);
    }

    public long solution(long n, long m) {
        int max = 60;

        long[] arr = new long[max+1];

        arr[0] = 1;

        long cur = 1;
        for(int i=1;i<=max;i++) {
            arr[i] = (arr[i-1] - 1)*2 + cur + 1;
            cur *= 2;
        }

        //for(int i=1;i<=max;i++) System.out.print(arr[i] + " ");

        long a = calculate(n-1, arr);
        long b = calculate(m, arr);

        return b-a;
    }

    public long calculate(long num, long[] arr) {
        long result = 0;

        while(num > 0) {
            long pow = 1;
            int share = 0;
            while (pow * 2 <= num) {
                pow *= 2;
                share++;
            }

            long rest = num - pow;
            if(rest == 0) result += arr[share];
            else result += rest + arr[share];

            num = rest;
        }

        return result;
    }
}
