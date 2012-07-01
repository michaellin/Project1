package src;
import javax.swing.*;
import java.awt.*;

/**
 * Class to display an image and the current location with a + sign.
 * 
 * @author Barb Ericson (ericson@cc.gatech.edu)
 * 	(Copyright Georgia Institute of Technology 2004)
 * @author Modified by Colleen Lewis (colleenl@berkeley.edu),
 * 	Jonathan Kotker (jo_ko_berkeley@berkeley.edu),
 * 	Kaushik Iyer (kiyer@berkeley.edu), George Wang (georgewang@berkeley.edu),
 * 	and David Zeng (davidzeng@berkeley.edu), for use in CS61BL, the data
 * 	structures course at University of California, Berkeley. 
 */
public class ImageDisplay extends JPanel implements Scrollable
{

	/////////////////////////// Fields (attributes) ///////////////////////////
	
	private Image image;         // the image to draw
	private Dimension prefSize;  // the preferred size of the display
	private int currentX = 0;    // the current x index 
	private int currentY = 0;    // the current y index
	private static final long serialVersionUID = 0; 
	
	//////////////////////////// Constructors /////////////////////////////////

	/**
	 * A constructor that takes the Image to display.
	 * 
	 * @param theImage The Image to display.
	 */
	public ImageDisplay(Image theImage) {
		image = theImage;
		prefSize = new Dimension(image.getWidth(this), image.getHeight(this));
		setPreferredSize(prefSize);
		revalidate();
	}

	/**
	 * Constructor that takes an Image and current x- and y-values.
	 * 
	 * @param theImage The Image to display.
	 * @param x The current x-value.
	 * @param y The current y-value.
	 */
	public ImageDisplay(Image theImage, int x, int y) {
		this(theImage);
		currentX = x;
		currentY = y;
	}

	///////////////////////////////// Methods /////////////////////////////////

	/**
	 * @return The associated Image.
	 */
	public Image getImage() { return image; }

	/**
	 * @return The current x-value.
	 */
	public int getCurrentX() { return currentX; }

	/**
	 * @return the current y-value.
	 */
	public int getCurrentY() { return currentY; }

	/**
	 * Sets the current x-value.
	 * 
	 * @param x The x-value to use.
	 */
	public void setCurrentX(int x) {
		currentX = x;
		repaint();
	}

	/**
	 * Sets the current y-value.
	 * 
	 * @param y The y-value to use.
	 */
	public void setCurrentY(int y) {
		currentY = y;
		repaint();
	}

	/**
	 * Sets the associated Image.
	 * 
	 * @param theImage The new Image to use.
	 */
	public void setImage(Image theImage) {
		image = theImage;
		setPreferredSize(new Dimension(image.getWidth(this),
				image.getHeight(this)));
		repaint();
	}

	/**
	 * @return The preferred size of this component.
	 */
	public Dimension getPreferredScrollableViewportSize() {
		return prefSize;
	}

	/**
	 * @param visibleRect The visible rectangle.
	 * @param orientation Whether orientation is vertical or horizontal.
	 * @param direction Negative value is up or left and
	 * 	positive value is right or down.
	 * 
	 * @return The unit increment for clicking in scroll area.
	 */
	public int getScrollableUnitIncrement(Rectangle visibleRect, 
			int orientation, int direction) { return 1; }

	/**
	 * Returns the block increment for scrolling.
	 * 
	 * @param visibleRect The visible rectangle.
	 * @param orientation Whether orientation is vertical or horizontal.
	 * @param direction Negative value is up or left and
	 * 	positive value is right or down.
	 * 
	 * @return The block increment for clicking in scroll area.
	 */
	public int getScrollableBlockIncrement(Rectangle visibleRect, 
			int orientation, int direction) { return 10; }

	/**
	 * @return True if viewport and source have same width
	 */
	public boolean getScrollableTracksViewportWidth() { return false; }

	/**
	 * @return True if viewport and source have same height
	 */
	public boolean getScrollableTracksViewportHeight() { return false; }

	/**
	 * Handles displaying this object.
	 * 
	 * @param g The graphics object to draw with.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int num = 3;
		int xStart = currentX - num;
		int xEnd = currentX + num;
		int yStart = currentY - num;
		int yEnd = currentY + num;
		int width = image.getWidth(this);
		int maxX = width - 1;
		int height = image.getHeight(this);
		int maxY = height - 1;

		// Draw the image
		g.drawImage(image, 0, 0, this);

		// Check if the current index is in the image.
		if (currentX >= 0 && currentX < width &&
				currentY >= 0 && currentY < height)
		{
			// Check that the start and end values are visible.
			if (xStart < 0)
				xStart = 0;
			if (xEnd > maxX)
				xEnd = maxX;
			if (yStart < 0)
				yStart = 0;
			if (yEnd > maxY)
				yEnd = maxY;

			/* Draw a small cross at the current x- and y-
			 * coordinates in yellow.
			 */
			g.setColor(Color.yellow);
			g.drawLine(xStart,currentY,xEnd,currentY);
			g.drawLine(currentX,yStart,currentX,yEnd);
			g.setColor(Color.black);

			// Outline the cross in black so that it shows up better.
			int leftX = currentX - 1;
			int rightX = currentX + 1;
			int upY = currentY - 1;
			int downY = currentY + 1; 
			if (xStart <= leftX && upY >= 0)
				g.drawLine(xStart, upY, leftX, upY);
			if (yStart <= upY && leftX >= 0)
				g.drawLine(leftX, yStart, leftX, upY);
			if (yStart <= upY && rightX <= maxX)
				g.drawLine(rightX, yStart, rightX, upY);
			if (upY >= 0 && rightX <= xEnd)
				g.drawLine(rightX, upY, xEnd, upY);
			if (downY < height && rightX <= xEnd)
				g.drawLine(rightX, downY, xEnd, downY);
			if (downY <= yEnd && rightX < width)
				g.drawLine(rightX, downY, rightX, yEnd);
			if (xStart <= leftX && downY < height)
				g.drawLine(xStart, downY, leftX, downY);
			if (leftX >= 0 && downY <= yEnd)
				g.drawLine(leftX, downY, leftX, yEnd);
		}
	}

} // End of ImageDisplay class.
