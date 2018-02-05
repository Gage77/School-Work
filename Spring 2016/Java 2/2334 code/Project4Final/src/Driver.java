/**
 * Project #4
 * CS 2334, Section 010 - Lab 13
 * Haley Mitchel, Hunter Black, Austin Phipps
 * April 21, 2016
 * <P>
 * This is the driver class for the program, contains the main method. It creates 
 * a MediaModel, MediaController, and SelectionView. These then run the rest of the
 * program
 * </P>
 * @version 1.0
 */
public class Driver 
{
	
	private static MediaModel model = new MediaModel();
	private static MediaController controller = new MediaController();
	private static SelectionView selectionView = new SelectionView();
	
	public static void main(String[]args)
	{
		selectionView.setModel(model);
		controller.setModel(model);
		controller.setSelectionView(selectionView);
	}
	
}