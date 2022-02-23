package bearmaps;

import java.util.List;

public class KDTree implements PointSet{
    private Node root = null;
    private static final boolean splitByX = true;

    public KDTree(List<Point> points) {
        for(Point p : points) {
            addNode(p);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        return nearest(root, target, root.point);
    }

    private Point nearest(Node node, Point target, Point best) {
        if (node == null) {
            return best;
        }

        double bestDist = Point.distance(best, target);
        double currDist = Point.distance(node.point, target);
        if (Double.compare(currDist, bestDist) < 0) {
            best = node.point;
        }

        Node goodSideNode;
        Node badSideNode;
        int cmp = comparePoints(target, node.point, node.splitDim);
        if (cmp < 0) {
            goodSideNode = node.left;
            badSideNode = node.right;
        } else {
            goodSideNode = node.right;
            badSideNode = node.left;
        }

        best = nearest(goodSideNode, target, best);
        if (isWorthLooking(node, target, best)) {
            best = nearest(badSideNode, target, best);
        }

        return best;
    }

    private boolean isWorthLooking(Node node, Point target, Point best) {
        double distToBest = Point.distance(best, target);
        double distToBad;
        if (node.splitDim == splitByX) {
            distToBad = Point.distance(new Point(node.point.getX(), target.getY()), target);
        } else {
            distToBad = Point.distance(new Point(target.getX(), node.point.getY()), target);
        }
        return Double.compare(distToBad, distToBest) < 0;
    }

    public void addNode(Point p) {
        root = addNode(root, p, splitByX);
    }

    private Node addNode(Node x, Point p, boolean splitDim) {
        if(x == null) {
            return new Node(p, splitDim);
        }
        if(p.equals(x.point)) {
            return x;
        }
        int cmp = comparePoints(x.point, p, splitDim);
        if(cmp > 0) {
            x.left = addNode(x.left, p, !splitDim);
        } else {
            x.right = addNode(x.right, p, !splitDim);
        }
        return x;
    }

    private int comparePoints(Point a, Point b, boolean splitByX) {
        if (splitByX) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    private class Node {
        private Point point;
        private Node left,right;
        private boolean splitDim;

        public Node(Point p, boolean splitDim) {
            this.point = p;
            this.splitDim = splitDim;
            left = null;
            right = null;
        }
    }
}
