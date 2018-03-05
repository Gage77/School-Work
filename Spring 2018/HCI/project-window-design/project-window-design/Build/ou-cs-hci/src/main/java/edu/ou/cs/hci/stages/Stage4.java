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
//
//******************************************************************************

package edu.ou.cs.hci.stages;

//import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import edu.ou.cs.hci.resources.Resources;

/**
 * The <CODE>BuildTest</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */

public final class Stage4
{
	//main
	public static void main(String[] args)
	{
		/****************************************
		* Create the main window of the program
		****************************************/
		//createMainWindow();

		/****************************************
		* Create the scenarios frame with relevant information.
		* Comment out the blelow line to not generate the Scenarios
		* frame.
		****************************************/
		//createScenarios();

		/****************************************
		* Create the survey frame with relevant questions.
		* Comment out the blelow line to not generate the survey
		* frame.
		****************************************/
		//createSurvey();

		/****************************************
		* Create the new, redesigned main window that takes follows
		* the guidelines established by our team. Comment out the line
		* below to not generate the redesigned main window
		****************************************/
		createRedesignedMainWindow();
	}

	/*************************************
	*	Create the main window of the program
	*************************************/
	public static void createMainWindow()
	{
		//creates the base JFrame on which everything will be displayed
		JFrame			frame = new JFrame("FridgTrackr");

		//creates the 3 category panels
		JPanel			recipes = new JPanel(new BorderLayout());
		JPanel			fridge = new JPanel(new BorderLayout());
		JPanel			groceries = new JPanel(new BorderLayout());

		//adds a button to each of the 3 category panels so new data can e added
		JButton			rAdd = new JButton("add");
		JButton			fAdd = new JButton("add");
		JButton			gAdd = new JButton("add");

		//adds a title to each category panel
		recipes.setBorder(BorderFactory.createTitledBorder("recipes"));
		fridge.setBorder(BorderFactory.createTitledBorder("fridge"));
		groceries.setBorder(BorderFactory.createTitledBorder("groceries"));

		//sets the defualt size of the main window
		frame.setBounds(50, 50, 600, 600);
		frame.getContentPane().setLayout(new BorderLayout());

		//creates the pane that will store the category tabs
		JTabbedPane tabs = new JTabbedPane();
		//sets icons for tabs
		Icon fridgeIcon = new ImageIcon(Stage4.class.getResource("refrigerator.png"));
		Icon recipesIcon = new ImageIcon(Stage4.class.getResource("contract.png"));
		Icon groceriesIcon = new ImageIcon(Stage4.class.getResource("groceries.png"));
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
		JPanel			filterPanel = new JPanel(new FlowLayout());
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
		//adds the filter panel to the base frame
		frame.getContentPane().add(filterPanel, BorderLayout.NORTH);

    	//adds the add buttons
    	fridge.add(fAdd, BorderLayout.PAGE_END);
    	recipes.add(rAdd, BorderLayout.PAGE_END);
    	groceries.add(gAdd, BorderLayout.PAGE_END);

		//sets the base frame to visible & to end on exit
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}

	/*************************************
	*	Create the updated main window that has been
	* redesigned to follow the guidelines created by
	* our group in the group part of project 5
	*************************************/
	public static void createRedesignedMainWindow()
	{
		// Create the main JFrame on which evrything will be placed
		JFrame frame = new JFrame("Fridg Trackr - Redesigned Main");
		//sets the defualt size of the main window
		frame.setBounds(50, 50, 600, 600);
		frame.getContentPane().setLayout(new BorderLayout());

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.out.println("Window closing");
			}
		});
	}


	/*************************************
	*	Create scenarios frame with relavent information
	* from /hci/resources/scenarios and
	* /hci/resources/personas
	*************************************/
	public static void createScenarios()
	{
		//scenarios and personas JFrame
		JFrame spFrame = new JFrame("Scenarios");

		//sets the defualt size of the main window
		spFrame.setBounds(700, 50, 600, 400);
		spFrame.getContentPane().setLayout(new BorderLayout());

		//get titles of scenarios from /resources/scenarios/titles.txt using
		//Resources.java
		ArrayList<String> sTitles = Resources.getLines("scenarios/titles.txt");
			//handle potential null error by displaying error message in JList
			if (sTitles.get(0) == null)
			{
				sTitles.set(0, "ERROR: No data found in provided path");
			}

		//get descriptions of scenarios from /resources/scenarios/descriptions.txt
		//using Resources.java
		ArrayList<String> sDescription = Resources.getLines("scenarios/descriptions.txt");
			//handle potential null error by displaying error message in JTextArea
			if (sDescription.get(0) == null)
			{
				sDescription.set(0, "ERROR: No data found in provided path");
			}

		//create and populate scenarios title jlist for right side of split pane
		//also ensure that JList is SINGLE_SELECTION
		JList<Scenario> scenarioTitle = new JList<Scenario>();
		DefaultListModel<Scenario> model = new DefaultListModel<>();
		scenarioTitle.setModel(model);
		scenarioTitle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//create non-editable text area for right side of split pane
		//also ensure that JTextArea is not editable
		JTextArea scenarioText = new JTextArea();
		scenarioText.setEditable(false);
		scenarioText.setLineWrap(true);
		scenarioText.setWrapStyleWord(true);

		//populate JList scenarioTitle with Scenario objects created from
		//ArrayList's sTitles and sDescription
		for (int i = 0; i < sTitles.size(); i++)
		{
			model.addElement(new Scenario(sTitles.get(i), sDescription.get(i)));
		}

		//add listener to update stuff and thangs in the JTextArea
		scenarioTitle.getSelectionModel().addListSelectionListener(e -> {
			scenarioText.setText(null);
			Scenario s = scenarioTitle.getSelectedValue();
			scenarioText.append("Description: \n" + s.getDescription());
		});

		// Select the first title by default
		scenarioTitle.setSelectedIndex(0);

		//create split pane
		JSplitPane scenarioPane =
			new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(scenarioTitle), scenarioText);

		//main panel
		JPanel scenariosPanel = new JPanel(new BorderLayout());
		scenariosPanel.add(scenarioPane, BorderLayout.CENTER);

		//add dat stuff to da frame
		spFrame.add(scenariosPanel, BorderLayout.CENTER);

		spFrame.setVisible(true);
		spFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/*************************************
	*	Private class to hold Scenario information
	* including the title and a relavent description
	*************************************/
	public static void createSurvey()
	{
		JFrame surveyFrame = new JFrame("Survey");

		//sets the defualt size of the main window
		surveyFrame.setBounds(700, 50, 700, 800);
		surveyFrame.getContentPane().setLayout(new GridLayout(6, 1));

		// Create panels for all 5 questions
		JPanel q1 = new JPanel(new GridLayout(1, 2));
		JPanel q2 = new JPanel(new GridLayout(2, 1));
		JPanel q3 = new JPanel(new GridLayout(5, 2));
		JPanel q4 = new JPanel(new GridLayout(1, 2));
		JPanel q5 = new JPanel(new GridLayout(0, 1));

		/************************************
		* Question 1 - Text field using JTextField
		************************************/
			// Setup text field for frustrations question
			JTextArea frustrationsField = new JTextArea();
			frustrationsField.setLineWrap(true);
			frustrationsField.setWrapStyleWord(true);
			JScrollPane frustrationScrollPane = new JScrollPane(frustrationsField);

			// Setup label for frustrations question
			JLabel frustrationsQuestion = new JLabel("<html>Please enter what frustrates you most<BR> about grocery shopping:</html>");

			q1.add(frustrationsQuestion);
			q1.add(frustrationScrollPane);
			q1.setBorder(BorderFactory.createLineBorder(Color.black));

		/************************************
		* Question 2 - Scale using JSlider
		************************************/
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
			sliderTable.put(new Integer(0), new JLabel("Not Useful at All"));
			sliderTable.put(new Integer(1), new JLabel("Kind of Useful"));
			sliderTable.put(new Integer(2), new JLabel("Neutral"));
			sliderTable.put(new Integer(3), new JLabel("Useful"));
			sliderTable.put(new Integer(4), new JLabel("Extremely Useful"));

			// Add labels to slider
			usefullnessSlider.setLabelTable(sliderTable);

			// Make the slider more useful by adding the following
			usefullnessSlider.setSnapToTicks(false);
			usefullnessSlider.setMajorTickSpacing(10);
			usefullnessSlider.setPaintLabels(true);

			// Setup the question
			String scaleQuestionText = "<html>How useful would an application that keeps "
				+ "track of the items in your fridge and pantry <BR> and allows you to set up reminders "
				+ "when you are running low on a specific item be to you?</html>";

			JLabel scaleQuestion = new JLabel(scaleQuestionText);

			// Add the question to the frame
			q2.add(scaleQuestion);
			q2.add(usefullnessSlider);
			q2.setBorder(BorderFactory.createLineBorder(Color.black));

		/************************************
		* Question 3 - Non-mutually exclusive response using JCheckBoxes
		************************************/
			// Setup question label
			JLabel nonMutualQuestion = new JLabel("<html>Of the stores listed below, select those<BR> that you visit relatively frequently:</html>");
			JLabel emptyLabel = new JLabel("");

			// Setup checkboxes for responses
			JCheckBox box1 = new JCheckBox("Aldi");
			JCheckBox box2 = new JCheckBox("Costco");
			JCheckBox box3 = new JCheckBox("H-E-B");
			JCheckBox box4 = new JCheckBox("Kroger");
			JCheckBox box5 = new JCheckBox("Sam's Club");
			JCheckBox box6 = new JCheckBox("Sprouts");
			JCheckBox box7 = new JCheckBox("Target");
			JCheckBox box8 = new JCheckBox("Trader Joes");
			JCheckBox box9 = new JCheckBox("Walmart");
			JCheckBox box10 = new JCheckBox("Whole Foods");

			// Add question label at top
			q3.add(nonMutualQuestion);
			q3.add(emptyLabel);
			// Add checkboxes below
			q3.add(box1);
			q3.add(box2);
			q3.add(box3);
			q3.add(box4);
			q3.add(box5);
			q3.add(box6);
			q3.add(box7);
			q3.add(box8);
			q3.add(box9);
			q3.add(box10);

			// Add border to panel
			q3.setBorder(BorderFactory.createLineBorder(Color.black));

		/************************************
		* Question 4 - Small integer using JSpinner
		************************************/
			// Setup label for small interger question
			JLabel smallIntQuestion = new JLabel("<html>Please give a rough estimate of how many<BR>items on average you keep in your<BR>fridge and pantry at any given time:</html>");

			// Setup spinner model and spinner
			SpinnerNumberModel bagModel = new SpinnerNumberModel(0/*default*/, 0/*min*/, 100/*max*/, 5/*incr.*/);
			JSpinner smallIntSpinner = new JSpinner(bagModel);

			// Add question label and spinner to panel
			q4.add(smallIntQuestion);
			q4.add(smallIntSpinner);

			// Add border to panel
			q4.setBorder(BorderFactory.createLineBorder(Color.black));

		/************************************
		* Question 5 - Range using JRadioButtons
		************************************/
			// Setup label for range question
			JLabel rangeQuestion = new JLabel("On average, how much time do you spend using a single app on your phone at a time?");

			// Setup radio buttons with responses
			JRadioButton range1 = new JRadioButton("< 0:30");
			JRadioButton range2 = new JRadioButton("0:30 - 1:00");
			JRadioButton range3 = new JRadioButton("1:00 - 1:30");
			JRadioButton range4 = new JRadioButton("1:30 - 2:00");
			JRadioButton range5 = new JRadioButton("2:00 - 2:30");
			JRadioButton range6 = new JRadioButton("2:30 - 3:00");
			JRadioButton range7 = new JRadioButton("> 3:00");

			// Create button group for JRadioButtons
			ButtonGroup rangeButtons = new ButtonGroup();
			rangeButtons.add(range1);
			rangeButtons.add(range2);
			rangeButtons.add(range3);
			rangeButtons.add(range4);
			rangeButtons.add(range5);
			rangeButtons.add(range6);
			rangeButtons.add(range7);

			// Add question label to panel
			q5.add(rangeQuestion);
			// Add buttons to question panel
			q5.add(range1);
			q5.add(range2);
			q5.add(range3);
			q5.add(range4);
			q5.add(range5);
			q5.add(range6);
			q5.add(range7);

			// Add border to panel
			q5.setBorder(BorderFactory.createLineBorder(Color.black));

		/************************************
		* Finish button
		************************************/
		JButton finish = new JButton("Finish");

		ActionListener finishListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				// Range question check
				boolean rangeIsClicked = false;
				String rangeAnswer = "";
				if (range1.isSelected())
				{
					rangeAnswer = range1.getText();
					rangeIsClicked = true;
				}
				else if (range2.isSelected())
				{
					rangeAnswer = range2.getText();
					rangeIsClicked = true;
				}
				else if (range3.isSelected())
				{
					rangeAnswer = range3.getText();
					rangeIsClicked = true;
				}
				else if (range4.isSelected())
				{
					rangeAnswer = range4.getText();
					rangeIsClicked = true;
				}
				else if (range5.isSelected())
				{
					rangeAnswer = range5.getText();
					rangeIsClicked = true;
				}
				else if (range6.isSelected())
				{
					rangeAnswer = range6.getText();
					rangeIsClicked = true;
				}
				else if (range7.isSelected())
				{
					rangeAnswer = range7.getText();
					rangeIsClicked = true;
				}

				// Non-mutually exlusive check
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
				if (box4.isSelected())
				{
					selectAnswer += box4.getText() + "\n";
					selectIsClicked = true;
				}
				if (box5.isSelected())
				{
					selectAnswer += box5.getText() + "\n";
					selectIsClicked = true;
				}
				if (box6.isSelected())
				{
					selectAnswer += box6.getText() + "\n";
					selectIsClicked = true;
				}
				if (box7.isSelected())
				{
					selectAnswer += box7.getText() + "\n";
					selectIsClicked = true;
				}
				if (box8.isSelected())
				{
					selectAnswer += box8.getText() + "\n";
					selectIsClicked = true;
				}
				if (box9.isSelected())
				{
					selectAnswer += box9.getText() + "\n";
					selectIsClicked = true;
				}
				if (box10.isSelected())
				{
					selectAnswer += box10.getText() + "\n";
					selectIsClicked = true;
				}

				// Get current value of spinner question
				int spinnerVal = (Integer) smallIntSpinner.getValue();

				// Get current slider value from slider question
				int sliderVal = usefullnessSlider.getValue();
				String sliderString = sliderTable.get(sliderVal).getText();

				/************************************
				* Final Write
				************************************/
				if (selectIsClicked && rangeIsClicked && !(frustrationsField.getText().isEmpty()))
				{
					// Write to txt file
					BufferedWriter output = null;
					try {
						File file = new File("survey_results.txt");
						output = new BufferedWriter(new FileWriter(file));

						// Write text field answer
						output.write(frustrationsQuestion.getText() + "\n");
						output.write(frustrationsField.getText() + "\n\n");

						// Write slider answer
						output.write("How useful would an application that keeps "
				        		+ "track of the items in your fridge and pantry and allows "
				        		+ "you to set up reminders when you are running low on a "
				        		+ "specific item be to you?\n");
				    output.write(sliderString + "\n\n");

						// Write checkbox answer
						output.write(nonMutualQuestion.getText() + "\n");
						output.write(selectAnswer + "\n\n");

						// Write spinner answer
						output.write(smallIntQuestion.getText() + "\n");
						output.write(spinnerVal + "\n\n");

						// Write radio button answer
						output.write(rangeQuestion.getText() + "\n");
						output.write(rangeAnswer + "\n\n");

					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						if (output != null)
						{
							try {
								output.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}

					//Print answers to console
					System.out.println(frustrationsQuestion.getText() + "\n");
					System.out.println(frustrationsField.getText() + "\n" + "========================" + "\n");

					System.out.println("How useful would an application that keeps "
									+ "track of the items in your fridge and pantry and allows "
									+ "you to set up reminders when you are running low on a "
									+ "specific item be to you?\n");
					System.out.println(sliderString + "\n" + "========================" + "\n");

					System.out.println(nonMutualQuestion.getText() + "\n");
					System.out.println(selectAnswer + "\n" + "========================" + "\n");

					System.out.println(smallIntQuestion.getText() + "\n");
					System.out.println(spinnerVal + "\n" + "========================" + "\n");

					System.out.println(rangeQuestion.getText() + "\n");
					System.out.println(rangeAnswer + "\n" + "========================" + "\n");

					// Close surveyFrame
					surveyFrame.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(surveyFrame, "Please answer all questions.");
				}
			}
		};		finish.addActionListener(finishListener);

		/************************************
		* Add all JPanels to the survey frame
		************************************/
			surveyFrame.add(q1);
			surveyFrame.add(q2);
			surveyFrame.add(q3);
			surveyFrame.add(q4);
			surveyFrame.add(q5);
			surveyFrame.add(finish);

			surveyFrame.setVisible(true);
			surveyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/*************************************
	*	Private class to hold Scenario information
	* including the title and a relavent description
	*************************************/
	private static class Scenario
	{
		String title;	// Title of scenario
		String description;	// Description of scenario

		// Base constructor
		public Scenario(String t, String d)
		{
			title = t;
			description = d;
		}

		// Getters and setters
		public String getTitle()
		{
			return title;
		}

		public String getDescription()
		{
			return description;
		}

		public void setTitle(String t)
		{
			title = t;
		}

		public void setDescription(String d)
		{
			description = d;
		}

		// Override toString method to just show the scenario title
		@Override
		public String toString()
		{
			return title;
		}
	}

}

//******************************************************************************
