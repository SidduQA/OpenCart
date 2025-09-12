package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException {

		String path = ".\\testData\\OpenCart_login_data.xlsx"; // taking xl file from testdata folder

		ExcelUtility xlutil = new ExcelUtility(path); // Creating an object of excel utility

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);

		String logindata[][] = new String[totalrows][totalcols]; // created for two dimension array which can store data

		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata; // retruning two dimension array

	}

}
