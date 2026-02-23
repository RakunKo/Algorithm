class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String target = toK(n, k);
        int start = 0;
        int end = 0;
        
        while(end <= target.length()) {
            if (end == target.length() || target.charAt(end) == '0') {
                if (start < end && isPrime(target.substring(start, end))) answer++; 
                start = end + 1;
            }
            end++;
        }
        
        return answer;
    }
    
    public boolean isPrime(String str) {
        long num = Long.parseLong(str);
        if(num == 2) return true;
        if(num == 1) return false;
        
        for(long i= 2;i * i <=num;i++) if(num % i == 0) return false;
        return true;
    }
    
    public String toK(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while(n > 0) {
            int r = n % k;
            sb.append(r);
            n /= k;
        }
        
        return sb.reverse().toString();
    }
}