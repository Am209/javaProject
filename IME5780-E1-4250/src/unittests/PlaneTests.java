/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author User
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	public void testGetNormal() {
		// Test validation of getNormal function 
		Plane p = new Plane(new Point3D(1,2,3),new Point3D(2,3,4),new Point3D(3,2,5));
		assertTrue("getNormal() wrong value",new Vector(2/Math.sqrt(8),0,-2/Math.sqrt(8)).equals(p.getNormal()));
		 
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		// ============ Equivalence Partitions Tests ==============
		// Test validation of getNormal(Point3D) function 
		Plane p = new Plane(new Point3D(1,2,3),new Point3D(2,3,4),new Point3D(3,2,5));
		assertTrue("getNormal() wrong value",new Vector(2/Math.sqrt(8),0,-2/Math.sqrt(8)).equals(p.getNormal(new Point3D (1,2,3))));
		
	}

}
