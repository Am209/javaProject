package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
* Abstract class RadialGeometry is the basic class representing a radial geometry
* the class implements the inteface Geometry
*/
public abstract class RadialGeometry implements Geometry {

	protected double _radius;
	/**
	 *  RadialGeometry constructor receiving radius
	 * @param _radius
	 */
	public RadialGeometry(double radius) {
		this._radius = radius;
	}
	/**
     * Copy constructor for RadialGeometry
     * 
     * @param _v
     */
	public RadialGeometry(RadialGeometry radialGeo) {
		_radius = radialGeo.get_radius();
	}
	/**
	 * RadialGeometry value getter
	 * @return the _radius
	 */
	public double get_radius() {
		return _radius;
	}
	/**
	 * Abstract function - return the normal to the point
	 * @return the normal  
	 */
	@Override
	public abstract Vector getNormal(Point3D p);

}
