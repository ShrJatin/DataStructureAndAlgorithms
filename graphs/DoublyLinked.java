package graphs;

import java.util.Scanner;

class dllNode<obj>{
    obj valObj;
    obj data;
    dllNode<obj> next;
    dllNode<obj> prev;

    dllNode(){

    }

    dllNode(obj val){
        data = val;
    }

}

class Doublyll<obj>{
    obj valObj;
    dllNode<obj> head;
    dllNode<obj> tail;
    int size;

    Doublyll(){}

    Doublyll(Doublyll<obj> l){
        head = l.head;
        tail = l.tail;
        size = l.size;
    }

    void addLast(obj val){
        dllNode<obj> temp = new dllNode<obj>(val);
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

    void addFirst(obj val){
        dllNode<obj> temp = new dllNode<obj>(val);
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

    void addAt(obj val, int ind){
        if(ind < 0 || ind > size){
            System.out.println("index range m nhi h.");
            return;
        } else if(ind == 0){
            addFirst(val);
        } else if(ind == size){
            addLast(val);
        } else{
            dllNode<obj> temp;
            int  i = 1;
            for(temp = head; i < ind; temp = temp.next){
                i++;
            }

            dllNode<obj> add = new dllNode<obj>(val);
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
            dllNode<obj> temp;

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

    private dllNode<obj> getFirst(){
        return head;
    }

    private dllNode<obj> getLast(){
        return tail;
    }

    private dllNode<obj> getAt(int ind){
        if(ind < 0 || ind > size-1){
            System.out.println("index out of range");
            return null;
        } else if(ind == 0){
            dllNode<obj> l = getFirst();
            return l;
        } else if(ind == size -1){
            dllNode<obj> l = getLast();
            return l;
        } else {
            dllNode<obj> temp;
            int  i = 1;
            for(temp = head; i <= ind; temp = temp.next){
                i++;
            }
            return temp;
        }
    }

    obj getvalFirst(){
        return getAt(0).data;
    }
    obj getvalLast(){
        return getAt(size-1).data;
    }
    obj getvalAt(int ind){
        if(ind < 0 || ind > size-1){
            System.out.println("index out of range");
            return valObj;
        }
        return getAt(ind).data;
    }

    void display(){
        for(dllNode<obj> temp = head; temp != null; temp = temp.next){
            System.out.print(temp.data + " " );
        }
        System.out.println();
    }    

}

public class DoublyLinked{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Doublyll<Integer> l = new Doublyll<>();


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

