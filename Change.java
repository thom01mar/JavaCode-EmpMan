/**
    Name:         Mary Y. Yu
    Email:        thom01@gmail.com
    Compilation:  javac Change.java
    Execution:    java Change
    Dependencies: No known dependencies. 
    Description: This program is part of the Employee Management Program. This
                 program changes employee information 
*/

import javax.swing.*;           // For Swing classes.
import java.awt.*;              // For Layout, Font, Formating.
import java.io.*;               // For file input and output
import javax.swing.JOptionPane; // For JOptionPane.
import java.util.ArrayList;     // For ArrayList class.


public class Change 
{
    // Declaration and intialization.
    private String name = "";
    private String dpt = "";
    private String tit = "";
    private String hir = "";
    private String idfind = "";
    
    public Change() 
    {
        // Create second JOptionPane to get information to be change
        // for employee with the give ID:
        // Define font size.
        Font myFont = new Font("Veranda", Font.PLAIN, 17);
        
        // Create and format text fields.
        JTextField nameFld = new JTextField(20);
        nameFld.setFont(myFont);
        JTextField dptFld = new JTextField(20);
        dptFld.setFont(myFont);
        JTextField titFld = new JTextField(20);
        titFld.setFont(myFont);
        JTextField hirFld = new JTextField(20);
        hirFld.setFont(myFont);
        
        // Create and format labels.
        //JLabel idLbl = new JLabel("Employee ID: " + idfind, SwingConstants.LEFT);
        JLabel idLbl = new JLabel("Employee ID: ", SwingConstants.LEFT);    // will include user input ID later
        idLbl.setFont(myFont);
        idLbl.setPreferredSize( new Dimension( 400, 30 ) );
        JLabel instLbl = new JLabel("Please fill only the field(s) to be changed.", SwingConstants.LEFT);
        instLbl.setFont(myFont);
        instLbl.setPreferredSize( new Dimension( 400, 30 ) );
        JLabel nameLbl = new JLabel("Name: ", SwingConstants.RIGHT);
        nameLbl.setFont(myFont);
        nameLbl.setPreferredSize( new Dimension( 110, 30 ) );
        JLabel dptLbl = new JLabel("Department: ", SwingConstants.RIGHT);
        dptLbl.setFont(myFont);
        dptLbl.setPreferredSize( new Dimension( 110, 30 ) );
        JLabel titLbl = new JLabel("Title: ", SwingConstants.RIGHT);
        titLbl.setFont(myFont);
        titLbl.setPreferredSize( new Dimension( 110, 30 ) );
        JLabel hirLbl = new JLabel("Hire Date: ", SwingConstants.RIGHT);
        hirLbl.setFont(myFont);
        hirLbl.setPreferredSize( new Dimension( 110, 30 ) );

        // Creat a JPanel.
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,1, 10));
        fieldPanel.setPreferredSize( new Dimension( 400, 350 ) );
        fieldPanel.setFont(new Font("Verdana", Font.BOLD, 48));
        
        // Add components to fieldPanel.
        fieldPanel.add(idLbl);        
        fieldPanel.add(instLbl);
        fieldPanel.add(nameLbl);
        fieldPanel.add(nameFld);
        fieldPanel.add(dptLbl);
        fieldPanel.add(dptFld);
        fieldPanel.add(titLbl);
        fieldPanel.add(titFld);
        fieldPanel.add(hirLbl);
        fieldPanel.add(hirFld);

        // try & catch to address nullPoineterException when the fields are empty.
        try
        {
            // First JOptionPane for user to input on Emplyee ID.		
            String in = JOptionPane.showInputDialog(null, "Enter Employees ID.");
            if ((in == null) || (in.length() == 0))
            {
                if (in != null) JOptionPane.showMessageDialog(null, "No ID entered.");
                throw new NullPointerException ("No ID Entered. Please re-enter.");
            }
            else
            {
            idfind = in.trim();
            idLbl.setText ("Employee ID: " + idfind);
            }
                        
            // second JOptionPane with the above fieldPanel for fields to be changed.
            int result = JOptionPane.showConfirmDialog(null, fieldPanel, 
                     "EMPLOYEE DATABASE.", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION)
            {
                String name = (nameFld.getText()).trim();
                String dpt = (dptFld.getText()).trim();
                String tit = (titFld.getText()).trim();
                String hir = (hirFld.getText()).trim();
               
                // To address misssing/corrupted file.
                try
                {
                    if (name.equals("") && dpt.equals("") && tit.equals("") && hir.equals(""))
                    { 
                        JOptionPane.showMessageDialog(null, "Nothing entered for change. Click 'OK' to exit.");
                    }
                    else
                    {
                        update (idfind, name, dpt, tit, hir);
                    }
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }           
            }
        }
        catch (NullPointerException exc)
        {
            JOptionPane.showMessageDialog(null, "Exiting...\n"+ " Click 'OK' to complete exit.");
        }                    
    }
    public static void main(String[] args) 
    {
        new Change ();
    }
   
    public void update(String idfind, String cname, String cdpt, String ctit, 
                        String chir) throws IOException
    {
        String oline = "", nline = "", info = "", nid = "";                  
        String name = "", dpt = "", tit = "", hir = "", id = "";
        ArrayList<String> temp = new ArrayList<String>();        
        try
        {
            // BufferedReader object to read in old/current EmpData.txt. 
            // Array temp stores lines that does not contain the
            // data to be deleted/updated
            BufferedReader oreader = new BufferedReader(new FileReader("EmpData.txt"));
                    
            // loop through each line of old/or current EmpData.txt
            int i = 0;
            boolean found = false;     //indicator for finding given ID.
            while ((oline = oreader.readLine()) != null)
            {
                info = oline.trim();
                if (i%5 == 0)
                {
                    name = info;
                }
                else if (i%5 == 1)
                {
                    dpt = info;
                }
                else if (i%5 == 2)
                {
                    tit = info;
                }
                else if (i%5 == 3)
                {
                    hir = info;
                }
                else if (i%5 == 4)
                {
                    id = info;
                }
                
                // Compare idfind string and file string. If equal, information of 
                // the employee will not be added to Array temp.
                if ((!id.equalsIgnoreCase(idfind)) && (!id.equals(nid)))
                {
                    temp.add (name);
                    temp.add (dpt);                     
                    temp.add (tit);                    
                    temp.add (hir); 
                    temp.add (id);
                }
                else
                {
                    if (id.equalsIgnoreCase(idfind))
                    found = true;
                    if (!id.equals(nid))
                    {
                        if (cname.equals(""))
                        {
                            temp.add (name);
                        }
                        else
                        {
                            temp.add (cname);
                        }
                        if (cdpt.equals(""))
                        {
                            temp.add (dpt);
                        }
                        else
                        {
                            temp.add (cdpt);
                        }
                        if (ctit.equals(""))
                        {
                            temp.add (tit);
                        }
                        else
                        {
                            temp.add (ctit);
                        }
                        if (chir.equals(""))
                        {
                            temp.add (hir);
                        }
                        else
                        {
                            temp.add (chir);
                        }
                        temp.add(id);
                    }             
                }
                nid = id; 								
                i++;
            }
            oreader.close();
                
            // Message if there is no match. Otherwise data in arraylist temp will 
            // be written to EmpData and successful update message upont completion.
            if (!found)
            {
                JOptionPane.showMessageDialog(null, "There is no match for the given ID" + 
                                "\n\n Click 'OK' to exit", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                // The old EmpData.txt will be replace/delete with a new EmpData.txt.
                // This approach is take (instead of writting to another file and rename 
                // file to "EmpData.txt" because this approach ensures no dependency on
                // platform dependency (as in renaming files).           
                    
                PrintWriter nfile = new PrintWriter("EmpData.txt");

                //Using enhanced for loop to write Arraylist to Empdata.txt.
                for (String line: temp)
                {
                    nfile.write (line + "\n");
                }
                JOptionPane.showMessageDialog(null, "Employee database is successfully updated", 
                                   "Employee database update.", JOptionPane.INFORMATION_MESSAGE );
                                                          
                //Making sure buffered data is written file, the close file.
                nfile .flush();        
                nfile.close();
                }
        }
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
}