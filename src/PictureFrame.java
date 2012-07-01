package src;
import javax.swing.*;

/**
 * Class that holds a DigitalPicture and displays it.
 * 
 * @author Barb Ericson (ericson@cc.gatech.edu)
 * 	(Copyright Georgia Institute of Technology 2004)
 * @author Modified by Colleen Lewis (colleenl@berkeley.edu),
 * 	Jonathan Kotker (jo_ko_berkeley@berkeley.edu),
 * 	Kaushik Iyer (kiyer@berkeley.edu), George Wang (georgewang@berkeley.edu),
 * 	and David Zeng (davidzeng@berkeley.edu), for use in CS61BL, the data
 * 	structures course at University of California, Berkeley. 
 */
public class PictureFrame
{

	//////////////////////////////// Fields ///////////////////////////////////

	/**
	 * Name of the project.
	 */
	private String projectName = " - Project One";
	
	/**
	 * Main window used as the frame.
	 */
	JFrame frame = new JFrame();

	/**
	 * ImageIcon used to display the picture in the label.
	 */
	ImageIcon imageIcon = new ImageIcon();

	/**
	 * Label used to display the picture.
	 */
	private JLabel label = new JLabel(imageIcon);

	/**
	 * SimplePicture to display.
	 */
	private SimplePicture picture;

	//////////////////////////////// Constructors /////////////////////////////

	/**
	 * A constructor that takes no arguments.
	 */
	public PictureFrame()
	{
		// Set up the frame
		initFrame();
	}

	/**
	 * A constructor that takes a SimplePicture to display.
	 * 
	 * @param picture The SimplePicture to display in the PictureFrame.
	 */
	public PictureFrame(SimplePicture picture)
	{
		// Set the current object's picture to the picture provided.
		this.picture = picture;

		// Set up the frame.
		initFrame();
	}

	////////////////////////////////// Methods ////////////////////////////////

	/**
	 * Sets the Picture to show in this PictureFrame.
	 * 
	 * @param picture The new picture to use.
	 */
	public void setPicture(Picture picture)
	{
		this.picture = picture;
		imageIcon.setImage(picture.getImage());
		setTitle(picture.getTitle());
		frame.pack();
		frame.repaint();
	}

	/**
	 * Updates the PictureFrame image with the image in
	 * the associated DigitalPicture.
	 */
	public void updateImage()
	{
		// Only do this if there is a picture.
		if (picture != null)
		{
			// Sets the image for the image icon from the picture.
			imageIcon.setImage(picture.getImage());

			// Sets the title of the frame to the title of the picture.
			setTitle(picture.getTitle());
		}
	}

	/**
	 * Updates the PictureFrame image with the image in
	 * the associated DigitalPicture and shows it.
	 */
	public void updateImageAndShowIt()
	{
		// First, update the image.
		updateImage();

		// Now, make sure it is shown.
		frame.setVisible(true);
	}

	/**
	 * Displays this PictureFrame.
	 */
	public void displayImage()
	{
		frame.setVisible(true);
	}

	/**
	 * Hides this PictureFrame.
	 */
	public void hide()
	{
		frame.setVisible(false);
	}

	/**
	 * Sets the visibility flag of this PictureFrame.
	 * 
	 * @param flag The new visibility flag of this PictureFrame.
	 */
	public void setVisible(boolean flag) 
	{ 
		frame.setVisible(flag);
	}

	/**
	 * Closes the PictureFrame.
	 */
	public void close()
	{
		frame.setVisible(false);
		frame.dispose();
	}

	/**
	 * Sets the title for this PictureFrame.
	 * 
	 * @param title The title to use for this PictureFrame.
	 */
	public void setTitle(String title)
	{ 
		frame.setTitle(title + projectName);
	}

	/**
	 * Forces this PictureFrame to repaint (redraw).
	 */
	public void repaint()
	{
		// Make the frame visible.
		frame.setVisible(true);

		// Update the image from the picture.
		updateImage();
		
		// Tell the JFrame to handle the repaint.
		frame.repaint();
	}

	/**
	 * Initializes this PictureFrame.
	 */
	private void initFrame()
	{
		// Set the image for the picture frame.
		updateImage();

		// Add the label to the frame.
		frame.getContentPane().add(label);

		// Pack the frame (set the size to as big as it needs to be).
		frame.pack();

		// Make the frame visible.
		frame.setVisible(true);
	}
	
} // End of PictureFrame class.
