package exercise1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        int totalWords = 0;
        String currentChampion = "";

        while (!StdIn.isEmpty()) {
            String candidateChampion = StdIn.readString();
            totalWords++;
            if (StdRandom.bernoulli((double) 1 / totalWords)) currentChampion = candidateChampion;
        }

        StdOut.println(currentChampion);
    }
}
