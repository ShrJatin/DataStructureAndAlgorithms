package AVL;

import java.util.Scanner;

public class run {
    public static void main(String[] args) {
        
    

        Scanner sc = new Scanner(System.in);
        AVL tr = new AVL();
    
        int n = sc.nextInt();

        while(n-->0) {
            String s = sc.next();

            if(s.equals("i")) {
                int x = sc.nextInt();
                tr.add(x);

            } else if (s.equals("d")) {
                int x = sc.nextInt();
                tr.remove(x);

            }

            tr.display();

        }
        tr.traversal();

        sc.close();

    }

}
