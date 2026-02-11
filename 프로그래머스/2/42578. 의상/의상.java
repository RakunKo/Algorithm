import java.util.*;
import java.lang.Math.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        int item = clothes.length;
        
        HashMap<String, Integer> hm = new HashMap<>();
        
        for(String[] cloth: clothes) hm.put(cloth[1], hm.getOrDefault(cloth[1] , 0)+1);
        
        for (Integer value : hm.values()) {
            if(item - value == 0) return item;
            answer *= (value + 1);
        }
        
        // head - 2  -> 3
        // eye - 1 -> 2
        
        
        return answer-1;
    }
}