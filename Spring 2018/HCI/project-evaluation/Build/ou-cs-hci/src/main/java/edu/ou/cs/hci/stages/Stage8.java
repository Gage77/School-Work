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
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JOptionPane;

import org.apache.commons.csv.*;	// for reading in CSVs

import edu.ou.cs.hci.resources.*;

//******************************************************************************

/**
 * The <CODE>Stage8</CODE> class.<P>
 *
 * @author  Chris Weaver & Group 6
 * @version %I%, %G%
 */
public final class Stage8
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	// Save changes flag
	public static boolean shouldSaveOnExit = false;

	//**********************************************************************
	// Private Class Members
	//**********************************************************************

	// Collection of Food, GroceryItem, and Recipe objects
	private static ArrayList<Food> foodCollection = new ArrayList<Food>();
	private static ArrayList<GroceryItem> groceryCollection = new ArrayList<GroceryItem>();
	private static ArrayList<Recipe> recipeCollection = new ArrayList<Recipe>();

	// Unicode character representation for stars and back arrow
	private static final String blackStarUnicode = "\u2605";
	private static final String whiteStarUnicode = "\u2606";
	private static final String backArrow = "\u2190";

	// Top panel items
	private static JPanel top;
	private static JLabel welcome;
	private static Icon backIcon;
	private static JButton back;
	private static Icon addIcon;
	private static JButton add;
	private static ActionListener backListener;
	private static ActionListener addListener;

	// Fridge tab items
	private static JPanel fridge;
	private static Icon fridgeIcon;
	private static String[] fridgeHeaders = new String[] {
		"*",
		"Name",
		"Amount",
		"Days Left",
		"Leftover"
	};
	private static JTable fridgeTable;
	private static MyRenderer renderer;

	// Recipe tab items
	private static JPanel recipes;
	private static Icon recipesIcon;
	private static String[] recipesTableHeaders = new String[] {
		"Name",
		"Delete"
	};
	private static JTable recipesTable;

	// Grocery tab items
	private static JPanel groceries;
	private static Icon groceriesIcon;
	private static String[] groceriesTableHeaders = new String[] {
		"Name",
		"Amount",
		"Delete"
	};
	private static JTable groceriesTable;

	// Tab stuff
	private static JTabbedPane tabs;

	// Filtering stuff
	private static JPanel mid;
	private static JPanel filterPanel;
	private static JCheckBox favoritesBox;
	private static JCheckBox expiredBox;
	private static JLabel expiredLabel;
	private static JCheckBox lowBox;
	private static JLabel lowLabel;
	private static JCheckBox leftoversBox;
	private static JLabel leftoversLabel;

	// Menubar items
	private static JMenuBar menuBar;
	private static JMenuItem openItem;
	private static JMenuItem saveItem;
	private static JMenuItem printItem;
	private static JMenuItem printAllItem;
	private static JMenuItem printFridgeItem;
	private static JMenuItem printRecipesItem;
	private static JMenuItem printGroceriesItem;
	private static JMenu printSubmenu;
	private static JMenuItem quitItem;
	private static JMenu fileMenu;
	private static JMenuItem copy;
	private static JMenuItem cut;
	private static JMenuItem paste;
	private static JMenuItem search;
	private static JMenuItem restore;
	private static JMenuItem favorites;
	private static JMenuItem expiration;
	private static JMenuItem lowStock;
	private static JMenuItem leftovers;
	private static JMenu filterBySubmenu;
	private static JMenu editMenu;
	private static JMenuItem resolution;
	private static JMenu settings;
	private static JMenuItem restorePt;
	private static JMenu units;
	private static JMenuItem metric;
	private static JMenuItem imperial;
	private static JMenuItem customary;
	private static JMenu windowMenu;
	private static JMenu help;
	private static JMenuItem link;
	private static JMenu accessibility;
	private static JMenuItem fontSize;
	private static JMenuItem invert;
	private static JMenuItem bold;
	private static JMenuItem donate;
	private static JMenuItem feedback;

	// Toolbar items
	private static JToolBar toolBar;
	private static JButton searchTool;
	private static JButton filterTool;
	private static JButton restoreTool;
	private static JButton settingsTool;
	private static JButton boldTool;

	// CSV stuff and thangs
	private static String[] COLUMNS = {
		"ID",
		"Favorite",
		"Name",
		"Amount",
		"ExpDate",
		"Leftover"
	};

	// Current time
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static LocalDateTime timeNow = LocalDateTime.now();

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		System.out.println(timeNow);
		//MAIN WINDOW creates the base JFrame on which everything will be displayed
		JFrame			frame = new JFrame("FridgTrackr");

		createTopPanel(frame);

		//creates the 3 category panels
		recipes = new JPanel(new BorderLayout());
		fridge = new JPanel(new BorderLayout());
		groceries = new JPanel(new BorderLayout());

		//adds a title to each category panel
		recipes.setBorder(BorderFactory.createTitledBorder("Recipes"));
		fridge.setBorder(BorderFactory.createTitledBorder("Fridge"));
		groceries.setBorder(BorderFactory.createTitledBorder("Groceries"));

		//sets the defualt size of the main window
		frame.setBounds(30, 30, 700, 700);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(top, BorderLayout.PAGE_START);

		//creates the pane that will store the category tabs
		tabs = new JTabbedPane();
		// Could use Weaver's getImage() for this now but this code works
		fridgeIcon = Resources.getImage("icons/refrigerator.png");
		recipesIcon = Resources.getImage("icons/contract.png");
		groceriesIcon = Resources.getImage("icons/groceries.png");
		//adds tabs to JTabbedPane
		tabs.addTab("Fridge", fridgeIcon, fridge);
		tabs.addTab("Recipes", recipesIcon, recipes);
		tabs.addTab("Groceries", groceriesIcon, groceries);
		//adds the JTabbedPane to the base pane
		frame.getContentPane().add(tabs, BorderLayout.CENTER);

		// Create empty table for fridge items
		Object[][] options = {
			{whiteStarUnicode, "bread", "7 (slices)", "4", ""}
		};
		fridgeTable = new JTable(options, fridgeHeaders);
		renderer = new MyRenderer();
		fridgeTable.setDefaultRenderer(Object.class, renderer);
		fridgeTable.getTableHeader().setReorderingAllowed(false);
		fridgeTable.setSelectionBackground(Color.decode("#ffcc00"));
		fridgeTable.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		fridgeTable.getTableHeader().setFont(new Font("Lucida Console", Font.PLAIN, 13));
		fridgeTable.getSelectionModel().addListSelectionListener(new FTListSelectionListener());
		fridge.add(new JScrollPane(fridgeTable), BorderLayout.CENTER);


		// Create empty table to grocery items
		groceriesTable = new JTable();
		groceriesTable.getTableHeader().setReorderingAllowed(false);
		groceriesTable.getSelectionModel().addListSelectionListener(new FTListSelectionListener());
		groceries.add(new JScrollPane(groceriesTable), BorderLayout.CENTER);

		// Create empty table for recipe items
		recipesTable = new JTable();
		recipesTable.getTableHeader().setReorderingAllowed(false);
		recipesTable.getSelectionModel().addListSelectionListener(new FTListSelectionListener());
		recipes.add(new JScrollPane(recipesTable), BorderLayout.CENTER);

		// Generate fridge tab filter checkbox panel
		createFridgeFilterPanel(frame);

		// Generate the menubar
		createMenuBar(frame);

		// Generate the toolbar
		createToolbar(frame);

		// Quit action listener to ask for saving
		quitItem.addActionListener(new AbstractAction()
		{
		    public void actionPerformed(ActionEvent a)
		    {
			    System.out.println("quit");
		    }
		});

		//sets the base frame to visible & to end on exit
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			});
	}

	//**********************************************************************
	// Public Class Methods
	//**********************************************************************

	//**********************************************************************
	// Private Class Methods
	//**********************************************************************

	// Create fridge tab filter checkbox panel
	private static void createFridgeFilterPanel(JFrame frame) {
		mid = new JPanel(new BorderLayout());

		filterPanel = new JPanel(new GridLayout(1, 8));
		filterPanel.setBorder(new EmptyBorder(0, 50, 0, 50));

		favoritesBox = new JCheckBox();
		favoritesBox.setText(blackStarUnicode);
		favoritesBox.setHorizontalAlignment(JCheckBox.LEFT);

		expiredBox = new JCheckBox();
		expiredBox.setHorizontalAlignment(JCheckBox.RIGHT);
		expiredLabel = new JLabel("Expired");

		lowBox = new JCheckBox();
		lowBox.setHorizontalAlignment(JCheckBox.RIGHT);
		lowLabel = new JLabel("Low Stock");

		leftoversBox = new JCheckBox();
		leftoversBox.setHorizontalAlignment(JCheckBox.RIGHT);
		leftoversLabel = new JLabel("Leftovers");

		filterPanel.add(favoritesBox);
		filterPanel.add(lowBox);
		filterPanel.add(lowLabel);
		filterPanel.add(expiredBox);
		filterPanel.add(expiredLabel);
		filterPanel.add(leftoversBox);
		filterPanel.add(leftoversLabel);

		mid.add(filterPanel, BorderLayout.CENTER);
		fridge.add(mid, BorderLayout.NORTH);
	}

	// Create the top panel
	private static void createTopPanel(JFrame frame) {
		top = new JPanel(new BorderLayout());

		// Setup the welcome label with text and logo
		welcome = new JLabel("Welcome to FridgTrackr!");
		welcome.setIcon(Resources.getImage("icons/FridgTrackr_Logo.png"));
		welcome.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		welcome.setBorder(new EmptyBorder(5, 95, 5, 80));

		// Create the back button
		backIcon = Resources.getImage("icons/back.png");
		back = new JButton();
		back.setIcon(backIcon);
		back.setPreferredSize(new Dimension(100, 50));
		back.setFont(new Font("Arial", Font.PLAIN, 25));

		// Create the add button
		addIcon = Resources.getImage("icons/add.png");
		add = new JButton();
		add.setIcon(addIcon);
		add.setFont(new Font("Arial", Font.PLAIN, 25));
		add.setPreferredSize(new Dimension(100, 50));

		// Add everything to the top panel
		top.add(back, BorderLayout.LINE_START);
		top.add(welcome, BorderLayout.CENTER);
		top.add(add, BorderLayout.LINE_END);

		// Add listener to the back button
		backListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("back button pressed. value: N/A");
			}
		};     back.addActionListener(backListener);

		// Add listener to the add button
		addListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("add item button pressed. value: N/A");
			}
		};     add.addActionListener(addListener);
	}

	// Create the menu bar for the application
	private static void createMenuBar(JFrame frame) {
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		//******************************************
		// File menu
		//******************************************

		openItem = new JMenuItem(new AbstractAction("Open (CTRL + O)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Open (CTRL + O). Opens a FridgTrackr file.");
						openCSV();
						for (Food item: foodCollection) {
							System.out.println(item.getName());
						}
		    }
		});

		saveItem = new JMenuItem(new AbstractAction("Save (CTRL + S)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Save (CTRL + S). Saves the current FridgTrackr file.");
						saveToCSV();
				}
		});

		printAllItem = new JMenuItem(new AbstractAction("All (CTRL + P)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Print -> All (CTRL + P). Prints the full FridgTrackr file.");
		    }
		});

		printFridgeItem = new JMenuItem(new AbstractAction("Fridge Stock")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Print -> Fridge Stock. Prints the current fridge stock.");
		    }
		});

		printRecipesItem = new JMenuItem(new AbstractAction("Recipes")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Print -> Recipes. Prints the currently stored recipes.");
		    }
		});

		printGroceriesItem = new JMenuItem(new AbstractAction("Groceries")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Print -> Groceries. Prints the current grocery list.");
		    }
		});

		printSubmenu = new JMenu("Print");
		quitItem = new JMenuItem(new AbstractAction("Quit	(CTRL + Q)")
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Quit pressed");
				if (shouldSaveOnExit) {
					Object[] options = {
						"Quit without saving",
						"Cancel",
						"Save before quitting"
					};

					int response = JOptionPane.showOptionDialog(
						frame,
						"Changes have been made since the last save.",
						"Save changes?",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[2]
					);

					if (response == JOptionPane.YES_OPTION) {	// Quit
						System.exit(0);
					}
					else if (response == JOptionPane.CANCEL_OPTION) {	// save to csv
						saveToCSV();
						System.exit(0);
					}
				}
				else {
					System.exit(0);
				}
			}
		});
		printSubmenu.add(printAllItem);
		printSubmenu.add(printFridgeItem);
		printSubmenu.add(printRecipesItem);
		printSubmenu.add(printGroceriesItem);
		fileMenu = new JMenu("File");
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(printSubmenu);
		fileMenu.add(quitItem);
		menuBar.add(fileMenu);

		//******************************************
		// Edit menu
		//******************************************

		copy = new JMenuItem(new AbstractAction("Copy (CTRL + C)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Copy (CTRL + C). Copies the selection.");
		    }
		});

		cut = new JMenuItem(new AbstractAction("Cut (CTRL + X)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Cut (CTRL + X). Cuts the selection.");
		    }
		});

		paste = new JMenuItem(new AbstractAction("Paste (CTRL + V)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Paste (CTRL + V). Pastes the selection.");
		    }
		});

		search = new JMenuItem(new AbstractAction("Search (CTRL + F)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Search (CTRL + F). Allows the user to search for a string.");
		    }
		});

		restore = new JMenuItem(new AbstractAction("Restore (CTRL + Z)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Restore (CTRL + Z). Restores the last deleted item to a limit.");
		    }
		});

		favorites = new JMenuItem(new AbstractAction("Favorites")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Filter By: --> Favorites. Filters by favorited items.");
		    }
		});

		expiration = new JMenuItem(new AbstractAction("Expired")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Filter By: --> Expired. Filters by expired items.");
		    }
		});

		lowStock = new JMenuItem(new AbstractAction("Low Stock")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Filter By: --> Low Stock. Filters by low stock.");
		    }
		});

		leftovers = new JMenuItem(new AbstractAction("Leftovers")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Filter By: --> Leftovers. Filters by items marked as leftovers.");
		    }
		});

		filterBySubmenu = new JMenu("Filter By:");
		filterBySubmenu.add(favorites);
		filterBySubmenu.add(expiration);
		filterBySubmenu.add(lowStock);
		filterBySubmenu.add(leftovers);
		editMenu = new JMenu("Edit");
		editMenu.add(copy);
		editMenu.add(cut);
		editMenu.add(paste);
		editMenu.addSeparator();
		editMenu.add(search);
		editMenu.add(restore);
		editMenu.add(filterBySubmenu);
		menuBar.add(editMenu);

		//******************************************
		// Window menu
		//******************************************

		resolution = new JMenuItem(new AbstractAction("Resolution")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Accessibility -> Resolution. Allows the user to adjust the resolution.");
		    }
		});

		settings = new JMenu("Settings");
		restorePt = new JMenuItem(new AbstractAction("Set Restore Point")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Settings -> Set Restore Point. "
		        		+ "Allows the user to set the amount of restores to keep.");
		    }
		});

		units = new JMenu("Set units");
		metric = new JMenuItem(new AbstractAction("Metric")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Settings -> Set Units -> Metric. "
		        		+ "Allows the user to set the units of measurement to the metric system.");
		    }
		});

		imperial = new JMenuItem(new AbstractAction("Imperial")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Settings -> Set Units -> Imperial. "
		        		+ "Allows the user to set the units of measurement to the imperial system.");
		    }
		});

		customary = new JMenuItem(new AbstractAction("Customary")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Settings -> Set Units -> Customary. "
		        		+ "Allows the user to set the units of measurement to the customary system.");
		    }
		});

		units.add(metric);
		units.add(imperial);
		units.add(customary);
		settings.add(units);
		settings.add(restorePt);
		windowMenu = new JMenu("Window");
		windowMenu.add(resolution);
		windowMenu.add(settings);
		menuBar.add(windowMenu);

		//******************************************
		// Help menu
		//******************************************

		help = new JMenu("Help");
		link = new JMenuItem(new AbstractAction("Link (CTRL + ?)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Link. Opens a related webpage: http://www.myfridgefood.com/");
		    }
		});

		accessibility = new JMenu("Accessibility");
		fontSize = new JMenuItem(new AbstractAction("Font Size")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Accessibility -> Font Size. Allows the user to edit the font size.");
		    }
		});

		invert = new JMenuItem(new AbstractAction("Invert")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Accessibility -> Invert. Inverts the colours being displayed.");
		    }
		});

		bold = new JMenuItem(new AbstractAction("Bold")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Accessibility -> Bold. Bolds all text.");
		    }
		});

		feedback = new JMenuItem(new AbstractAction("Feedback (CTRL + H)")
		{
				public void actionPerformed(ActionEvent a)
				{
						System.out.println("Help -> Feedback (CTRL + H). Opens a window to allow the user provide feedback.");
				}
		});

		donate = new JMenuItem(new AbstractAction("Donate (CTRL + $)")
		{
				public void actionPerformed(ActionEvent a)
				{
						System.out.println("Help -> Donate (CTRL + $). Allows the user to donate.");
				}
		});

		// Adds funtionality of opening about page in JEditorPane
		JMenuItem about = new JMenuItem(new AbstractAction("About")
		{
			public void actionPerformed(ActionEvent a)
			{
				System.out.println("Help -> About pressed");
				JFrame aboutFrame = new JFrame("About FridgTrackr");
				aboutFrame.setBounds(50, 50, 600, 600);
				aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				AboutPanel aboutPanel = new AboutPanel();
				aboutFrame.getContentPane().add(aboutPanel, BorderLayout.CENTER);
				aboutFrame.setVisible(true);
			}
		});

		accessibility.add(fontSize);
		accessibility.add(invert);
		accessibility.add(bold);
		help.add(accessibility);
		help.add(feedback);
		help.add(donate);
		help.add(about);
		menuBar.add(help);
	}

	// Create the toolbar for the application
	private static void createToolbar(JFrame frame) {
		toolBar = new JToolBar("Tool Bar");

		// Search button
		searchTool = new JButton(new AbstractAction("Search")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("ToolBar --> Search (CTRL + F). Allows the user to search for an item in the database.");
		    }
		});
		searchTool.setIcon(Resources.getImage("icons/search.png"));
		searchTool.setFont(new Font("Arial", Font.PLAIN, 15));
		searchTool.setVerticalTextPosition(SwingConstants.TOP);
		searchTool.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(searchTool);

		// Filter button
		filterTool = new JButton(new AbstractAction("Filter")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("ToolBar --> Filter. Allows the user to choose criteria to filter by.");
		    }
		});
		filterTool.setIcon(Resources.getImage("icons/filter.png"));
		filterTool.setFont(new Font("Arial", Font.PLAIN, 15));
		filterTool.setVerticalTextPosition(SwingConstants.TOP);
		filterTool.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(filterTool);
		toolBar.addSeparator();

		// Restore button
		restoreTool = new JButton(new AbstractAction("Restore")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("ToolBar --> Restore (CTRL + Z). Restores the most recently deleted item.");
		    }
		});
		restoreTool.setIcon(Resources.getImage("icons/restore.png"));
		restoreTool.setFont(new Font("Arial", Font.PLAIN, 15));
		restoreTool.setVerticalTextPosition(SwingConstants.TOP);
		restoreTool.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(restoreTool);

		// Settings button
		settingsTool = new JButton(new AbstractAction("Settings")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("ToolBar --> Settings. Opens the settings window for the application.");
		    }
		});
		settingsTool.setIcon(Resources.getImage("icons/settings.png"));
		settingsTool.setFont(new Font("Arial", Font.PLAIN, 15));
		settingsTool.setVerticalTextPosition(SwingConstants.TOP);
		settingsTool.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(settingsTool);

		// Bold button
		boldTool = new JButton(new AbstractAction("Bold")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("ToolBar --> Bold. Bolds all text.");
		    }
		});
		boldTool.setIcon(Resources.getImage("icons/bold.png"));
		boldTool.setFont(new Font("Arial", Font.PLAIN, 15));
		boldTool.setVerticalTextPosition(SwingConstants.TOP);
		boldTool.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(boldTool);
		toolBar.setBorder(new EmptyBorder(0, 100, 10, 100));

		// Add everything to the frame
		frame.getContentPane().add(toolBar, BorderLayout.PAGE_END);
	}

	// Read in a CSV data file
	private static void openCSV() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Select FridgTrackr Collection File (CSV)");
		int fcReturn = fc.showOpenDialog(null);

		if (fcReturn == JFileChooser.APPROVE_OPTION) {
			try {
				System.out.println("File chosen ---> " + fc.getSelectedFile().getAbsolutePath());
				File file = fc.getSelectedFile();
				URL fileURL = file.toURI().toURL();
				InputStream is = fileURL.openStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);

				// Apache Commons CSV library for reading CSV
				CSVFormat format = CSVFormat.DEFAULT;
				CSVParser parser = CSVParser.parse(br, format);
				java.util.List<CSVRecord> records = parser.getRecords();

				// 2D array to keep rows and columns of csv
				String[][] values = new String[records.size()][COLUMNS.length];

				// Loop over all rows
				for (CSVRecord record : records) {
					Iterator<String> k = record.iterator();
					int i = (int)record.getRecordNumber() - 1;
					int j = 0;	// column number

					// Print each record to the console
					System.out.println("------- #" + i + " -------");

					// Loop over columns and populate the values matrix
					while (k.hasNext()) {
						values[i][j] = k.next(); // Grab each cell's values

						// Print each value to the console
						System.out.println(COLUMNS[j] + " = " + values[i][j]);
						j++;
					}
					System.out.println();
				}

				// Clear whatever is currently in the collection arraylists
				foodCollection.clear();
				groceryCollection.clear();
				recipeCollection.clear();

				// Populate the collections
				for (int i = 0; i < records.size(); i++) {
					// Grab first value of each row to determine what kind of item is
					// currently being read
					int firstValue = 0;
					try {
						firstValue = Integer.parseInt(values[i][0]);
					} catch (NumberFormatException ex) {
						ex.printStackTrace();
					}

					//Switch statement to create currently read in object
					switch (firstValue) {
						case 0:	// Food item
							System.out.println("Food item");
							createFoodItem(values, i);
							break;
						case 1:	// Grocery item
							System.out.println("Grocery item");
							createGroceryItem(values, i);
							break;
						case 2:	// Recipe item
						System.out.println("Recipe");
						createRecipe(values, i);
							break;
						default:	// Default to food item to fix first item in CSV ID error
							System.out.println("Probably a food item");
							createFoodItem(values, i);
							break;
					}
				}
				is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// populate all 3 jtables
			populateFridgeTable();
			populateGroceryTable();
			populateRecipeTable();
		}
	}

	// Save to CSV
	private static void saveToCSV() {
		JFileChooser fc = new JFileChooser();
		int status = fc.showSaveDialog(fc);

		if (status == JFileChooser.APPROVE_OPTION) {
			try
			{
				if (fc.getSelectedFile().exists()) {
					JOptionPane.showMessageDialog(new JFrame(), "That file already exists!", "File already exists", JOptionPane.WARNING_MESSAGE);
				}
				else {
					PrintWriter pw = new PrintWriter(fc.getSelectedFile()+".csv");

					// Write food items
					for (int i = 0; i < foodCollection.size(); i++) {
						StringBuilder sb = new StringBuilder();
						sb.append("0");
						sb.append(',');
						if (foodCollection.get(i).isFavorite()) {
							sb.append("1");
						}
						else {
							sb.append("0");
						}
						sb.append(',');
						sb.append(foodCollection.get(i).getName());
						sb.append(',');
						sb.append(foodCollection.get(i).getAmount());
						sb.append(',');
						sb.append(foodCollection.get(i).getExpDate());
						sb.append(',');
						if (foodCollection.get(i).isLeftover()) {
							sb.append("1");
						}
						else {
							sb.append("0");
						}
						sb.append('\n');

						pw.write(sb.toString());
					}

					// Write grocery items
					for (int j = 0; j < groceryCollection.size(); j++) {
						StringBuilder sb = new StringBuilder();
						sb.append("1");
						sb.append(',');
						sb.append(groceryCollection.get(j).getName());
						sb.append(',');
						sb.append(groceryCollection.get(j).getAmount());
						sb.append(',');
						sb.append("");
						sb.append(',');
						sb.append("");
						sb.append(',');
						sb.append("");
						sb.append('\n');

						pw.write(sb.toString());
					}

					// Write recipes
					for (int k = 0; k < recipeCollection.size(); k++) {
						StringBuilder sb = new StringBuilder();
						sb.append("2");
						sb.append(',');
						sb.append(recipeCollection.get(k).getName());
						sb.append(',');
						sb.append(recipeCollection.get(k).getDescriptionPath());
						sb.append(',');
						for (int p = 0; p < recipeCollection.get(k).getIngredients().length; p++) {
							sb.append(recipeCollection.get(k).getIngredients()[p]);
							sb.append("./");
						}
						sb.append(',');
						sb.append("");
						sb.append(',');
						sb.append("");
						sb.append("\n");

						pw.write(sb.toString());
					}
					pw.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		shouldSaveOnExit = false;	// reset save flag
	}

	// ID 0 or default was read, create food item
	private static void createFoodItem(String[][] values, int rowIndex) {
		// Get all necessary constructor values
		boolean isFavorite = false;
		if (Integer.parseInt(values[rowIndex][1]) == 1) {
			isFavorite = true;
		}
		String name = values[rowIndex][2];
		String amount = values[rowIndex][3];
		String expDate = values[rowIndex][4];
		boolean isLeftover = false;
		if (Integer.parseInt(values[rowIndex][5]) == 1) {
			isLeftover = true;
		}

		// Create new food item, then add the food item to the food collection
		Food food = new Food(name, amount, expDate, isFavorite, isLeftover);
		foodCollection.add(food);
	}

	// ID 1 was read, create grocery item
	private static void createGroceryItem(String[][] values, int rowIndex) {
		// Get all necessary constructor values
		String name = values[rowIndex][1];
		String amount = values[rowIndex][2];

		// Create grocery item, then add grocery item to grocery collection
		GroceryItem groceryItem = new GroceryItem(name, amount);
		groceryCollection.add(groceryItem);
	}

	// ID 2 was read, create recipe item
	private static void createRecipe(String[][] values, int rowIndex) {
		// Get all necessary constructor values
		String name = values[rowIndex][1];
		String descriptionPath = values[rowIndex][2];
		String ingredientsListUnsplit = values[rowIndex][3];

		// Parse ingredients string into ingredients array
		String[] ingredients = ingredientsListUnsplit.split("./");

		Recipe recipe = new Recipe(name, descriptionPath, ingredients);
		recipeCollection.add(recipe);
	}

	// Populate the fridge table with read in fridge collection values
	private static void populateFridgeTable() {
		System.out.println("pop fridge entered");
		Object[][] fridgeOptions = new Object[foodCollection.size()][5];

		for (int i = 0; i < foodCollection.size(); i++) {
			if (foodCollection.get(i).isFavorite()) {
				fridgeOptions[i][0] = blackStarUnicode;
			}
			else {
				fridgeOptions[i][0] = whiteStarUnicode;
			}
			fridgeOptions[i][1] = foodCollection.get(i).getName();
			fridgeOptions[i][2] = foodCollection.get(i).getAmount();
			fridgeOptions[i][3] = foodCollection.get(i).getExpDate();

			if (foodCollection.get(i).isLeftover()) {
				fridgeOptions[i][4] = "Yes";
			}
			else {
				fridgeOptions[i][4] = "";
			}
		}

		fridgeTable.setModel(new DefaultTableModel(fridgeOptions, fridgeHeaders));
	}

	// Populate the grocery table with read in grocery collection values
	private static void populateGroceryTable() {

	}

	// Populate the recipes table with read in recipe collection values
	private static void populateRecipeTable() {

	}

	//**********************************************************************
	// Private Inner Classes
	//**********************************************************************

	/*
	 * Changes JTable so that the default highlighting is yellow
	 */
	private static class MyTable extends JTable
	{
		MyTable(Object[][] obj, String[] cols) {
			super(obj,cols);
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			this.setSelectionBackground(Color.decode("#ffcc00"));
		}
	}

	/*
	 * Changes the renderer so that the default highlighting is yellow
	 * the soon to be "expired" item which is less than 3 days left are red
	 * and when a red is highlighted it is orange instead of yellow
	 * I did a lot of googling and reading to figure this one out
	 */
	private static class MyRenderer extends DefaultTableCellRenderer
	{
			public MyRenderer()
			{
					super();
					setOpaque(true);
			}
			public Component getTableCellRendererComponent(JTable table, Object value,
							boolean isSelected, boolean hasFocus, int row, int column)
			{
					if(Integer.parseInt((String)table.getValueAt(row, 3)) < 3)
					{
							setForeground(Color.black);
							if (isSelected)
							{
								setBackground(Color.decode("#ff6600"));
							}
							else {
									setBackground(Color.decode("#e60000"));
							}
					}
					else
					{
							if (isSelected)
							{
								setBackground(Color.decode("#ffcc00"));
							}
							else {
								setBackground(Color.white);
							}
					}
					setText(value.toString());
					return this;
			}
	}

	// Custom listselectionlistener for tables
	private static class FTListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			shouldSaveOnExit = true;
			System.out.println("Change to table");
		}
	}
}
