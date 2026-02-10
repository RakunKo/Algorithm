import java.util.*;

class Solution {
    HashMap<Integer, Integer> hm = new HashMap<>();
    
    public int solution(int[] nums) {
        int answer = 0;
        int size = nums.length;
        
        for(int num: nums) hm.put(num, hm.getOrDefault(num, 0) + 1);
        
        if(size/2 >= hm.size()) answer = hm.size();
        else answer = size/2;
        
        return answer;
    }
}