import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num = 1;
        // Please write your code here.
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(num + " ");
                if(num + 1 > 9) num = 1;
                else num++;
            }
            System.out.print("\n");
        }
    }
}