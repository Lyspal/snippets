package test.snippets.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import main.snippets.adt.pq.MaxPriorityQueue;
import main.snippets.adt.pq.PriorityQueue;
import main.snippets.ds.heap.BinaryHeap;
import main.snippets.ds.heap.MaxBinaryHeap;
import main.snippets.ds.heap.MinBinaryHeap;

class BinaryHeapTest {

	static final int LOOPS = 1000;
	static final int MAX_SIZE = 100;
	
	/**
	 * Provides tests with different heap types.
	 * 
	 * @return	the stream of heaps of different types
	 */
	public static Stream<Arguments> provideHeaps() {
		return Stream.of(
			Arguments.of(new MaxBinaryHeap<>()),
			Arguments.of(new MinBinaryHeap<>())
		);
	}
	
	@ParameterizedTest
	@MethodSource("provideHeaps")
	public void testIsEmpty(BinaryHeap<Integer> heap) {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		assertEquals(null, heap.poll());
		assertEquals(null, heap.peek());
	}

	@Test
	public void testMinHeapProperty() {
		MinBinaryHeap<Integer> heap = new MinBinaryHeap<Integer>();
		Integer[] nums = {3, 2, 5, 6, 7, 9, 4, 8, 1};

		for (int n : nums) {
			heap.add(n);
		}
		for (int i = 1; i <= 9; i++) {
			assertTrue(i == heap.poll());
		}
		
		heap.clear();

	    // Try heapify constructor
	    heap = new MinBinaryHeap<>(nums);
	    for (int i = 1; i <= 9; i++) {
	    	assertTrue(i == heap.poll());
	    }
	}
	
	@Test
	public void testMaxHeapProperty() {
		MaxBinaryHeap<Integer> heap = new MaxBinaryHeap<Integer>();
		Integer[] nums = {3, 2, 5, 6, 7, 9, 4, 8, 1};

		for (int n : nums) {
			heap.add(n);
		}
		for (int i = 9; i >= 1; i--) {
			assertTrue(i == heap.poll());
		}
		
		heap.clear();

	    // Try heapify constructor
	    heap = new MaxBinaryHeap<>(nums);
	    for (int i = 9; i >= 1; i--) {
	    	assertTrue(i == heap.poll());
	    }
	}
	
	@Test
	public void testMinHeapify() {
		for (int i = 1; i < LOOPS; i++) {
			Integer[] lst = genRandArray(i);
			BinaryHeap<Integer> minHeap = new MinBinaryHeap<>(lst);

			PriorityQueue<Integer> pq = new PriorityQueue<>(i);
			for (int x : lst) pq.add(x);

			assertTrue(minHeap.isHeap(0)); 
			while (!pq.isEmpty()) {
				assertEquals(minHeap.poll(), pq.poll());
			}
		}
	}
	
	@Test
	public void testMaxHeapify() {
		for (int i = 1; i < LOOPS; i++) {
			Integer[] lst = genRandArray(i);
			BinaryHeap<Integer> maxHeao = new MaxBinaryHeap<>(lst);

			PriorityQueue<Integer> maxPQ = new MaxPriorityQueue<>(i);
			for (int x : lst) maxPQ.add(x);

			assertTrue(maxHeao.isHeap(0)); 
			while (!maxPQ.isEmpty()) {
				assertEquals(maxHeao.poll(), maxPQ.poll());
			}
		}
	}

	@ParameterizedTest
	@MethodSource("provideHeaps")
	public void testClear(BinaryHeap<String> heap) {
		String[] strs = {"aa", "bb", "cc", "dd", "ee"};
		for (String str : strs) {
			heap.add(str);
		}
		heap.clear();
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
	}
	
	@ParameterizedTest
	@MethodSource("provideHeaps")
	public void testContains(BinaryHeap<String> heap) {
		String[] strs = {"aa", "bb", "cc", "dd", "ee"};
		for (String str : strs) {
			heap.add(str);
		}
		heap.remove("aa");
		assertFalse(heap.contains("aa"));
		heap.remove("bb");
		assertFalse(heap.contains("bb"));
		heap.remove("cc");
		assertFalse(heap.contains("cc"));
		heap.remove("dd");
		assertFalse(heap.contains("dd"));
		heap.clear();
		assertFalse(heap.contains("ee"));
	}
	
	@Test
	public void testContainsRandomized() {
		for (int i = 0; i < LOOPS; i++) {
			List<Integer> randNums = genRandList(100);
			BinaryHeap<Integer> maxHeap = new MaxBinaryHeap<>();
			BinaryHeap<Integer> minHeap = new MinBinaryHeap<>();
			for (int j = 0; j < randNums.size(); j++) {
				maxHeap.add(randNums.get(j));
				minHeap.add(randNums.get(j));
			}
	
			for (int j = 0; j < randNums.size(); j++) {
				int randVal = randNums.get(j);
				assertEquals(maxHeap.contains(randVal), minHeap.contains(randVal));
				maxHeap.remove(randVal);
				minHeap.remove(randVal);
				assertEquals(maxHeap.contains(randVal), minHeap.contains(randVal));
			}
		}
	}
	
	/**
	 * Method implementing the test for for tesRemoving.
	 * 
	 * @param in			the input elements
	 * @param removeOrder	the order in which to remove elements
	 */
	public void sequentialRemoving(Integer[] in, Integer[] removeOrder) {
		assertEquals(in.length, removeOrder.length);
		
		BinaryHeap<Integer> heap = new MinBinaryHeap<>(in);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int value : in) {
			pq.add(value);
		}
	
		assertTrue(heap.isHeap(0));
	
		for (int i = 0; i < removeOrder.length; i++) {
	
			int elem = removeOrder[i];
	
			assertTrue(heap.peek() == pq.peek());
			assertEquals(heap.remove(elem), pq.remove(elem));
			assertTrue(heap.size() == pq.size());
			assertTrue(heap.isHeap(0));
		}
		assertTrue(heap.isEmpty());
	}
	
	@Test
	public void testRemoving() {
		Integer[] in = {1, 2, 3, 4, 5, 6, 7};
		Integer[] removeOrder = {1, 3, 6, 4, 5, 7, 2};
		sequentialRemoving(in, removeOrder);
		
		in = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		removeOrder = new Integer[] {7, 4, 6, 10, 2, 5, 11, 3, 1, 8, 9};
		sequentialRemoving(in, removeOrder);
		
		in = new Integer[] {8, 1, 3, 3, 5, 3};
		removeOrder = new Integer[] {3, 3, 5, 8, 1, 3};
		sequentialRemoving(in, removeOrder);
		
		in = new Integer[] {7, 7, 3, 1, 1, 2};
		removeOrder = new Integer[] {2, 7, 1, 3, 7, 1};
		sequentialRemoving(in, removeOrder);
	
		in = new Integer[] {32, 66, 93, 42, 41, 91, 54, 64, 9, 35};
		removeOrder = new Integer[] {64, 93, 54, 41, 35, 9, 66, 42, 32, 91};
		sequentialRemoving(in, removeOrder);
	}

	@Test
	public void testRemovingDuplicatesMin() {
		Integer[] in = new Integer[] {2, 7, 2, 11, 7, 13, 2};
		BinaryHeap<Integer> pq = new MinBinaryHeap<>(in);

		assertTrue(pq.peek() == 2);
		pq.add(3);

		assertTrue(pq.poll() == 2);
		assertTrue(pq.poll() == 2);
		assertTrue(pq.poll() == 2);
		assertTrue(pq.poll() == 3);
		assertTrue(pq.poll() == 7);
		assertTrue(pq.poll() == 7);
		assertTrue(pq.poll() == 11);
		assertTrue(pq.poll() == 13);
	}
	
	@Test
	public void testRemovingDuplicatesMax() {
		Integer[] in = new Integer[] {2, 7, 2, 11, 7, 13, 2};
		BinaryHeap<Integer> pq = new MaxBinaryHeap<>(in);

		assertTrue(pq.peek() == 13);
		pq.add(3);

		assertTrue(pq.poll() == 13);
		assertTrue(pq.poll() == 11);
		assertTrue(pq.poll() == 7);
		assertTrue(pq.poll() == 7);
		assertTrue(pq.poll() == 3);
		assertTrue(pq.poll() == 2);
		assertTrue(pq.poll() == 2);
		assertTrue(pq.poll() == 2);
	}
	
	@Test
	public void testRandomizedPollingMin() {
		for (int i = 0; i < LOOPS; i++) {
			int sz = i;
			
			List<Integer> randNums = genRandList(sz);
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			BinaryHeap<Integer> minHeap = new MinBinaryHeap<>();
			
			// Add all the elements to both priority queues
			for (Integer value : randNums) {
				pq.add(value);
				minHeap.add(value);
			}
			
			while (!pq.isEmpty()) {
				assertTrue(minHeap.isHeap(0));
				assertEquals(pq.size(), minHeap.size());
				assertEquals(pq.peek(), minHeap.peek());
				assertEquals(pq.contains(pq.peek()), minHeap.contains(minHeap.peek()));
				
				Integer v1 = pq.poll();
				Integer v2 = minHeap.poll();
				
				assertEquals(v1, v2);
				assertEquals(pq.peek(), minHeap.peek());
				assertEquals(pq.size(), minHeap.size());
				assertTrue(minHeap.isHeap(0));
			}
		}
	}
	
	@Test
	public void testRandomizedPollingMax() {
		for (int i = 0; i < LOOPS; i++) {
			int sz = i;
			
			List<Integer> randNums = genRandList(sz);
			PriorityQueue<Integer> maxPQ = new MaxPriorityQueue<>();
			BinaryHeap<Integer> maxHeap = new MaxBinaryHeap<>();
			
			// Add all the elements to both priority queues
			for (Integer value : randNums) {
				maxPQ.add(value);
				maxHeap.add(value);
			}
			
			while (!maxPQ.isEmpty()) {
				assertTrue(maxHeap.isHeap(0));
				assertEquals(maxPQ.size(), maxHeap.size());
				assertEquals(maxPQ.peek(), maxHeap.peek());
				assertEquals(maxPQ.contains(maxPQ.peek()), maxHeap.contains(maxHeap.peek()));
				
				Integer v1 = maxPQ.poll();
				Integer v2 = maxHeap.poll();
				
				assertEquals(v1, v2);
				assertEquals(maxPQ.peek(), maxHeap.peek());
				assertEquals(maxPQ.size(), maxHeap.size());
				assertTrue(maxHeap.isHeap(0));
			}
		}
	}
	
	@Test
	public void testRandomizedRemovingMin() {
		for (int i = 0; i < LOOPS; i++) {
			int sz = i;
			List<Integer> randNums = genRandList(sz);
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			BinaryHeap<Integer> minHeap = new MinBinaryHeap<>();
			
			// Add all the elements to both priority queues
			for (Integer value : randNums) {
				pq.add(value);
				minHeap.add(value);
			}
			
			Collections.shuffle(randNums);
			int index = 0;
			
			while (!pq.isEmpty()) {
				int removeNum = randNums.get(index++);
				assertTrue(minHeap.isHeap(0));
				assertEquals(pq.size(), minHeap.size());
				assertEquals(pq.peek(), minHeap.peek());
				pq.remove(removeNum);
				minHeap.remove(removeNum);
				assertEquals(pq.peek(), minHeap.peek());
				assertEquals(pq.size(), minHeap.size());
				assertTrue(minHeap.isHeap(0));
			}
		}
	}
	
	@Test
	public void testRandomizedRemovingMax() {
		for (int i = 0; i < LOOPS; i++) {
			int sz = i;
			List<Integer> randNums = genRandList(sz);
			PriorityQueue<Integer> maxPQ = new MaxPriorityQueue<>();
			BinaryHeap<Integer> maxHeap = new MaxBinaryHeap<>();
			
			// Add all the elements to both priority queues
			for (Integer value : randNums) {
				maxPQ.add(value);
				maxHeap.add(value);
			}
			
			Collections.shuffle(randNums);
			int index = 0;
			
			while (!maxPQ.isEmpty()) {
				int removeNum = randNums.get(index++);
				assertTrue(maxHeap.isHeap(0));
				assertEquals(maxPQ.size(), maxHeap.size());
				assertEquals(maxPQ.peek(), maxHeap.peek());
				maxPQ.remove(removeNum);
				maxHeap.remove(removeNum);
				assertEquals(maxPQ.peek(), maxHeap.peek());
				assertEquals(maxPQ.size(), maxHeap.size());
				assertTrue(maxHeap.isHeap(0));
			}
		}
	}
	
	@Test
	public void testPQReusability() {
		List<Integer> SZs = genUniqueRandList(LOOPS);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		BinaryHeap<Integer> heap = new MinBinaryHeap<>();
		
		for (int sz : SZs) {
			heap.clear();
			pq.clear();
			
			List<Integer> nums = genRandList(sz);
			for (int n : nums) {
				heap.add(n);
				pq.add(n);
			}
			
			Collections.shuffle(nums);
			
			for (int i = 0; i < sz / 2; i++) {
				// Sometimes add a new number into the BinaryHeap
				if (0.25 < Math.random()) {
					int randNum = (int) (Math.random() * 10000);
					pq.add(randNum);
					heap.add(randNum);
				}
				
				int removeNum = nums.get(i);
				assertTrue(heap.isHeap(0));
				assertEquals(pq.size(), heap.size());
				assertEquals(pq.peek(), heap.peek());
				
				pq.remove(removeNum);
				heap.remove(removeNum);
				
				assertEquals(pq.peek(), heap.peek());
				assertEquals(pq.size(), heap.size());
				assertTrue(heap.isHeap(0));
			}
		}
	}
	
	@Test
	public void testMaxPQReusability() {
		List<Integer> SZs = genUniqueRandList(LOOPS);
		PriorityQueue<Integer> pq = new MaxPriorityQueue<>();
		BinaryHeap<Integer> heap = new MaxBinaryHeap<>();
		
		for (int sz : SZs) {
			heap.clear();
			pq.clear();
			
			List<Integer> nums = genRandList(sz);
			for (int n : nums) {
				heap.add(n);
				pq.add(n);
			}
			
			Collections.shuffle(nums);
			
			for (int i = 0; i < sz / 2; i++) {
				// Sometimes add a new number into the BinaryHeap
				if (0.25 < Math.random()) {
					int randNum = (int) (Math.random() * 10000);
					pq.add(randNum);
					heap.add(randNum);
				}
				
				int removeNum = nums.get(i);
				assertTrue(heap.isHeap(0));
				assertEquals(pq.size(), heap.size());
				assertEquals(pq.peek(), heap.peek());
				
				pq.remove(removeNum);
				heap.remove(removeNum);
				
				assertEquals(pq.peek(), heap.peek());
				assertEquals(pq.size(), heap.size());
				assertTrue(heap.isHeap(0));
			}
		}
	}
	
	/**
	 * Generates a random int array.
	 * 
	 * @param size	the size of the array
	 * @return		the array
	 */
	static Integer[] genRandArray(int size) {
		Integer[] lst = new Integer[size];
		for (int i = 0; i < size; i++) {
			lst[i] = (int) (Math.random() * MAX_SIZE);
		}
		return lst;
	}
	
	/**
	 * Generates a list of random numbers.
	 * 
	 * @param sz	the size of the list
	 * @return		the list
	 */
	static List<Integer> genRandList(int sz) {
		List<Integer> lst = new ArrayList<>(sz);
		for (int i = 0; i < sz; i++) {
			lst.add((int) (Math.random() * MAX_SIZE));
		}
		return lst;
	}
	
	/**
	 * Generate a list of unique random numbers.
	 * 
	 * @param sz	the size of the list
	 * @return		the list
	 */
	static List<Integer> genUniqueRandList(int sz) {
		List<Integer> lst = new ArrayList<>(sz);
		for (int i = 0; i < sz; i++) {
			lst.add(i);
		}
		Collections.shuffle(lst);
		return lst;
	}
	
}
