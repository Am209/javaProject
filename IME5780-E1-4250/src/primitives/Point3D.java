package primitives;

/**
* Class Point3D is the basic class representing a Point3D
*/
public class Point3D {
	private Coordinate _coor1;
	private Coordinate _coor2;
	private Coordinate _coor3;
	public static Point3D ZERO  = new Point3D(0,0,0);
	
	/**
     * Point3D constructor receiving  3 coordinates value
     * 
     * @param _coor1 coordinate 1 value
     * @param _coor2 coordinate 2 value
     * @param _coor3 coordinate 3 value
     */
	public Point3D(Coordinate coor1, Coordinate coor2, Coordinate coor3) {
		this._coor1 = coor1;
		this._coor2 = coor2;
		this._coor3 = coor3;
	}
	/**
     * Point3D constructor receiving  3 double
     * 
     * @param _d1 coordinate 1 value
     * @param _d2 coordinate 2 value
     * @param _d3 coordinate 3 value
     */
	public Point3D(double d1, double d2, double d3) {
		_coor1 = new Coordinate(d1);
		_coor2 = new Coordinate(d2);
		_coor3 = new Coordinate(d3);
	} 
	/**
     * Copy constructor for Point3D
     * 
     * @param _p
     */
	public Point3D(Point3D p) {
		_coor1 = p.get_coor1();
		_coor2 = p.get_coor2();
	    _coor3 = p.get_coor3();
	}
	/**
	 * Point value getter
	 * @return  _coor1
	 */
	public Coordinate get_coor1() {
		return _coor1;
	} 
	/**
	 * Point value getter
	 * @return  _coor2
	 */
	public Coordinate get_coor2() {
		return _coor2;
	} 
	/**
	 * Point value getter
	 * @return  _coor3
	 */
	public Coordinate get_coor3() {
		return _coor3;
	}
	/**
	 * the function preforms a substraction operation between the points 
	 * @param __p another point3D
	 * @return new vector  - the result of the operation
	 */
	public Vector subtract(Point3D p) {
		double d1 = _coor1.get() - p.get_coor1().get();
		double d2 = _coor2.get() - p.get_coor2().get();
		double d3 = _coor3.get() - p.get_coor3().get();
		return new Vector(d1,d2,d3);
	}
	/**
	 * the function preforms an addition operation between point and vector
	 * @param __v vector
	 * @return new Point  - the result of the operation
	 */
	public Point3D add(Vector v) {
		double d1 =v.get_p().get_coor1().get() + _coor1.get();
		double d2 =v.get_p().get_coor2().get() + _coor2.get();
		double d3 =v.get_p().get_coor3().get() + _coor3.get();
		return new Point3D(d1,d2,d3);
	}
	/**
	 * the function calculate the distanceSquared between two points
	 * @param __p point
	 * @return distance  
	 */
	public double distanceSquared(Point3D p) {
		double d1 = p.get_coor1().get()- _coor1.get();
		double d2 = p.get_coor2().get()- _coor2.get();
		double d3 = p.get_coor3().get()- _coor3.get();
		return d1*d1+d2*d2+d3*d3;
	}
	/**
	 * the function calculate the distance between two points
	 * @param __p point
	 * @return distance  
	 */
	public double distance(Point3D p) {
		double d = distanceSquared(p);
		return Math.sqrt(d);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (_coor1 == null) {
			if (other._coor1 != null)
				return false;
		} else if (!_coor1.equals(other._coor1))
			return false;
		if (_coor2 == null) {
			if (other._coor2 != null)
				return false;
		} else if (!_coor2.equals(other._coor2))
			return false;
		if (_coor3 == null) {
			if (other._coor3 != null)
				return false;
		} else if (!_coor3.equals(other._coor3))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "(" + _coor1 + "," + _coor2 + "," + _coor3 + ")";
	}
	
	
}
