class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        
        int[] s1 = new int[n];
        int[] s2 = new int[n];
        for(int i=0;i<n;i++) {
            s1[i] = (i % 2 == 0) ? sequence[i] : -sequence[i];
            s2[i] = (i % 2 == 1) ? sequence[i] : -sequence[i];
        }
        
        long sum1 = 0;
        long max1 = s1[0];
        long sum2 = 0;
        long max2 = s2[0];
        
        for(int i=0;i<n;i++) {
            sum1 += s1[i];
            sum2 += s2[i];
            
            if(sum1 > max1) max1 = sum1;
            if(sum1 < 0) sum1 = 0;
            if(sum2 > max2) max2 = sum2;
            if(sum2 < 0) sum2 = 0;
        }
        
        if(max1 > max2) return max1;
        else return max2;
    }
}