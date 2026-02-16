import java.util.*;
import java.lang.Math.*;

class Solution {
    List<Character> alpha = List.of('A', 'E', 'I', 'O', 'U');
    int MAX_SIZE = 5;
    
    public int solution(String word) {
        int answer = 0;
        char[] arr = word.toCharArray();
        for(int i=0;i<arr.length;i++) {
            int prev = back(arr[i], i);
            answer += (prev + 1);
        }
        return answer;
    }
    
    public int back(char c, int index) {
        int cnt = 0;
        int aplhaIndex = alpha.indexOf(c);
        for(int i=0;i<MAX_SIZE - index;i++) {
            cnt += (aplhaIndex * (int)Math.pow(MAX_SIZE, i));
        }
        return cnt;
    }
}