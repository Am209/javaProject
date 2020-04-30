package geometries;


import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
* Class Sphere is the basic class representing a sphere
* the class inherits from the class RadialGeometry
*/
public class Sphere extends RadialGeometry {

	protected Point3D _center;
	/**
	 *  Sphere constructor receiving radius and center point
	 * @param _radius
	 * @param _center
	 */
	public Sphere(double radius,Point3D center) {
		super(radius);
		this._center =center;
	}
	/**
	 * Sphere value getter
	 * @return the _center
	 */
	public Point3D get_center() {
		return _center;
	}
	/**
	 * The function return the normal to the point
	 * @return the normal  
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v = new Vector(p.subtract(_center));
		return v.normalize();
	}
	/**
	 * The function return a list of the intersections points  of the ray with the Sphere
	 * @param ray
	 * @return List<Point3D>
	 */
	public List<Point3D> findIntersections(Ray ray){
		List<Point3D> l = new LinkedList<Point3D>();
		
		Vector u = _center.subtract(ray.get_p());
		 
		double uLength = u.length();
		double tm = u.dotProduct(ray.get_v());
		double d = Math.sqrt(u.lengthSquared() - tm*tm);
		
		if(d>=_radius||d<0) //no intersection points
			return null;
		double th = Math.sqrt(_radius*_radius-d*d);
		double t1 = tm+th;
		double t2 = tm-th;
		if(t1>0){
			l.add(ray.getPoint(t1));
		}
		if(t2>0 && uLength>_radius){
			l.add(ray.getPoint(t2));
		}
		return l.isEmpty()?null:l; 
	}
	@Override
	public String toString() {
		return "Sphere center=" + _center + ", radius=" + _radius ;
	}

}
