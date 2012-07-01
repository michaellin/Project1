package src;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * A class that represents a simple picture. A simple picture may have an
 * 	associated file name and a title. A simple picture has pixels, width, and
 * 	height. A simple picture uses a BufferedImage to hold the pixels. You can
 * 	show a simple picture in a PictureFrame (a JFrame).
 * 
 * @author Barb Ericson (ericson@cc.gatech.edu)
 * 	(Copyright Georgia Institute of Technology 2004)
 * @author Modified by Colleen Lewis (colleenl@berkeley.edu),
 * 	Jonathan Kotker (jo_ko_berkeley@berkeley.edu),
 * 	Kaushik Iyer (kiyer@berkeley.edu), George Wang (georgewang@berkeley.edu),
 * 	and David Zeng (davidzeng@berkeley.edu), for use in CS61BL, the data
 * 	structures course at University of California, Berkeley. 
 */
public class SimplePicture
{

	///////////////////////////////// Fields ///////////////////////////////////

	/**
	 * Name of the project.
	 */
	private static String projectName = " - Project: PiCTURE";

	/**
	 * Filename associated with this SimplePicture.
	 */
	private String fileName;

	/**
	 * Title of this SimplePicture.
	 */
	private String title;
	
	/**
	 * BufferedImage to hold the pixels for this SimplePicture.
	 */
	private BufferedImage bufferedImage;

	/**
	 * PictureFrame used to display the SimplePicture.
	 */
	private PictureFrame pictureFrame;

	/** 
	 * Extension for this file (jpg or bmp).
	 */
	private String extension;

	/////////////////////////////// Constructors //////////////////////////////

	/**
	 * A constructor that takes no arguments. All fields will be null.
	 * 	A no-argument constructor must be given in order for a class to
	 * 	be able to be subclassed. By default, all subclasses will implicitly
	 * 	call this in their parent's no-argument constructor unless a 
	 * 	different call to super() is explicitly made as the first
	 * 	line of code in a constructor.
	 */
	public SimplePicture() {
		this(200, 100);
	}

	/**
	 * A constructor that takes a file name and uses the file to create
	 * 	a picture.
	 * 
	 * @param fileName The file name to use in creating the picture.
	 */
	public SimplePicture(String fileName) {
		// Load the picture into the BufferedImage. 
		load(fileName);
	}

	/**
	 * A constructor that takes the width and height desired for a picture
	 * 	and creates a BufferedImage of that size. This constructor does not
	 * 	show the picture.
	 * 
	 * @param width The desired width.
	 * @param height The desired height.
	 */
	public SimplePicture(int width, int height) {
		bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		title = "No current picture" + projectName;
		fileName = "None";
		extension = "jpg";
		setAllPixelsToAColor(Color.white);
	}

	/**
	 * A constructor that takes the width and height desired for a picture
	 * 	and creates a BufferedImage of that size. It also takes the color
	 * 	to use for the background of the picture.
	 * 
	 * @param width The desired width.
	 * @param height The desired height.
	 * @param theColor The background color for the picture.
	 */
	public SimplePicture(int width, int height, Color theColor) {
		this(width, height);
		setAllPixelsToAColor(theColor);
	}

	/**
	 * A constructor that takes another SimplePicture to copy information from.
	 * 
	 * @param copyPicture The SimplePicture to copy from.
	 */
	public SimplePicture(SimplePicture copyPicture) {
		if (copyPicture.fileName != null)
		{
			this.fileName = new String(copyPicture.fileName);
			this.extension = copyPicture.extension;
		}

		if (copyPicture.title != null)
			this.title = new String(copyPicture.title);

		if (copyPicture.bufferedImage != null)
		{
			this.bufferedImage = new BufferedImage(copyPicture.getWidth(),
					copyPicture.getHeight(), BufferedImage.TYPE_INT_RGB);
			this.copyPicture(copyPicture);
		}
	}

	//////////////////////////////// Methods //////////////////////////////////

	/**
	 * @return The extension (jpg or bmp) of this picture.
	 */
	public String getExtension() { return extension; }

	/**
	 * Copies all of the source SimplePicture provided into the
	 * 	current SimplePicture object.
	 *  
	 * @param sourcePicture The SimplePicture object to copy.
	 */
	public void copyPicture(SimplePicture sourcePicture) {
		Pixel sourcePixel = null;
		Pixel targetPixel = null;

		// Loop through the columns
		for (int sourceX = 0, targetX = 0; 
		sourceX < sourcePicture.getWidth() && targetX < this.getWidth();
		sourceX++, targetX++) {
			// Loop through the rows
			for (int sourceY = 0, targetY = 0; 
			sourceY < sourcePicture.getHeight() && 
			targetY < this.getHeight();
			sourceY++, targetY++)
			{
				sourcePixel = sourcePicture.getPixel(sourceX, sourceY);
				targetPixel = this.getPixel(targetX, targetY);
				targetPixel.setColor(sourcePixel.getColor());
			}
		}
	}

	/**
	 * Sets the Color in this SimplePicture to the Color provided.
	 * 
	 * @param color The Color to set to.
	 */
	public void setAllPixelsToAColor(Color color) {
		// Loop through all x.
		for (int x = 0; x < this.getWidth(); x++)
		{
			// Loop through all y.
			for (int y = 0; y < this.getHeight(); y++)
			{
				getPixel(x, y).setColor(color);
			}
		}
	}

	/**
	 * @return The BufferedImage in this SimplePicture. 
	 */
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	/**
	 * @return A Graphics object to be used by this SimplePicture for drawing.
	 */
	public Graphics getGraphics() {
		return bufferedImage.getGraphics();
	}

	/**
	 *@return A Graphics2D object for this SimplePicture
	 *	which can be used to do 2D drawing on the picture.
	 */
	public Graphics2D createGraphics() {
		return bufferedImage.createGraphics();
	}

	/**
	 * @return The file name associated with this SimplePicture.
	 */
	public String getFileName() { return fileName; }

	/**
	 * @return The title of this SimplePicture.
	 */
	public String getTitle() { return title; }

	/**
	 * Sets the title for this SimplePicture.
	 * 
	 * @param title The title to use for this SimplePicture.
	 */
	public void setTitle(String title) {
		this.title = title + projectName;
		if (pictureFrame != null)
			pictureFrame.setTitle(title);
	}

	/**
	 * @return The width of this SimplePicture in pixels.
	 */
	public int getWidth() { return bufferedImage.getWidth(); }

	/**
	 * @return The height of this SimplePicture picture in pixels.
	 */
	public int getHeight() { return bufferedImage.getHeight(); }

	/**
	 * @return the PictureFrame associated with this
	 * 	SimplePicture (it may be null).
	 */
	public PictureFrame getPictureFrame() { return pictureFrame; }

	/**
	 * Sets the PictureFrame for this SimplePicture.
	 * 
	 * @param pictureFrame The PictureFrame to use. 
	 */
	public void setPictureFrame(PictureFrame pictureFrame) {
		this.pictureFrame = pictureFrame;
	}

	/**
	 * @return The Image associated with this SimplePicture.
	 */
	public Image getImage() {
		// Return the BufferedImage object because it is an Image.
		return bufferedImage;
	}

	/**
	 * @param x The x-coordinate of the pixel.
	 * @param y The y-coordinate of the pixel.
	 * 
	 * @return The pixel value as an integer, with alpha, red, green,
	 * 	blue, each occupying 8 bits each.
	 */
	public int getBasicPixel(int x, int y) {
		return bufferedImage.getRGB(x, y);
	}

	/** 
	 * Sets the value of a pixel to a color provided by an int.
	 * 
	 * @param x The x-coordinate of the pixel.
	 * @param y The y-coordinate of the pixel.
	 * @param rgb The new RGB value of the pixel (alpha, red, green, blue).
	 */     
	public void setBasicPixel(int x, int y, int rgb) {
		bufferedImage.setRGB(x, y, rgb);
	}

	/**
	 * @param x The x-coordinate of the pixel.
	 * @param y The y-coordinate of the pixel.
	 * 
	 * @return a Pixel object for this location
	 */
	public Pixel getPixel(int x, int y) {
		/* Create the Pixel object for this SimplePicture
		 * and the given x- and y-coordinates. */
		return new Pixel(this, x, y);
	}

	/**
	 * Loads the BufferedImage associated with this
	 * 	SimplePicture with the Image provided.
	 * 
	 * @param image The Image to use.
	 */
	public void load(Image image) {
		// Get a Graphics context to use to draw on the BufferedImage.
		Graphics2D graphics2d = bufferedImage.createGraphics();

		/* Draw the Image on the BufferedImage
		 * starting at (0,0). */
		graphics2d.drawImage(image, 0, 0, null);

		// Show the new image.
		show();
	}

	/**
	 * Shows this SimplePicture in a PictureFrame.
	 */
	public void show() {
		if (pictureFrame != null) {
			pictureFrame.updateImageAndShowIt();
		} else {
			pictureFrame = new PictureFrame(this);
		}
	}

	/**
	 * Hides this SimplePicture.
	 */
	public void hide() {
		if (pictureFrame != null)
			pictureFrame.setVisible(false);
	}

	/**
	 * Sets the visibility of this SimplePicture.
	 * 
	 * @param flag True, if the picture is to be visible; false, otherwise.
	 */
	public void setVisible(boolean flag) {
		if (flag)
			this.show();
		else 
			this.hide();
	}

	/**
	 * Opens a PictureExplorer on a copy of this SimplePicture.
	 */
	public void explore() {
		new PictureExplorer(new Picture(this));
	}

	/**
	 * Forces this SimplePicture to redraw itself. This is very useful
	 * 	after pixels in the SimplePicture have been changed.
	 */
	public void repaint() {
		// If there is a PictureFrame, tell it to repaint.
		if (pictureFrame != null)
			pictureFrame.repaint();

		// Else, create a new PictureFrame.
		else
			pictureFrame = new PictureFrame(this);
	}

	/**
	 * Loads this SimplePicture from the file name provided.
	 * 
	 * @param fileName The file name to use to load the
	 * 	SimplePicture from.
	 */
	public void loadOrFail(String fileName) throws IOException {
		// Set the file name of this SimplePicture.
		this.fileName = fileName;

		// Set the extension.
		int posDot = fileName.indexOf('.');
		if (posDot >= 0)
			this.extension = fileName.substring(posDot + 1);

		// If the current title is null, use the file name.
		if (title == null)
			title = fileName + projectName;

		File file = new File(this.fileName);

		if (!file.canRead()) 
			throw new IOException(this.fileName +
					" could not be opened. " +
			"Check to see that you can read to the directory.");

		bufferedImage = ImageIO.read(file);
	}

	/**
	 * Writes the contents of this SimplePicture from a file with
	 * the name provided, without throwing errors.
	 * 
	 * @param fileName The name of the file to write the picture from.
	 * 
	 * @return True, if successful; false, otherwise.
	 */
	public boolean load(String fileName) {
		try {
			this.loadOrFail(fileName);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	/**
	 * Loads this SimplePicture from the file name provided. This
	 * 	simply calls load(fileName) and is made available for name
	 * 	compatibility.
	 * 
	 * @param fileName The file name to use to load this
	 * 	SimplePicture from.
	 * @return True, if successful; false, otherwise.
	 */
	public boolean loadImage(String fileName) {
		return load(fileName);
	}

	/**
	 * Draws a message as a String on the BufferedImage.
	 *  
	 * @param message The message to draw on the BufferedImage.
	 * @param xPos The x-coordinate of the leftmost point
	 * 	of the String. 
	 * @param yPos The y-coordinate of the top of
	 * 	the String.
	 */
	public void addMessage(String message, int xPos, int yPos) {
		// Get a graphics context to use to draw on the BufferedImage.
		Graphics2D graphics2d = bufferedImage.createGraphics();

		// Set the color to white.
		graphics2d.setPaint(Color.white);

		// Set the font to Helvetica bold style with size 16.
		graphics2d.setFont(new Font("Helvetica", Font.BOLD,16));

		// Draw the message.
		graphics2d.drawString(message, xPos, yPos);

	}

	/**
	 * Draws a String at the given location on the Picture.
	 * 
	 * @param text The text to draw.
	 * @param xPos The leftmost x-coordinate for the text. 
	 * @param yPos The topmost y-coordinate for the text.
	 */
	public void drawString(String text, int xPos, int yPos) {
		addMessage(text, xPos, yPos);
	}

	/**
	 * Creates a new SimplePicture with the height provided. 
	 * The aspect ratio of the width and height will stay the same.
	 * 
	 * @param height The desired height.
	 * 
	 * @return The resulting SimplePicture.
	 */
	public SimplePicture getPictureWithHeight(int height) {
		// Set up the scale transform.
		double yFactor = (double) height / this.getHeight();
		AffineTransform scaleTransform = new AffineTransform();
		scaleTransform.scale(yFactor,yFactor);

		// Create a new SimplePicture object of the right size.
		SimplePicture result =
			new SimplePicture((int) (getWidth() * yFactor),
					(int) (getHeight() * yFactor));

		// Get the Graphics2D object to draw on the result.
		Graphics graphics = result.getGraphics();
		Graphics2D g2 = (Graphics2D) graphics;

		// Draw the current Image onto the result image.
		g2.drawImage(this.getImage(), scaleTransform, null);

		return result;
	}

	/**
	 * Loads a SimplePicture from a file name and show it
	 * 	in a PictureFrame.
	 * 
	 * @param fileName The file name to load the SimplePicture from.
	 * 
	 * @return True, if successful; false, otherwise.
	 */
	public boolean loadPictureAndShowIt(String fileName) {
		boolean result = true;  // The default assumption is that it worked.

		// Try to load.
		result = load(fileName);

		// Show the picture.
		show();

		return result;
	}

	/**
	 * Writes the contents of the SimplePicture to a file with 
	 * the name provided.
	 * 
	 * @param fileName The name of the file to write the Picture to.
	 */
	public void writeOrFail(String fileName) throws IOException {
		String extension = this.extension; // The default is current.

		// Create the file object.
		File file = new File(fileName);
		File fileLoc = file.getParentFile();

		// canWrite is true only when the file exists already! (alexr)
		if (!fileLoc.canWrite()) {
			throw new IOException(fileName +
					" could not be opened. Check to see if you can" +
			" write to the directory.");
		}

		// Get the extension.
		int posDot = fileName.indexOf('.');
		if (posDot >= 0)
			extension = fileName.substring(posDot + 1);

		/* Write the contents of the BufferedImage to the file
		 * as JPEG. */
		ImageIO.write(bufferedImage, extension, file);
	}

	/**
	 * Write the contents of this SimplePicture to a file with 
	 * the passed name without throwing errors.
	 * 
	 * @param fileName The name of the file to write the picture to.
	 * 
	 * @return True, if successful; false, otherwise.
	 */
	public boolean write(String fileName) {
		try {
			this.writeOrFail(fileName);
			return true;
		} catch (Exception ex) {
			System.out.println("There was an error trying to write " +
					fileName);
			return false;
		}
	}

	/**
	 * Returns the coordinates of the enclosing rectangle after this
	 * transformation is applied to the current picture.
	 * 
	 * @return The enclosing rectangle.
	 */
	public Rectangle2D getTransformEnclosingRect(AffineTransform trans) {
		int width = getWidth();
		int height = getHeight();
		double maxX = width - 1;
		double maxY = height - 1;
		double minX, minY;
		Point2D.Double p1 = new Point2D.Double(0, 0);
		Point2D.Double p2 = new Point2D.Double(maxX, 0);
		Point2D.Double p3 = new Point2D.Double(maxX, maxY);
		Point2D.Double p4 = new Point2D.Double(0, maxY);
		Point2D.Double result = new Point2D.Double(0, 0);
		Rectangle2D.Double rect = null;

		// Get the new points and min/max x and y.
		trans.deltaTransform(p1, result);
		minX = result.getX();
		maxX = result.getX();
		minY = result.getY();
		maxY = result.getY();
		trans.deltaTransform(p2, result);
		minX = Math.min(minX, result.getX());
		maxX = Math.max(maxX, result.getX());
		minY = Math.min(minY, result.getY());
		maxY = Math.max(maxY, result.getY());
		trans.deltaTransform(p3, result);
		minX = Math.min(minX, result.getX());
		maxX = Math.max(maxX, result.getX());
		minY = Math.min(minY, result.getY());
		maxY = Math.max(maxY, result.getY());
		trans.deltaTransform(p4, result);
		minX = Math.min(minX, result.getX());
		maxX = Math.max(maxX, result.getX());
		minY = Math.min(minY, result.getY());
		maxY = Math.max(maxY, result.getY());

		// Create the bounding rectangle to return.
		rect = new Rectangle2D.Double(minX, minY, 
				maxX - minX + 1, maxY - minY + 1);
		return rect;
	}

	/**
	 * Returns a String with information about this
	 * 	SimplePicture.
	 * 
	 * @return A String with information about this
	 * 	SimplePicture. 
	 */
	public String toString() {
		String output = "Simple Picture, filename = " + fileName + 
		", height = " + getHeight() + ", width = " + getWidth();
		return output;
	}

} // End of SimplePicture</tt> class.
