import edu.princeton.cs.algs4.Merge;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException(
                "argument to BruteCollinearPoints constructor is null");

        Point[] pointsCopy = points.clone();
        Merge.sort(pointsCopy);
        if (hasDuplicates(pointsCopy))
            throw new IllegalArgumentException(
                    "argument to BruteCollinearPoints constructor has duplicates");

        for (int i = 0; i < pointsCopy.length - 3; i++) {
            Merge.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());

            for (int p = 0, first = 1, last = 2; last < pointsCopy.length; last++) {
                if (Double.compare(pointsCopy[p].slopeTo(pointsCopy[first]),
                                   pointsCopy[p].slopeTo(pointsCopy[last])) == 0) continue;

                if (last - first >= 3 && pointsCopy[p].compareTo(pointsCopy[first]) < 0) {
                    segments.add(new LineSegment(pointsCopy[p], pointsCopy[last - 1]));
                }

                first = last;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

    }

    private boolean hasDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) return true;
        }

        return false;
    }
}
