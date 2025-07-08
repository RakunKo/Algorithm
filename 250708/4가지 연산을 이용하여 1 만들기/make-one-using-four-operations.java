import java.util.*;
public class Main {
    static Queue<Integer> q = new LinkedList<>();
    static int[] vis;
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        vis = new int[2000000];
        q.add(n);
        vis[n]=1;
        bfs(n);
        System.out.print(answer);
    }

    public static void bfs(int n) {
        boolean flag = true;
        while(flag) {
            int cnt = q.size();
            for(int i=0;i<cnt;i++) {
                Integer currentNum = q.poll();
                if(currentNum==1) {
                    flag = false;
                    break;
                }

                if(currentNum % 3 == 0) {
                    if(vis[currentNum/3]==0) {
                        //System.out.print("3나누기 ");
                        q.add(currentNum/3);
                        vis[currentNum/3]=1;
                    }
                }
                if(currentNum % 2 == 0) {
                    if(vis[currentNum/2]==0) {
                        //System.out.print("2나누기 ");
                        q.add(currentNum/2);
                        vis[currentNum/2]=1;
                    }
                }
                if(currentNum <=2000000) {
                    if(vis[currentNum+1]==0) {
                        //System.out.print("1더하기 ");
                        q.add(currentNum+1);
                        vis[currentNum+1]=1;
                    }
                }
                if(currentNum > 1) {
                    if(vis[currentNum-1]==0) {
                        //System.out.print("1빼기 ");
                        q.add(currentNum-1);
                        vis[currentNum-1]=1;
                    }
                }
                //System.out.print("\n");
            }
            if(flag) answer++;
        }
    }
}