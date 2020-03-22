/**
 * Copyright 2020 Sylvain Laporte.
 */
package main.snippets.adt.queue;

import java.util.Iterator;

/**
 * A queue interface.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public interface Queue<T> extends Iterable<T> {	
	
	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return	the number of elements
	 */
	public int size();
	
	/**
	 * Checks if the queue is empty.
	 * 
	 * @return	true if empty; false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Peeks the element at the front of the queue without removing the element.
	 * 
	 * @return	the peeked element
	 * @throws	RuntimeException
	 */
	public T peek();
	
	/**
	 * Removes the element from the front of the queue.
	 * 
	 * @return	the polled element
	 * @throws	RuntimeException
	 */
	public T dequeue();
	
	/**
	 * Adds an element to the back of the queue.
	 * 
	 * @param element	the element to add
	 */
	public void enqueue(T element);
	
	/**
	 * Gets the iterator of the queue.
	 * 
	 * @return	the iterator
	 */
	@Override
	public Iterator<T> iterator();

}
