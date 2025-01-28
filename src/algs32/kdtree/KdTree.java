package algs32.kdtree;
import algs12.Point2D;
import algs13.Queue;
import stdlib.*;

/* a set of points implemented as kd-tree */
public class KdTree {

  private static class Node {
    // TODO
  }
  private Node root;

  /* construct an empty set of points */
  public KdTree() {
    // TODO -- maybe nothing to do here... in which case you can remove this and use the default constructor
  }

  /* is the set empty? */
  public boolean isEmpty() {
    // TODO
    return false;
  }

  /* add the point p to the set (if it is not already in the set) */
  public void insert(Point2D p) {
    // TODO
  }

  /* does the set contain the point p? */
  public boolean contains(Point2D target) {
    // TODO
    return false;
  }

  /* all points in the set that are inside the target rectangle */
  public Iterable<Point2D> range(RectHV target) {
    // TODO
    return new Queue<>();
  }

  /* a nearest neighbor to target point; null if empty */
  public Point2D nearest(Point2D target) {
    // TODO
    return new Point2D (0, 0);
  }

  /* draw all of the points to standard draw */
  /* for x-node, use red line to draw the division between left/right */
  /* for y-node, use blue line to draw the division between top/bottom */
  /* see the writeup for examples */
  public void draw() {
    // TODO
  }
  


  /* some test code */
  public void toGraphviz() { GraphvizBuilder.nodesToFile (root); }
  private static void insert5Points (KdTree kdtree, double xoffset, double yoffset) {
    kdtree.insert (new Point2D (0.7 + xoffset, 0.2 + yoffset));
    kdtree.insert (new Point2D (0.5 + xoffset, 0.4 + yoffset));
    kdtree.insert (new Point2D (0.2 + xoffset, 0.3 + yoffset));
    kdtree.insert (new Point2D (0.4 + xoffset, 0.7 + yoffset));
    kdtree.insert (new Point2D (0.9 + xoffset, 0.6 + yoffset));
  }
  public static void main (String[] args) {
    KdTree kdtree = new KdTree ();
    insert5Points (kdtree, 0.0, 0.0); // after this there should be 5 points
    insert5Points (kdtree, 0.0, 0.0); // after doing it twice there should still be 5
    insert5Points (kdtree, 0.1, 0.0); // this should add 5 more points
    insert5Points (kdtree, 0.0, 0.1); // this should add 5 more points


    //        kdtree.insert (new Point2D(0.15, 0.18));
    //        kdtree.insert (new Point2D(0.86, 0.26));
    //        kdtree.insert (new Point2D(0.70, 0.11));
    //        kdtree.insert (new Point2D(0.16, 0.01));
    //        kdtree.insert (new Point2D(0.62, 0.95));
    //        kdtree.insert (new Point2D(0.98, 0.04));
    //        kdtree.insert (new Point2D(0.87, 0.79));
    //        kdtree.insert (new Point2D(0.83, 0.21));

    //        Point2D mid = new Point2D (0.5, 0.5);
    //        Point2D less = new Point2D (0.5, 0.4);
    //        Point2D more = new Point2D (0.5, 0.6);
    //        kdtree.insert (mid);
    //        kdtree.insert (less);
    //        kdtree.insert (more);
    //        StdOut.println (kdtree.contains (less));
    //        StdOut.println (kdtree.contains (more));
    //        StdOut.println (kdtree.range (new RectHV (0.5, 0.4, 0.5, 0.6)));
    //        StdOut.println (kdtree.nearest (less));
    //        StdOut.println (kdtree.nearest (more));

    StdOut.println (kdtree);
	kdtree.toGraphviz ();
    kdtree.draw ();
  }
}