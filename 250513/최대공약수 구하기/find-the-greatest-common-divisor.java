import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // Please write your code here.
        int result = 0;
        for(int i=2;i<=n;i++) {
            if(n % i == 0 && m % i == 0)result = i;
        }
        System.out.print(result);
    }
}