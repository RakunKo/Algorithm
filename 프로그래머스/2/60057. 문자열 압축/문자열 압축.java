import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        int n = s.length();
        
        if(n==1) return 1;
        
        for(int i=1;i<=n/2;i++) {
            String last = s.substring(0, i);
            int cnt = 1;
            int total = 0;
            int cur=i;
            
            for(;cur<=n-i;cur+=i) {
                String str = s.substring(cur, cur+i);
                if(str.equals(last)) cnt++;
                else {
                    if(cnt == 1) total += last.length();
                    else total += String.valueOf(cnt).length() + last.length();
                    
                    cnt = 1;
                    last = str;
                }
            }
            
            total += (cnt == 1) ? last.length() : (String.valueOf(cnt).length() + last.length());
            total += n - cur;
            
            if(answer > total) answer = total;
        }
        
        return answer;
    }
}