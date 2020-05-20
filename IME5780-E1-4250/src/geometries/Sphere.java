package geometries;


import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;

import primitives.Color;
import primitives.Material;
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
	 *  Sphere constructor receiving color, material, radius and center point
	 * @param emmission
	 * @param material
	 * @param _radius
	 * @param _center
	 */
	public Sphere(Color emmission, Material material, double _radius, Point3D _center) {
		super(emmission, material, _radius);
		this._center = _center;
	}
	/**
	 * Sphere constructor receiving color, radius and center point
	 * @param emmission
	 * @param radius
	 * @param center
	 */
	public Sphere(Color emmission, double radius, Point3D center) {
		super(emmission, radius);
		_center = center;
	}
	/**
	 *  Sphere constructor receiving radius and center point
	 * @param _radius
	 * @param _center
	 */
	public Sphere(double radius,Point3D center) {
		super(radius);
		_center =center;
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
	public List<GeoPoint> findIntersections(Ray ray){
		List<GeoPoint> l = new LinkedList<GeoPoint>();
		try {
			Vector u = _center.subtract(ray.get_p());
			double uLength = u.length();
			double tm = alignZero(u.dotProduct(ray.get_v()));
			double d = Math.sqrt(u.lengthSquared() - tm*tm);
			if(d>=_radius||d<0) //no intersection points
				return null;
			double th = alignZero(Math.sqrt(_radius*_radius-d*d));
			double t1 = alignZero(tm+th);
			double t2 = alignZero(tm-th);
			if(t1>0){
				
				l.add(new GeoPoint(this,ray.getPoint(t1)));
			}
			if(t2>0 && uLength>_radius){
				l.add(new GeoPoint(this,ray.getPoint(t2)));
			}
		}catch(IllegalArgumentException e) {
			 l.add(new GeoPoint(this,ray.getPoint(_radius)));
		}
		return l.isEmpty()?null:l; 
		
	}
	@Override
	public String toString() {
		return "Sphere center=" + _center + ", radius=" + _radius ;
	}

}
