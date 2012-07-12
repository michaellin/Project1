package src;
import java.awt.Color;

import junit.framework.TestCase;
import java.util.LinkedList;
import static org.junit.Assert.*;

/**
 * This testing framework provides basic level tests for 
 * each of the methods, however additional testing will be 
 * required, along with extensive testing of ALL helper methods
 * that you write.
 */
public class PictureTest extends TestCase {
	/**
	 * A method to test the private helper methods within
	 * Picture.java
	 */
	public void testHelpers()
	{
		assertTrue(Picture.helpersWork());
		
	}
	/**
	 * Validate that grayscale works and does not modify the 
	 * original Picture object.
	 */
	public void testGrayscale()
	{
		Picture pic 		= Picture.loadPicture("Creek.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("Creek_grayscale.bmp");
		Picture picTest		= pic.grayscale();
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	/**
	 * Validate that negate works and does not modify the 
	 * original Picture object.
	 */
	public void testNegate()
	{
		Picture pic 		= Picture.loadPicture("Creek.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("Creek_negate.bmp");
		Picture picTest		= pic.negate();
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	/**
	 * Validate that rotate(1) works and does not modify the 
	 * original Picture object.
	 */
	public void testRotate1()
	{
		Picture pic 		= Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("CalRotate1.bmp");
		Picture picTest		= pic.rotate(1);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}

	/**
	 * Validate that rotate(2) works and does not modify the 
	 * original Picture object.
	 */
	public void testRotate2()
	{
		Picture pic 		= Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("CalRotate2.bmp");
		Picture picTest		= pic.rotate(2);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}

	/**
	 * Validate that rotate(3) works and does not modify the 
	 * original Picture object.
	 */
	public void testRotate3()
	{
		Picture pic 		= Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("CalRotate3.bmp");
		Picture picTest		= pic.rotate(3);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	
	public void testRotateNeg(){
		Picture  pic = Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy = new Picture(pic);
		Picture picCorrect = Picture.loadPicture("CalRotate1.bmp");
		Picture picTest = pic.rotate(-3);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	
	public void testRotate6(){
		Picture  pic = Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy = new Picture(pic);
		Picture picCorrect = Picture.loadPicture("CalRotate2.bmp");
		Picture picTest = pic.rotate(6);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	/**
	 * Validate that flip(Picture.HORIZONTAL) works and does not modify the 
	 * original Picture object.
	 */
	public void testFlipHorizontal()
	{
		Picture pic 		= Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("CalHorizontal.bmp");
		Picture picTest		= pic.flip(Picture.HORIZONTAL);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	/**
	 * Validate that flip(Picture.VERTICAL) works and does not modify the 
	 * original Picture object.
	 */
	public void testFlipVertical()
	{
		Picture pic 		= Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("CalVertical.bmp");
		Picture picTest		= pic.flip(Picture.VERTICAL);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	/**
	 * Validate that flip(Picture.FORWARD_DIAGONAL) works and 
	 * does not modify the original Picture object.
	 */
	public void testFlipForwardDiagonal()
	{
		Picture pic 		= Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("CalForwardDiagonal.bmp");
		Picture picTest		= pic.flip(Picture.FORWARD_DIAGONAL);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	/**
	 * Validate that flip(Picture.BACKWARD_DIAGONAL) works and 
	 * does not modify the original Picture object.
	 */
	public void testFlipBackwardDiagonal()
	{
		Picture pic 		= Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("CalBackwardDiagonal.bmp");
		Picture picTest		= pic.flip(Picture.BACKWARD_DIAGONAL);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	
	/**
	 * Tests that a non-recognized integer will throw an IllegalArgumentException
	 * 
	 */
	public void testFlipIllegalArgument(){
		Picture pic 		= Picture.loadPicture("CalOriginal.bmp");
		try{
			pic.flip(-13527);
			assertTrue(false);
		} catch (IllegalArgumentException e){
			assertTrue(true);
		}
	}
	
	/**
	 * Tests flip by flipping in each fashion two times: result should be the original picture
	 * 
	 */
	public void testFlipTwoTimes(){
		Picture pic 		= Picture.loadPicture("CalOriginal.bmp");
		Picture picCopy 	= new Picture(pic);
		for(int i = 0; i < 2; i++){
			picCopy	= picCopy.flip(Picture.BACKWARD_DIAGONAL);
		}
		assertTrue(pic.equals(picCopy));
		for(int i = 0; i < 2; i++){
			picCopy	= picCopy.flip(Picture.FORWARD_DIAGONAL);
		}
		assertTrue(pic.equals(picCopy));
		for(int i = 0; i < 2; i++){
			picCopy	= picCopy.flip(Picture.FORWARD_DIAGONAL);
		}
		assertTrue(pic.equals(picCopy));
		for(int i = 0; i < 2; i++){
			picCopy	= picCopy.flip(Picture.FORWARD_DIAGONAL);
		}
		assertTrue(pic.equals(picCopy));
		
	}
	/**
	 * Validate that blur works and does not modify the 
	 * original Picture object.
	 */
	public void testBlur1()
	{
		Picture pic 		= Picture.loadPicture("Creek.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("Creek_blur.bmp");
		Picture picTest		= pic.blur(3);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	
	/**
	 * Validate that blur will work even when the threshold is
	 * larger than the picture.
	 */
	public void testBlur2() {
		Picture testPic = Picture.loadPicture("dollar.bmp");
		try {
			testPic.blur(30);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	/**
	 * Validate that blur doesn't modify the picture if the threshold given is simply zero.
	 * 
	 */
	public void testBlurZero(){
		Picture pic 		= Picture.loadPicture("Creek.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picTest		= pic.blur(0);
		assertTrue(pic.equals(picCopy));
		assertTrue(pic.equals(picTest));
	}
	/**
	 * Validate that showEdges works and does not modify the 
	 * original Picture object.
	 */
	public void testShowEdges()
	{
		Picture pic 		= Picture.loadPicture("Colleen.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("Colleen_showEdges.bmp");
		Picture picTest		= pic.showEdges(20);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	/**
	 * Validate that chromaKey works and does not modify the 
	 * original Picture object.
	 */
	public void testChromaKey()
	{
		Picture pic 		= Picture.loadPicture("Colleen.bmp");
		Picture bg 			= Picture.loadPicture("Creek.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("Colleen_chromaKey.bmp");
		Picture picTest		= pic.chromaKey(118, 54, bg, 30);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}

	/**
	 * Tests darken on a picture and makes sure that all pixels are black if the parameter is 255
	 * darken
	 */
	
	public void testDarken(){
		Picture pic = Picture.loadPicture("Colleen.bmp");
		Picture testPic = pic.darken(255);
		for(int w = 0; w<testPic.getWidth(); w++){
			for(int h=0; h<testPic.getHeight(); h++){
				assertEquals(testPic.getPixel(w,h).getColor(),(Color.black));
			}
		}
	}
	
	/**
	 * Tests lighten on a picture and makes sure that all pixels are white if parameter is 255
	 */
	public void testLighten(){
		Picture pic = Picture.loadPicture("Colleen.bmp");
		Picture testPic = pic.lighten(255);
		for(int w = 0; w<testPic.getWidth(); w++){
			for(int h=0; h<testPic.getHeight(); h++){
				assertEquals(testPic.getPixel(w,h).getColor(),(Color.white));
			}
		}
	}
	
	/**
	 * Tests the color changing a solid color:
	 * darken
	 */
	public void testColorTranslationsDarker()
	{
		Picture pic = Picture.loadPicture("Gray.bmp");
		Picture darker = Picture.loadPicture("Gray_darker.bmp");
		assertTrue(darker.equals(pic.darken(30)));
			
	}
	/**
	 * Tests the color changing a solid color:
	 * lighten
	 */
	public void testColorTranslationsLighter()
	{
		Picture pic = Picture.loadPicture("Gray.bmp");
		Picture lighter = Picture.loadPicture("Gray_lighter.bmp");
		assertTrue(lighter.equals(pic.lighten(30)));
			
	}
	/**
	 * Tests the color changing a solid color:
	 * addGreen
	 */
	public void testColorTranslationsGreener()
	{
		Picture pic = Picture.loadPicture("Gray.bmp");
		Picture greener	= Picture.loadPicture("Gray_more_green.bmp");
		assertTrue(greener.equals(pic.addGreen(30)));
			
	}
	/**
	 * Tests the color changing a solid color:
	 * addBlue
	 */
	public void testColorTranslationsBluer()
	{
		Picture pic = Picture.loadPicture("Gray.bmp");
		Picture bluer = Picture.loadPicture("Gray_more_blue.bmp");
		assertTrue(bluer.equals(pic.addBlue(30)));
			
	}
	
	/**
	 * Tests the color changing a solid color:
	 * addRed
	 */
	public void testColorTranslationsRedder()
	{
		Picture pic = Picture.loadPicture("Gray.bmp");
		Picture reder = Picture.loadPicture("Gray_more_red.bmp");
		assertTrue(reder.equals(pic.addRed(30)));	
			
	}
	
	/**
	 * Validate that paintBucket works and does not modify the 
	 * original Picture object.
	 */
	public void testPaintBucket()
	{
		Picture pic 		= Picture.loadPicture("Colleen.bmp");
		Picture picCopy 	= new Picture(pic);
		Picture picCorrect	= Picture.loadPicture("Colleen_paintBucket.bmp");
		Picture picTest		= pic.paintBucket(118, 54, 30, new Color(0, 255, 0));
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	
	/**
	 * Validate that convertToAscii works and does not modify the 
	 * original Picture object.
	 */
	public void testConvertToAscii()
	{
		Picture pic         = Picture.loadPicture("mickey.bmp");
		Picture picCopy     = new Picture(pic);
		Picture picCorrect  = Picture.loadPicture("mickey_ascii.bmp");
		Picture picTest     = pic.convertToAscii();
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	
	/**
	 * Validate that showEdges works and does not modify the 
	 * original Picture object (and this picture is cute)
	 */
	public void testShowEdgesMickey()
	{
		Picture pic         = Picture.loadPicture("mickey.bmp");
		Picture picCopy     = new Picture(pic);
		Picture picCorrect  = Picture.loadPicture("mickey_showEdges.bmp");
		Picture picTest     = pic.showEdges(20);
		assertTrue(pic.equals(picCopy));
		assertTrue(picCorrect.equals(picTest));
	}
	
	/**
	 * Validate that showEdges returns a picture with only black and white pixels.
	 */
	public void testShowEdgesColors() {
		Picture pic = Picture.loadPicture("Colleen.bmp");
		Picture testPic = pic.showEdges(30); 
		assertTrue(PictureTest.isOkColors(testPic));
	}
	
	private static boolean isOkColors(Picture pic) {
		boolean isOk = true;
		 for (int w = 0 ; w < pic.getWidth() ; w++) {
			 for (int h = 0 ; h < pic.getHeight() ; h++) {
				 Pixel toCheck = pic.getPixel(w, h);
				 if (!(toCheck.getColor().equals(Color.white) ||
					 toCheck.getColor().equals(Color.black))) {
					 isOk = false;
				 }
			 }
		 }
		 return isOk;
	}
	
	/**
	 * Validate that paintBucket will only paint colors that are linked
	 * to the chosen pixel.
	 */
	public void testPaintBucketCorrect() {
		Picture pic = Picture.loadPicture("Creek.bmp");
		Picture newPic = pic.paintBucket(446, 168, 40, new Color(0, 255, 0));
		Pixel subject = newPic.getPixel(446, 168);
		System.out.println(subject.getAlpha());
		assertTrue(subject.getColor().equals(new Color(0, 255, 0)));
		assertTrue(!newPic.getPixel(152, 167).getColor().equals(new Color(0, 255, 0)));
	}

	/**
	 * Test run-time of blur with different picture sizes.
	 */
	public void testRunTime() {
		LinkedList<Long> times = new LinkedList<Long>();
		for (int n = 5 ; n < 50  ; n += 5) {
			long total = 0;
			for (int r = 0 ; r < 6 ; r++) {
				Picture pic1 = Picture.loadPicture("Creek" + n + "0.bmp");
				long start = System.currentTimeMillis();
				pic1.blur(5);
				long end = System.currentTimeMillis();
				total += (end - start);
			}
			times.add(new Long(total/5));
		}

		System.out.println(times);
	}

	/**
	 * Run-time test that shows run-times of blur with various thresholds. Was used in blur analysis.
	 * Test run-time of blur with different thresholds.
	 */
	public void testRunTimeThreshold(){
		LinkedList<Long> times = new LinkedList<Long>();
		for (int n = 1 ; n < 6 ; n++) {
			long total = 0;
			for (int r = 0 ; r < 6 ; r++) {
				Picture pic1 = Picture.loadPicture("Colleen.bmp");
				long start = System.currentTimeMillis();
				pic1.blur(n);
				long end = System.currentTimeMillis();
				total += (end - start);
			}
			times.add(new Long(total/5));
		}
		System.out.println(times);
		assertTrue(true);
	}
}
