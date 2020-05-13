/**
 * 
 */
package unittests;
import org.junit.Test;
import renderer.ImageWriter;
/**
 * Testing ImageWriter class
 *
 */
public class ImageWriterTest {
	/**
     * Test method for ImageWriter
     */
	@Test
	public void TestImageWriter(){
		java.awt.Color backgroundColor = new java.awt.Color(0,0,255);
		java.awt.Color gridColor = new java.awt.Color(255,0,0);
		ImageWriter image = new ImageWriter("ViewPlane",1600,1000,800,500);
		for(int i = 0; i < 800; i++)
			for(int j = 0 ; j < 500; j++) {
				if(i % 50 == 0 || j % 50 == 0)
					image.writePixel(i, j, gridColor);
				else
					image.writePixel(i, j,backgroundColor );
			}
		image.writeToImage();
	}

}
