package geometries;

import primitives.Color;
import primitives.Material;
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
	 *  Cylinder constructor receiving color, material, radius, ray and height
	 * @param emmission
	 * @param material
	 * @param radius
	 * @param axisRay
	 * @param _height
	 */
	public Cylinder(Color emmission, Material material, double radius, Ray axisRay, double height) {
		super(emmission, material, radius, axisRay);
		this._height = height;
	}
	/**
	 *  Cylinder constructor receiving color, radius, ray and height
	 * @param emmission
	 * @param _radius
	 * @param _axisRay
	 * @param _height
	 */
	public Cylinder(Color emmission,double radius, Ray axisRay, double height) {
		this(radius, axisRay,height);
		_emmission = emmission;
		
	
	}
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
