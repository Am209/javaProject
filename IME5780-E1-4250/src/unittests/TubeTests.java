/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Tube
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// Test validation of getNormal function 
		 Tube t = new Tube(1,new Ray(new Point3D(1,2,3),new Vector(1,1,1)));
		 assertTrue("getNormal() wrong value",t.getNormal(new Point3D(2,3,4)).equals(new Vector(-2/Math.sqrt(12),-2/Math.sqrt(12),-2/Math.sqrt(12))));
	}

}
