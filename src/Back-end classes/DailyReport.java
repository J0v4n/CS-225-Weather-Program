import java.util.Objects;

/**
 * @author David Smirnov
 */
public class DailyReport implements IReport {
    private String data;
    private String stationName;
    private String stationId;
    private double AverageWindSpeed;
    private double MaxWindSpeed;
    private double DailyPrecipitation;
    public DailyReport(String info,String sN,String sID,double aWS, double mWS, double dPersipitation){
        this.data=info;
        this.stationName=sN;
        this.stationId=sID;
        this.AverageWindSpeed=aWS;
        this.MaxWindSpeed=mWS;
        this.DailyPrecipitation=dPersipitation;
    }
    public DailyReport(){
        data="";
        stationName="";
        stationId="";
        AverageWindSpeed=0;
        MaxWindSpeed=0;
        DailyPrecipitation=0;
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
    private double getAverageWindSpeed(){return AverageWindSpeed;}
    private double getMaxWindSpeed(){return MaxWindSpeed;}
    private double getDailyPrecipitation(){return DailyPrecipitation;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyReport that = (DailyReport) o;
        return Double.compare(that.AverageWindSpeed, AverageWindSpeed) == 0 && Double.compare(that.MaxWindSpeed, MaxWindSpeed) == 0 && Double.compare(that.DailyPrecipitation, DailyPrecipitation) == 0 && Objects.equals(data, that.data) && Objects.equals(stationName, that.stationName) && Objects.equals(stationId, that.stationId);
    }

    @Override
    public String toString(){
        return ""+data+""+""+stationId+""+stationName+""+AverageWindSpeed+""+MaxWindSpeed+""+DailyPrecipitation;
    }
}
