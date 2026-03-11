import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int n = elements.length;
        Set<Integer> set = new HashSet<>();
        
        for(int win=1;win<=n;win++) {
            int sum = 0;
            for(int i=0;i<win;i++) sum += elements[i];
            //System.out.println(sum);
            set.add(sum);
            
            for(int i=0;i<n;i++) {
                if(i < n-win) {
                    sum -= elements[i];
                    sum += elements[i+win];
                }else {
                    sum -= elements[i];
                    sum += elements[i+win-n];
                }
                //System.out.println(sum);
                set.add(sum);
            }
        }
        
        return set.size();
    }
}