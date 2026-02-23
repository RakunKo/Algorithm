import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String, Integer> status = new HashMap<>();
        Map<String, Set<String>> users = new HashMap<>();
        Map<String, Integer> idx = new HashMap<>();
        
        for(int i=0;i<id_list.length;i++) idx.put(id_list[i], i);
        
        Set<String> ban = new HashSet<>();
        for(String r: report) {
            String[] user = r.split(" ");
            if(!users.getOrDefault(user[0], new HashSet<>()).contains(user[1])) {
                status.put(user[1], status.getOrDefault(user[1], 0) + 1);
            }
            
            Set<String> set = users.getOrDefault(user[0], new HashSet<>());
            set.add(user[1]);
            users.put(user[0], set);
            
            if(status.get(user[1]) >= k) ban.add(user[1]);
        }
        
        for(Map.Entry<String, Set<String>> e: users.entrySet()) {
            String user = e.getKey();
            for(String u: e.getValue()) {
                if(ban.contains(u)) answer[idx.get(user)]++;
            }
        }
        
        return answer;
    }
}