package queue;

class Queue {
    int data[];
    int front;
    int size;

    Queue(int len){
        data = new int[len];
        front = 0;
        size = 0; 
    }

    int size() {
        return size;
    }
    
    void display() {
        
        for(int i = front; i < front + size && i < data.length; i++){
            System.out.print(data[i] + " ");
        }

        for(int i = 0; i < front + size - data.length; i++){
            System.out.print(data[i] + " ");
        }

        System.out.println();
    }

    void add(int val) {
      
        if(size == data.length){
            System.out.println("Queue overflow");
        } else {
            int ad = front + size;
            if (ad >= data.length) {
                ad -= data.length;
            }
            data[ad] = val;
            size++;
        }
    }

    int remove() {
        
        if(size == 0){
            System.out.println("Queue underflow");
            return -1;
        } else {
            int temp = data[front];
            data[front] = 0;
            if(front == data.length-1){
                front = 0;
            } else {
                front++;
            }
            size--;
            return temp;
        }
        
    }

    int peek() {
        
        if(size == 0){
            System.out.println("Queue underflow");
            return -1;
        } else {
            return data[front];
        }
    }
    
}
