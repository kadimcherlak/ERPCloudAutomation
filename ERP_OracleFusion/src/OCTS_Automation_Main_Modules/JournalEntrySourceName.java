/*===============================================================================================================================
        CLASS Name:    JournalEntrySourceName
        CREATED BY:    Navya Mallajosyula
        DATE CREATED:  Nov 2017
        DESCRIPTION:   Journal Entry Source Name Validation                    
        PARAMETERS:                                                                  
        RETURNS:      
        COMMENTS:                                     
        Modification Log:
        Date                             Initials                                                Modification
        
-------------         ------------    ------------------------------------------------------------------------------------------------------------------------------*/

package OCTS_Automation_Main_Modules;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class JournalEntrySourceName extends ReadERPInputDataSheet {
	
	// Required Variables to pull out the matching string
	/*static String zipFilePath = "C:\\Automation_OCTS\\Output\\70594.zip";  
    static String destDir = "C:\\Automation_OCTS\\Output\\";*/
	static String fileObj = ERP_Financial_Webservice_MainClass.zipFilePath.replace("zip", "txt");
	static String excelPath = "C:\\Automation_OCTS\\Data\\ERP_InputDatasheet.xlsx";
	static String entrySourceName = "";
	static String[] splitNewLine = {};
	static String finalOutputString = "";
	static List<String> finalOutputValues = new ArrayList<String>();
	static ArrayList<Cell> outputValue = new ArrayList<Cell>();
	static String ColumnWanted = "SEJRRequestImport_PL2_JournalSource";
	int flag=0;
	
	@Test
	public void journalEntrySourcename() throws IOException {
		Common_Utility.ReporterBaseTest.test = Common_Utility.ReporterBaseTest.extent.createTest("Journal Entry Source Name");
		ReadERPInputDataSheet re = new ReadERPInputDataSheet();
		outputValue = re.parseInputExcelFile(excelPath, ColumnWanted);
		entrySourceName = outputValue.get(0).toString();

		getEntrySourceName();

		for (int i = 0; i < finalOutputValues.size() - 1; i++) {
			if (finalOutputValues.get(0).equals(entrySourceName)) {
				finalOutputString = finalOutputValues.get(0);
				flag=1;
			} 
		}
		if(flag==1)
		{
		System.out
				.println("Entry Source Name extracted from the Output File \n"
						+ finalOutputString);
		Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Step 1: Journal Entry Source Name matched with Output file source name" );
		}
		if(flag==0){
			System.out
			.println("Please check if the Entry Source Name keyed is correct");
			Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Step 1: Journal Entry Source Name mismatch found. Please check the input data file" );
			}
	}

	// Method to parse the text file and pull out the Matching pattern to
	// validate the excel
	public static List<String> getEntrySourceName() throws IOException {
		try {
			String line = null;
			String newLine = "";
			@SuppressWarnings("resource")
			BufferedReader bufRdr = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileObj), "UTF-8"));

			line = line + bufRdr.readLine();

			while ((line = bufRdr.readLine()) != null) {
				line = bufRdr.readLine();
				if (line.startsWith(entrySourceName)) {
					// System.out.println("Before trimming spaces\n" + line);
					newLine = line.trim();
					// System.out.println("After trimming spaces\n" + newLine);
					splitNewLine = newLine.split("\\s");
					for (String matchString : splitNewLine) {
						if ((!matchString.matches("\\d"))
								&& (matchString.length() != 0)
								&& (!matchString.matches("[^\\w.*]")))

						{

							finalOutputValues.add(matchString);
						}

					}
				}
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return finalOutputValues;

	}

}
