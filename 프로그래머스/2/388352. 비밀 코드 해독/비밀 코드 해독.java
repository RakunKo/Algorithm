import java.util.*;

class Solution {
    List<Set<Integer>> ls;
    boolean[] vis;
    int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        ls = new ArrayList<>();
        vis = new boolean[n+1];
        
        for(int i=0;i<q.length;i++) {
            Set<Integer> set = new HashSet<>();
            for(int j=0;j<q[i].length;j++) set.add(q[i][j]);
            ls.add(set);
        }
        
        selectNumber(0, n, ans, new ArrayList<>(), 0);
        
        return answer;
    }
    
    public void selectNumber(int d, int n, int[] ans, List<Integer> arr, int index) {
        if(d == 5) {
            for(int i=0;i<ans.length;i++) {
                Set<Integer> set = ls.get(i);
                int target = ans[i];
                
                int cnt = 0;
                for(Integer num: arr) {
                    if(set.contains(num)) cnt++;
                }
                
                if(cnt != target) return;
            }
            answer++;
            return;
        }
        
        for(int i=index+1;i<=n;i++) {
            if(vis[i]) continue;
            arr.add(i);
            vis[i] = true;
            selectNumber(d+1, n, ans, arr, i);
            vis[i] = false;
            arr.remove(arr.size() - 1);
        }
    }
    
}