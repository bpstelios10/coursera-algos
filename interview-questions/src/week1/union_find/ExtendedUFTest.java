package week1.union_find;

import edu.princeton.cs.algs4.StdOut;

import static testing.Assertions.assertThat;
import static testing.Assertions.assertThatThrows;

public class ExtendedUFTest {

    public static void main(String[] args) {
        StdOut.println("Start running unit tests for class");

        ExtendedUFTest tests = new ExtendedUFTest();
        tests.testFind_returnsMax();
        tests.findMax_throwsException_whenInvalidElements();
        tests.constructor_throwsException_whenInvalidElements();
        // more tests for validation

        StdOut.println("End of unit tests with no failures");
    }

    private void testFind_returnsMax() {
        ExtendedUF extendedUnionFind = new ExtendedUF(10);
        assertThat(extendedUnionFind.find(8) == 8);

        extendedUnionFind.union(1, 2);
        assertThat(extendedUnionFind.find(1) == 2);
        extendedUnionFind.union(3, 4);
        assertThat(extendedUnionFind.find(3) == 4);
        extendedUnionFind.union(5, 6);
        assertThat(extendedUnionFind.find(5) == 6);
        extendedUnionFind.union(6, 1);
        assertThat(extendedUnionFind.find(1) == 6);
        assertThat(extendedUnionFind.find(2) == 6);
        assertThat(extendedUnionFind.find(5) == 6);
        assertThat(extendedUnionFind.find(6) == 6);
        extendedUnionFind.union(9, 7);
        assertThat(extendedUnionFind.find(7) == 9);
        assertThat(extendedUnionFind.find(9) == 9);
        extendedUnionFind.union(3, 7);
        assertThat(extendedUnionFind.find(3) == 9);
        assertThat(extendedUnionFind.find(4) == 9);
        assertThat(extendedUnionFind.find(7) == 9);
        assertThat(extendedUnionFind.find(9) == 9);

        assertThat(extendedUnionFind.find(8) == 8);
    }

    private void findMax_throwsException_whenInvalidElements() {
        ExtendedUF uf = new ExtendedUF(10);
        assertThatThrows(() -> uf.find(-1), IllegalArgumentException.class,
                         "index -1 is not between 0 and 9");

    }

    private void constructor_throwsException_whenInvalidElements() {
        assertThatThrows(() -> new ExtendedUF(-1), IllegalArgumentException.class);
    }
}
