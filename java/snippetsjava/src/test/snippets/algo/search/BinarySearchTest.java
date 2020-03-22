package test.snippets.algo.search;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.snippets.algo.search.BinarySearch;

class BinarySearchTest {

	int[] testArray = {1, 2, 3, 4, 5};
	
	@Test
	void elementShouldBeFound() {
		for (int element : testArray) {
			String message = element + " is not in the array";
			assertTrue(BinarySearch.binarySearch(testArray, element) >= 0, message);
		}
	}
	
	@Test
	void elementShouldNotBeFound() {
		assertFalse(BinarySearch.binarySearch(testArray, 0) >= 0, "0 is in the array");
		assertFalse(BinarySearch.binarySearch(testArray, 6) >= 0, "6 is in the array");
	}

}
