/**
 * Copyright 2020 Sylvain Laporte.
 */
package main.snippets.adt.queue;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Dynamic array implementation of a queue.
 * <p>
 * Space complexity:	48 + 24n
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 * 
 */
public class DynamicArrayQueue<T> implements Queue<T> {

	private ArrayList<T> array = new ArrayList<T>();
	
	/**
	 * Constructor.
	 */
	public DynamicArrayQueue() {
		
	}
	
	/**
	 * Constructor. Creates a queue with an initial element.
	 * 
	 * @param firstElement
	 */
	public DynamicArrayQueue(T firstElement) {
		enqueue(firstElement);
	}
	
	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return	the number of elements
	 */
	public int size() {
		return array.size();
	}
	
	/**
	 * Checks if the queue is empty.
	 * 
	 * @return	true if empty; false otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Peeks the element at the front of the queue without removing the element.
	 * 
	 * @return	the peeked element
	 * @throws	RuntimeException
	 */
	public T peek() {
		if (isEmpty()) {
			throw new RuntimeException("Empty queue");
		}
		return array.get(0);
	}
	
	/**
	 * Removes the element from the front of the queue.
	 * 
	 * @return	the polled element
	 * @throws	RuntimeException
	 */
	public T dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("Empty queue");
		}
		return array.remove(0);
	}
	
	/**
	 * Adds an element to the back of the queue.
	 * 
	 * @param element	the element to add
	 */
	public void enqueue(T element) {
		array.add(element);
	}
	
	/**
	 * Gets the iterator of the queue.
	 * 
	 * @return	the iterator
	 */
	@Override
	public Iterator<T> iterator() {
		return array.iterator();
	}

}
