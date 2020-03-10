package snippets.algo;

// TODO: Implements for generic types.

/**
 * Implements different sorting algorithms.
 * <p>
 * We suppose the array contains integers only.
 * 
 * @author Sylvain Laporte
 * @version %I%, %G%
 */
public class Sort {

    // Utility methods.

    /**
     * Swaps two elements of an array.
     * 
     * @param array the array inside of which are the elements
     * @param e1    the first element
     * @param e2    the second element
     */
    private static void swap(int[] array, int e1, int e2) {
        int buffer = array[e1];
        array[e1] = array[e2];
        array[e2] = buffer;
    }

    // Comparison sorts.

    /**
     * Sorts an array using selection sort algorithm.
     * <p>
     * Finds the smallest unsorted element in an array, swap it with the
     * first element of the array, then marks it as sorted. Iterate over the
     * remaining unsorted part of the array.
     * 
     * @param array the array to sort
     */
    public static void selectionSort(int[] array) {
        int n = array.length;
        // Repeat until no unsorted element remains.
        for (int first = 0; first < n; first++) {
            int smallest = first;
            // Search the unsorted part of the array to find the smallest value.
            for (int unsorted = first; unsorted < n; unsorted++) {
                if (array[smallest] > array[unsorted]) {
                    smallest = unsorted;
                }
            }
            // Swap the smallest found value with the first element of the
            // unsorted part.
            swap(array, first, smallest);
        }
    }

    /**
     * Sorts an array using the insertion sort algorithm.
     * <p>
     * Finds the smallest element of the array and shifts it whith the larger
     * elements before it. Same as sorting playing cards.
     * 
     * @param array
     */
    public static void insertionSort(int[] array) {
        int n = array.length;
        // Call the first element of the array "sorted".
        // Repeat until all elements are sorted.
        for (int unsorted = 1; unsorted < n; unsorted++) {
            // Look at the next unsorted element. Insert into the "sorted"
            // portion by shifting the requisite number of elements.
            int buffer = array[unsorted];
            int sorted = unsorted - 1;
            // Shifts the required number of elements.
            while (buffer < array[sorted] && sorted >= 0) {
                array[sorted + 1] = array[sorted];
                sorted--;
            }
            // Insert into "sorted" part of the array.
            array[sorted + 1] = buffer;
        }
    }

    /**
     * Sorts an array using the merge sort algorithm.
     * <p>
     * Divide the array in two parts, conquer the subproblems by solving them
     * recursively, and combine the solutions to the subproblems into the
     * solution of the original problem.
     * 
     * @param array the array to sort
     * @param left  the left index of the subarray (0 at first iteration)
     * @param right the right index of the subarray (n at first iteration)
     */
    public static void mergeSort(int[] array, int left, int right) {
        // Base case: empty array.
        if (right - left < 2) {
            return;
        }
        // Divide.
        int middle = (int) Math.floor((right + left) / 2);
        // Conquer.
        mergeSort(array, left, middle);
        mergeSort(array, middle, right);
        // Combine.
        merge(array, left, middle, right);
    }

    /**
     * For merge sort. Merges two subarrays.
     * 
     * @param array     initial array
     * @param left      first index of subarray1
     * @param middle    last index of subarray1 / first index of subarray2
     * @param right     last index of subarray2
     */
    private static void merge(int[] array, int left, int middle, int right) {
        int i;
        int j;
        int[] temp = new int[array.length];
        
        // Copy subarray1 into temp.
        for (i = left; i < middle - 1; i++) {
            temp[i] = array[i];
        }
        // Copy the inverted subarray2 into temp.
        for (j = middle; j < right - 1; j++) {
            temp[middle + right - 1 - j] = array[j];
        }
        // Reset indices.
        i = left;
        j = right - 1;
        int k = left;
        // Merge the subarrays.
        while (i < middle) {
            if (temp[i] <= temp[j]) {
                array[k] = temp[i];
                i++;
                k++;
            } else {
                array[k] = temp[j];
                j--;
                k++;
            }
        }
    }
}