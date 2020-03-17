package test.snippets.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.snippets.ds.DoublyLinkedList;

/**
 * tests for the DoublyLinkedList class.
 * <p>
 * Uses JUnit 5.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 *
 */
class DoublyLinkedListTest {
	
	static final int LOOPS = 10000;
	static final int TEST_SIZE = 40;
	static final int NUM_NULLS = TEST_SIZE / 5;
	static final int MAX_RAND_NUM = 250;
	
	DoublyLinkedList<Integer> list;
	
	/**
	 * Runs before tests start.
	 */
	@BeforeEach
	public void setup() {
		list = new DoublyLinkedList<>();
	}

	@Test
	public void testEmptyList() {
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test
	public void testClear() {
		for (int i = 0; i < 2; i++) {
			list.add(22);
			list.add(33);
			list.add(44);
			assertEquals(3, list.size());
			list.clear();
			assertEquals(0, list.size());
		}
	}
	
	@Test
	public void testRemoveFirstOfEmpty() {
		assertThrows(Exception.class, () -> {
			list.removeFirst();
		});
	}
	
	@Test
	public void testRemoveLastOfEmpty() {
		assertThrows(Exception.class, () -> {
			list.removeLast();
		});
	}
	
	@Test
	public void testPeekFirstOfEmpty() {
		assertThrows(Exception.class, () -> {
			list.peekFirst();
		});
	}
	
	@Test
	public void testPeekLastOfEmpty() {
		assertThrows(Exception.class, () -> {
			list.peekLast();
		});
	}

	@Test
	public void testAddFirst() {
		list.addFirst(3);
		assertEquals(1, list.size());
		list.addFirst(5);
		assertEquals(2, list.size());
	}

	@Test
	public void testAddLast() {
		list.addLast(3);
		assertEquals(1, list.size());
		list.addLast(5);
		assertEquals(2, list.size());
	}

	@Test
	public void testPeekFirst() {
		list.addFirst(3);
		assertTrue(list.peekFirst() == 3);
		assertEquals(1, list.size());
	}

	@Test
	public void testPeekLast() {
		list.addLast(3);
		assertTrue(list.peekLast() == 3);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testPeeking() {
		// [ 5 ]
		list.addFirst(5);
		assertTrue(list.peekFirst() == 5);
		assertTrue(list.peekLast() == 5);
		
		// [ 6, 5 ]
		list.addFirst(6);
		assertTrue(list.peekFirst() == 6);
		assertTrue(list.peekLast() == 5);
		
		// [ 7, 6, 5 ]
		list.addFirst(7);
		assertTrue(list.peekFirst() == 7);
		assertTrue(list.peekLast() == 5);
		
		// [ 7, 6, 5, 8 ]
		list.addLast(8);
		assertTrue(list.peekFirst() == 7);
		assertTrue(list.peekLast() == 8);
		
		// [ 7, 6, 5 ]
		list.removeLast();
		assertTrue(list.peekFirst() == 7);
		assertTrue(list.peekLast() == 5);
		
		// [ 7, 6 ]
		list.removeLast();
		assertTrue(list.peekFirst() == 7);
		assertTrue(list.peekLast() == 6);
		
		// [ 6 ]
		list.removeFirst();
		assertTrue(list.peekFirst() == 6);
		assertTrue(list.peekLast() == 6);
	}

	@Test
	public void testRemoveFirst() {
		list.addFirst(3);
		assertTrue(list.removeFirst() == 3);
		assertTrue(list.isEmpty());
	}

	@Test
	public void testRemoveLast() {
		list.addLast(3);
		assertTrue(list.removeLast() == 3);
		assertTrue(list.isEmpty());
	}

	@Test
	public void testRemoveAt() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.removeAt(0);
		list.removeAt(2);
		assertTrue(list.peekFirst() == 2);
		assertTrue(list.peekLast() == 3);
		list.removeAt(1);
		list.removeAt(0);
		assertEquals(0, list.size());
	}

	@Test
	public void testRemove() {
		DoublyLinkedList<String> strs = new DoublyLinkedList<>();
		strs.add("a");
		strs.add("b");
		strs.add("c");
		strs.add("d");
		strs.add("e");
		strs.add("f");
		strs.remove("b");
		strs.remove("a");
		strs.remove("d");
		strs.remove("e");
		strs.remove("c");
		strs.remove("f");
		assertEquals(0, strs.size());
	}
	
	@Test
	public void testRandomizedRemove() {
		LinkedList<Integer> base = new LinkedList<>();
		
		// Repeat the test LOOPS times
		for (int loops = 0; loops < LOOPS; loops++) {
			base.clear();
			list.clear();
			
			// Fill base and list with random numbers
			List<Integer> randNums = genRandList(TEST_SIZE);
			for (Integer value : randNums) {
				base.add(value);
				list.add(value);
			}
			
			Collections.shuffle(randNums);
			
			// Remove every numbers randomly and check against base
			for (int i = 0; i < randNums.size(); i++) {
				Integer valueToRemove = randNums.get(i);
				assertEquals(base.remove(valueToRemove), list.remove(valueToRemove));
				assertEquals(base.size(), list.size());
				
				// Check if base and list have the same values
				Iterator<Integer> baseIter = base.iterator();
				Iterator<Integer> listIter = list.iterator();
				while (baseIter.hasNext()) {
					assertEquals(baseIter.next(), listIter.next());
				}
			}
			
			base.clear();
			list.clear();
			
			// Refill base and list with the random numbers
			for (Integer value : randNums) {
				base.add(value);
				list.add(value);
			}
			
			// Try removing numbers whether or not they exist
			for (int i = 0; i < randNums.size(); i++) {
				Integer valueToRemove = (int) (MAX_RAND_NUM * Math.random());
				assertEquals(base.remove(valueToRemove), list.remove(valueToRemove));
				assertEquals(base.size(), list.size());
				
				// Check if base and list have the same values
				Iterator<Integer> baseIter = base.iterator();
				Iterator<Integer> listIter = list.iterator();
				while (baseIter.hasNext()) {
					assertEquals(baseIter.next(), listIter.next());
				}
			}
		}
	}

	@Test
	public void testRandomizedRemoveAt() {
		LinkedList<Integer> base = new LinkedList<>();
		
		// Repeat the test LOOPS times
		for (int loops = 0; loops < LOOPS; loops++) {
			base.clear();
			list.clear();
			
			// Fill base and list with random numbers
			List<Integer> randNums = genRandList(TEST_SIZE);
			for (Integer value : randNums) {
				base.add(value);
				list.add(value);
			}
			
			// Check if the value removed is the same
			for (int i = 0; i < randNums.size(); i++) {
				int indexToRemove = (int) (list.size() * Math.random());
				Integer baseNum = base.remove(indexToRemove);
				Integer listNum = list.removeAt(indexToRemove);
				assertEquals(baseNum, listNum);
				assertEquals(base.size(), list.size());
				
				// Check if base and list have the same values
				Iterator<Integer> baseIter = base.iterator();
				Iterator<Integer> listIter = list.iterator();
				while (baseIter.hasNext()) {
					assertEquals(baseIter.next(), listIter.next());
				}
			}
		}
	}
	
	@Test
	public void testRandomizedIndexOf() {
		LinkedList<Integer> base = new LinkedList<>();
		
		// Repeat the test LOOPS times
		for (int loops = 0; loops < LOOPS; loops++) {
			base.clear();
			list.clear();
			
			// Fill base and list with unique random numbers
			List<Integer> randNums = genUniqueRandList(TEST_SIZE);
			for (Integer value : randNums) {
				base.add(value);
				list.add(value);
			}
			
			Collections.shuffle(randNums);
			
			// Check if indexOf() returns the correct value
			for (int i = 0; i < randNums.size(); i++) {
				Integer element = randNums.get(i);
				Integer baseIndex = base.indexOf(element);
				Integer listIndex = list.indexOf(element);
				assertEquals(baseIndex, listIndex);
				assertEquals(base.size(), list.size());
				
				// Check if base and list have the same values
				Iterator<Integer> baseIter = base.iterator();
				Iterator<Integer> listIter = list.iterator();
				while (baseIter.hasNext()) {
					assertEquals(baseIter.next(), listIter.next());
				}
			}
		}
	}

	// Utility methods
	
	/**
	 * Generates a list of random numbers.
	 * 
	 * @param size	the size of the list
	 * @return		the generated list
	 */
	static List<Integer> genRandList(int size) {
		List<Integer> out = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			out.add((int) (Math.random() * MAX_RAND_NUM));
		}
		for (int i = 0; i < NUM_NULLS; i++) {
			out.add(null);
		}
		Collections.shuffle(out);
		return out;
	}
	
	/**
	 * Generates a list of unique random numbers.
	 * 
	 * @param size	the size of the list
	 * @return		the generated list
	 */
	static List<Integer> genUniqueRandList(int size) {
		List<Integer> out = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			out.add(i);
		}
		for (int i = 0; i < NUM_NULLS; i++) {
			out.add(null);
		}
		Collections.shuffle(out);
		return out;
	}
	
}
