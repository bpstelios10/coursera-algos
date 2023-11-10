import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class KdTree {

    private Node root;
    private int size;

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument cant be null");
        if (size == 0) {
            root = new Node(null, null, false, p);
            size++;
            return;
        }

        insert(root, p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument cant be null");

        if (root == null) return false;

        return contains(root, p);
    }

    // draw all points to standard draw
    public void draw() {
        draw(root);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("argument cant be null");

        ArrayList<Point2D> containedPoints = new ArrayList<>();

        if (root != null) getRectPoints(root, rect, containedPoints);

        return containedPoints;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("argument cant be null");
        if (isEmpty()) return null;

        return new NearestSearch(p, root).champion();
        // return getMinimumDistance(root, p, root.value);
    }


    // unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        assertThat(kdTree.isEmpty());
        assertThat(kdTree.size() == 0);

        kdTree.insert(new Point2D(0.01, 0.04));
        assertThat(!kdTree.isEmpty());
        assertThat(kdTree.size() == 1);
        assertThat(kdTree.contains(new Point2D(0.01d, 0.04d)));
        kdTree.insert(new Point2D(0.02, 0.04));
        kdTree.insert(new Point2D(0.01, 0.05));
        assertThat(kdTree.size() == 3);
        assertThat(kdTree.contains(new Point2D(0.02d, 0.04d)));
        assertThat(kdTree.contains(new Point2D(0.01, 0.05)));
        assertThat(!kdTree.contains(new Point2D(0.02, 0.05)));
        assertThat(!kdTree.contains(new Point2D(0.01, 0.03)));
    }

    private void insert(Node current, Point2D newPoint) {
        if (current.compareToPoint2D(newPoint) == 0) return;
        if (current.compareToPoint2D(newPoint) < 0) {
            if (current.right == null) {
                current.right = new Node(null, null, !current.isEven, newPoint);
                size++;
            }
            else insert(current.right, newPoint);
        }
        else {
            if (current.left == null) {
                current.left = new Node(null, null, !current.isEven, newPoint);
                size++;
            }
            else insert(current.left, newPoint);
        }
    }

    private boolean contains(Node currentNode, Point2D searchPoint) {
        if (currentNode.compareToPoint2D(searchPoint) == 0) return true;
        if (currentNode.compareToPoint2D(searchPoint) < 0) {
            if (currentNode.right == null) return false;
            else return contains(currentNode.right, searchPoint);
        }
        else {
            if (currentNode.left == null) return false;
            else return contains(currentNode.left, searchPoint);
        }
    }

    private void draw(Node node) {
        if (node == null) return;

        node.value.draw();
        draw(node.left);
        draw(node.right);
    }

    private void getRectPoints(Node currentNode, RectHV rect, ArrayList<Point2D> containedPoints) {
        if (currentNode.value.x() <= rect.xmax() && currentNode.value.x() >= rect.xmin()
                && currentNode.value.y() <= rect.ymax() && currentNode.value.y() >= rect.ymin())
            containedPoints.add(currentNode.value);

        if (currentNode.compareToRectHV(rect) <= 0) {
            Node nextHigherNode = currentNode.right;

            if (nextHigherNode != null) getRectPoints(nextHigherNode, rect, containedPoints);
        }
        if (currentNode.compareToRectHV(rect) >= 0) {
            Node nextLowerPoint = currentNode.left;

            if (nextLowerPoint != null) getRectPoints(nextLowerPoint, rect, containedPoints);
        }
    }

    private static void assertThat(boolean assertion) {
        if (!assertion) throw new RuntimeException("Assertion failed");
    }

    private class Node {
        Node left;
        Node right;
        boolean isEven;
        Point2D value;

        public Node(Node left, Node right, boolean b, Point2D p) {
            this.left = left;
            this.right = right;
            this.isEven = b;
            this.value = p;
        }

        public int compareToPoint2D(Point2D that) {
            if (this.value.equals(that)) return 0;
            if (this.isEven) {
                return Double.compare(this.value.y(), that.y()) < 0 ? -1 : 1;
            }
            else {
                return Double.compare(this.value.x(), that.x()) < 0 ? -1 : 1;
            }
        }

        public int compareToRectHV(RectHV rect) {
            if (this.isEven) {
                if (this.value.y() <= rect.ymax() && this.value.y() >= rect.ymin()) return 0;
                else return this.value.y() < rect.ymin() ? -1 : 1;
            }
            else {
                if (this.value.x() <= rect.xmax() && this.value.x() >= rect.xmin()) return 0;
                else return this.value.x() < rect.xmin() ? -1 : 1;
            }
        }
    }

    private class NearestSearch {
        private Point2D point;
        private Point2D champion;
        private double distance;

        public NearestSearch(Point2D p, Node node) {
            point = p;
            distance = Double.POSITIVE_INFINITY;
            search(node);
        }

        private void search(Node node) {
            if (node == null)
                return;

            RectHV square = zoom();
            if (square.contains(node.value)) {
                double value = node.value.distanceSquaredTo(point);
                if (value < distance * distance) {
                    distance = Math.sqrt(value);
                    champion = node.value;
                    square = zoom();
                }
            }

            int cmp = node.compareToRectHV(square);
            if (cmp > 0)
                search(node.left);
            else if (cmp < 0)
                search(node.right);
            else {
                boolean goLeft = node.compareToPoint2D(point) < 0;
                if (goLeft)
                    search(node.left);
                else
                    search(node.right);

                square = zoom();
                cmp = node.compareToRectHV(square);
                if (cmp == 0) {
                    if (!goLeft)
                        search(node.left);
                    else
                        search(node.right);
                }
            }
        }

        private RectHV zoom() {
            return new RectHV(point.x() - distance, point.y() - distance, point.x() + distance,
                              point.y() + distance);
        }

        public Point2D champion() {
            return champion;
        }
    }
}
