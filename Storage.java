import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Storage {
    	
	private List<DailyReport> dailyList = new ArrayList<DailyReport>();
	private List<MonthlyReport> monthlyList = new ArrayList<MonthlyReport>();
	
	/***
	 * Testing Constructor
	 */
	public Storage() {
			try {
				readCSV(new FileReader("All Daily Data.csv"),1);
				readCSV(new FileReader("All Monthly Data.csv"),2);
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
	
	//Getters
	public List<DailyReport> getDailyList(){
		return Collections.unmodifiableList(this.dailyList);
	}
	
	public List<MonthlyReport> getMonthlyList(){
		return Collections.unmodifiableList(this.monthlyList);
	}
	
	private void readCSV(FileReader givenFile, int daily) throws IOException{
		//read daily
		BufferedReader currFile = new BufferedReader(givenFile);
		String ignoreLine = currFile.readLine();
		String foundLine = "";
		String[] found = null;
		if(daily == 1) {
			try {
				while((foundLine = currFile.readLine()) != null) {
					found = foundLine.split(",");
					
					DailyReport dRep = new DailyReport(found[1], found[0], 
						Double.parseDouble(found[2]) , Double.parseDouble(found[3]), 
						Double.parseDouble(found[4]));
					this.dailyList.add(dRep);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//monthly list case
		if(daily == 2) {
			try {				
				while((foundLine = currFile.readLine()) != null) {
					found = foundLine.split(",");
					MonthlyReport mRep = new MonthlyReport(found[1], found[0], 
						Double.parseDouble(found[4]), Double.parseDouble(found[5]), 
						Double.parseDouble(found[3]), Double.parseDouble(found[2]));
					this.monthlyList.add(mRep);	
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}			
}
