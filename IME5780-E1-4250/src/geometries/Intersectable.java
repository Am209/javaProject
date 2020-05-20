/**
 * 
 */
package geometries;

import java.util.List;
import primitives.Point3D;
import primitives.Ray;

/**
 * Interface Intersectable represent all the Geometries that rays can intersect them
 *
 */
public interface Intersectable {
	/**
	 * Class GeoPoint is static class representing Point on Geometry  
	 *
	 */
	public static class GeoPoint {
	    public Geometry _geometry;
	    public Point3D _point;
		/**
		 * Constructor
		 * @param geometry
		 * @param point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			_geometry = geometry;
			_point = point;
		}
		/**
		 * @return the _geometry
		 */
		public Geometry getGeometry() {
			return _geometry;
		}
		/**
		 * @return the _point
		 */
		public Point3D getPoint() {
			return _point;
		}
		 
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (_geometry == null) {
				if (other._geometry != null)
					return false;
			} else if (!_geometry.equals(other._geometry))
				return false;
			if (_point == null) {
				if (other._point != null)
					return false;
			} else if (!_point.equals(other._point))
				return false;
			return true;
		}
		
	    
	}

	public List<GeoPoint> findIntersections(Ray ray);

}
