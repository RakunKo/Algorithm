import java.util.*;
import java.lang.Math.*;

class Solution {
    public String solution(long n, String[] bans) {
        String answer = "";
        
        Arrays.sort(bans, (a,b) -> {
            if(a.length() == b.length()) return a.compareTo(b);
            else return a.length() - b.length();
        });
        
        int[] idx = new int[bans.length];
        for(int i=0;i<bans.length;i++) {
            long val = 0;
            for(char c: bans[i].toCharArray()) val = val * 26 + (c - 'a' + 1);
            if(val <= n) n++;
        }
        
        // n번째 주문의 자릿수 확인
        long sum = 0;
        long pow = 1;
        int digit = 0;
        while(sum <= n) {
            digit++;
            pow *= 26;
            sum += pow;
        }
        
        // n번째 주문 구하기
        long r = n - (sum - pow);
        while(digit-- > 0) {
            pow /= 26;
            long d = (r - 1) / pow;
            char c = (char)('a' + d);
            answer += c;
            r = (r - 1) % pow + 1;
        }
        
        return answer;
    }
}