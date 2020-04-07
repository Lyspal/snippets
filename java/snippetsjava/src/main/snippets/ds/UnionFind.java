package main.snippets.ds;

/**
 * Union-find / disjoint set data structure.
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 */
public class UnionFind {

	private int size;			// The number of elements in this union-find
	private int numComponents;	// The number of components in the union-find
	
	// Used to track the size of each component.
	private int[] componentSizes;
	
	// id[i] points to the parent of i; if id[i] = i, then i is a root node.
	private int[] ids;
	
	/**
	 * Constructor.
	 * 
	 * @param size	the size of the union-find
	 * @throws IllegalArgumentException
	 */
	public UnionFind(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size <= 0 is not allowed");
		}
		
		this.size = numComponents = size;
		componentSizes = new int[size];
		ids = new int[size];
		
		for (int i = 0; i < size; i++) {
			ids[i] = i;				// Link to itself (self root)
			componentSizes[i] = 1;	// Each component is originally of size 1
		}
	}
	
	/**
	 * Finds which component/set an element belongs to.
	 * <p>
	 * Time complexity: amortized constant time, alpha(n)
	 * 
	 * @param element	the element for which we search the group
	 * @return			the index of the component
	 */
	public int find(int element) {
		// Find the root of the component/set.
		int root = element;
		while (root != ids[root]) {
			root = ids[root];
		}
		
		// Compress the path leading back to the root.
		// (aka path compression, which gives us amortized time complexity).
		while (element != root) {
			int next = ids[element];
			ids[element] = root;
			element = next;
		}
		
		return root;
	}
	
//	/**
//	 * Alternative recursive formulation for the find method.
//	 * 
//	 * @param element	the element for which we search the group
//	 * @return			the index of the component
//	 */
//	public int find(int element) {
//		if (element == ids[element]) {
//			return element;
//		}
//		return ids[element] = find(ids[element]);
//	}
	
	/**
	 * Unify the components/sets containing elements element1 and element2.
	 * 
	 * @param element1	the element of the first component/set
	 * @param element2	the element of the second component/set
	 */
	public void unify(int element1, int element2) {
		int root1 = find(element1);
		int root2 = find(element2);
		
		// If the elements are in the same group.
		if (root1 == root2) {
			return;
		}
		
		// Merge smaller component/set into the larger one.
		if (componentSizes[root1] < componentSizes[root2]) {
			componentSizes[root2] += componentSizes[root1];
			ids[root1] = root2;
		} else {
			componentSizes[root1] += componentSizes[root2];
			ids[root2] = root1;
		}
		
		numComponents--;
	}
	
	/**
	 * Checks if two elements are in the same component/set.
	 * 
	 * @param element1	the first element
	 * @param element2	the second element
	 * @return			true if they belong to the same element; false otherwise
	 */
	public boolean connected(int element1, int element2) {
		return find(element1) ==find(element2);
	}
	
	/**
	 * Getter. Gets the size of the component/set in which element belongs to.
	 * 
	 * @param element	the element of the component/set of which we want the size
	 * @return			the size of the component/set
	 */
	public int componentSize(int element) {
		return componentSizes[find(element)];
	}
	
	/**
	 * Getter. Gets the size of the union find.
	 * 
	 * @return	the size of the union find
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Getter. Gets the number of components/sets in the union-find.
	 * 
	 * @return	the number of components/sets
	 */
	public int components() {
		return numComponents;
	}
	
}
