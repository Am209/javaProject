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
	private double _kT;
	private double _kR;
	/**
	 *  Material constructor recieving  kD, kS and nShininess
	 * @param _kD
	 * @param _kS
	 * @param _nShininess
	 */
	public Material(double kD, double kS, int nShininess) {
		this(kD,kS,nShininess,0,0);
	}
	/**
	 *  Material constructor recieving  kD, kS,nShininess,kT and kr
	 * @param _kD
	 * @param _kS
	 * @param _nShininess
	 * @param _kT
	 * @param _kR
	 */
	public Material(double kD, double kS, int nShininess,double kT, double kR) {
		_kD = kD;
		_kS = kS;
		_nShininess = nShininess;
		_kT = kT;
		_kR = kR;
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
	/**
	 *  Material value getter
	 * @return the _kT
	 */
	public double get_kT() {
		return _kT;
	}
	
	/**
	 *  Material value getter
	 * @return the _kR
	 */
	public double get_kR() {
		return _kR;
	}
	
	

}
