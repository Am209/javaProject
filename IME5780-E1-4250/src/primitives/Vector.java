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
	public Vector(Coordinate coor1,Coordinate coor2,Coordinate coor3)  {
		if(coor1.get()==0 && coor2.get()==0 && coor3.get()==0)
			throw new IllegalArgumentException("Vector zero");
		_p = new Point3D(coor1,coor2,coor3);
	}
	/**
     * Vector constructor receiving  3 double
     * @param _d1 coordinate 1 value
     * @param _d2 coordinate 2 value
     * @param _d3 coordinate 3 value
     */
	public Vector(double d1,double d2,double d3) {
		if(d1==0 && d2==0 && d3==0)                   
			throw new IllegalArgumentException("Vector zero");
		_p = new Point3D(d1,d2,d3);
	}
	/**
     * Vector constructor receiving Point3D
     * 
     * @param _p  Point value
     */
	public Vector(Point3D p) {
		if(p.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Vector zero");
		this._p = new Point3D(p);
	}
	/**
     * Copy constructor for Vector
     * 
     * @param _v
     */
	public Vector(Vector v) {
		_p = new Point3D(v.get_p());
	}
	/**
	 * Vector value getter
	 * @return  _p
	 */
	public Point3D get_p() {
		return _p;
	}
	/**
	 * the function preforms an addition operation between two vectors
	 * @param _v vector
	 * @return new vector  - the result of the operation
	 */
	public Vector add(Vector v)  {
		return new Vector(_p.add(v));
	}
	/**
	 * the function preforms a substraction operation between the vectors 
	 * @param __v another vector
	 * @return new vector  - the result of the operation
	 */
	public Vector subtract(Vector v) {
		return _p.subtract(v.get_p());
	}
	/**
	 * the function preforms a multiplication operation between the vector and scalar
	 * @param __d the scalar
	 * @return new vector  - the result of the operation
	 */
	public Vector scale(double d) {
		double d1 =_p.get_coor1().get()*d;
		double d2 =_p.get_coor2().get()*d;
		double d3 =_p.get_coor3().get()*d;
		return new Vector(d1,d2,d3);
	}
	/**
	 * the function preforms a dotProduct operation between the vectors
	 * @param __v another vector
	 * @return  the result of the operation
	 */
	public double dotProduct (Vector v){
		double d1 =_p.get_coor1().get() * v.get_p().get_coor1().get();
		double d2 =_p.get_coor2().get() * v.get_p().get_coor2().get();
		double d3 =_p.get_coor3().get() * v.get_p().get_coor3().get();
		return d1 + d2 +d3;
	}
	/**
	 * the function preforms a crossProduct operation between the vectors
	 * @param __v another vector
	 * @return new vector - the result of the operation
	 */
	public Vector crossProduct(Vector v) {
		double d1 =_p.get_coor2().get()*v.get_p().get_coor3().get() - _p.get_coor3().get()*v.get_p().get_coor2().get();
		double d2 =_p.get_coor3().get()*v.get_p().get_coor1().get() - _p.get_coor1().get()*v.get_p().get_coor3().get();
		double d3 =_p.get_coor1().get()*v.get_p().get_coor2().get() - _p.get_coor2().get()*v.get_p().get_coor1().get();
		return new Vector(d1,d2,d3);
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
