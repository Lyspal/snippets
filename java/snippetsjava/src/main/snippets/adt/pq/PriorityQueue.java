package main.snippets.adt.pq;

import java.util.Collection;

import main.snippets.ds.heap.BinaryHeap;
import main.snippets.ds.heap.MinBinaryHeap;

/**
 * A priority queue implemented using a min binary heap.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public class PriorityQueue<T extends Comparable<T>> {
	
	protected BinaryHeap<T> heap = null;
	
	/**
	 * Constructor.
	 */
	public PriorityQueue() {
		this(1);
	}
	
	/**
	 * Constructor. Constructs a priority queue of a given initial capacity.
	 * 
	 * @param capacity	the size of the priority queue
	 */
	public PriorityQueue(int capacity) {
		heap = new MinBinaryHeap<>(capacity);
	}
	
	/**
	 * Constructor. Constructs a priority queue from an existing element array.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param elements
	 */
	public PriorityQueue(T[] elements) {
		heap = new MinBinaryHeap<T>(elements);
	}
	
	/**
	 * Constructor. Constructs a priority queue from a collection.
	 * <p>
	 * Time complexity: O(nlog(n))
	 * 
	 * @param elements
	 */
	public PriorityQueue(Collection<T> elements) {
		heap = new MinBinaryHeap<T>(elements);
	}
	
	/**
	 * Checks if the priority queue is empty.
	 * 
	 * @return	true if empty; false otherwise
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	/**
	 * Clears the priority queue.
	 * <p>
	 * Time complexity: O(n)
	 */
	public void clear() {
		heap.clear();
	}
	
	/**
	 * Returns the size of the priority queue.
	 * 
	 * @return	the size of the priority queue
	 */
	public int size() {
		return heap.size();
	}
	
	/**
	 * Returns the element at the top of the priority queue.
	 * 
	 * @return	the element at the top of the priority queue;
	 * 			null if the priority queue is empty
	 */
	public T peek() {
		return heap.peek();
	}
	
	/**
	 * Removes the element at the top of the priority queue.
	 * <p>
	 * Time complexity: O(log n)
	 * 
	 * @return	the element at the top of the priority queue
	 */
	public T poll() {
		return heap.poll();
	}
	
	/**
	 * Checks if the priority queue contains the specified element.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param element	the element to look for
	 * @return			true if the priority queue contains the element;
	 * 					false otherwise
	 */
	public boolean contains(T element) {
		return heap.contains(element);
	}
	
	/**
	 * Adds an element to the priority queue.
	 * <p>
	 * Time complexity: O(log n)
	 * 
	 * @param element	the element to add
	 * @throws IllegalArgumentException
	 */
	public void add(T element) {
		heap.add(element);
	}
	
	/**
	 * Removes the specified element from the priority queue.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param element	the element to remove
	 * @return			true if the element is removed; false otherwise
	 */
	public boolean remove(T element) {
		return heap.remove(element);
	}
	
	/**
	 * Converts the priority queue to a String representation.
	 * 
	 * @return	the String representation of the priority queue
	 */
	@Override
	public String toString() {
		return heap.toString();
	}
}
