public class WeightedUFWithMax {
    private final int[] id;
    private final int[] iz;
    private final int[] im;

    public WeightedUFWithMax(int N) {
        id = new int[N];
        iz = new int[N];
        im = new int[N];
        for (int i = 0; i < id.length; i++) {
            iz[i] = i;
            id[i] = i;
            im[i] = i;
        }
    }

    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // we flatten the height of tree
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        int maxOfUnion = Math.max(find(i), find(j));

        if (iz[i] < iz[j]) {
            id[i] = j;
            iz[j] += iz[i];
        }
        else {
            id[j] = i;
            iz[i] += iz[j];
        }

        im[root(p)] = maxOfUnion;
    }

    public int find(int p) {
        return im[root(p)];
    }
}
