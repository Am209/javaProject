/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author User
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
				// Test validation of getNormal(Point3D) function 
		Sphere s1 = new Sphere(4, new Point3D(0,0,0));
		assertTrue("getNormal() wrong value",s1.getNormal(new Point3D(0,0,4)).equals(new Vector(0,0,1)));
		assertTrue("getNormal() wrong value",s1.getNormal(new Point3D(0,0,-4)).equals(new Vector(0,0,-1)));
		
		Sphere s2 = new Sphere(1, new Point3D(1,1,1));
		assertTrue("getNormal() wrong value",s2.getNormal(new Point3D(1,1,0)).equals(new Vector(0,0,-1)));
		assertTrue("getNormal() wrong value",s2.getNormal(new Point3D(1,0,1)).equals(new Vector(0,-1,0)));
	}

}
