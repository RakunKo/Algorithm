import java.lang.Math.*;
import java.util.*;

class Solution {
    int answer = 16000001;
    
    public int solution(int[][] cost, int[][] hint) {
        int n = cost.length;
        int k = hint[0].length;
        
        dfs(0, cost, hint, n, k, 0, new int[n]);
        return answer;
    }
    
    public void dfs(int d, int[][] cost, int[][] hint, int n, int k, int total, int[] h) {
        if(total > answer) return;
        if(d == n) {
            answer = Math.min(answer, total);
            return;
        }
        
        int currentHint = Math.min(h[d], n-1);
        int currentCost = cost[d][currentHint];
        
        //힌트비구매
        dfs(d+1, cost, hint, n, k, total + currentCost, h);
        
        // 힌트구매
        if(d < n-1) {
            for(int i=1;i<hint[d].length;i++) h[hint[d][i]-1]++;
            dfs(d+1, cost, hint, n, k, total + currentCost + hint[d][0], h);
            for(int i=1;i<hint[d].length;i++) h[hint[d][i]-1]--;
        }
    }
}