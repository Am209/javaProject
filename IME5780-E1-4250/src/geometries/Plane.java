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
	public Plane(Point3D _p, Vector _normal) {
		this._p = new Point3D(_p);
		this._normal = new Vector(_normal);
	}
	/**
	 *  Plane constructor receiving 3 points
	 * @param _p1 point1 value
	 * @param _p2 point2 value
	 * @param _p3 point3 value
	 */
	public Plane(Point3D _p1,Point3D _p2,Point3D _p3) { 
		this._p = new Point3D(_p1);
		Vector V = new Vector(_p2.subtract(_p1));
		Vector U = new Vector(_p3.subtract(_p1));
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
	public Vector getNormal(Point3D _p) {
		return _normal;
	}
	

}
