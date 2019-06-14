import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;


public class FastCollinearPoints {
    private List<Double> slopes = new ArrayList<>();
    private List<Point> endPoints = new ArrayList<>();
    private List<LineSegment> lineSegments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        assertNoException(points);
        Point[] copyPoints = Arrays.copyOf(points, points.length);
        for(Point startPoint: points) {
            Arrays.sort(copyPoints, startPoint.slopeOrder());
            List<Point> sameSlopePoints = new ArrayList<>();
            double curSlope = 0;
            double prevSlope = Double.NEGATIVE_INFINITY;
            for(int i=1; i<copyPoints.length; i++) {
                curSlope = startPoint.slopeTo(copyPoints[i]);
                if(curSlope == prevSlope) {
                    sameSlopePoints.add(copyPoints[i]);
                } else {
                    if (sameSlopePoints.size()>=3) {
                        sameSlopePoints.add(startPoint);
                        addSegmentIfNew(sameSlopePoints, prevSlope);
                    }
                    sameSlopePoints.clear();
                    sameSlopePoints.add(copyPoints[i]);
                }
                prevSlope = curSlope;
            }
            if(sameSlopePoints.size() >=3) {
                sameSlopePoints.add(startPoint);
                addSegmentIfNew(sameSlopePoints, curSlope);
            }
        }
    }

    private void addSegmentIfNew(List<Point> sameSlopePoints, double slope) {
        Collections.sort(sameSlopePoints);
        Point startPoint = sameSlopePoints.get(0);
        Point endPoint = sameSlopePoints.get(sameSlopePoints.size()-1);
        if (lineSegments.isEmpty()) {
            lineSegments.add(new LineSegment(startPoint, endPoint));
            slopes.add(slope);
            endPoints.add(endPoint);
        } else {
            boolean isRepeat = false;
//            for (int i = 0; i < slopes.size(); i++) {
//                if (slope == slopes.get(i) && endPoint == endPoints.get(i)) {
//                    isRepeat = true;
//                    break;
//                }
//            }
            Collections.sort(slopes);

            if (!isRepeat) {
                lineSegments.add(new LineSegment(startPoint, endPoint));
                slopes.add(slope);
                endPoints.add(endPoint);
            }
        }
    }

    private void assertNoException(Point[] points) {
        if(points == null) throw new java.lang.IllegalArgumentException("points are null");
        for(int i = 0; i < points.length; i++) {
            if(points[i] == null) throw new java.lang.IllegalArgumentException("point is null");
            for(int j = i+1; j<points.length; j++) {
                if(points[j] == null) throw new java.lang.IllegalArgumentException("point is null");
                if(points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY)
                    throw new java.lang.IllegalArgumentException("points are the same");
            }
        }
    }

    public int numberOfSegments(){
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] res = new LineSegment[numberOfSegments()];
        for(int i=0;i<numberOfSegments();i++) {
            res[i] = lineSegments.get(i);
        }
        return res;
    }
}