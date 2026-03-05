class Solution {
    public int solution(int[][] signals) {
        int answer = 0;
        
        int max = 1;
        for(int[] signal: signals) {
            max *= (signal[0]+signal[1]+signal[2]);
        }
        
        while(answer <= max) {
            boolean flag = true;
            for(int[] signal: signals) {
                flag = flag && isYellow(answer, signal);
            }
            
            if(flag) return answer;
            answer++;
        }
        
        return -1;
    }
    
    public boolean isYellow(int sec, int[] signal) {
        sec = sec % (signal[0]+signal[1]+signal[2]);
        
        if(sec >= signal[0]+1 && sec <= signal[0]+signal[1]) return true;
        else return false;
    }
}