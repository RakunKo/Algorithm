import java.lang.Math.*;

class Solution {
    public int solution(int n, long l, long r) {
        return (int)(f(n, r) - f(n, l-1));
    }
    
    public long f(int n, long x) {
        if(n==0) return 1;
        if(x<=0) return 0;
            
        long block = (long) Math.pow(5, n-1);
        long one = (long) Math.pow(4, n-1);
        
        long remain = x % block;
        long section = x / block;
        
        // x가 block의 배수일 때 (예: x=5, 10...)
        // 해당 섹션의 끝까지 다 채운 것으로 처리하기 위해 조정
        if (remain == 0) {
            if (section == 0) return 0; 
            section--; 
            remain = block;
        }
        
        if(section==0) return f(n-1, remain);
        else if(section==1) return one + f(n-1, remain);
        else if(section==2) return 2*one;
        else if(section==3) return 2*one + f(n-1, remain);
        else return 3*one + f(n-1, remain);
    }
    
    //11011
    //11011 11011 00000 11011 11011
    //1101111011000001101111011 1101111011000001101111011 
    
    //크게 보면 n-1 n-1 00000*5^(n-1) n-1 n-1 규칙으로 늘어남
    // 3 
    // 3 8 11~15 18 23
    // 3 8 11~15 18 23 / 28 33 36~40 43 48 / 51~75 / ...  / ...
}