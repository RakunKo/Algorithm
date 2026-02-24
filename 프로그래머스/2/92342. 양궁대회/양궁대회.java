class Solution {
    int[] result = new int[11];
    int[] status = new int[11];
    int max = 0;
    int maxD = 100;
    
    public int[] solution(int n, int[] info) {
        rion(0, n, info);
        for(int i=0;i<11;i++) System.out.print(result[i] + " ");
        
        if(max == 0) return new int[]{-1};
        else return result;
    }
    
    // 점수를 버릴거면 그 점수에 안쏘는게 이득
    public void rion(int d, int n, int[] info) {
        if(d == 11 || n==0) {
            if(d == 11) status[10] = n;
                
            int a = 0;
            int r = 0;
            for(int i=0;i<11;i++) {
            //    System.out.print(status[i] + " ");
                if(info[i] == 0 && status[i] == 0) continue;
                if(info[i] >= status[i]) a += (10-i);
                else r += (10-i);
            }
            
            //System.out.println(" / " + (r-a) + " / " + max);
            if(a < r && max <= r - a) {
                if(max == r-a && maxD < d) {
                    result = status.clone();
                    maxD = d;    
                }
                if(max < r-a) {
                    result = status.clone();
                    maxD = d;  
                }
                max = r - a;
            }
            
            return;
        }
        
        // 버림
        rion(d+1, n, info);
        status[d] = 0;
        
        int apache = info[d] + 1;
        if(n-apache >= 0) {
            status[d] = apache;
            rion(d+1, n-apache, info);
            status[d] = 0;
        }
    }
    
}