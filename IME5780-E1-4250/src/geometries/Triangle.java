package geometries;

import primitives.Point3D;
/**
* Class Triangle is the basic class representing a triangle
* the class inherits from the class Polygon
*/
public class Triangle extends Polygon {

	/**
	 *  Triangle constructor receiving 3 points
	 * @param _p1 point1 value
	 * @param _p2 point2 value
	 * @param _p3 point3 value
	 */
	public Triangle(Point3D p1,Point3D p2,Point3D p3) {
		 super(p1,p2,p3);
	}

}
