import java.util.Objects;
/**
 * @author David Smirnov
 */
public class MonthlyReport implements IReport {
    private String date;
    private String stationName;
    private double monthlyAvgTemp;
    private double monthlyMaxTemp;
    private double monthlyMinTemp;
    private double monthlyTotalPrecipitation;
    public MonthlyReport(String day, String sN, double monthlyaTemp,double monthlyhighTemp,double monthlylowTemp,double monthlytPrecipitation){
        this.date=day;
        this.stationName=sN;
        this.monthlyAvgTemp=monthlyaTemp;
        this.monthlyMaxTemp=monthlyhighTemp;
        this.monthlyMinTemp=monthlylowTemp;
        this.monthlyTotalPrecipitation=monthlytPrecipitation;
    }
    public MonthlyReport(){
        date="";
        stationName="";
        monthlyAvgTemp=0;
        monthlyMaxTemp=0;
        monthlyMinTemp=0;
        monthlyTotalPrecipitation=0;

    }
    public String getDate(){return date; }
    public String getStationName(){
        return stationName;
    }
    public double getmonthlymonthlyAvgTemp() {
        return monthlyAvgTemp;
    }
    public double getmonthlymonthlyMaxTemp() {
        return monthlyMaxTemp;
    }
    public double getmonthlymonthlyMinTemp() {
        return monthlyMinTemp;
    }
    public double getmonthlymonthlyTotalPrecipitation() {
        return monthlyTotalPrecipitation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyReport report = (MonthlyReport) o;
        return Double.compare(report.monthlyAvgTemp, monthlyAvgTemp) == 0 && Double.compare(report.monthlyMaxTemp, monthlyMaxTemp) == 0 && Double.compare(report.monthlyMinTemp, monthlyMinTemp) == 0 && Double.compare(report.monthlyTotalPrecipitation, monthlyTotalPrecipitation) == 0 && Objects.equals(date, report.date) && Objects.equals(stationName, report.stationName);
    }
    @Override
    public String toString(){
        return ""+date+""+monthlyMinTemp+""+monthlyMaxTemp+""+stationName+""+monthlyTotalPrecipitation+"";
    }
}
