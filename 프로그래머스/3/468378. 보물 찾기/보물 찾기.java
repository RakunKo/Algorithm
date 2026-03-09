import java.util.function.Function;

class Solution {
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int w = depth.length;
        int[][] dp = new int[w+2][w+2];
        int[][] bestMid = new int[w+2][w+2];
        
        for(int len = 1;len<=w;len++) {
            for(int start=1;start<=w-len+1;start++) {
                int end = start+len-1;
                int minMaxCost = Integer.MAX_VALUE;
                int best = start;
                
                for(int i=start;i<=end;i++) {
                    int currentMax = depth[i - 1] + Math.max(dp[start][i - 1], dp[i + 1][end]);
                    
                    if (currentMax <= minMaxCost) {
                        minMaxCost = currentMax;
                        best = i;
                    }
                }
                
                dp[start][end] = minMaxCost;
                bestMid[start][end] = best;
            }
        }
        
        return binary_search(1, w, bestMid, excavate);
    }
    
    public int binary_search(
        int start, 
        int end, 
        int[][] bestMid,
        Function<Integer, Integer> excavate
    ) {
        int cost = 0;
        
        while(start <= end) {
            int mid = bestMid[start][end];
            int result = excavate.apply(mid);
            
            if(result == 0) return mid;
            else if (result == -1) end = mid-1;
            else start = mid+1;
        }
        
        return 0;
    }
}