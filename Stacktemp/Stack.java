package Stacktemp;

class Stack {

    int ts;
    int data[];
    
    Stack(int len){
        data = new int[len];
        ts = -1;
    }


    int size(){
        return ts+1;
    }


    boolean empty(){
        return(ts == -1);
    }


    int peek(){
        if(ts < 0){
            System.out.println("stack is empty");
            return -1;
        } 
        return data[ts];
    }


    void push(int val){
        if(size() == data.length){
            System.out.println("stack list is full now, cant't add further");
            return;
        }

        ts++;
        data[ts] = val;
        return;
    }


    int pop(){
        if(ts < 0){
            System.out.println("stack is alerady empty");
            return -1;
        } 
        int del = data[ts];
        data[ts] = 0;
        ts--;
        return del;
    }
    

    void display(){
        int i = ts+1;
        while(i --> 0){
            System.out.println(data[i]);
        }
    }

}
