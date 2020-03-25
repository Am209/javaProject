package primitives;
/**
* Class Vector is the basic class representing a vector
*/
public class Vector {
	private Point3D _p;
	/**
     * Vector constructor receiving  3 coordinates value
     * @param _coor1 coordinate 1 value
     * @param _coor2 coordinate 2 value
     * @param _coor3 coordinate 3 value
     */
	public Vector(Coordinate _coor1,Coordinate _coor2,Coordinate _coor3)  {
		if(_coor1.get()==0 && _coor2.get()==0 && _coor3.get()==0)
			throw new IllegalArgumentException("Vector zero");
		_p = new Point3D(_coor1,_coor2,_coor3);
	}
	/**
     * Vector constructor receiving  3 double
     * @param _d1 coordinate 1 value
     * @param _d2 coordinate 2 value
     * @param _d3 coordinate 3 value
     */
	public Vector(double _d1,double _d2,double _d3) {
		if(_d1==0 && _d2==0 && _d3==0)                   
			throw new IllegalArgumentException("Vector zero");
		_p = new Point3D(_d1,_d2,_d3);
	}
	/**
     * Vector constructor receiving Point3D
     * 
     * @param _p  Point value
     */
	public Vector(Point3D _p) {
		if(_p.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Vector zero");
		_p = new Point3D(_p);
	}
	/**
     * Copy constructor for Vector
     * 
     * @param _v
     */
	public Vector(Vector _v) {
		_p = new Point3D(_v.get_p());
	}
	/**
	 * Vector value getter
	 * @return  _p
	 */
	public Point3D get_p() {
		return _p;
	}
	/**
	 * the function preforms an addition operation between two vector 
	 * @param __v vector
	 * @return new vector  - the result of the operation
	 */
	public Vector add(Vector _v)  {
		return new Vector(_p.add(_v));
	}
	/**
	 * the function preforms a substraction operation between the vectors 
	 * @param __v another vector
	 * @return new vector  - the result of the operation
	 */
	public Vector subtract(Vector _v) {
		return new Vector(_p.subtract(_v.get_p()));
	}
	/**
	 * the function preforms a multiplication operation between the vector and scalar
	 * @param __d the scalar
	 * @return new vector  - the result of the operation
	 */
	public Vector scale(double _d) {
		double _d1 =_p.get_coor1().get()*_d;
		double _d2 =_p.get_coor2().get()*_d;
		double _d3 =_p.get_coor3().get()*_d;
		return new Vector(_d1,_d2,_d3);
	}
	/**
	 * the function preforms a dotProduct operation between the vectors
	 * @param __v another vector
	 * @return  the result of the operation
	 */
	public double dotProduct (Vector _v){
		double _d1 =_p.get_coor1().get() * _v.get_p().get_coor1().get();
		double _d2 =_p.get_coor2().get() * _v.get_p().get_coor2().get();
		double _d3 =_p.get_coor3().get() * _v.get_p().get_coor3().get();
		return _d1 + _d2 +_d3;
	}
	/**
	 * the function preforms a crossProduct operation between the vectors
	 * @param __v another vector
	 * @return new vector - the result of the operation
	 */
	public Vector crossProduct(Vector _v) {
		double _d1 =_p.get_coor2().get()*_v.get_p().get_coor3().get() - _p.get_coor3().get()*_v.get_p().get_coor2().get();
		double _d2 =_p.get_coor3().get()*_v.get_p().get_coor1().get() - _p.get_coor1().get()*_v.get_p().get_coor3().get();
		double _d3 =_p.get_coor1().get()*_v.get_p().get_coor2().get() - _p.get_coor2().get()*_v.get_p().get_coor1().get();
		return new Vector(_d1,_d2,_d3);
	}
	/**
	 * the function calculate the lengthSquared of the vector
	 * @return length  
	 */
	public double lengthSquared () {
		return _p.distanceSquared(Point3D.ZERO);
	} 
	/**
	 * the function calculate the length of the vector
	 * @return length  
	 */
	public double length  () {
		return Math.sqrt(lengthSquared());
	}
	/**
	 * the function normalize the vector
	 * @return this vetor after it normalized
	 */
	public Vector normalize() {
		double l = length();
		_p = new Point3D(_p.get_coor1().get()/l,_p.get_coor2().get()/l,_p.get_coor3().get()/l);
		return this;
	} 
	/**
	 * the function normalize the vector
	 * @return new vetor 
	 */
	public Vector normalized() {
		return new Vector(normalize());
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (_p == null) {
			if (other._p != null)
				return false;
		} else if (!_p.equals(other._p))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  _p.toString() ;
	}
	
}
