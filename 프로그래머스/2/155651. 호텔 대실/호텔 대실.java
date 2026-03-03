class Solution {
    public int solution(String[][] book_time) {
        int[] time = new int[2400];
        
        for(String[] book : book_time) {
            int start = toTime(book[0]);
            int end = toTime(book[1]) + 10;
            
            time[start]++;
            time[end]--;
        }
        
        int sum = 0;
        int answer = 0;
        for(int i: time) {
            sum += i;
            if(answer < sum) answer = sum;
        }
            
        return answer;
    }
    
    public int toTime(String time) {
        String[] part = time.split(":");
        return Integer.parseInt(part[0])*60 + Integer.parseInt(part[1]);
    }
}