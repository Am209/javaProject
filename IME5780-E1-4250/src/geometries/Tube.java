package geometries;

import java.util.List;

import primitives.Color;
import primitives.Material;
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
	 * Tube constructor receiving color,material, radius and ray
	 * @param emmission
	 * @param material
	 * @param _radius
	 * @param _axisRay
	 */
	public Tube(Color emmission, Material material, double radius, Ray axisRay) {
		super(emmission, material, radius);
		_axisRay = axisRay;
	}
	/**
	 * Tube constructor receiving color, radius and ray
	 * @param emmission
	 * @param _radius
	 * @param _axisRay
	 */
	public Tube(Color emmission,double radius, Ray axisRay) {
		this(radius,axisRay);
		_emmission =emmission;
		
	}
	/**
	 * Tube constructor receiving radius and ray
	 * @param _radius
	 * @param _axisRay
	 */
	public Tube(double radius, Ray axisRay) {
		super(radius);
		this._axisRay = axisRay;
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
	public Vector getNormal(Point3D p) {
		Vector v =  _axisRay.get_v();
		Point3D P0 =  _axisRay.get_p();
		double t = v.dotProduct(p.subtract(P0));
		Point3D O = P0.add(v.scale(t)); 
		Vector N = (P0.subtract(O));
		return N.normalize();
		
	}
	public List<GeoPoint> findIntersections(Ray ray){
		return null;
	}
	@Override
	public String toString() {
		return "Tube: axisRay=" + _axisRay + ", radius=" + _radius ;
	}

}
