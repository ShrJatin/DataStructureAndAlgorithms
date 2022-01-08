package tree;

import java.util.ArrayList;

public class run {
    public static void main(String[] args) {
        
        BT bt1 = new BT();

        Integer arr[] = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = bt1.construct(arr);
    
        bt1.preInPostTravel(root);
        ArrayList <Integer> l = bt1.nodetoroot(root, 30);
        System.out.println(l);

    }
}
