import java.lang.Math.*;
import java.util.*;

class Solution {
    int answer = -1;
    int[][] vis;

    public int solution(int[][] info, int n, int m) {
        vis = new int[info.length + 1][n];
        for (int i = 0; i <= info.length; i++) {
            Arrays.fill(vis[i], m);
        }
        
        dfs(0, info, n, m, 0, 0);
        return answer;
    }

    public void dfs(int depth, int[][] info, int n, int m, int a, int b) {
        //더이상 진행하지 않음
        if(a >= n || b >= m) return;
        if(answer != -1 && a >= answer) return;
        if(vis[depth][a] <= b) return;
        vis[depth][a] = b;

        if(depth == info.length) {
            if(answer == -1) answer = a;
            else answer = Math.min(answer, a);
            return;
        }

        dfs(depth+1, info, n, m, a+info[depth][0], b);
        dfs(depth+1, info, n, m, a, b+info[depth][1]);   
    }
}