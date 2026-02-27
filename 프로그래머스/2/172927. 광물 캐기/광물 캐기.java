import java.util.*;
import java.lang.Math.*;

class Solution {
    int answer = 100000001;
    Map<Integer, Map<String, Integer>> fatigue;
    
    public int solution(int[] picks, String[] minerals) {
        int n = minerals.length;
        
        fatigue = new HashMap<>();
        fatigue.put(0, Map.of("diamond", 1, "iron", 1, "stone", 1));
        fatigue.put(1, Map.of("diamond", 5, "iron", 1, "stone", 1));
        fatigue.put(2, Map.of("diamond", 25, "iron", 5, "stone", 1));
        
        Deque<Integer> pick = new ArrayDeque<>();
        for(int i=0;i<picks.length;i++) {
            for(int j=0;j<picks[i];j++) pick.offer(i);
        }
        
        dfs(picks, minerals, 0, n, 0);
        
        return answer;
    }
    
    public void dfs(int[] picks, String[] minerals, int idx, int n, int total) {
        if(total > answer) return;
        
        //System.out.println(picks[0] + " " + picks[1] + " "+ picks[2] + " "+total + " "+ idx);
        if(idx >= n || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            answer = Math.min(answer, total);
            return;
        }
        
        int limit = idx + 5;
        if(limit > n) limit = n;
        
        for(int i=0;i<picks.length;i++) {
            if(picks[i] != 0) {
                int cnt = 0;
                int start = idx;
                Map<String, Integer> f = fatigue.get(i);
                for(;start<limit;start++) cnt += f.get(minerals[start]);
                picks[i]--;
                dfs(picks, minerals, start, n, cnt+total);
                picks[i]++;
            }
        }
    }
}