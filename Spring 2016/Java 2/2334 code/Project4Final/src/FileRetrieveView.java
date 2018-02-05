import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/*
 * Needs to make calls to ParseFile. ParseFile will expect a File[]. Use JFileChoosers 'getSelectedFiles' method to get this array.
 */
public class FileRetrieveView {
	private MediaModel model;
	private JFileChooser menu = new JFileChooser();

	FileRetrieveView() {

		this.menu = new JFileChooser();
		menu.setVisible(true);
	}

	public File getFile() {
		return menu.getSelectedFile();
	}

	/*
	 * public void actionPerformed(ActionEvent event) { if (event.getSource() ==
	 * openButton) { int returnVal = menu.showOpenDialog(menu);
	 * 
	 * if (returnVal == JFileChooser.APPROVE_OPTION) { File file =
	 * menu.getSelectedFiles(); } } }
	 */

	public void setModel(MediaModel model) {
		this.model = model;
	}
}