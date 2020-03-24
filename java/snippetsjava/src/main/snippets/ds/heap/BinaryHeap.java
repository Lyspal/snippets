package main.snippets.ds.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abatract class implementing basic methods for a binary heap.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public abstract class BinaryHeap<T extends Comparable<T>> {

	// The number of elements currently inside the heap.
	protected int heapSize = 0;
	
	// The internal capacity of the heap.
	protected int heapCapacity = 0;
	
	protected List<T> heap = null;
	
	/**
	 * Constructor.
	 */
	public BinaryHeap() {
		this(1);
	}
	
	/**
	 * Constructor. Constructs a heap of a given initial capacity.
	 * 
	 * @param capacity	the size of the heap
	 */
	public BinaryHeap(int capacity) {
		heap = new ArrayList<>(capacity);
	}
	
	/**
	 * Constructor. Constructs a heap from a collection.
	 * <p>
	 * Time complexity: O(nlog(n))
	 * 
	 * @param elements	the elements to put in the heap
	 */
	public BinaryHeap(Collection<T> elements) {
		this(elements.size());
		for (T element : elements) {
			add(element);
		}
	}
	
	/**
	 * Checks if the heap is empty.
	 * 
	 * @return	true if empty; false otherwise
	 */
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	/**
	 * Clears the heap.
	 * <p>
	 * Time complexity: O(n)
	 */
	public void clear() {
		for (int i = 0; i < heapCapacity; i++) {
			heap.set(i, null);
		}
		heapSize = 0;
	}
	
	/**
	 * Returns the size of the heap.
	 * 
	 * @return	the size of the heap
	 */
	public int size() {
		return heapSize;
	}
	
	/**
	 * Returns the element at the top of the heap.
	 * 
	 * @return	the element at the top of the heap;
	 * 			null if the heap is empty
	 */
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return heap.get(0);
	}
	
	/**
	 * Removes the element at the top of the heap.
	 * <p>
	 * Time complexity: O(log n)
	 * 
	 * @return	the element at the top of the heap
	 */
	public T poll() {
		return removeAt(0);
	}
	
	/**
	 * Checks if the heap contains the specified element.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param element	the element to look for
	 * @return			true if the heap contains the element;
	 * 					false otherwise
	 */
	public boolean contains(T element) {
		for (int i = 0; i < heapSize; i++) {
			if (heap.get(i).equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds an element to the heap.
	 * <p>
	 * Time complexity: O(log n)
	 * 
	 * @param element	the element to add
	 * @throws IllegalArgumentException
	 */
	public void add(T element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		if (heapSize < heapCapacity) {
			heap.set(heapSize, element);
		} else {
			heap.add(element);
			heapCapacity++;
		}
		swim(heapSize);
		heapSize++;
	}
	
	/**
	 * Compares the values of element i and element j, depending on the type
	 * of the heap: min heap = <=; max heap >=.
	 * 
	 * @param i		the index of the first element
	 * @param j		the indes of the second element
	 * @return		true if comparison is true; false otherwise
	 */
	protected abstract boolean compare(int i, int j);
	
	/**
	 * Moves an element up the tree to maintain the heap invariant.
	 * <p>
	 * Swap elements at every tree level.
	 * <p>
	 * Time complexity: O(log n)
	 * 
	 * @param current	the index of the element to swim
	 */
	protected void swim(int current) {
		int parent = (current - 1) / 2;
		while (current > 0 && compare(current, parent)) {
			swap(parent, current);
			current = parent;
			parent = (current - 1) / 2;
		}
	}
	
//	/**
//	 * Moves an element up the tree to maintain the heap invariant.
//	 * <p>
//	 * Only swap the current element once.
//	 * <p>
//	 * Time complexity: O(log n)
//	 * 
//	 * @param current	the index of the element to swim
//	 */
//	protected void swim(int current) {
//		T element = heap.get(current);
//		int parent = (current - 1) / 2;
//		while (parent > 0 && compare(current, parent)) {
//			heap.set(current, heap.get(parent));
//			current = parent;
//			parent = (current - 1) / 2;
//		} 
//		heap.set(current, element);
//	}
	
	/**
	 * Moves an element down the tree to maintain heap invariant.
	 * <p>
	 * Swap elements at every tree level.
	 * <p>
	 * Time complexity: O(log n)
	 * 
	 * @param current
	 */
	protected void sink(int current) {
		while (true) {
			int left = 2 * current + 1;
			int right = 2 * current + 2;
			int sinkWith = left;	// assume left is the element to sink with
			
			// Find which is smaller left or right.
			if (right < heapSize && compare(right, left)) {
				sinkWith = right;
			}
			
			// Stop if we're outside the bounds of the tree
			// or stop early if we cannot sink current anymore.
			if (left >= heapSize || compare(current, sinkWith)) {
				break;
			}
			
			// Move down the tree following the smallest element.
			swap(sinkWith, current);
			current = sinkWith;
		}
	}
	
//	/**
//	 * Moves an element down the tree to maintain heap invariant.
//	 * <p>
//	 * Only swap the current element once.
//	 * <p>
//	 * Time complexity: O(log n)
//	 * 
//	 * @param current
//	 */
//	protected void sink(int current) {
//		T element = heap.get(current);
//		while (true) {
//			int left = 2 * current + 1;
//			int right = 2 * current + 2;
//			int sinkWith = left;	// assume left is the element to sink with
//			
//			// Find which is smaller left or right.
//			if (right < heapSize && compare(right, left)) {
//				sinkWith = right;
//			}
//			
//			// Stop if we're outside the bounds of the tree
//			// or stop early if we cannot sink current anymore.
//			if (left >= heapSize || compare(current, sinkWith)) {
//				break;
//			}
//			
//			// Move down the tree following the smallest element.
//			heap.set(current, heap.get(sinkWith));
//			current = sinkWith;
//		}
//		heap.set(current, element);
//	}
	
	/**
	 * Swaps two elements.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param i		the first element
	 * @param j		the second element
	 */
	protected void swap(int i, int j) {
		T element1 = heap.get(i);
		T element2 = heap.get(j);
		
		heap.set(i, element2);
		heap.set(j, element1);
	}
	
	/**
	 * Removes the specified element from the heap.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param element	the element to remove
	 * @return			true if the element is removed; false otherwise
	 */
	public boolean remove(T element) {
		if (element == null) {
			return false;
		}
		// Linear removal via search, O(n).
		for (int i = 0; i < heapSize; i++) {
			if (element.equals(heap.get(i))) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes an element at a particular index.
	 * <p>
	 * Time complexity: O(log n)
	 * 
	 * @param i		the index of the element to remove
	 * @return		the removed element
	 */
	protected T removeAt(int i) {
		if (isEmpty()) {
			return null;
		}
		heapSize--;
		T removedElement = heap.get(i);
		
		// Swap the element to remove at the end of the tree and remove it.
		swap(i, heapSize);
		heap.set(heapSize, null);
		
		// If there is not more element in the heap, return the removed element.
		if (i == heapSize) {
			return removedElement;
		}
		
		T element = heap.get(i);
		sink(i);
		
		// If sinking did not work, try swimming.
		if (heap.get(i).equals(element)) {
			swim(i);
		}
		
		return removedElement;
	}
	
	/**
	 * Test method. Recursively checks if the heap invariant is maintained.
	 * 
	 * @param current	the index of the current element
	 * @return			true if maintained; false otherwise
	 */
	public boolean isHeap(int current) {
		// If outside of the bounds of the heap, return true.
		if (current >= heapSize) {
			return true;
		}
		
		int left = 2 * current + 1;
		int right = 2 * current + 2;
		
		// Make sure that the current element is less thn both of its children.
		if (left < heapSize && !compare(current, left)) {
			return false;
		}
		if (right < heapSize && !compare(current, right)) {
			return false;
		}
		
		// Recurse on both children.
		return isHeap(left) && isHeap(right);
	}
	
	/**
	 * Converts the heap to a String representation.
	 * 
	 * @return	the String representation of the heap
	 */
	@Override
	public String toString() {
		return heap.toString();
	}
}
