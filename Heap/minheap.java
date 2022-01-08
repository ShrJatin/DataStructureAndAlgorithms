package Heap;

import java.util.*;

class minheap {
    // a min heap is the one jisme parent is less among all its child for every node
    int[] arr;
    int size;
    int capacity;

    minheap(int n) {
        arr = new int[n];
        size = 0;
        capacity = n;

    }

    int size() {
        return size;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int left(int i) {
        return (2 * i) + 1;
    }

    int right(int i) {
        return (2 * i) + 2;
    }

    int getmin() {
        return size > 0 ? arr[0] : -1;
    }

    boolean search(int k) {
        boolean ans = false;
        for (int i : arr) {
            if (i == k) {
                ans = true;
                break;
            }
        }

        return ans;
    }

    void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    int height() {
        return (int) (Math.ceil(Math.log(size + 1)) - 1);
    }

    void downheap(int i) {
        int l = left(i);
        int r = right(i);
        int small = i;
        if (l < size && arr[l] < arr[small])
            small = l;
        if (r < size && arr[r] < arr[small])
            small = r;
        if (small != i) {
            swap(i, small);
            downheap(small);
        }

    }

    void upheap(int i) {
        if (i >= 1 && arr[parent(i)] > arr[i]) {
            swap(i, parent(i));
            upheap(parent(i));
        }
    }

    void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void insert(int k) {
        if (size == capacity) {
            System.out.println("Overflow");
            return;
        }
        int i = size;
        size++;
        arr[i] = k;
        // you can even do the same by calling the upheapify function added above
        while (i > 0 && arr[parent(i)] > arr[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    int removemin() {
        if (size == 0) {
            System.out.println("Underflow");
            return -1;
        }
        if (size == 1) {
            size--;
            return arr[0];
        }
        int root = arr[0];
        arr[0] = arr[size - 1];
        size--;
        downheap(0);
        return root;
    }

    void replace(int i, int key) {
        arr[i] = key;
        while (i > 0 && arr[parent(i)] > arr[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    void delete(int i, int k) {
        replace(i, -1);
        removemin();
    }       

    boolean isvalid(int i) {
        boolean ans = true;
        int l = left(i);
        int r = right(i);
        int small = i;
        if (l < size && arr[l] < arr[small])
            small = l;
        if (r < size && arr[r] < arr[small])
            small = r;
        if (small != i) {
            ans = false;
            // swap(i, small);
            // downheap(small);
        }
        return ans;
    }

    public void decrease(int i, int val) {
        // too i from val the value at index
        
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        minheap mn = new minheap(n);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
            mn.insert(arr[i]);
        }
        int c = 0;
        while (mn.getmin() < k && mn.size() > 0) {
            int x = mn.removemin();
            int y = mn.removemin();
            mn.insert(x + y);
            c++;
        }
        if (mn.size() == 0)
            System.out.println(-1);
        else
            System.out.println(c);
    }
}
