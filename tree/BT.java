package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {

    int data;
    Node left;
    Node right;

    Node() {
    }

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Pair {
    Node node;
    int point;

    Pair(Node node) {
        this.node = node;
        point = 1;
    }
}

public class BT {

    Node root;

    Node construct(Integer arr[]) {

        root = new Node(arr[0]);
        Pair pr = new Pair(root);

        Stack<Pair> st = new Stack<>();
        st.push(pr);

        int i = 0;

        while (!st.isEmpty()) {

            Pair top = st.peek();

            if (top.point == 1) {
                i++;

                if (arr[i] != null) {

                    top.node.left = new Node(arr[i]);
                    Pair lp = new Pair(top.node.left);
                    st.push(lp);

                } else {
                    top.node.left = null;
                }

                top.point++;

            } else if (top.point == 2) {
                i++;

                if (arr[i] != null) {

                    top.node.right = new Node(arr[i]);
                    Pair rp = new Pair(top.node.right);
                    st.push(rp);

                } else {
                    top.node.right = null;
                }

                top.point++;

            } else {
                st.pop();
            }
        }

        return root;
    }

    void display() {
        display(root);
    }

    void display(Node node) {
        if (node == null)
            return;

        String str = "";
        str += (node.left == null) ? "." : node.left.data;
        str += " <= " + node.data + " => ";
        str += (node.right == null) ? "." : node.right.data;

        System.out.println(str);

        display(node.left);
        display(node.right);
        return;
    }

    void levelOrdPrint(){
        levelOrdPrint(root);
    }

    void levelOrdPrint(Node node){
        Queue <Node> q = new LinkedList<>();
        q.add(node);
       
        while(q.size() > 0){
            int run = q.size();
            for(int i = 0; i < run; i++){
                Node top = q.peek();

                if(top.left != null) q.add(q.peek().left);
                if(top.right!= null) q.add(q.peek().right);

                System.out.print(q.remove().data + " ");
            }
            System.out.println();
        }

    }

    void preInPostTravel(Node node){
        if(node == null) return;
        Stack<Pair> st = new Stack<>();
        Pair pr = new Pair(node);
        st.push(pr);

        String pre = "";
        String in = "";
        String post = "";
        
        while(!st.isEmpty()){
            Pair top = st.peek();
            if(top.point == 1){
                pre+=top.node.data + " ";
                top.point++;
                if(top.node.left != null){
                    Pair lp = new Pair(top.node.left);
                    st.push(lp);
                }

            } else if(top.point == 2){
                in+=top.node.data + " ";
                top.point++;
                if(top.node.right != null){
                    Pair rp = new Pair(top.node.right);
                    st.push(rp);
                }

            } else {
                post += st.pop().node.data +" ";
            }
        }

        System.out.println(pre + "\n" + in + "\n" + post);
    }

    ArrayList<Integer> nodetoroot(Node node, int data){
        if(node == null){
            ArrayList <Integer> l = new ArrayList<>();
            return l;
        } 
        ArrayList <Integer> ans = new ArrayList<>();

        Stack<Pair> st = new Stack<>();
        Pair pr = new Pair(node);
        st.push(pr);

        while(!st.isEmpty()){
            Pair top = st.peek();
            if(data == top.node.data){
                break;
            }
            if(top.point == 1){
                top.point++;
                if(top.node.left != null){
                    Pair lp = new Pair(top.node.left);
                    st.push(lp);
                }

            } else if(top.point == 2){
                
                top.point++;
                if(top.node.right != null){
                    Pair rp = new Pair(top.node.right);
                    st.push(rp);
                }

            } else {
                st.pop();
            }
        }

        while(!st.isEmpty()){
            ans.add(st.pop().node.data);
        }

        return ans;

    }

    int size(Node node) {

        if (node == null)
            return 0;

        int n1 = size(node.left);
        int n2 = size(node.right);
        return 1 + n1 + n2;

    }

    int sum(Node node) {

        if (node == null)
            return 0;

        int n1 = sum(node.left);
        int n2 = sum(node.right);
        return node.data + n1 + n2;
    }

    int max(Node node) {

        if (node == null) return 0;

        int n = node.data;
        n = Math.max(n, max(node.left));
        n = Math.max(n, max(node.right));

        return n;
    }

    int height(Node node) {
        if (node == null)
            return -1;

        int n1 = height(node.left);
        int n2 = height(node.right);
        return Math.max(n1, n2) + 1;
    } 

    



}
