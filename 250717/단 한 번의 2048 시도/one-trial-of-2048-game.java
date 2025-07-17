import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] grid = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        char dir = sc.next().charAt(0);
        // Please write your code here.
        int[][] result = new int[4][4];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<4;i++) {
            stack.clear();
            boolean flag = false;
            switch(dir) {
                case 'L' : {
                    for(int j=0;j<4;j++) {
                        if(grid[i][j] == 0) continue;
                        
                        if(!stack.isEmpty() && !flag) {
                            if(stack.peek() == grid[i][j]) {
                                stack.push(stack.pop()*2);
                                flag = true;
                            }
                            else {
                                stack.push(grid[i][j]);
                                flag = false;
                            }
                        } 
                        else stack.push(grid[i][j]);
                    }
                    for(int j=0;j<stack.size();j++) {
                        result[i][j] = stack.get(j);
                    }
                    break;
                }
                case 'R' : {
                    for(int j=3;j>=0;j--) {
                        if(grid[i][j] == 0) continue;
                        
                        if(!stack.isEmpty() && !flag) {
                            if(stack.peek() == grid[i][j]) {
                                stack.push(stack.pop()*2);
                                flag = true;
                            }
                            else {
                                stack.push(grid[i][j]);
                                flag = false;
                            }
                        } 
                        else stack.push(grid[i][j]);
                    }
                    for(int j=0;j<stack.size();j++) {
                        result[i][3-j] = stack.get(j);
                    }
                    break;      
                }
                case 'U' : {
                    for(int j=0;j<4;j++) {
                        if(grid[j][i] == 0) continue;
                        
                        if(!stack.isEmpty() && !flag) {
                            if(stack.peek() == grid[j][i]) {
                                stack.push(stack.pop()*2);
                                flag = true;
                            }
                            else {
                                stack.push(grid[j][i]);
                                flag = false;
                            }
                        } 
                        else stack.push(grid[j][i]);
                    }
                    for(int j=0;j<stack.size();j++) {
                        result[j][i] = stack.get(j);
                    }
                    break;   
                }
                case 'D' : {
                    for(int j=3;j>=0;j--) {
                        if(grid[j][i] == 0) continue;
                        
                        if(!stack.isEmpty() && !flag) {
                            if(stack.peek() == grid[j][i]) {
                                stack.push(stack.pop()*2);
                                flag = true;
                            }
                            else {
                                stack.push(grid[j][i]);
                                flag = false;
                            }
                        } 
                        else stack.push(grid[j][i]);
                    }
                    for(int j=0;j<stack.size();j++) {
                        result[3-j][i] = stack.get(j);
                    }
                    break;         
                }
            }
        }  
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) System.out.print(result[i][j] + " ");
            System.out.print("\n");
        }
        return;
    }
}