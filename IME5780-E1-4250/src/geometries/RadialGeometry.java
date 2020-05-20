package geometries;


import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
/**
* Abstract class RadialGeometry is the basic class representing a radial geometry
* the class implements the inteface Geometry
*/
public abstract class RadialGeometry extends Geometry {

	protected double _radius;
	
	/**
	 * RadialGeometry constructor receiving emmission material and radius 
	 * @param emmission
	 * @param material
	 * @param _radius
	 */
	public RadialGeometry(Color emmission, Material material, double _radius) {
		super(emmission, material);
		this._radius = _radius;
	}
	/**
	 * RadialGeometry constructor receiving color and radius
	 * @param emmission
	 * @param radius
	 */
	public RadialGeometry(Color emmission, double radius) {
		super(emmission);
		_radius = radius;
	}
	/**
	 *  RadialGeometry constructor receiving radius
	 * @param _radius
	 */
	public RadialGeometry(double radius) {
		_radius = radius;
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
