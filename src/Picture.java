package src;

import java.awt.*;
import java.util.LinkedList;
import java.net.URL;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality and picture effects.
 * 
 * @author Barb Ericson (ericson@cc.gatech.edu) (Copyright Georgia Institute of
 *         Technology 2004)
 * @author Modified by Colleen Lewis (colleenl@berkeley.edu), Jonathan Kotker
 *         (jo_ko_berkeley@berkeley.edu), Kaushik Iyer (kiyer@berkeley.edu),
 *         George Wang (georgewang@berkeley.edu), and David Zeng
 *         (davidzeng@berkeley.edu), for use in CS61BL, the data structures
 *         course at University of California, Berkeley.
 */
public class Picture extends SimplePicture {

	// ///////////////////////// Static Variables //////////////////////////////

	// Different axes available to flip a picture.
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final int FORWARD_DIAGONAL = 3;
	public static final int BACKWARD_DIAGONAL = 4;

	// Different Picture objects for the bitmaps used in ASCII art conversion.
	private static Picture BMP_AMPERSAND;
	private static Picture BMP_APOSTROPHE;
	private static Picture BMP_AT;
	private static Picture BMP_BAR;
	private static Picture BMP_COLON;
	private static Picture BMP_DOLLAR;
	private static Picture BMP_DOT;
	private static Picture BMP_EXCLAMATION;
	private static Picture BMP_GRAVE;
	private static Picture BMP_HASH;
	private static Picture BMP_PERCENT;
	private static Picture BMP_SEMICOLON;
	private static Picture BMP_SPACE;

	// ////////////////////////// Constructors /////////////////////////////////

	/**
	 * A constructor that takes no arguments.
	 */
	public Picture() {
		super();
	}

	/**
	 * Creates a Picture from the file name provided.
	 * 
	 * @param fileName
	 *            The name of the file to create the picture from.
	 */
	public Picture(String fileName) {
		// Let the parent class handle this fileName.
		super(fileName);
	}

	/**
	 * Creates a Picture from the width and height provided.
	 * 
	 * @param width
	 *            the width of the desired picture.
	 * @param height
	 *            the height of the desired picture.
	 */
	public Picture(int width, int height) {
		// Let the parent class handle this width and height.
		super(width, height);
	}

	/**
	 * Creates a copy of the Picture provided.
	 * 
	 * @param pictureToCopy
	 *            Picture to be copied.
	 */
	public Picture(Picture pictureToCopy) {
		// Let the parent class do the copying.
		super(pictureToCopy);
	}

	/**
	 * Creates a copy of the SimplePicture provided.
	 * 
	 * @param pictureToCopy
	 *            SimplePicture to be copied.
	 */
	public Picture(SimplePicture pictureToCopy) {
		// Let the parent class do the copying.
		super(pictureToCopy);
	}

	// ///////////////////////////// Methods ///////////////////////////////////

	/**
	 * @return A string with information about the picture, such as filename,
	 *         height, and width.
	 */
	public String toString() {
		String output = "Picture, filename = " + this.getFileName() + ","
				+ " height = " + this.getHeight() + ", width = "
				+ this.getWidth();
		return output;
	}

	// ///////////////////// PROJECT 1 BEGINS HERE /////////////////////////////

	/*
	 * Each of the methods below is constructive: in other words, each of the
	 * methods below generates a new Picture, without permanently modifying the
	 * original Picture.
	 */

	// ////////////////////////////// Level 1 //////////////////////////////////

	/**
	 * Converts the Picture into grayscale. Since any variation of gray is
	 * obtained by setting the red, green, and blue components to the same
	 * value, a Picture can be converted into its grayscale component by setting
	 * the red, green, and blue components of each pixel in the new picture to
	 * the same value: the average of the red, green, and blue components of the
	 * same pixel in the original.
	 * 
	 * @return A new Picture that is the grayscale version of this Picture.
	 */
	public Picture grayscale() {
		Picture newPicture = new Picture(this);

		int pictureHeight = this.getHeight();
		int pictureWidth = this.getWidth();

		for (int x = 0; x < pictureWidth; x++) {
			for (int y = 0; y < pictureHeight; y++) {
				newPicture.setPixelToGray(x, y);
			}
		}
		return newPicture;
	}

	/**
	 * Helper method for grayscale() to set a pixel at (x, y) to be gray.
	 * 
	 * @param x
	 *            The x-coordinate of the pixel to be set to gray.
	 * @param y
	 *            The y-coordinate of the pixel to be set to gray.
	 */
	private void setPixelToGray(int x, int y) {
		Pixel currentPixel = this.getPixel(x, y);
		int average = currentPixel.getAverage();
		currentPixel.setRed(average);
		currentPixel.setGreen(average);
		currentPixel.setBlue(average);
	}

	/**
	 * Test method for setPixelToGray. This method is called by the JUnit file
	 * through the public method Picture.helpersWork().
	 */
	private static boolean setPixelToGrayWorks() {
		Picture bg = Picture.loadPicture("Creek.bmp");
		Pixel testPixel = bg.getPixel(10, 10);
		bg.setPixelToGray(10, 10);
		int goalColor = (int) testPixel.getAverage();
		int originalAlpha = testPixel.getColor().getAlpha();
		boolean redCorrect = testPixel.getRed() == goalColor;
		boolean greenCorrect = testPixel.getGreen() == goalColor;
		boolean blueCorrect = testPixel.getBlue() == goalColor;
		boolean alphaCorrect = testPixel.getAlpha() == originalAlpha;
		return redCorrect && greenCorrect && blueCorrect && alphaCorrect;
	}

	/**
	 * This method provide JUnit access to the testing methods written within
	 * Picture.java
	 */
	public static boolean helpersWork() {
		if (!Picture.setPixelToGrayWorks()) {
			return false;
		}
		if (!Picture.negateWorks()) {
			return false;
		}
		if (!Picture.lightenWorks()) {
			return false;
		}
		if (!Picture.darkenWorks()) {
			return false;
		}
		if (!Picture.addRedWorks()) {
			return false;
		}
		if (!Picture.addGreenWorks()) {
			return false;
		}
		if (!Picture.addBlueWorks()) {
			return false;
		}
		if (!Picture.averagePatchWorks1()) {
			return false;
		}
		if (!Picture.averagePatchWorks2()) {
			return false;
		}
		if (!Picture.toPosWorks()) {
			return false;
		}
		if (!Picture.colorDistanceWorks()) {
			return false;
		}
		if(!accumChunkColorWorks()) {
			return false;
		}
		if(!replaceWithAsciiWorks()){
			return false;
		}
		return true;
	}

	/**
	 * Converts the Picture into its photonegative version. The photonegative
	 * version of an image is obtained by setting each of the red, green, and
	 * blue components of every pixel to a value that is 255 minus their current
	 * values.
	 * 
	 * @return A new Picture that is the photonegative version of this Picture.
	 */
	public Picture negate() {
		Picture negPicture = new Picture(this);

		int picHeight = this.getHeight();
		int picWidth = this.getWidth();

		for (int h = 0; h < picHeight; h++) {
			for (int w = 0; w < picWidth; w++) {
				negPicture.setPixelToNegative(w, h);
			}
		}
		return negPicture;
	}

	/**
	 * Convenience method to set each pixel to its negative color.
	 * 
	 * @param x
	 *            The x coor of the pixel to negate.
	 * @param y
	 *            The y coor of the pixel to negate.
	 */
	private void setPixelToNegative(int x, int y) {
		Pixel currentPixel = this.getPixel(x, y);
		int newRed = 255 - currentPixel.getRed();
		int newGreen = 255 - currentPixel.getGreen();
		int newBlue = 255 - currentPixel.getBlue();
		currentPixel.setRed(newRed);
		currentPixel.setGreen(newGreen);
		currentPixel.setBlue(newBlue);
	}

	/**
	 * Test method for negate() This method is called by the JUnit file through
	 * the public method Picture.helpersWork().
	 */
	private static boolean negateWorks() {
		Picture correctPic = Picture.loadPicture("Creek_negate.bmp");
		Picture testPic = Picture.loadPicture("Creek.bmp").negate();
		Pixel correctPixel = correctPic.getPixel(10, 10);
		Pixel testPixel = testPic.getPixel(10, 10);
		int originalAlpha = correctPixel.getColor().getAlpha();
		boolean redCorrect = correctPixel.getRed() == testPixel.getRed();
		boolean greenCorrect = correctPixel.getGreen() == testPixel.getGreen();
		boolean blueCorrect = correctPixel.getBlue() == testPixel.getBlue();
		boolean alphaCorrect = originalAlpha == testPixel.getAlpha();
		return redCorrect && greenCorrect && blueCorrect && alphaCorrect;
	}

	/**
	 * Creates an image that is lighter than the original image. The range of
	 * each color component should be between 0 and 255 in the new image. The
	 * alpha value should not be changed.
	 * 
	 * @return A new Picture that has every color value of the Picture increased
	 *         by the lightenAmount.
	 */
	public Picture lighten(int lightenAmount) {
		Picture lightPicture = new Picture(this);

		int picHeight = this.getHeight();
		int picWidth = this.getWidth();

		for (int h = 0; h < picHeight; h++) {
			for (int w = 0; w < picWidth; w++) {
				lightPicture.lightenPixel(w, h, lightenAmount);
			}
		}
		return lightPicture;
	}

	/**
	 * Convenience method to lighten each pixel by param amount.
	 * 
	 * @param x
	 *            The x coor of the pixel to lighten.
	 * @param y
	 *            The y coor of the pixel to lighten.
	 * @param amount
	 *            The amount to lighten by.
	 */
	private void lightenPixel(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		int newRed = currentPixel.getRed() + amount;
		int newGreen = currentPixel.getGreen() + amount;
		int newBlue = currentPixel.getBlue() + amount;
		currentPixel.setRed(newRed);
		currentPixel.setGreen(newGreen);
		currentPixel.setBlue(newBlue);
	}

	/**
	 * Test method for lighten. This method is called by the JUnit file through
	 * the public method Picture.helpersWork().
	 */
	private static boolean lightenWorks() {
		Picture bg = Picture.loadPicture("Creek.bmp");
		Pixel correctPixel = bg.getPixel(10, 10);
		int originalAlpha = correctPixel.getColor().getAlpha();
		Picture newPic = bg.lighten(100);
		Pixel testPixel = newPic.getPixel(10, 10);
		int goalRed = (int) correctPixel.getRed() + 100;
		int goalGreen = (int) correctPixel.getGreen() + 100;
		int goalBlue = (int) correctPixel.getBlue() + 100;
		boolean redCorrect = testPixel.getRed() == goalRed;
		boolean greenCorrect = testPixel.getGreen() == goalGreen;
		boolean blueCorrect = testPixel.getBlue() == goalBlue;
		boolean alphaCorrect = testPixel.getAlpha() == originalAlpha;
		return redCorrect && greenCorrect && blueCorrect && alphaCorrect;
	}

	/**
	 * Creates an image that is darker than the original image.The range of each
	 * color component should be between 0 and 255 in the new image. The alpha
	 * value should not be changed.
	 * 
	 * @return A new Picture that has every color value of the Picture decreased
	 *         by the darkenenAmount.
	 */
	public Picture darken(int darkenAmount) {
		Picture darkPicture = new Picture(this);
		int picHeight = this.getHeight();
		int picWidth = this.getWidth();

		for (int h = 0; h < picHeight; h++) {
			for (int w = 0; w < picWidth; w++) {
				darkPicture.darkenPixel(w, h, darkenAmount);
			}
		}
		return darkPicture;
	}

	/**
	 * Convenience method to darken each pixel by param amount.
	 * 
	 * @param x
	 *            The x coor of the pixel to darken.
	 * @param y
	 *            The y coor of the pixel to darken.
	 * @param amount
	 *            The amount to darken by.
	 */
	private void darkenPixel(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		int newRed = currentPixel.getRed() - amount;
		int newGreen = currentPixel.getGreen() - amount;
		int newBlue = currentPixel.getBlue() - amount;
		currentPixel.setRed(newRed);
		currentPixel.setGreen(newGreen);
		currentPixel.setBlue(newBlue);
	}

	/**
	 * Test method for darken. This method is called by the JUnit file through
	 * the public method Picture.helpersWork().
	 */
	private static boolean darkenWorks() {
		Picture bg = Picture.loadPicture("Creek.bmp");
		Pixel correctPixel = bg.getPixel(10, 10);
		int originalAlpha = correctPixel.getColor().getAlpha();
		Picture newPic = bg.darken(40);
		Pixel testPixel = newPic.getPixel(10, 10);
		int goalRed = (int) correctPixel.getRed() - 40;
		int goalGreen = (int) correctPixel.getGreen() - 40;
		int goalBlue = (int) correctPixel.getBlue() - 40;
		boolean redCorrect = testPixel.getRed() == goalRed;
		boolean greenCorrect = testPixel.getGreen() == goalGreen;
		boolean blueCorrect = testPixel.getBlue() == goalBlue;
		boolean alphaCorrect = testPixel.getAlpha() == originalAlpha;
		return redCorrect && greenCorrect && blueCorrect && alphaCorrect;
	}

	/**
	 * Creates an image where the blue value has been increased by amount.The
	 * range of each color component should be between 0 and 255 in the new
	 * image. The alpha value should not be changed.
	 * 
	 * @return A new Picture that has every blue value of the Picture increased
	 *         by amount.
	 */
	public Picture addBlue(int amount) {
		Picture bluePicture = new Picture(this);
		int picHeight = this.getHeight();
		int picWidth = this.getWidth();

		for (int h = 0; h < picHeight; h++) {
			for (int w = 0; w < picWidth; w++) {
				bluePicture.addBluePixel(w, h, amount);
			}
		}
		return bluePicture;
	}

	/**
	 * Convenience method to add blue each pixel by param amount.
	 * 
	 * @param x
	 *            The x coor of the pixel to add Blue.
	 * @param y
	 *            The y coor of the pixel to add Blue.
	 * @param amount
	 *            The amount of Blue to add.
	 */
	private void addBluePixel(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		int newBlue = currentPixel.getBlue() + amount;
		currentPixel.setBlue(newBlue);
	}

	/**
	 * Test method for addBlue. This method is called by the JUnit file through
	 * the public method Picture.helpersWork().
	 */
	private static boolean addBlueWorks() {
		Picture bg = Picture.loadPicture("Creek.bmp");
		Pixel correctPixel = bg.getPixel(10, 10);
		int originalAlpha = correctPixel.getColor().getAlpha();
		Picture newPic = bg.addBlue(100);
		Pixel testPixel = newPic.getPixel(10, 10);
		int goalRed = (int) correctPixel.getRed();
		int goalGreen = (int) correctPixel.getGreen();
		int goalBlue = (int) correctPixel.getBlue() + 100;
		boolean redCorrect = testPixel.getRed() == goalRed;
		boolean greenCorrect = testPixel.getGreen() == goalGreen;
		boolean blueCorrect = testPixel.getBlue() == goalBlue;
		boolean alphaCorrect = testPixel.getAlpha() == originalAlpha;
		return redCorrect && greenCorrect && blueCorrect && alphaCorrect;
	}

	/**
	 * Creates an image where the red value has been increased by amount. The
	 * range of each color component should be between 0 and 255 in the new
	 * image. The alpha value should not be changed.
	 * 
	 * @return A new Picture that has every red value of the Picture increased
	 *         by amount.
	 */
	public Picture addRed(int amount) {
		Picture redPicture = new Picture(this);
		int picHeight = this.getHeight();
		int picWidth = this.getWidth();
		for (int h = 0; h < picHeight; h++) {
			for (int w = 0; w < picWidth; w++) {
				redPicture.addRedPixel(w, h, amount);
			}
		}
		return redPicture;
	}

	/**
	 * Convenience method to add red each pixel by param amount.
	 * 
	 * @param x
	 *            The x coor of the pixel to add Red.
	 * @param y
	 *            The y coor of the pixel to add Red.
	 * @param amount
	 *            The amount of Red to add.
	 */
	private void addRedPixel(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		int newRed = currentPixel.getRed() + amount;
		currentPixel.setRed(newRed);
	}

	/**
	 * Test method for addRed. This method is called by the JUnit file through
	 * the public method Picture.helpersWork().
	 */
	private static boolean addRedWorks() {
		Picture bg = Picture.loadPicture("Creek.bmp");
		Pixel correctPixel = bg.getPixel(10, 10);
		int originalAlpha = correctPixel.getColor().getAlpha();
		Picture newPic = bg.addRed(100);
		Pixel testPixel = newPic.getPixel(10, 10);
		int goalRed = (int) correctPixel.getRed() + 100;
		int goalGreen = (int) correctPixel.getGreen();
		int goalBlue = (int) correctPixel.getBlue();
		boolean redCorrect = testPixel.getRed() == goalRed;
		boolean greenCorrect = testPixel.getGreen() == goalGreen;
		boolean blueCorrect = testPixel.getBlue() == goalBlue;
		boolean alphaCorrect = testPixel.getAlpha() == originalAlpha;
		return redCorrect && greenCorrect && blueCorrect && alphaCorrect;
	}

	/**
	 * Creates an image where the green value has been increased by amount. The
	 * range of each color component should be between 0 and 255 in the new
	 * image. The alpha value should not be changed.
	 * 
	 * @return A new Picture that has every green value of the Picture increased
	 *         by amount.
	 */
	public Picture addGreen(int amount) {
		Picture greenPicture = new Picture(this);
		int picHeight = this.getHeight();
		int picWidth = this.getWidth();

		for (int h = 0; h < picHeight; h++) {
			for (int w = 0; w < picWidth; w++) {
				greenPicture.addGreenPixel(w, h, amount);
			}
		}
		return greenPicture;
	}

	/**
	 * Convenience method to green each pixel by param amount.
	 * 
	 * @param x
	 *            The x coor of the pixel to add Green.
	 * @param y
	 *            The y coor of the pixel to add Green.
	 * @param amount
	 *            The amount of Green to add.
	 */
	private void addGreenPixel(int x, int y, int amount) {
		Pixel currentPixel = this.getPixel(x, y);
		int newGreen = currentPixel.getGreen() + amount;
		currentPixel.setGreen(newGreen);
	}

	/**
	 * Test method for addGreen. This method is called by the JUnit file through
	 * the public method Picture.helpersWork().
	 */
	private static boolean addGreenWorks() {
		Picture bg = Picture.loadPicture("Creek.bmp");
		Pixel correctPixel = bg.getPixel(10, 10);
		int originalAlpha = correctPixel.getColor().getAlpha();
		Picture newPic = bg.addGreen(100);
		Pixel testPixel = newPic.getPixel(10, 10);
		int goalRed = (int) correctPixel.getRed();
		int goalGreen = (int) correctPixel.getGreen() + 100;
		int goalBlue = (int) correctPixel.getBlue();
		boolean redCorrect = testPixel.getRed() == goalRed;
		boolean greenCorrect = testPixel.getGreen() == goalGreen;
		boolean blueCorrect = testPixel.getBlue() == goalBlue;
		boolean alphaCorrect = testPixel.getAlpha() == originalAlpha;
		return redCorrect && greenCorrect && blueCorrect && alphaCorrect;
	}

	/**
	 * @param x
	 *            x-coordinate of the pixel currently selected.
	 * @param y
	 *            y-coordinate of the pixel currently selected.
	 * @param background
	 *            Picture to use as the background.
	 * @param threshold
	 *            Threshold within which to replace pixels.
	 * 
	 * @return A new Picture where all the pixels in the original Picture, which
	 *         differ from the currently selected pixel within the provided
	 *         threshold (in terms of color distance), are replaced with the
	 *         corresponding pixels in the background picture provided.
	 * 
	 *         If the two Pictures are of different dimensions, the new Picture
	 *         will have length equal to the smallest of the two Pictures being
	 *         combined, and height equal to the smallest of the two Pictures
	 *         being combined. In this case, the Pictures are combined as if
	 *         they were aligned at the top left corner (0, 0).
	 */
	public Picture chromaKey(int xRef, int yRef, Picture background,
			int threshold) {
		Pixel comparePixel = this.getPixel(xRef, yRef);
		Picture chromaPicture = new Picture(this);
		int picHeight = Math.min(background.getHeight(), this.getHeight());
		int picWidth = Math.min(background.getWidth(), this.getWidth());

		for (int h = 0; h < picHeight; h++) {
			for (int w = 0; w < picWidth; w++) {
				if (colorDistance(this.getPixel(w, h), comparePixel) <= threshold) {
					Pixel currentPixel = chromaPicture.getPixel(w, h);
					Pixel backgroundPixel = background.getPixel(w, h);
					currentPixel.setRed(backgroundPixel.getRed());
					currentPixel.setBlue(backgroundPixel.getBlue());
					currentPixel.setGreen(backgroundPixel.getGreen());
				}
			}
		}
		return chromaPicture;
	}

	/**
	 * Convenience method to determine the color distance between two pixels.
	 * Helper method for chromaKey and other methods.
	 * 
	 * @param myPixel
	 *            the initial pixel
	 * @param comparison
	 *            the pixel to compare to.
	 * @return double that is the distance between the two colors
	 */
	private static double colorDistance(Pixel myPixel, Pixel comparison) {
		double red = Math.pow(myPixel.getRed() - comparison.getRed(), 2);
		double blue = Math.pow(myPixel.getBlue() - comparison.getBlue(), 2);
		double green = Math.pow(myPixel.getGreen() - comparison.getGreen(), 2);
		return Math.sqrt(red + blue + green);
	}

	/**
	 * Test method for colorDistance(). This method is called by the JUnit file
	 * through the public method Picture.helpersWork().
	 */
	private static boolean colorDistanceWorks() {
		Picture bg = Picture.loadPicture("Creek.bmp");
		Picture copy = new Picture(bg);
		Pixel myPixel = copy.getPixel(10, 10);
		Pixel otherPixel = copy.getPixel(15, 15);
		myPixel.setColor(new Color(0, 0, 0));
		otherPixel.setColor(new Color(10, 10, 10));
		return colorDistance(myPixel, otherPixel) == Math.sqrt(300);
	}

	// ////////////////////////////// Level 2 //////////////////////////////////

	/**
	 * Rotates this Picture by the integer multiple of 90 degrees provided. If
	 * the number of rotations provided is positive, then the picture is rotated
	 * clockwise; else, the picture is rotated counterclockwise. Multiples of
	 * four rotations (including zero) correspond to no rotation at all.
	 * 
	 * @param rotations
	 *            The number of 90-degree rotations to rotate this image by.
	 * 
	 * @return A new Picture that is the rotated version of this Picture.
	 */
	public Picture rotate(int rotations) {
		int rotNums = toPos(rotations) % 4;
		Picture tmpPic;
		Picture newPic = this;
		while (rotNums > 0) {
			tmpPic = newPic;
			int height = tmpPic.getHeight();
			int width = tmpPic.getWidth();
			newPic = new Picture(height, width);
			for (int h = 0; h < height; h++) {
				for (int w = 0; w < width; w++) {
					Color toSet = tmpPic.getPixel(w, h).getColor();
					Pixel toChange = newPic.getPixel(height - h - 1, w);
					toChange.setColor(toSet);
				}
			}
			rotNums--;
		}
		return newPic;
	}

	/**
	 * Helper method to convert negative parameters of rotate
	 * to positive number of rotations.
	 * @param num
	 * @return the converted number
	 */
	private static int toPos(int num) {
		int result = num;
		while (result < 0) {
			result = result + 4;
		}
		return result;
	}

	/**
	 * Test method for toPos(). This method is called by the JUnit file through
	 * the public method Picture.helpersWork().
	 */
	private static boolean toPosWorks() {
		return (toPos(0) == 0) && (toPos(-3) == 1);
	}

	/**
	 * Flips this Picture about the given axis. The axis can be one of four
	 * static integer constants:
	 * 
	 * (a) Picture.HORIZONTAL: The picture should be flipped about a horizontal
	 * axis passing through the center of the picture. (b) Picture.VERTICAL: The
	 * picture should be flipped about a vertical axis passing through the
	 * center of the picture. (c) Picture.FORWARD_DIAGONAL: The picture should
	 * be flipped about an axis that passes through the north-east and
	 * south-west corners of the picture. (d) Picture.BACKWARD_DIAGONAL: The
	 * picture should be flipped about an axis that passes through the
	 * north-west and south-east corners of the picture.
	 * 
	 * @param axis
	 *            Axis about which to flip the Picture provided.
	 * 
	 * @return A new Picture flipped about the axis provided.
	 */
	public Picture flip(int axis) {
		Picture newPic;
		if (axis == Picture.VERTICAL || axis == Picture.HORIZONTAL) {
			newPic = new Picture(this.getWidth(), this.getHeight());
		} else if (axis == Picture.FORWARD_DIAGONAL
				|| axis == Picture.BACKWARD_DIAGONAL) {
			newPic = new Picture(this.getHeight(), this.getWidth());
		} else {
			throw new IllegalArgumentException("invalid argument");
		}
		int xCoord;
		int yCoord;
		for (int w = 0; w < this.getWidth(); w++) {
			for (int h = 0; h < this.getHeight(); h++) {
				if (axis == Picture.HORIZONTAL) {
					xCoord = w;
					yCoord = this.getHeight() - h - 1;
				} else if (axis == Picture.VERTICAL) {
					xCoord = this.getWidth() - w - 1;
					yCoord = h;
				} else if (axis == Picture.FORWARD_DIAGONAL) {
					xCoord = this.getHeight() - h - 1;
					yCoord = newPic.getHeight() - w - 1;
				} else if (axis == Picture.BACKWARD_DIAGONAL) {
					xCoord = h;
					yCoord = w;
				} else {
					xCoord = w;
					yCoord = h;
				}
				Color toSet = this.getPixel(w, h).getColor();
				Pixel toChange = newPic.getPixel(xCoord, yCoord);
				toChange.setColor(toSet);
			}
		}
		return newPic;
	}

	/**
	 * @param threshold
	 *            Threshold to use to determine the presence of edges.
	 * 
	 * @return A new Picture that contains only the edges of this Picture. For
	 *         each pixel, we separately consider the color distance between
	 *         that pixel and the one pixel to its left, and also the color
	 *         distance between that pixel and the one pixel to the north, where
	 *         applicable. As an example, we would compare the pixel at (3, 4)
	 *         with the pixels at (3, 3) and the pixels at (2, 4). Also, since
	 *         the pixel at (0, 4) only has a pixel to its north, we would only
	 *         compare it to that pixel. If either of the color distances is
	 *         larger than the provided color threshold, it is set to black
	 *         (with an alpha of 255); otherwise, the pixel is set to white
	 *         (with an alpha of 255). The pixel at (0, 0) will always be set to
	 *         white.
	 */
	public Picture showEdges(int threshold) {
		int width = this.getWidth();
		int height = this.getHeight();
		Picture newPic = new Picture(width, height);
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				int norDis = 0;
				int leftDis = 0;
				if (h != 0) {
					norDis = (int) colorDistance(this.getPixel(w, h),
							this.getPixel(w, h - 1));
				}
				if (w != 0) {
					leftDis = (int) colorDistance(this.getPixel(w, h),
							this.getPixel(w - 1, h));
				}
				Pixel thePix = newPic.getPixel(w, h);
				thePix.setAlpha(255);
				if (norDis > threshold || leftDis > threshold) {
					thePix.setColor(Color.black);
				} else {
					thePix.setColor(Color.white);
				}
			}
		}
		return newPic;
	}


	// ////////////////////////////// Level 3 //////////////////////////////////

	/**
	 * @return A new Picture that is the ASCII art version of this Picture. To
	 *         implement this, the Picture is first converted into its grayscale
	 *         equivalent. Then, starting from the top left, the average color
	 *         of every chunk of 10 pixels wide by 20 pixels tall is computed.
	 *         Based on the average value obtained, this chunk will be replaced
	 *         by the corresponding ASCII character specified by the table
	 *         below.
	 * 
	 *         The ASCII characters to be used are available as Picture objects,
	 *         also of size 10 pixels by 20 pixels. The following characters
	 *         should be used, depending on the average value obtained:
	 * 
	 *         0 to 18: # (Picture.BMP_POUND) 19 to 37: @ (Picture.BMP_AT) 38 to
	 *         56: & (Picture.BMP_AMPERSAND) 57 to 75: $ (Picture.BMP_DOLLAR) 76
	 *         to 94: % (Picture.BMP_PERCENT) 95 to 113: | (Picture.BMP_BAR) 114
	 *         to 132: ! (Picture.BMP_EXCLAMATION) 133 to 151: ;
	 *         (Picture.BMP_SEMICOLON) 152 to 170: : (Picture.BMP_COLON) 171 to
	 *         189: ' (Picture.BMP_APOSTROPHE) 190 to 208: ` (Picture.BMP_GRAVE)
	 *         209 to 227: . (Picture.BMP_DOT) 228 to 255: (Picture.BMP_SPACE)
	 * 
	 *         We provide a getAsciiPic method to obtain the Picture object
	 *         corresponding to a character, given any of the static Strings
	 *         mentioned above.
	 * 
	 *         Note that the resultant Picture should be the exact same size as
	 *         the original Picture; this might involve characters being
	 *         partially copied to the final Picture.
	 */

	public Picture convertToAscii() {
		Picture analyzePic = this.grayscale();
		Picture newPic = new Picture(this.getWidth(), this.getHeight());
		for (int w = 0; w < this.getWidth(); w += 10) {
			for (int h = 0; h < this.getHeight(); h += 20) {
				int average = Picture.accumChunkColor(w, h, analyzePic);
				replaceWithAscii(w, h, newPic, getAsciiPic(average));
			}
		}
		return newPic;
	}

	/**
	 * Helper method that accumulates the colors in a chunk of 10 by 20 pixels
	 * and returns the average value.
	 * 
	 * @param initX
	 * @param initY
	 * @param pic
	 * @return int of average value
	 */
	private static int accumChunkColor(int initX, int initY, Picture pic) {
		int average = 0;
		int accumWidth = 0;
		int accumHeight = 0;
		for (int w = initX; w < (initX + 10) && w < pic.getWidth(); w++, accumWidth++) {
			accumHeight = 0;
			for (int h = initY; h < (initY + 20) && h < pic.getHeight(); h++, accumHeight++) {
				average += pic.getPixel(w, h).getAverage();
			}
		}
		return average / (accumWidth * accumHeight);
	}

	/**
	 * Helper method to replace a chunk of a Picture with a replacement picture.
	 * 
	 * @param initX
	 * @param initY
	 * @param pic
	 * @param replace
	 */
	private void replaceWithAscii(int initX, int initY, Picture pic,
			Picture replace) {
		for (int w = initX; w < (initX + 10) && w < pic.getWidth(); w++) {
			for (int h = initY; h < (initY + 20) && h < pic.getHeight(); h++) {
				Color toSet = replace.getPixel(w - initX, h - initY).getColor();
				pic.getPixel(w, h).setColor(toSet);
			}
		}
	}


	/**
	 * Test method for the helper method accumChunkColor(). This method is called
	 * by the JUnit file through the public method Picture.helpersWork().
	 */
	 private static boolean accumChunkColorWorks() {
		 Picture pic = Picture.loadPicture("Creek.bmp");
		 int testAverage = Picture.accumChunkColor(pic.getWidth() - 5, 0, pic);
		 int correctAverage = 0;
		for (int w = pic.getWidth() - 5 ; w < pic.getWidth(); w++) {
			for (int h = 0; h < 20 ; h++) {
				correctAverage += pic.getPixel(w, h).getAverage();
			}
		}
		correctAverage = correctAverage/100;
		return correctAverage == testAverage;
	 }

	private static boolean replaceWithAsciiWorks(){
		Picture pic = Picture.loadPicture("Colleen.bmp");
		Picture ascii = Picture.loadPicture("ampersand.bmp");
		int bmpXCoord = 0;
		pic.replaceWithAscii(pic.getWidth() - 5, 0, pic, ascii);
		for (int w = pic.getWidth() - 5; w < pic.getWidth(); w++) {
			for (int h = 0; h < 20 ; h++) {
				if(!pic.getPixel(w, h).equals(ascii.getPixel(bmpXCoord, h))){
					return false;
				}
			}
			bmpXCoord++;
		}
		return true;
		
	}


	/**
	 * Blurs this Picture. To achieve this, the algorithm takes a pixel, and
	 * sets it to the average value of all the pixels in a square of side (2 *
	 * blurThreshold) + 1, centered at that pixel. For example, if blurThreshold
	 * is 2, and the current pixel is at location (8, 10), then we will consider
	 * the pixels in a 5 by 5 square that has corners at pixels (6, 8), (10, 8),
	 * (6, 12), and (10, 12). If there are not enough pixels available -- if the
	 * pixel is at the edge, for example, or if the threshold is larger than the
	 * image -- then the missing pixels are ignored, and the average is taken
	 * only of the pixels available.
	 * 
	 * The red, blue, green and alpha values should each be averaged separately.
	 * 
	 * @param blurThreshold
	 *            Size of the blurring square around the pixel.
	 * 
	 * @return A new Picture that is the blurred version of this Picture, using
	 *         a blurring square of size (2 * threshold) + 1.
	 */
	public Picture blur(int blurThreshold) {
		if (blurThreshold > 0) {
			int width = this.getWidth();
			int height = this.getHeight();
			Picture newPic = new Picture(width, height);
			for (int w = 0; w < width; w++) {
				for (int h = 0; h < height; h++) {
					Color newColor = averagePatch(w, h, blurThreshold);
					newPic.getPixel(w, h).setColor(newColor);
				}
			}
			return newPic;
		} else {
			return this;
		}
	}

	/**
	 * Helper method that calculates the average Color of a patch of pixels
	 * within the range of the current pixel.
	 * 
	 * @param width
	 * @param height
	 * @param range
	 * @return Color average color
	 */
	public Color averagePatch(int width, int height, int range) {
		int reds = 0;
		int blues = 0;
		int greens = 0;
		int alphas = 0;
		int totPixels = 0;
		for (int x = width - range; x <= width + range; x++) {
			for (int y = height - range; y <= height + range; y++) {
				if (x < 0 || y < 0 || x >= this.getWidth()
						|| y >= this.getHeight()) {
					continue;
				}
				totPixels++;
				Pixel thePix = this.getPixel(x, y);
				reds += thePix.getRed();
				blues += thePix.getBlue();
				greens += thePix.getGreen();
				alphas += thePix.getAlpha();
			}
		}
		return new Color(reds / totPixels, greens / totPixels, blues
				/ totPixels, alphas / totPixels);
	}

	/**
	 * Test method for the helper method averagePatch(). This method is called
	 * by the JUnit file through the public method Picture.helpersWork().
	 */
	private static boolean averagePatchWorks1() {
		Picture testPic = Picture.loadPicture("Creek.bmp");
		int correctReds = 0;
		int correctBlues = 0;
		int correctGreens = 0;
		int correctAlphas = 0;
		for (int w = 0; w < 11; w++) {
			for (int h = 0; h < 11; h++) {
				correctReds += testPic.getPixel(w, h).getRed();
				correctBlues += testPic.getPixel(w, h).getBlue();
				correctGreens += testPic.getPixel(w, h).getGreen();
				correctAlphas += testPic.getPixel(w, h).getAlpha();
			}
		}
		Color correctAverage = new Color(correctReds / 121,
				correctGreens / 121, correctBlues / 121, correctAlphas / 121);
		Color testAverage = testPic.averagePatch(5, 5, 5);
		return correctAverage.equals(testAverage);
	}

	/**
	 * Test method for helper method averagePatch for pixels outside the
	 * Picture's range. This method is called by the JUnit file through the
	 * public method Picture.helpersWork().
	 */
	private static boolean averagePatchWorks2() {
		Picture testPic = Picture.loadPicture("Creek.bmp");
		int correctReds = 0;
		int correctBlues = 0;
		int correctGreens = 0;
		int correctAlphas = 0;
		for (int w = 0; w < 9; w++) {
			for (int h = 0; h < 9; h++) {
				correctReds += testPic.getPixel(w, h).getRed();
				correctBlues += testPic.getPixel(w, h).getBlue();
				correctGreens += testPic.getPixel(w, h).getGreen();
				correctAlphas += testPic.getPixel(w, h).getAlpha();
			}
		}
		Color correctAverage = new Color(correctReds / 81, correctGreens / 81,
				correctBlues / 81, correctAlphas / 81);
		Color testAverage = testPic.averagePatch(3, 3, 5);
		return correctAverage.equals(testAverage);
	}


	/**
	 * @param x
	 *            x-coordinate of the pixel currently selected.
	 * @param y
	 *            y-coordinate of the pixel currently selected.
	 * @param threshold
	 *            Threshold within which to delete pixels.
	 * @param newColor
	 *            New color to color pixels.
	 * 
	 * @return A new Picture where all the pixels connected to the currently
	 *         selected pixel, and which differ from the selected pixel within
	 *         the provided threshold (in terms of color distance), are colored
	 *         with the new color provided.
	 */

	public Picture paintBucket(int x, int y, int threshold, Color newColor) {
		Picture newPic = new Picture(this);
		int width = this.getWidth();
		int height = this.getHeight();
		Pixel comparePix = this.getPixel(x, y);
		LinkedList<Pixel> queue = new LinkedList<Pixel>();
		queue.add(newPic.getPixel(x, y));
		while (queue.size() > 0) {
			Pixel testPix = queue.getLast();
			queue.removeLast();
			int xCoor = testPix.getX();
			int yCoor = testPix.getY();
			if (colorDistance(testPix, comparePix) <= threshold) {
				testPix.setColor(newColor);
				if (yCoor < height - 1) {
					queue.add(newPic.getPixel(xCoor, yCoor + 1));
				}
				if (xCoor < width - 1) {
					queue.add(newPic.getPixel(xCoor + 1, yCoor));
				}
				if (xCoor > 0) {
					queue.add(newPic.getPixel(xCoor - 1, yCoor));
				}
				if (yCoor > 0) {
					queue.add(newPic.getPixel(xCoor, yCoor - 1));
				}
				if (yCoor < height - 1 && xCoor < width - 1) {
					queue.add(newPic.getPixel(xCoor + 1, yCoor + 1));
				}
				if (yCoor < height - 1 && xCoor > 0) {
					queue.add(newPic.getPixel(xCoor - 1, yCoor + 1));
				}
				if (yCoor > 0 && xCoor < width - 1) {
					queue.add(newPic.getPixel(xCoor + 1, yCoor - 1));
				}
				if (xCoor > 0 && yCoor > 0) {
					queue.add(newPic.getPixel(xCoor - 1, yCoor - 1));
				}
			}
		}
		return newPic;
	}

	// /////////////////////// PROJECT 1 ENDS HERE /////////////////////////////

	public boolean equals(Object obj) {
		if (!(obj instanceof Picture)) {
			return false;
		}

		Picture p = (Picture) obj;
		// Check that the two pictures have the same dimensions.
		if ((p.getWidth() != this.getWidth())
				|| (p.getHeight() != this.getHeight())) {
			return false;
		}

		// Check each pixel.
		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
				if (!this.getPixel(x, y).equals(p.getPixel(x, y))) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Helper method for loading a picture in the current directory.
	 */
	protected static Picture loadPicture(String pictureName) {
		URL url = Picture.class.getResource("../Pictures/" + pictureName);
		return new Picture(url.getFile().replaceAll("%20", " "));
	}

	/**
	 * Helper method for loading the pictures corresponding to each character
	 * for the ASCII art conversion.
	 */
	private static Picture getAsciiPic(int grayValue) {
		int asciiIndex = (int) grayValue / 19;

		if (BMP_AMPERSAND == null) {
			BMP_AMPERSAND = Picture.loadPicture("ampersand.bmp");
			BMP_APOSTROPHE = Picture.loadPicture("apostrophe.bmp");
			BMP_AT = Picture.loadPicture("at.bmp");
			BMP_BAR = Picture.loadPicture("bar.bmp");
			BMP_COLON = Picture.loadPicture("colon.bmp");
			BMP_DOLLAR = Picture.loadPicture("dollar.bmp");
			BMP_DOT = Picture.loadPicture("dot.bmp");
			BMP_EXCLAMATION = Picture.loadPicture("exclamation.bmp");
			BMP_GRAVE = Picture.loadPicture("grave.bmp");
			BMP_HASH = Picture.loadPicture("hash.bmp");
			BMP_PERCENT = Picture.loadPicture("percent.bmp");
			BMP_SEMICOLON = Picture.loadPicture("semicolon.bmp");
			BMP_SPACE = Picture.loadPicture("space.bmp");
		}

		switch (asciiIndex) {
		case 0:
			return Picture.BMP_HASH;
		case 1:
			return Picture.BMP_AT;
		case 2:
			return Picture.BMP_AMPERSAND;
		case 3:
			return Picture.BMP_DOLLAR;
		case 4:
			return Picture.BMP_PERCENT;
		case 5:
			return Picture.BMP_BAR;
		case 6:
			return Picture.BMP_EXCLAMATION;
		case 7:
			return Picture.BMP_SEMICOLON;
		case 8:
			return Picture.BMP_COLON;
		case 9:
			return Picture.BMP_APOSTROPHE;
		case 10:
			return Picture.BMP_GRAVE;
		case 11:
			return Picture.BMP_DOT;
		default:
			return Picture.BMP_SPACE;
		}
	}

	public static void main(String[] args) {
		Picture initialPicture = new Picture(
				FileChooser.pickAFile(FileChooser.OPEN));
		initialPicture.explore();

	}

} // End of Picture class
