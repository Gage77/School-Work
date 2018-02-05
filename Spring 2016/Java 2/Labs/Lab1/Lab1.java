import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Lab #1
 * CS 2334, Section *** INSERT YOUR LAB SECTION NUMBER HERE ***
 * *** THE CURRENT DATE GOES HERE ***
 * <P>
 * This class creates a sample program with a graphical user
 * interface.
 * </P>
 * @version 1.0
 */
public class Lab1 implements ActionListener
{
   /** The main window of the program. */
   private JFrame     windowFrame;
   /** A label that holds a message to be displayed in the window. */
   private JLabel     textLabel;
   /** A button that will terminate the program and close the window. */
   private JButton    exitButton;

   /**
    * This is the constructor for the class Lab1. It initializes
    * the main window of the program and set ups event handling for
    * the program.
    * <P>
    * @param             message   The message to display to the user.
    */   
   public Lab1( String message )
   {
      // Create a text label for the program.
      textLabel = new JLabel( message );

      /* Create a button for the program that will terminate the program
       * and associate an action listener for the button.
       */
      exitButton = new JButton( "Click Here to Exit" );
      exitButton.addActionListener( this );

      /* Create a JFrame that will be the window for the program. */
      windowFrame = new JFrame( "CS 2334, Lab Exercise #1" );
      windowFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      
      /* Add the text field and button to the frame */
      windowFrame.getContentPane().add( textLabel, BorderLayout.CENTER );
      windowFrame.getContentPane().add( exitButton, BorderLayout.SOUTH );

      /* Make the window appear on the screen. */
      windowFrame.pack();
      windowFrame.setVisible( true );
   }

   /**
    * This method handles action events for the button displayed
    * in the window of this program.  When the button is pressed
    * the program will be terminated and the window will close.
    * <P>
    * @param             actionEvent   Any event of type ActionEvent
    *                                  associated with this program.
    */
   public void actionPerformed( ActionEvent actionEvent )
   {
      // Check to see if the user clicked on the exit button exitButton.
      if( actionEvent.getSource() == exitButton )
      {
         // Properly terminate the program.
         System.exit(0);
      }
   }

   /**
    * This is the main method for this lab exercise. The method should
    * instantiate an object of type Lab1 and pass it a message to be
    * displayed to the user.  This program will not process any
    * command line arguments sent to it by the user.
    * <P>
    * @param             args       Contains the command line arguments.
    */
  public static void main( String[] args )
   {
      Lab1 lab1Program;

      lab1Program = new Lab1("This is the first lab exercise for CS 2334.");
   }

}
