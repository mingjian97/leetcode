package algorithm;

import java.util.Random;

/**
 * @description:
 * @author: wangmingjian02
 * @create: 2020-10-28
 **/
public class QuickSort {
    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] a = {4, 6, 2, 3, 1, 7, 5};
        sort.quickSort(a, 0, a.length - 1);
        for (int i : a) {
            System.out.print(i + " ");
        }

    }

    public void quickSort(int[] a, int l, int r) {
        if (l < r) {
            int pos = partition(a, l, r);
            quickSort(a, l, pos - 1);
            quickSort(a, pos + 1, r);
        }
    }

    public int partition(int[] arr, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l;
        int pivot = arr[i];
        swap(arr,l,i);
        i=l;
        while (l < r) {
            while (l < r && arr[r] >= pivot) {
                r--;
            }
            while (l < r && arr[l] <= pivot) {
                l++;
            }
            swap(arr, l, r);
        }
        swap(arr, l, i);
        return l;
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
