/**
 * Copyright 2020 Sylvain Laporte.
 */
package test.snippets.adt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import main.snippets.adt.stack.ArrayStack;
import main.snippets.adt.stack.LinkedListStack;
import main.snippets.adt.stack.Stack;

/**
 * Tests for the Stack class. 
 * <p>
 * Uses JUnit 5.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
class StackTest {

	/**
	 * Provides tests with different stack types.
	 * 
	 * @return	the stream of stacks of dirrefent type
	 */
	public static Stream<Arguments> provideStacks() {
		  return Stream.of(
		      Arguments.of(new ArrayStack<Integer>()),
		      Arguments.of(new LinkedListStack<Integer>())
		  );
		}
	
	/**
	 * Test method for {@link main.snippets.adt.stack.LinkedListStack#isEmpty()}.
	 */
	@ParameterizedTest
	@MethodSource("provideStacks")
	public void testIsEmpty(Stack<Integer> stack) {
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
	}
	
	/**
	 * Tests pop on an empty stack.
	 */
	@ParameterizedTest
	@MethodSource("provideStacks")
	public void testPopOnEmpty(Stack<Integer> stack) {
		assertThrows(Exception.class, () -> {
			stack.pop();
		});
	}
	
	/**
	 * Tests peek on an empty stack.
	 */
	@ParameterizedTest
	@MethodSource("provideStacks")
	public void testPeekOnEmpty(Stack<Integer> stack) {
		assertThrows(Exception.class, () -> {
			stack.peek();
		});
	}

	/**
	 * Test method for {@link main.snippets.adt.stack.LinkedListStack#push(java.lang.Object)}.
	 */
	@ParameterizedTest
	@MethodSource("provideStacks")
	void testPush(Stack<Integer> stack) {
		stack.push(2);
		assertEquals(1, stack.size());
	}

	/**
	 * Test method for {@link main.snippets.adt.stack.LinkedListStack#pop()}.
	 */
	@ParameterizedTest
	@MethodSource("provideStacks")
	void testPop(Stack<Integer> stack) {
		stack.push(2);
		assertTrue(stack.pop() == 2);
		assertEquals(0, stack.size());
	}

	/**
	 * Test method for {@link main.snippets.adt.stack.LinkedListStack#peek()}.
	 */
	@ParameterizedTest
	@MethodSource("provideStacks")
	void testPeek(Stack<Integer> stack) {
		stack.push(2);
		assertTrue(stack.peek() == 2);
		assertEquals(1, stack.size());
	}
	
	/**
	 * Tests a combinaison of operations.
	 */
	@ParameterizedTest
	@MethodSource("provideStacks")
	public void testExhaustively(Stack<Integer> stack) {
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
