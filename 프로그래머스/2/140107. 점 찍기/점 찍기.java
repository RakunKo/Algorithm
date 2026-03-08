import java.lang.Math.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        if(k > d) return 1;
        int max_v = d / k;
        
        for(int i=0;i<=max_v;i++) {
            int a = i*k;
            long diff = (long)Math.sqrt((long)d*d - (long)a*a);
            if(diff >= 0) answer+=diff/k+1;
        }
        
        return answer;
    }
}