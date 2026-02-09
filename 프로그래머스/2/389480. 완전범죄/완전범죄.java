import java.lang.Math.*;
import java.util.*;

class Solution {

    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        int L = info.length;
        
        int[][] dp = new int[L+1][n];
        
        for(int i=0;i<=L;i++) Arrays.fill(dp[i], m);
        dp[0][0] = 0;
        
        for(int i=0;i<L;i++) {
            // i번째 물건 훔칠 때 j(A 흔적)
            for(int j=0;j<n;j++) {
                if(dp[i][j] == m) continue;
                
                if(j + info[i][0] < n) {
                    dp[i+1][j + info[i][0]] = Math.min(dp[i+1][j + info[i][0]], dp[i][j]);
                }
                
                if(dp[i][j] + info[i][1] < m) {
                    dp[i+1][j] = Math.min(dp[i+1][j] ,dp[i][j] + info[i][1]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dp[L][i] < m) return i;
        }
        return -1;
    }
}


// import java.lang.Math.*; 
// import java.util.*; 
//  
// class Solution { 
//     int answer = -1; 
//     int[][] vis; 
//  
//     public int solution(int[][] info, int n, int m) { 
//         vis = new int[info.length + 1][n]; 
//         for (int i = 0; i <= info.length; i++) { 
//             Arrays.fill(vis[i], m); 
//         } 
//          
//         dfs(0, info, n, m, 0, 0); 
//         return answer; 
//     } 
//  
//     public void dfs(int depth, int[][] info, int n, int m, int a, int b) { 
//         //더이상 진행하지 않음 
//         if(a >= n || b >= m) return; 
//         if(answer != -1 && a >= answer) return; 
//         if(vis[depth][a] <= b) return; 
//         vis[depth][a] = b; 
//  
//         if(depth == info.length) { 
//             if(answer == -1) answer = a; 
//             else answer = Math.min(answer, a); 
//             return; 
//         } 
//  
//         dfs(depth+1, info, n, m, a+info[depth][0], b); 
//         dfs(depth+1, info, n, m, a, b+info[depth][1]); 
//     } 
// } 