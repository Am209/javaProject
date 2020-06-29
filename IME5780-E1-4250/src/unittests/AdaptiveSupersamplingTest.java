package unittests;
import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
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
public class AdaptiveSupersamplingTest {

	/**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void Threads() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 100), 50, new Point3D(0, 0, 50)));

        scene.addLights(new SpotLight(new Color(500, 300, 0), new Point3D(-50, 50, -50),
                1, 0.00001, 0.00000001, new Vector(1, -1, 2)));

        ImageWriter imageWriter = new ImageWriter("Threads", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.getImageWriter().writeToImage();
    }
    
    @Test
    public void AdaptiveSuperSampling() {
    	Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("Adaptive SuperSampling", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.getImageWriter().writeToImage();
    }
    @Test
    public void planeSpheres() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(new Color(java.awt.Color.BLACK));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries(
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 100, 0, 0), 
						40, new Point3D(0, -40, 500)),
				new Sphere(new Color(75,0,130), new Material(0.2, 0.2, 100, 0, 0), // )
						30, new Point3D(100, -30, 500)),
				new Sphere(new Color(0,100,0), new Material(0.2, 0.2, 100, 0.5, 0), // )
								20, new Point3D(-25, -15,-500)),
				new Sphere(new Color(255,0,0), new Material(0.2, 0.2, 100, 0.5, 0), // )
						6, new Point3D(20, 0,-600)),
				new Plane(new Color(128,128,128),new Material(0.2, 0.2, 100, 0, 1),new Point3D(50,0,500),new Vector(0,-100,-1)));
				 
		scene.addLights( 
				
				new SpotLight(new Color(220, 220, 220),  new Point3D(600, -40, 500), 1, 0.00001, 0.000005, 
						 new Vector(-1, 0, 0)),
				new SpotLight(new Color(95,158,160),  new Point3D(-100, 0, -600), 1, 0.00001, 0.000005, 
						   new Vector(-1, -1, 100)),
				new SpotLight(new Color(0, 0, 128),  new Point3D(20, 0, -650), 1, 0.00001, 0.000005, 
						   new Vector(0, 0, -50))
				);
		
		ImageWriter imageWriter = new ImageWriter("Adaptive SuperSampling improve", 200, 200, 600, 600,9,9);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.getImageWriter().writeToImage();
	}
}
