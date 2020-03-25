package geometries;

import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
/**
* Class Tube is the basic class representing a tube
* the class inherits from the class RadialGeometry
*/
public class Tube extends RadialGeometry {

	protected Ray _axisRay;
	/**
	 * Tube constructor receiving radius and ray
	 * @param _radius
	 * @param _axisRay
	 */
	public Tube(double _radius, Ray _axisRay) {
		super(_radius);
		this._axisRay = _axisRay;
	}
	/**
	 * Tube value getter
	 * @return the _axisRay
	 */
	public Ray get_axisRay() {
		return _axisRay;
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
		return "Tube: axisRay=" + _axisRay + ", radius=" + _radius ;
	}

}
