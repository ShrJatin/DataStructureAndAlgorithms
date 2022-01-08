package Heap;

import java.util.Scanner;

public class run {
    static int delete(int arr[], int idx){
        
        int val = arr[0];
        arr[0] = arr[idx];
        arr[idx] = 0;
        
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
    public static void main(String[] args) {

        MaxHeap hp = new MaxHeap();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int arr [] = new int[n];

        for(int i = 0; i < n; i++){
           arr[i] = sc.nextInt();
           hp.print();
        }
        
        hp.delete();
        hp.delete();
        hp.print();
        // hp.heapify(arr);


        sc.close();

    }

}
