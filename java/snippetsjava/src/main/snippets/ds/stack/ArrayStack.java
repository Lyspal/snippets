/**
 * Copyright 2020 Sylvain Laporte.
 */
package main.snippets.ds.stack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * A dynamic array implementation of a stack.
 * <p>
 * Space complexity:	24 + 24n
 * 						24 + 8n (for static array))
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public class ArrayStack<T> implements Stack<T> {

	private ArrayList<T> array = new ArrayList<T>();
	
	/**
	 * Constructor.
	 */
	public ArrayStack() {
		
	}
	
	/**
	 * Constructor. Create a stack with an initial element.
	 * 
	 * @param firstElement	the initial element
	 */
	public ArrayStack(T firstElement) {
		push(firstElement);
	}
	
	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return	the number of elements
	 */
	public int size() {
		return array.size();
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
		array.add(element);
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
		return array.remove(size() - 1);
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
		return array.get(size() - 1);
	}
	
	/**
	 * Iterates through the stack using an iterator.
	 */
	@Override
	public Iterator<T> iterator() {
		return array.iterator();
	}

}
