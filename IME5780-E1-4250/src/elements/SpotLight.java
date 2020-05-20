/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class SpotLight contains all the fileds conected to spotLight
 * The class extends class Light implements the interface LightSource
 */
public class SpotLight extends PointLight {
	private Vector _direction;

	/**
	 * SpotLight constructor receiving intensity, position,  kC, kL, kQ and direction
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param _direction
	 */
	public SpotLight(Color intensity, Point3D position, double kC, double kL, double kQ, Vector direction) {
		super(intensity, position, kC, kL, kQ);
		_direction = direction.normalize();
	}
	/**
	 * The function calculae and return the lights intensity at specific point
	 * @param p  - the point
	 * @return the intensity
	 */
	@Override
	public Color getIntensity(Point3D p) {
		double d = p.distance(_position);
		double dirL = _direction.dotProduct(getL(p));
		if(dirL<=0)
			return Color.BLACK;
		return _intensity.scale(dirL/(_kC+_kL*d+_kQ*d*d));
	}
	/**
	 * The function return vector L  - the vector from the light source to a specific point
	 * @param p  - the point
	 * @return Vector L
	 */
	@Override
	public Vector getL(Point3D p) {
		return p.subtract(_position).normalized();
	}
	
	
}
