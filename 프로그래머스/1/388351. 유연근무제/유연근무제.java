class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for(int i=0;i<schedules.length;i++) {
            if(isValid(schedules[i], timelogs[i], startday)) answer++;
        }
        
        return answer;
    }
    
    public boolean isValid(int schedule, int[] timelog, int startday) {
        int limitHour = schedule / 100;
        int limitMin = schedule % 100 + 10;
        
        if(limitMin >= 60) {
            limitMin -= 60;
            limitHour += 1;
        }
        
        for(int time: timelog) {
            if(startday == 6 || startday == 7) {
                startday = (startday+1) % 8;
                if(startday == 0) startday = 1;
                continue;
            }
            
            int hour = time / 100;
            int min = time % 100;
            
            if(limitHour < hour) return false;
            else if(limitHour == hour && limitMin < min) return false;  
            
            startday = (startday+1) % 8;
            if(startday == 0) startday = 1;
        }
        
        return true;
    }
    
    
    
}