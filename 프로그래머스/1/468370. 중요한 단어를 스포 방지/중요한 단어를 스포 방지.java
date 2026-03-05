import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        int n = message.length();
        
        boolean[] isSpoiler = new boolean[n];
        for(int[] range: spoiler_ranges) {
            for(int i=range[0];i<=range[1];i++) isSpoiler[i] = true;
        }
        
        Set<String> notSpoilerWord = new HashSet<>();
        
        String[] words = message.split(" ");
        int cur = 0;
        for(String word: words) {
            boolean flag = true;
            
            int l = cur + word.length();
            for(;cur<l;cur++) {
                if(isSpoiler[cur]) flag = false;
            }
            
            cur++;
            if(flag) notSpoilerWord.add(word);
        }
        
        cur = 0;
        for(String word: words) {
            boolean flag = true;
            
            int l = cur + word.length();
            for(;cur<l;cur++) {
                if(isSpoiler[cur]) flag = false;
            }
            
            cur++;
            
            if(!flag && !notSpoilerWord.contains(word)) {
                answer++; 
                notSpoilerWord.add(word);
            }
        }
        
        
        return answer;
    }
}