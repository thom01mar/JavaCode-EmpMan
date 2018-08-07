/** 
    Name:         Mary Y. Yu
    Email:        thom01@gmail.com
    Compilation:  javac Add.java
    Execution:    java Add
    Dependencies: No known dependencies. 
    Description: This program emulates Employee database entry. This Program 
                 is part of Employee Management Program.
   
*/

import java.awt.*;              // For BorderLayout class.
import javax.swing.*;           // For Swing classes.
import java.awt.event.*;        // For Action Events.
import javax.swing.border.*;    // For borders.
import javax.swing.JOptionPane; // For error message to user.
import java.util.ArrayList;     // For ArrayList class.
import java.io.*;

public class Add extends JFrame
{
    // Declarations.
    ArrayList<String> nameList = new ArrayList<String>();
    ArrayList<String> idList = new ArrayList<String>();
    ArrayList<String> dptList = new ArrayList<String>();
    ArrayList<String> titList = new ArrayList<String>();
    ArrayList<String> hirList = new ArrayList<String>();

    private JPanel fieldIPanel, fieldPanel, buttonPanel;              // Area, fields, and button panels.
    private JLabel labelI, label1, label2, label3, label4, label5;    // Labels.
    private JTextField name, id, dpt, tit, hir;                       // TextFields.
    private JButton entButton, exitButton;                            // Buttons

    // The Constructor. Create the GUI for user input.
    public Add()
    {
        //Set fonts and Borders.
        Font myFont = new Font("Veranda", Font.PLAIN, 15);
        Font myFont1 = new Font("Veranda", Font.PLAIN, 12);
        Font myFont3 = new Font("Veranda", Font.BOLD, 15);
        Font myFont4 = new Font("Veranda", Font.BOLD, 17);       
        Border inner = BorderFactory.createEmptyBorder(4, 4, 4, 4);
        Border outer = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border combined = BorderFactory.createCompoundBorder(outer, inner);
		
        // Set the title bar text.
        setTitle("Employee Database");
               
        //Specify an action for the close button.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add a BorderLayout manager to the content pane.
        setLayout(new BorderLayout(0,30));

        // Create two panels.
        fieldIPanel = new JPanel();
        fieldPanel = new JPanel();
        buttonPanel = new JPanel();
        
        // Create and format 6 labels.
        labelI = new JLabel ("Fill ALL the fields below for EACH employee. " +
                              "Click 'Enter' to Submit.", JLabel.CENTER);
        labelI.setFont(myFont4);
        labelI.setPreferredSize(new Dimension(580, 50));
        label1 = new JLabel("Name:  ", JLabel.RIGHT);
        label1.setFont(myFont3);
        label1.setPreferredSize(new Dimension(110, 30));
        label2 = new JLabel("ID:  ", JLabel.RIGHT);
        label2.setFont(myFont3);
        label2.setPreferredSize(new Dimension(110, 30));
        label3 = new JLabel("Department:  ", JLabel.RIGHT);
        label3.setFont(myFont3);
        label3.setPreferredSize(new Dimension(110, 30));
        label4 = new JLabel("Title:  ", JLabel.RIGHT);
        label4.setFont(myFont3);
        label4.setPreferredSize(new Dimension(110, 30));
        label5 = new JLabel("Hire Date:  ", JLabel.RIGHT);
        label5.setFont(myFont3);
        label5.setPreferredSize(new Dimension(110, 30));
        
        //Create and format five text fields. 
        name = new JTextField(25);
        name.setBackground(Color.WHITE);
        name.setBorder(combined);
        name.setFont(myFont);
        name.setEditable(true);
        id = new JTextField(25);
        id.setBackground(Color.WHITE);
        id.setBorder(combined);
        id.setFont(myFont);
        id.setEditable(true);     
        dpt = new JTextField(25);
        dpt.setBackground(Color.WHITE);
        dpt.setBorder(combined);
        dpt.setFont(myFont);
        dpt.setEditable(true);     
        tit = new JTextField(25);
        tit.setBackground(Color.WHITE);
        tit.setBorder(combined);
        tit.setFont(myFont);
        tit.setEditable(true);     
        hir = new JTextField(25);
        hir.setBackground(Color.WHITE);
        hir.setBorder(combined);
        hir.setFont(myFont);
        hir.setEditable(true);     

        // Create and format two buttons.
        entButton = new JButton("Enter");
        entButton.setFont(myFont);
        exitButton = new JButton("Exit");
        exitButton.setFont(myFont);        

        // Add the components to the panels.
        fieldIPanel.add(labelI); 
        fieldPanel.add(label1);
        fieldPanel.add(name);                
        fieldPanel.add(label2);
        fieldPanel.add(id);       
        fieldPanel.add(label3);
        fieldPanel.add(dpt);       
        fieldPanel.add(label4);
        fieldPanel.add(tit);       
        fieldPanel.add(label5);
        fieldPanel.add(hir);       
        buttonPanel.add(entButton);
        buttonPanel.add(exitButton);

        // Add the panels to the content pane.
        add(fieldIPanel, BorderLayout.NORTH);
        add(fieldPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
            
        // Pack and display the window with custom format.
        pack();
        setSize(580, 400);
        setResizable(false);
        setFont(myFont1);
        setVisible(true);
        
        // Register the action listeners.
        entButton.addActionListener(new EntButtonListener());
        exitButton.addActionListener(new ExitButtonListener());
        //name.addMouseListener(new MyMouseListener());
    }
    
    /*
     Private inner class that handles mouse click events.
     When an event occurred, the input text field for 
     that event cleared and backgound is set to grey.
    private class MyMouseListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            name.setText (" ");
            name.setBackground(Color.lightGray);
        }
    }   
    /* 
     Pivate inner class that handles Ent button click events.
     When an event occurs, all user entered information will 
     be added/append to the arrays for temporary storage until
     user exit program.
    */
    private class EntButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //check for missin information
            if ((!name.getText().trim().equals("")) && (!id.getText().trim().equals("")) &&
                 (!dpt.getText().trim().equals("")) && (!tit.getText().trim().equals("")) &&
                   (!hir.getText().trim().equals("")))
            {
                nameList.add (name.getText().trim());
                idList.add (id.getText().trim());
                dptList.add (dpt.getText().trim());
                titList.add (tit.getText().trim());
                hirList.add (hir.getText().trim());
                name.setText("");
                id.setText("");
                dpt.setText("");
                tit.setText("");
                hir.setText("");                         
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "All fields must be populated! "+
                                                     "\nPlease correct and Click 'Enter'", 
                                                     "Warning:", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    /*
     Private inner class to handle the event when user clicks the "Exit" button. 
     When an event occurrs, a new file "EmpData.txt" will be created if it does 
     not exits. All User input information written/append to file "EmpDat.txt" 
     and  program terminates. 
    */
    private class ExitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)    
        {
            if (!nameList.isEmpty())
            {
                try
                {
                    FileWriter dWriter = new FileWriter("EmpData.txt", true);
                    PrintWriter outputFile = new PrintWriter(dWriter);
                    for (int i = 0; i<nameList.size(); i++)
                    {
                        outputFile.println(nameList.get(i));
                        outputFile.println(dptList.get(i));
                        outputFile.println(titList.get(i));
                        outputFile.println(hirList.get(i));
                        outputFile.println(idList.get(i));
                    }
                    outputFile.close();
                }
                catch (IOException ex) 
                {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Data written to the file EmpData.txt.");
            }
            setVisible(false);
            dispose();
        }
    }
    
  
    // The main method. 
    public static void main(String[] args)
    {
        new Add();  // An instance of Add().
    }
    
           
}
