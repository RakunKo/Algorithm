class Solution {
    
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int n = diffs.length;
        
        int min = 1;
        int max = min;
        for(int i=0;i<n;i++) if(max < diffs[i]) max = diffs[i];
        
        while(min <= max) {
            int level = (min + max) / 2;
            
            long finish = 0;
            for(int i=0;i<n;i++) {
                int prev = 0;
                if(i != 0) prev = times[i-1];

                finish += calculate(diffs[i], level, times[i], prev);
            }
            
            if(finish <= limit) {
                answer = level;
                max = level - 1;
            }else {
                min = level + 1;
            }
        }
        
        return answer;
    }
    
    public int calculate(int diff, int level, int timeCur, int timePrev) {
        if(diff <= level) return timeCur;
        else return (diff-level)*(timeCur+timePrev) + timeCur;
    }
}