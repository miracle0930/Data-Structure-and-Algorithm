package closestPoints;

public class ClosestPairNaive {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    
    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "NAIVE (x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    
    public static void findClosestPair(XYPoint points[], boolean print) {
    	double minLength = INF;
    	double tempLength = 0;
    	XYPoint[] answer = new XYPoint[2];
    	for (int i = 0; i < points.length - 1; i++) {
    		for (int j = i + 1; j < points.length; j++) {
    			tempLength = points[i].dist(points[j]);
    			if (tempLength < minLength) {
    				minLength = tempLength;
    				answer[0] = points[i];
    				answer[1] = points[j];
    			}
    		}
    	}
    	print = true;
        if (print) {
        	System.out.println("NAIVE (" + answer[0].x + "," + answer[0].y + ") (" + answer[1].x + ","+answer[1].y + ") " + minLength);
        }
	  }
}
