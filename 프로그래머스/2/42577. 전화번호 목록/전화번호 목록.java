import java.util.*;

class Solution {
    HashSet<String> hs = new HashSet<>();
    
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        // O(N)
        for(String str: phone_book) hs.add(str);
        
        // O(N) * 20(max)
        for(String str: phone_book) {
            for(int i=0;i<str.length();i++) {
                if(hs.contains(str.substring(0, i))) return false;
            }
        }
        
        return answer;
    }
}