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
	private ImageWriter _imageWriter;
	private Scene _scene;
	
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private int _threads = 1; 
	private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores 
	private boolean _print = false;  // printing progress percentage
	
	
	/** 
	 *  Pixel is an internal helper class whose objects are associated with a Render object that 
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up 
	 *  its progress.<br/> 
	 *   There is a main follow up object and several secondary objects - one in each thread. 
	 */ 
	private class Pixel {
		private long _maxRows = 0;     // Ny 
		 private long _maxCols = 0; // Nx 
		 private long _pixels = 0; // Total number of pixels: Nx*Ny 
		 public volatile int row = 0; // Last processed row 
		 public volatile int col = -1; // Last processed column 
		 private long _counter = 0; // Total number of pixels processed 
		 private int _percents = 0; // Percent of pixels processed 
		 private long _nextCounter = 0; // Next amount of processed pixels for percent progress
		 
		 /** 
		  *  The constructor for initializing the main follow up Pixel object 
		  *   @param maxRows the amount of pixel rows 
		  *    @param maxCols the amount of pixel columns 
		  */
		 public Pixel(int maxRows, int maxCols) {
			 _maxRows = maxRows;
			 _maxCols = maxCols; 
			 _pixels = maxRows * maxCols; 
			 _nextCounter = _pixels / 100;
			 if (Render.this._print) 
				 System.out.printf("\r %02d%%", _percents);
		 }
		 /** 
		  *   Default constructor for secondary Pixel objects 
		  */ 
		 public Pixel() {}	 
		
		 /** 
		  *  Public function for getting next pixel number into secondary Pixel object. 
		  *  The function prints also progress percentage in the console window. 
		  *  @param target target secondary Pixel object to copy the row/column of the next pixel 
		  *  @return true if the work still in progress, -1 if it's done 
		  */
		 public boolean nextPixel(Pixel target) { 
			 int percents = nextP(target); 
			 if (_print && percents > 0) 
				 System.out.printf("\r %02d%%", percents); 
			 if (percents >= 0)
				 return true; 
			 if (_print) 
				 System.out.printf("\r %02d%%", 100); 
			 return false; 
		}
		
		 /** 
		  *  Internal function for thread-safe manipulating of main follow up Pixel object - this function is 
		  *  critical section for all the threads, and main Pixel object data is the shared data of this critical 
		  *  section.<br/> 
		  *  The function provides next pixel number each call. 
		  *  @param target target secondary Pixel object to copy the row/column of the next pixel 
		  *  @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is 
		  *  finished, any other value - the progress percentage (only when it changes) 
		  */ 
		 private synchronized int nextP(Pixel target) { 
			 ++col; 
			  ++_counter; 
			  if (col < _maxCols) {
				  target.row = this.row; 
				  target.col = this.col; 
				  if (_print && _counter == _nextCounter) { 
					  ++_percents;
					  _nextCounter = _pixels * (_percents + 1) / 100; return _percents; 
					  } 
				  return 0; 
				} 
			  ++row; 
			  if (row < _maxRows) {
				  col = 0; 
				  if (_print && _counter == _nextCounter) {
					  ++_percents; _nextCounter = _pixels * (_percents + 1) / 100; 
					  return _percents;
				  }
				  return 0;
				}
			  return -1; 
		 }
		
	}
	
	/** 
	 *  Set multithreading <br> * - if the parameter is 0 - number of coress less SPARE (2) is taken 
	 *  @param threads number of threads 
	 *   @return the Render object itself 
	 */
	public Render setMultithreading(int threads) { 
		if (threads < 0) 
			throw new IllegalArgumentException("Multithreading must be 0 or higher"); 
		if (threads != 0) 
			_threads = threads; 
		else { 
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			_threads = cores <= 2 ? 1 : cores; 
		}
		return this; 
	}
	
	/** * Set debug printing on 
	 * @return the Render object itself
	 */ 
	public Render setDebugPrint() {
		_print = true;
		return this;
	}
	
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
		int nX =  _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		final Pixel thePixel = new Pixel(nY, nX); // Main pixel management object 
		Thread[] threads = new Thread[_threads]; 
		for (int i = _threads - 1; i >= 0; --i) { // create all threads 
			threads[i] = new Thread(() -> 
			{ 
				Pixel pixel = new Pixel(); // Auxiliary thread’s pixel object 
				while (thePixel.nextPixel(pixel)) { 
					Color color = calcPixelColor(nX,nY,pixel.col, pixel.row);
					_imageWriter.writePixel(pixel.col, pixel.row,color.getColor());
				}
			}); 
		} 
		for (Thread thread : threads) // Start all the threads 
			thread.start(); 
		for (Thread thread : threads)       // Wait for all threads to finish  
			try { thread.join(); } catch (Exception e) {} 
		if (_print) 
			System.out.printf("\r100%%\n"); // Print 100%
	}
	
	/**
	 * The function calculate the color of certain pixel in the image
	 * @param nX amount of pixels by Width 
	 * @param nY amount of pixels by Hight
	 * @param pixelColumn 
	 * @param pixelRow
	 * @return color -the pixel color 
	 */
	private Color calcPixelColor(int nX,int nY, int pixelColumn, int pixelRow) {
		Camera camera = _scene.getCamera();
		double distance = _scene.getDistance();
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();
		Ray ray =  camera.constructRayThroughPixel(nX, nY, pixelColumn, pixelRow, distance, width, height);	
		Point3D pixelCenter =  ray.getPoint(distance);
		return  calcPixelColor(1, pixelCenter,width/nX,height/nY);
		
	}
	private Color calcPixelColor(int level, Point3D pixelCenter,double pixelWidth,double pixelHight) {
		Color[] colors = calcPixelEdgesColor(pixelCenter,pixelWidth,pixelHight);
		if(colors[0].equals(colors[1]) && colors[1].equals(colors[2]) && colors[2].equals(colors[3]))
			return colors[0]; 
		else {
			Point3D[] centers = getPixelEdges(pixelCenter,pixelWidth/2.0,pixelHight/2.0);
			Color color1,color2,color3,color4;
			Color color = Color.BLACK; 
			color1 = calcPixelColor(level+1, centers[0],pixelWidth/2.0,pixelHight/2.0);
			color2 = calcPixelColor(level+1, centers[1],pixelWidth/2.0,pixelHight/2.0);
			color3 = calcPixelColor(level+1, centers[2],pixelWidth/2.0,pixelHight/2.0);
			color4 = calcPixelColor(level+1, centers[3],pixelWidth/2.0,pixelHight/2.0);
			return color.add(color1,color2,color3,color4).reduce(4);
		}
	}
	
	private Color[] calcPixelEdgesColor (Point3D pixelCenter,double pixelWidth,double pixelHight) {
		Intersectable geometries = _scene.getGeometries(); 		
		Color background = _scene.getBackground();
		Point3D cameraPoint = _scene.get_camera().get_p();
		Point3D[] edges = getPixelEdges(pixelCenter,pixelWidth, pixelHight);
		Color[] colors = new Color[4];
		for(int i = 0; i < 4; i++) {
			Ray ray = new Ray(cameraPoint,edges[i].subtract(cameraPoint));
			List<GeoPoint> intersectionPoints = geometries.findIntersections(ray); 
			if (intersectionPoints==null) 
				colors[i] = background;                              
			else {
				GeoPoint closestPoint =  findCLosestIntersection(ray);
				colors[i] = calcColor(closestPoint,ray);
			}
		}
		return colors;
	}
	private Point3D[] getPixelEdges (Point3D pixelCenter,double pixelWidth,double pixelHight) {
		Point3D[] edges = new Point3D[4];
		Camera camera = _scene.getCamera();
		edges[0] = pixelCenter.add(camera.get_vRight().scale(pixelWidth/2.0)).add(camera.get_vUp().scale(pixelHight/2.0));     //upright
		edges[1] = pixelCenter.add(camera.get_vRight().scale(-pixelWidth/2.0)).add(camera.get_vUp().scale(pixelHight/2.0));    //upLeft
		edges[2] = pixelCenter.add(camera.get_vRight().scale(pixelWidth/2.0)).add(camera.get_vUp().scale(-pixelHight/2.0));    //downright
		edges[3] = pixelCenter.add(camera.get_vRight().scale(-pixelWidth/2.0)).add(camera.get_vUp().scale(-pixelHight/2.0));   //downLeft 
	
		return edges;
	}
	/**
	 * The function recieve point and  calculate its color 
	 * @param geoPoint
	 * @param ray
	 * @return color -the points color
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
	 * The function return the transparency of the point
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
