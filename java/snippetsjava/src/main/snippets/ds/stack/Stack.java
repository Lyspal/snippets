/**
 * Copyright 2020 Sylvain Laporte.
 */
package main.snippets.ds.stack;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * A stack interface.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public interface Stack<T> extends Iterable<T> {
	
	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return	the number of elements
	 */
	public int size();
	
	/**
	 * Checks if the stack is empty.
	 * 
	 * @return	true if empty; false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Pushes an element on the stack.
	 * 
	 * @param element	the element to push
	 */
	public void push(T element);
	
	/**
	 * Pops an element off the stack.
	 * <p>
	 * Throws an exception if the stack is empty.
	 * 
	 * @return	the popped element
	 * @throws	EmptyStackException
	 */
	public T pop();
	
	/**
	 * Peeks the top of the stack without removing an element.
	 * <p>
	 * Throws an exception if the stack is empty.
	 * 
	 * @return	the peeked element
	 * @throws	EmptyStackException
	 */
	public T peek();
	
	/**
	 * Iterates through the stack using an iterator.
	 */
	@Override
	public Iterator<T> iterator();

}
