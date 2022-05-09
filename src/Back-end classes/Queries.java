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
    }


}
