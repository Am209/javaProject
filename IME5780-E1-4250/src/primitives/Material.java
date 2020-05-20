/**
 * 
 */
package primitives;

/**
 * Class Material contains the coefficients corresponding of the material
 *
 */
public class Material {
	private double _kD;
	private double _kS;
	private int _nShininess;
	/**
	 *  Material constructor recieving  kD, kS and nShininess
	 * @param _kD
	 * @param _kS
	 * @param _nShininess
	 */
	public Material(double kD, double kS, int nShininess) {
		_kD = kD;
		_kS = kS;
		_nShininess = nShininess;
	}
	/**
	 *  Material value getter
	 * @return the _kD
	 */
	public double get_kD() {
		return _kD;
	}
	/**
	 *  Material value getter
	 * @return the _kS
	 */
	public double get_kS() {
		return _kS;
	}
	/**
	 *  Material value getter
	 * @return the _nShininess
	 */
	public int get_nShininess() {
		return _nShininess;
	}
	
	

}
