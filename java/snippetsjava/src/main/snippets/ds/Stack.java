/**
 * Copyright 2020 Sylvain Laporte.
 */
package main.snippets.ds;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A doubly linked list implementation of a stack.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public class Stack<T> implements Iterable<T> {

	private LinkedList<T> list = new LinkedList<T>();
	
	/**
	 * Constructor.
	 */
	public Stack() {
		
	}
	
	/**
	 * Constructor. Create a stack with an initial element.
	 * 
	 * @param firstElement	the initial element
	 */
	public Stack(T firstElement) {
		push(firstElement);
	}
	
	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return	the number of elements
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Checks if the stack is empty.
	 * 
	 * @return	true if empty; false otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Pushes an element on the stack.
	 * 
	 * @param element	the element to push
	 */
	public void push(T element) {
		list.addLast(element);
	}
	
	/**
	 * Pops an element off the stack.
	 * <p>
	 * Throws an exception if the stack is empty.
	 * 
	 * @return	the popped element
	 * @throws	EmptyStackException
	 */
	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.removeLast();
	}
	
	/**
	 * Peeks the top of the stack without removing an element.
	 * <p>
	 * Throws an exception if the stack is empty.
	 * 
	 * @return	the peeked element
	 * @throws	EmptyStackException
	 */
	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.peekLast();
	}
	
	/**
	 * Iterates through the stack using an iterator.
	 */
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

}
