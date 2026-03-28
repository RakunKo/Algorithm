import java.lang.Math.*;

class Solution {
    int MAX = 20;
    
    public int[] solution(int target) {
        int[][] dp = new int[target+1][2];
        
        for(int i=0;i<=target;i++) dp[i][0] = Integer.MAX_VALUE;
    
        dp[0][0] = 0;
        dp[0][1] = 0;
        
        for(int i=0;i<=target;i++) {
            if(dp[i][0] == Integer.MAX_VALUE) continue;
            
            update(dp, i+50, dp[i][0] + 1, dp[i][1]+1, target);
            
            for(int s=1; s<=MAX; s++) {
                update(dp, i+s, dp[i][0] + 1, dp[i][1] + 1, target);
                update(dp, i+2*s, dp[i][0] + 1, dp[i][1], target);
                update(dp, i+3*s, dp[i][0] + 1, dp[i][1], target);
            }
        }

        return dp[target];
    }
    
    public void update(int[][] dp, int cnt, int dart, int bull_single, int target) {
        if(cnt > target) return;
        
        if(dp[cnt][0] > dart) {
            dp[cnt][0] = dart;
            dp[cnt][1] = bull_single;
        }else if(dp[cnt][0] == dart){
            dp[cnt][1] = Math.max(dp[cnt][1], bull_single);
        }
    }
}