package geometries;

import primitives.Point3D;
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
	public Sphere(double _radius,Point3D _center) {
		super(_radius);
		this._center =_center;
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
	public Vector getNormal(Point3D _p) {
		return null;
	}

	@Override
	public String toString() {
		return "Sphere center=" + _center + ", radius=" + _radius ;
	}

}
