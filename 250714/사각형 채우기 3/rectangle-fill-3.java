import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        //n=1 일때 2
        //n=2 일때 7 -> 2*2 + 3 
        //n=3 22 -> 7*2 + 2*3 +   //16/6
        //d[k] = d[k-1] * 7 + d[k-2] * 2 + d[k-3] * 2
        // d[k-1] * [1] -> 겹치는거 없음
        // d[k-2] * (d[2]) -> d[k-1] * [1]와 겹치는거 존재
        // ㅡ가 아닌 |와 1x1이 겹침.
        long[] dp = new long[n+1];
        if(n==1) {
            System.out.print(2);
            return;
        }
        dp[0] = 1; dp[1] = 2;
        for(int i=2;i<=n;i++) {
            dp[i] = (dp[i-1]*2 + dp[i-2]*3) % 1000000007;
            for(int j=i-3;j>=0;j--)
                dp[i] = (dp[i] + dp[j]*2) % 1000000007;
        }

        System.out.print(dp[n]);
    }
}