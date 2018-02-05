
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