package unittests;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class FinalTest {
	@Test
	public void finalPicture() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(new Color(java.awt.Color.BLACK));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries(
                //corona
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
				50, new Point3D(-70, 50, 500)),
			
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-70, 102, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-70, 104, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-70, 108, 500)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-70, -2, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-70, -5, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-70, -7, 500)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-123, 50, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-125, 50, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-130, 50, 500)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-17, 50, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-15, 50, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-10, 50, 500)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-100, 80, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-102, 82, 442)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-104, 84, 445)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-40, 80, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-38, 82, 442)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-36, 84, 445)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-40, 20, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-38, 18, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-35, 16, 440)),
				
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-100, 20, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-102, 18, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-104, 16, 440)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-90, 50, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-91, 51, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-93, 52, 440)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-45, 50, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-44, 51, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-42, 52, 440)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-70, 27, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-70, 25, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-70, 22, 440)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-70, 72, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3, new Point3D(-70, 74, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						5, new Point3D(-70, 76, 440)),
				
				
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						35, new Point3D(70, 30, 500)),
						
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(70, 67, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(70, 69, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(70, 71, 500)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(70, -7, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(70, -9, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(70, -11, 500)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(35, 30, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(33, 30, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(31, 30, 500)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(107, 30, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(109, 30, 500)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(111, 30, 500)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(85, 55, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(87, 57, 442)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(89, 59, 445)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(45, 50, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(43, 52, 442)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(41, 54, 445)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(90, 15, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(92, 13, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(94, 11, 440)),
				
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(44, 15, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(42, 13, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(40, 11, 440)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(52, 30, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(51, 31, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(49, 32, 440)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(85, 30, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(86, 31, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(87, 32, 440)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(70, 15, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(70, 14, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(70, 12, 440)),
				
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(70, 45, 450)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						2, new Point3D(70, 46, 445)),
				new Sphere(new Color(65,105,225), new Material(0.5, 0.5, 100, 0.6, 0), 
						3.5, new Point3D(70, 49, 440)),		
				
				//Mirror
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1),
						new Point3D(-200, 100, 1500),new Point3D(-200, -150, 1500), new Point3D(200, -150,1500)),
				
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1),
					new Point3D(-200,100, 1500),new Point3D(200, -150, 1500), new Point3D(200, 100,1500)),
				 
				 
				 
				
				
				//Characters       
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(130, -100, 1200), new Point3D(170, -96, 1200), new Point3D(170, -100, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(130, -100, 1200), new Point3D(130, -96, 1200), new Point3D(170, -96, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(166, -56, 1200), new Point3D(170, -56, 1200), new Point3D(170, -96, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(166, -56, 1200), new Point3D(166, -96, 1200), new Point3D(170, -96, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(144, -66, 1200), new Point3D(140, -66, 1200), new Point3D(144, -26, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(140, -66, 1200), new Point3D(140, -26, 1200), new Point3D(144, -26, 1200)),
				
				
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(124, -100, 1200), new Point3D(124, -56, 1200), new Point3D(120, -100, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(120, -100, 1200), new Point3D(120, -56, 1200), new Point3D(124, -56, 1200)),
				
				
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(114, -100, 1200), new Point3D(114, -96, 1200), new Point3D(74, -100, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(74, -100, 1200), new Point3D(74, -96, 1200), new Point3D(114, -96, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(114, -56, 1200), new Point3D(110, -56, 1200), new Point3D(114, -96, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(110, -56, 1200), new Point3D(110, -96, 1200), new Point3D(114, -96, 1200)),

				
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(68, -100, 1200), new Point3D(68, -56, 1200), new Point3D(64, -100, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(64, -100, 1200), new Point3D(64, -56, 1200), new Point3D(68, -56, 1200)),
				
				
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(58, -100, 1200), new Point3D(58, -56, 1200), new Point3D(54, -100, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(54, -100, 1200), new Point3D(54, -56, 1200), new Point3D(58, -56, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(54, -60, 1200), new Point3D(54, -56, 1200), new Point3D(14, -60, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(14, -60, 1200), new Point3D(14, -56, 1200), new Point3D(54, -56, 1200)),
				
				
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(8, -100, 1200), new Point3D(8, -96, 1200), new Point3D(-32, -100, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(-32, -100, 1200), new Point3D(-32, -96, 1200), new Point3D(8, -96, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(8, -56, 1200), new Point3D(4, -56, 1200), new Point3D(8, -96, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(4, -56, 1200), new Point3D(4, -96, 1200), new Point3D(8, -96, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(-26, -56, 1200), new Point3D(-22, -56, 1200), new Point3D(-26, -76, 1200)),
				new Triangle(new Color(65,105,225), new Material(0.5, 0.5, 100),
                        new Point3D(-22, -56, 1200), new Point3D(-26, -76, 1200), new Point3D(-22, -76, 1200))
				 

				);
		
		scene.addLights( 
				new SpotLight(new Color(500, 300, 0), new Point3D(-2000, 2000, -50),
		                1, 0.0001, 0.0000001, new Vector(1, -1, 2)),
				//new DirectionalLight(new Color(65,105,225), new Vector(0, 50, -0.5)),
				 
				new SpotLight(new Color(500, 300, 0),new Point3D(100, -150, 1400),
		                1, 0.0001, 0.0000001, new Vector(-1, 1, 4)),
				 
				new SpotLight(new Color(java.awt.Color.RED), //
						new Point3D(-70, 50, 500), 1, 4E-4, 2E-5, new Vector(0, 0, -1))
				);
		
		ImageWriter imageWriter = new ImageWriter("final picture", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();;

		render.renderImage();
		render.getImageWriter().writeToImage();
	}

}
