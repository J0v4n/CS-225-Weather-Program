import java.util.Objects;

/**
 * @author David Smirnov
 */
public class DailyReport {
    private double AverageWindSpeed;
    private double MaxWindSpeed;
    private double DailyPrecipitation;
    public DailyReport(double aWS, double mWS, double dPersipitation){
        this.AverageWindSpeed=aWS;
        this.MaxWindSpeed=mWS;
        this.DailyPrecipitation=dPersipitation;
    }
    public DailyReport(){
        AverageWindSpeed=0;
        MaxWindSpeed=0;
        DailyPrecipitation=0;
    }
    private double getAverageWindSpeed(){
        return AverageWindSpeed;
    }
    private double getMaxWindSpeed(){
        return MaxWindSpeed;
    }
    private double getDailyPrecipitation(){
        return DailyPrecipitation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyReport that = (DailyReport) o;
        return Double.compare(that.AverageWindSpeed, AverageWindSpeed) == 0 && Double.compare(that.MaxWindSpeed, MaxWindSpeed) == 0 && Double.compare(that.DailyPrecipitation, DailyPrecipitation) == 0;
    }
    @Override
    public String toString(){
        return ""+AverageWindSpeed+""+MaxWindSpeed+""+DailyPrecipitation;
    }
}
