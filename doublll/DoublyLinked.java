package doublll;

import java.util.Scanner;

class Node{

    int data;
    Node next;
    Node prev;

    Node(){

    }

    Node(int val){
        data = val;
    }

}

class Doublyll{
    Node head;
    Node tail;
    int size;

    Doublyll(){}

    Doublyll(Doublyll l){
        head = l.head;
        tail = l.tail;
        size = l.size;
    }

    void addLast(int val){
        Node temp = new Node(val);
        size++;
        if(head == null){
            head = tail = temp;
            return;
        }

        tail.next = temp;
        temp.prev = tail;
        tail = temp;
        return;
    }

    void addFirst(int val){
        Node temp = new Node(val);
        size++;
        if(head == null){
            head = tail = temp;
            return;
        }

        temp.next = head;
        head.prev = temp;
        head = temp;
        return;
    }

    void addAt(int val, int ind){
        if(ind < 0 || ind > size){
            System.out.println("index range m nhi h.");
            return;
        } else if(ind == 0){
            addFirst(val);
        } else if(ind == size){
            addLast(val);
        } else{
            Node temp;
            int  i = 1;
            for(temp = head; i < ind; temp = temp.next){
                i++;
            }

            Node add = new Node(val);
            add.next = temp.next;
            temp.next.prev = add;
            add.prev = temp;
            temp.next = add;
            size++;
            return;
        }
    }

    void removeLast(){
        size--;
        if(head == null){
            System.out.println("element nhi h");
            return;
        }
        tail = tail.prev;
        tail.next = null;
        return;
    }

    void removeFirst(){
        size--;
        if(head == null){
            System.out.println("element nhi h");
            return;
        }
        head = head.next;
        head.prev = null;
        return;
    }

    void removeAt(int ind){

        if(ind < 0 || ind >= size){
            System.out.println("index range m nhi h.");
            return;

        } else if(ind == 0){
            removeFirst();

        } else if(ind == size-1){
            removeLast();

        } else{
            Node temp;

            int  i = 1;
            for(temp = head; i < ind; temp = temp.next){
                i++;
            }

            temp.next = temp.next.next;
            temp.next.prev = temp;
            
            size--;
            return;
        }

    }

    private Node getFirst(){
        return head;
    }

    private Node getLast(){
        return tail;
    }

    private Node getAt(int ind){
        if(ind < 0 || ind > size-1){
            System.out.println("index out of range");
            return null;
        } else if(ind == 0){
            Node l = getFirst();
            return l;
        } else if(ind == size -1){
            Node l = getLast();
            return l;
        } else {
            Node temp;
            int  i = 1;
            for(temp = head; i <= ind; temp = temp.next){
                i++;
            }
            return temp;
        }
    }

    int getvalFirst(){
        return getAt(0).data;
    }
    int getvalLast(){
        return getAt(size-1).data;
    }
    int getvalAt(int ind){
        if(ind < 0 || ind > size-1){
            System.out.println("index out of range");
            return -1;
        }
        return getAt(ind).data;
    }

    void display(){
        for(Node temp = head; temp != null; temp = temp.next){
            System.out.print(temp.data + " " );
        }
        System.out.println();
    }    

}

public class DoublyLinked{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Doublyll l = new Doublyll();


        while(true){
            int op = sc.nextInt();
            if(op == -1){
                return;
            } else if(op == 1){
                int x = sc.nextInt();
                l.addFirst(x);
                l.display();
            } else if(op == 2){
                int x = sc.nextInt();
                l.addLast(x);
                l.display();
            } else if(op == 3){
                int val = sc.nextInt();
                int ind = sc.nextInt();
                l.addAt(val, ind);
                l.display();
            } else if(op == 4){
                // int val = sc.nextInt();
                int ind = sc.nextInt();
                l.removeAt(ind);
                l.display();
            } else if(op == 5){
                // int val = sc.nextInt();
                int ind = sc.nextInt();
                int m = l.getvalAt(ind);
                System.out.println(m);
                
            } else if(op == 6){
                // l.reverse();
            }


            else if(op == 10){
                l.display();
            }
        
        
            sc.close();
        }

        
    }
}

