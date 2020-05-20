package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
/**
* Class Geometry provides the function getNormal for all the classes that inhert from it.
*/
public abstract class Geometry implements Intersectable{
	protected Color _emmission;
	protected Material _material;
	
	
	/**
	 * Geometry constructor recieving emmission and emmission
	 * @param _emmission
	 * @param _material
	 */
	public Geometry(Color emmission, Material material) {
		_emmission = emmission;
		_material = material;
	}

	/**
	 * Geometry constructor recieving emmission
	 * @param _emmission
	 */
	public Geometry(Color emmission) {
		_emmission = emmission;
		_material = new Material(0, 0, 0); 
	}

	/**
	 * Geometry constructor
	 */
	public Geometry() {
		this(Color.BLACK);
	}

	/**
	 * Geometry value getter
	 * @return the _material
	 */
	public Material getMaterial() {
		return _material;
	}


	/**
	 * Geometry value getter
	 * @return the _emmission
	 */
	public Color getEmmission() {
		return _emmission;
	}

	public abstract Vector getNormal(Point3D p);
}
