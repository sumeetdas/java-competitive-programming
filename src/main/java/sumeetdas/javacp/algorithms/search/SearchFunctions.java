package sumeetdas.javacp.algorithms.search;

import java.util.Random;

public class SearchFunctions {
    private SearchFunctions() {}

    /**
     * Lower bound search algorithm.
     * 
     * Lower bound is kind of binary search algorithm but:
     * -If searched element doesn't exist function returns index of first element which is bigger than searched value.
     * -If searched element is bigger than any array element function returns first index after last element.
     * -If searched element is lower than any array element function returns index of first element.
     * -If there are many values equals searched value function returns first occurrence.
     * 
     * Behaviour for unsorted arrays is unspecified.
     * Complexity O(log n).
     */
    public static int lowerBound(int[] array, int length, int value) {
        int low = 0;
        int high = length;
        while (low < high) {
            final int mid = (low + high) / 2;
            //checks if the value is less than middle element of the array
            if (value <= array[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Upper bound search algorithm.
     * 
     * Upper bound is kind of binary search algorithm but:
     * -It returns index of first element which is grater than searched value.
     * -If searched element is bigger than any array element function returns first index after last element.
     * 
     * Behaviour for unsorted arrays is unspecified.
     * Complexity O(log n).
     */
    public static int upperBound(int[] array, int length, int value) {
        int low = 0;
        int high = length;
        while (low < high) {
            final int mid = (low + high) / 2;
            if (value >= array[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     * Linear search in unsorted arrays.
     */
    public static final int unsortedFind(int value, int[] array) {
        for (var i = 0; i < array.length; i++) {
            var iValue = array[i];
            if (value == iValue) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static final Random RANDOM = new Random();

    /**
     * quickselect is a selection algorithm to find the k-th smallest element in an unordered list. 
     * It is related to the quicksort sorting algorithm.
     * Worst-case performance  О(n2)
     * Best-case performance   О(n)
     * Average performance     O(n)
     */
    public static final int quickSelect(int value, int[] array) {
        var unsorted = array;
        var temp = new int[unsorted.length];
        var tempLength = unsorted.length;
        var length = tempLength;
        var pivot = unsorted[0];
        while (length > 0) {
            length = tempLength;
            pivot = unsorted[RANDOM.nextInt(length)];
            tempLength = 0;
            for (int i = 0; i < length; i++) {
                int iValue = unsorted[i];
                if (value == iValue)
                    return i;
                else if (value > pivot && iValue > pivot)
                    temp[tempLength++] = iValue;
                else if (value < pivot && iValue < pivot)
                    temp[tempLength++] = iValue;
            }
            unsorted = temp;
            length = tempLength;
        }
        return Integer.MAX_VALUE;
    }
}
