/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Vector;

/**
 * Testing Vector
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		 Vector v1 = new Vector(1, 2, 3);
	     Vector v2 = new Vector(1, 2, 3);
	     Vector v = v1.add(v2);
	     // Test validation of add function 
	     assertEquals(new Vector(2, 4, 6),v);
	     
	     // =============== Boundary Values Tests ==================
	      // test zero vector from add vectors
	     v2 = new Vector(-1,-2, -3);
	     try {
	    	 v = v1.add(v2);
	         fail("add() for  vector zero does not throw an exception");
	         } catch (Exception e) {}
	    
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		 Vector v1 = new Vector(1, 2, 3);
	     Vector v2 = new Vector(2, 4, 6);
	     Vector v = v1.subtract(v2);
	     // Test validation of subtract function 
	     assertEquals(new Vector(-1, -2, -3),v);
	     
	     // =============== Boundary Values Tests ==================
	      // test zero vector from subtract vectors
	     v2 = new Vector(1,2, 3);
	     try {
	       	 v = v1.subtract(v2);
	         fail("subtract() for  vector zero does not throw an exception");
        } catch (Exception e) {}
	    
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		Vector v1 = new Vector(1, 2, 3);
	    Vector v = v1.scale(-1);
	    // Test validation of scale function 
	    assertEquals(new Vector(-1, -2, -3),v);

	     // =============== Boundary Values Tests ==================
	        // test zero vector from scale vector
	     try {
	       	 v = v1.scale(0);
	         fail("scale() for  vector zero does not throw an exception");
       } catch (Exception e) {}
	    
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		 Vector v1 = new Vector(1, 2, 3);
	     Vector v2 = new Vector(-2, -4, -6);
	     Vector v3 = new Vector(0, 3, -2);
	     // Test validation of dotProduct function with orthogonal vectors
	     assertTrue("dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));
	     // Test validation of dotProduct function 
	     assertTrue("dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));

	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        
        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		 Vector v = new Vector(1, 2, 3);
		// Test if the lengthSquared value is correct
		 assertTrue("lengthSquared() wrong value",isZero(v.lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		 Vector v = new Vector(0, 3, 4);
		// Test if the length value is correct
	     assertTrue("length() wrong value",isZero(v.length() - 5));
	}
	               
	       
	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		 Vector v = new Vector(1, 2, 3);
	     Vector vCopy = new Vector(v);
	     Vector vCopyNormalize = vCopy.normalize();
	     // Test normelize if it dosent  create new vector
	     assertTrue("normalize() function creates a new vector",vCopy==vCopyNormalize);
	     // Test normelize to check if its create  unit vector
	     assertTrue("normalize() result is not a unit vector",isZero(vCopyNormalize.length() - 1));
		
	}
	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		 Vector v = new Vector(1, 2, 3);
		 Vector u = v.normalized();
		// Test normelize if its create new vector
		 assertFalse("normalizated() function does not create a new vector",v==u);
	}
}
