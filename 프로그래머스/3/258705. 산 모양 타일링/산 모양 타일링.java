class Solution {
    public int solution(int n, int[] tops) {
        int[] dp = new int[2*n+2];
        dp[0] = 1; dp[1] = 1;
        
        for(int i=2;i<=2*n+1;i++) {
            if(i % 2 == 0 && tops[i/2-1] == 1) dp[i] = (dp[i-2] + dp[i-1] * 2)%10007;
            else dp[i] = (dp[i-2] + dp[i-1])%10007;
        }
        
        return dp[2*n+1];
    }
}