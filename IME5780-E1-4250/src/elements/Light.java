/**
 * 
 */
package elements;

import primitives.Color;

/**
 * Class Light is an abstract class representing differents types of lights
 *
 */
 abstract class Light {
	 protected Color _intensity;

	/**
	 *  Light constructor receiving intensity
	 * @param _intensity
	 */
	public Light(Color intensity) {
		_intensity = intensity;
	}

	/**
	 * Light value getter
	 * @return the _intensity
	 */
	public Color getIntensity() {
		return _intensity;
	} 
	 

}
