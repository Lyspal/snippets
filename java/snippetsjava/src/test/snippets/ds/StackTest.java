/**
 * Copyright 2020 Sylvain Laporte.
 */
package test.snippets.ds;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.snippets.ds.Stack;

/**
 * Tests for the Stack class. 
 * <p>
 * Uses JUnit 5.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
class StackTest {

	Stack<Integer> stack;
	
	@BeforeEach
	public void setup() {
		stack = new Stack<Integer>();
	}

	/**
	 * Test method for {@link main.snippets.ds.Stack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
	}
	
	/**
	 * Tests pop on an empty stack.
	 */
	@Test
	public void testPopOnEmpty() {
		assertThrows(Exception.class, () -> {
			stack.pop();
		});
	}
	
	/**
	 * Tests peek on an empty stack.
	 */
	@Test
	public void testPeekOnEmpty() {
		assertThrows(Exception.class, () -> {
			stack.peek();
		});
	}

	/**
	 * Test method for {@link main.snippets.ds.Stack#push(java.lang.Object)}.
	 */
	@Test
	void testPush() {
		stack.push(2);
		assertEquals(1, stack.size());
	}

	/**
	 * Test method for {@link main.snippets.ds.Stack#pop()}.
	 */
	@Test
	void testPop() {
		stack.push(2);
		assertTrue(stack.pop() == 2);
		assertEquals(0, stack.size());
	}

	/**
	 * Test method for {@link main.snippets.ds.Stack#peek()}.
	 */
	@Test
	void testPeek() {
		stack.push(2);
		assertTrue(stack.peek() == 2);
		assertEquals(1, stack.size());
	}
	
	/**
	 * Tests a combinaison of operations.
	 */
	@Test
	public void testExhaustively() {
		assertTrue(stack.isEmpty());
		stack.push(1);
		assertTrue(!stack.isEmpty());
		stack.push(2);
		assertEquals(2, stack.size());
		assertTrue(stack.peek() == 2);
		assertEquals(2, stack.size());
		assertTrue(stack.pop() == 2);
		assertEquals(1, stack.size());
		assertTrue(stack.peek() == 1);
		assertEquals(1, stack.size());
		assertTrue(stack.pop() == 1);
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
	}

}
