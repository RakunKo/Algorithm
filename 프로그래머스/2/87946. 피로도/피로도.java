import java.lang.Math.*;

class Solution {
    int[] vis;
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        vis = new int[dungeons.length];
        explore(0, dungeons, k);
        
        return answer;
    }
    
    public void explore(int d, int[][] dungeons, int k) {
        answer = Math.max(answer, d);
        if(d == dungeons.length) return;
        
        for(int i=0;i<dungeons.length;i++) {
            if(k < dungeons[i][0] || vis[i] == 1) continue;
            vis[i] = 1;
            explore(d+1, dungeons, k - dungeons[i][1]);
            vis[i] = 0;
        }
    }
}