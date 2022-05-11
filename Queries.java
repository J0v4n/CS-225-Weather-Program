import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Queries {
   	
	//Dummy Instance
	Storage storage = new Storage();
	private List<DailyReport> d;
	private List<MonthlyReport> m;
	//Dummy Constructor
	public Queries(){
		this.d = this.storage.getDailyList();
		this.m = this.storage.getMonthlyList();
	}
	
	public List<DailyReport> getDailyReportsInAMonth(String date){
		return null;
	}
	
	public List getStationReports(String year, boolean isDaily){
		return null;
	}
	
	/**
	 * PROCEDURE:
	 * -Sort dailylist by date
	 * -Binary search middle index
	 * -Return if middle index equals
	 * -Else evaluate charsAt(i)
	 * -if matching cities but
	 * different dates compare by date 
	 * -if <, ignore leftSide
	 * -if >, ignore rightSide
	 * 
	 * 
	 * @author: Carlos Rodriguez
	 * Uses binary search to retrieve a specific report
	 * on a specific date.
	 * 
	 * @param station: the desired station of the report.
	 * @param date: the desired date of the report.
	 * @return: a DailyReport matching the criteria, null
	 * if no matching element was found. */
	
	public DailyReport getSpecificDailyReport(String station, String date) {
		DailyReport foundReport = null;
		int startingIdx = 0;
		int endingIdx = d.size() - 1;
		boolean matchingStation = false;
		
		while(startingIdx <= endingIdx && matchingStation) {
			//Find the list's midpoint
			int middleIdx = startingIdx + (endingIdx - startingIdx) / 2;
			String currStation = d.get(middleIdx).getDailyStationName();
			String currDate = d.get(middleIdx).getData();
			
			//Matching specific element
			if(currStation.equals(station) && currDate.equals(date)) {
				foundReport = d.get(middleIdx);
				matchingStation = true;
				return foundReport;
			}
			//Further checking
			else {
				//-1 to the left, 1 to the right
				int direction = -1;
				//Station matching
				if(currStation.equals(station)) {
					//Check for date through linear search
					String[] dateGiven = date.split("/");
					String[] currD = currDate.split("/");
					if(Integer.parseInt(currD[2]) < Integer.parseInt(dateGiven[2])) {
						if(Integer.parseInt(currD[0]) < Integer.parseInt(dateGiven[0])) {
							if(Integer.parseInt(currD[0]) < Integer.parseInt(dateGiven[0])) {
								direction = -1;
							}
						}
					}
					else {
						direction = 1;
					}
					//Linear search
					if(direction == -1) {
						while(middleIdx > startingIdx && !matchingStation) {
							middleIdx--;
							currStation = this.d.get(middleIdx).getDailyStationName();
							currDate = this.d.get(middleIdx).getData();
							if(currStation.equals(station) && currDate.equals(date)) {
								foundReport = d.get(middleIdx);
								matchingStation = true;
								return foundReport;
							}
						}
					}
					else {
						while(middleIdx < endingIdx && !matchingStation) {
							middleIdx++;
							currStation = this.d.get(middleIdx).getDailyStationName();
							currDate = this.d.get(middleIdx).getData();
							if(currStation.equals(station) && currDate.equals(date)) {
								foundReport = d.get(middleIdx);
								matchingStation = true;
								return foundReport;
							}
						}
					}
				}
				//Not matching station
				else {
					
				}
			}
		}
		return foundReport;
	}

	public static void main(String[] args) {
		Queries aQ = new Queries();
		DailyReport aRep = aQ.getSpecificDailyReport("BEDFORD HANSCOM FIELD", "1/1/2021");
		System.out.println(aRep.toString());
			
	}
	
}
