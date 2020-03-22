/**
 * Copyright 2020 Sylvain Laporte.
 */
package main.snippets.adt.queue;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Doubly linked list implementation of a queue.
 * <p>
 * Space complexity:	24 + 40n (with inner class)
 * 						24 + 32n (with static class)
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 * 
 */
public class LinkedListQueue<T> implements Queue<T> {

	private LinkedList<T> list = new LinkedList<T>();
	
	/**
	 * Constructor.
	 */
	public LinkedListQueue() {
		
	}
	
	/**
	 * Constructor. Creates a queue with an initial element.
	 * 
	 * @param firstElement
	 */
	public LinkedListQueue(T firstElement) {
		enqueue(firstElement);
	}
	
	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return	the number of elements
	 */
	public int size() {
		return list.size();
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
		return list.peekFirst();
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
		return list.removeFirst();
	}
	
	/**
	 * Adds an element to the back of the queue.
	 * 
	 * @param element	the element to add
	 */
	public void enqueue(T element) {
		list.addLast(element);
	}
	
	/**
	 * Gets the iterator of the queue.
	 * 
	 * @return	the iterator
	 */
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

}
