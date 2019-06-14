import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class BruteCollinearPoints {
    private List<LineSegment> lineSegments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        assertNoException(points);
        Point[] copyPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyPoints);
        int n = copyPoints.length;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                for(int k=j+1;k<n;k++) {
                    if (copyPoints[i].slopeTo(copyPoints[j]) !=copyPoints[j].slopeTo(copyPoints[k])) continue;
                    for(int l=k+1;l<n;l++) {
                        if (copyPoints[i].slopeTo(copyPoints[j]) == copyPoints[j].slopeTo(copyPoints[k]) && copyPoints[j].slopeTo(copyPoints[k])==copyPoints[k].slopeTo(copyPoints[l])) {
                            lineSegments.add(new LineSegment(copyPoints[i],copyPoints[l]));
                        }
                    }
                }
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

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        int size = lineSegments.size();
        LineSegment[] res = new LineSegment[size];
        for(int i=0;i<size;i++) {
            res[i] = lineSegments.get(i);
        }
        return res;
    }

}