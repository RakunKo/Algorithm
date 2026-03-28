import java.util.*;

class Solution {
    long MAX = 10000 * 10000;
    
    public int solution(int n, int[] cores) {
        int answer = 0;
        int core = cores.length;
        
        // -- exit
        if(core >= n) return n;
        n -= core;
        
        long left = 0;
        long right = MAX;
        while(left < right) {
            long mid = left + (right - left) / 2;
            
            long done = 0;
            for(int i=0;i<core;i++) done += mid/cores[i];
            
            if(done < n) left = mid + 1;
            else right = mid;
        }
        
        long remain = n;
        for(int i=0;i<core;i++) remain -= (left-1)/cores[i];
        //System.out.println(remain);
        
        for(int i=0;i<core;i++) {
            if(left % cores[i] == 0) remain--;
            
            if(remain == 0) {
                answer = i+1;
                break;
            }
        }
        
        return answer;
    }
}