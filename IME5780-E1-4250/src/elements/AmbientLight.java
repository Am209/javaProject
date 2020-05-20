/**
 * 
 */
package elements;

import primitives.Color;

/**
 * Class AmbientLight responsible for the AmbientLight of the scene
 * The class extends class Light 
 *
 */
public class AmbientLight extends Light{
	
	/**AmbientLight constructor that calaculate the intensity of the Light
	 * @param IA
	 * @param kA
	 */
	public AmbientLight(Color IA, double kA) {
		super(IA.scale(kA));
	}
	
	
}
