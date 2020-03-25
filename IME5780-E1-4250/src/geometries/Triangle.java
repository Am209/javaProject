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
	public Triangle(Point3D _p1,Point3D _p2,Point3D _p3) {
		 super(_p1,_p2,_p3);
	}

}
