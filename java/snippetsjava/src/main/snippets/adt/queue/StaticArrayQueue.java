/**
 * Copyright 2020 Sylvain Laporte.
 */
package main.snippets.adt.queue;

import java.util.Iterator;

/**
 * Static array implementation of a queue.
 * <p>
 * Space complexity:	24 + 8n
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 * 
 */
public class StaticArrayQueue<T> implements Queue<T> {

	private T[] array;
	private int size = 0;
	
	/**
	 * Constructor.
	 */
	@SuppressWarnings("unchecked")
	public StaticArrayQueue(int size) {
		array = (T[]) new Object[size];
	}
	
	/**
	 * Constructor. Creates a queue with an initial element.
	 * 
	 * @param firstElement
	 */
	public StaticArrayQueue(int size, T firstElement) {
		this(size);
		enqueue(firstElement);
	}
	
	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return	the number of elements
	 */
	public int size() {
		return size;
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
		return array[0];
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
		T out = array[0];
		int i = 0;
		
		// Advance element in the queue if size > 1.
		for (; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		
		array[i] = null;
		size--;
		
		return out;
	}
	
	/**
	 * Adds an element to the back of the queue.
	 * 
	 * @param element	the element to add
	 */
	public void enqueue(T element) {
		array[size] = element;
		size++;
	}
	
	/**
	 * Creates an iterator of the queue.
	 * 
	 * @return	the iterator
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;
			
			/**
			 * Checks if the iterator has a next element.
			 * 
			 * @return	true if it has a next element; false otherwise
			 */
			@Override
			public boolean hasNext() {
				return index < size;
			}
			
			//TODO Add checking for concurrent modification.
			
			/**
			 * Returns the next element in the iterator.
			 * 
			 * @return	the next element in the iterator
			 */
			@Override
			public T next() {
				return array[index++];
			}
		};
	}
	
	/**
	 * Converts the queue to a String representation.
	 * 
	 * @return	the String representation of the array
	 */
	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder(size).append("[ ");
			for (int i = 0; i < size - 1; i++) {
				sb.append(array[i] + ", ");
			}
			return sb.append(array[size - 1] + " ]").toString();
		}
	}
}
