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
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.ou.cs.hci.resources.*;

//******************************************************************************

public final class Stage6
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	//**********************************************************************
	// Private Members
	//**********************************************************************

	//**********************************************************************
	// Main
	//**********************************************************************

	//main
	public static void main(String[] args)
	{
		//MAIN WINDOW creates the base JFrame on which everything will be displayed
		JFrame			frame = new JFrame("FridgTrackr");
		//sets the defualt size of the main window
		frame.setBounds(50, 50, 800, 600);
		frame.getContentPane().setLayout(new BorderLayout());

		// Top panel
		JPanel top = new JPanel(new BorderLayout());
		JLabel welcome = new JLabel("Welcome to FridgTrackr!");
		welcome.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		welcome.setBorder(new EmptyBorder(5, 200, 5, 200));
		top.add(welcome, BorderLayout.CENTER);

		JButton back = new JButton("<-");
		back.setBackground(Color.decode("#e60000"));
		back.setForeground(Color.WHITE);
		back.setOpaque(true);
		back.setToolTipText("Go back");
		back.setPreferredSize(new Dimension(100, 50));
		back.setFont(new Font("Arial", Font.PLAIN, 25));
		top.add(back, BorderLayout.WEST);

		JButton add = new JButton("+");
		add.setBackground(Color.decode("#ffcc00"));
		add.setForeground(Color.WHITE);
		add.setToolTipText("Add a new item");
		add.setFont(new Font("Arial", Font.PLAIN, 25));
		add.setPreferredSize(new Dimension(100, 50));
		top.add(add, BorderLayout.EAST);

		ActionListener backListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("back button pressed. value: N/A");
			}
		};
		back.addActionListener(backListener);

		ActionListener addListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("add item button pressed. value: N/A");
			}
		};
		add.addActionListener(addListener);

		//creates the 3 category panels
		JPanel			recipes = new JPanel(new BorderLayout());
		JPanel			fridge = new JPanel(new BorderLayout());
		JPanel			groceries = new JPanel(new BorderLayout());

		//adds a title to each category panel
		recipes.setBorder(BorderFactory.createTitledBorder("Recipes"));
		fridge.setBorder(BorderFactory.createTitledBorder("Fridge"));
		groceries.setBorder(BorderFactory.createTitledBorder("Groceries"));

		frame.getContentPane().add(top, BorderLayout.PAGE_START);

		//creates the pane that will store the category tabs
		JTabbedPane tabs = new JTabbedPane();
		// Could use Weaver's getImage() for this now but this code works
		Icon fridgeIcon = new ImageIcon(Stage6.class.getResource("refrigerator.png"));
		Icon recipesIcon = new ImageIcon(Stage6.class.getResource("contract.png"));
		Icon groceriesIcon = new ImageIcon(Stage6.class.getResource("groceries.png"));
		//adds tabs to JTabbedPane
		tabs.addTab("Fridge", fridgeIcon, fridge);
		tabs.addTab("Recipes", recipesIcon, recipes);
		tabs.addTab("Groceries", groceriesIcon, groceries);
		tabs.setTabPlacement(JTabbedPane.TOP);
		//adds the JTabbedPane to the base pane
		frame.getContentPane().add(tabs, BorderLayout.CENTER);

		//creates the content of the fridge category panel
		String[] colName = new String[] {"☆", "Name" ,"Amount", "Days Left", "Leftovers?"};
		Object[][] products = new Object[][] {
                { "☆", "Apples", "15 (Apples)", "3" , ""},
                { "★", "Eggs", "6 (Eggs)", "12" , ""},
                { "☆", "Chili", "--", "3", "Yes"},
                { "★", "Oranges" ,"20 (Oranges)", "4", ""},
                { "☆", "Peaches" ,"10 (Peaches)", "1", ""},
                { "☆", "Tacos", "--", "2", "Yes"},
                { "★", "Bread", "2 (Slices)", "7", ""},
                { "☆", "Potato Chips", "1 (Bags)", "3" , ""}
							};
		//creates a table to hold the fridge panel data
		JTable fridgeTable = new JTable( products, colName );
		fridgeTable.getColumnModel().getColumn(0).setMaxWidth(25);
		fridgeTable.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		fridgeTable.getTableHeader().setFont(new Font("Lucida Console", Font.PLAIN, 13));
		fridgeTable.setRowHeight(20);
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
		recipes.add(new JScrollPane(table2), BorderLayout.CENTER);

		//**********************************************************************
		// Fridge Tab Filter Checkbox Panel
		//**********************************************************************

		JPanel			mid = new JPanel(new BorderLayout());

		JPanel			filterPanel = new JPanel(new GridLayout(2, 4));
		filterPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		JCheckBox		favoritesBox = new JCheckBox();
		favoritesBox.setHorizontalAlignment(JCheckBox.RIGHT);
		JLabel			favoritesLabel = new JLabel("Favorites");
		JCheckBox		expiredBox = new JCheckBox();
		expiredBox.setHorizontalAlignment(JCheckBox.RIGHT);
		JLabel			expiredLabel = new JLabel("Expired");
		JCheckBox		lowBox = new JCheckBox();
		lowBox.setHorizontalAlignment(JCheckBox.RIGHT);
		JLabel			lowLabel = new JLabel("Low Stock");
		JCheckBox		leftoversBox = new JCheckBox();
		leftoversBox.setHorizontalAlignment(JCheckBox.RIGHT);
		JLabel			leftoversLabel = new JLabel("Leftovers");

		filterPanel.add(favoritesBox);
		filterPanel.add(favoritesLabel);
		filterPanel.add(expiredBox);
		filterPanel.add(expiredLabel);
		filterPanel.add(lowBox);
		filterPanel.add(lowLabel);
		filterPanel.add(leftoversBox);
		filterPanel.add(leftoversLabel);
		mid.add(filterPanel, BorderLayout.CENTER);
		fridge.add(mid, BorderLayout.NORTH);

		//**********************************************************************
		// REcipes Tab Filter Checkbox Panel
		//**********************************************************************

		JPanel			mid2 = new JPanel(new BorderLayout());

		JPanel			filterPanel2 = new JPanel(new GridLayout(1, 4));
		filterPanel2.setBorder(new EmptyBorder(5, 50, 5, 50));
		JCheckBox		favoritesBox2 = new JCheckBox(); // Items marked as favorite by user
		favoritesBox2.setHorizontalAlignment(JCheckBox.RIGHT);
		JLabel			favoritesLabel2 = new JLabel("Favorites");
		JCheckBox		inStockBox = new JCheckBox(); // Recipes for which ingredients are in stock
		inStockBox.setHorizontalAlignment(JCheckBox.RIGHT);
		JLabel			inStockLabel = new JLabel("In Stock");

		filterPanel2.add(favoritesBox2);
		filterPanel2.add(favoritesLabel2);
		filterPanel2.add(inStockBox);
		filterPanel2.add(inStockLabel);
		mid2.add(filterPanel2, BorderLayout.CENTER);
		recipes.add(mid2, BorderLayout.NORTH);

		//**********************************************************************
		// Menu Bar
		//**********************************************************************

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		//**********************************************************************
		// File Menu
		//**********************************************************************

		JMenuItem openItem = new JMenuItem(new AbstractAction("Open (CTRL + O)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Open (CTRL + O). Opens a FridgTrackr file.");
		    }
		});
		JMenuItem saveItem = new JMenuItem(new AbstractAction("Save (CTRL + S)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Save (CTRL + S). Saves the current FridgTrackr file.");
		    }
		});
		JMenuItem printAllItem = new JMenuItem(new AbstractAction("All (CTRL + P)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Print -> All (CTRL + P). Prints the full FridgTrackr file.");
		    }
		});
		//JMenuItem printSelectedItem = new JMenuItem("Currently Shown");
		JMenuItem printFridgeItem = new JMenuItem(new AbstractAction("Fridge Stock")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Print -> Fridge Stock. Prints the current fridge stock.");
		    }
		});
		JMenuItem printRecipesItem = new JMenuItem(new AbstractAction("Recipes")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Print -> Recipes. Prints the currently stored recipes.");
		    }
		});
		JMenuItem printGroceriesItem = new JMenuItem(new AbstractAction("Groceries")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("File -> Print -> Groceries. Prints the current grocery list.");
		    }
		});
		JMenu printSubmenu = new JMenu("Print");
		JMenuItem quitItem = new JMenuItem("Quit	(CTRL + Q)"); // ActionListener added later
		printSubmenu.add(printAllItem);
		//printSubmenu.add(printSelectedItem);
		printSubmenu.add(printFridgeItem);
		printSubmenu.add(printRecipesItem);
		printSubmenu.add(printGroceriesItem);
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(printSubmenu);
		fileMenu.add(quitItem);
		menuBar.add(fileMenu);

		//**********************************************************************
		// Edit Menu
		//**********************************************************************

		JMenuItem copy = new JMenuItem(new AbstractAction("Copy (CTRL + C)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Copy (CTRL + C). Copies the selection.");
		    }
		});
		JMenuItem cut = new JMenuItem(new AbstractAction("Cut (CTRL + X)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Cut (CTRL + X). Cuts the selection.");
		    }
		});
		JMenuItem paste = new JMenuItem(new AbstractAction("Paste (CTRL + V)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Paste (CTRL + V). Pastes the selection.");
		    }
		});
		JMenuItem search = new JMenuItem(new AbstractAction("Search (CTRL + F)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Search (CTRL + F). Allows the user to search for a string.");
		    }
		});
		JMenuItem restore = new JMenuItem(new AbstractAction("Restore (CTRL + Z)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Restore (CTRL + Z). Restores the last deleted item to a limit.");
		    }
		});
		JMenuItem favorites = new JMenuItem(new AbstractAction("Favorites")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Filter By: --> Favorites. Filters by favorited items.");
		    }
		});
		JMenuItem expiration = new JMenuItem(new AbstractAction("Expired")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Filter By: --> Expired. Filters by expired items.");
		    }
		});
		JMenuItem lowStock = new JMenuItem(new AbstractAction("Low Stock")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Filter By: --> Low Stock. Filters by low stock.");
		    }
		});
		JMenuItem leftovers = new JMenuItem(new AbstractAction("Leftovers")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Edit -> Filter By: --> Leftovers. Filters by items marked as leftovers.");
		    }
		});
		JMenu filterBySubmenu = new JMenu("Filter By:");
		filterBySubmenu.add(favorites);
		filterBySubmenu.add(expiration);
		filterBySubmenu.add(lowStock);
		filterBySubmenu.add(leftovers);
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(copy);
		editMenu.add(cut);
		editMenu.add(paste);
		editMenu.addSeparator();
		editMenu.add(search);
		editMenu.add(restore);
		editMenu.add(filterBySubmenu);
		menuBar.add(editMenu);

		//**********************************************************************
		// Window Menu
		//**********************************************************************

		JMenu theme = new JMenu("Theme");
		JMenuItem fontStyle = new JMenuItem(new AbstractAction("Font Style")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Theme -> Font Style. Allows the user to select a font style.");
		    }
		});
		JMenuItem fontColour = new JMenuItem(new AbstractAction("Font Colour")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Theme -> Font Colour. Allows the user to select a font colour.");
		    }
		});
		JMenu background = new JMenu("Background");
		JMenuItem primary = new JMenuItem(new AbstractAction("Primary Colour")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Theme -> Background -> Primary Colour. "
		        		+ "Allows the user to select the primary background colour.");
		    }
		});
		JMenuItem secondary = new JMenuItem(new AbstractAction("Secondary Colour")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Theme -> Background -> Secondary Colour. "
		        		+ "Allows the user to select the secondary background colour.");
		    }
		});
		JMenuItem swap = new JMenuItem(new AbstractAction("Swap")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Theme -> Background -> Swap. "
		        		+ "Allows the user to swap the primary and secondary background colours.");
		    }
		});
		background.add(primary);
		background.add(secondary);
		background.add(swap);
		JMenuItem importTheme = new JMenuItem(new AbstractAction("Import")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Theme -> Import. Allows the user to import a theme.");
		    }
		});
		JMenuItem saveTheme = new JMenuItem(new AbstractAction("Save")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Theme -> Save. Allows the user to save the current theme.");
		    }
		});
		theme.add(fontStyle);
		theme.add(fontColour);
		theme.add(background);
		theme.addSeparator();
		theme.add(importTheme);
		theme.add(saveTheme);
		JMenu settings = new JMenu("Settings");
		JMenuItem restorePt = new JMenuItem(new AbstractAction("Set Restore Point")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Settings -> Set Restore Point. "
		        		+ "Allows the user to set the amount of restores to keep.");
		    }
		});
		JMenu units = new JMenu("Set units");
		JMenuItem metric = new JMenuItem(new AbstractAction("Metric")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Settings -> Set Units -> Metric. "
		        		+ "Allows the user to set the units of measurement to the metric system.");
		    }
		});
		JMenuItem imperial = new JMenuItem(new AbstractAction("Imperial")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Window -> Settings -> Set Units -> Imperial. "
		        		+ "Allows the user to set the units of measurement to the imperial system.");
		    }
		});
		JMenuItem customary = new JMenuItem(new AbstractAction("Customary")
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
		JMenu windowMenu = new JMenu("Window");
		windowMenu.add(theme);
		windowMenu.add(settings);
		menuBar.add(windowMenu);

		//**********************************************************************
		// Help Menu
		//**********************************************************************

		JMenu help = new JMenu("Help");
		JMenuItem link = new JMenuItem(new AbstractAction("Link (CTRL + ?)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Link. Opens a related webpage: http://www.myfridgefood.com/");
		    }
		});
		JMenu accessibility = new JMenu("Accessibility");
		JMenuItem fontSize = new JMenuItem(new AbstractAction("Font Size")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Accessibility -> Font Size. Allows the user to edit the font size.");
		    }
		});
		JMenuItem resolution = new JMenuItem(new AbstractAction("Resolution")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Accessibility -> Resolution. Allows the user to adjust the resolution.");
		    }
		});
		JMenuItem invert = new JMenuItem(new AbstractAction("Invert")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Accessibility -> Invert. Inverts the colours being displayed.");
		    }
		});
		JMenuItem bold = new JMenuItem(new AbstractAction("Bold")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Accessibility -> Bold. Bolds all text.");
		    }
		});
		accessibility.add(fontSize);
		accessibility.add(resolution);
		accessibility.add(invert);
		accessibility.add(bold);
		help.add(accessibility);
		JMenuItem feedback = new JMenuItem(new AbstractAction("Feedback (CTRL + H)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Feedback (CTRL + H). Opens a window to allow the user provide feedback.");
		    }
		});
		JMenuItem donate = new JMenuItem(new AbstractAction("Donate (CTRL + $)")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("Help -> Donate (CTRL + $). Allows the user to donate.");
		    }
		});
		help.add(feedback);
		help.add(donate);
		menuBar.add(help);

		//**********************************************************************
		// Tool Bar
		//**********************************************************************

		JToolBar toolBar = new JToolBar("Tool Bar");
		JButton searchTool = new JButton(new AbstractAction("Search")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("ToolBar --> Search (CTRL + F). Allows the user to search for an item in the database.");
		    }
		});
		searchTool.setIcon(Resources.getImage("icons/magnifying-glass.png"));
		searchTool.setFont(new Font("Arial", Font.PLAIN, 15));
		searchTool.setBackground(Color.decode("#ffcc00"));
		searchTool.setVerticalTextPosition(SwingConstants.TOP);
		searchTool.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(searchTool);
		JButton filterTool = new JButton(new AbstractAction("Filter")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("ToolBar --> Filter. Allows the user to choose criteria to filter by.");
		    }
		});
		filterTool.setIcon(Resources.getImage("icons/filter.png"));
		filterTool.setFont(new Font("Arial", Font.PLAIN, 15));
		filterTool.setBackground(Color.decode("#ffcc00"));
		filterTool.setVerticalTextPosition(SwingConstants.TOP);
		filterTool.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(filterTool);
		toolBar.addSeparator();
		JButton restoreTool = new JButton(new AbstractAction("Restore")
		{
				public void actionPerformed(ActionEvent a)
				{
						System.out.println("ToolBar --> Restore (CTRL + Z). Restores the most recently deleted item.");
				}
		});
		restoreTool.setIcon(Resources.getImage("icons/reload.png"));
		restoreTool.setFont(new Font("Arial", Font.PLAIN, 15));
		restoreTool.setBackground(Color.decode("#e60000"));
		restoreTool.setForeground(Color.WHITE);
		restoreTool.setVerticalTextPosition(SwingConstants.TOP);
		restoreTool.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(restoreTool);
		JButton settingsTool = new JButton(new AbstractAction("Settings")
		{
		    public void actionPerformed(ActionEvent a)
		    {
		        System.out.println("ToolBar --> Settings. Opens the settings window for the application.");
		    }
		});
		settingsTool.setIcon(Resources.getImage("icons/settings.png"));
		settingsTool.setFont(new Font("Arial", Font.PLAIN, 15));
		settingsTool.setBackground(Color.decode("#e60000"));
		settingsTool.setForeground(Color.WHITE);
		settingsTool.setVerticalTextPosition(SwingConstants.TOP);
		settingsTool.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(settingsTool);
		JButton boldTool = new JButton(new AbstractAction("Bold")
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

		frame.getContentPane().add(toolBar, BorderLayout.PAGE_END);

		//**********************************************************************
		// Filter buttons action listeners
		//**********************************************************************

		ActionListener favListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("favorites filter checkbox pressed. value: " + favoritesBox.isSelected());
			}
		};
		favoritesBox.addActionListener(favListener);

		ActionListener favListener2 = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("favorites filter checkbox pressed. value: " + favoritesBox2.isSelected());
			}
		};
		favoritesBox2.addActionListener(favListener2);

		ActionListener expiredListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("expired filter checkbox pressed. value: " + expiredBox.isSelected());
			}
		};
		expiredBox.addActionListener(expiredListener);

		ActionListener lowListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("low stock filter checkbox pressed. value: " + lowBox.isSelected());
			}
		};
		lowBox.addActionListener(lowListener);

		ActionListener leftoversListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("leftovers filter checkbox pressed. value: " + leftoversBox.isSelected());
			}
		};
		leftoversBox.addActionListener(leftoversListener);

		ActionListener inStockListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("In Stock filter checkbox pressed. value: " + inStockBox.isSelected());
			}
		};
		inStockBox.addActionListener(inStockListener);

		//**********************************************************************
		// Final steps and setup closing behavior
		//**********************************************************************

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
				public void windowClosing(WindowEvent e)
				{
					quitItem.doClick();
					System.exit(0);
				}
		});

	//**********************************************************************
	// Private Inner Classes
	//**********************************************************************

	}
}
