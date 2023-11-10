import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {

    private final TreeSet<Point2D> tree;

    // construct an empty set of points
    public PointSET() {
        tree = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    // number of points in the set
    public int size() {
        return tree.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument cant be null");

        tree.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument cant be null");

        return tree.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : tree) point.draw();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("argument cant be null");

        ArrayList<Point2D> containedPoints = new ArrayList<>();

        for (Point2D point : tree) {
            if (rect.contains(point)) containedPoints.add(point);
        }

        return containedPoints;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument cant be null");
        if (tree.isEmpty()) return null;

        Point2D nearestPoint = tree.first();

        for (Point2D point : tree) {
            if (nearestPoint.distanceSquaredTo(p) > point.distanceSquaredTo(p))
                nearestPoint = point;
        }

        return nearestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET pointSET = new PointSET();
        assertThat(pointSET.isEmpty());
        assertThat(pointSET.size() == 0);

        pointSET.insert(new Point2D(0.01, 0.04));
        assertThat(!pointSET.isEmpty());
        assertThat(pointSET.size() == 1);
        assertThat(pointSET.contains(new Point2D(0.01d, 0.04d)));
    }

    private static void assertThat(boolean assertion) {
        if (!assertion) throw new RuntimeException("Assertion failed");
    }
}
