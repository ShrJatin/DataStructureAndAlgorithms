package graphs;

class queueNode<obj> {
    obj value;
    obj data;
    queueNode<obj> next;

    queueNode(obj d) {
        this.data = d;
    }
}

public class Queuell<obj> {
    obj value;
    queueNode<obj> head;
    queueNode<obj> tail;
    int size;

    int size() {
        return size;
    }

    void enqueue(obj d) {
        queueNode<obj> new_queueNode = new queueNode<obj>(d);
        if (head == null) {
            head = tail = new_queueNode;
        }

        else {
            tail.next = new_queueNode;
            tail = new_queueNode;
        }

        size++;
    }

    obj dequeue() {
        obj temp = value;
        if (head == null) {
            System.out.println("EMPTY");  

        } else {
            temp = head.data;
            head = head.next;
        }
        size--;
        return temp;

    }

    obj peek() {
        if (head == null) {
            System.out.println("EMPTY");
            return value;
        } else {
            System.out.println(head.data);
            return head.data;
        }
    }

    boolean isempty() {
        return size == 0;
    }

    void display() {
        if (head == null) {
            return;
        }
        for (queueNode<obj> temp = head; temp != null; temp = temp.next) {
            System.out.print(temp.data + "-->");
        }
        System.out.println();
    }
}
