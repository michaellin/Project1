package src;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.io.*;

/**
 * A class to make working with a file chooser easier for students.
 * 	It uses a JFileChooser to let the user pick a file and returns the chosen
 * 	file name.
 * 
 * @author Barb Ericson (ericson@cc.gatech.edu)
 * 	(Copyright Georgia Institute of Technology 2004)
 * @author Modified by Colleen Lewis (colleenl@berkeley.edu),
 * 	Jonathan Kotker (jo_ko_berkeley@berkeley.edu),
 * 	Kaushik Iyer (kiyer@berkeley.edu), George Wang (georgewang@berkeley.edu),
 * 	and David Zeng (davidzeng@berkeley.edu), for use in CS61BL, the data
 * 	structures course at University of California, Berkeley.
 */
public class FileChooser 
{

	////////////////////////////////Fields ///////////////////////////////////

	/** Represents a dialog box to select a file to be opened. */
	public static String OPEN = "Open";
	
	/** Represents a dialog box to select a file to be saved. */
	public static String SAVE = "Save";
	
	////////////////////////////////// Methods ////////////////////////////////

	/**
	 * Allows the user to pick a file and return the full file name as a
	 * string. If the user did not pick a file, then the file name will be
	 * null.
	 * 
	 * @param diagBoxType Type of dialog box to open.
	 * 
	 * @return The full filename of the picked file, or null.
	 */
	public static String pickAFile(String diagBoxType)
	{
		JFileChooser fileChooser = null;

		// Start the filename off as null.
		String fileName = null;

		// Get the current media directory.
		String mediaDir = "";

		/* Create a file for this and check that the directory exists
		 * and if it does, set the file chooser to use it.
		 */
		try {
			File file = new File(mediaDir);
			if (file.exists())
				fileChooser = new JFileChooser(file);
		} catch (Exception ex) {
		}

		// If no file chooser exists yet, create one
		if (fileChooser == null)
			fileChooser = new JFileChooser();

		/* Create a JFrame to be the parent of the file 
		 * chooser open dialog; without this, the dialog may not
		 * be seen.
		 */
		JFrame frame = new JFrame();

		// Get the return value from choosing a file.
		int returnVal = (diagBoxType == FileChooser.OPEN) ?
				fileChooser.showOpenDialog(frame) :
					fileChooser.showSaveDialog(frame);

		/* If the return value says the user picked a file,
		 * return the corresponding file name.
		 */
		if (returnVal == JFileChooser.APPROVE_OPTION)
			fileName = fileChooser.getSelectedFile().getPath();

		return fileName;
	}
	
} // End of FileChooser class.
