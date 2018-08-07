/** 
    Name:         Mary Y. Yu
    Email:        thom01@gmail.com
    Compilation:  javac EmpMan.java
    Execution:    java EmpMan
    Dependencies: Requires Add.java, Change.java, Del.java,
                  Perf.java.
    Description:  This program is for Employee management. This program has theses
                  features:
                   - Adding/Writing Employee information (Name, ID, Title, Departement,
                     and date hired) to "EmpData.txt' (emulating a database)
                   - Searching Employee, using User provided employee ID number", in 
                     the file "EmpData.txt." Program display information on the Employee 
                     with the given ID
                   - Given the User provided ID, program can update/change employee 
                     information (except the unique ID number) in file "EmpData.txt."
                   - Delete an employee (all the information in file "EmpData.txt."
                   - Employee Perfomance rating: Program can upload ten Factors/Measures, 
                     each of 50 characters maximum. Rating is base on 5 level Likert Scale.
                     Program scores each of the Five Categories, each with two Factors.  
                     Scores is written to file "EmpPerf.txt".
*/

import java.awt.*;               // For BorderLayout class.
import javax.swing.*;            // For Swing classes.
import java.awt.event.*;         // For Action Events.
import javax.swing.border.*;     // For borders.
import java.util.Scanner;        // For Scanner class to read file.
import java.lang.ProcessBuilder; // For opening external executables.
import java.io.*;                // Forn file input and outpu.

public class EmpMan extends JFrame
{
    private JPanel empbuttonPanel, hipbuttonPanel, exitbuttonPanel;      // Button Panels 
    private JPanel labelpanel;                                           // Label Panel
    private JLabel label, label1, label2;                                // Labels
    private JButton exitButton, addButton, seaButton, updButton;         // Buttons
    private JButton delButton, hirButton, perButton;                     // Buttons

    // The Constructor. Create the GUI for user input.
    public EmpMan()
    {
        //Set fonts and Borders.
        Font myFont = new Font("Veranda", Font.PLAIN, 15);
        Font myFont1 = new Font("Veranda", Font.PLAIN, 12);
        Font myFont3 = new Font("Veranda", Font.PLAIN, 17);
        Border inner = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        Border outer = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border ThickL = BorderFactory.createLineBorder(Color.GRAY, 3);
        Border BlankL = BorderFactory.createEmptyBorder(10, 20, 20, 20);
        Border Bevel = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        Border wlabel1 = BorderFactory.createTitledBorder(outer, "EMPLOYEE DATABASE", 
                         TitledBorder.CENTER, TitledBorder.ABOVE_TOP, myFont, Color.BLACK);
        Border wlabel2 = BorderFactory.createTitledBorder(outer, "HIRING & PERFORMANCE", 
                         TitledBorder.CENTER, TitledBorder.ABOVE_TOP, myFont, Color.BLACK);
        Border combined1 = BorderFactory.createCompoundBorder(wlabel1, inner);
        Border combined2 = BorderFactory.createCompoundBorder(wlabel2, inner);
        Border combined3 = BorderFactory.createCompoundBorder(BlankL, inner);
        Border combined4 = BorderFactory.createCompoundBorder(Bevel, ThickL);
		
        // Set the title bar text.
        setTitle("Employee Management.");
               
        //Specify an action for the close button.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a BorderLayout manager to the content pane.
        //setLayout(new GridLayout(4,1));
        setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));

        // Create panels.
        labelpanel = new JPanel();
        empbuttonPanel = new JPanel(new GridLayout(2,2));
        empbuttonPanel.setBorder(combined1);
        hipbuttonPanel = new JPanel();
        hipbuttonPanel.setBorder(combined2);
        exitbuttonPanel = new JPanel();
        exitbuttonPanel.setBorder(combined3);
                
        // Create and format 3 labels.
        label = new JLabel("Please make your choice by clicking one " + 
                            "of the buttons below.",JLabel.CENTER);
        label.setFont(myFont3);
        label.setPreferredSize(new Dimension(500, 40));

        // Create and format seven buttons.
        exitButton = new JButton("EXIT");
        exitButton.setBorder(combined4);
        exitButton.setFont(myFont);
        exitButton.setPreferredSize(new Dimension(100, 30));
        addButton = new JButton("ADD");
        addButton.setBorder(combined4);
        addButton.setFont(myFont);
        addButton.setPreferredSize(new Dimension(100, 30));
        seaButton = new JButton("SEARCH");
        seaButton.setBorder(combined4);
        seaButton.setFont(myFont);       
        seaButton.setPreferredSize(new Dimension(100, 30));
        updButton = new JButton("UPDATE");
        updButton.setFont(myFont);
        updButton.setBorder(combined4);
        updButton.setPreferredSize(new Dimension(100, 30));
        delButton = new JButton("DELETE");
        delButton.setFont(myFont);
        delButton.setBorder(combined4);
        delButton.setPreferredSize(new Dimension(100, 30));
        hirButton = new JButton("INTERVIEW");
        hirButton.setFont(myFont);
        hirButton.setBorder(combined4);
        hirButton.setPreferredSize(new Dimension(100, 30));
        perButton = new JButton("PERFORMANCE");
        perButton.setFont(myFont);
        perButton.setBorder(combined4);
        perButton.setPreferredSize(new Dimension(150, 30));

        // Add the components to the panels. 
        labelpanel.add(label);
        exitbuttonPanel.add(exitButton);
        empbuttonPanel.add(addButton);
        empbuttonPanel.add(seaButton);     
        empbuttonPanel.add(updButton);
        empbuttonPanel.add(delButton);
        hipbuttonPanel.add(hirButton);
        hipbuttonPanel.add(perButton);

        // Add the panels to the content pane.
        add(labelpanel);
        add(empbuttonPanel);
        add(hipbuttonPanel);
        add(exitbuttonPanel);
            
        // Pack and display the window with custom format.
        pack();
        setSize(500, 550);
        setResizable(false);
        setFont(myFont1);
        setVisible(true);
        
        // Register the action listeners.
        exitButton.addActionListener(new ExitButtonListener());
        addButton.addActionListener(new AddButtonListener());
        seaButton.addActionListener(new SeaButtonListener());        
        updButton.addActionListener(new UpdButtonListener()); 
        delButton.addActionListener(new DelButtonListener());
        hirButton.addActionListener(new HirButtonListener());
        perButton.addActionListener(new PerButtonListener());
    }
    /*
     Private inner class to handle the event when user clicks 
     the "Exit" button. When an event occurrs, program ends
     and sytem exits.
    */
    private class ExitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)    
        {
            System.exit(0);
        }
    }
    
    /*
     Private inner class to handle the event when user clicks 
     the "Add" button. When an event occurrs, Add() instance is
     created.
    */
    private class AddButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)    
        {
           // Call Add() to add/Write Employee information to
           // File "EmpData.txt".
           new Add();
        }
    }
    /*
     Private inner class to handle the event when user clicks 
     the "Search" button. When an event occurrs, SearchDat()
     instance is created.
    */
    private class SeaButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)    
        {
            // Calls SeachDat() to search employee, in 
            // "EmpData.txt"given employee ID number 
            // provided by user.
            new SearchDat();
        }
    }   

    /*
     Private inner class to handle the event when user clicks 
     the "Database" button. When an event occurrs, Change () 
     instance is created.
    */
    private class UpdButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)    
        {
           // Calls Change() to change/update employee information
           // in file "EmpData.txt".
           new Change();
        }
    }
    /*
     Private inner class to handle the event when user clicks 
     the "Database" button. When an event occurrs, Del () 
     instance is created.
    */    
    private class DelButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)    
        {
            // Calls Del() to delete Employee from file 
            // "EmpData.txt".
            new Del();

        }
    }
    /*
     Private inner class to handle the event when user clicks 
     the "Database" button. When an event occurrs, external
     excutable is started.
    */
    private class HirButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            try 
            {
                // JOptionPane to get the external executable file from User.
                String filename = JOptionPane.showInputDialog(" Please put the .exe file in the same location " + 
                                                                "as this program.\n"+
                                                                "Enter Filename including the extension." +
                                                               "(Ex: Sample.exe)");
                // Start external executable file.
                Process p = new ProcessBuilder(filename).start();
            } 
            catch (IOException ex) 
            {
                // Disply message of this exception
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            catch (NullPointerException ex)
            {
                    // for empty field that thow this exception.
            }
        }
    }
    /*
     Private inner class to handle the event when user clicks 
     the "Database" button. When an event occurrs, Perf() 
     instance is created.
    */
    private class PerButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)    
        {
            // call a Perf() instance to generate a performance
            // Rating Dialog.
            new Perf();
        }
    }   
    
    // The main method. 
    public static void main(String[] args)
    {
        // An instance of Eliza.
        new EmpMan(); 
    }                 
}
