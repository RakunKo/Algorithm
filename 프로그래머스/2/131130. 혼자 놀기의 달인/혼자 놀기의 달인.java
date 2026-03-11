import java.util.*;
import java.lang.Math.*;

class Solution {
    int answer = 0;
    boolean[] vis;
        
    public int solution(int[] cards) {
        int n = cards.length;
        vis = new boolean[n+1];
            
        List<Integer>[] list = new ArrayList[n+1];
        for(int i=0;i<n;i++) list[i] = new ArrayList<>();
            
        int idx = 0;
        for(int i=0;i<n;i++) {
            if(vis[i]) continue;
            int cur = i;
            
            while(!vis[cur]) {
                vis[cur] = true;
                list[idx].add(cards[cur]);
                cur = cards[cur]-1;
            }
            
            idx++;
        }
        
        for(int i=0;i<idx;i++) {
            for(int j=i+1;j<idx;j++) {
                answer = Math.max(answer, list[i].size() * list[j].size());
            }
        }
        
        return answer;
    }
    
}