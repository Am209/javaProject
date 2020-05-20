/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

/**
 * Testing Intersection with Geometries
 *
 */
public class CameraIntegrationTests {
	 final int WIDTH = 3;
	 final int HEIGHT = 3;
	 int counter;
	 List<GeoPoint> list = new LinkedList<GeoPoint>();
	 
	 
	 /**
	  * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	  */
	 @Test
	 public void testRayIntersectionWithSphere() {
		 
		 // TC01:Sphere r=1
		 counter = 0;
		 Sphere sphere1 = new Sphere(1d, new Point3D(0, 0, 3));
		 Camera camera1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
		 for (int i = 0; i < WIDTH; i++) 
			 for (int j = 0; j < HEIGHT; j++) {
				 list = sphere1.findIntersections(camera1.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1,WIDTH, HEIGHT));
				 if(list!=null)
					 counter+=list.size();
				 }
		assertEquals(" Wrong number of points",2,counter);
		 
		 // TC02:Sphere r=2.5
		 counter = 0;
		 Camera camera2 = new Camera(new Point3D(0,0,-0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
		 Sphere sphere2 = new Sphere(2.5d, new Point3D(0, 0, 2.5));
		 for (int i = 0; i < WIDTH; i++) 
			 for (int j = 0; j < HEIGHT; j++) {
				 list = sphere2.findIntersections(camera2.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1,WIDTH, HEIGHT));
				 if(list!=null)
					 counter+=list.size();
				 }
		 assertEquals(" Wrong number of points",18,counter);
		  
		
		// TC03:Sphere r=2
		 counter = 0;
		 Sphere sphere3 = new Sphere(2d, new Point3D(0, 0, 2));
			
		 for (int i = 0; i < WIDTH; i++) 
			 for (int j = 0; j < HEIGHT; j++) {
				 list = sphere3.findIntersections(camera2.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1,WIDTH, HEIGHT));
				 if(list!=null)
					 counter+=list.size();
				 }
		 assertEquals(" Wrong number of points",10,counter);
		  	
		// TC04:Sphere r=4
		 counter = 0;
		 Sphere sphere4 = new Sphere(4d, new Point3D(0, 0, 0));
		 for (int i = 0; i < WIDTH; i++) 
			 for (int j = 0; j < HEIGHT; j++) {
				 list = sphere4.findIntersections(camera2.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1,WIDTH, HEIGHT));
				 if(list!=null)
					 counter+=list.size();
				 }
		assertEquals(" Wrong number of points",9,counter);
	  
	    // TC04:Sphere r=4
	    counter = 0;
	    Sphere sphere5 = new Sphere(0.5d, new Point3D(0, 0, -1));
	    for (int i = 0; i < WIDTH; i++) 
			 for (int j = 0; j < HEIGHT; j++) {
				 list = sphere5.findIntersections(camera2.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1,WIDTH, HEIGHT));
				 if(list!=null)
					 counter+=list.size();
				 }
		assertEquals(" Wrong number of points",0,counter);
	 } 
	 
	 
	 /**
	  * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	  */
	 @Test
	 public void testRayIntersectionWithPlane() {
		 // TC01:
		 counter = 0;
		 Plane p1 = new Plane(new Point3D(0,0,4),new Point3D(1,2,4),new Point3D(0,2,4));
		 Camera camera1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));  
		 for (int i = 0; i < WIDTH; i++) 
			 for (int j = 0; j < HEIGHT; j++) {
				 list = p1.findIntersections(camera1.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1,WIDTH,HEIGHT));
				 if(list!=null)
					 counter+=list.size();
				 }
		assertEquals(" Wrong number of points",9,counter);
		// TC02:
				 counter = 0;
				 Plane p2 = new Plane(new Point3D(0,0,7),new Point3D(1,50,0),new Point3D(1,0,7));
				 for (int i = 0; i < WIDTH; i++) 
					 for (int j = 0; j < HEIGHT; j++) {
						 list = p2.findIntersections(camera1.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1,WIDTH, HEIGHT));
						 if(list!=null)
							 counter+=list.size();
						 }
				assertEquals(" Wrong number of points",9,counter);
		 // TC03:
		 counter = 0;
		 Plane p3 = new Plane(new Point3D(0,0,7),new Point3D(0,7,0),new Point3D(7,0,7));
		 for (int i = 0; i < WIDTH; i++) 
			 for (int j = 0; j < HEIGHT; j++) {
				 list = p3.findIntersections(camera1.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1, WIDTH,HEIGHT));
				 if(list!=null)
					 counter+=list.size();
				 }
		assertEquals(" Wrong number of points",6,counter);
		
	 }
		  

	 /**
	  * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	  */
	 @Test
	 public void testRayIntersectionWithTriangle() {
	  // TC01:
		 counter = 0;
		 Triangle t1 = new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
		 Camera camera1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
		 for (int i = 0; i < WIDTH; i++) 
			 for (int j = 0; j < HEIGHT; j++) {
				 list = t1.findIntersections(camera1.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1,WIDTH,HEIGHT));
				 if(list!=null)
					 counter+=list.size();
				 }
		assertEquals(" Wrong number of points",1,counter);
		
		 // TC02:
		 counter = 0;
		 Triangle t2 = new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
		 for (int i = 0; i < WIDTH; i++) 
			 for (int j = 0; j < HEIGHT; j++) {
				 list = t2.findIntersections(camera1.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1,WIDTH,HEIGHT));
				 if(list!=null)
					 counter+=list.size();
				 }
		assertEquals(" Wrong number of points",2,counter);
		 
	 } 

}
