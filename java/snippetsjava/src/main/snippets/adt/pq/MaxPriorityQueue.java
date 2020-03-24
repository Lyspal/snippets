package main.snippets.adt.pq;

import java.util.Collection;

import main.snippets.ds.heap.MaxBinaryHeap;

/**
 * A max priority queue implemented using a max binary heap.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public class MaxPriorityQueue<T extends Comparable<T>> extends PriorityQueue<T> {
		
	/**
	 * Constructor.
	 */
	public MaxPriorityQueue() {
		this(1);
	}
	
	/**
	 * Constructor. Constructs a priority queue of a given initial capacity.
	 * 
	 * @param capacity	the size of the priority queue
	 */
	public MaxPriorityQueue(int capacity) {
		heap = new MaxBinaryHeap<>(capacity);
	}
	
	/**
	 * Constructor. Constructs a priority queue from an existing element array.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param elements
	 */
	public MaxPriorityQueue(T[] elements) {
		heap = new MaxBinaryHeap<T>(elements);
	}
	
	/**
	 * Constructor. Constructs a priority queue from a collection.
	 * <p>
	 * Time complexity: O(nlog(n))
	 * 
	 * @param elements
	 */
	public MaxPriorityQueue(Collection<T> elements) {
		heap = new MaxBinaryHeap<T>(elements);
	}

}
