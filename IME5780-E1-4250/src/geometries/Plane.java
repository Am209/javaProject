package geometries;

import primitives.Point3D;
import primitives.Vector;
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
	

}
