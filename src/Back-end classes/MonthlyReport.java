import java.util.Objects;
/**
 * @author David
 */
public class MonthlyReport implements IReport {
public class MonthlyReport {
    private String data;
    private String stationName;
    private String stationId;
    private double avgTemp;
    private double maxTemp;
    private double minTemp;
    private double totalPrecipitation;
    public MonthlyReport(String info, String sN, String sID,double aTemp,double highTemp,double lowTemp,double tPrecipitation){
        this.data=info;
        this.stationName=sN;
        this.stationId=sID;
        this.avgTemp=aTemp;
        this.maxTemp=highTemp;
        this.minTemp=lowTemp;
        this.totalPrecipitation=tPrecipitation;
    }
    public MonthlyReport(){
        data="";
        stationName="";
        stationId="";
        avgTemp=0;
        maxTemp=0;
        minTemp=0;
        totalPrecipitation=0;

    }
    public String getData(){
        return data;
    }
    public String getStationName(){
        return stationName;
    }
    public String getStationId(){
        return stationId;
    }
    public double getAvgTemp() {
        return avgTemp;
    }
    public double getMaxTemp() {
        return maxTemp;
    }
    public double getMinTemp() {
        return minTemp;
    }
    public double getTotalPrecipitation() {
        return totalPrecipitation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyReport report = (MonthlyReport) o;
        return Double.compare(report.avgTemp, avgTemp) == 0 && Double.compare(report.maxTemp, maxTemp) == 0 && Double.compare(report.minTemp, minTemp) == 0 && Double.compare(report.totalPrecipitation, totalPrecipitation) == 0 && Objects.equals(data, report.data) && Objects.equals(stationName, report.stationName) && Objects.equals(stationId, report.stationId);
    }
    @Override
    public String toString(){
        return ""+data+""+minTemp+""+maxTemp+""+stationId+""+stationName+""+totalPrecipitation+"";
    }
}
