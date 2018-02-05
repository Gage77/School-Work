
/**
 * Lab #7 CS 2334, Section 013 4/7/16
 * Hunter Black, Andrew Easley 
 * <P>
 * This is the driver class for the MVC lab. It creates and initializes the
 * variables for the model, views and controller.
 * </P>
 * 
 * @version 1.0
 */
public class Lab7Driver {
	private Model model; 
	private StateView stateView = new StateView();
	private PlaceView placeView = new PlaceView();
	private Controller controller = new Controller();

	/**
	 * This method initializes the variables for the model, views, and
	 * controller. It sets the model for the views and controller and also sets
	 * the view in the controller.
	 * <P>
	 * 
	 * @param filename
	 *            String Name of the filename to be read.
	 */
	public Lab7Driver(String filename) {
		model = new Model();
		model.readFile(filename);
		stateView.setModel(model);
		placeView.setModel(model);
		controller.setModel(model);
		controller.setView(stateView);
	}

	/**
	 * This is the main method of the program. It calls the constructor.
	 * <P>
	 * 
	 * @param args
	 *            Command line arguments. This program requires a filename as
	 *            the first argument.  No other arguments are used.
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			new Lab7Driver(args[0]);
		} else {
			System.out.println("Useage: Please specify the file name as the argument.");
		}
	}
}