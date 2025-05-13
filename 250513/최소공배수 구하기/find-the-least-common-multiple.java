import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // Please write your code here.
        int i = 1;
        while(true) {
            if ((n*i) % m == 0) {
                System.out.print(n*i);
                break;
            }
            i++;
        }
    }
}