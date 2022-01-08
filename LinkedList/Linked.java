package LinkedList;

class Node {
    int data;
    Node next;

    // constuctor if only data is provided
    Node(int val){
        data = val;
        next = null;
    }

    // constuctor if data and link address both are provided provided
    Node(int val, Node link){
        data = val;
        next = link;
    }
}

class LinkedList{
    Node head;
    Node tail;
    int size;

    int size(){
        /*the return the size of linkedlist.*/
        return size;
    }
    
    boolean isEmpty(){
        return size == 0;
    }
    
    void print(){
        for(Node temp = head; temp != null; temp = temp.next) System.out.print(temp.data + " ");
        System.out.println();
    }

    private Node getNode(int ind){
        if(size == 0){
            return null;
        } else {
            int i = 0;
            Node temp;
            for(temp = head; i < ind; temp = temp.next){
                i++;
            }
            return temp;
        }
    }

// ...........add start.............................................................
    boolean addLast(int element){
        //  add element to  the last of the list.
        Node temp = new Node(element);
        if(size == 0){
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;            
        }
        size++;
        return true;
    }

    boolean addFirst(int element){
        // add element to  the first of the list.
        Node temp = new Node(element);
        if(size == 0){
            head = tail = temp;
        } else {
            temp.next = head;
            head = temp;            
        }
        size++;
        return true;
    }

    boolean add(int index, int element){
        //  add element to the particular paindex of the list.
        if(index > size || index < 0) return false;
        if(index == 0)  return addFirst(element);
        else if (index == size) return addLast(element);
        else {
            Node temp = head;
            for(int i = 0; i < index-1; i++){
                temp = temp.next;
            }
            Node new_node = new Node(element,temp.next);
            temp.next = new_node;
            return true;
        }    
    }
// ...........add finish.............................................................


// ...........get start..............................................................

    int getLast(){
        //  get element from the last of the list.
        if(size == 0){
            System.out.println("List is empty, No element");
            return -1;
        } else {
            return tail.data;
        }
    }

    int getFirst(){
        // get element from the first of the list.
       
        if(size == 0){
            System.out.println("List is empty, No element");
            return -1;
        } else {
            return head.data;            
        }
    }

    int get(int index){
        // get element from the particular paindex of the list.
        if(size == 0){
            System.out.println("List is empty, No element");
            return -1;
        } else if(index >= size || index < 0){
            System.out.println("Index out of bound, Index can be from " + 0 + " to " + (size-1) + " yours is " + index + ".");
            return -1;
        } 

        else if(index == 0)  return getFirst();
        else if (index == size) return getLast();
        else {
            Node temp = head;
            for(int i = 0; i < index; i++){
                temp = temp.next;
            }
            return temp.data;
        }    
    }
// ...........get finish................................................................


// ...........get start..............................................................

    int removeLast() {
        //  get element from the last of the list.
        if (size == 0) {
            System.out.println("List is empty, No elment");
            return -1;
        } else if (size == 1) {
            int data = head.data;
            head = tail = null;
            size = 0;
            return data;
        } else {
            Node temp = head;
            for (int i = 0; i < size - 2; i++) {
                temp = temp.next;
            }
            int data = tail.data;
            tail = temp;
            tail.next = null;
            size--;
            return data;
        }
    }

    int removeFirst(){
        // get element from the first of the list.
    
        if(size == 0){
            System.out.println("List is empty, No element");
            return -1;
        }else if (size == 1) {
            int data = head.data;
            head = tail = null;
            size = 0;
            return data;
        } else {
            int data = head.data;  
            head = head.next;
            size--;
            return data;          
        }
    }

    int remove(int index){
        // get element from the particular paindex of the list.
        if(size == 0){
            System.out.println("List is empty, No element");
            return -1;
        } else if(index >= size || index < 0){
            System.out.println("Index out of bound, Index can be from " + 0 + " to " + (size-1) + " yours is " + index + ".");
            return -1;
        } 

        else if(index == 0)  return removeFirst();
        else if (index == size) return removeLast();
        else {
            Node temp = head;
            for(int i = 0; i < index-1; i++){
                temp = temp.next;
            }
            int data = temp.next.data;
            temp.next = temp.next.next; 
            size--;
            return data;
        }    
    }
// ...........get finish................................................................


// ...........reversing the list start..................................................

    // o(n) approach           
    void reverse(){
        if(size == 0){
            System.out.println("Trying to reverse empty list");
            return;
        }

        Node curNode = head;
        Node prevNode = null;

        while(curNode != null){
            Node temp = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = temp;
        }

        tail = head;
        head = prevNode;
        return;
    }

    // o(n) approach 
    private void reverserechelp(Node node){
        if(node == tail) return;
        reverserechelp(node.next);
        node.next.next = node;
    }

    void reverserec(){
        if(size == 0){
            System.out.println("Trying to reverse empty list");
            return;
        }

        reverserechelp(head);
        head.next = null;
        Node temp = head;
        head = tail;
        tail = temp;
        return;
    }

    // o(n^2) approach
    void reverse_n2(){
        int i = 0;
        int j = size - 1;
        while(i < j){
            Node leftNode = getNode(i);
            Node rightNode = getNode(j);
            int temp = leftNode.data;
            leftNode.data = rightNode.data;
            rightNode.data = temp;
            i++;
            j--;
        }
    }

// ...........reversing the list finish.................................................


// ...........chcking palindrome start..................................................

    boolean isPalbwInd(int start_ind, int end_ind){

        if(size == 0 || end_ind >= size || start_ind < 0){
            System.out.println("empty list or out of bound indexes");
            return false;
        }

        if(start_ind >= end_ind) return true;
        else return getNode(start_ind).data == getNode(end_ind).data && isPalbwInd(++start_ind, --end_ind); 
    }

    boolean ispalhelp(Node lt){
        if(lt == null) return true;

        boolean b1 = ispalhelp(lt.next); 
        if(st.data != lt.data || !b1) return false;
        else{ 
            st = st.next;
            return true;
        }
    }
    Node st;
    boolean ispalin(){
        st = head;
        return ispalhelp(head);
    }

// ...........chcking palindrome the list finish.........................................

}

public class Linked{
    public static void main(String[] args) {

        LinkedList l1 = new LinkedList();
        // l1.addLast(2);
        // l1.addLast(1);
        // l1.addLast(2);
        // l1.addLast(4);
        // l1.addLast(2);
        l1.print();
        System.out.println(l1.ispalin());
        l1.print();

        // System.out.println(l1.remove(1));
        // l1.remove(1);
        // l1.remove(1);

        // System.out.println(l1.get(0));
        // System.out.println(l1.get(1));
        // System.out.println(l1.get(4)); 
        
    }
}