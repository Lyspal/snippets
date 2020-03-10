package snippets.ds;

import java.util.Iterator;

/**
 * Dynamic array.
 * <p>
 * Very similar to Java ArrayList.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

	private T[] array;
	private int length = 0;		// length user thinks array is
	private int capacity = 0;	// actual array size
	
	/**
	 * Default constructor.
	 */
	public DynamicArray() {
		this(16);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param capacity	the initial capacity of the array
	 */
	public DynamicArray(int capacity) {
		// Input capacity validation.
		if (capacity < 0) {
			throw new IllegalArgumentException("Illegal capacity: " + capacity);
		}
		this.capacity = capacity;
		array = (T[]) new Object[capacity];
	}
	
	/**
	 * Returns the size of the array.
	 * 
	 * @return	the size of the array
	 */
	public int size() {
		return length;
	}
	
	/**
	 * Checks if the array is empty.
	 * 
	 * @return	true if empty; else false
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Gets the value at index.
	 * 
	 * @param index	the index of the value to get
	 * @return		the value at index
	 */
	public T get(int index) {
		return array[index];
	}
	
	/**
	 * Sets the value of the element at index.
	 * 
	 * @param index		the index of the element to set
	 * @param element	the element to set at index
	 */
	public void set(int index, T element) {
		array[index] = element;
	}
	
	/**
	 * Clears the array, setting all elements to null.
	 */
	public void clear() {
		for (int i = 0; i < capacity; i++) {
			array[i] = null;
		}
		length = 0;
	}
	
	/**
	 * Adds an element the the array.
	 * <p>
	 * If the capacity of the array is smaller than required, the array is
	 * resized at the double of its current capacity.
	 * 
	 * @param element	the element to add
	 */
	public void add(T element) {
		// Checks if resize is needed.
		if (length + 1 >= capacity) {
			if (capacity == 0) {
				capacity = 1;
			} else {
				capacity *= 2;	// double the size
			}
			// Creates a new array of doubled capacity.
			T[] newArray = (T[]) new Object[capacity];
			// Copies the elements from the old array to the new.
			for (int i = 0; i < length; i++) {
				newArray[i] = array[i];
			}
			// Replaces the old array by the new one.
			array = newArray;
		}
		array[length++] = element;
	}
	
	/**
	 * Removes the element at the specified index of the array, and reduces the
	 * array capacity.
	 * 
	 * @param index	the index of the element to remove
	 * @return		the element removed
	 */
	public T removeAt(int index) {
		// Input index validation.
		if (index >= length && index < 0) {
			throw new IndexOutOfBoundsException();
		}
		T data = array[index];
		// Creates a smaller array.
		T[] newArray = (T[]) new Object[length - 1];
		for (int i = 0, j = 0; i < length; i++, j++) {
			// Skips over index by fixing j temporarily (it will lag over).
			if (i == index) {
				j--;
			} else {
				newArray[j] = array[i];
			}
		}
		array = newArray;
		capacity = --length;
		return data;
	}
	
	/**
	 * Removes the specified element from the array.
	 * 
	 * @param obj	the element to remove
	 * @return		true if the element is removed; false otherwise
	 */
	public boolean remove(Object obj) {
		for (int i = 0; i < length; i++) {
			if (array[i].equals(obj)) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the index of the specified element.
	 * 
	 * @param obj	the element of which we want the index
	 * @return		the index of the specified element
	 */
	public int indexOf(Object obj) {
		for (int i = 0; i < length; i++) {
			if (array[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Checks if the array contains the specified element.
	 * 
	 * @param obj	the element to check
	 * @return		true if the element is found; false otherwise
	 */
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}
	
	/**
	 * Creates a custom Iterator<T> as defined.
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
			public boolean hasNext() {
				return index < length;
			}
			
			//TODO Add checking for concurrent modification.
			
			/**
			 * Returns the next element in the iterator.
			 * 
			 * @return	the next element in the iterator
			 */
			public T next() {
				return array[index++];
			}
		};
	}
	
	/**
	 * Converts the array as a String representation.
	 * 
	 * @return	the String representation of the array
	 */
	@Override
	public String toString() {
		if (length == 0) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder(length).append("[");
			for (int i = 0; i < length - 1; i++) {
				sb.append(array[i] + ", ");
			}
			return sb.append(array[length - 1] + "]").toString();
		}
	}
	
}
