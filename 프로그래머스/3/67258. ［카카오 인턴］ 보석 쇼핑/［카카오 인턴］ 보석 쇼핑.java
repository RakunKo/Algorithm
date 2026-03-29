import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for(String gem: gems) set.add(gem);
        int kind = set.size();
        
        int start = 1; int end = 1;
        Map<String, Integer> map = new HashMap<>();
        int total = 0;
        
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        while(end <= gems.length) {
            String e = gems[end-1];
            int e_c = map.getOrDefault(e, 0);
            
            if(e_c == 0) total++;
            map.put(e, e_c+1);
            
            if(total == kind) {
                while(true) {
                    String s = gems[start-1];
                    int s_c = map.getOrDefault(s, 0);
                    
                    if(s_c >= 2) {
                        start++;
                        map.put(s, s_c-1);
                    }else break;
                }
                
                if(min > end-start) {
                    answer[0] = start;
                    answer[1] = end;
                    min = end-start;
                }
            }
            
            end++;
        }
        
        return answer;
    }
    //["X", "Y", "Y", "Z", "Z", "X", "Y", "Z"]
}