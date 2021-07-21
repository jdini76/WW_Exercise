package functions;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.opencsv.CSVReader;

public class FunctionLibrary {

	public static WebDriver Driver;

	public void setBrowserDriver(String url) {

		System.setProperty("webdriver.chrome.driver", "./resources/driver/chromedriver.exe");
		Driver = new ChromeDriver();
		Driver.get(url);

	}

	public void loginfo(String input) {

		System.out.println(input);
	}
	
	public Object[][] getDataFromCSV(String TestName,String Filepath)throws Exception{
		//Map<String, String> map = new HashMap<String, String>();
		String[] aa,str;
		int rows=0, dataRow=0;
		List<String[]> Data = new LinkedList<String[]>();
		File file = new File(Filepath);
		Filepath = file.getAbsolutePath();
		CSVReader reader = new CSVReader(new FileReader(new File(Filepath)));
		while (!Arrays.asList((str = reader.readNext())).contains("END")){
			if (str[0].contains(TestName)) {
				while(!(String.join("", (aa = reader.readNext())).trim().equals(""))&&(!Arrays.asList(aa).contains("END"))){
					     Data.add(aa);  
					     rows++;  
					                         
                }
					break;	
			}
		}
				String[] columns = Data.get(0);
				Object[][] Enddata = new Object[rows-1][1];
				Hashtable<String, String> table = null;
				for(int rnum =1; rnum<rows; rnum++){
					String[] rowdata = Data.get(rnum);
					table = new Hashtable<String, String>();
					for(int cnm = 0; cnm<columns.length; cnm++){						
						String Key =  columns[cnm];
						String Value = rowdata[cnm];						
				        table.put(Key, Value);
						
					}
					Enddata[dataRow][0] = table;
					dataRow++;
				}
			
		return Enddata;	

		}

}
