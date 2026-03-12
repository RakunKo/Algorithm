import java.util.*;
import java.lang.Math.*;

class Solution {
    boolean[] vis = new boolean[3];
    List<Long> num = new ArrayList<>();
    List<Integer> ops = new ArrayList<>();
    long answer = 0;
    
    public long solution(String expression) {
        Set<Integer> ops_set = new HashSet<>();
        int start = 0;
        for(int i=0;i<expression.length();i++) {
            char c = expression.charAt(i);
            if(c == '-' || c == '+' || c == '*') {
                int op = 0;

                if(c == '+') op = 0;
                else if(c == '-') op = 1;
                else op = 2;
                
                ops.add(op);
                ops_set.add(op);
                
                String s = expression.substring(start, i);
                num.add(Long.parseLong(s));
                
                start = i+1;
            }
        }
        
        String s = expression.substring(start, expression.length());
        num.add(Long.parseLong(s));
        
        
        List<Integer> ops = new ArrayList<>(ops_set);
        dfs(0, ops.size(), new ArrayList<>(), ops);
        
        return answer;
    }
    
    public void dfs(int d, int n, List<Integer> order, List<Integer> ops_set) {
        if(d==n) {
            //System.out.println(order);
            long result = calculate(order);
            answer = Math.max(answer, result);
            return;
        }
        
        for(int ops: ops_set) {
            if(vis[ops]) continue;
            
            vis[ops] = true;
            order.add(ops);
            dfs(d+1, n, order, ops_set);
            vis[ops] = false;
            order.remove(order.size() - 1);
        }
    }
    
    public long calculate(List<Integer> order) {
        List<Long> ori_num = new ArrayList<>(num);
        List<Integer> ori_ops = new ArrayList<>(ops);
        
        for(int op: order) {
            for(int i=0;i<ori_ops.size();) {
                if(ori_ops.get(i) == op) {
                    long result = 0;
                    long n1 = ori_num.get(i);
                    long n2 = ori_num.get(i+1);
                    if(op == 0) result = n1 + n2;
                    else if(op==1) result = n1 - n2;
                    else result = n1 * n2;
                    
                    ori_num.set(i, result);
                    ori_num.remove(i+1);
                    ori_ops.remove(i);
                }else i++;
            }
        }
        
        return Math.abs(ori_num.get(0));
    }
}