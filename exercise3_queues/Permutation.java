package exercise3_queues;/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (randomizedQueue.size() == k && k > 0) randomizedQueue.dequeue();
            randomizedQueue.enqueue(item);
        }

        for (String s : randomizedQueue) {
            StdOut.println(s);
        }
    }
}
