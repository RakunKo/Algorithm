class Solution {
    int MAX = 40001;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int answer = 0;
        int n = to_second(play_time);
        int adv = to_second(adv_time);
        
        long[] cnt = new long[n+1];
        
        for(String log: logs) {
            String[] times = log.split("-");
            int start = to_second(times[0]);
            int end = to_second(times[1]);
            
            cnt[start] += 1;
            cnt[end] -= 1;
        }
        
        for(int i=1;i<=n;i++) cnt[i] += cnt[i-1];
        
        long sum = 0;
        for(int i=0;i<adv;i++) sum += cnt[i]; 
        
        long max = 0;
        for(int i=adv;i<=n;i++) {
            if(max < sum) {
                max = sum;
                answer = i-adv;
            }
            
            sum -= cnt[i-adv];
            sum += cnt[i];
        }
        
        return to_hour(answer);
    }
    
    public int to_second(String time) {
        String[] part = time.split(":");
        return Integer.parseInt(part[0]) * 60 * 60 + Integer.parseInt(part[1]) * 60 + Integer.parseInt(part[2]);
    }
    
    public String to_hour(int second) {
        int hour = second / 3600;
        second = second % 3600;
        int min = second / 60;
        int sec = second % 60;
        
        return String.format("%02d:%02d:%02d", hour, min, sec);   
    }
    //36000 + 3600 + 60
    // 39660
}