import java.util.ArrayList;

/**
 * Class which will be used in the controller class of every UI
 * element for retrieving only relevant data
 * @author Jovan Rodriguez
 */
public class Queries {
    private Storage store; //Storage object for accessing weather data
    private ArrayList<MonthlyReport> monthly;
    private ArrayList<DailyReport> daily;

    //Default constructor for Queries class
    public Queries(){
        store = new Storage();
        monthly = store.getMonthlyList();
        daily = store.getDailyList();

        daily.add(new DailyReport("Boston", "1/1/2022", "whatever", 10, 10, 10));
    }

    /** PROCEDURE:
     * -Sort dailylist by station
     * -Binary search middle index
     * -Return if middle index equals
     * -Else evaluate charsAt(i)
     * -if matching cities but
     * -different dates compare by date
     * -if <, ignore leftSide if >, ignore rightSide
     * @author: Carlos Rodriguez
     * Uses binary search to retrieve a specific report
     * on a specific date.
     * @param station: the desired station of the report.
     * @param date: the desired date of the report.
     *
     @return: a DailyReport matching the criteria, null
      * if no matching element was found.
      */
    public DailyReport getSpecificDailyReport (String station, String date) {
        //Sort daily list by stations (alphabetically)
        store.sortByDate(1);
        DailyReport foundReport = null;

        int startingIdx = 0;
        int endingIdx = this.daily.size()-1;

        while(startingIdx <= endingIdx){
            //Find the list's midpoint

            int middleIdx = startingIdx + (endingIdx - startingIdx) / 2;
            System.out.println(middleIdx);
            String currStation = this.daily.get(middleIdx).getStationName();
            String currDate = this.daily.get(middleIdx).getData();
            if(currStation.equals(station) && currDate.equals (date)) {
                foundReport = this.daily.get(middleIdx);
                return foundReport;
            }
            int currIdx = 0;
            while(((currIdx < currStation.length()) && (currIdx < station.length()))
                    && (currStation.charAt(currIdx) == station.charAt (currIdx))){
                currIdx++;
            }
            if(currStation.equals (station)) {
                String[] foundDate = currDate.split("/");
                String[] givenDate = date.split("/");
                if (Integer.parseInt(foundDate[1]) < Integer.parseInt(givenDate[1])) {
                    startingIdx = middleIdx + 1;
                }
                else {
                    endingIdx = middleIdx - 1;
                }
            }
            else {
                if (currStation.charAt(currIdx) < station.charAt(currIdx)) {
                    startingIdx = middleIdx + 1;
                }
                else {
                    endingIdx = middleIdx - 1;
                }
            }
        }
        return foundReport;
    }

    /** PROCEDURE:
     * -Sort monthlylist by station
     * -Binary search middle index
     * -Return if middle index equals
     * -Else evaluate charsAt(i)
     * -if matching cities but
     * -different dates compare by date
     * -if <, ignore leftSide if >, ignore rightSide
     * @author: Carlos Rodriguez
     * Uses binary search to retrieve a specific report
     * on a specific date.
     * @param station: the desired station of the report.
     * @param month: the desired month of the report.
     *
     @return: a DailyReport matching the criteria, null
      * if no matching element was found.
     */
    public MonthlyReport getSpecificMonthlyReport (String station, String month) {
        //Sort daily list by stations (alphabetically)
        store.sortByDate(2);
        MonthlyReport foundReport = null;

        int startingIdx = 0;
        int endingIdx = this.monthly.size();

        while(startingIdx <= endingIdx){
            //Find the list's midpoint

            int middleIdx = startingIdx + (endingIdx - startingIdx) / 2;
            System.out.println(middleIdx);
            String currStation=this.monthly.get(middleIdx).getStationName();
            String currMonth = this.monthly.get(middleIdx).getData();
            if(currStation.equals(station) && currMonth.equals (month)) {
                foundReport = this.monthly.get(middleIdx);
                return foundReport;
            }
            int currIdx = 0;
            while(((currIdx < currStation.length()) && (currIdx < station.length()))
                    && (currStation.charAt(currIdx) == station.charAt (currIdx))){
                currIdx++;
            }
            if(currStation.equals (station)) {
                String[] foundDate = currMonth.split("/");
                String[] givenDate = month.split("/");
                if (Integer.parseInt(foundDate[1]) < Integer.parseInt(givenDate[1])) {
                    startingIdx = middleIdx + 1;
                }
                else {
                    endingIdx = middleIdx - 1;
                }
            }
            else {
                if (currStation.charAt(currIdx) < station.charAt(currIdx)) {
                    startingIdx = middleIdx + 1;
                }
                else {
                    endingIdx = middleIdx - 1;
                }
            }
        }
        return foundReport;
    }

    public static void main(String[] args){
        Queries aQ = new Queries();
        DailyReport aRep = aQ.getSpecificDailyReport ("Boston", "2022/30/1");
        System.out.println(aRep.toString());
    }
}


