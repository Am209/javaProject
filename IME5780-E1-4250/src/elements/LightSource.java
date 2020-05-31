/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Interface LightSourc representing diffrent types of lights 
 */
public interface LightSource  {
	
	public Color getIntensity(Point3D p);
	public Vector getL(Point3D p);
	public double getDistance(Point3D point);

}
