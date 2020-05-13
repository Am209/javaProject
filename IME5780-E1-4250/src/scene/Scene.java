/**
 * 
 */
package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * Class Scene contains and provides all the field associated to a scene
 *
 */
public class Scene {
	private String _name;
	private Color _background;
	private AmbientLight _ambientLight;
	private Geometries _geometries;
	private Camera _camera;
	private double _distance;
	/**
	 * constructor 
	 * @param _name
	 */
	public Scene(String name) {
		_name = name;
		_geometries = new Geometries();
	}
	/**
	 * @return the _name
	 */
	public String getName() {
		return _name;
	}
	/**
	 * @return the _background
	 */
	public Color getBackground() {
		return _background;
	}
	/**
	 * @return the _ambientLight
	 */
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}
	/**
	 * @return the _geometries
	 */
	public Geometries getGeometries() {
		return _geometries;
	}
	/**
	 * @return the _camera
	 */
	public Camera getCamera() {
		return _camera;
	}
	/**
	 * @return the _distance
	 */
	public double getDistance() {
		return _distance;
	}
	/**
	 * @param _background the background to set
	 */
	public void setBackground(Color _background) {
		this._background = _background;
	}
	/**
	 * @param _ambientLight the ambientLight to set
	 */
	public void setAmbientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}
	/**
	 * @param _camera the camera to set
	 */
	public void setCamera(Camera _camera) {
		this._camera = _camera;
	}
	/**
	 * @param _distance the distance to set
	 */
	public void setDistance(double _distance) {
		this._distance = _distance;
	}
	/**
	 * The function recieve list of geometries and add them to the class geometries 
	 * @param geometries
	 */
	public void addGeometries(Intersectable... geometries) {
		_geometries.add(geometries);
	}
	
	
	 
}
