package Heap;

class MaxHeap {
    
    Integer heap[];
    int front;

    MaxHeap(){}

    MaxHeap(int length){
        heap = new Integer[length];
        front = 0;
    }

    int size(){
        return front;
    }

    void insert(int data){
        
        int i = front;
        while(i > 0 && data > heap[(i-1)/2]){
            heap[i] = heap[(i-1)/2];
            i = (i-1)/2;
        }
        heap[i] = data;
        front++;
    }

    private void helper(int st, int end){
        
        int i = st, j = 2*st+1;
        
        while(j < end){
            
            if(heap[j+1] != null && heap[j+1] > heap[j]) j+=1;
            
            if(heap[i] < heap[j]) {
                swap(heap, i, j);    
                i = j;
                j = 2*i+1;
            }
        }   
    }

    int delete(int arr[], int idx){
        
        int val = arr[0];
        arr[0] = arr[idx];
        arr[idx] = 0;
        front--;
        
        int i = 0, j = 1;
            
        while(j < idx){
            
            if(j + 1 < arr.length && arr[j+1] > arr[j]) j+=1;
            
            if(arr[i] < arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;    
                i = j;
                j = 2*i+1;
            } else
                break; 
        }
        return val;

    }
    
    void delete(){
        
        front--;
        heap[0] = heap[front];
        heap[front] = null;
        
        helper(0,front);
        
    }
    
    void heap_sort(){
        int x = front;
        
        while(x > 0){
            x--;          
            swap(heap, 0, x);
            helper(0,x-1);
        }
    }

    void swap(Integer heap[], int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        return;
    }

    void heapify(Integer [] arr){
       
        for(int x = arr.length -1; x >= 0; x--){
            int i = x;
            int j = 2*x + 1;

            while(j < arr.length){

                if(j + 1 < arr.length && arr[j+1] > arr[j]) j+=1;

                if(arr[i] < arr[j]) {
                    swap(arr, i, j);    
                    i = j;
                    j = 2*i+1;
                }
            } 
        }

        heap = arr;
        return;
    }

    void print(){

        for(Integer i: heap){
            if(i == null)
                break;
            System.out.print(i + " ");
        }
        System.out.println();
        
    }


}
