package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
* Class Cylinder is the basic class representing a cylinder
* the class inherits from the class Tube
*/
public class Cylinder extends Tube {
	protected double _height;

	/**
	 *  Cylinder constructor receiving radius ray and height
	 * @param _radius
	 * @param _axisRay
	 * @param _height
	 */
	public Cylinder(double _radius, Ray _axisRay, double _height) {
		super(_radius, _axisRay);
		this._height = _height;
	}
	/**
	 * Cylinder value getter
	 * @return the _height
	 */
	public double get_height() {
		return _height;
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
		return "Cylinder: height=" + _height +"axisRay=" + _axisRay + ", radius=" + _radius;
	}
	

}
