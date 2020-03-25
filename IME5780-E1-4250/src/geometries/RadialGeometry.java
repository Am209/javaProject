package geometries;

import primitives.Point3D;
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
	public RadialGeometry(double _radius) {
		this._radius = _radius;
	}
	/**
     * Copy constructor for RadialGeometry
     * 
     * @param _v
     */
	public RadialGeometry(RadialGeometry _radialGeo) {
		_radius = _radialGeo.get_radius();
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
	public abstract Vector getNormal(Point3D _p);

}
