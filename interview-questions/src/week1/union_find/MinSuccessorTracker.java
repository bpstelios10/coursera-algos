package week1.union_find;

/**
 * The hint says that we need to use the previous question structure (ExtendedUF). based on
 * that, we can create an ExtendedUF and add a link for each deleted element with its next.
 * Then, for every successor search, we call find method of ExtendedUF
 */
public class MinSuccessorTracker {

    private final ExtendedUF deletedElementsUnion;
    private int size;
    private boolean isLastElementDeleted = false;

    /**
     * Initializes an empty custom data structure with
     * {@code n} elements {@code 0} through {@code n-1}.
     *
     * @param n the number of elements
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public MinSuccessorTracker(int n) {
        if (n < 0) throw new IllegalArgumentException();

        size = n;
        deletedElementsUnion = new ExtendedUF(n);
    }

    /**
     * Size of the structure
     *
     * @return the total number of elements in structure.
     */
    public int size() {
        return size;
    }

    /**
     * Method that finds the next successor of the given element.
     * {@code p} element for which the min successor is requested.
     *
     * @return minimum successor of the element p.
     */
    public int findSuccessor(int p) {
        validate(p);
        if (p == size - 1) return -1;

        int successor = deletedElementsUnion.find(p + 1);
        // System.out.println(successor);

        return successor == size - 1 && isLastElementDeleted ? -1 : successor;
    }

    /**
     * Deletes an element from the structure.
     */
    public void delete(int p) {
        validate(p);
        if (p == size - 1) {
            isLastElementDeleted = true;
            return;
        }
        // if (deletedElementsUnion.find(p) == p) return;

        deletedElementsUnion.union(p, p + 1);
    }

    // validate that p is a valid index
    private void validate(int p) {
        if (p < 0 || p >= size) {
            throw new IllegalArgumentException(
                    "index " + p + " is not between 0 and " + (size - 1));
        }
    }
}
