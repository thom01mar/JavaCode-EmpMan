/** 
    Name:          Mary Y. Yu
    Email:         thom01@gmail.com
    Compilation:   javac Perf.java
    Execution:     java Perf
    Dependencies:  No known dependencies. 
    Description:   This program is a part of the Employee Management Program. 
                   program features:     
                   1) Scoring an Employee: Score is per Category.
                   2) Simple formatting and Writing Employee score 
                      (with Employee ID and name) to file "EmpPerf.txt".
                   3) Uploading Measures/Factors from a text file. The constraints:
                      - Must be a text file
                      - Maximum number of Performance Measures/Factors is 10
                      - Maximum Length of each Measure is 50 characters
                      - Five Categories. Performance Measures/Factors for a Category
                        must be contiguous in the text file
      
*/

import java.awt.*;              // For BorderLayout class.
import javax.swing.*;           // For Swing classes.
import java.awt.event.*;        // For Action Events.
import javax.swing.border.*;    // For borders.
import javax.swing.JOptionPane; // For error message to user.
import java.util.ArrayList;     // For ArrayList class.
import java.io.*;               // For Input and output to files.

public class Perf extends JFrame
{
    // Declaration.
    private JPanel infoPanel, instPanel, buttonPanel, q1Panel, 
                    q2Panel, q3Panel, q4Panel, q5Panel, 
                    q6Panel, q7Panel, q8Panel, q9Panel, q10Panel;             // Panels.
    private JTextArea inst;                                                  // Instruction Area.
    private JLabel infoLabel, instLabel, q1Label, q2Label, q3Label, q4Label,  
                    q5Label,q6Label, q7Label, q8Label, q9Label, q10Label;     // Labels.    
    private JRadioButton scale11, scale12, scale13, scale14, scale15;        // ReadioButtons.
    private JRadioButton scale21, scale22, scale23, scale24, scale25;        // ReadioButtons.
    private JRadioButton scale31, scale32, scale33, scale34, scale35;        // ReadioButtons.
    private JRadioButton scale41, scale42, scale43, scale44, scale45;        // ReadioButtons.
    private JRadioButton scale51, scale52, scale53, scale54, scale55;        // ReadioButtons.
    private JRadioButton scale61, scale62, scale63, scale64, scale65;        // ReadioButtons.
    private JRadioButton scale71, scale72, scale73, scale74, scale75;        // ReadioButtons.
    private JRadioButton scale81, scale82, scale83, scale84, scale85;        // ReadioButtons.
    private JRadioButton scale91, scale92, scale93, scale94, scale95;        // ReadioButtons.
    private JRadioButton scale101, scale102, scale103, scale104, scale105;   // ReadioButtons.
    private ButtonGroup group1, group2, group3, group4, group5, 
                    group6, group7, group8, group9, group10;                  //Buttongroup for RadioButton.
    private JButton uplButton, entButton, exitButton;                        // entry accepts input, exit exits program.
    private String q1 ="", q2 = "", q3 = "", q4 = "", q5 = "", q6 = ""; 
    private String q7 = "", q8 = "", q9 = "", q10 = "", name = "", id = "";
    private String[] idnam, idn;

    // Defining fonts and Borders.
    Font myFont = new Font("Veranda", Font.PLAIN, 15);
    Font myFont1 = new Font("Veranda", Font.PLAIN, 12);
    Font myFont3 = new Font("Veranda", Font.BOLD, 15);
    Border outer = BorderFactory.createEmptyBorder(4, 8, 4, 8);
    Border inner = BorderFactory.createEmptyBorder(4,8, 4, 8);
    Border combined = BorderFactory.createCompoundBorder(outer, inner);
    
    // Constructor creates the GUI.
    public Perf()
    {
        // try and catch to address nullPointerException when input is
        // empty in the first JOption Pane.
        try
        {
            // Create first input JoptionPane to ger Employee ID and name.
            // Create a Jpanel with Grid layout.
            JPanel fieldPanel = new JPanel(new GridLayout(5, 1));
           
            // Create and format input text fields and labels
            JTextField nameFld = new JTextField(20);
            JTextField idFld = new JTextField(20);
            JLabel instLbl = new JLabel("Enter both Employee ID and name , then Click 'OK' to start. " +
                               " Click 'Cancel' to end the program." );
            JLabel idLbl = new JLabel("Employee ID: ");
            JLabel nameLbl = new JLabel("Employee Name: ");
            
            //Add components to JPanel
            fieldPanel.add(instLbl);
            fieldPanel.add(idLbl);
            fieldPanel.add(idFld);     
            fieldPanel.add(nameLbl);
            fieldPanel.add(nameFld);
            
            // JOptionPane with above JPanel and its content/components.
            int result = JOptionPane.showConfirmDialog(null, fieldPanel, 
                     "EMPLOYEE DATABASE.", JOptionPane.OK_CANCEL_OPTION);
            id =  (idFld.getText()).trim();
            name = (nameFld.getText()).trim();

            if (id.equals("") || name.equals(""))  
            {
                throw new NullPointerException ();    // 
            }

            // Set the title bar text.
            setTitle("EMPLOYEE PERFORMANCE EVALUATION");
                   
            //Specify an action for the close button.
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Add a BorderLayout manager to the content pane.
            setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
            
            // create Text area for instructions. 
            // Note: The key to the five Scales (1 - 5) provided here.
            inst = new JTextArea(7, 38);
            inst.setBorder(combined);
            inst.setFont(myFont);
            inst.setText("Please use these Performance Rating Scales to rate each Factor:\n" +
                          "-----------------------------------------------------------------" +
                          "--------------------\n" + "5. Exceptional.\n" + 
                          "4. Exceeds expectation.\n"+ "3. Meets expectation.\n" + 
                          "2. Improvement needed\n" + "1. Unsatisfactory.\n" +
                          " Click 'Upload' to upload questions. Click Enter to submit.");
            inst.setEditable(false);
            
            // Create 11 panels.
            infoPanel = new JPanel();  
            instPanel = new JPanel();
            q1Panel = new JPanel();
            q2Panel = new JPanel();
            q3Panel = new JPanel();
            q4Panel = new JPanel();
            q5Panel = new JPanel();
            q6Panel = new JPanel();
            q7Panel = new JPanel();
            q8Panel = new JPanel();
            q9Panel = new JPanel();
            q10Panel = new JPanel();
            buttonPanel = new JPanel();        
            
            // Create and format 11 labels. One label for each Performance Measure/Factor. 
            // Note: Above JOptionPane that obtain user input on Employee ID and Name 
            // goes into infoLabel here.       
            infoLabel = new JLabel ("ID: "+ id  + ".  Name: " + name, JLabel.LEFT);
            q1Label = new JLabel ("Technical Skills: Job Knowlege.", JLabel.LEFT);
            q1Label.setFont(myFont3);
            q1Label.setPreferredSize(new Dimension(400, 20));
            q2Label = new JLabel("Technical Skills: Analyzes Problems.", JLabel.LEFT);
            q2Label.setFont(myFont3);
            q2Label.setPreferredSize(new Dimension(400, 30));
            q3Label = new JLabel("Quality of Work: Accuracy and Precision.", JLabel.LEFT);
            q3Label.setFont(myFont3);
            q3Label.setPreferredSize(new Dimension(400, 30));
            q4Label = new JLabel("Quality of Work:  Reliabiity.", JLabel.LEFT);
            q4Label.setFont(myFont3);
            q4Label.setPreferredSize(new Dimension(400, 30));
            q5Label = new JLabel("Interpersonal Skills: Team Participation.", JLabel.LEFT);
            q5Label.setFont(myFont3);
            q5Label.setPreferredSize(new Dimension(400, 30));
            q6Label = new JLabel("Interpersonal Skills: Tact and Diplomacy.", JLabel.LEFT);
            q6Label.setFont(myFont3);
            q6Label.setPreferredSize(new Dimension(400, 30));
            q7Label = new JLabel("Job Performance: Planning and ORganization.", JLabel.LEFT);
            q7Label.setFont(myFont3);
            q7Label.setPreferredSize(new Dimension(400, 30));
            q8Label = new JLabel("Job Performance: Flexibility/Adaptibility", JLabel.LEFT);
            q8Label.setFont(myFont3);
            q8Label.setPreferredSize(new Dimension(400, 30));
            q9Label = new JLabel("Leadership Skills: Trains/Mentor new employee.", JLabel.LEFT);
            q9Label.setFont(myFont3);
            q9Label.setPreferredSize(new Dimension(400, 30));
            q10Label = new JLabel("Leadership skills: Supports constructive criticism.", JLabel.LEFT);
            q10Label.setFont(myFont3);
            q10Label.setPreferredSize(new Dimension(400, 30));
          
            // Create five groups of five radio buttons, 
            // a group for each Performance Measure/Factor.
            group1 = new ButtonGroup();
            group1.add(scale11 = new JRadioButton("1"));
            scale11.setActionCommand("1");
            group1.add(scale12 = new JRadioButton("2"));
            scale12.setActionCommand("2");
            group1.add(scale13 = new JRadioButton("3"));
            scale13.setActionCommand("3");
            group1.add(scale14 = new JRadioButton("4"));
            scale14.setActionCommand("4");
            group1.add(scale15 = new JRadioButton("5"));
            scale15.setActionCommand("5");
            group2 = new ButtonGroup();
            group2.add(scale21 = new JRadioButton("1"));
            scale21.setActionCommand("1");
            group2.add(scale22 = new JRadioButton("2"));
            scale22.setActionCommand("2");
            group2.add(scale23 = new JRadioButton("3"));
            scale23.setActionCommand("3");
            group2.add(scale24 = new JRadioButton("4"));
            scale24.setActionCommand("4");
            group2.add(scale25 = new JRadioButton("5"));
            scale25.setActionCommand("5");
            group3 = new ButtonGroup();
            group3.add(scale31 = new JRadioButton("1"));
            scale31.setActionCommand("1");
            group3.add(scale32 = new JRadioButton("2"));
            scale32.setActionCommand("2");
            group3.add(scale33 = new JRadioButton("3"));
            scale33.setActionCommand("3");
            group3.add(scale34 = new JRadioButton("4"));
            scale34.setActionCommand("4");
            group3.add(scale35 = new JRadioButton("5"));
            scale35.setActionCommand("5");
            group4 = new ButtonGroup();
            group4.add(scale41 = new JRadioButton("1"));
            scale41.setActionCommand("1");
            group4.add(scale42 = new JRadioButton("2"));
            scale42.setActionCommand("2");
            group4.add(scale43 = new JRadioButton("3"));
            scale43.setActionCommand("3");
            group4.add(scale44 = new JRadioButton("4"));
            scale44.setActionCommand("4");
            group4.add(scale45 = new JRadioButton("5"));
            scale45.setActionCommand("5");
            group5 = new ButtonGroup();
            group5.add(scale51 = new JRadioButton("1"));
            scale51.setActionCommand("1");
            group5.add(scale52 = new JRadioButton("2"));
            scale52.setActionCommand("2");
            group5.add(scale53 = new JRadioButton("3"));
            scale53.setActionCommand("3");
            group5.add(scale54 = new JRadioButton("4"));
            scale54.setActionCommand("4");
            group5.add(scale55 = new JRadioButton("5"));
            scale55.setActionCommand("5");
            group6 = new ButtonGroup();
            group6.add(scale61 = new JRadioButton("1"));
            scale61.setActionCommand("1");
            group6.add(scale62 = new JRadioButton("2"));
            scale62.setActionCommand("2");
            group6.add(scale63 = new JRadioButton("3"));
            scale63.setActionCommand("3");
            group6.add(scale64 = new JRadioButton("4"));
            scale64.setActionCommand("4");
            group6.add(scale65 = new JRadioButton("5"));
            scale65.setActionCommand("5");
            group7 = new ButtonGroup();
            group7.add(scale71 = new JRadioButton("1"));
            scale71.setActionCommand("1");
            group7.add(scale72 = new JRadioButton("2"));
            scale72.setActionCommand("2");
            group7.add(scale73 = new JRadioButton("3"));
            scale73.setActionCommand("3");
            group7.add(scale74 = new JRadioButton("4"));
            scale74.setActionCommand("4");
            group7.add(scale75 = new JRadioButton("5"));
            scale75.setActionCommand("5");
            group8 = new ButtonGroup();
            group8.add(scale81 = new JRadioButton("1"));
            scale81.setActionCommand("1");
            group8.add(scale82 = new JRadioButton("2"));
            scale82.setActionCommand("2");
            group8.add(scale83 = new JRadioButton("3"));
            scale83.setActionCommand("3");
            group8.add(scale84 = new JRadioButton("4"));
            scale84.setActionCommand("4");
            group8.add(scale85 = new JRadioButton("5"));
            scale85.setActionCommand("5");
            group9 = new ButtonGroup();
            group9.add(scale91 = new JRadioButton("1"));
            scale91.setActionCommand("1");
            group9.add(scale92 = new JRadioButton("2"));
            scale92.setActionCommand("2");
            group9.add(scale93 = new JRadioButton("3"));
            scale93.setActionCommand("3");
            group9.add(scale94 = new JRadioButton("4"));
            scale94.setActionCommand("4");
            group9.add(scale95 = new JRadioButton("5"));
            scale95.setActionCommand("5");
            group10 = new ButtonGroup();
            group10.add(scale101 = new JRadioButton("1"));
            scale101.setActionCommand("1");
            group10.add(scale102 = new JRadioButton("2"));
            scale102.setActionCommand("2");
            group10.add(scale103 = new JRadioButton("3"));
            scale103.setActionCommand("3");
            group10.add(scale104 = new JRadioButton("4"));
            scale104.setActionCommand("4");
            group10.add(scale105 = new JRadioButton("5"));
            scale105.setActionCommand("5");

            // Create and format three buttons.
            uplButton = new JButton("Upload");
            entButton = new JButton("Enter");
            entButton.setFont(myFont);
            exitButton = new JButton("Exit");
            exitButton.setFont(myFont);        

            // Add the components to the panels.
            infoPanel.add(infoLabel);
            instPanel.add(inst);
            q1Panel.add(q1Label);
            q1Panel.add(scale11);  
            q1Panel.add(scale12);            
            q1Panel.add(scale13);  
            q1Panel.add(scale14);            
            q1Panel.add(scale15);         
            q2Panel.add(q2Label);
            q2Panel.add(scale21);  
            q2Panel.add(scale22);            
            q2Panel.add(scale23);  
            q2Panel.add(scale24);            
            q2Panel.add(scale25);         
            q3Panel.add(q3Label);
            q3Panel.add(scale31);  
            q3Panel.add(scale32);            
            q3Panel.add(scale33);  
            q3Panel.add(scale34);            
            q3Panel.add(scale35);         
            q4Panel.add(q4Label);
            q4Panel.add(scale41);  
            q4Panel.add(scale42);            
            q4Panel.add(scale43);  
            q4Panel.add(scale44);            
            q4Panel.add(scale45);         
            q5Panel.add(q5Label);
            q5Panel.add(scale51);  
            q5Panel.add(scale52);            
            q5Panel.add(scale53);  
            q5Panel.add(scale54);            
            q5Panel.add(scale55);         
            q6Panel.add(q6Label);    
            q6Panel.add(scale61);  
            q6Panel.add(scale62);            
            q6Panel.add(scale63);  
            q6Panel.add(scale64);            
            q6Panel.add(scale65);         
            q7Panel.add(q7Label);
            q7Panel.add(scale71);  
            q7Panel.add(scale72);            
            q7Panel.add(scale73);  
            q7Panel.add(scale74);            
            q7Panel.add(scale75);         
            q8Panel.add(q8Label);       
            q8Panel.add(scale81);  
            q8Panel.add(scale82);            
            q8Panel.add(scale83);  
            q8Panel.add(scale84);            
            q8Panel.add(scale85);         
            q9Panel.add(q9Label);
            q9Panel.add(scale91);  
            q9Panel.add(scale92);            
            q9Panel.add(scale93);  
            q9Panel.add(scale94);            
            q9Panel.add(scale95);         
            q10Panel.add(q10Label);        
            q10Panel.add(scale101);  
            q10Panel.add(scale102);            
            q10Panel.add(scale103);  
            q10Panel.add(scale104);            
            q10Panel.add(scale105);
            buttonPanel.add(uplButton);         
            buttonPanel.add(entButton); 
            buttonPanel.add(exitButton);

            // Add the panels to the content pane.
            add(infoPanel);
            add(instPanel);
            add(q1Panel);
            add(q2Panel);
            add(q3Panel);
            add(q4Panel);
            add(q5Panel);
            add(q6Panel);
            add(q7Panel);
            add(q8Panel);
            add(q9Panel);
            add(q10Panel);
            add(buttonPanel);              
                
            // Pack and display the window with custom format.
            pack();
            setSize(700, 750);
            setResizable(false);
            setFont(myFont1);
            setVisible(true);
            
            // Register the action listeners for the three buttons.
            uplButton.addActionListener(new UplButtonListener());
            exitButton.addActionListener(new ExitButtonListener());
            entButton.addActionListener(new EntButtonListener());       
        }
        catch (NullPointerException exc)
        {
            JOptionPane.showMessageDialog(null, "Ending when'Cancel' is clicked or missing ID/name\n"+ " Click 'OK' to complete exit.");
        }                   
    }
        
    /*
     Private inner class to handle the event when user clicks the "Exit" button. 
     When an event occurrs, program terminates.
    */
    private class ExitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)    
        {
            setVisible(false);
            dispose();
        }
    }
    
    /*
     Private inner class to handle the event when user clicks the "Enter" button. 
     When an event occurrs, the scores for each Performance Category is added. 
     Scores (with Employee ID and Name) is written (with basic formatting) to a 
     file called "EmpPerf.txt".
    */

     private class EntButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // NullPointerException will occur if any of the group does not have a 
            // selection.
            try
            {
                int score1 = Integer.parseInt(group1.getSelection().getActionCommand());
                int score2 = Integer.parseInt(group2.getSelection().getActionCommand());
                int score3 = Integer.parseInt(group3.getSelection().getActionCommand());
                int score4 = Integer.parseInt(group4.getSelection().getActionCommand());
                int score5 = Integer.parseInt(group5.getSelection().getActionCommand());
                int score6 = Integer.parseInt(group6.getSelection().getActionCommand());
                int score7 = Integer.parseInt(group7.getSelection().getActionCommand());
                int score8 = Integer.parseInt(group8.getSelection().getActionCommand());
                int score9 = Integer.parseInt(group9.getSelection().getActionCommand());
                int score10 = Integer.parseInt(group10.getSelection().getActionCommand());
                
                // IO Exception for the file to be read.
                try
                {
                    FileWriter dWriter = new FileWriter("EmpPerf.txt", true);
                    PrintWriter outputFile = new PrintWriter(dWriter);
                    outputFile.println("ID: " + id);
                    outputFile.println("Name: " + name +"\n");
                    outputFile.println("Summary of scores for each Category:");
                    outputFile.println("------------------------------------");
                    outputFile.println("Technical: " + (score1 + score2));
                    outputFile.println("Quality of Work: " + (score3 + score4));
                    outputFile.println("Interpersonal skills: " +(score5 + score6));
                    outputFile.println("Job Performance: " + (score7 + score8));
                    outputFile.println("Leadership skills: " + (score9 + score10) +'\n');
                    outputFile.close();
                }
                catch (IOException ex)
                {              
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Data written to the file 'EmpPerf.txt'");
                setVisible(false);
                dispose();
            }
            catch (NullPointerException exc)
            {
                    JOptionPane.showMessageDialog(null, "Missing Ratings! Try again");
             }                       
        }
    }
    /* Private inner class to handle the event when user clicks the "Enter" button. 
       When an event occurrs, user will be asked for the text file name. Once filename is
       provide, program will read and write each Performance Measure/Factor into each
       Jlabel text. 
    */
    private class UplButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String in = JOptionPane.showInputDialog(null, "Maximum number of Performance " +
                       "Measures is 10.\n" + "Maximum Length of each Measure is 50 characters.\n"+ 
                       "Enter the full file name (example: Factors.txt) to upload the Performance" +
                       " measures", "EMPLOYEE PERFORMANCE", JOptionPane.QUESTION_MESSAGE);      

            if ((in != null) && (in.length() > 0))
            {		
                try 
                {
                    String filename = in.trim();
                    // Create our bufferedreader to read the file
                    BufferedReader reader = new BufferedReader(new FileReader(filename));		
                    
                    // Loop through the file reading in lines until readLine returns null (end of file)
                    int i = 0;
                    String line;
                    boolean found = false;
                    while (((line = reader.readLine()) != null))
                    {
                        String info = line.trim();
                        if (i==0) q1Label.setText(info);
                        if (i==1) q2Label.setText(info);
                        if (i==2) q3Label.setText(info);
                        if (i==3) q4Label.setText(info);
                        if (i==4) q5Label.setText(info);
                        if (i==5) q6Label.setText(info);
                        if (i==6) q7Label.setText(info);
                        if (i==7) q8Label.setText(info);
                        if (i==8) q9Label.setText(info);
                        if (i==9) q10Label.setText(info);		           
                        i++;
                    }
                    reader.close();               
                }   
                catch (Exception ex) 
                { 
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } 
            }
        }  
    }
    // The main method. 
    public static void main(String[] args)
    {
        new Perf();                         // an instance of Perf().
        
    }           
}