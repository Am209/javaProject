/**
 * 
 */
package renderer;

import java.util.List;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * Class Render contains scene and imageWriter and responsible to render an image
 *
 */
public class Render {
	private ImageWriter _imageWriter;
	private Scene _scene;
	/**
	 * Constructor
	 * @param _imageWriter
	 * @param _scene
	 */
	public Render(ImageWriter imageWriter, Scene scene) {
		_imageWriter = imageWriter;
		_scene = scene;
	}
	
	/**
	 * @return the _imageWriter
	 */
	public ImageWriter getImageWriter() {
		return _imageWriter;
	}

	/**
	 * @return the _scene
	 */
	public Scene getScene() {
		return _scene;
	}
	/**
	 * The function create and and render an image using the scene and the imageWriter
	 */
	public void renderImage() {
		Camera camera = _scene.getCamera();
		
		Intersectable geometries = _scene.getGeometries(); 		
		java.awt.Color background = _scene.getBackground().getColor();
		int nX =  _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		double distance = _scene.getDistance();
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++) {
				Ray ray =  camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
				List<Point3D> intersectionPoints = geometries.findIntersections(ray); 
				if (intersectionPoints==null) {
					_imageWriter.writePixel(j, i, background);
					}
				else {
					Point3D closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
				}
			}
	}
	/**
	 * The function recieve point and return the points lights intensity color
	 * @param _imageWriter
	 * @return color
	 */
	private Color calcColor(Point3D point) {
		return _scene.getAmbientLight().getIntensity();
	} 
	/**
	 * The function recieve list of points and return the closet point
	 * @param List<Point3D> points
	 * @return point
	 */
	private Point3D getClosestPoint(List<Point3D> points){
		Point3D camPoint = _scene.getCamera().get_p();
		Point3D point  = points.get(0);
		for(Point3D p : points) 
			if(point.distance(camPoint) > p.distance(camPoint))
				point = p;
		return point;
	}
	/**
	 * The function recieve interval and a color and print grid on the image with this color 
	 * @param interval
	 * @param color
	 */
	public void printGrid(int interval, java.awt.Color color){
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				if((i % interval) == 0 || (j % interval) == 0)
					_imageWriter.writePixel(i, j, color);
	}
	

}
