import java.util.*;

class Solution {
    int MAX = 51;
    
    public String[] solution(String[] commands) {
        String[][] grid = new String[MAX][MAX];
        for(int i = 0; i < MAX; i++) {
            for(int j = 0; j < MAX; j++) grid[i][j] = ""; 
        }
        
        int[] parent = new int[MAX*MAX + MAX];
        for(int i = 0; i < MAX*MAX + MAX; i++) parent[i] = i;
        
        String[] value = new String[MAX*MAX + MAX];
        for(int i = 0; i < MAX*MAX + MAX; i++) value[i] = "";
        
        List<String> answer = new ArrayList<>();
        for(String command: commands) {
            String[] part = command.split(" ");
            String ops = part[0];
            
            if(ops.equals("UPDATE")) {
                if(part.length == 4) update(parent, value, Integer.parseInt(part[1]), Integer.parseInt(part[2]), part[3]);
                else updateAll(value, part[1], part[2]);
            }
            else if(ops.equals("MERGE")) merge(parent, value, Integer.parseInt(part[1]), Integer.parseInt(part[2]), Integer.parseInt(part[3]), Integer.parseInt(part[4]));
            else if(ops.equals("UNMERGE")) unmerge(parent, value, Integer.parseInt(part[1]), Integer.parseInt(part[2]));
            else print(parent, value, answer, Integer.parseInt(part[1]), Integer.parseInt(part[2]));   
        }
        
        return answer.stream().map(String::valueOf).toArray(String[]::new);
    }
    
    public void update(int[] parent, String[] value, int r, int c, String v) {
        int idx = find(parent, r*50+c);
        value[idx] = v;
    }
    
    public void updateAll(String[] value, String v, String t) {
        for(int i = 1; i < MAX*MAX + MAX; i++) {
            if(value[i].equals(v)) value[i] = t;
        }
    }
    
    public void merge(int[] parent, String[] value, int r1, int c1, int r2, int c2) {
        if(r1==r2 && c1==c2) return;
        
        int idx1 = find(parent, r1*50+c1);
        int idx2 = find(parent, r2*50+c2);
        
        if(idx1 != idx2) {
            String finalValue = value[idx1] == "" ? value[idx2] : value[idx1];
            parent[idx2] = idx1;
            value[idx1] = finalValue;
            value[idx2] = "";
        }
    }
    
    public void unmerge(int[] parent, String[] value, int r, int c) {
        int idx = find(parent, r*50+c);
        String v = value[idx];
        
        List<Integer> targets = new ArrayList<>();
        for (int i = 1; i < parent.length; i++) {
            if (find(parent, i) == idx) { 
                targets.add(i);
            }
        }

        for (int targetIdx : targets) {
            parent[targetIdx] = targetIdx;
            value[targetIdx] = ""; 
        }
        
        value[r*50+c] = v;
    }
    
    public void print(int[] parent, String[] value, List<String> answer, int r, int c) {
        int idx = find(parent, r*50+c);
        if(value[idx].equals("")) answer.add("EMPTY");
        else answer.add(value[idx]);
    }
    
    public int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent, parent[i]); 
    }
}