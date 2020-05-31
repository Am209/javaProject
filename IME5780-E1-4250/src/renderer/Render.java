/**
 * 
 */
package renderer;

import java.util.List;
import static primitives.Util.*;
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
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
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
					 
					GeoPoint closestPoint =  findCLosestIntersection(ray);
					_imageWriter.writePixel(j, i, closestPoint == null? background : calcColor(closestPoint,ray).getColor());
				}
			}
		}
	}
	/**
	 * The function recieve point and  calculate its color 
	 * @param geoPoint
	 * @param ray
	 * @return color
	 */
	private Color calcColor(GeoPoint geopoint, Ray ray) { 
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, 1.0).add( _scene.get_ambientLight().getIntensity()); 
		}
	/**
	 * The function  calculate the point color using recursion
	 * @param geoPoint
	 * @param ray
	 * @param level
	 * @param k
	 * @return color
	 */
	private Color calcColor(GeoPoint geoPoint,Ray ray,int level, double k) {
		if (level == 1|| k < MIN_CALC_COLOR_K) 
			return Color.BLACK; 
		Color color = geoPoint.getGeometry().getEmmission(); 
		Vector v = geoPoint.getPoint().subtract(_scene.getCamera().get_p()).normalize();
		Vector n = geoPoint.getGeometry().getNormal(geoPoint.getPoint());
		Material material =geoPoint.getGeometry().getMaterial();
		int nShininess = material.get_nShininess();
		double kd = material.get_kD();
		double ks = material.get_kS();
		double kr = material.get_kR();
		double kt = material.get_kT();
		double kkr = k * kr; 
		double kkt = k * kt; 
		for (LightSource lightSource : _scene.getLights()) {
			Vector l = lightSource.getL(geoPoint.getPoint()).normalize();
			if ((n.dotProduct(l)>0&&n.dotProduct(v)>0) || (n.dotProduct(l)<0&&n.dotProduct(v)<0)) {
				double ktr = transparency(lightSource, l, n, geoPoint); 
				if (ktr * k > MIN_CALC_COLOR_K){
					Color lightIntensity = lightSource.getIntensity(geoPoint.getPoint()).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
					calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		
		if (kkr > MIN_CALC_COLOR_K) { 
			Ray reflectedRay = constructReflectedRay(n, geoPoint.getPoint(), ray); 
			GeoPoint reflectedPoint = findCLosestIntersection(reflectedRay); 
			if (reflectedPoint != null) 
				color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr)); 
		}
		
		if (kkt > MIN_CALC_COLOR_K) { 
			Ray refractedRay = constructRefractedRay(n, geoPoint.getPoint(), ray);
			GeoPoint refractedPoint = findCLosestIntersection(refractedRay); 
			if (refractedPoint != null) 
				color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt)); 
		}
		return color;
	} 
	
	/**
	 * The function return the reflected ray
	 * @param Vector n
	 * @param point
	 * @param ray
	 * @return  reflected ray
	 */
	private Ray constructReflectedRay(Vector n, Point3D point, Ray ray) {
		Vector v = ray.get_v();
		Vector r = (v.subtract(n.scale(2*v.dotProduct(n)))).normalize();
		return new Ray(point, r,n);
	}
	/**
	 * The function return the refracted ray
	 * @param Vector n
	 * @param point
	 * @param ray
	 * @return refracted ray
	 */
	private Ray constructRefractedRay(Vector n,Point3D point, Ray ray) {
		return new Ray(point, ray.get_v(),n);
		
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
		Vector r = (l.subtract(n.scale(2*l.dotProduct(n)))).normalize();
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
	 * The function check if the point is in shadow
	 * @param light
	 * @param Vector l
	 * @param Vector n
	 * @param GeoPoint 
	 * @return true or false
	 */

	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
		Vector lightDirection = l.scale(-1); 
		Ray lightRay = new Ray(geoPoint.getPoint(), lightDirection,n);
		List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay); 
		if (intersections==null) 
			return true; 
		double lightDistance = light.getDistance(geoPoint.getPoint()); 
		for (GeoPoint gp : intersections) {
			if (alignZero(gp.getPoint().distance(geoPoint.getPoint())-lightDistance) <= 0  && gp.getGeometry().getMaterial().get_kT() == 0)
				return false; 
		}
		return true;
	}
	/**
	 * The function return the  transparency of the point
	 * @param light
	 * @param Vector l
	 * @param Vector n
	 * @param GeoPoint 
	 * @return true or false
	 */
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geoPoint) { 
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geoPoint.getPoint(), lightDirection, n);
		List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
		if (intersections == null) 
			return 1.0;
		double lightDistance = light.getDistance(geoPoint.getPoint()); 
		double ktr = 1.0; 
		for (GeoPoint gp : intersections) {
			if (alignZero(gp.getPoint().distance(geoPoint.getPoint())-lightDistance) <= 0) {
				ktr *= gp.getGeometry().getMaterial().get_kT(); 
				if (ktr < MIN_CALC_COLOR_K) 
					return 0.0; 
				} 
			} 
		return ktr;
	}

	/**
	 * The function find the closest intersection point to the ray point
	 * @param ray
	 */
	private GeoPoint findCLosestIntersection(Ray ray) {
		List<GeoPoint> l = _scene.getGeometries().findIntersections(ray);
		if(l == null)
			return null;
		double minDistance = Double.MAX_VALUE ,distance;
		GeoPoint closestPoint = null;
		for(GeoPoint gp: l) {
			distance = gp.getPoint().distance(ray.get_p()); 
			if(distance<minDistance) {
				minDistance = distance;
				closestPoint = gp;
			}
		}
		return closestPoint;
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
