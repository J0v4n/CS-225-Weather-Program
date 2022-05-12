import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queries {

    private Storage storage = new Storage();
    private List<DailyReport> d;
    private List<MonthlyReport> m;
    public static  List<String> w=SceneController.words;

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
            System.out.println(currStation);

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
     * */

    public List<MonthlyReport> getSpecificMonthlyReports(String month) {
        List<MonthlyReport> foundReports = new ArrayList<MonthlyReport>();
        for(int i=0; i<this.m.size(); i++) {
            String[] currDate = this.m.get(i).getDate().split("/");
            if(currDate[0].equals(month)) {
                foundReports.add(this.m.get(i));
            }
        }
        return Collections.unmodifiableList(foundReports);
    }

    public List<List<String>> parseReports(String month, int isDaily){
        List<List<String>> outerList = null;
        if(isDaily == 1) {
            List<MonthlyReport> aList = getSpecificMonthlyReports(month);
            List<String> innerList = new ArrayList<String>();
            outerList = new ArrayList<List<String>>();
            for(int i=0; i<aList.size(); i++) {
                innerList.add(aList.get(i).getStationName());
                innerList.add(aList.get(i).getDate());
                innerList.add(Double.toString(aList.get(i).getmonthlymonthlyMinTemp()));
                innerList.add(Double.toString(aList.get(i).getmonthlymonthlyAvgTemp()));
                innerList.add(Double.toString(aList.get(i).getmonthlymonthlyMaxTemp()));
                innerList.add(Double.toString(aList.get(i).getmonthlymonthlyTotalPrecipitation()));
            }
            outerList.add(innerList);
        }
        return outerList;
    }

    /**
     * @author: Carlos Rodriguez
     * modified by Yuliia Synytska
     * Linearly searches for all available stations.
     * @return = List of String type with the names.
     */
    public List<String> allStationsNames(){
        List<String> allNames = w;
        allNames.add(this.m.get(0).getStationName());
        for(int i=1; i<this.m.size(); i++) {
            if(!allNames.contains(this.m.get(i).getStationName())) {
                allNames.add(this.m.get(i).getStationName());
            }
        }
        return Collections.unmodifiableList(allNames);
    }

    //For testing purposes
    public static void main(String[] args) {

        // DailyReport aRep = aQ.getSpecificDailyReport("LAWRENCE MUNICIPAL AIRPORT", "4/2/2022");
        //System.out.println(aRep.toString());
        System.out.print(w);

    }
}
