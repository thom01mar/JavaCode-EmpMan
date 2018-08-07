/** 
    Name:         Mary Y. Yu
    Email:        thom01@gmail.com
    Compilation:  javac Add.java
    Execution:    java Add
    Dependencies: No known dependencies. 
    Description:  This program is a part of the Employee Management Program. 
                   This program searches the file for the ID provided by user. 
                   If ID is found, program display all the information on 
                   the employee with that ID. 

import javax.swing.JOptionPane;  // For all the JOptionPanes.
import java.io.*;                // For file searches.

public class SearchDat 
{
		// Declarations and initialization.
		// Line to hold the line read from file.
		// info to trim() the line.
		String line = "", info = "", idfind = "";
        String name = "", dpt = "", tit = "", hir = "", id = "";

    public SearchDat()
    {
        // try and catch for NullPointerException encountered when fields are empty
        // when OK, cancel, and x are clicked.
        try
        {        
            // JOptionPane for user to input  Emplyee ID.
            String in = JOptionPane.showInputDialog("Enter Employees ID: \n" + 
                             "(Program exits when 'OK' is clicked with no entry.)");
            idfind = in.trim();
         }
        catch (NullPointerException e)
        {
            // No actions since normal cancel or x exit also yield a null.
        }
        
        // Try and catch necessary to IOException in case "EmpData.txt" file is not
        // found or is corrupted/unable to read/open.
        try
        {
            if ((idfind != null) && (idfind.length() > 0))  // Prevents the next display without ID.
            {
                search(idfind);                             // Calls search Method.
            }
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Exiting..." + "Click 'OK' to complete exit");
        }
    }
    // Maing method.
    public static void main(String args[]) 
    {
        new SearchDat();    // an instance of SearchDat().
	}
	
	// The search method that search User provided IP. Display Employee, with given ID,
	// information or a message "There is no match".
	public void search(String idfind) throws IOException
	{
        // Create our bufferedreader to read the file
        BufferedReader reader = new BufferedReader(new FileReader("EmpData.txt"));
			
			
		// Loop through the file reading in lines until readLine returns null (end of file)
		// or id is found.
		int i = 0;
		boolean found = false;
		while (((line = reader.readLine()) != null) && (!found))
		{
			info = line.trim();
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
			// Compare idfind string and file string. If equal, found = true and
			// search stops.
			if (id.equalsIgnoreCase(idfind))
			{
                found = true;
                JOptionPane.showMessageDialog(null, "ID: " + id + "\nName: " + name +
                                          "\nDepartment: " + dpt + "\nTitle: " + tit +
                                          "\nHire Date: " + hir + "\n\nClick 'OK' to exit",
                                          "Search Result:", JOptionPane.INFORMATION_MESSAGE );
            }
								
            i++;
		}			
        // Message if there is no match.
        if (!id.equalsIgnoreCase(idfind))
        {
            JOptionPane.showMessageDialog(null, "There is no match" + "\n\n Click 'OK' to exit", 
                                                "Warning!", JOptionPane.WARNING_MESSAGE);
        }
		reader.close(); //close file
	}
	
}