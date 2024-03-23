package week1.algorithm_analysis;

import edu.princeton.cs.algs4.StdOut;

import java.util.List;

import static testing.Assertions.assertThat;

public class ThreeSumQuadraticTest {

    public static void main(String[] args) {
        StdOut.println("Start running unit tests for class");

        ThreeSumQuadraticTest tests = new ThreeSumQuadraticTest();

        tests.whenAllPositives_thenZeroResult();
        tests.whenAllNegatives_thenZeroResult();
        tests.givenTwoNonBothPositiveElements_whenSumSmallerThanNextElement_thenSkip();
        tests.whenOneResultExists_thenItShouldNotCountedMultipleTimes();
        tests.whenDuplicateKeys_thenResultsExist();
        tests.whenAllZeros_thenResultsExist();
        tests.whenEdgeCase_thenResultsExist();
        tests.whenAverageCase_thenCorrectResult();

        StdOut.println("End of unit tests with no failures");
    }

    private void whenAllPositives_thenZeroResult() {
        ThreeSumQuadratic underTest = new ThreeSumQuadratic(List.of(30, 40, 10, 5));

        int result = underTest.calculateHowManyTripletsSumToZero();

        assertThat(result == 0);
    }

    private void whenAllNegatives_thenZeroResult() {
        ThreeSumQuadratic underTest = new ThreeSumQuadratic(List.of(-30, -40, -10, -5));

        int result = underTest.calculateHowManyTripletsSumToZero();

        assertThat(result == 0);
    }

    private void givenTwoNonBothPositiveElements_whenSumSmallerThanNextElement_thenSkip() {
        ThreeSumQuadratic underTest = new ThreeSumQuadratic(List.of(-1, 4, 5, 8));

        int result = underTest.calculateHowManyTripletsSumToZero();

        assertThat(result == 0);
    }

    private void whenOneResultExists_thenItShouldNotCountedMultipleTimes() {
        ThreeSumQuadratic underTest = new ThreeSumQuadratic(List.of(-1, -4, 5, 8));

        int result = underTest.calculateHowManyTripletsSumToZero();

        assertThat(result == 1);
    }

    private void whenAllZeros_thenResultsExist() {
        ThreeSumQuadratic underTest = new ThreeSumQuadratic(List.of(0, 0, 0, 0));

        int result = underTest.calculateHowManyTripletsSumToZero();

        assertThat(result == 4);
    }

    private void whenDuplicateKeys_thenResultsExist() {
        ThreeSumQuadratic underTest = new ThreeSumQuadratic(List.of(-1, 0, 1, 1));

        int result = underTest.calculateHowManyTripletsSumToZero();

        assertThat(result == 2);
    }

    private void whenEdgeCase_thenResultsExist() {
        ThreeSumQuadratic underTest = new ThreeSumQuadratic(List.of(-2, -1, 0, 1, 2));

        int result = underTest.calculateHowManyTripletsSumToZero();

        assertThat(result == 2);
    }

    private void whenAverageCase_thenCorrectResult() {
        ThreeSumQuadratic underTest = new ThreeSumQuadratic(
                List.of(30, -40, -20, -10, 40, 0, 10, 5));

        int result = underTest.calculateHowManyTripletsSumToZero();

        assertThat(result == 4);
    }
}
