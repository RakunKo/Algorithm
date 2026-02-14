import java.lang.Math.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;
        
        for(int[] size: sizes) {
            // 일반
            int commonWidth = Math.max(maxWidth, size[0]);
            int commonHeight = Math.max(maxHeight, size[1]);
            
            // 눕힌 수납
            int slideWidth = Math.max(maxWidth, size[1]);
            int slideHeight = Math.max(maxHeight, size[0]);
            
            if(commonWidth * commonHeight < slideWidth * slideHeight) {
                maxWidth = commonWidth;
                maxHeight = commonHeight;
            } else {
                maxWidth = slideWidth;
                maxHeight = slideHeight;
            }
            
        }
        
        return maxWidth * maxHeight;
    }
}