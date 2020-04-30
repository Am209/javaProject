package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Triangle
 *
 */
public class TriangleTests {
	/**
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	 public void testFindIntersections() {
		Triangle t = new Triangle(new Point3D(0,0,0),new Point3D(2,0,0),new Point3D(1,0,2));
		// ============ Equivalence Partitions Tests ==============
		
		 // TC01:Ray intersects the triangle 
		 assertEquals("Ray intersects the triangle",List.of(new Point3D(1,0,1)),t.findIntersections(new Ray(new Point3D(1, -1, 1), new Vector(0, 1, 0))));
		 // TC02:Ray Outside and against edge 
		 assertNull( "Ray Outside and against edge",t.findIntersections(new Ray(new Point3D(3,-1, 1), new Vector(0, 1, 0))));	
		 // TC03:Ray Outside and against vertex
		 assertNull( "Ray Outside and against vertex",t.findIntersections(new Ray(new Point3D(3,-1, -0.5), new Vector(0, 1, 0))));	
		 
		 // =============== Boundary Values Tests ==================
		 
		 //TC04:Ray begins before the plane and intersect an edge 
		 assertNull( "Ray begins before the plane and intersect an edge ",t.findIntersections(new Ray(new Point3D(1,-1, 0), new Vector(0, 1, 0))));	
		// TC05:Ray begins before the plane and intersect a vertex  
		 assertNull( "Ray begins before the plane and intersecta vertex",t.findIntersections(new Ray(new Point3D(2,-1, 0), new Vector(0, 1, 0))));	
		// TC06:Ray begins before the plane and intersect  an edge's continuation
		 assertNull( "Ray begins before the plane and intersecta  an edge's continuation",t.findIntersections(new Ray(new Point3D(3,-1, 0), new Vector(0, 1, 0))));	
			
	 }

}
