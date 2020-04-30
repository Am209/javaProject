package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
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
	/**
	 * The function return a list of the intersections points  of the ray with the Triangle
	 * @param ray
	 * @return List<Point3D>
	 */
	public List<Point3D> findIntersections(Ray ray){
		Vector v1 = _vertices.get(0).subtract(ray.get_p());
		Vector v2 = _vertices.get(1).subtract(ray.get_p());
		Vector v3 = _vertices.get(2).subtract(ray.get_p());
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();
		
		double t1 = ray.get_v().dotProduct(n1);
		double t2 = ray.get_v().dotProduct(n2);
		double t3 = ray.get_v().dotProduct(n3);
		if(t1>0&&t2>0&&t3>0 || t1<0&&t2<0&&t3<0)
			return _plane.findIntersections(ray);
		return null;
	}
}
