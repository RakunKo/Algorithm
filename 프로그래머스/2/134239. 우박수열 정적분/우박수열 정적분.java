import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        
        int n = 0;
        List<Double> sum = new ArrayList<>();
        sum.add(0.0);
        long prev = k;
        while(k != 1) {
            if(k%2==0) k /= 2;
            else k = k*3 + 1;
            
            double breath = toBreath(prev, k);
            sum.add(breath + sum.get(sum.size()-1));
            
            prev = k;
            n++;
        }
        
        //System.out.println(sum);
        
        int r = ranges.length;
        double[] answer = new double[r];
        for(int i=0;i<r;i++) {
            int start = ranges[i][0];
            int end = n + ranges[i][1];
            
            if(start > end) answer[i] = -1;
            else {
                if(start >= 0) answer[i] = sum.get(end) - sum.get(start);
                else answer[i] = sum.get(end);    
            }
        }
        
        return answer;
    }
    
    public double toBreath(long a, long b) {
        return (a+b)/(double)2;
    }
}