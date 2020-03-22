/**
 * Copyright 2020 Sylvain Laporte.
 */
package main.snippets.adt.stack;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * A static array implementation of a stack.
 * <p>
 * Space complexity:	24 + 8n
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public class StaticArrayStack<T> implements Stack<T> {

	private T[] array;
	private int size = 0;
	
	/**
	 * Constructor.
	 */
	@SuppressWarnings("unchecked")
	public StaticArrayStack(int capacity) {
		array = (T[]) new Object[capacity];
	}
	
	/**
	 * Constructor. Create a stack with an initial element.
	 * 
	 * @param firstElement	the initial element
	 */
	public StaticArrayStack(int capacity, T firstElement) {
		this(capacity);
		push(firstElement);
	}
	
	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return	the number of elements
	 */
	public int size() {
		return size;
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
		array[size] = element;
		size++;
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
		T out = array[size - 1];
		array[size - 1] = null;
		size--;
		return out;
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
		return array[size - 1];
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
	 * Converts the stack to a String representation.
	 * 
	 * @return	the String representation of the stack
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
