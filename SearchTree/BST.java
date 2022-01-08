package SearchTree;

class Node {

    int data;
    Node left;
    Node right;

    Node() {
    }

    Node(final int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class BST {
    Node root = null;

    private Node construct(final Integer arr[], final int lo, final int hi) {

        if (lo > hi)
            return null;

        final int mid = (lo + hi) / 2;

        final Node lp = construct(arr, lo, mid - 1);
        final Node rp = construct(arr, mid + 1, hi);

        final Node temp = new Node(arr[mid]);
        temp.left = lp;
        temp.right = rp;

        return temp;
    }

    Node construct(final Integer arr[]) {
        root = construct(arr, 0, arr.length - 1);
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


    int size() {
        return size(root);
    }
    int size(Node node) {

        if (node == null)
            return 0;

        final int ls = size(node.left);
        final int rs = size(node.right);

        return 1 + ls + rs;
    }


    int sum() {
        return sum(root);
    }
    int sum(Node node) {

        if (node == null)
            return 0;

        final int ls = sum(node.left);
        final int rs = sum(node.right);

        return node.data + ls + rs;
    }


    int max() {
        return max(root);
    }
    int max(Node node) {

        if (node.right == null)
            return node.data;
        int rm = max(node.right);
        return rm;

    }


    int min() {
        return min(root);
    }
    int min(Node node) {
        if (node.left == null)
            return node.data;
        int rm = min(node.left);
        return rm;
    }


    boolean find(int data) {
        return find(root, data);
    }
    boolean find(Node node, final int data) {

        if (node == null)
            return false;
        if (node.data == data)
            return true;
        else if (node.data < data)
            return find(node.left, data);
        else
            return find(node.right, data);
    }


    Node add(int data){
        root = add(root,data);
        return root;
    }
    Node add(Node node, int data){
        if (node == null) {
            return new Node(data);
        }

        if (data > node.data) {
            node.right = add(node.right, data);

        } else if (data < node.data) {
            node.left = add(node.left, data);
        }

        return node;

    }


    Node remove(int data){
        root = remove(root, data);
        return root;
    }
    Node remove(Node node, int data){
        if(data == node.data){
            if(node.left == null && node.right == null){
                return null;

            } else if(node.left == null && node.right != null){
                return node.right;

            } else if(node.left != null && node.right == null){
                return node.left;

            } else{
                int max = max(node.left);
                node.data = max;
                node.left = remove(node.left, max);
                return node;
            }
            
        } else if(data > node.data){
            Node a = remove(node.right,data);
            node.right = a; 

        } else {
            Node a = remove(node.left,data);
            node.left = a; 
        }
        return node;
    }


}

