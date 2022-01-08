package circularlinked;

class Node {
    int data;
    Node next;

    // constuctor if only data is provided
    Node(int val) {
        data = val;
        next = null;
    }
}

class circular {

    Node head;
    Node tail;
    int size;

    void add(int val) {
        Node temp = new Node(val);
        if (head == null) {
            head = tail = temp;
            head.next = tail;
            size++;
            return;
        }

        temp.next = head;
        head = temp;
        tail.next = head;
        size++;
        return;
    }

    void addAt(int ind, int val) {
        Node temp = new Node(val);
        if (ind < 0 || ind > size) {
            System.out.println("out of range");

        } else if (ind == 0 || ind == size) {
            add(val);
        } else {
            Node ptr = head;
            for (int i = 0; i < ind - 1; i++) {
                ptr = ptr.next;
            }
            temp.next = ptr.next;
            ptr.next = temp;
            size++;
            return;
        }
    }

    int removeFirst() {
        int data = head.data;
        head = head.next;
        tail.next = head;

        return data;
    }

    int removeLast() {
        int data = tail.data;
        Node temp = head;
        for (int i = 0; i < size - 1; i++) {
            temp = temp.next;
        }
        tail = temp;
        tail.next = head;
        return data;
    }

    int remove(int ind) {
        if (ind < 0 || ind >= size) {
            System.out.println("out of range");
            return -1;
        } else if (ind == 0) {
            int m = removeFirst();
            return m;
        } else if (ind == size - 1) {
            int m = removeLast();
            return m;
        } else {
            Node temp = head;
            for (int i = 0; i < ind - 1; i++) {
                temp = temp.next;
            }
            int data = temp.next.data;
            temp.next = temp.next.next;

            size--;

            return data;
        }
    }

    private Node get(int ind){

        if (ind < 0 || ind >= size) {
            System.out.println("out of range");
            return null;

        } else if (ind == 0) {
            return head;
        } else if (ind == size - 1) {
            return tail;
        } else {
            Node temp = head;
            for (int i = 0; i < ind; i++) {
                temp = temp.next;
            }

            return temp;
        }
    }
    
    int getFirst(){
        return get(0).data;
    }

    int getLast(){
        return get(size-1).data;
    }

    int getAt(int ind){
        return get(ind).data;
    }

    void display(){

        Node temp = head;
        
        for(int i = 0; i < size; i++){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}


