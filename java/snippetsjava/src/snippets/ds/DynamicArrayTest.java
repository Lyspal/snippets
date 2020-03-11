package snippets.ds;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DynamicArrayTest {
	
	@Test
	void arrayShouldBeCreated() {
		assertTrue((new DynamicArray<Integer>()) != null, "Array not created");
	}

	@Test
	void arrayWithCapacityShouldBeCreated() {
		assertTrue((new DynamicArray<Integer>(8)) != null, "Array not created");
	}

	@Test
	void arrayShouldHaveSize() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		assertEquals(0, array.size(), "Array size is not 0");
		array.add(3);
		assertEquals(1, array.size(), "Array size is not 1");
	}

	@Test
	void arrayShouldBeEmpty() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		assertTrue(array.isEmpty(), "Array is not empty");
		array.add(3);
		assertFalse(array.isEmpty(), "Array is empty");
	}

	@Test
	void methodShouldGetElement() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		array.add(3);
		assertEquals(3, array.get(0), "Did not get the element");
	}

	@Test
	void methodShouldSetElement() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		array.add(3);
		array.set(0, 5);
		assertEquals(5, array.get(0), "Element is not 5");
	}

	@Test
	void methodShouldClearArray() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		array.add(3);
		array.clear();
		assertTrue(array.isEmpty(), "Array is not empty");
	}

	@Test
	void methodShouldAddElement() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		array.add(3);
		assertFalse(array.isEmpty(), "Array is empty");
	}

	@Test
	void methodShouldRemoveAtIndex() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		array.add(3);
		assertEquals(3, array.removeAt(0), "Element is not removed");
		assertTrue(array.isEmpty(), "Array is not empty");
	}

	@Test
	void methodShouldRemoveObj() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		array.add(3);
		assertTrue(array.remove(3), "Element is not removed");
		assertTrue(array.isEmpty(), "Array is not empty");	}

	@Test
	void methoudShouldReturnIndexOfObj() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		array.add(3);
		assertEquals(0, array.indexOf(3), "Index is not 3");
	}

	@Test
	void arrayShouldContain() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		array.add(3);
		assertTrue(array.contains(3), "Array does not contain 3");
	}

	@Test
	void arrayShouldConvertToString() {
		DynamicArray<Integer> array = new DynamicArray<Integer>();
		array.add(3);
		array.add(4);
		assertEquals("[3, 4]", array.toString(), "Array not properly converted to String");
	}

}
