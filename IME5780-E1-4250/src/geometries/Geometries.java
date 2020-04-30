/**
 * 
 */
package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
* Class Geometries representing collection of Intersectable Geometries
* the class implements the interface "Intersectable"
*/
public class Geometries implements Intersectable {
	private List<Intersectable> _list;
	/**
     *  Geometries constructor
     */
	public Geometries() {
		_list = new ArrayList<Intersectable>();
	}
	/**
     * Geometries constructor receiving  list of geometries
     * @param geometries list of geometries
     */
	public Geometries(Intersectable... geometries) {
		_list = new ArrayList<Intersectable>();
		for(Intersectable e : geometries) {
			_list.add(e);
		}
	}
	/**
     * Add function 
     * @param geometries -  list of geometries
     */
	public void add(Intersectable... geometries) {
		for(Intersectable e : geometries) {
			_list.add(e);
		}
	}
	
	/**
	 * The function return a list of the intersections points  of the ray with the Geometries
	 * @param ray
	 * @return List<Point3D>
	 */
	public List<Point3D> findIntersections(Ray ray) {
		List<Point3D> list = new LinkedList<Point3D>();
		for(Intersectable e : _list) {
			List<Point3D> l = e.findIntersections(ray);
			if(l != null)
				list.addAll(l);
		}
		return list.isEmpty() ? null : list;
	}
}
