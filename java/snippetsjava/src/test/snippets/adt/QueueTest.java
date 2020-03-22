package test.snippets.adt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import main.snippets.adt.queue.DynamicArrayQueue;
import main.snippets.adt.queue.LinkedListQueue;
import main.snippets.adt.queue.Queue;
import main.snippets.adt.queue.StaticArrayQueue;

class QueueTest {
	/**
	 * Provides tests with different queue types.
	 * 
	 * @return	the stream of queues of different types
	 */
	public static Stream<Arguments> provideQueues() {
		return Stream.of(
			Arguments.of(new StaticArrayQueue<Integer>(2)),
			Arguments.of(new DynamicArrayQueue<Integer>()),
			Arguments.of(new LinkedListQueue<Integer>())
		);
	}
	
	/**
	 * Test method for {@link main.snippets.adt.queue.LinkedListQueue#isEmpty()}.
	 */
	@ParameterizedTest
	@MethodSource("provideQueues")
	public void testIsEmpty(Queue<Integer> queue) {
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
	}
	
	/**
	 * Tests dequeue on an empty queue.
	 */
	@ParameterizedTest
	@MethodSource("provideQueues")
	public void testDequeueOnEmpty(Queue<Integer> queue) {
		assertThrows(Exception.class, () -> {
			queue.dequeue();
		});
	}
	
	/**
	 * Tests peek on an empty queue.
	 */
	@ParameterizedTest
	@MethodSource("provideQueues")
	public void testPeekOnEmpty(Queue<Integer> queue) {
		assertThrows(Exception.class, () -> {
			queue.peek();
		});
	}

	/**
	 * Test method for {@link main.snippets.adt.queue.LinkedListQueue#enqueue(java.lang.Object)}.
	 */
	@ParameterizedTest
	@MethodSource("provideQueues")
	void testEnqueue(Queue<Integer> queue) {
		queue.enqueue(2);
		assertEquals(1, queue.size());
	}

	/**
	 * Test method for {@link main.snippets.adt.queue.LinkedListQueue#dequeue()}.
	 */
	@ParameterizedTest
	@MethodSource("provideQueues")
	void testDequeue(Queue<Integer> queue) {
		queue.enqueue(2);
		assertTrue(queue.dequeue() == 2);
		assertEquals(0, queue.size());
	}

	/**
	 * Test method for {@link main.snippets.adt.queue.LinkedListQueue#peek()}.
	 */
	@ParameterizedTest
	@MethodSource("provideQueues")
	void testPeek(Queue<Integer> queue) {
		queue.enqueue(2);
		assertTrue(queue.peek() == 2);
		assertEquals(1, queue.size());
	}
	
	/**
	 * Tests a combinaison of operations.
	 */
	@ParameterizedTest
	@MethodSource("provideQueues")
	public void testExhaustively(Queue<Integer> queue) {
		assertTrue(queue.isEmpty());
		queue.enqueue(1);
		assertTrue(!queue.isEmpty());
		queue.enqueue(2);
		assertEquals(2, queue.size());
		assertTrue(queue.peek() == 1);
		assertEquals(2, queue.size());
		assertTrue(queue.dequeue() == 1);
		assertEquals(1, queue.size());
		assertTrue(queue.peek() == 2);
		assertEquals(1, queue.size());
		assertTrue(queue.dequeue() == 2);
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
	}

}
