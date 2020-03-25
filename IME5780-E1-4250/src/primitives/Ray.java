package primitives;
/**
* Class Ray is the basic class representing a ray
*/
public class Ray {

	private Point3D _p;
	private Vector _v;
	/**
     * Ray constructor receiving point3D and vector
     * @param _p point value
     * @param _v vector value
     */
	public Ray(Point3D _p, Vector _v) {
		this._p = _p;
		this._v = _v;
		this._v.normalize();      
	}
	/**
     * Copy constructor for Ray
     * @param _r
     */
	public Ray(Ray _r) {
		_p = _r.get_p();
		_v = _r.get_v();
		_v.normalize();        
	}
	/**
	 *  Ray value getter
	 * @return  _p
	 */
	public Point3D get_p() {
		return _p;
	}
	/**
	 * Ray value getter
	 * @return  _v
	 */
	public Vector get_v() {
		return _v;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (_p == null) {
			if (other._p != null)
				return false;
		} else if (!_p.equals(other._p))
			return false;
		if (_v == null) {
			if (other._v != null)
				return false;
		} else if (!_v.equals(other._v))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ray point=" + _p + ", vector=" + _v ;
	}
	
	
}
