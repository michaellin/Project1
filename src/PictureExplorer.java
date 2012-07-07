package src;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Displays a picture and allows exploration of the picture by displaying
 * 	the x, y, red, green, and blue values of the pixel at the cursor when
 * 	a mouse button is clicked or a mouse button is pressed and held while
 * 	moving the cursor. The x and y values of a pixel can also be entered
 * 	to see the color at that pixel.
 * 
 * @author Keith McDermott (gte047w@cc.gatech.edu) and
 * 	Barb Ericson (ericson@cc.gatech.edu)
 * 	(Copyright Georgia Institute of Technology 2004)
 * @author Modified by Colleen Lewis (colleenl@berkeley.edu),
 * 	Jonathan Kotker (jo_ko_berkeley@berkeley.edu),
 * 	Kaushik Iyer (kiyer@berkeley.edu), George Wang (georgewang@berkeley.edu),
 * 	and David Zeng (davidzeng@berkeley.edu), for use in CS61BL, the data
 * 	structures course at University of California, Berkeley.
 */
public class PictureExplorer implements MouseMotionListener, ActionListener, MouseListener
{

	// Current x- and y-index.
	private int xIndex = 0;
	private int yIndex = 0;

	// Main GUI variables.
	private JFrame pictureFrame;
	private JScrollPane scrollPane;

	// Information bar variables.
	private JLabel xLabel;
	private JLabel yLabel;
	private JButton xPrevButton;
	private JButton yPrevButton;
	private JButton xNextButton;
	private JButton yNextButton;
	private JTextField xValue;
	private JTextField yValue;
	private JLabel rLabel;
	private JLabel rValue;
	private JLabel gLabel;
	private JLabel gValue;
	private JLabel bLabel;
	private JLabel bValue;
	private JLabel colorLabel;
	private JPanel colorPanel;

	// Menu components.
	private JMenuBar menuBar;

	// File menu.
	private JMenu fileMenu;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem exitMenuItem;

	// Strings used in file menu items.
	private static String file = "File";
	private static String open = "Open...";
	private static String save = "Save";
	private static String saveAs = "Save As...";
	private static String exit = "Exit";

	// Zoom menu.
	private JMenu zoomMenu;
	private JMenuItem twentyFiveMenuItem;
	private JMenuItem fiftyMenuItem;
	private JMenuItem seventyFiveMenuItem;
	private JMenuItem hundredMenuItem;
	private JMenuItem hundredFiftyMenuItem;
	private JMenuItem twoHundredMenuItem;
	private JMenuItem fiveHundredMenuItem;

	// Strings used in zoom menu items.
	private static String zoom = "Zoom";
	private static String twentyFive = "25%";
	private static String fifty = "50%";
	private static String seventyFive = "75%";
	private static String hundred = "100%";
	private static String hundredFifty = "150%";
	private static String twoHundred = "200%";
	private static String fiveHundred = "500%";

	// Picture effects menu.
	private JMenu pictureEffectsMenu;
	private JMenuItem chromakeyMenuItem;
	private JMenuItem blurMenuItem;
	private JMenuItem showEdgesMenuItem;
	private JMenuItem asciiMenuItem;
	private JMenuItem paintBucketMenuItem;
	
	private static final String pictureEffects = "Picture Effects";
	private static final String chromakey = "Chromakey on Current Point";
	private static final String blur = "Blur Image";
	private static final String showEdges = "Show Edges";
	private static final String ascii = "ASCII";
	private static final String paintBucket = "Paint Bucket on Current Point";
	
	// Picture rotate menu
	private JMenu rotateflipMenu;
	private JMenuItem flipHorzMenuItem;
	private JMenuItem flipVertMenuItem;
	private JMenuItem flipForwardMenuItem;
	private JMenuItem flipBackwardMenuItem;
	private JMenuItem rotateMenuItem;

	private static final String rotateFlipEffects = "Rotate/Flip";
	private static final String rotate = "Rotate Image Right";
	private static final String flipHorz = "Flip Horizontally";
	private static final String flipVert = "Flip Vertically";
	private static final String flipForward = "Flip on Forward Slash";
	private static final String flipBackward = "Flip on Backward Slash";
	
	// Picture color change menu 
	private JMenu changeColorMenu;
	private JMenuItem grayscaleMenuItem;
	private JMenuItem negateMenuItem;
	private JMenuItem lightenMenuItem;
	private JMenuItem darkenMenuItem;
	private JMenuItem addRedMenuItem;
	private JMenuItem addGreenMenuItem;
	private JMenuItem addBlueMenuItem;

	private static final String colorChange = "Change Colors";
	private static final String grayscale = "Grayscale";
	private static final String negate 	 = "Photonegative";
	private static final String lighten  = "Lighten";
	private static final String darken 	 = "Darken";
	private static final String addRed 	 = "Add Red";
	private static final String addGreen = "Add Green";
	private static final String addBlue	 = "Add Blue";
	
	

	// Strings used in the ImageDisplay and in the pixel navigation system.
	private static final String imageDisplayTooltip = "Click a mouse button " +
	"on a pixel to see the pixel information";
	private static final String xNextTooltip = "Click to go to the " +
			"next x value";
	private static final String xPrevTooltip = "Click to go to the " +
			"previous x value";
	private static final String yNextTooltip = "Click to go to the " +
			"next y value";
	private static final String yPrevTooltip = "Click to go to the " +
			"previous y value";
	private static final String redLabel = "R: ";
	private static final String greenLabel = "G: ";
	private static final String blueLabel = "B: ";
	private static final String cannotShowText = "N/A";

	/** The Picture being explored. */
	private Picture picture;

	/** The ImageDisplay. */
	private ImageDisplay imageDisplay;

	/** The zoom factor (amount to zoom). */
	private double zoomFactor;

	/** The number system to use.
	 * 	0 means starting at 0, 1 means starting at 1. */
	private int numberBase = 0;

	/**
	 * Public constructor.
	 * 
	 * @param picture The Picture to explore.
	 */
	public PictureExplorer(Picture picture) {
		this.picture = picture;
		zoomFactor = 1;

		// Create the window and set things up.
		createWindow();
	}

	/**
	 * Changes the number system to start at base one.
	 */
	public void changeToBaseOne() {
		numberBase = 1;
	}

	/**
	 * Sets the title of the frame.
	 * 
	 * @param title The title to use.
	 */
	public void setTitle(String title) {
		pictureFrame.setTitle(title);
	}

	/**
	 * Creates and initializes the picture frame.
	 */
	private void createAndInitPictureFrame() {
		pictureFrame = new JFrame(); // Create the JFrame.
		pictureFrame.setResizable(true);  // Allow the user to resize it.
		pictureFrame.getContentPane().
		setLayout(new BorderLayout()); // Use border layout.
		pictureFrame.setDefaultCloseOperation
		(JFrame.DISPOSE_ON_CLOSE); // When closed, stop.
		pictureFrame.setTitle(picture.getTitle());
		PictureExplorerFocusTraversalPolicy newPolicy = 
			new PictureExplorerFocusTraversalPolicy();
		pictureFrame.setFocusTraversalPolicy(newPolicy);
	}

	/**
	 * Creates the menu bar, menus, and menu items.
	 */
	private void setUpMenuBar() {
		// Create menu bar.
		menuBar = new JMenuBar();

		// Add the file menu.
		fileMenu = new JMenu(file);
		openMenuItem = new JMenuItem(open);
		saveMenuItem = new JMenuItem(save);
		saveAsMenuItem = new JMenuItem(saveAs);
		exitMenuItem = new JMenuItem(exit);

		// Add the zoom menu.
		zoomMenu = new JMenu(zoom);
		twentyFiveMenuItem = new JMenuItem(twentyFive);
		fiftyMenuItem = new JMenuItem(fifty);
		seventyFiveMenuItem = new JMenuItem(seventyFive);
		hundredMenuItem = new JMenuItem(hundred);
		hundredMenuItem.setEnabled(false);
		hundredFiftyMenuItem = new JMenuItem(hundredFifty);
		twoHundredMenuItem = new JMenuItem(twoHundred);
		fiveHundredMenuItem = new JMenuItem(fiveHundred);

		

		// Add the picture effects menu.
		pictureEffectsMenu 	 = new JMenu(pictureEffects);
		chromakeyMenuItem 	 = new JMenuItem(chromakey);
		blurMenuItem 		 = new JMenuItem(blur);
		showEdgesMenuItem 	 = new JMenuItem(showEdges);
		asciiMenuItem 		 = new JMenuItem(ascii);
		paintBucketMenuItem  = new JMenuItem(paintBucket);
		
		// Add the rotate flip menu.
		rotateflipMenu 		 = new JMenu(rotateFlipEffects);
		rotateMenuItem 		 = new JMenuItem(rotate);
		flipHorzMenuItem 	 = new JMenuItem(flipHorz);
		flipVertMenuItem 	 = new JMenuItem(flipVert);
		flipForwardMenuItem  = new JMenuItem(flipForward);
		flipBackwardMenuItem = new JMenuItem(flipBackward);
		
		// Add the color change menu
		changeColorMenu 	 = new JMenu(colorChange);
		grayscaleMenuItem 	 = new JMenuItem(grayscale);
		negateMenuItem 		 = new JMenuItem(negate);
		lightenMenuItem 	 = new JMenuItem(lighten);
		darkenMenuItem 		 = new JMenuItem(darken);
		addRedMenuItem 		 = new JMenuItem(addRed);
		addGreenMenuItem 	 = new JMenuItem(addGreen);
		addBlueMenuItem 	 = new JMenuItem(addBlue);
		
		// Add the action listeners for the file menu.
		openMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		saveAsMenuItem.addActionListener(this);
		exitMenuItem.addActionListener(this);

		// Add the action listeners for the zoom menu.
		twentyFiveMenuItem.addActionListener(this);
		fiftyMenuItem.addActionListener(this);
		seventyFiveMenuItem.addActionListener(this);
		hundredMenuItem.addActionListener(this);
		hundredFiftyMenuItem.addActionListener(this);
		twoHundredMenuItem.addActionListener(this);
		fiveHundredMenuItem.addActionListener(this);

		// Add the action listeners for the picture effects menu.
		chromakeyMenuItem.addActionListener(this);
		blurMenuItem.addActionListener(this);
		showEdgesMenuItem.addActionListener(this);
		asciiMenuItem.addActionListener(this);
		paintBucketMenuItem.addActionListener(this);
		
		// Add the action listeners for the rotate/flip effects menu.
		rotateMenuItem.addActionListener(this);
		flipHorzMenuItem.addActionListener(this);
		flipVertMenuItem.addActionListener(this);
		flipForwardMenuItem.addActionListener(this);
		flipBackwardMenuItem.addActionListener(this);
		
		// Add the action listeners for the color change menu.
		grayscaleMenuItem.addActionListener(this);
		negateMenuItem.addActionListener(this);
		lightenMenuItem.addActionListener(this);
		darkenMenuItem.addActionListener(this);
		addRedMenuItem.addActionListener(this);
		addGreenMenuItem.addActionListener(this);
		addBlueMenuItem.addActionListener(this);

		// Add the menu items to the menus.
		fileMenu.add(openMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);

		zoomMenu.add(twentyFiveMenuItem);
		zoomMenu.add(fiftyMenuItem);
		zoomMenu.add(seventyFiveMenuItem);
		zoomMenu.add(hundredMenuItem);
		zoomMenu.add(hundredFiftyMenuItem);
		zoomMenu.add(twoHundredMenuItem);
		zoomMenu.add(fiveHundredMenuItem);
		menuBar.add(zoomMenu);

		pictureEffectsMenu.add(chromakeyMenuItem);
		pictureEffectsMenu.add(blurMenuItem);
		pictureEffectsMenu.add(showEdgesMenuItem);
		pictureEffectsMenu.add(asciiMenuItem);
		pictureEffectsMenu.add(paintBucketMenuItem);
		menuBar.add(pictureEffectsMenu);
		
		rotateflipMenu.add(rotateMenuItem);
		rotateflipMenu.add(flipHorzMenuItem);
		rotateflipMenu.add(flipVertMenuItem);
		rotateflipMenu.add(flipForwardMenuItem);
		rotateflipMenu.add(flipBackwardMenuItem);
		menuBar.add(rotateflipMenu);

		changeColorMenu.add(grayscaleMenuItem);
		changeColorMenu.add(negateMenuItem);
		changeColorMenu.add(lightenMenuItem);
		changeColorMenu.add(darkenMenuItem);
		changeColorMenu.add(addRedMenuItem);
		changeColorMenu.add(addGreenMenuItem);
		changeColorMenu.add(addBlueMenuItem);
		menuBar.add(changeColorMenu);
		
		// Set the menu bar to this menu.
		pictureFrame.setJMenuBar(menuBar);
	}

	/**
	 * Creates and initializes the scrolling image.
	 */
	private void createAndInitScrollingImage() {
		if (scrollPane != null) {
			pictureFrame.getContentPane().remove(scrollPane);
		}

		scrollPane = new JScrollPane();

		BufferedImage bimg = picture.getBufferedImage();
		imageDisplay = new ImageDisplay(bimg);
		imageDisplay.addMouseMotionListener(this);
		imageDisplay.addMouseListener(this);
		imageDisplay.setToolTipText(imageDisplayTooltip);
		scrollPane.setViewportView(imageDisplay);

		pictureFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		pictureFrame.validate();
	}

	/**
	 * Creates the JFrame and sets everything up.
	 */
	private void createWindow() {
		// Create the picture frame and initializes it.
		createAndInitPictureFrame();

		// Set up the menu bar.
		setUpMenuBar();

		// Create the information panel.
		createInfoPanel();

		// Create the scrollpane for the picture.
		createAndInitScrollingImage();

		// Show the picture in the frame at the size it needs to be.
		pictureFrame.pack();
		pictureFrame.setVisible(true);
	}

	/**
	 * Sets up the next and previous buttons for the pixel location
	 * information.
	 */
	private void setUpNextAndPreviousButtons() {
		// Create the image icons for the buttons.
		Icon prevIcon = new ImageIcon(
				PictureExplorer.class.getResource("../Pictures/leftArrow.gif"),
		"previous index");
		Icon nextIcon = new ImageIcon(
				PictureExplorer.class.getResource("../Pictures/rightArrow.gif"),
		"next index");

		// Create the arrow buttons.
		xPrevButton = new JButton(prevIcon);
		xNextButton = new JButton(nextIcon);
		yPrevButton = new JButton(prevIcon);
		yNextButton = new JButton(nextIcon);

		// Set the tool tip text.
		xNextButton.setToolTipText(xNextTooltip);
		xPrevButton.setToolTipText(xPrevTooltip);
		yNextButton.setToolTipText(yNextTooltip);
		yPrevButton.setToolTipText(yPrevTooltip);

		// Set the sizes of the buttons.
		int prevWidth = prevIcon.getIconWidth() + 2;
		int nextWidth = nextIcon.getIconWidth() + 2;
		int prevHeight = prevIcon.getIconHeight() + 2;
		int nextHeight = nextIcon.getIconHeight() + 2;
		Dimension prevDimension = new Dimension(prevWidth,prevHeight);
		Dimension nextDimension = new Dimension(nextWidth, nextHeight);
		xPrevButton.setPreferredSize(prevDimension);
		yPrevButton.setPreferredSize(prevDimension);
		xNextButton.setPreferredSize(nextDimension);
		yNextButton.setPreferredSize(nextDimension);

		// Handle previous x-button press.
		xPrevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				xIndex--;
				if (xIndex < 0)
					xIndex = 0;
				displayPixelInformation(xIndex, yIndex);
			}
		});

		// Handle previous y button press.
		yPrevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				yIndex--;
				if (yIndex < 0)
					yIndex = 0;
				displayPixelInformation(xIndex, yIndex);
			}
		});

		// Handle next x button press.
		xNextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				xIndex++;
				if (xIndex >= picture.getWidth())
					xIndex = picture.getWidth() - 1;
				displayPixelInformation(xIndex, yIndex);
			}
		});

		// Handle next y button press.
		yNextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				yIndex++;
				if (yIndex >= picture.getHeight())
					yIndex = picture.getHeight() - 1;
				displayPixelInformation(xIndex, yIndex);
			}
		});
	}

	/**
	 * Creates the pixel location panel.
	 * 
	 * @param labelFont The font for the labels.
	 * 
	 * @return The location panel.
	 */
	public JPanel createLocationPanel(Font labelFont) {

		// Create a location panel.
		JPanel locationPanel = new JPanel();
		locationPanel.setLayout(new FlowLayout());
		Box hBox = Box.createHorizontalBox();
		// Create the labels.
		xLabel = new JLabel("X:");
		yLabel = new JLabel("Y:");

		// Create the text fields.
		xValue = new JTextField(Integer.toString(xIndex + numberBase), 6);
		xValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayPixelInformation(xValue.getText(), yValue.getText());
			}
		});
		yValue = new JTextField(Integer.toString(yIndex + numberBase), 6);
		yValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayPixelInformation(xValue.getText(), yValue.getText());
			}
		});

		// Set up the next and previous buttons.
		setUpNextAndPreviousButtons();

		// Set up the font for the labels.
		xLabel.setFont(labelFont);
		yLabel.setFont(labelFont);
		xValue.setFont(labelFont);
		yValue.setFont(labelFont);

		// Add the items to the vertical box and the box to the panel.
		hBox.add(Box.createHorizontalGlue());
		hBox.add(xLabel);
		hBox.add(xPrevButton);
		hBox.add(xValue);
		hBox.add(xNextButton);
		hBox.add(Box.createHorizontalStrut(10));
		hBox.add(yLabel);
		hBox.add(yPrevButton);
		hBox.add(yValue);
		hBox.add(yNextButton);
		locationPanel.add(hBox);
		hBox.add(Box.createHorizontalGlue());

		return locationPanel;
	}

	/**
	 * Creates the color information panel.
	 * 
	 * @param labelFont The font to use for labels.
	 * 
	 * @return the color information panel.
	 */
	private JPanel createColorInfoPanel(Font labelFont) {
		// Create a color information panel.
		JPanel colorInfoPanel = new JPanel();
		colorInfoPanel.setLayout(new FlowLayout());

		// Get the pixel at the current x and y.
		Pixel pixel = picture.getPixel(xIndex, yIndex);

		// Create the labels.
		rLabel = new JLabel(redLabel);
		rValue = new JLabel(Integer.toString(pixel.getRed()));
		gLabel = new JLabel(greenLabel);
		gValue = new JLabel(Integer.toString(pixel.getGreen()));
		bLabel = new JLabel(blueLabel);
		bValue = new JLabel(Integer.toString(pixel.getBlue()));

		// Create the sample color panel and label.
		colorLabel = new JLabel("Color at location: ");
		colorPanel = new JPanel();
		colorPanel.setBorder(new LineBorder(Color.black, 1));

		// Set the color sample to the pixel color.
		colorPanel.setBackground(pixel.getColor());

		// Set the font.
		rLabel.setFont(labelFont);
		rValue.setFont(labelFont);
		gLabel.setFont(labelFont);
		gValue.setFont(labelFont);
		bLabel.setFont(labelFont);
		bValue.setFont(labelFont);
		colorLabel.setFont(labelFont);
		colorPanel.setPreferredSize(new Dimension(25, 25));

		// Add items to the color information panel.
		colorInfoPanel.add(rLabel);
		colorInfoPanel.add(rValue);
		colorInfoPanel.add(gLabel);
		colorInfoPanel.add(gValue);
		colorInfoPanel.add(bLabel);
		colorInfoPanel.add(bValue);
		colorInfoPanel.add(colorLabel);
		colorInfoPanel.add(colorPanel);

		return colorInfoPanel; 
	}

	/**
	 * Creates the north JPanel with all the pixel location
	 * and color information.
	 */
	private void createInfoPanel() {
		// Create the info panel and set the layout.
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());

		// Create the font.
		Font largerFont = new Font(infoPanel.getFont().getName(),
				infoPanel.getFont().getStyle(), 14);

		// Create the pixel location panel.
		JPanel locationPanel = createLocationPanel(largerFont);

		// Create the color information panel.
		JPanel colorInfoPanel = createColorInfoPanel(largerFont);

		// Add the panels to the info panel.
		infoPanel.add(BorderLayout.NORTH, locationPanel);
		infoPanel.add(BorderLayout.SOUTH, colorInfoPanel); 

		// Add the info panel.
		pictureFrame.getContentPane().add(BorderLayout.NORTH, infoPanel);
	} 

	/**
	 * Checks that the current position is in the viewing area and if
	 * 	not, scroll to center the current position, if possible.
	 */
	public void checkScroll() {
		// Get the x and y position in pixels.
		int xPos = (int) (xIndex * zoomFactor); 
		int yPos = (int) (yIndex * zoomFactor); 

		// Only do this if the image is larger than normal.
		if (zoomFactor > 1) {

			// Get the rectangle that defines the current view.
			JViewport viewport = scrollPane.getViewport();
			Rectangle rect = viewport.getViewRect();
			int rectWidth = (int) rect.getWidth();
			int rectHeight = (int) rect.getHeight();

			// Get the maximum possible x and y index.
			int maxIndexX = (int) (picture.getWidth() * zoomFactor)
			- rectWidth - 1;
			int maxIndexY = (int) (picture.getHeight() * zoomFactor)
			- rectHeight - 1;

			/* Calculate how to position the current position in the
			 * middle of the viewing area. */
			int viewX = xPos - (int) (rectWidth / 2);
			int viewY = yPos - (int) (rectHeight / 2);

			// Reposition the viewX and viewY if outside allowed values.
			if (viewX < 0)
				viewX = 0;
			else if (viewX > maxIndexX)
				viewX = maxIndexX;
			if (viewY < 0)
				viewY = 0;
			else if (viewY > maxIndexY)
				viewY = maxIndexY;

			// Move the viewport upper left point.
			viewport.scrollRectToVisible(new Rectangle(viewX,
					viewY, rectWidth, rectHeight));
		}
	}

	/**
	 * Zooms in on the picture by scaling the image.
	 * Caution: It is extremely memory intensive.
	 * 
	 * @param factor The amount to zoom by.
	 */
	public void zoom(double factor) {
		// Save the current zoom factor.
		zoomFactor = factor;

		// Calculate the new width and height and get an image that size.
		int width = (int)(picture.getWidth() * zoomFactor);
		int height = (int)(picture.getHeight() * zoomFactor);
		BufferedImage bimg = picture.getBufferedImage();

		// Set the scroll image icon to the new image.
		imageDisplay.setImage(bimg.getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
		imageDisplay.setCurrentX((int) (xIndex * zoomFactor));
		imageDisplay.setCurrentY((int) (yIndex * zoomFactor));
		imageDisplay.revalidate();
		checkScroll();  // Check if need to reposition scroll.
	}

	/**
	 * Repaints the image on the scrollpane.
	 */
	public void repaint() {
		pictureFrame.repaint();
	}

	///////////////////////////// Event Listeners /////////////////////////////

	/**
	 * Called when the mouse is dragged (button held down and moved).
	 * 
	 * @param e the mouse event
	 */
	public void mouseDragged(MouseEvent e) {
		displayPixelInformation(e);
	}

	/**
	 * Checks if the given x and y coordinates
	 * 	are in the picture.
	 * 
	 * @param x The horizontal coordinate.
	 * @param y The vertical coordinate.
	 * 
	 * @return True, if the horizontal and vertical coordinates provided
	 * 	are in the picture; false, otherwise.
	 */
	private boolean isLocationInPicture(int x, int y) {
		boolean result = false; // Default is false.
		if (x >= 0 && x < picture.getWidth() &&
				y >= 0 && y < picture.getHeight())
			result = true;

		return result;
	}

	/**
	 * Displays the pixel information from the x and y
	 * 	coordinates provided.
	 * 
	 * @param xString The x value as a String from the user.
	 * @param yString The y value as a String from the user.
	 */
	public void displayPixelInformation(String xString, String yString) {
		int x = -1;
		int y = -1;
		try {
			x = Integer.parseInt(xString);
			x = x - numberBase;
			y = Integer.parseInt(yString);
			y = y - numberBase;
		} catch (Exception ex) {
		}

		if (x >= 0 && y >= 0) {
			displayPixelInformation(x,y);
		}
	}

	/**
	 * Display pixel information for the x and y
	 * 	coordinates provided.
	 * 
	 * @param pictureX The x value in the picture.
	 * @param pictureY the y value in the picture.
	 */
	private void displayPixelInformation(int pictureX, int pictureY) {
		// Check that this x and y is in range.
		if (isLocationInPicture(pictureX, pictureY))
		{
			// Save the current x and y index.
			xIndex = pictureX;
			yIndex = pictureY;

			// Get the pixel at the x and y provided.
			Pixel pixel = picture.getPixel(xIndex, yIndex);

			// Set the values based on the pixel.
			xValue.setText(Integer.toString(xIndex + numberBase));
			yValue.setText(Integer.toString(yIndex + numberBase));

			int redValue = pixel.getRed();
			int greenValue = pixel.getGreen();
			int blueValue = pixel.getBlue();
			rValue.setText(Integer.toString(redValue));
			gValue.setText(Integer.toString(greenValue));
			bValue.setText(Integer.toString(blueValue));
			colorPanel.setBackground(
					new Color(redValue, greenValue, blueValue));

		} else {
			clearInformation();
		}

		// Notify the image display of the current x and y.
		imageDisplay.setCurrentX((int) (xIndex * zoomFactor));
		imageDisplay.setCurrentY((int) (yIndex * zoomFactor));
	}

	/**
	 * Displays pixel information based on a mouse event.
	 * 
	 * @param e The mouse event.
	 */
	private void displayPixelInformation(MouseEvent e) {
		// Get the cursor x- and y-coordinate.
		int cursorX = e.getX();
		int cursorY = e.getY();

		// Get the x and y in original (not scaled) image.
		int pictureX = (int) (cursorX / zoomFactor + numberBase);
		int pictureY = (int) (cursorY / zoomFactor + numberBase);

		// Display the information for this x and y.
		displayPixelInformation(pictureX, pictureY);
	}

	/**
	 * Clears the labels and current color and resets the current index to -1.
	 */
	private void clearInformation() {
		xValue.setText(cannotShowText);
		yValue.setText(cannotShowText);
		rValue.setText(cannotShowText);
		gValue.setText(cannotShowText);
		bValue.setText(cannotShowText);
		colorPanel.setBackground(Color.black);
		xIndex = -1;
		yIndex = -1;
	}

	/**
	 * Called when the mouse is moved with no buttons down.
	 * 
	 * @param e The mouse event.
	 */
	public void mouseMoved(MouseEvent e) {}

	/**
	 * Called when the mouse is clicked.
	 * 
	 * @param e The mouse event.
	 */
	public void mouseClicked(MouseEvent e) {
		displayPixelInformation(e);
	}

	/**
	 * Called when the mouse button is pushed down.
	 * 
	 * @param e The mouse event.
	 */ 
	public void mousePressed(MouseEvent e) {
		displayPixelInformation(e);
	}

	/**
	 * Called when the mouse button is released.
	 * 
	 * @param e The mouse event.
	 */
	public void mouseReleased(MouseEvent e) { }

	/**
	 * Called when the component is entered (mouse moves over it).
	 * 
	 * @param e The mouse event.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Called when the mouse moves over the component.
	 * 
	 * @param e The mouse event
	 */
	public void mouseExited(MouseEvent e) { }

	/**
	 * Enables all zoom menu commands.
	 */
	private void enableZoomItems() {
		twentyFiveMenuItem.setEnabled(true);
		fiftyMenuItem.setEnabled(true);
		seventyFiveMenuItem.setEnabled(true);
		hundredMenuItem.setEnabled(true);
		hundredFiftyMenuItem.setEnabled(true);
		twoHundredMenuItem.setEnabled(true);
		fiveHundredMenuItem.setEnabled(true);
	}

	/**
	 * Enables all picture menu commands.
	 */
	private void enablePictureItems() {
		grayscaleMenuItem.setEnabled(true);
	}

	/**
	 * Controls the menu bar.
	 *
	 * @param a the ActionEvent 
	 */
	public void actionPerformed(ActionEvent a) {
		if (a.getActionCommand().equals(open)) {
			String fileName = FileChooser.pickAFile(FileChooser.OPEN);

			if (fileName != null) {
				try {
					picture.loadOrFail(fileName);
					pictureFrame.setTitle(picture.getTitle());
					createAndInitScrollingImage();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(pictureFrame,
						    "Could not open file: " + fileName,
						    "Open Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (a.getActionCommand().equals(save)) {
			saveFile();
		}
		
		if (a.getActionCommand().equals(saveAs)) {
			String fileName = FileChooser.pickAFile(FileChooser.SAVE);
			
			if (fileName != null) {
				try {
					String extension = picture.getExtension();

					// User may have provided a file extension.
					int posDot = fileName.indexOf('.');
					if (posDot >= 0)
						extension = fileName.substring(posDot + 1);
					else
						fileName = fileName + "." + extension;

					File file = new File(fileName);
					
					ImageIO.write(picture.getBufferedImage(),
							picture.getExtension(), file);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(pictureFrame,
						    "Could not save file. If you have added an " +
						    "image extension (such as JPG or BMP), please" +
						    "ensure that the extension is a vaild one.",
						    "Save Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (a.getActionCommand().equals(exit)) {
			System.exit(0);
		}
		
		if (a.getActionCommand().equals(twentyFive)) {
			zoom(.25);
			enableZoomItems();
			enablePictureItems();
			twentyFiveMenuItem.setEnabled(false);
		}

		if (a.getActionCommand().equals(fifty)) {
			zoom(.50);
			enableZoomItems();
			fiftyMenuItem.setEnabled(false);
		}

		if (a.getActionCommand().equals(seventyFive)) {
			zoom(.75);
			enableZoomItems();
			seventyFiveMenuItem.setEnabled(false);
		}

		if (a.getActionCommand().equals(hundred)) {
			zoom(1.0);
			enableZoomItems();
			hundredMenuItem.setEnabled(false);
		}

		if (a.getActionCommand().equals(hundredFifty)) {
			zoom(1.5);
			enableZoomItems();
			hundredFiftyMenuItem.setEnabled(false);
		}

		if (a.getActionCommand().equals(twoHundred)) {
			zoom(2.0);
			enableZoomItems();
			twoHundredMenuItem.setEnabled(false);
		}

		if (a.getActionCommand().equals(fiveHundred)) {
			zoom(5.0);
			enableZoomItems();
			fiveHundredMenuItem.setEnabled(false);
		}

		if (a.getActionCommand().equals(grayscale)) {
			picture = new Picture(picture.grayscale());
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(negate)) {
			picture = new Picture(picture.negate());
			createAndInitScrollingImage();
		}

		if (a.getActionCommand().equals(chromakey)) {
			int threshold =  getParameterValue("the color threshold", 1, 100);			
			picture = new Picture(picture.chromaKey(xIndex, yIndex, new Picture(FileChooser.pickAFile(FileChooser.OPEN)), threshold));
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(rotate)) {
			picture = new Picture(picture.rotate(1));
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(blur)) {
			int blurThreshold =  getParameterValue("the blur threshold", 1, 5);
			picture = new Picture(picture.blur(blurThreshold));
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(showEdges)) {
			int threshold =  getParameterValue("the edge threshold", 1, 100);
			picture = new Picture(picture.showEdges(threshold));
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(flipHorz)) {
			picture = picture.flip(Picture.HORIZONTAL);
			// repaint();
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(flipVert)) {
			picture = picture.flip(Picture.VERTICAL);
			// repaint();
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(flipForward)) {
			picture = picture.flip(Picture.FORWARD_DIAGONAL);
			// repaint();
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(flipBackward)) {
			picture = picture.flip(Picture.BACKWARD_DIAGONAL);
			// repaint();
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(ascii)) {
			picture = new Picture(picture.convertToAscii());
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(paintBucket)) {
			int threshold =  getParameterValue("the color threshold", 1, 100);
			picture = new Picture(picture.paintBucket(xIndex, yIndex, threshold, new Color(0, 0, 255)));
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(lighten)) {
			int value =  getParameterValue("the amount to increase all colors ", 1, 255);
			picture = new Picture(picture.lighten(value));
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(darken)) {
			int value =  getParameterValue("the amount to decrease all colors ", 1, 255);
			picture = new Picture(picture.darken(value));
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(addRed)) {
			int value =  getParameterValue("the amount to increase red", 1, 255);
			picture = new Picture(picture.addRed(value));
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(addGreen)) {
			int value =  getParameterValue("the amount to increase green", 1, 255);
			picture = new Picture(picture.addGreen(value));
			createAndInitScrollingImage();
		}
		if (a.getActionCommand().equals(addBlue)) {
			int value =  getParameterValue("the amount to increase blue", 1, 255);
			picture = new Picture(picture.addBlue(value));
			createAndInitScrollingImage();
		}
	}

	/**
	 * Saves the file corresponding to the current picture.
	 */
	private void saveFile() {
		File file = new File(picture.getFileName());

		try {
			ImageIO.write(picture.getBufferedImage(),
					picture.getExtension(), file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(pictureFrame,
				    "Could not save file.",
				    "Save Error",
				    JOptionPane.WARNING_MESSAGE);
		}

	}

	/**
	 * Handle input to functions
	 */
	public int getParameterValue(String parameterName, int min, int max) {
		String inputValue = JOptionPane
				.showInputDialog("Please input a value for " + parameterName
						+ " in the range of " + min + " to " + max);
		try {
			// the String to int conversion happens here
			int i = Integer.parseInt(inputValue.trim());
			if (i > max) {
				JOptionPane.showMessageDialog(null, "Your input " + inputValue
						+ " for " + parameterName
						+ " was too large. The maximum value " + max
						+ " will be used.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
				return max;
			} else if (i < min) {
				JOptionPane.showMessageDialog(null, "Your input " + inputValue
						+ " for " + parameterName
						+ " was too small. The minimum value " + min
						+ " will be used.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
				return min;
			} else {
				// they provide input in a valid range.
				return i;
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Your input " + inputValue
					+ " for " + parameterName
					+ " was invalid. The minimum value " + min
					+ " will be used.", "Invalid Input",
					JOptionPane.ERROR_MESSAGE);
			return min;
		}

	}
	/**
	 * Class for establishing the focus for the textfields.
	 */
	private class PictureExplorerFocusTraversalPolicy
	extends FocusTraversalPolicy {

		/**
		 * Gets the next component for focus.
		 */
		public Component getComponentAfter(Container focusCycleRoot,
				Component aComponent) {
			if (aComponent.equals(xValue))
				return yValue;
			else 
				return xValue;
		}

		   
		/**
		 * Gets the previous component for focus.
		 */
		public Component getComponentBefore(Container focusCycleRoot,
				Component aComponent) {
			if (aComponent.equals(xValue))
				return yValue;
			else 
				return xValue;
		}

		public Component getDefaultComponent(Container focusCycleRoot) {
			return xValue;
		}

		public Component getLastComponent(Container focusCycleRoot) {
			return yValue;
		}

		public Component getFirstComponent(Container focusCycleRoot) {
			return xValue;
		}
	}

} // End of PictureExplorer</tt> class.
