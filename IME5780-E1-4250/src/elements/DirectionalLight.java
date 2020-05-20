package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * Class DirectionalLight representing light that have direction
 * The class extends class Light implements the interface LightSource
 *
 */
public class DirectionalLight extends Light implements LightSource {
	 private  Vector _direction;

	/**
	 *  Light constructor receiving intensity and direction
	 * @param intensity
	 * @param _direction
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		_direction = direction.normalize();
	}
	/**
	 * The function return the lights intensity at specific point
	 * @param p  - the point
	 * @return the intensity
	 */
	@Override
	public Color getIntensity(Point3D p) {
		return _intensity;
	}
	/**
	 * The function return vector L  - the vector from the light source to a specific point
	 * @param p  - the point
	 * @return Vector L
	 */
	@Override
	public Vector getL(Point3D p) {
		return _direction.normalized();
	}

	


}
