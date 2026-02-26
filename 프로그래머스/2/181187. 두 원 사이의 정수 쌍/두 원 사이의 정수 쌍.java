import java.lang.Math.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        long cnt = 0;
        for(int i=1;i<=r2;i++) {
            long max = (long) Math.sqrt((long)r2*r2 - (long)i*i);
            long min = (long) Math.ceil(Math.sqrt((long)r1*r1 - (long)i*i));
            
            if(min < 0) cnt += max - 0 + 1;
            else cnt += max - min + 1;
        }
        
        return cnt * 4;
    }
    
    // 1 -> 5
    // 2 -> 5 + 2*4 = 13
    // 3 -> 13 + 4*4 = 29
    // 4 -> 29 + 5*4 = 49
}