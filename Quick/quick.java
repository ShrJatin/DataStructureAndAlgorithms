package Quick;

import java.util.*;

public class quick {

    static void quicksort(int arr[], int low, int high) {

        if (low >= high) {
            return;
        }

        int piv = partition(arr, low, high);

        quicksort(arr, low, piv - 1);
        quicksort(arr, piv, high);

        return;

    }

    static int partition(int arr[], int low, int high) {

        int mid = (low + high) / 2;
        int piv = arr[mid];

        int st = low;
        int end = high;

        while (st <= end) {

            while (arr[st] < piv) {
                st++;
            }

            while (arr[end] > piv) {
                end--;
            }

            if (st <= end) {
                swap(arr, st, end);
                st++;
                end--;
            }
        }

        return st;
    }

    static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        quicksort(arr, 0, n - 1);

        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();

        sc.close();
    }
}