class Solution {
    long MAX = 1000000000;
    
    public long solution(int n, int[] times) {
        
        long start = 0; long end = MAX*MAX;
        while(start < end) {
            long mid = start + (end-start)/2;
            long complete = 0;
            
            for(int time: times) complete += mid/time;
            
            if(complete < n) start = mid+1;
            else end = mid;
        }
        
        return start;
    }
}