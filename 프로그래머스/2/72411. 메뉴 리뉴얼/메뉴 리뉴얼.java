import java.util.*;

class Solution {
    Map<String, Integer> answer = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        
        for(int i=0;i<orders.length;i++) {
            for(int c: course) {
                List<Character> temp = new ArrayList<>();
                
                for(char t : orders[i].toCharArray()){
                    temp.add(t);
                }
                
                Collections.sort(temp);
                
                dfs(0, "", temp, 0, c);
            }
        }
        
        List<String> list = new ArrayList<>();
        for(int c: course) {
            List<String> temp = new ArrayList<>();
            int max = 0;
            
            for(String key: answer.keySet()) {
                if(key.length() == c) {
                    int cnt = answer.get(key);
                    if(cnt >= 2 && max <= cnt) {
                        if(max<cnt) temp.clear();
                        max = cnt;
                        temp.add(key);
                    }        
                }
            }
            
            list.addAll(temp);
        }
        
        Collections.sort(list);
        
        return list.stream().toArray(String[]::new);
    }
    
    public void dfs(int len, String comb, List<Character> order, int idx, int target) {
        if(len == target) {
            answer.put(comb, answer.getOrDefault(comb, 0) + 1);
            return;
        }
        
        for(int i=idx;i<order.size();i++) {
            dfs(len+1, comb+order.get(i), order, i+1, target);
        }
    }
}