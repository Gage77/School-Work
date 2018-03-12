//******************************************************************************
// Copyright (C) 2016-2018 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Feb  9 20:33:16 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20160209 [weaver]:	Original file (for CS 4053/5053 homeworks).
// 20180123 [weaver]:	Modified for use in CS 3053 team projects.
//
//******************************************************************************
// Notes:
//	Icon author credit:
//		Magnifying Glass: Icon made by Gregor Cresnar from www.flatiron.com
//		Gear: 						Icon made by Gregor Cresnar from www.flatiron.com
//		Restore:					Icon made by Gregor Cresnar from www.flatiron.com
//		Filter:						Icon made by Freepik from www.flatiron.com
//		Bold:							Icon made by Dave Gandy from www.flatiron.com
//
//******************************************************************************

package edu.ou.cs.hci.stages;

//import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.ou.cs.hci.resources.*;

//******************************************************************************

/**
 * The <CODE>Stage4</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Stage5
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	public static Action quitAction = new QuitAction("Quit", "Exit the program and save info to text file");

	//**********************************************************************
	// Private Members
	//**********************************************************************

	private static JFrame frame;

	// File menu items
	private static JMenuItem openItem;
	private static JMenuItem saveItem;
	private static JMenuItem printAllItem;
	private static JMenuItem printFridgeItem;
	private static JMenuItem printGroceriesItem;
	private static JMenuItem printRecipeItem;
	private static JMenuItem quitItem;

	// Edit menu items
	private static JMenuItem cutItem;
	private static JMenuItem copyItem;
	private static JMenuItem pasteItem;
	private static JMenuItem searchItem;
	private static JMenuItem restoreItem;
	private static JMenuItem quantityItem;
	private static JMenuItem expItem;
	private static JMenuItem favoriteItem;
	private static JMenuItem leftoverItem;

	// Window menu items
	private static JMenuItem fontItem;
	private static JMenuItem fontColItem;
	private static JMenuItem primaryItem;
	private static JMenuItem secondaryItem;
	private static JMenuItem swapItem;
	private static JMenuItem importItem;
	private static JMenuItem saveThmItem;
	private static JMenuItem restorePointItem;
	private static JMenuItem customaryItem;
	private static JMenuItem metricItem;
	private static JMenuItem imperialItem;

	// Help menu items
	private static JMenuItem linkItem;
	private static JMenuItem fontSizeItem;
	private static JMenuItem resolutionItem;
	private static JMenuItem invertItem;
	private static JMenuItem boldItem;
	private static JMenuItem feedbackItem;
	private static JMenuItem donateItem;

	//**********************************************************************
	// Main
	//**********************************************************************

	//main
	public static void main(String[] args)
	{
		//MAIN WINDOW creates the base JFrame on which everything will be displayed
		frame = new JFrame("FridgTrackr");

		createMenuBar(frame);

		// Top panel
		JPanel top = new JPanel(new BorderLayout());
		JButton back = new JButton("<-");
		back.setPreferredSize(new Dimension(100, 50));
		back.setFont(new Font("Arial", Font.PLAIN, 25));
		//back.setBorder(new EmptyBorder(0, 15, 0, 0));
		JLabel welcome = new JLabel("Welcome to FridgTrackr!");
		welcome.setBorder(new EmptyBorder(0, 125, 0, 0));
		JButton add = new JButton("+");
		add.setFont(new Font("Arial", Font.PLAIN, 25));
		//add.setBorder(new EmptyBorder(0, 0, 0, 15));
		add.setPreferredSize(new Dimension(100, 50));
		top.add(back, BorderLayout.LINE_START);
		top.add(welcome, BorderLayout.CENTER);
		top.add(add, BorderLayout.LINE_END);

		createToolBar(top);

		ActionListener backListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("back button pressed. value: N/A");
			}
		};     back.addActionListener(backListener);

		ActionListener addListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("add item button pressed. value: N/A");
			}
		};     add.addActionListener(addListener);

		//creates the 3 category panels
		JPanel			recipes = new JPanel(new BorderLayout());
		JPanel			fridge = new JPanel(new BorderLayout());
		JPanel			groceries = new JPanel(new BorderLayout());

		//adds a title to each category panel
		recipes.setBorder(BorderFactory.createTitledBorder("recipes"));
		fridge.setBorder(BorderFactory.createTitledBorder("fridge"));
		groceries.setBorder(BorderFactory.createTitledBorder("groceries"));

		//sets the defualt size of the main window
		frame.setBounds(50, 50, 600, 600);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(top, BorderLayout.PAGE_START);

		//creates the pane that will store the category tabs
		JTabbedPane tabs = new JTabbedPane();
		//sets icons for tabs
		Icon fridgeIcon = new ImageIcon(Stage5.class.getResource("refrigerator.png"));
		Icon recipesIcon = new ImageIcon(Stage5.class.getResource("contract.png"));
		Icon groceriesIcon = new ImageIcon(Stage5.class.getResource("groceries.png"));
		//adds tabs to JTabbedPane
		tabs.addTab("Fridge", fridgeIcon, fridge);
		tabs.addTab("Recipes", recipesIcon, recipes);
		tabs.addTab("Groceries", groceriesIcon, groceries);
		//adds the JTabbedPane to the base pane
		frame.getContentPane().add(tabs, BorderLayout.CENTER);

		//creates the content of the fridge category panel
		String[] colName = new String[] {"Name" ,"Amount", "Delete"};
		Object[][] products = new Object[][] {
                { "Apples" ,"15", "[x]" },
                { "Oranges" ,"20", "[x]"},
                { "Peaches" ,"10", "[x]"},
							};
		//creates a table to hold the fridge panel data
    	JTable fridgeTable = new JTable( products, colName );
		//adds the data panel to the fridge category panel
		fridge.add(new JScrollPane(fridgeTable));

		//creates the content of the groceries category panel
		String[] colName1 = new String[] {"Name" ,"Amount", "Delete"};
		Object[][] products1 = new Object[][] {
							 { "Apples" ,"15", "[x]" },
							 { "Oranges" ,"20", "[x]"},
							 { "Peaches" ,"10", "[x]"},
						 };
		//creates a table to hold the groceries panel data
		JTable table1 = new JTable( products1, colName1 );
		//adds the data panel to the fridge category panel
		groceries.add(new JScrollPane(table1) );

		//creates the content of the recipes category panel
		String[] colName2 = new String[] {"Name","Delete"};
    	Object[][] products2 = new Object[][] {
                { "Grilled Cheese", "[x]" },
                { "Pizza", "[x]" },
                { "Mac & Cheese", "[x]" },
            };
		//creates a table to hold the recipes panel data
		JTable table2 = new JTable( products2, colName2);
		//adds the data panel to the recipes category panel
    	recipes.add( new JScrollPane(table2));
		//creates the filters
		JPanel			filterPanel = new JPanel(new GridLayout(2, 2));
		filterPanel.setBorder(new EmptyBorder(0, 150, 0, 150));
		JCheckBox		favoritesBox = new JCheckBox();
		JLabel			favoritesLabel = new JLabel("Favorites");
		JCheckBox		expiredBox = new JCheckBox();
		JLabel			expiredLabel = new JLabel("Expired");
		JCheckBox		lowBox = new JCheckBox();
		JLabel			lowLabel = new JLabel("Low Stock");
		JCheckBox		leftoversBox = new JCheckBox();
		JLabel			leftoversLabel = new JLabel("Leftovers");

		//adds the filters to the filter panel
		filterPanel.add(favoritesBox);
		filterPanel.add(favoritesLabel);
		filterPanel.add(expiredBox);
		filterPanel.add(expiredLabel);
		filterPanel.add(lowBox);
		filterPanel.add(lowLabel);
		filterPanel.add(leftoversBox);
		filterPanel.add(leftoversLabel);

		ActionListener favListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("favorites filter checkbox pressed. value: " + favoritesBox.isSelected());
			}
		};     favoritesBox.addActionListener(favListener);

		ActionListener expiredListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("expired filter checkbox pressed. value: " + expiredBox.isSelected());
			}
		};     expiredBox.addActionListener(expiredListener);

		ActionListener lowListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("low stock filter checkbox pressed. value: " + lowBox.isSelected());
			}
		};     lowBox.addActionListener(lowListener);

		ActionListener leftoversListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("leftovers filter checkbox pressed. value: " + leftoversBox.isSelected());
			}
		};     leftoversBox.addActionListener(leftoversListener);
		//adds the filter panel to the base frame
		fridge.add(filterPanel, BorderLayout.NORTH);

		//sets the base frame to visible & to end on exit
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
				public void windowClosing(WindowEvent e)
				{
					quitProtocol();
				}
			});

	// --------------------------------------
    // PERSONAS & SCENARIOS
	// --------------------------------------
	JFrame			personas = new JFrame("Senarios");

	//creates the titles and senarios panels
	JPanel			listPane = new JPanel(new BorderLayout());
	JPanel			descriptPane = new JPanel(new BorderLayout());

	//JText Area string
	String text = "default";

	//titles for each pane
	listPane.setBorder(BorderFactory.createTitledBorder("scenarios"));
	descriptPane.setBorder(BorderFactory.createTitledBorder("descriptions"));

	//set up frame
	personas.setBounds(150, 100, 700, 600);
	personas.getContentPane().setLayout(new BorderLayout());

	//add things to frame
	personas.getContentPane().add(listPane, BorderLayout.WEST);
	personas.getContentPane().add(descriptPane, BorderLayout.CENTER);

	ArrayList<String> desc = Resources.getLines("scenarios/descriptions.txt");
	ArrayList<String> tit  = Resources.getLines("scenarios/titles.txt");

	if (desc.isEmpty()) {
		desc.add("default ");
	}
	if (tit.isEmpty()) {
		tit.add("default ");
	}

	//list
	//String[] sen = new String[] {"long selections" ,"sen 2", "sen 3"};
	DefaultListModel<String> dfl = new DefaultListModel<String>();
	for (int i = 0; i < tit.size(); i++) {
		dfl.addElement(tit.get(i));
	}

	JList			scenarios = new JList(dfl);
	scenarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	scenarios.setLayoutOrientation(JList.VERTICAL);
	scenarios.setVisibleRowCount(-1);
	scenarios.setSelectedIndex(0);

	//JTextArea
	JTextArea		descriptions = new JTextArea(text);
	descriptions.setFont(new Font("Serif", Font.ITALIC, 16));
	descriptions.setLineWrap(true);
	descriptions.setWrapStyleWord(true);
	descriptions.setText(desc.get(scenarios.getSelectedIndex()));

	//selection listener
	ListSelectionListener sl = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {

				descriptions.setText(desc.get(scenarios.getSelectedIndex()));
			}
		}
	};

	scenarios.addListSelectionListener(sl);
	listPane.add(scenarios, BorderLayout.CENTER);
	descriptPane.add(descriptions, BorderLayout.CENTER);

	// ------------------------------------------------------
	// SURVEY AND ANALYSIS
	// ------------------------------------------------------
	JFrame			survey 	= new JFrame("Survey and Analysis");
	survey.setBounds(50, 50, 850, 700);
	survey.getContentPane().setLayout(new BorderLayout());

	JPanel 			mainPane = new JPanel();
	mainPane.setLayout(new GridLayout(0,1));
	survey.add(mainPane);


	//Question 1 pane
	JPanel			questionPane1		= new JPanel();
    questionPane1.setBorder(BorderFactory.createLineBorder(Color.black));
	questionPane1.setLayout(new GridLayout(5,1));
	mainPane.add(questionPane1);

	//Question 1 Responses
	JLabel			question1	= new JLabel("Q1: Which of these, in your opinion, is the biggest problem?");
	JCheckBox		box1 = new JCheckBox("Not knowing what foods I have to make meals with");
	JCheckBox		box2 = new JCheckBox("Not knowing what I need to go grocery shopping for");
	JCheckBox		box3 = new JCheckBox("Accidentally letting my food go bad");

	//Q1 add
	questionPane1.add(question1);
	questionPane1.add(box1);
	questionPane1.add(box2);
	questionPane1.add(box3);

	//RANGE
	JPanel range = new JPanel(new GridLayout(0, 1));
	range.setBorder(BorderFactory.createLineBorder(Color.black));
	mainPane.add(range);

	JLabel question = new JLabel("Q2: How much of the food that you buy would you estimate that you waste/throw out?");

	range.add(question);
	JRadioButton button1 = new JRadioButton("0-10%");
	JRadioButton button2 = new JRadioButton("10-20%");
	JRadioButton button3 = new JRadioButton("20-30%");
	JRadioButton button4 = new JRadioButton("Over 30%");

	ButtonGroup group = new ButtonGroup();
    group.add(button1);
    group.add(button2);
    group.add(button3);
    group.add(button4);

    range.add(button1);
    range.add(button2);
    range.add(button3);
    range.add(button4);

    //SPINNER
    //panel for the # of groceries questions
    JPanel spinPanel = new JPanel(new BorderLayout());
    mainPane.add(spinPanel);
    //adds the questions text
    JLabel spinQuestion = new JLabel("Q3: How many people do you share a kitchen with?");
    //creates the spinner model
    SpinnerNumberModel bagModel = new SpinnerNumberModel(0 /*default*/, 0 /*min*/, 100/*max*/, 1/*incr.*/);
    //creates spinner
    JSpinner groSpinner = new JSpinner(bagModel);
    //adds the question text to the panel
    spinPanel.add(spinQuestion, BorderLayout.NORTH);
    //adds the spinner to panel
    spinPanel.add(groSpinner, BorderLayout.SOUTH);
    //adds a border to the panel
    spinPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    JPanel			questPanel = new JPanel();
    questPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    mainPane.add(questPanel);
    GridLayout		questionLayout = new GridLayout(2,1);
    questPanel.setLayout(questionLayout);
    JTextArea		questionArea = new JTextArea("Q4: If you could, what information would you like to always know about "
    				+ "your kitchen, in order of importance? For example food stock, expiration dates, etc.");
    questionArea.setEditable(false);
    questionArea.setLineWrap(true);
    questionArea.setOpaque(false);
    JTextArea		answerArea = new JTextArea();
    answerArea.setBorder(BorderFactory.createLineBorder(Color.black));
    questPanel.add(questionArea);
    questPanel.add(answerArea);

    // SLIDER
 	JPanel q4 = new JPanel(new GridLayout(2, 1));
 	mainPane.add(q4);

 	// Setup parameters for slider
 	int sliderMin = 0;
 	int sliderMax = 4;
 	int sliderInitial = 2;
 	// Create slider
 	JSlider usefullnessSlider = new JSlider(JSlider.HORIZONTAL, sliderMin,
 		sliderMax, sliderInitial);

 	// Create labels for slider in hashtable. Integer value corresponds to its
 	// cooresponding location on slider
 	Hashtable<Integer, JLabel> sliderTable = new Hashtable<Integer, JLabel>();
 	sliderTable.put(new Integer(0), new JLabel("Very Displeased"));
 	sliderTable.put(new Integer(1), new JLabel("Somewhat Displeased"));
 	sliderTable.put(new Integer(2), new JLabel("Neutral"));
 	sliderTable.put(new Integer(3), new JLabel("Relatively Pleased"));
 	sliderTable.put(new Integer(4), new JLabel("Very Pleased"));

 	// Add labels to slider
 	usefullnessSlider.setLabelTable(sliderTable);

 	// Make the slider more useful by adding the following
 	usefullnessSlider.setSnapToTicks(false);
 	usefullnessSlider.setMajorTickSpacing(10);
 	usefullnessSlider.setPaintLabels(true);

 	// Setup the question
 	String scaleQuestionText = "<html> On a scale of 1-5, how pleased are you with the"
 			+ "<BR>organization of your fridge and pantry?</html>";

 	JLabel scaleQuestion = new JLabel(scaleQuestionText);

 	// Add the question to the frame
 	q4.add(scaleQuestion);
 	q4.add(usefullnessSlider);
 	q4.setBorder(BorderFactory.createLineBorder(Color.black));

    JButton finish = new JButton("Finish");
    mainPane.add(finish);

ActionListener finishListener = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{

				// RANGE QUESTION CHECK
				boolean rangeIsClicked = false;
				String rangeAnswer = "";
				if (button1.isSelected())
				{
					rangeAnswer = button1.getText();
					rangeIsClicked = true;
				}
				else if (button2.isSelected())
				{
					rangeAnswer = button2.getText();
					rangeIsClicked = true;
				}
				else if (button3.isSelected())
				{
					rangeAnswer = button3.getText();
					rangeIsClicked = true;
				}
				else if (button4.isSelected())
				{
					rangeAnswer = button4.getText();
					rangeIsClicked = true;
				}

				boolean selectIsClicked = false;
				String selectAnswer = "";
				if (box1.isSelected())
				{
					selectAnswer += box1.getText() + "\n";
					selectIsClicked = true;
				}
				if (box2.isSelected())
				{
					selectAnswer += box2.getText() + "\n";
					selectIsClicked = true;
				}
				if (box3.isSelected())
				{
					selectAnswer += box3.getText() + "\n";
					selectIsClicked = true;
				}

				int spinnerVal = (Integer) groSpinner.getValue();


				int sliderVal = usefullnessSlider.getValue();
				String sliderString = sliderTable.get(sliderVal).getText();



				// FINAL WRITE
				if (selectIsClicked && rangeIsClicked && !(answerArea.getText().isEmpty()))
				{
					BufferedWriter output = null;
				    try {
				        File file = new File("survey_results.txt");
				        output = new BufferedWriter(new FileWriter(file));

				        output.write(question1.getText() + "\n");
				        output.write(selectAnswer + "\n");

				        output.write(question.getText() + "\n");
				        output.write(rangeAnswer + "\n\n");

				        output.write(spinQuestion.getText() + "\n");
				        output.write(spinnerVal + "\n\n");

				        output.write(questionArea.getText() + "\n");
				        output.write(answerArea.getText() + "\n\n");

				        output.write("Q5: How useful would an application that keeps "
				        		+ "track of the items in your fridge and pantry and allows "
				        		+ "you to set up reminders when you are running low on a "
				        		+ "specific item be to you?\n");
				        output.write(sliderString + "\n\n");

				    } catch ( IOException e1 ) {
				        e1.printStackTrace();
				    } finally {
				      if ( output != null ) {
				        try {
							output.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				      }
				    }

				  	survey.dispose();
				    survey.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(survey, "Please answer all questions.");
				}

		}
	};     finish.addActionListener(finishListener);

		//sets personas frame to visible and end on exit
		/* personas.setVisible(true);
		personas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		personas.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});*/

		//sets personas frame to visible and end on exit
		/* survey.setVisible(true);
		survey.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		survey.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});*/
	}

	//**********************************************************************
	// Private Inner Classes
	//**********************************************************************

	//***********************************
	// Create the menu bar for the main window
	//***********************************
	private static void createMenuBar(JFrame frame)
	{
		JMenuBar menuBar = new JMenuBar();

		//***********************************
		// Create file menu
		//***********************************
		JMenu fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
			openItem.setMnemonic(KeyEvent.VK_O);
			associateActionListener(openItem, "File menu -> Open pressed");
	 	saveItem = new JMenuItem("Save");
			saveItem.setMnemonic(KeyEvent.VK_S);
			associateActionListener(saveItem, "File menu -> Save pressed");
		JMenu printSubMenu = new JMenu("Print");	// Submenu
		printAllItem = new JMenuItem("All");	// In print submenu
			printAllItem.setMnemonic(KeyEvent.VK_P);
			associateActionListener(printAllItem, "File menu -> Save menu -> Print all pressed");
		printFridgeItem = new JMenuItem("Fridge");	// In print submenu
			associateActionListener(printFridgeItem, "File menu -> Save menu -> Print fridge pressed");
		printGroceriesItem = new JMenuItem("Groceries");	// In print submenu
			associateActionListener(printGroceriesItem, "File menu -> Save menu -> Print groceries pressed");
		printRecipeItem = new JMenuItem("Recipes");	// In print submenu
			associateActionListener(printRecipeItem, "File menu -> Save menu -> Print recipes pressed");

		quitItem = new JMenuItem(quitAction);
		quitItem.setMnemonic(KeyEvent.VK_Q);

		printSubMenu.add(printAllItem);
		printSubMenu.add(printFridgeItem);
		printSubMenu.add(printGroceriesItem);
		printSubMenu.add(printRecipeItem);

		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(printSubMenu);
		fileMenu.add(quitItem);

		menuBar.add(fileMenu);

		//***********************************
		// Create edit menu
		//***********************************
		JMenu editMenu = new JMenu("Edit");
		cutItem = new JMenuItem("Cut");
			cutItem.setMnemonic(KeyEvent.VK_X);
			associateActionListener(cutItem, "Edit menu -> Cut pressed");
		copyItem = new JMenuItem("Copy");
			copyItem.setMnemonic(KeyEvent.VK_C);
			associateActionListener(copyItem, "Edit menu -> Copy pressed");
		pasteItem = new JMenuItem("Paste");
			pasteItem.setMnemonic(KeyEvent.VK_V);
			associateActionListener(pasteItem, "Edit menu -> Paste pressed");
		searchItem = new JMenuItem("Search");
			searchItem.setMnemonic(KeyEvent.VK_F);
			associateActionListener(searchItem, "Edit menu -> Search pressed");
		restoreItem = new JMenuItem("Restore");
			restoreItem.setMnemonic(KeyEvent.VK_Z);
			associateActionListener(restoreItem, "Edit menu -> Restore pressed");
		JMenu filterBySubMenu = new JMenu("Filter by");	// Submenu
		quantityItem = new JMenuItem("Quantity");	// In print submenu
			associateActionListener(quantityItem, "Edit menu -> Filter by menu -> Quantity pressed");
		expItem = new JMenuItem("Expiration Date");	// In print submenu
			associateActionListener(expItem, "Edit menu -> Filter by menu -> Expiration date pressed");
		favoriteItem = new JMenuItem("Favorites");	// In print submenu
			associateActionListener(favoriteItem, "Edit menu -> Filter by menu -> Favorites pressed");
		leftoverItem = new JMenuItem("Left Overs");	// In print submenu
			associateActionListener(leftoverItem, "Edit menu -> Filter by menu -> Left overs pressed");

		filterBySubMenu.add(quantityItem);
		filterBySubMenu.add(expItem);
		filterBySubMenu.add(favoriteItem);
		filterBySubMenu.add(leftoverItem);

		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.addSeparator();
		editMenu.add(searchItem);
		editMenu.add(restoreItem);
		editMenu.add(filterBySubMenu);

		menuBar.add(editMenu);

		//***********************************
		// Create window menu
		//***********************************
		JMenu windowMenu = new JMenu("Window");
		JMenu themeSubMenu = new JMenu("Theme");	// Theme submenu
		fontItem = new JMenuItem("Font");	// In theme submenu
			associateActionListener(fontItem, "Window menu -> Theme menu -> Font pressed");
		fontColItem = new JMenuItem("Font Color");	// In theme submenu
			associateActionListener(fontColItem, "Window menu -> Theme menu -> Font Color pressed");
		JMenu screenColSubMenu = new JMenu("Screen Color"); // Screen color submenu in theme submenu
		primaryItem = new JMenuItem("Primary");	// In screen color submenu
			associateActionListener(primaryItem, "Window menu -> Theme menu -> Screen Color menu -> Primary pressed");
		secondaryItem = new JMenuItem("Secondary");	// In screen color submenu
			associateActionListener(secondaryItem, "Window menu -> Theme menu -> Screen Color menu -> Secondary pressed");
		swapItem = new JMenuItem("Swap");	// In screen color submenu
			associateActionListener(swapItem, "Window menu -> Theme menu -> Screen Color menu -> Swap pressed");
		importItem = new JMenuItem("Import");	// In theme submenu
			associateActionListener(importItem, "Window menu -> Theme menu -> Import pressed");
		saveThmItem = new JMenuItem("Save Theme");	// In theme submenu
			associateActionListener(saveThmItem, "Window menu -> Theme menu -> Save theme pressed");
		JMenu settingsSubMenu = new JMenu("Settings");	// Settings Submenu
		restorePointItem = new JMenuItem("Restore Point");	// in settings submenu
			associateActionListener(restorePointItem, "Window menu -> Settings menu -> Restore point pressed");
		JMenu unitSubMenu = new JMenu("Set Units");	// Set units submenu in settings submenu
		customaryItem = new JMenuItem("Customary"); // In units submenu
			associateActionListener(customaryItem, "Window menu -> Settings menu -> Set units menu -> Customary pressed");
		metricItem = new JMenuItem("Metric"); // In units submenu
			associateActionListener(metricItem, "Window menu -> Settings menu -> Set units menu -> Metric pressed");
		imperialItem = new JMenuItem("Imperial"); // In units submenu
			associateActionListener(imperialItem, "Window menu -> Settings menu -> Set units menu -> Imperial pressed");

		screenColSubMenu.add(primaryItem);
		screenColSubMenu.add(secondaryItem);
		screenColSubMenu.add(swapItem);

		themeSubMenu.add(fontItem);
		themeSubMenu.add(fontColItem);
		themeSubMenu.add(screenColSubMenu);
		themeSubMenu.add(importItem);
		themeSubMenu.add(saveThmItem);

		unitSubMenu.add(customaryItem);
		unitSubMenu.add(metricItem);
		unitSubMenu.add(imperialItem);

		settingsSubMenu.add(restorePointItem);
		settingsSubMenu.add(unitSubMenu);

		windowMenu.add(themeSubMenu);
		windowMenu.add(settingsSubMenu);

		menuBar.add(windowMenu);

		//***********************************
		// Create help menu
		//***********************************
		JMenu helpMenu = new JMenu("Help");
		linkItem = new JMenuItem("Help");
			associateActionListener(linkItem, "Help menu -> Help pressed");
		JMenu accessabilitySubMenu = new JMenu("Accessability");
		fontSizeItem = new JMenuItem("Font Size");
			associateActionListener(fontSizeItem, "Help menu -> Accessability menu -> Font size pressed");
		resolutionItem = new JMenuItem("Resolution");
			associateActionListener(resolutionItem, "Help menu -> Accessability menu -> Resolution pressed");
		invertItem = new JMenuItem("Invert");
			associateActionListener(invertItem, "Help menu -> Accessability menu -> Invert pressed");
		boldItem = new JMenuItem("Bold");
			associateActionListener(boldItem, "Help menu -> Accessability menu -> Bold pressed");
		feedbackItem = new JMenuItem("Feedback");
			feedbackItem.setMnemonic(KeyEvent.VK_H);
			associateActionListener(feedbackItem, "Help menu -> Feedback pressed");
		donateItem = new JMenuItem("Donate");
			associateActionListener(donateItem, "Help menu -> Donate pressed");

		accessabilitySubMenu.add(fontSizeItem);
		accessabilitySubMenu.add(resolutionItem);
		accessabilitySubMenu.add(invertItem);
		accessabilitySubMenu.add(boldItem);

		helpMenu.add(linkItem);
		helpMenu.add(accessabilitySubMenu);
		helpMenu.add(feedbackItem);
		helpMenu.add(donateItem);

		menuBar.add(helpMenu);

		//***********************************
		// Add menubar to frame and return
		//***********************************
		frame.setJMenuBar(menuBar);
	}

	//***********************************
	// Create the toolbar for the main menu
	//***********************************
	private static void createToolBar(JPanel panel)
	{
		// Setup the toolbar and create its buttons
		JToolBar toolBar = new JToolBar("Tools");

		ImageIcon searchIcon = Resources.getImage("icons/magnifying-glass.png");
		Action searchTool = new SearchAction("Search", searchIcon, "Search for a specific item in the collection");
		JButton searchButton = new JButton(searchTool);

		ImageIcon filterIcon = Resources.getImage("icons/filter.png");
		Action filterTool = new FilterAction("Filter", filterIcon, "Filter collection by...");
		JButton filterButton = new JButton(filterTool);

		ImageIcon settingsIcon = Resources.getImage("icons/settings.png");
		Action settingsTool = new SettingsAction("Settings", settingsIcon, "Adjust window settings");
		JButton settingsButton = new JButton(settingsTool);

		ImageIcon boldIcon = Resources.getImage("icons/bold-text-option.png");
		Action boldTool = new BoldAction("Bold", boldIcon, "Set all text to bold");
		JButton boldButton = new JButton(boldTool);

		ImageIcon restoreIcon = Resources.getImage("icons/reload.png");
		Action restoreTool = new RestoreAction("Restore", restoreIcon, "Restore deleted item");
		JButton restoreButton = new JButton(restoreTool);

		// Add buttons to toolbar
		toolBar.add(searchButton);
		toolBar.add(filterButton);
		toolBar.addSeparator();
		toolBar.add(settingsButton);
		toolBar.add(boldButton);
		toolBar.add(restoreButton);

		// Add toolbar to the main window
		panel.add(toolBar, BorderLayout.PAGE_START);
	}

	//***********************************
	// Create an action listener for the specified JMenuItem
	//***********************************
	private static void associateActionListener(JMenuItem item, String output)
	{
		item.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ev)
			{
				System.out.println(output);
			}
		});
	}

	//***********************************
	// Internal Action class for search toolbar button
	//***********************************
	public static class SearchAction extends AbstractAction
	{
		public SearchAction(String name, ImageIcon icon, String shortDescription)
		{
			super(name, icon);
			putValue(SHORT_DESCRIPTION, shortDescription);
		}

		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Toolbar -> Search\n" + "\t" + this.getValue(SHORT_DESCRIPTION));
		}
	}

	//***********************************
	// Internal Action class for filter toolbar button
	//***********************************
	public static class FilterAction extends AbstractAction
	{
		public FilterAction(String name, ImageIcon icon, String shortDescription)
		{
			super(name, icon);
			putValue(SHORT_DESCRIPTION, shortDescription);
		}

		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Toolbar -> Filter\n" + "\t" + this.getValue(SHORT_DESCRIPTION));
		}
	}

	//***********************************
	// Internal Action class for settings toolbar button
	//***********************************
	public static class SettingsAction extends AbstractAction
	{
		public SettingsAction(String name, ImageIcon icon, String shortDescription)
		{
			super(name, icon);
			putValue(SHORT_DESCRIPTION, shortDescription);
		}

		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Toolbar -> Settings\n" + "\t" + this.getValue(SHORT_DESCRIPTION));
		}
	}

	//***********************************
	// Internal Action class for bold toolbar button
	//***********************************
	public static class BoldAction extends AbstractAction
	{
		public BoldAction(String name, ImageIcon icon, String shortDescription)
		{
			super(name, icon);
			putValue(SHORT_DESCRIPTION, shortDescription);
		}

		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Toolbar -> Bold\n" + "\t" + this.getValue(SHORT_DESCRIPTION));
		}
	}

	//***********************************
	// Internal Action class for search toolbar button
	//***********************************
	public static class RestoreAction extends AbstractAction
	{
		public RestoreAction(String name, ImageIcon icon, String shortDescription)
		{
			super(name, icon);
			putValue(SHORT_DESCRIPTION, shortDescription);
		}

		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Toolbar -> Restore\n" + "\t" + this.getValue(SHORT_DESCRIPTION));
		}
	}

	//***********************************
	// Internal Action class for quit action
	//***********************************
	public static class QuitAction extends AbstractAction
	{
		public QuitAction(String name, String shortDescription)
		{
			super(name);
			putValue(SHORT_DESCRIPTION, shortDescription);
		}

		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Quit pressed, running quit protocol...\n");
			quitProtocol();
		}
	}

	//***********************************
	// Perform necessary program quit procedures
	//***********************************
	public static void quitProtocol()
	{
		// Run through all menu items and perform their action
		String outputString = runAllMenuItems();

		// Repeat and print to file
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(frame);

		BufferedWriter bw = null;

		try {
			File file = new File(fc.getSelectedFile()+".txt");
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(outputString);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			// Reset System.out to stdout
			if (bw != null)
			{
				try {
					bw.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}

		System.exit(0);
	}

	//***********************************
	// Print all actionEvents for all menu items
	//***********************************
	private static String runAllMenuItems()
	{
		StringBuilder outputString = new StringBuilder();

		// File menu
		openItem.doClick();
		saveItem.doClick();
		printAllItem.doClick();
		printFridgeItem.doClick();
		printGroceriesItem.doClick();
		printRecipeItem.doClick();

		// Edit menu
		cutItem.doClick();
		copyItem.doClick();
		pasteItem.doClick();
		searchItem.doClick();
		restoreItem.doClick();
		quantityItem.doClick();
		expItem.doClick();
		favoriteItem.doClick();
		leftoverItem.doClick();

		// Window menu
		fontItem.doClick();
		fontColItem.doClick();
		primaryItem.doClick();
		secondaryItem.doClick();
		swapItem.doClick();
		importItem.doClick();
		saveThmItem.doClick();
		restorePointItem.doClick();
		customaryItem.doClick();
		metricItem.doClick();
		imperialItem.doClick();

		// Help menu
		linkItem.doClick();
		fontSizeItem.doClick();
		resolutionItem.doClick();
		invertItem.doClick();
		boldItem.doClick();
		feedbackItem.doClick();
		donateItem.doClick();

		outputString.append("Edit menu -> Search pressed\n");
		outputString.append(" ::: Edit menu -> Restore pressed\n");
		outputString.append(" ::: Window menu -> Theme menu -> Font pressed\n");
		outputString.append(" ::: Edit menu -> Theme menu -> Font Color pressed\n");
		outputString.append(" ::: Edit menu -> Settings menu -> Set Units menu -> Customary pressed\n");
		outputString.append(" ::: Edit menu -> Settings menu -> Set Units menu -> Metric pressed\n");
		outputString.append(" ::: Edit menu -> Settings menu -> Set Units menu -> Imperial pressed\n");
		outputString.append(" ::: Help menu -> Accessability menu -> Font size pressed\n");
		outputString.append(" ::: Help menu -> Accessability menu -> Invert pressed\n");
		outputString.append(" ::: Help menu -> Accessability menu -> Bold pressed\n");
		outputString.append(" ::: Help menu -> Feedback pressed\n");
		outputString.append(" ::: Help menu -> Donate pressed\n");

		return outputString.toString();
	}
}

//******************************************************************************
