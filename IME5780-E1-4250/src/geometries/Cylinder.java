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
	public Cylinder(double radius, Ray axisRay, double height) {
		super(radius, axisRay);
		this._height = height;
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
	public Vector getNormal(Point3D p) {
		return null;
	}

	@Override
	public String toString() {
		return "Cylinder: height=" + _height +"axisRay=" + _axisRay + ", radius=" + _radius;
	}
	

}
