package src;
import java.awt.Color;

/**
 * Class that references a pixel in a picture. A pixel in a picture is located
 * 	at a particular set of x- and y-coordinates. This class allows us to get
 * 	and set the red, green, blue, and alpha values of a particular pixel. This
 * 	class also allows us to get and set the color of the pixel using a Color
 * 	object.
 * 
 * @author Barb Ericson (ericson@cc.gatech.edu)
 * 	(Copyright Georgia Institute of Technology 2004)
 * @author Modified by Colleen Lewis (colleenl@berkeley.edu),
 * 	Jonathan Kotker (jo_ko_berkeley@berkeley.edu),
 * 	Kaushik Iyer (kiyer@berkeley.edu), George Wang (georgewang@berkeley.edu),
 * 	and David Zeng (davidzeng@berkeley.edu), for use in CS61BL, the data
 * 	structures course at University of California, Berkeley. 
 */
public class Pixel
{

	//////////////////////////////// Fields ///////////////////////////////////

	/** The SimplePicture this Pixel belongs to */
	private SimplePicture picture;

	/** x location of this Pixel in the SimplePicture; (0,0) is top left. */
	private int x; 

	/** y location of this Pixel in the SimplePicture; (0,0) is top left. */
	private int y; 

	//////////////////////////// Constructors /////////////////////////////////

	/** 
	 * A constructor that takes the x and y location for this Pixel and the
	 * 	SimplePicture that this Pixel is coming from.
	 * 
	 * @param picture The SimplePicture holding this Pixel.
	 * @param x The x location of the Pixel.
	 * @param y The y location of the Pixel.
	 */
	public Pixel(SimplePicture picture, int x, int y) {
		this.picture = picture;
		this.x = x;
		this.y = y;
	}

	////////////////////////////////// Methods ////////////////////////////////

	/**
	 * @return The x location of this Pixel in the SimplePicture.
	 */
	public int getX() { return x; }

	/**
	 * @return The y location of this Pixel in the SimplePicture.
	 */
	public int getY() { return y; }

	/**
	 * @param value Value to extract bits from.
	 * @param pos Position to start extracting bits from.
	 * @return 8 bits from value, starting at pos.
	 */
	private static int extractEightBits(int value, int pos) {
		return (value >> pos) & 0xff;
	}

	/**
	 * @return The amount of alpha (transparency) at this Pixel.
	 * 	The value varies from 0 to 255.
	 */
	public int getAlpha() {
		/* Get the value at the location from the picture as a
		 * 32-bit int, with alpha, red, green, blue each taking
		 * 8 bits from left to right. */
		int value = picture.getBasicPixel(x,y);

		int alpha = extractEightBits(value, 24);
		return alpha;
	}

	/**
	 * @return The amount of red at this Pixel. The value varies
	 * 	from 0 for none to 255 for maximum.
	 */
	public int getRed() { 
		/* Get the value at the location from the picture as a
		 * 32-bit int, with alpha, red, green, blue each taking
		 * 8 bits from left to right. */
		int value = picture.getBasicPixel(x,y);

		int red = extractEightBits(value, 16);
		return red;
	}

	/**
	 * @param value A color value.
	 * 
	 * @return The red component of the provided color value.
	 */
	public static int getRed(int value)
	{
		int red = extractEightBits(value, 16);
		return red;
	}

	/**
	 * @return The amount of green at this Pixel. The value varies
	 * 	from 0 for none to 255 for maximum.
	 */
	public int getGreen() { 
		/* Get the value at the location from the picture as a
		 * 32-bit int, with alpha, red, green, blue each taking
		 * 8 bits from left to right. */
		int value = picture.getBasicPixel(x,y);

		int green = extractEightBits(value, 8);
		return green;
	}

	/**
	 * @param value A color value.
	 * 
	 * @return The green component of the provided color value.
	 */
	public static int getGreen(int value) {
		int green = extractEightBits(value, 8);
		return green;
	}

	/**
	 * @return The amount of blue at this Pixel. The value varies
	 * 	from 0 for none to 255 for maximum.
	 */
	public int getBlue() { 
		/* Get the value at the location from the DigitalPicture
		 * as a 32-bit int, with alpha, red, green, blue each taking
		 * 8 bits from left to right. */
		int value = picture.getBasicPixel(x,y);

		int blue = extractEightBits(value, 0);
		return blue;
	}

	/**
	 * @param value A color value.
	 * 
	 * @return The blue component of the provided color value.
	 */
	public static int getBlue(int value)
	{
		int blue = extractEightBits(value, 0);
		return blue;
	}

	/**
	 * @return A Color object representing the Pixel color.
	 */
	public Color getColor() { 
		/* Get the value at the location from the picture as a 32-bit int,
		 * with alpha, red, green, blue each taking 8 bits from left to
		 * right.
		 */
		int value = picture.getBasicPixel(x, y);

		return new Color(getRed(value), getGreen(value), getBlue(value));
	}

	/**
	 * Set the Pixel color to the Color object provided.
	 * 
	 * @param newColor The new color to use.
	 */
	public void setColor(Color newColor) {
		// Set the red, green, and blue values.
		int red = newColor.getRed();
		int green = newColor.getGreen();
		int blue = newColor.getBlue();

		// Update the associated picture.
		updatePicture(this.getAlpha(), red, green, blue);
	}

	/**
	 * Updates the picture based on the color values provided for
	 * 	this Pixel.
	 * 
	 * @param alpha The alpha (transparency) at this Pixel.
	 * @param red The red value for the color at this Pixel.
	 * @param green The green value for the color at this Pixel.
	 * @param blue The blue value for the color at this Pixel.
	 */
	public void updatePicture(int alpha, int red, int green, int blue) {
		int value = (alpha << 24) + (red << 16) + (green << 8) + blue;

		picture.setBasicPixel(x, y, value);
	}

	/**
	 * Corrects a color value to be within 0 and 255.
	 * 
	 * @param value The value to correct.
	 * @return A value between 0 and 255.
	 */
	private static int correctValue(int value) {
		if (value < 0)
			value = 0;
		if (value > 255)
			value = 255;

		return value;
	}

	/**
	 * Sets the red color component to a new red value.
	 * 
	 * @param value The new value to use.
	 */
	public void setRed(int value) {
		// Set the red value to the corrected value.
		int red = correctValue(value);

		// Update the pixel value in the picture.
		updatePicture(getAlpha(), red, getGreen(), getBlue());
	} 

	/**
	 * Sets the green color component to a new green value.
	 * 
	 * @param value The new value to use.
	 */
	public void setGreen(int value) {
		// Set the green value to the corrected value.
		int green = correctValue(value);

		// Update the pixel value in the picture.
		updatePicture(getAlpha(), getRed(), green, getBlue());
	} 

	/**
	 * Sets the blue color component to a new blue value.
	 * 
	 * @param value The new value to use.
	 */
	public void setBlue(int value) {
		// Set the blue value to the corrected value.
		int blue = correctValue(value);

		// Update the pixel value in the picture.
		updatePicture(getAlpha(), getRed(), getGreen(), blue);
	} 

	/**
	 * Sets the alpha (transparency) to a new alpha value.
	 * 
	 * @param value The new value to use.
	 */
	public void setAlpha(int value) {
		// Ensure that the alpha is from 0 to 255 .
		int alpha = correctValue(value);

		// Update the associated picture.
		updatePicture(alpha, getRed(), getGreen(), getBlue());
	} 

	/**
	 * Obtains the distance between the color of this Pixel
	 * 	and the color provided.
	 * 
	 * @param otherColor The color to compare to.
	 * 
	 * @return The distance between this pixel's color and the passed color.
	 */
	public double colorDistance(Color otherColor) {
		double redDistance = this.getRed() - otherColor.getRed();
		double greenDistance = this.getGreen() - otherColor.getGreen();
		double blueDistance = this.getBlue() - otherColor.getBlue();
		double distance = Math.sqrt(redDistance * redDistance + 
				greenDistance * greenDistance +
				blueDistance * blueDistance);
		return distance;
	}

	/**
	 * Computes the color distances between two Color objects.
	 * 
	 * @param color1 A color object.
	 * @param color2 A color object.
	 * 
	 * @return The distance between the two colors.
	 */
	public static double colorDistance(Color color1, Color color2) {
		double redDistance = color1.getRed() - color2.getRed();
		double greenDistance = color1.getGreen() - color2.getGreen();
		double blueDistance = color1.getBlue() - color2.getBlue();
		double distance = Math.sqrt(redDistance * redDistance + 
				greenDistance * greenDistance +
				blueDistance * blueDistance);
		return distance;
	}

	/** 
	 * @return The average of the red, green, and blue values of this Pixel.
	 */
	public int getAverage() {
		double average = (getRed() + getGreen() + getBlue())/3.0;
		return ((int) average);
	}

	/**
	 * @return A String with information about this Pixel.
	 */
	public String toString() {
		return "Pixel at (" + x + ", " + y + ") has color components " +
		"red=" + getRed() + " green=" + getGreen() +
		" blue=" + getBlue();
	}
	
	/**
	 * @return Whether this Pixel is equal to the Pixel provided.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Pixel)) {
			return false;
		}
		
		Pixel otherPixel = (Pixel) obj;
		return (this.getAlpha() == otherPixel.getAlpha()) &&
		(this.getRed() == otherPixel.getRed()) &&
		(this.getGreen() == otherPixel.getGreen()) &&
		(this.getBlue() == otherPixel.getBlue());
	}

} // End of Pixel</tt> class
