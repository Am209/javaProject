package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
* Interface Geometry provides the function getNormal for all the classes that implement it.
*/
public interface Geometry {
	public Vector getNormal(Point3D p);
	
}
