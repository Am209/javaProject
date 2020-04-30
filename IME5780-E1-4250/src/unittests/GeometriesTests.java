/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Geometries
 *
 */
public class GeometriesTests {
	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	 public void testFindIntersections() {
		Geometries g;
		Plane p = new Plane(new Point3D(0,0,1),new Point3D(1,1,0),new Point3D(1,0,1));
		Sphere s = new Sphere(1d, new Point3D(1, 0, 0));
		Triangle t = new Triangle(new Point3D(0,0,0),new Point3D(2,0,0),new Point3D(1,0,2));
		
		// ============ Equivalence Partitions Tests ==============
		
		// TC01:Ray intersects some Geometries
		g = new Geometries(p,s,t);
		List<Point3D> result  = g.findIntersections(new Ray(new Point3D(1, 0, 0.5), new Vector(0, -1, 0)));
		assertEquals(" Wrong number of points",1,result.size());
		
		// =============== Boundary Values Tests ==================
		// TC02: Geometries is empty
		g = new Geometries();
		assertNull("Geometries is empty",g.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(1, 0, 0))));
	       
		// TC03: Ray does not intersects the Geometries
		g = new Geometries(p,s,t);
		assertNull("Geometries is empty",g.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(1, 0, 0))));
	    
		// TC04: Ray intersects all Geometries 
		 result  = g.findIntersections(new Ray(new Point3D(1, 2, 0.5), new Vector(0, -1, 0)));
		assertEquals(" Wrong number of points",4,result.size());
	    
		
	}
}
