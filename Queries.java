import java.time.LocalDate;
import java.util.List;

public class Queries {
   	
	Storage storage = new Storage();
	private List<DailyReport> d;
	private List<MonthlyReport> m;
	
	//Constructor
	public Queries(){
		this.d = this.storage.getDailyList();
		this.m = this.storage.getMonthlyList();
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
		int startingIdx = 0;
		int endingIdx = d.size() - 1;
		
		while(startingIdx <= endingIdx) {
			//Find the list's midpoint
			int middleIdx = startingIdx + (endingIdx - startingIdx) / 2;
			String currStation = d.get(middleIdx).getDailyStationName();
			String currDate = d.get(middleIdx).getData();
			//System.out.println(d.get(middleIdx).toString());
			//Matching specific element
			if((station.equals(currStation)) && (date.equals(currDate))) {
				foundReport = d.get(middleIdx);
				return foundReport;
			}
			else {
				//If station was found
				if(currStation.equals(station)) {
					String[] given = date.split("/");
					String[] curr = currDate.split("/"); 
					int[] givenI = new int[given.length];
					int[] currI = new int[curr.length];
					
					for(int i=0; i<given.length; i++) {
						givenI[i] = Integer.parseInt(given[i]);
						currI[i] = Integer.parseInt(curr[i]);
					}
					
					//given
					LocalDate date1 = LocalDate.of(givenI[2], givenI[0], givenI[1]);
					//found
					LocalDate date2 = LocalDate.of(currI[2], currI[0], currI[1]);
					boolean givenIsBefore = date1.isBefore(date2);
					boolean elementFound = false;
					int find = middleIdx;
					//Linear search
					//backtrack
					if(givenIsBefore) {
						while(find > startingIdx && !elementFound) {
							find--;
							currDate = d.get(find).getData();
							if(currDate.equals(date)) {
								elementFound = true;
								foundReport = this.d.get(find);
								return foundReport;
							}
						}
					}
					//front
					else {
						while(find < endingIdx && !elementFound) {
							find++;
							currDate = d.get(find).getData();
							if(currDate.equals(date)) {
								elementFound = true;
								foundReport = this.d.get(find);
								return foundReport;
							}
						}
					}
				}
				//Not Matching
				else {
					int c = 0;
					while(c < currStation.length() && c < station.length()
							&& currStation.charAt(c) == station.charAt(c)) {
						c++;
					}
					//Lower End
					if(currStation.charAt(c) > station.charAt(c)) {
						endingIdx = middleIdx - 1;
					}
					//Higher
					else {
						startingIdx = middleIdx + 1;
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Queries aQ = new Queries();
		DailyReport aRep = aQ.getSpecificDailyReport("BOSTON", "1/31/2022");
		System.out.println(aRep.toString());
	}
	
}
