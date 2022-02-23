package bearmaps;

import java.util.HashSet;
import java.util.List;

public class NaivePointSet implements PointSet{
    private HashSet<Point> set;

    public NaivePointSet(List<Point> points) {
        set = new HashSet<>(points);
    }

    @Override
    public Point nearest(double x, double y) {
        Point pnt = new Point(x, y);
        Point ret = null;
        double min_dis = Double.MAX_VALUE;
        for(Point p : set) {
            double dis = Point.distance(p, pnt);
            if(dis < min_dis) {
                min_dis = dis;
                ret = p;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        //ret.getX(); // evaluates to 3.3
        //ret.getY(); // evaluates to 4.4
        System.out.println(ret);
    }
}
