import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queries {
   	
	/**
	 * storage: Instance to have access to the 
	 * data gathered.
	 * d: global dailyList
	 * m: global monthlyList */
	private Storage storage;
	private List<DailyReport> d;
	private List<MonthlyReport> m;
	
	//Default Constructor
	public Queries(){
		this.storage = new Storage();
		this.d = this.storage.getAllDaily();
		this.m = this.storage.getAllMonthly();
	}
		
	/**
	 * @author: Carlos Rodriguez
	 * Uses binary search to find specific station and
	 * linear search in matched station to retrieve a 
	 * specific report on a specific date.
	 * 
	 * @param station: the desired station of the report.
	 * @param date: the desired date of the report.
	 * @return: a DailyReport matching the criteria, null
	 * if no matching element was found. */
	
	public DailyReport getSpecificDailyReport(String station, String date) {
		DailyReport foundReport = null;
		boolean matchingStation = false;
		boolean reportWasFound = false;
		int startIdx = 0;
		int endIdx = this.d.size() - 1;
		int middleIdx = 0;
		String currStation = "";
		String aDate = "";
		
		//Binary Search for matching station
		while((startIdx <= endIdx) && (!reportWasFound)
				&& (!matchingStation)) {
			middleIdx = startIdx + (endIdx - startIdx) / 2;
			currStation = this.d.get(middleIdx).getDailyStationName();
			
			//Found matching station
			if(currStation.equals(station)) {
				matchingStation = true;
			}
			else {
				//Binary search choice: to left or to right of list
				int currIdx = 0;
				while(((currIdx < currStation.length()) && (currIdx < station.length()))
						&& (currStation.charAt(currIdx) == station.charAt(currIdx))) {
					currIdx++;
				}
				if(station.charAt(currIdx) > currStation.charAt(currIdx)) {
					startIdx = middleIdx + 1; }
				else {
					endIdx = middleIdx - 1; }
			}
		}
		
		//Once a matching station has been found
		if(matchingStation) {
			int c = middleIdx;
			
			String[] givenDate = date.split("/");
			String[] currDate = this.d.get(c).getData().split("/");
			LocalDate date1 = LocalDate.of(Integer.parseInt(givenDate[2]), Integer.parseInt(givenDate[0]), Integer.parseInt(givenDate[1]));
			LocalDate date2 = LocalDate.of(Integer.parseInt(currDate[2]), Integer.parseInt(currDate[0]), Integer.parseInt(currDate[1]));
		
			boolean currLessThan = date2.isBefore(date1);
			//Linear search forward
			if(currLessThan) {
				while(currLessThan && c <= endIdx) {
					givenDate = date.split("/");
					aDate = this.d.get(c).getData();
					currDate = aDate.split("/");
					currStation = this.d.get(c).getDailyStationName();
					
					date1 = LocalDate.of(Integer.parseInt(givenDate[2]), Integer.parseInt(givenDate[0]), Integer.parseInt(givenDate[1]));
					date2 = LocalDate.of(Integer.parseInt(currDate[2]), Integer.parseInt(currDate[0]), Integer.parseInt(currDate[1]));
					currLessThan = date2.isBefore(date1);
					
					if(currStation.equals(station) && aDate.equals(date)) {
						reportWasFound = true;
						foundReport = this.d.get(c);
					}
					c++;
				}
			}
			//Linear search backward
			else {
				while(!currLessThan && c >= startIdx) {
					givenDate = date.split("/");
					aDate = this.d.get(c).getData();
					currDate = aDate.split("/");
					currStation = this.d.get(c).getDailyStationName();
					
					date1 = LocalDate.of(Integer.parseInt(givenDate[2]), Integer.parseInt(givenDate[0]), Integer.parseInt(givenDate[1]));
					date2 = LocalDate.of(Integer.parseInt(currDate[2]), Integer.parseInt(currDate[0]), Integer.parseInt(currDate[1]));
					currLessThan = date2.isBefore(date1);
					
					if(currStation.equals(station) && aDate.equals(date)) {
						reportWasFound = true;
						foundReport = this.d.get(c);
					}
					c--;
				}
			}
		
		}	
		return foundReport;
	}
	
	/**
	 * @author: Carlos Rodriguez
	 * Uses binary search to find specific station and
	 * linear search in matched station to retrieve a 
	 * specific report on a specific month and year.
	 * 
	 * @param station: the desired station of the report.
	 * @param month: the desired month of the report.
	 * @param yearL the desired year of the report.
	 * @return: a MonthlyReport matching the criteria, null
	 * if no matching element was found. */
	
	public MonthlyReport getSpecificMonthlyReport(String station, String month, String year) {
		MonthlyReport foundReport = null;
		String convertedMonth = "";
		//Converting written month to number
		switch(month) {
			case "JANUARY":
				convertedMonth = "1";
				break;
			case "FEBRUARY":
				convertedMonth = "2";
				break;
			case "MARCH":
				convertedMonth = "3";
				break;
			case "APRIL":
				convertedMonth = "4";
				break;
			case "MAY":
				convertedMonth = "5";
				break;
			case "JUNE":
				convertedMonth = "6";
				break;
			case "JULY":
				convertedMonth = "7";
				break;
			case "AUGUST":
				convertedMonth = "8";
				break;
			case "SEPTEMBER":
				convertedMonth = "9";
				break;
			case "OCTOBER":
				convertedMonth = "10";
				break;
			case "NOVEMBER":
				convertedMonth = "11";
				break;
			case "DECEMBER":
				convertedMonth = "12";
				break;
			default:
				convertedMonth = null;
				break;
		}
		//If a valid month was converted
		if(convertedMonth != null) {
			boolean matchingStation = false;
			boolean reportWasFound = false;
			int startIdx = 0;
			int endIdx = this.m.size() - 1;
			int middleIdx = 0;
			String currStation = "";
			String aDate = "";
			//Binary Search for matching station
			while((startIdx <= endIdx) && (!reportWasFound)
					&& (!matchingStation)) {
				middleIdx = startIdx + (endIdx - startIdx) / 2;
				currStation = this.m.get(middleIdx).getStationName();
				
				//Found matching station
				if(currStation.equals(station)) {
					matchingStation = true;
				}
				else {
					//Binary search choice: to left or to right of list
					int currIdx = 0;
					while(((currIdx < currStation.length()) && (currIdx < station.length()))
							&& (currStation.charAt(currIdx) == station.charAt(currIdx))) {
						currIdx++;
					}
					if(station.charAt(currIdx) > currStation.charAt(currIdx)) {
						startIdx = middleIdx + 1; }
					else {
						endIdx = middleIdx - 1; }
				}
			}
			//Once a matching station has been found
			if(matchingStation) {
				int c = middleIdx;
				String[] currDate = this.m.get(c).getDate().trim().split("/");
				//Match the year
				while((!currDate[2].equals(year))) {
					currDate = this.m.get(c).getDate().split("/");
					//Linear forward year
					if(Integer.parseInt(currDate[2]) < Integer.parseInt(year)) {
						c++;
					}
					//Linear backward year
					if(Integer.parseInt(currDate[2]) > Integer.parseInt(year)) {
						c--;
					}
				}
				//Match the month
				while((!currDate[0].equals(convertedMonth) && !reportWasFound)
						&& (c >= startIdx && c <= endIdx)) {
					currDate = this.m.get(c).getDate().trim().split("/");
					//Linear forward month
					if(Integer.parseInt(currDate[0]) < Integer.parseInt(convertedMonth)) {
						c++;
					}
					//Linear backward month
					if(Integer.parseInt(currDate[0]) > Integer.parseInt(convertedMonth)) {
						c--;
					}
					//Matching month
					if(currDate[0].equals(convertedMonth) && currDate[2].equals(year)) {
						foundReport = this.m.get(c);
						reportWasFound = true;
					}
				}
			}
		}
		return foundReport;
	}
		
	/**
	 * @author: Carlos Rodriguez
	 * 
	 * Linearly searches for all available stations.
	 * @return = List of String type with the names. */
	public List<String> allStationsNames(){
		List<String> allNames = new ArrayList<String>();
		allNames.add(this.m.get(0).getStationName());
 		for(int i=1; i<this.m.size(); i++) {
 			if(!allNames.contains(this.m.get(i).getStationName())) {
 				allNames.add(this.m.get(i).getStationName());
 			}
		}
		return Collections.unmodifiableList(allNames);
	}
	
	/**
	 * @author: Carlos Rodriguez
	 * 
	 * Linearly searches for all available years.
	 * @return = List of String type with the years. */
	public List<String> allAvailableYears(){
		List<String> allYears = new ArrayList<String>();
		String[] split = this.m.get(0).getDate().split("/"); 
		allYears.add(split[2]);
 		for(int i=1; i<this.m.size(); i++) {
 			split = this.m.get(i).getDate().split("/");
 			if(!allYears.contains(split[2])) {
 				allYears.add(split[2]);
 			}
		}
		return Collections.unmodifiableList(allYears);
	}
}
