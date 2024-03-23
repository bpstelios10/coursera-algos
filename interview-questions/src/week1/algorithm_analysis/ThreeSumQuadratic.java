package week1.algorithm_analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Problem: Given N distinct integers, how many triples sum to exactly zero?
 */
public class ThreeSumQuadratic {

    private final List<Integer> elements;

    public ThreeSumQuadratic(List<Integer> elements) {
        this.elements = new ArrayList<>(elements);
    }

    public int calculateHowManyTripletsSumToZero() {
        int result = 0;
        Collections.sort(elements);

        if (elementsAreAllPositiveOrAllNegative(elements)) return 0;

        for (int i = 0; i < elements.size() - 2; i++) {
            Integer element1 = elements.get(i);
            // System.out.println("element1: " + element1);
            if (element1 > 0) break;

            for (int j = i + 1; j < elements.size() - 1; j++) {
                Integer element2 = elements.get(j);
                // System.out.println("\telement2: " + element2);
                if (Math.abs(element1 + element2) < elements.get(j + 1)) break;

                int searchKeyOfSum = iteratorBinarySearch(elements, -element1 - element2, j+1);

                if (searchKeyOfSum > 0) {
                    do {
                        result++;
                        searchKeyOfSum = iteratorBinarySearch(elements, -(element1 + element2), searchKeyOfSum + 1);
                    } while (searchKeyOfSum > 0);

                }
            }
        }

        // System.out.println(result);
        return result;
    }

    private boolean elementsAreAllPositiveOrAllNegative(List<Integer> elements) {
        if (elements.get(0) > 0 || elements.get(elements.size() - 1) < 0) return true;

        return false;
    }

    //This could/should be a class on its own but for simplicity:
    /**
     * This is a custom implementation of the Collections.binarySearch that handles duplicate keys.
     * One more improvement for our case is that we dont need to search for matches if the index is
     * lower than a specific boundary.
     * @param list of Integer elements to search in. IMPORTANT, this list needs to be sorted!
     * @param key the value we are looking for in the list
     * @param lowBoundaryIndex the index after which we are looking for the key.
     *
     * @return negative if not found or else the index of the key found
     */
    private int iteratorBinarySearch(List<Integer> list, int key, int lowBoundaryIndex) {
        int low = lowBoundaryIndex;
        int high = list.size() - 1;
        ListIterator<Integer> i = list.listIterator();

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<Integer> midVal = get(i, mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found
    }

    private Comparable<Integer> get(ListIterator<Integer> i, int mid) {
        int result;
        int pos = i.nextIndex();
        if (pos <= mid) {
            do {
                result = i.next();
            } while (pos++ < mid);
        } else {
            do {
                result = i.previous();
            } while (--pos > mid);
        }

        return result;
    }
}
