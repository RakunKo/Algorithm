class Solution {
    int cnt = 0;
    int[] vis = new int[10000000];
    int[] visIdx = new int[7];
    
    public int solution(String numbers) {
        dfs(0, numbers.toCharArray(), "");
        return cnt;
    }
    
    public void dfs(int d, char[] chars, String number) {
        if(d > chars.length) return;
        
        if (!number.equals("")) {
            int n = Integer.parseInt(number);
            if(vis[n] == 0) {
                if (isPrime(n)) cnt++;
                vis[n] =1;
            }
        }
    
        for(int i=0;i<chars.length;i++) {
            if(visIdx[i] == 1) continue;
            int num = chars[i] - '0';
            visIdx[i] = 1;
            dfs(d+1, chars, number+num);
            visIdx[i] = 0;
        }
    }
    
    public boolean isPrime(int num) {
        if(num == 1 || num == 0) return false;
        if(num == 2) return true;
        
        for(int i=2;i * i <= num;i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}