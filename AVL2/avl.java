package AVL2;

class Node {
    //Node class
    Node left; // address of left child
    Node right;  // address of right child
    long data;   // Id of the person
    long height;  // height of the node 
    long size;   // size of the node
    // a new propert in class is been longroduced to the avl
    // tree so that rquired function can be implimented efficiently

    Node() {
        // constructor of the Node class
    }

    Node(long data) {
        // constructor of the Node class is known already
        this.data = data;
    }
}

class AVL {
    //AVL tree: the algorthm to implement thed avl tree is been taken form the class 
    // lecture slide of Prof. ARUN BALAJI BUDRU. lecture no: 12

    Node root = null;
    //root node longiated with null initailly
    

    Node LLrot(Node node) {
    // class method performing LL rotation.

        Node lc = node.left; 
        Node lr = lc.right;

        lc.right = node;
        node.left = lr;

        node.height = height(node);
        node.size = size(node);  //updte the size of the node Node
       
        lc.height = height(lc);
        lc.size = size(lc);  //updte the size of the lc Node

        // Here we have updted the height and size of Node node, lc as in the rotation postion have been 
        // changed for these nodes and their height and size have to be re evaluated correctly and needs 
        // to be updated as it may be wrong after the rotation.

        if (root == node)
        // change the root node to lc as it will be the new root of avl formed. 
            root = lc;

        return lc;
    }


    Node LRrot(Node node) {
        // class method performing LR rotation.

        Node lc = node.left;
        Node lcrc = lc.right;

        node.left = lcrc.right;
        lc.right = lcrc.left;

        lcrc.left = lc;
        lcrc.right = node;

        lc.height = height(lc);
        lc.size = size(lc);   //updte the size of the lc Node

        node.height = height(node);
        node.size = size(node);   //updte the size of the node Node

        lcrc.height = height(lcrc);
        lcrc.size = size(lcrc);  //updte the size of the lcrc Node

        // Here we have updted the height and size of Node node, lc, lcrc as in the rotation postion have been 
        // changed for these  three nodes and their height and size have to be re evaluated correctly and needs 
        // to be updated as it may be wrong after the rotation.

        if (root == node)
        // change the root node to lc as it will be the new root of avl formed. 

            root = lcrc;

        return lcrc;
    }


    Node RLrot(Node node) {
    // class method performing RL rotation.

        Node rc = node.right;
        Node rl = rc.left;

        node.right = rl.left;
        rc.left = rl.right;

        rl.left = node;
        rl.right = rc;

        rc.height = height(rc);
        rc.size = size(rc);   //updte the size of the rc Node

        node.height = height(node);
        node.size = size(node);  //updte the size of the node Node

        rl.height = height(rl);
        rl.size = size(rl);   //updte the size of the rl Node

        // Here we have updted the height and size of Node rl, node, rc as the substitution have been 
        // done for the these nodes and their height and size have to be re evaluated correctly as it may
        // wrong after this rotation.


        if (root == node)
        // change the root node to lc as it will be the new root of avl formed. 

            root = rl;

        return rl;
    }


    Node RRrot(Node node) {
    // class method performing LL rotation.

        Node rc = node.right;
        Node rl = rc.left;

        rc.left = node;
        node.right = rl;

        node.height = height(node);
        node.size = size(node);   //updte the size of the node Node
        
        rc.height = height(rc);
        rc.size = size(rc);     //updte the size of the rc Node

        // Here we have updted the height and size of Node node, rc as in the rotation postion have been 
        // changed for these two nodes and their height and size have to be re evaluated correctly and needs 
        // to be updated as it may be wrong after the rotation.

        if (root == node)
        // change the root node to lc as it will be the new root of avl formed. 
            root = rc;

        return rc;
    }


    // the approach to insertion the node longo the avl tree is recursive so we let our fate on the 
    // insert funcion that it will insert the node longo the avl tree
    Node insert(long data) {
        //function to insert a node when only data to be inserted been passed
        root = insert(root, data);
        // call the function with parameter root and the data 
        return root;
    }
    Node insert(Node node, long data) {
        //function to insert a node when the data that have to be inserted been passed
        //  as well as the node over which ithave to be insered been passed
        
        if (node == null) {
            // if the node is null thenworking create a new node and retrn
            //  (it is working as base case as well 
            Node temp  = new Node(data);
            temp.height = 1;  // the node have no child so height have been set to 1;
            temp.size = 1;    // the node have no child so size have been set to 1;
            return temp;    //return  the node
        }

        if (data > node.data) {
            // if the data that have to be inserted found to be greater than data of node,
            // then it should be inserted somewhere to the right of the avl treeso the correct
            // position will be searched recursievly to insert the new data and hence the
            // right of the node been equated with the call which will insert the data at right side
            // of the node in avl tree.
            node.right = insert(node.right, data);
           

        } else if (data < node.data) {
            // if the data that have to be inserted found to be less than data of node,
            // then it should be inserted somewhere to the left of the avl tree, so the correct
            // position will be searched recursievly to insert the new data and hence the
            // left of the node been equated with the call which will insert the data at left side
            // of the node in avl tree.
            node.left = insert(node.left, data);
        }

        node.height = height(node);  
        // after inserting the element to the avl tree, height of every node should be updated
        // rcursively using the height function.

        node.size = size(node);
        // after inserting the element to the avl tree, size of every node should be updated
        // rcursively using the size function.


        // the tree after addition of new data we get will be a bst but not a balanced tree
        // so it needs to restore the avl property of thi tree. so to balance the tree we calculate the
        // balance no of each node and if found to be other than -1, 0, 1 then the balancing of the tree
        // will be required.
        // while doing the balancing if the tree there may be 4 cases aris those are handled below. 

        if (balno(node) == 2 && balno(node.left) == 1) {
        // case of LL rotation, after doinng the LL rotation the avl tree will be balnced.
            return LLrot(node);
        }

        if (balno(node) == 2 && balno(node.left) == -1) {     
        // case of LR rotation, after doinng the LR rotation the avl tree will be balnced.
            return LRrot(node);
        }

        if (balno(node) == -2 && balno(node.right) == 1) {
        // case of RL rotation, after doinng the RL rotation the avl tree will be balnced.
            return RLrot(node);
        }

        if (balno(node) == -2 && balno(node.right) == -1) {
        // case of RR rotation, after doinng the RR rotation the avl tree will be balnced.
            return RRrot(node);
        }
        
        return node;

    }


    // the approach to delete the node from the avl tree is recursive so we let our fate on the 
    // delete funcion that it will delete the node from the avl tree
    Node delete(long data) {
    //function to remove a node when only data to be removed have been passed
        root = delete(root, data);
         // call the function with parameter root and the data 
        return root;
    }
    Node delete(Node node, long data) {
        if (node == null)
        // if the node itself have no elements in it so nothing to b deleted and hence return null.
            return null;

        else if (data > node.data) {
            // if the data that have to be deleted found to be greater than data of node,
            // then definetly it should be deleted from somewhere to the right of the avl tree
            // so the correct position will be searched recursievly to delete the desired data
            // from avl tree. as our fate on the delete function is that it will delete the desired
            // so we assumed that the node will be deleted from the right tree
            node.right = delete(node.right, data);

        } else if (data < node.data) {
            // if the data that have to be deleted found to be lesser than data of node,
            // then definetly it should be deleted from somewhere to the right of node of 
            // so the correct position will be searched recursievly to delete the desired data
            // from avl tree. as our fate on the delete function is that it will delete the desired
            // so we assumed that the node will be deleted from the left tree
            node.left = delete(node.left, data);
        }

        else {
            // if the data that have to be deleted is the node then.

            if (node.left == null && node.right == null) {
            // check if the node at right and left are actually null or not
            // then the null will be returned simply so that the node no longer
            // be the part of the avl.
                return null;

            } else if (node.left == null && node.right != null) {
            // check if the node at right is not null but at left is null then 
            // right  of node will be returned so that the node no longer
            // be the part of the avl.
                return node.right;

            } else if (node.left != null && node.right == null) {
            // check if the node at left is not null but at right is null then 
            // left of node will be returned so that the node no longer
            // be the part of the avl.
                return node.left;

            } else {
            // If none of the above 3 follows then none of node at right or at left is null.
            // in this case find the max available element in this left subtree and replace 
            // the data of this node with that element.  after which remove that max element
            // from left subtree.
                long max = max(node.left);
                node.data = max;
                node.left = delete(node.left, max);

            }
        }

        node.height = height(node);  
        // after inserting the element to the avl tree, height of every node should be updated
        // rcursively using the height function.

        node.size = size(node);
        // after inserting the element to the avl tree, size of every node should be updated
        // rcursively using the size function.


        // the tree after deletion of new data we get will be a bst but not a balanced tree
        // so it needs to restore the avl property of thi tree. so to balance the tree we calculate the
        // balance no of each node and if found to be other than -1, 0, 1 then the balancing of the tree
        // will be required.
        // while doing the balancing if the tree there may be 4 cases aris those are handled below. 


        if (balno(node) == 2 && balno(node.left) == 1) {
        // case of LL rotation, after doinng the LL rotation the avl tree will be balnced.
            return LLrot(node);
        }
    
        if (balno(node) == 2 && balno(node.left) == -1) {     
        // case of LR rotation, after doinng the LR rotation the avl tree will be balnced.
            return LRrot(node);
        }

        if (balno(node) == -2 && balno(node.right) == 1) {
        // case of RL rotation, after doinng the RL rotation the avl tree will be balnced.
            return RLrot(node);
        }

        if (balno(node) == -2 && balno(node.right) == -1) {
        // case of RR rotation, after doinng the RR rotation the avl tree will be balnced.
            return RRrot(node);
        }
        
        return node;
    }


    long height() {
        // returns the hight of avl whole tree
        return height(root);
    }
    long height(Node node) {
        // returns the height of the node

        long hl = (node != null) && (node.left != null) ? node.left.height : 0;
        // height of the left subtree if the left node and node itself sre not null. 
        // the height of the left subtree will be taken from the height property of node

        long hr = (node != null) && (node.right != null) ? node.right.height : 0;
        // height of the right subtree if the right node and node itself sre not null
        // the height of the right subtree will be taken from the height property of node

        // the height of the node will be max of height of the right subtree and left subtree
        // and increased wwith one.
        return 1 + (hl > hr ? hl : hr);
        
    }


    long size(){
        // returns size of the whole avl tree.
        return size(root);
    }
    long size(Node node){
        //returns size of node.

        if(node.right == null && node.left == null) return 1;
        // if there is no child of a node then this node will be the only node after or at the level
        // of presnt node, so the  count of node will be one and will be returned as size of this node.
        
        if(node.right == null) return node.left.size + 1;
        // if there is no right child of the node then count of the node after or at the level of
        // present node will be the count of node till left child(i.e the size of the left child) + 1(for presnt node)
        // so the  count of node will be one + size of left node and this will be returned as size of the present node
        
        if(node.left == null) return node.right.size + 1;
        // if there is no left child of the node then count of the node after or at the level of
        // present node will be the count of node till right child(i.e the size of the right child) + 1(for presnt node)
        // so the count of node will be one + size of right node and this will be returned as size of the present node

        return node.left.size + 1 + node.right.size;
        // if none of above three folllows it means that node have both child in this case then the size of the node will
        // be equal to the size of left chid + right child + 1(for present node itself)


        //  the size that is being accesed here is a O(1) complexity operation as we are accesing the size from the
        // class property of node directly

        // hence the complexity of getting the size of the node is a constant time operation
    }


    long balno(Node node) {
        // balance no is defined as the height as the height of left subtree minus height of
        // right subtree.

        long hl = (node != null) && (node.left != null) ? node.left.height : 0;
        // height of the left subtree if the left node and node itself sre not null. 
        // the height of the left subtree will be taken from the height property of node

        long hr = (node != null) && (node.right != null) ? node.right.height : 0;
        // height of the right subtree if the right node and node itself sre not null
        // the height of the right subtree will be taken from the height property of node


        return hl - hr;
        // taking difference of the left and right subtree to find  the balance no.
        // we will use the concept of balance no discussed in class of Prof. ARUN BALAJI BUDRU
        // to find the unbalanced polong in the tree and then which kind of orotation there should be. 
    }

        
    long max(){
        // return the max element of the whole tree
        return max(root);
    }
    long max(Node node) {
        // return the max data in the subtree whose root node is node
        if (node.right == null)
        // (base case ) as we reach to the right most leaf node the data of this node will be 
        // maximum by the property of an avl tree 
            return node.data;

        // call the max fn recursively till we reahes to the right most lea node
        long rm = max(node.right); // stor the value in rm

        return rm; //return this value
    }


    long min(){
    // return the max element of the whole tree
        return min(root);
    }
    long min(Node node) {
    // return the max data in the subtree whose root node is node

        if (node.left == null)
        // (base case ) as we reach to the left most leaf node the data of this node will be 
        // minimum by the property of an avl tree 
            return node.data;

        // call the min fn recursively till we reahes to the left most leaf node
        long rm = min(node.left); // stor the value in rm

        return rm;//return this value
    }


    long find(long key){
    // return the (key)th smallest element from th tree
        return find(root, key);
    }
    long find(Node node, long key) {
    // return the (key)th smallest element in the tree with the root as node.

        if(key > root.size || key <= 0 || root == null){
        // if the key is is negative or greater the no of all roots(size of root)
        // there will be no element that will correspond to the that key so return -1
        // with a small error msg..
            System.out.print("Error: Key is out of range");
            return -1;
        }

        if(node.left == null){
        //if there is no left child on the tree or subtree then there can be two case arise
        // 1. there is only 1 node in this case max possile value of key will be 1
        // 2. there is only 2 node(1 node itself and other is child) in thhis case the max
        //    possible value of the key will be 2. 
            if(key == 1) return node.data;
        // if key == 1 then return the value of node.

            if(key == 2) return node.right.data;
        // if key == 2 then return the value of right child.
        }

        // if left node is not null then get the size of left child.
        long check = node.left.size;
        
        // if the key is less than or equal to the size of left child of node 
        // it means that the (key)th smallest element we can found in the left subtree.
        if(key <= check) return find(node.left, key);

        // if the key is equal to the size of node then this node will be the (key)th smallest
        // element itself.
        if(key == check + 1) return node.data;

        // if the key is grater than the size of node than it means that the element will can be
        // find in the right subtree of the node so we will check for the (key - check - 1)th in
        // right subtree rather of (key)th smallest as (key - check - 1)th smallest element in the
        // right subtree is equivalnt to the kth smalledt in the whole tree
        return find(node.right, key - check - 1);
    }

    void alpha(boolean maximum){
        // function to implimwnt queries of alpha

        long id = maximum ? max():min(); //check which have to be select max or min
        delete(id); // delet the selected id
        System.out.print(id); //prlong the selected id
    }

    void beta(long k){
        // function to implimwnt queries of beta
        long id = find(k); //find the required id
        delete(id);  // delet the selected id
        System.out.print(id); //prlong the selected id
    }

    void gamma(){
        // function to implimwnt queries of gamma
        long id = find((root.size+1)/2); //find the required id
        delete(id);  // delet the selected id
        System.out.print(id); //prlong the selected id
    }

}