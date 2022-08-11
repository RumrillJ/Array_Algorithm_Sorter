import java.util.*;

public class Sorter
{
    public void bubblesort(int arr[])
    {
        int x = arr.length;
        for (int i = 0; i < x - 1; i++)
            for (int j = 0; j < x - i - 1; j++)
                if (arr[j] > arr[j + 1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
    public static int getMax(int arr[], int n)
    {
        int max = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }

    public static void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--)
        {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    static void radixsort(int arr[], int n)
    {
        int m = getMax(arr, n);
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    //Swap Array Utility
    public static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void insertionSort(int arr[], int low, int high)
    {
        for (int i = low + 1; i <= high; i++)
        {
            for (int j = i - 1; j >= low; j--)
            {
                if (arr[j] > arr[j + 1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                else
                {
                    break;
                }
            }
        }
    }

    public static int insertionpartition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = low;
        int j = low;

        while (i <= high) {
            if (arr[i] > pivot)
                i++;
            else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j++;
            }
        }
        return j - 1;
    }

    public static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public static void hybridquickSort(int arr[], int low, int high)
    {
        while (low < high)
        {
            if (high - low < 10)
            {
                insertionSort(arr, low, high);
                break;
            }
            else
            {
                int pivot = insertionpartition(arr, low, high);

                if (pivot - low < pivot - high)
                {
                    hybridquickSort(arr, low, pivot - 1);
                    low = pivot + 1;
                }
                else
                {
                    hybridquickSort(arr, pivot + 1, high);
                    high = pivot - 1;
                }
            }
        }
    }

    public static void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public void mergesort(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];

        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void merge_sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            int m =l+ (r-l)/2;

            merge_sort(arr, l, m);
            merge_sort(arr, m + 1, r);

            mergesort(arr, l, m, r);
        }
    }

    public void heapsort(int arr[])
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heap_sort(arr, n, i);

        for (int i = n - 1; i >= 0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heap_sort(arr, i, 0);
        }
    }

    public void heap_sort(int arr[], int n, int i)
    {
        int largest = i;
        int l = 2 * i + 1;  // left
        int r = 2 * i + 2;  // right

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heap_sort(arr, n, largest);
        }
    }
    //utility class to print arr
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        //Scan Input Array
        System.out.println("Enter Size of Array: ");
        Scanner Scan = new Scanner(System.in);
        int Size = Scan.nextInt();
        int arr[] = new int[Size];
        int x = arr.length;

        //random class
        Random Rand = new Random();

        //loop to add randomint value into array indexes
        for (int i = 0; i < Size; i++)
        {
            arr[i] = Rand.nextInt(10000000);
        }

        //switch statement and run loop for console commands
        boolean Running = true;
        while (Running)
        {
            System.out.println("Press 1 for Heap Sort : ");
            System.out.println("Press 2 for Merge Sort: ");
            System.out.println("Press 3 for Quick Sort: ");
            System.out.println("Press 4 for Hybrid Quick Sort: ");
            System.out.println("Press 5 for Radix Sort: ");
            System.out.println("Press 6 for Bubble Sort: ");
            System.out.println("Press 0 to Exit: ");

            int choice = Scan.nextInt();
            System.out.println("\n");
            switch (choice)
            {
                case 0:
                    Running = false;
                    break;

                case 1:
                    System.out.println("Unsorted Array is: ");
                    printArray(arr);

                    Sort Heap = new Sort();
                    Heap.heapsort(arr);

                    System.out.println("Heap Sorted Array is: ");
                    printArray(arr);

                    Heap.heapsort(arr);
                    System.out.println("Resorted Heap Sort is: ");
                    printArray(arr);
                    System.out.println("\n");
                    break;

                case 2:
                    System.out.println("Unsorted Array is: ");
                    printArray(arr);

                    Sort Merge = new Sort();
                    Merge.merge_sort(arr, 0, arr.length - 1);

                    System.out.println("Merge Sorted Array is: ");
                    printArray(arr);

                    Merge.merge_sort(arr, 0, arr.length - 1);

                    System.out.println("Resorted MergeSort Array is: ");
                    printArray(arr);
                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("Unsorted Array is: ");
                    printArray(arr);

                    quickSort(arr, 0, x - 1);
                    System.out.println("Quick Sorted Array is: ");
                    printArray(arr);

                    quickSort(arr, 0, x - 1);
                    System.out.println("Re-Quick Sorted Array is: ");
                    printArray(arr);
                    System.out.println("\n");
                    break;

                case 4:
                    System.out.println("Unsorted Array is: ");
                    printArray(arr);

                    hybridquickSort(arr, 0, arr.length - 1);
                    System.out.println("HybridQuick Sorted Array is: ");
                    printArray(arr);

                    hybridquickSort(arr, 0, arr.length - 1);
                    System.out.println("Re-HybridQuick Sorted Array is: ");
                    printArray(arr);
                    System.out.println("\n");
                    break;


                case 5:
                    System.out.println("Unsorted Array is: ");
                    printArray(arr);

                    radixsort(arr, x);
                    System.out.println("Radix Sorted Array is: ");
                    printArray(arr);

                    radixsort(arr, x);
                    System.out.println("Re-Radix Sorted Array is: ");
                    printArray(arr);
                    System.out.println("\n");
                    break;

                case 6:
                    System.out.println("Unsorted Array is: ");
                    printArray(arr);

                    Sort bubble = new Sort();
                    System.out.println("Bubble Sorted Array is: ");
                    bubble.bubblesort(arr);
                    printArray(arr);

                    System.out.println("Re-Bubble Sorted Array is: ");
                    bubble.bubblesort(arr);
                    printArray(arr);
                    System.out.println("\n");
                    break;

                default:
                    Running = true;
                    System.out.println("Unpredicted input, please retry");
                    break;
            }

        }
    }
}
