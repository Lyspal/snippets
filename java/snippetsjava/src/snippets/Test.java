/**
 * Copyright 2020 Sylvain Laporte.
 */
package snippets;

import snippets.algo.Sort;

/**
 * Tests for the implemented snippets.
 * 
 * @author Sylvain Laporte
 * @version %I%, %G%
 */
public class Test {

    public static void main(String[] args) {
        int[] a = {1, 4, 2, 3, 6, 5};

        // Sorting algorithm tests.
        // Sort.selectionSort(a);
        // Sort.insertionSort(a);
        Sort.mergeSort(a, 0, a.length);

        for (int element : a) {
            System.out.println(element);
        }
    }

}