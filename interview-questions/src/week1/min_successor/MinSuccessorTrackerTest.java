package week1.min_successor;

import edu.princeton.cs.algs4.StdOut;

import static week1.testing.Assertions.assertThat;
import static week1.testing.Assertions.assertThatThrows;

public class MinSuccessorTrackerTest {

    public static void main(String[] args) {
        StdOut.println("Start running unit tests for class");

        MinSuccessorTrackerTest tests = new MinSuccessorTrackerTest();

        tests.validations_throwException_whenInvalidInputs();
        tests.size_succeeds();
        tests.successor_succeeds();
        tests.delete_succeeds();
        tests.successorAndDelete_basicSequence_succeeds();
        tests.successorAndDelete_deleteBackwards_succeeds();
        tests.successorAndDelete_deleteMixed_succeeds();

        StdOut.println("End of unit tests with no failures");
    }

    private void validations_throwException_whenInvalidInputs() {
        assertThatThrows(() -> new MinSuccessorTracker(-1), IllegalArgumentException.class);

        MinSuccessorTracker tracker = new MinSuccessorTracker(5);

        assertThatThrows(() -> tracker.findSuccessor(-1), IllegalArgumentException.class,
                         "index -1 is not between 0 and 4");
        assertThatThrows(() -> tracker.findSuccessor(5), IllegalArgumentException.class,
                         "index 5 is not between 0 and 4");

        assertThatThrows(() -> tracker.delete(-1), IllegalArgumentException.class,
                         "index -1 is not between 0 and 4");
        assertThatThrows(() -> tracker.delete(5), IllegalArgumentException.class,
                         "index 5 is not between 0 and 4");
    }

    private void size_succeeds() {
        MinSuccessorTracker tracker = new MinSuccessorTracker(5);
        assertThat(tracker.size() == 5);

        MinSuccessorTracker tracker1 = new MinSuccessorTracker(0);
        assertThat(tracker1.size() == 0);
    }

    private void successor_succeeds() {
        MinSuccessorTracker tracker = new MinSuccessorTracker(5);

        assertThat(tracker.findSuccessor(0) == 1);
        assertThat(tracker.findSuccessor(1) == 2);
        assertThat(tracker.findSuccessor(2) == 3);
        assertThat(tracker.findSuccessor(3) == 4);
        assertThat(tracker.findSuccessor(4) == -1);
    }

    private void delete_succeeds() {
        MinSuccessorTracker tracker = new MinSuccessorTracker(5);

        assertThat(tracker.findSuccessor(2) == 3);
        tracker.delete(3);
        assertThat(tracker.findSuccessor(2) == 4);
    }

    private void successorAndDelete_basicSequence_succeeds() {
        MinSuccessorTracker tracker = new MinSuccessorTracker(5);

        assertThat(tracker.findSuccessor(0) == 1);
        assertThat(tracker.findSuccessor(1) == 2);
        tracker.delete(1);
        assertThat(tracker.findSuccessor(0) == 2);
        assertThat(tracker.findSuccessor(1) == 2);
        tracker.delete(2);
        assertThat(tracker.findSuccessor(0) == 3);
        assertThat(tracker.findSuccessor(1) == 3);
        tracker.delete(3);
        assertThat(tracker.findSuccessor(0) == 4);
        tracker.delete(4);
        assertThat(tracker.findSuccessor(0) == -1);
        tracker.delete(0);
        assertThat(tracker.findSuccessor(0) == -1);
        assertThat(tracker.findSuccessor(1) == -1);
        assertThat(tracker.findSuccessor(2) == -1);
        assertThat(tracker.findSuccessor(3) == -1);
        assertThat(tracker.findSuccessor(4) == -1);
    }

    private void successorAndDelete_deleteBackwards_succeeds() {
        MinSuccessorTracker tracker = new MinSuccessorTracker(5);

        assertThat(tracker.findSuccessor(0) == 1);
        assertThat(tracker.findSuccessor(3) == 4);
        tracker.delete(4);
        assertThat(tracker.findSuccessor(0) == 1);
        assertThat(tracker.findSuccessor(2) == 3);
        assertThat(tracker.findSuccessor(3) == -1);
        tracker.delete(3);
        assertThat(tracker.findSuccessor(0) == 1);
        assertThat(tracker.findSuccessor(2) == -1);
        assertThat(tracker.findSuccessor(3) == -1);
        tracker.delete(2);
        assertThat(tracker.findSuccessor(0) == 1);
        assertThat(tracker.findSuccessor(3) == -1);
        tracker.delete(1);
        assertThat(tracker.findSuccessor(0) == -1);
        assertThat(tracker.findSuccessor(1) == -1);
        assertThat(tracker.findSuccessor(2) == -1);
        assertThat(tracker.findSuccessor(3) == -1);
        assertThat(tracker.findSuccessor(4) == -1);
        tracker.delete(0);
        assertThat(tracker.findSuccessor(0) == -1);
        assertThat(tracker.findSuccessor(1) == -1);
        assertThat(tracker.findSuccessor(2) == -1);
        assertThat(tracker.findSuccessor(3) == -1);
        assertThat(tracker.findSuccessor(4) == -1);
    }

    private void successorAndDelete_deleteMixed_succeeds() {
        MinSuccessorTracker tracker = new MinSuccessorTracker(5);

        assertThat(tracker.findSuccessor(0) == 1);
        assertThat(tracker.findSuccessor(1) == 2);
        assertThat(tracker.findSuccessor(2) == 3);
        assertThat(tracker.findSuccessor(3) == 4);
        assertThat(tracker.findSuccessor(4) == -1);

        tracker.delete(2);
        assertThat(tracker.findSuccessor(0) == 1);
        assertThat(tracker.findSuccessor(1) == 3);
        assertThat(tracker.findSuccessor(2) == 3);
        tracker.delete(1);
        assertThat(tracker.findSuccessor(0) == 3);
        assertThat(tracker.findSuccessor(1) == 3);
        tracker.delete(3);
        assertThat(tracker.findSuccessor(0) == 4);
        assertThat(tracker.findSuccessor(1) == 4);
        assertThat(tracker.findSuccessor(2) == 4);
        assertThat(tracker.findSuccessor(3) == 4);
        assertThat(tracker.findSuccessor(4) == -1);
    }
}
