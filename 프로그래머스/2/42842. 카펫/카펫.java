class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int grid = brown + yellow;
        
        for(int i=1;i<=grid/2;i++) {
            if(grid % i != 0) continue;
            
            int width = grid / i;
            // yellow가 이 안에 들어갈 수 있나 확인
            if(isPossible(width, i, yellow)) {
                answer[0] = width;
                answer[1] = i;
                break;
            }
        }
        
        return answer;
    }
    
    public boolean isPossible(int width, int height, int yellow) {
        if(height == 1 || height ==2) return false;
        if((width - 2)*(height - 2) == yellow) return true;
        
        return false;
    }
}