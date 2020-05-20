/**
 * 
 */
package renderer;

import java.util.List;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

/**
 * Class Render contains scene and imageWriter and responsible to render an image
 *
 */
public class Render {
	private ImageWriter _imageWriter;
	private Scene _scene;
	/**
	 * Render constructor receiving imageWriter and scene
	 * @param _imageWriter
	 * @param _scene
	 */
	public Render(ImageWriter imageWriter, Scene scene) {
		_imageWriter = imageWriter;
		_scene = scene;
	}
	
	/**
	 * Render value getter
	 * @return the _imageWriter
	 */
	public ImageWriter getImageWriter() {
		return _imageWriter;
	}

	/**
	 * Render value getter
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
		for(int i = 0; i < nX; i++) {
			for(int j = 0; j < nY; j++) {
				Ray ray =  camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
				List<GeoPoint> intersectionPoints = geometries.findIntersections(ray); 
				if (intersectionPoints==null) {
					_imageWriter.writePixel(j, i, background); 
					}
				else {
					 
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
				}
			}
		}
	}
	/**
	 * The function recieve point and return the points lights intensity color
	 * @param _imageWriter
	 * @return color
	 */
	private Color calcColor(GeoPoint geoPoint) {
		Color color = _scene.getAmbientLight().getIntensity();
		color = color.add(geoPoint.getGeometry().getEmmission());
		Vector v = geoPoint.getPoint().subtract(_scene.getCamera().get_p()).normalize();
		Vector n = geoPoint.getGeometry().getNormal(geoPoint.getPoint());
		Material material =geoPoint.getGeometry().getMaterial();
		int nShininess = material.get_nShininess();
		double kd = material.get_kD();
		double ks = material.get_kS();
		for (LightSource lightSource : _scene.getLights()) {
			Vector l = lightSource.getL(geoPoint.getPoint()).normalize();
			if ((n.dotProduct(l)>0&&n.dotProduct(v)>0) || (n.dotProduct(l)<0&&n.dotProduct(v)<0)) {
				Color lightIntensity = lightSource.getIntensity(geoPoint.getPoint());
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
				calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		return color;
	} 
	/**
	 * The function calculate the specular which effact the color
	 * @param ks
	 * @param Vector l
	 * @param Vector n
	 * @param Vector v
	 * @param nShininess
	 * @param Color IL - lightIntensity
	 * @return color
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color IL) {
		Vector r = l.subtract(n.scale(2*l.dotProduct(n)));
		r.normalize();
		double vr = -v.dotProduct(r);
		if(vr<=0)
			return Color.BLACK;
		return IL.scale(ks*Math.pow(vr, nShininess));
	}
	/**
	 * The function calculate the diffusive which effact the color
	 * @param kd
	 * @param Vector l
	 * @param Vector n
	 * @param Color IL-lightIntensity
	 * @return color
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color IL) {
		return IL.scale(kd*Math.abs(l.dotProduct(n)));
	}

	/**
	 * The function recieve list of points and return the closet point
	 * @param List<Point3D> points
	 * @return point
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> points){
		Point3D camPoint = _scene.getCamera().get_p();
		GeoPoint point  = points.get(0);
		for(GeoPoint p : points) 
			if(point.getPoint().distance(camPoint) > p.getPoint().distance(camPoint))
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
