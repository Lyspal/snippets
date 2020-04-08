package main.snippets.ds.tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Binary search tree data structure.
 * <p>
 * This implementation prevents from having the same value more than once.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public class BinarySearchTree<T extends Comparable<T>> {

	private int nodeCount = 0;
	private Node root = null;
	
	/**
	 * Inner class for tree nodes.
	 * 
	 * @author sylvainlaporte
	 * @version %I%, %G%
	 */
	private class Node {
		
		T data;
		Node left, right;
		
		/**
		 * Constructor.
		 * 
		 * @param left		the left child node
		 * @param right		the right child node
		 * @param element	the node data
		 */
		public Node(Node left, Node right, T element) {
			this.data = element;
			this.left = left;
			this.right = right;
		}
		
	}
	
	/**
	 * Checks if the binary tree is empty.
	 * 
	 * @return	true if empty; false otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Returns the size of the binary tree.
	 * 
	 * @return	the number of nodes
	 */
	public int size() {
		return nodeCount;
	}
	
	/**
	 * Checks if an element exists in the tree.
	 * 
	 * @param element	the element to find
	 * @return			true if the element exists; false otherwise
	 */
	public boolean contains(T element) {
		return contains(root, element);
	}
	
	/**
	 * Checks if an element exists in the tree. Recursive.
	 * 
	 * @param node		the root of the subtree
	 * @param element	the element to find
	 * @return			true if the element exists; false otherwise
	 */
	private boolean contains(Node node, T element) {
		// Base case: reached bottom, value not found.
		if (node == null) {
			return false;
		}
		
		int cmp = element.compareTo(node.data);
		
		// Dig into the left subtree if element is smaller than the current value.
		if (cmp < 0) {
			return contains(node.left, element);
		
		// Dig into the right subtree if element is larger than the current value.
		} else if (cmp > 0) {
			return contains(node.right, element);
		
		// Value found.
		} else {
			return true;
		}
	}
	
	/**
	 * Adds an element to the tree.
	 *<p>
	 *Do not allow for duplicates.
	 * 
	 * @param element	the element to add
	 * @return			true if successfully added; false otherwise
	 */
	public boolean add(T element) {
		if (contains(element)) {
			return false;
		} else {
			root = add(root, element);
			nodeCount++;
			return true;
		}
	}
	
	/**
	 * Adds a new node to the tree. Recursive.
	 * 
	 * @param node		the root of the subtree
	 * @param element	the element in the new node
	 * @return			the newly created node
	 */
	private Node add(Node node, T element) {
		// Base case: found a leaf node.
		if (node == null) {
			node = new Node(null, null, element);
		} else {
			// Pick a subtree to insert the element.
			if (element.compareTo(node.data) < 0) {
				node.left = add(node.left, element);
			} else {
				node.right = add(node.right, element);
			}
		}
		return node;
	}
	
	/**
	 * Removes the specified element from the tree.
	 * 
	 * @param element	the element to remove
	 * @return			true if successfully removed; false otherwise
	 */
	public boolean remove(T element) {
		if (contains(element)) {
			root = remove(root, element);
			nodeCount--;
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the specified node from the tree. Recursive.
	 * <p>
	 * In this implementation, if the node to remove has two subtrees, it finds
	 * the smallest value in the right subtree.
	 * 
	 * @param node		the root of the subtree
	 * @param element	the element to remove
	 * @return			the removed node
	 */
	private Node remove(Node node, T element) {
		if (node == null) {
			return null;
		}
		
		int cmp = element.compareTo(node.data);
		
		// Dig into the left subtree.
		// Search for the value greater than the current value.
		if (cmp < 0) {
			node.left = remove(node.left, element);
			
		// Dig into the right subtree.
		// Search for the value smaller than the current value.
		} else if (cmp > 0) {
			node.right = remove(node.right, element);
			
		// Found the node to remove.
		} else {
			// Case with only a right subtree or no subtree at all.
			if (node.left == null) {
				Node rightChild = node.right;
				
				// Free memory.
				node.data = null;
				node = null;
				
				return rightChild;
				
			// Case with only a left subtree or no subtree at all.
			} else if (node.right == null) {
				Node leftChild = node.left;
				
				// Free memory.
				node.data = null;
				node = null;
				
				return leftChild;
				
			// Case with two subtrees.
			// The successor can either be:
			// 		- option 1: the largest value in the left subtree; or 
			// 		- option 2: the smallest value in the right subtree. (choosed)
			} else {
				// Option 2: the smallest value in the right subtree.
				Node smallest = findMin(node.right);
				node.data = smallest.data;
				
				// Go into the right subtree, remove the leftmost node found and
				// and swap data with. This prevents from having two nodes in
				// the tree with the same value.
				node.right = remove(node.right, smallest.data);
				
//				// Option 1: the largest value in the left subtree.
//				Node largest = findMax(node.left);
//				node.data = largest.data;
//				node.left = remove(node.left, largest.data);
			}
		}
		return node;
	}
	
	/**
	 * Helper method. Finds the leftmost node (which has the smallest value).
	 * 
	 * @param node	the root of the subtree
	 * @return		the leftmost node
	 */
	private Node findMin(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
//	/**
//	 * Helper method. Finds the rightmost node (which has the largest value).
//	 * 
//	 * @param node	the root of the subtree
//	 * @return		the rightmost node
//	 */
//	private Node findMax(Node node) {
//		while (node.right != null) {
//			node = node.right;
//		}
//		return node;
//	}
	
	/**
	 * Computes the height of the tree.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @return	the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	/**
	 * Computes the height of the tree.
	 * <p>
	 * Time complexity: O(n)
	 * 
	 * @param node	the root of the subtree
	 * @return		the height of the tree
	 */
	private int height(Node node) {
		if (node == null) {
			return 0;
		}
		return Math.max(height(node.left), height(node.right)) + 1;
	}
	
	/**
	 * Returns an iterator for a given TreeTraversalOrder.
	 * <p>
	 * The ways in which you can traverse the tree are in four different ways:
	 * prerder, inorder, postorder and levelorder.
	 * 
	 * @param order	the TreeTraversalOrder on which the iterator is based
	 * @return		the iterator
	 */
	public Iterator<T> traverse(TreeTraversalOrder order) {
		switch (order) {
			case PRE_ORDER:
				return preOrderTraversal();
			case IN_ORDER:
				return inOrderTraversal();
			case POST_ORDER:
				return postOrderTraversal();
			case LEVEL_ORDER:
				return levelOrderTraversal();
			default:
				return null;
		}
	}
	
	/**
	 * Creates an iterator to traverse the tree in preorder.
	 * 
	 * @return	the iterator
	 */
	private Iterator<T> preOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final Stack<Node> stack = new Stack<>();
		stack.push(root);
		
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount) {
					throw new ConcurrentModificationException();
				}
				return root != null && !stack.isEmpty();
			}
			
			@Override
			public T next() {
				if (expectedNodeCount != nodeCount) {
					throw new ConcurrentModificationException();
				}
				Node node = stack.pop();
				if (node.right != null) {
					stack.push(node.right);
				}
				if (node.left != null) {
					stack.push(node.left);
				}
				return node.data;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	/**
	 * Creates an iterator to traverse the tree in inorder.
	 * 
	 * @return	the iterator
	 */
	private Iterator<T> inOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final Stack<Node> stack = new Stack<>();
		stack.push(root);
		
		return new Iterator<T>() {
			Node trav = root;
			
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount) {
					throw new ConcurrentModificationException();
				}
				return root != null && !stack.isEmpty();
			}
			
			@Override
			public T next() {
				if (expectedNodeCount != nodeCount) {
					throw new ConcurrentModificationException();
				}
				
				// Dig left.
				while (trav != null && trav.left != null) {
					stack.push(trav.left);
					trav = trav.left;
				}
				
				Node node = stack.pop();
				
				// Try moving down right once.
				if (node.right != null) {
					stack.push(node.right);
					trav = node.right;
				}
				return node.data;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	/**
	 * Creates an iterator to traverse the tree in postorder.
	 * 
	 * @return	the iterator
	 */
	private Iterator<T> postOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final Stack<Node> stack1 = new Stack<>();
		final Stack<Node> stack2 = new Stack<>();
		stack1.push(root);
		
		while (!stack1.isEmpty()) {
			Node node = stack1.pop();
			if (node != null) {
				stack2.push(node);
				if (node.left != null) {
					stack1.push(node.left);
				}
				if (node.right != null) {
					stack1.push(node.right);
				}
			}
		}
		
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount) {
					throw new ConcurrentModificationException();
				}
				return root != null && !stack2.isEmpty();
			}
			
			@Override
			public T next() {
				if (expectedNodeCount != nodeCount) {
					throw new ConcurrentModificationException();
				}
				return stack2.pop().data;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	/**
	 * Creates an iterator to traverse the tree in level order.
	 * 
	 * @return	the iterator
	 */
	private Iterator<T> levelOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount) {
					throw new ConcurrentModificationException();
				}
				return root != null && !queue.isEmpty();
			}
			
			@Override
			public T next()  {
				if (expectedNodeCount != nodeCount) {
					throw new ConcurrentModificationException();
				}
				Node node = queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				return node.data;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
