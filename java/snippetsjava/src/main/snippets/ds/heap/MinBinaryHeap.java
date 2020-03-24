package main.snippets.ds.heap;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A min binary heap.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public class MinBinaryHeap<T extends Comparable<T>> extends BinaryHeap<T> {

	/**
	 * Constructor.
	 */
	public MinBinaryHeap() {
		this(1);
	}
	
	/**
	 * Constructor. Constructs a heap of a given size.
	 * 
	 * @param size	the size of the heap
	 */
	public MinBinaryHeap(int size) {
		super(size);
	}
	
	/**
	 * Constructor. Constructs a heap from an existing element array.
	 * <p>
	 * Uses the heapify technique.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param elements	the elements to put in the heap
	 */
	public MinBinaryHeap(T[] elements) {
		heapCapacity = elements.length;
		heapSize = heapCapacity;
		heap = new ArrayList<T>(heapCapacity);
		
		// Place all elements in the heap.
		for (int i = 0; i < heapSize; i++) {
			heap.add(elements[i]);
		}
		
		// Heapify process, O(n).
		for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
			sink(i);
		}
	}
	
	/**
	 * Constructor. Constructs a heap from a collection.
	 * <p>
	 * Time complexity: O(nlog(n))
	 * 
	 * @param elements	the elements to put in the heap
	 */
	public MinBinaryHeap(Collection<T> elements) {
		super(elements);
	}

	/**
	 * Compares the values of element i and element j, depending on the type
	 * of the heap: min heap = <=; max heap >=.
	 * 
	 * @param i		the index of the first element
	 * @param j		the indes of the second element
	 * @return		true if comparison is true; false otherwise
	 */
	@Override
	protected boolean compare(int i, int j) {
		T element1 = heap.get(i);
		T element2 = heap.get(j);
		return element1.compareTo(element2) <= 0;
	}
	
}
