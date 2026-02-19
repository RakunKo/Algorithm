class Solution {
    public String solution(String number, int k) {
        String answer = "";
        int L = number.length();
        int target = L - k;
        char[] arr = new char[L-k];
        
        int idx = 0;
        for(int i=0;i<L;i++) {
            char n = number.charAt(i);
            
            while(idx > 0 && arr[idx - 1] < n && k > 0) {
                idx--;
                k--;
            }
            
            if(idx < target) arr[idx++] = n;
            else k--;
        }
                
        return new String(arr);
    }
}