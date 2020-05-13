/**
 * 
 */
package elements;

import primitives.Color;

/**
 * Class AmbientLight responsible for the AmbientLight of the scene
 *
 */
public class AmbientLight {
	private Color _intensity;
	
	/**Constructor that calaculate the intensity of the Light
	 * @param IA
	 * @param kA
	 */
	public AmbientLight(Color IA, double kA) {
		_intensity = IA.scale(kA) ;
	}
	/**
	 * AmbientLight value getter
	 * @return _intensity
	 */
	 public Color getIntensity() {
		 return _intensity;
	 }
	
	
}
