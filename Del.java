/** 
    Name:         Mary Y. Yu
    Email:        thom01@gmail.com
    Compilation:  javac Del.java
    Execution:    java Del
    Dependencies: No known dependencies. 
    Description:  This program is a part of the Employee Management Program. This 
                  program searches and deletes the employee that has the
                  User provided ID number. 
*/

import java.io.*;               // Needed for File input & output.
import javax.swing.JOptionPane; // Needed for JOptionPane.
import java.util.ArrayList;     // Needed for ArrayList class

public class Del
{
        // Declaration & initialization
		//oline & nline to hold the line read.
		//id to trim() the line input.
		String oline = "", nline = "", info = "", idfind = "", nid = "";                  
        String name = "", dpt = "", tit = "", hir = "", id = "";
        ArrayList<String> temp = new ArrayList<String>();
        
    public Del()
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
                searchDel(idfind);                          // Calls searchDel() Method.
            }
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Exiting..." + "Click 'OK' to complete exit");
        }
    }
    // Main method.
	public static void main(String[] args)
	{
        new Del();                                          // An instance of Del()
    }
					
	// BufferedReader object to read in old/current EmpData.txt. 
	// Arraylist temp stores lines that does not contain the
	// data to be deleted/updated.
	public void searchDel(String idfind)  throws IOException 
	{
		BufferedReader oreader = new BufferedReader(new FileReader("EmpData.txt"));
				
		// loop through each line of old/current in "EmpData.txt".
		int i = 0;
		boolean found = false;                              //indicator for finding given ID.
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
			// the employee will not be added to array temp. Instead, found 
			// will be set to true indicating that ID is found.
			if ((!id.equalsIgnoreCase(idfind)) && (!id.equals(nid)))
			{
                temp.add (name);
                temp.add (dpt);                     
                temp.add (tit);                    
                temp.add (hir); 
                temp.add (id);
            }
            else if (id.equalsIgnoreCase(idfind))
            { 
                found = true;
            }
            nid = id; 								
			i++;
				
        }
        oreader.close();
        // If found is false, Message "There is no match for the given ID will be 
        // displayed. Otherwise data in array temp will be written to "EmpData.txt"
        // and successful update message upon completion.
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
            //PrintWriter nfile = new PrintWriter("output.txt");
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
}
