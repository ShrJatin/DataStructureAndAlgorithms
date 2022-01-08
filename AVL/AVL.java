package AVL;

class Node {

    Node left;
    Node right;
    int data;
    int height;

    Node() {
    }

    Node(int data) {
        this.data = data;
    }
}

class AVL {

    Node root = null;
    int left_rot = 0;
    int right_rot = 0;


    Node LLrot(Node node) {
        Node lc = node.left;
        Node lr = lc.right;

        lc.right = node;
        node.left = lr;

        node.height = height(node);
        lc.height = height(lc);

        if (root == node)
            root = lc;

        return lc;
    }


    Node LRrot(Node node) {

        Node lc = node.left;
        Node lcrc = lc.right;

        node.left = lcrc.right;
        lc.right = lcrc.left;

        lcrc.left = lc;
        lcrc.right = node;

        lc.height = height(lc);
        node.height = height(node);
        lcrc.height = height(lcrc);

        if (root == node)
            root = lcrc;

        return lcrc;
    }


    Node RLrot(Node node) {

        Node rc = node.right;
        Node rl = rc.left;

        node.right = rl.left;
        rc.left = rl.right;

        rl.left = node;
        rl.right = rc;

        rc.height = height(rc);
        node.height = height(node);
        rl.height = height(rl);

        if (root == node)
            root = rl;

        return rl;
    }


    Node RRrot(Node node) {

        Node rc = node.right;
        Node rl = rc.left;

        rc.left = node;
        node.right = rl;

        node.height = height(node);
        rc.height = height(rc);

        if (root == node)
            root = rc;

        return rc;
    }


    Node add(int data) {
        root = add(root, data);
        return root;
    }
    Node add(Node node, int data) {

        
        if (node == null) {
            Node temp  = new Node(data);
            temp.height = 1;
            return temp;
        }

        if (data > node.data) {
            node.right = add(node.right, data);

        } else if (data < node.data) {
            node.left = add(node.left, data);
        }

        node.height = height(node);

        if (balno(node) == 2 && balno(node.left) == 1) {
            left_rot++;
            return LLrot(node);

        }

        if (balno(node) == 2 && balno(node.left) == -1) {
            left_rot++;
            right_rot++;
            return LRrot(node);

        }

        if (balno(node) == -2 && balno(node.right) == 1) {
            left_rot++;
            right_rot++;
            return RLrot(node);
        }

        if (balno(node) == -2 && balno(node.right) == -1) {
            right_rot++;
            return RRrot(node);

        }

        return node;

    }

    
    Node remove(int data) {
        root = remove(root, data);
        return root;
    }
    Node remove(Node node, int data) {
        if (node == null)
            return null;

        else if (data > node.data) {
            Node a = remove(node.right, data);
            node.right = a;

        } else if (data < node.data) {
            Node a = remove(node.left, data);
            node.left = a;
        }

        else {
            if (node.left == null && node.right == null) {
                return null;

            } else if (node.left == null && node.right != null) {
                return node.right;

            } else if (node.left != null && node.right == null) {
                return node.left;

            } else {
                int max = max(node.left);
                node.data = max;
                node.left = remove(node.left, max);

            }
        }

        node.height = height(node);

        if (balno(node) == 2 && balno(node.left) == 1) {
            return LLrot(node);
        }

        if (balno(node) == 2 && balno(node.left) == -1) {
            return LRrot(node);
        }

        if (balno(node) == -2 && balno(node.right) == 1) {
            return RLrot(node);
        }

        if (balno(node) == -2 && balno(node.right) == -1) {
            return RRrot(node);

        }

        return node;
    }

    
    int max(){
        return max(root);
    }
    int max(Node node) {

        if (node.right == null)
            return node.data;
        int rm = max(node.right);
        return rm;
    }


    int min(){
        return min(root);
    }
    int min(Node node) {

        if (node.left == null)
            return node.data;
        int rm = min(node.left);
        return rm;
    }


    boolean find(Node node, int data) {
        if (node == null) {
            return false;
        }

        return node.data == data || find(node.left, data) || find(node.right, data);
    }


    int height() {
        return height(root);
    }
    int height(Node node) {
    
        int hl = (node != null) && (node.left != null) ? node.left.height : 0;
        int hr = (node != null) && (node.right != null) ? node.right.height : 0;

        return 1 + (hl > hr ? hl : hr);
    }


    int balno(Node node) {

        int hl = (node != null) && (node.left != null) ? node.left.height : 0;
        int hr = (node != null) && (node.right != null) ? node.right.height : 0;

        return hl - hr;
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


    void traversal(){
        traversal(root);
        System.out.println();
    }
    void traversal(Node node){
        if(node != null){
            System.out.print(node.data + " ");
            traversal(node.left);
            traversal(node.right);
        }
    }

}
