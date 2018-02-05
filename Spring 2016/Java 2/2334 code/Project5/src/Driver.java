/**
 * Project #5
 * CS 2334, Section 010 - Lab 13
 * Hunter Black
 * April 25, 2016
 * <P>
 * This is the driver class for the program, containing the main method. It creates 
 * a MediaModel, MediaController, and SelectionView. These then run the rest of the
 * program.
 * 
 * This program is in the program design format of MVC
 * </P>
 * @version 1.0
 */
public class Driver 
{

	/** This will be the base MediaModel of this program */
	private static MediaModel model = new MediaModel();
	/** This will be the base MediaController for this program */
	private static MediaController controller = new MediaController();
	/** This will be the base SelectionView for this program */
	private static SelectionView selectionView = new SelectionView();
	
	/**
	 * This is the main method of the program, where the program will begin
	 * 
	 * @param args			Program arguments
	 */
	public static void main(String[] args) {
		selectionView.setModel(model);
		controller.setModel(model);
		controller.setSelectionView(selectionView);
	}

}
