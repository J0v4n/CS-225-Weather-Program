
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    	
	/**
	 * @author: Arjun Bott
	 * [class variable]
	 * a list for a MonthlyReport
	 */
	private List<MonthlyReport> monthlyList;

	/**
	 * @author: Arjun Bott
	 * [class variable]
	 * a list for DailyReport
	 */
	private List<DailyReport> dailyList;

	/**
	 * @author: Arjun Bott
	 * default constructor
	 * Modified by: Carlos Rodriguez */
	public Storage() {
		this.monthlyList = new ArrayList<MonthlyReport>();
		this.dailyList = new ArrayList<DailyReport>();
		try {
			readCSV(new FileReader("src/All Daily Data.csv"), 1);
			readCSV(new FileReader("src/All Monthly Data.csv"), 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author: Arjun Bott
	 * Modified by: Carlos Rodriguez
	 * [getAllDaily method]
	 * @returns a copy of the current daily report list
	 */
	public List<DailyReport> getAllDaily() {
		return Collections.unmodifiableList(this.dailyList);
	}

	/**
	 * @author: Arjun Bott
	 * [getAllMonthly method]
	 * Modified by: Carlos Rodriguez
	 * @returns a copy of the current monthly report list */
	public List<MonthlyReport> getAllMonthly() {
		return Collections.unmodifiableList(this.monthlyList);
	}
	
	/**
	 * @author: Arjun Bott
	 * [readCSV method]
	 * Modified by: Carlos Rodriguez
	 * 
	 * Reads a given FileReader file, and based on the
	 * integer passed, it will store that data in the
	 * dailyList or the monthlyList.
	 * 
	 * @param givenFile: file to read.
	 * @param daily: 1 to store in dailyList, 2 to
	 * store in monthlyList
	 * */
	private void readCSV(FileReader givenFile, int daily) throws IOException{
		//read daily
		BufferedReader currFile = new BufferedReader(givenFile);
		String ignoreLine = currFile.readLine();
		String foundLine = "";
		String[] found = null;
		if(daily == 1) {
			try {
				while((foundLine = currFile.readLine()) != null) {
					found = foundLine.trim().split(",");
					
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
					found = foundLine.trim().split(",");
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

	/**
	 * @author: Arjun Bott
	 * [equals method]
	 * @param  obj  [the other object to check equality for]
	 * @return the equality of the two objects
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		return ((Storage) obj).dailyList.equals(this.dailyList) &&
				 ((Storage) obj).monthlyList.equals(this.monthlyList);
	}

	/**
	 * @author: Arjun Bott
	 * returns a string representation of this object
	 * @return a string representation of this object */
	@Override
	public String toString() {
		String buffer = "[Storage]\t";
		buffer += this.dailyList.toString() + "\t";
		buffer += this.monthlyList.toString();
		return buffer;
	}
	
	
}