import java.util.Objects;

/**
 * @author David Smirnov
 */
public class DailyReport {
    private String date;
    private String dailyStationName;
    private double dailyAverageWindSpeed;
    private double dailyMaxWindSpeed;
    private double dailyPrecipitation;
    public DailyReport(String day,String sN,double DailyaWS, double DailymWS, double dPersipitation){
        this.date=day;
        this.dailyStationName=sN;
        this.dailyAverageWindSpeed=DailyaWS;
        this.dailyMaxWindSpeed=DailymWS;
        this.dailyPrecipitation=dPersipitation;
    }
    public DailyReport(){
        date="";
        dailyStationName="";
        dailyAverageWindSpeed=0;
        dailyMaxWindSpeed=0;
        dailyPrecipitation=0;
    }
    public String getData(){
        return date;
    }
    public String getDailyStationName(){
        return dailyStationName;
    }
    private double getDailyAverageWindSpeed(){return dailyAverageWindSpeed;}
    private double getDailyMaxWindSpeed(){return dailyMaxWindSpeed;}
    private double getDailyPrecipitation(){return dailyPrecipitation;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyReport that = (DailyReport) o;
        return Double.compare(that.dailyAverageWindSpeed, dailyAverageWindSpeed) == 0 && Double.compare(that.dailyMaxWindSpeed, dailyMaxWindSpeed) == 0 && Double.compare(that.dailyPrecipitation, dailyPrecipitation) == 0 && Objects.equals(date, that.date) && Objects.equals(dailyStationName, that.dailyStationName);
    }

    @Override
    public String toString(){
        return ""+date+""+""+dailyStationName+""+dailyAverageWindSpeed+""+dailyMaxWindSpeed+""+dailyPrecipitation;
    }
}