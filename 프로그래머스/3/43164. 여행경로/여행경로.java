import java.util.*;

class Solution {
    boolean[] vis;
    List<String> answer = new ArrayList<>();
     
    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));
        
        vis = new boolean[n+1];
        List<String> path = new ArrayList<>();
        path.add("ICN");
        dfs(0, "ICN", tickets, n, path);
        
        return answer.stream().toArray(String[]::new);
    }
    
    public void dfs(int d, String airport, String[][] tickets, int n, List<String> ap) {
        if(d == n && answer.size() == 0) {
            answer = new ArrayList<>(ap);
            return;
        }
        
        for(int i=0;i<n;i++) {
            // String 비교는 equals 사용(객체임)
            if(vis[i] || !tickets[i][0].equals(airport)) continue;
            vis[i] = true;
            ap.add(tickets[i][1]);
            dfs(d+1, tickets[i][1], tickets, n, ap);
            ap.remove(ap.size() - 1);
            vis[i] = false;
        }
    }
}