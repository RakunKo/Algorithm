class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = toSec(video_len);
        int opStart = toSec(op_start);
        int opEnd = toSec(op_end);
        int curr = toSec(pos);
        
        for(String command: commands) {
            if(curr <= opEnd && curr >= opStart) curr = opEnd;
            if(command.equals("prev")) {
                if(curr - 10 <= 0) curr = 0;
                else curr -= 10;
            }else {
                if(curr + 10 >= videoLen) curr = videoLen;
                else curr += 10;
            }
        }
        if(curr <= opEnd && curr >= opStart) curr = opEnd;

        return toMin(curr);
    }
    
    public int toSec(String time) {
        String[] str = time.split(":");
        return Integer.parseInt(str[0])*60 + Integer.parseInt(str[1]);
    }
    
    public String toMin(int sec) {
        int m = sec / 60;
        int s = sec % 60;
        return String.format("%02d:%02d", m, s);
    }
}