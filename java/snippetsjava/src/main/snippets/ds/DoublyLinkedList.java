package main.snippets.ds;

import java.util.Iterator;

/**
 * Doubly linked list data structure.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public class DoublyLinkedList<T> implements Iterable<T> {

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;
	
	/**
	 * Internal node class to represent data. 
	 * 
	 * @author sylvainlaporte
	 * @version %I%, %G%
	 * 
	 * @param <T>
	 */
	private static class Node<T> {
		
		private T data;
		private Node<T> prev;
		private Node<T> next;
		
		/**
		 * Constructor.
		 * 
		 * @param data	the data of the node
		 * @param prev	the previous node
		 * @param next	the next node
		 */
		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
		/**
		 * Returns the data as a string.
		 */
		@Override
		public String toString() {
			return data.toString();
		}
		
	}
	
	/**
	 * Empties the linked list.
	 * <p>
	 * Time complexity: O(n)
	 */
	public void clear() {
		Node<T> trav = head;
		while (trav != null) {
			Node<T> next = trav.next;
			trav.prev = null;
			trav.next = null;
			trav.data = null;
			trav = next;
		}
		head = null;
		tail = null;
		trav = null;
		size = 0;
	}
	
	/**
	 * Returns the size of the linked list.
	 * 
	 * @return	the size of the linked list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Checks if the linked list is empty.
	 * 
	 * @return	true if empty; false otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Adds an element to the tail of the linked list.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param element	the element to add
	 */
	public void add(T element) {
		addLast(element);
	}
	
	/**
	 * Adds an element to the beginning of the linked list.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param element	the element to add
	 */
	public void addFirst(T element) {
		// Checks if the linked list is empty
		if (isEmpty()) {
			tail = new Node<T>(element, null, null);
			head = tail;
		} else {
			head.prev = new Node<T>(element, null, head);
			head = head.prev;
		}
		size++;
	}
	
	/**
	 * Adds an element to the tail of the linked list.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param element	the element to add
	 */
	public void addLast(T element) {
		// Checks if the linked list is empty
		if (isEmpty()) {
			tail = new Node<T>(element, null, null);
			head = tail;
		} else {
			tail.next = new Node<T>(element, tail, null);
			tail = tail.next;
		}
		size++;
	}
	
	/**
	 * Returns the value of the first node, if it exists.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @return	the value of the first node
	 */
	public T peekFirst() {
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
		return head.data;
	}
	
	/**
	 * Returns the value of the last node, if it exists.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @return	the value of the first node
	 */
	public T peekLast() {
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
		return tail.data;
	}
	
	/**
	 * Removes the first value at the head of the linked list.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @return	the value of the head node
	 */
	public T removeFirst() {
		// If the list is empty
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
		
		// Extract the data at the head and move the head pointer forwards
		// one node
		T data = head.data;
		head = head.next;
		--size;
		
		// Now, if the list is empty, set the tail to null
		if (isEmpty()) {
			tail = null;
		} else {	// Do a memory clean of the previous node
			head.prev = null;
		}
		return data;
	}
	
	/**
	 * Removes the last value at the tail of the linked list.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @return	the value of the head node
	 */
	public T removeLast() {
		// If the list is empty
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
		
		// Extract the data at the tail and move the tali pointer backwards
		// one node
		T data = tail.data;
		tail = tail.prev;
		--size;
		
		// Now, if the list is empty, set the head to null
		if (isEmpty()) {
			head = null;
		} else {	// Do a memory clean of the node that was just removed
			tail.next = null;
		}
		return data;
	}
	
	/**
	 * Removes the specified node from the linked list.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param node	the node to remove
	 * @return		the value of the removed node
	 */
	private T remove(Node<T> node) {
		// If the node to remove is at the head or at the tail
		if (node.prev == null) {
			return removeFirst();
		}
		if (node.next == null) {
			return removeLast();
		}
		
		// Make the pointers of adjacent nodes skip over "node"
		node.next.prev = node.prev;
		node.prev.next = node.next;
		
		// Temporary store the data to return
		T data = node.data;
		
		// Memory cleanup
		node.data = null;
		node.prev = null;
		node.next = null;
		node = null;
		
		--size;
		
		return data;
	}
	
	/**
	 * Removes the node at a specified index.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param index	the index of the node to remove
	 * @return		the value of the removed node
	 */
	public T removeAt(int index) {
		// Make sure the index provided is valid
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}
		
		int i;
		Node<T> trav;
		
		// If index is in the first half of the list, search from the head of
		// the list
		if (index < size / 2) {
			for (i = 0, trav = head; i != index; i++) {
				trav = trav.next;
			}
		// Else, search from the tail f the list
		} else {
			for (i = size - 1, trav = tail; i != index; i--) {
				trav = trav.prev;
			}
		}
		return remove(trav);
	}
	
	/**
	 * Removes the specified value in the linked list.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param value	the value to remove
	 * @return		true if the value is removed; otherwise false
	 */
	public boolean remove(Object value) {
		Node<T> trav = head;
		// Support searching for null
		if (value == null) {
			for (trav = head; trav != null; trav = trav.next) {	// loop through list
				if (trav.data == null) {
					remove(trav);
					return true;
				}
			}
		// Search for non null object
		} else {
			for (trav = head; trav != null; trav = trav.next) {	// loop through list
				if (value.equals(trav.data)) {
					remove(trav);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Finds the index of the specified value in the linked list.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param value		the value to search for 
	 * @return			the index of the found value; otherwise -1
	 */
	public int indexOf(Object value) {
		int index = 0;
		Node<T> trav = head;
		// Support searching for null
		if (value == null) {
			for (; trav != null; trav = trav.next, index++) {
				if (trav.data == null) {
					return index;
				}
			}
		// Search for non null value
		} else {
			for (; trav != null; trav = trav.next, index++) {
				if (value.equals(trav.data)) {
					return index;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Checks if a value is contained within the linked list.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param value		the value to check
	 * @return			true if contains; otherwise false
	 */
	public boolean contains(Object value) {
		return indexOf(value) != -1;
	}
	
	/**
	 * Creates a custom Iterator<T> as defined.
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> trav = head;
			
			/**
			 * Checks if the iterator has a next element.
			 * 
			 * @return	true if it has a next element; false otherwise
			 */
			@Override
			public boolean hasNext() {
				return trav != null;
			}
						
			/**
			 * Returns the next element in the iterator.
			 * 
			 * @return	the next element in the iterator
			 */
			@Override
			public T next() {
				T data = trav.data;
				trav = trav.next;
				return data;
			}
			
			/**
			 * Disables the remove operation.
			 */
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	/**
	 * Converts the linked list to a String representation.
	 * 
	 * @return	the String representation of the array
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		Node<T> trav = head;
		while (trav != null) {
			sb.append(trav.data + ", ");
			trav = trav.next;
		}
		sb.append(" ]");
		return sb.toString();
	}

}
