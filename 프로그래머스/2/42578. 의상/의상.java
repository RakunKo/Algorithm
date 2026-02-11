import java.util.*;
import java.lang.Math.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> hm = new HashMap<>();
        
        for(String[] cloth: clothes) hm.put(cloth[1], hm.getOrDefault(cloth[1] , 0)+1);
        
        for (Integer value : hm.values()) answer *= (value + 1);
        
        return answer-1;
    }
}