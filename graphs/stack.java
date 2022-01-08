package graphs;

class stackNode<obj> {
    obj value;
    obj data;
    stackNode<obj> next;
    stackNode<obj> prev;

    stackNode(obj d) {
        this.data = d;
    }
}

public class stack<obj> {
    obj value;
    stackNode<obj> head;
    stackNode<obj> tail;
    int size;

    int size() {
        return size;
    }

    void push(obj d) {
        stackNode<obj> new_stackNode = new stackNode<obj>(d);
        if (head == null) {
            head = tail = new_stackNode;
        }

        else {
            tail.next = new_stackNode;
            new_stackNode.prev = tail;
            tail = new_stackNode;
        }

        size++;
    }

    obj pop() {
        obj temp;
        if (head == null) {
            System.out.println("EMPTY");
            return value;
        } else {
            temp = tail.data;
            tail = tail.prev;
        }
        size--;
        return temp;
    }

    obj peek() {
        if (head == null) {
            System.out.println("EMPTY");
            return value;
        } else {
            System.out.println(tail.data);
            return tail.data;
        }
    }

    boolean isempty() {
        return size == 0;
    }

    void display() {
        if (head == null) {
            return;
        }
        for (stackNode<obj> temp = head; temp != null; temp = temp.next) {
            System.out.print(temp.data + "-->");
        }
        System.out.println();
    }
}
