package snippets.algo.search;

/**
 * Implementation of binary search.
 * Time complexitty: O(log(n))
 * 
 * @author sylvainlaporte
 * @version %I%, %G%
 * 
 */
public class BinarySearch {

	/**
	 * Finds an element in an array of integers.
	 * 
	 * @param array	the array in which to search
	 * @param value	the value to search
	 * @return		the index of the first found value; -1 if not found
	 */
	public static int binarySearch(int[] array, int value) {
		int low = 0;
		int high = array.length;
		
		while (low <= high) {
			int mid = (low + high) / 2;
			if (array[mid] == value) {
				return mid;
			} else if (array[mid] < value) {
				low = mid + 1;
			} else if (array[mid] > value) {
				high = mid - 1;
			}
		}
		return -1;	// Value not found
	}
	
}
