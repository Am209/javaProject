package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
/**
* Class Plane is the basic class representing a plane
* the class implement the inteface Geometry
*/
public class Plane implements Geometry {

	Point3D _p;
	Vector _normal;
	/**
	 *  Plane constructor receiving point and vector(normal)
	 * @param _p point
	 * @param _v vector(normal)
	 */
	public Plane(Point3D p, Vector normal) {
		this._p = new Point3D(p);
		this._normal = new Vector(normal);
	}
	/**
	 *  Plane constructor receiving 3 points
	 * @param _p1 point1 value
	 * @param _p2 point2 value
	 * @param _p3 point3 value
	 */
	public Plane(Point3D p1,Point3D p2,Point3D p3) { 
		this._p = new Point3D(p1);
		Vector V = new Vector(p2.subtract(p1));
		Vector U = new Vector(p3.subtract(p1));
		Vector N = U.crossProduct(V);
		N.normalize();
		N = N.scale(-1);
		this._normal = N;
	}
	/**
	 * Plane value getter
	 * @return  _p
	 */
	public Point3D get_p() {
		return _p;
	}
	/**
	 * The function return the planes normal
	 * @return the normal
	 */
	public Vector getNormal() {
		return _normal;
	}
	/**
	 * The function return the normal to the point
	 * @return the normal  
	 */
	@Override
	public Vector getNormal(Point3D p) {
		return _normal;
	}
	/**
	 * The function return a list of the intersections points  of the ray with the Plane
	 * @param ray
	 * @return List<Point3D>
	 */
	public List<Point3D> findIntersections(Ray ray){
		List<Point3D> l = null;
		double nv =_normal.dotProduct(ray.get_v());
		if(isZero(nv)) 
			return null;
		double t = (_normal.dotProduct(_p.subtract(ray.get_p())))/nv;
		if(t>0) {
			l = new LinkedList<Point3D>();
			l.add(ray.getPoint(t));
		}
		return l;
	}

}
