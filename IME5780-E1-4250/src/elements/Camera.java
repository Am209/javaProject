/**
 * 
 */
package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static java.lang.System.out;
import static primitives.Util.*;

/**
 * Class camera represents the viewing point from which we are overlook the scene 
 *
 */
public class Camera {
	private Point3D _p0;
	private Vector _vUp,_vTo,_vRight;
	/**
	 * Camera constructor recieving position point and two vectors
	 * @param p
	 * @param Vup
	 * @param Vto
	 */
	public Camera(Point3D p, Vector vTo, Vector vUp) {
		if(!isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("Vup is not ortthogonal to Vto");
		_p0 = p;
		_vUp = vUp.normalized();
		_vTo = vTo.normalized();
		_vRight = vTo.crossProduct(vUp).normalized();
	}
	/**
	 * Camera value getter
	 * @return the _p
	 */
	public Point3D get_p() {
		return _p0;
	}
	/**
	 * Camera value getter
	 * @return the _vUp
	 */
	public Vector get_vUp() {
		return _vUp;
	}
	/**
	 * Camera value getter
	 * @return the _vTo
	 */
	public Vector get_vTo() {
		return _vTo;
	}
	/**
	 * Camera value getter
	 * @return the _Vright
	 */
	public Vector get_vRight() {
		return _vRight;
	}
	/**
	 * The function construct ray that go through specific pixel
	 * @return Ray
	 */
	public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, 
			double screenWidth, double screenHeight) {
		 
		double rX = screenWidth/nX;
		double rY = screenHeight/nY;
		double xJ = (j -(nX - 1)/2.0) * rX;
		double yI = (i -(nY - 1)/2.0) * rY;
		Point3D p = _p0.add(_vTo.scale(screenDistance));
		if(xJ != 0)
			p = p.add(_vRight.scale(xJ));
		if(yI != 0)
			p = p.add(_vUp.scale(-yI));
		return new Ray(_p0,p.subtract(_p0));
		
	}
 
	

}
