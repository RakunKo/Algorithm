import java.util.*;
import java.lang.Math.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        List<String> list = new ArrayList<>();
        for(int i=0;i<numbers.length;i++) {
            list.add(Integer.toString(numbers[i]));
        }
        list.sort((a,b) -> (b + a).compareTo(a + b));
        
        if (list.get(0).equals("0")) return "0";
        for(String s: list) answer += s;
        
        return answer;
    }
}