import java.util.*;
import java.lang.Math.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        
        int hIndex = 0;
        for(int i=0;i<n;i++) {
            int h = n - i;
            
            // 인용 횟수가 논문 편수(h)보다 크거나 같은 지점
            if(citations[i] >= h) {
                hIndex = h;
                break;
            }
        }
        
        return hIndex;
    }
}