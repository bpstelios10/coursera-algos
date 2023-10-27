import edu.princeton.cs.algs4.Merge;

import java.util.ArrayList;

public class BruteCollinearPoints {

    private int numberOfSegments;
    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException(
                "argument to BruteCollinearPoints constructor is null");

        Point[] pointsCopy = points.clone();
        try {
            Merge.sort(pointsCopy);
        }
        catch (NullPointerException ex) {
            throw new IllegalArgumentException("Point is null");
        }
        if (hasDuplicates(pointsCopy))
            throw new IllegalArgumentException(
                    "argument to BruteCollinearPoints constructor has duplicates");

        for (int i = 0; i < pointsCopy.length; i++) {
            if (pointsCopy[i] == null) throw new IllegalArgumentException("point is null");

            for (int k = i + 1; k < pointsCopy.length; k++) {
                double candidateSlope = pointsCopy[i].slopeTo(pointsCopy[k]);

                for (int j = k + 1; j < pointsCopy.length; j++) {
                    if (pointsCopy[i].slopeTo(pointsCopy[j]) != candidateSlope) continue;
                    LineSegment line = null;

                    for (int m = j + 1; m < pointsCopy.length; m++) {
                        if (pointsCopy[i].slopeTo(pointsCopy[m]) == candidateSlope)
                            line = new LineSegment(pointsCopy[i], pointsCopy[m]);
                    }
                    if (line != null) {
                        segments.add(line);
                        numberOfSegments++;
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
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
