import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.time.chrono.Chronology;
import java.util.ResourceBundle;

//Controller class for the daily view section of the GUI -Justin Lamberson
public class dailyViewController implements Initializable {

    @FXML
    private DatePicker dailyDateSelector;

    @FXML
    private TextArea avgWindDisplay;

    @FXML
    private TextArea maxWindDisplay;

    @FXML
    private TextArea precipitationDisplay;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Sets all text areas to not be able to be edited when started
        avgWindDisplay.setEditable(false);
        maxWindDisplay.setEditable(false);
        precipitationDisplay.setEditable(false);

    }

    //Used to set the display for the average wind
    public void setAvgWindDisplay(String avgWind){
        avgWindDisplay.setText("");
        avgWindDisplay.setText(avgWind);
    }

    //Used to set the max wind display
    public void setMaxWindDisplay(String maxWind){
        maxWindDisplay.setText("");
        maxWindDisplay.setText(maxWind);
    }

    //used to set the precipitation display
    public void setPrecipitationDisplay(String precipitation){
        precipitationDisplay.setText("");
        precipitationDisplay.setText(precipitation);
    }

    //used to get the date selected by the user
    public Chronology getDate(){
        return dailyDateSelector.getChronology();
    }






}
