/**
 * 
 */
package scene;

import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * Class Scene contains and provides all the field and operation associated to a scene
 *
 */
public class Scene {
	private String _name;
	private Color _background;
	private AmbientLight _ambientLight;
	private Geometries _geometries;
	private Camera _camera;
	private double _distance;
	private List<LightSource> _lights;
	/**
	 * Scene constructor receiving name;
	 * @param _name
	 */
	public Scene(String name) {
		_name = name;
		_geometries = new Geometries();
		_lights = new LinkedList<LightSource>();
	}
	/**
	 * Scene value getter
	 * @return the _name
	 */
	public String getName() {
		return _name;
	}
	/**
	 * Scene value getter
	 * @return the _background
	 */
	public Color getBackground() {
		return _background;
	}
	/**
	 * Scene value getter
	 * @return the _ambientLight
	 */
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}
	/**
	 * Scene value getter
	 * @return the _geometries
	 */
	public Geometries getGeometries() {
		return _geometries;
	}
	/**
	 * Scene value getter
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
	 * Scene value getter
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}
	/**
	 * Scene value getter
	 * @return the _background
	 */
	public Color get_background() {
		return _background;
	}
	/**
	 * Scene value getter
	 * @return the _ambientLight
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}
	/**
	 * Scene value getter
	 * @return the _geometries
	 */
	public Geometries get_geometries() {
		return _geometries;
	}
	/**
	 * Scene value getter
	 * @return the _camera
	 */
	public Camera get_camera() {
		return _camera;
	}
	/**
	 * Scene value getter
	 * @return the _distance
	 */
	public double get_distance() {
		return _distance;
	}
	/**
	 * Scene value getter
	 * @return the _lights
	 */
	public List<LightSource> getLights() {
		return _lights;
	}
	 
	/**
	 * Scene value setter
	 * @param _background the background to set
	 */
	public void setBackground(Color _background) {
		this._background = _background;
	}
	/**
	 * Scene value setter
	 * @param _ambientLight the ambientLight to set
	 */
	public void setAmbientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}
	/**
	 * Scene value setter
	 * @param _camera the camera to set
	 */
	public void setCamera(Camera _camera) {
		this._camera = _camera;
	}
	/**
	 * Scene value setter
	 * @param _distance the distance to set
	 */
	public void setDistance(double _distance) {
		this._distance = _distance;
	}
	/**
	 * The function recieve several geometries and add them to our list of geometries 
	 * @param geometries
	 */
	public void addGeometries(Intersectable... geometries) {
		for(Intersectable g : geometries)
			_geometries.add(g);
	}
	/**
	 * The function recieve several lights and add them to our  list of lights 
	 * @param lights
	 */
	public void addLights(LightSource... lights) { 
		for(LightSource l : lights)
			_lights.add(l);
		
	}
	
	 
}
