/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Plane
 * @author 
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
	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	 public void testFindIntersections() {
		Plane p = new Plane(new Point3D(0,0,1),new Point3D(1,1,0),new Point3D(1,0,1));
		// ============ Equivalence Partitions Tests ==============
		 // TC01:Ray intersects the plane 
		 assertEquals("Ray intersects the plane",List.of(new Point3D(1,1,0)),p.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0))));
		 // TC02:Ray does not intersect the plane
		 assertNull( "Ray does not intersects the plane",p.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, -1, 0))));
			
		 // =============== Boundary Values Tests ==================
		 // TC03: Ray is parallel to the plane and included in the plane
		 assertNull( "Ray is parallel and included in the plane",p.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 0, 0))));
		 // TC04: Ray is parallel to the plane and  not included in the plane
		 assertNull( "Ray is parallel and not included in the plane",p.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 0, 0))));	
		 //TC05:Ray is before the plane and orthogonal to the plane
		 assertEquals( "Ray is before the plane and orthogonal to the plane",List.of(new Point3D(0,0.5,0.5)),p.findIntersections(new Ray(new Point3D(0, 0, 0), 
				 new Vector(0, 1/Math.sqrt(2), 1/Math.sqrt(2)))));
		 //TC06:Ray is in the plane  and orthogonal to the plane
		 assertNull( "Ray is in the plane  and orthogonal to the plane",p.findIntersections(new Ray(new Point3D(1, 1, 0), 
				 new Vector(0, 1/Math.sqrt(2), 1/Math.sqrt(2)))));			
		 //TC07:Ray is after the plane and orthogonal to the plane
		 assertNull( "Ray is after the plane  and orthogonal to the plane",p.findIntersections(new Ray(new Point3D(1, 2, 0), 
				 new Vector(0, 1/Math.sqrt(2), 1/Math.sqrt(2)))));	
		 //TC08:Ray begien at the plane 
		assertNull( "Ray begien at the plane",p.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(0, 1, 0))));	
			
		 //TC09:Ray begins in the same point which appears as reference point in the plane //זריקת חריגה וקטור 0
		 //assertNull( "Ray begins in the same point which appears as reference point in the plane",p.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 0))));	
			
		 
		 
		 

	 }
	 

}
