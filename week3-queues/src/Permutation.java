import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
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
