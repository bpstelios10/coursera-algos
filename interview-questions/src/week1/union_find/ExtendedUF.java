package week1.union_find;

public class ExtendedUF {

    private int[] parent;   // parent[i] = parent of i
    private byte[] rank;    // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;      // number of components
    private int[] max;      // max of connected elements

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code n-1}.
     * Initially, each element is in its own set.
     *
     * @param n the number of elements
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public ExtendedUF(int n) {
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new byte[n];
        max = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            max[i] = i;
        }
    }

    /**
     * Returns the max element of the set containing element {@code p}.
     *
     * @param p an element
     * @return the max element of the set containing {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        return max[findRoot(p)];
    }

    /**
     * Returns the number of sets.
     *
     * @return the number of sets (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if the two elements are in the same set.
     *
     * @param p one element
     * @param q the other element
     * @return {@code true} if {@code p} and {@code q} are in the same set;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     * @deprecated Replace with two calls to {@link #find(int)}.
     */
    @Deprecated
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    /**
     * Merges the set containing element {@code p} with the set
     * containing element {@code q}.
     *
     * @param p one element
     * @param q the other element
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) return;
        int maxP = max[findRoot(p)];
        int maxQ = max[findRoot(q)];

        // make root of smaller rank point to root of larger rank
        if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;

        max[findRoot(p)] = maxP > maxQ ? maxP : maxQ;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    private int findRoot(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }
}
