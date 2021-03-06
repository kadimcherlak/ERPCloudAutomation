/*===============================================================================================================================
        CLASS Name:    ErrorReportGeneration
        CREATED BY:    Navya Mallajosyula
        DATE CREATED:  Nov 2017
        DESCRIPTION:   Error Reporting                    
        PARAMETERS:                                                                  
        RETURNS:      
        COMMENTS:                                     
        Modification Log:
        Date                             Initials                                                Modification
        
-------------         ------------    ------------------------------------------------------------------------------------------------------------------------------*/

package OCTS_Automation_Main_Modules;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class ErrorReportGeneration {
	static int errorStartLine;
	static int errorLastLine;
	/*static String zipFilePath = "C:\\Automation_OCTS\\Output\\70594.zip";  
    static String destDir = "C:\\Automation_OCTS\\Output\\";*/
	static String fileObj = ERP_Financial_Webservice_MainClass.zipFilePath.replace("zip", "txt");
	static StringBuffer errorLog = new StringBuffer();

	@Test
	public void journalErrorReportGeneration() throws IOException
	{
		System.out.println("\n Error Reporting \n");
		LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(
				new FileInputStream(fileObj), "UTF-8"));
			try{

				    String line = null;
				    line=line+lineNumberReader.readLine();
				    while((line = lineNumberReader.readLine()) != null) {
					    line=line+lineNumberReader.readLine();
					    if(line.contains("Error Lines"))	
					        errorStartLine = lineNumberReader.getLineNumber();

					    	if(line.contains("End of Report"))
					        errorLastLine = lineNumberReader.getLineNumber();
				    }

				}
			catch(Exception e){}
			 LineNumberReader lineNumberReader1 = new LineNumberReader(new InputStreamReader(
						new FileInputStream(fileObj), "UTF-8"));
			try{

			    String line = null;
			    line=line+lineNumberReader1.readLine();
			    while((line = lineNumberReader1.readLine()) != null) {
				
				    int getCurrentLineNumber =  lineNumberReader1.getLineNumber();
				    if(getCurrentLineNumber>=errorStartLine && getCurrentLineNumber<=errorLastLine)
				    {
				    	System.out.println(line);
				    }
			    }

			}
		catch(Exception e){}
			lineNumberReader.close();
			lineNumberReader1.close();
			
	}


}
