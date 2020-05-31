/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class PointLight contains all the fileds conected to contains all the fileds conected to
 * The class extends class Light implements the interface LightSource
 */
public class PointLight extends Light implements LightSource {
	protected Point3D _position;
	protected double _kC, _kL, _kQ;
	/**
	 * PointLight constructor receiving intensity, position,  kC, kL and kQ
	 * @param intensity
	 * @param _position
	 * @param _kC
	 * @param _kL
	 * @param _kQ
	 */
	public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
		super(intensity);
		_position = position;
		_kC = kC;
		_kL = kL;
		_kQ = kQ;
	}
	/**
	 * The function calculae and return the lights intensity at specific point
	 * @param p  - the point
	 * @return the intensity
	 */
	@Override
	public Color getIntensity(Point3D p) {
		double d = p.distance(_position);
		return _intensity.scale(1/(_kC+_kL*d+_kQ*d*d));
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
	/**
	 * The function return the distance between the point and the light source
	 * @param p  - the point
	 * @return double distance 
	 */
	@Override
	public double getDistance(Point3D p) {
		return p.distance(_position);
	}

}
