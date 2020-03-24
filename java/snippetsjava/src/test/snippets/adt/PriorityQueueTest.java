package test.snippets.adt;

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

/**
 * Test for the PriorityQueue class.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 *
 */
class PriorityQueueTest {

	static final int LOOPS = 1000;
	static final int MAX_SIZE = 100;
	
	/**
	 * Provides tests with different heap types.
	 * 
	 * @return	the stream of heaps of different types
	 */
	public static Stream<Arguments> providePQs() {
		return Stream.of(
			Arguments.of(new PriorityQueue<>()),
			Arguments.of(new MaxPriorityQueue<>())
		);
	}
	
	@ParameterizedTest
	@MethodSource("providePQs")
	public void testIsEmpty(PriorityQueue<Integer> pq) {
		assertTrue(pq.isEmpty());
		assertEquals(0, pq.size());
		assertEquals(null, pq.poll());
		assertEquals(null, pq.peek());
	}
	
	@ParameterizedTest
	@MethodSource("providePQs")
	public void testClear(PriorityQueue<String> pq) {
		String[] strs = {"aa", "bb", "cc", "dd", "ee"};
		for (String str : strs) {
			pq.add(str);
		}
		pq.clear();
		assertEquals(0, pq.size());
		assertTrue(pq.isEmpty());
	}
	
	@ParameterizedTest
	@MethodSource("providePQs")
	public void testContains(PriorityQueue<String> pq) {
		String[] strs = {"aa", "bb", "cc", "dd", "ee"};
		for (String str : strs) {
			pq.add(str);
		}
		pq.remove("aa");
		assertFalse(pq.contains("aa"));
		pq.remove("bb");
		assertFalse(pq.contains("bb"));
		pq.remove("cc");
		assertFalse(pq.contains("cc"));
		pq.remove("dd");
		assertFalse(pq.contains("dd"));
		pq.clear();
		assertFalse(pq.contains("ee"));
	}
	
	@Test
	public void testContainsRandomized() {
		for (int i = 0; i < LOOPS; i++) {
			List<Integer> randNums = genRandList(100);
			PriorityQueue<Integer> maxPQ = new MaxPriorityQueue<>();
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int j = 0; j < randNums.size(); j++) {
				maxPQ.add(randNums.get(j));
				pq.add(randNums.get(j));
			}
	
			for (int j = 0; j < randNums.size(); j++) {
				int randVal = randNums.get(j);
				assertEquals(maxPQ.contains(randVal), pq.contains(randVal));
				maxPQ.remove(randVal);
				pq.remove(randVal);
				assertEquals(maxPQ.contains(randVal), pq.contains(randVal));
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
