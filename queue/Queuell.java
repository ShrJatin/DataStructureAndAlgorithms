package queue;

class Node{
    int data;
    Node next;
    Node(int d){
        this.data = d;
    }
}



public class Queuell {
    Node head;
    Node tail;
    void enqueue(int d){

        Node new_node = new Node(d);
        if(head == null){
            head = tail = new_node;
        }

        else{
            tail.next = new_node;
            tail = new_node;
        }
    }

    void dequeue(){
        if(head == null){
            System.out.println("EMPTY");
            return;
        }
        else{
            head = head.next;
        }
    }
    void peek(){
        if(head == null){
            System.out.println("EMPTY");
        }
        else{
            System.out.println(head.data);
        }
    }

    void display(){
        if(head == null){
            return;
        }
        for(Node temp = head; temp != null; temp = temp.next){
            System.out.print(temp.data + "-->");
        }
        System.out.println();
    }
}

