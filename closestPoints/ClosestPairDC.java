package closestPoints;

import java.util.ArrayList;
import java.util.List;
public class ClosestPairDC {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    static XYPoint[] result=new XYPoint[2];  // store the shortest pair.

    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "DC (x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    
     public static void findClosestPair(XYPoint pointsByX[], XYPoint pointsByY[], boolean print) { 
    	 
    	 double minLength = ClosestPair(pointsByX, pointsByY, pointsByX.length);
    	 print = true;
    	 if (print) {
             System.out.println("DC ("+result[0].x+","+result[0].y+") ("+result[1].x+","+result[1].y+") "+minLength);  
    	 }
     }   
     
     public static double ClosestPair(XYPoint pointsByX[], XYPoint pointsByY[], int length) {
    	 double minLength = INF;
    	 if (length == 1) return minLength;
    	 if (length == 2) return pointsByX[0].dist(pointsByX[1]);
    	 int mid = length / 2;
    	 XYPoint[] XL = new XYPoint[mid];
    	 XYPoint[] XR = new XYPoint[length - mid];
    	 XYPoint[] YL = new XYPoint[mid];
    	 XYPoint[] YR = new XYPoint[length - mid];
    	 for (int i = 0; i < mid; i++) {
    		 XL[i] = pointsByX[i];
    		 YL[i] = pointsByY[i];
    	 }
    	 for (int i = 0; i < length - mid; i++) {
    		 XR[i] = pointsByX[i + mid];
    		 YR[i] = pointsByY[i + mid];
    	 }
    	 double lrDist = Math.min(ClosestPair(XL, YL, mid), ClosestPair(XR, YR, length - mid));
    	 return Combine(pointsByY, pointsByX[mid], length, lrDist);
     }
     
     public static double Combine(XYPoint[] pointsByY, XYPoint midPoint, double length, double lrDist) {
    	 List<XYPoint> yStrip = new ArrayList<>();
    	 for (int i = 0; i < pointsByY.length; i++) {
    		 if (Math.abs(pointsByY[i].x - midPoint.x) < lrDist) yStrip.add(pointsByY[i]);
    	 }
    	 double minDist = lrDist;
    	 for (int i = 0; i < yStrip.size() - 1; i++) {
    		 int k = i + 1;
    		 while (k < yStrip.size() && yStrip.get(k).y - yStrip.get(i).y < lrDist) {
    			 double d = yStrip.get(k).dist(yStrip.get(i));
    			 if (d < minDist) {
    	    		 result[0] = yStrip.get(k);
    	    		 result[1] = yStrip.get(i);
    	    		 minDist = d;
    			 }
    			 k++;
    		 }
    	 }
    	 return minDist;
     }
}
 