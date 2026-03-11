import java.util.*;
import java.lang.Math.*;

class Solution {
    public String[] solution(String[] expressions) {
        
        Set<Integer> set = new HashSet<>();
        for(int i=2;i<=9;i++) set.add(i);
        
        for(String ex: expressions) {
            String[] part = ex.split(" ");
            String result = part[4];
            
            String a = part[0];
            String b = part[2];
            String ops = part[1];
            
            if(result.equals("X")) {
                decision_x(set, a, b);
            }else {
                decision(set, a, b, ops, result);
            }
        }
        
        //for(int i: set) System.out.print(i+ " ");
        //    System.out.print("\n");
        
        List<String> answer = new ArrayList<>();
        for(String ex: expressions) {
            String[] part = ex.split(" ");
            String result = part[4];
            
            if(result.equals("X")) {
                Set<String> r = new HashSet<>();
                String a = part[0];
                String b = part[2];
                String ops = part[1];
                
                for(int i: set) {
                    int a_i = Integer.parseInt(a, i);
                    int b_i = Integer.parseInt(b, i);
                    
                    if(ops.equals("+")) r.add(Integer.toString(a_i + b_i, i));
                    if(ops.equals("-")) r.add(Integer.toString(a_i - b_i, i));
                }
                
                if(r.size() > 1) answer.add(a + " " + ops + " " + b + " = ?");
                else answer.add(a + " " + ops + " " + b + " = " + r.iterator().next());
            }
            
            
        }
        
        return answer.stream().toArray(String[]::new);
    }
    
    public void decision(
        Set<Integer> set, 
        String a, 
        String b, 
        String ops, 
        String result
    ) {
        List<Integer> remove = new ArrayList<>();
        int max = 0;
        for(char c: a.toCharArray()) max = Math.max(max, c - '0');
        for(char c: b.toCharArray()) max = Math.max(max, c - '0');
        for(char c: result.toCharArray()) max = Math.max(max, c - '0');
        
        for(int i: set) {
            if(i <= max) {
                remove.add(i);
                continue;
            }
            long a_i = Integer.parseInt(a, i);
            long b_i = Integer.parseInt(b, i);
            long r_i = Integer.parseInt(result, i);

            if((ops.equals("+") && a_i + b_i == r_i) ||
               (ops.equals("-") && a_i - b_i == r_i)) continue;
            
            remove.add(i);
        }
        
        for(int i: remove) set.remove(i);
    }
    
    public void decision_x(
        Set<Integer> set, 
        String a, 
        String b
    ) {
        List<Integer> remove = new ArrayList<>();
        int max = 0;
        for(char c: a.toCharArray()) max = Math.max(max, c - '0');
        for(char c: b.toCharArray()) max = Math.max(max, c - '0');
        
        for(int i: set) {
            if(i <= max) {
                remove.add(i);
                continue;
            }
        }
        
        for(int i: remove) set.remove(i);
    }
}