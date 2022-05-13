import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
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

        avgWindDisplay.setStyle("-fx-font-size: 2.5em;");
        maxWindDisplay.setStyle("-fx-font-size: 2.5em;");
        precipitationDisplay.setStyle("-fx-font-size: 2.5em;");

        //testDisplay(); //Used to test the display

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
    public String getDate(){
        //System.out.println(dailyDateSelector.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))); used to test what the output was for the date
        return dailyDateSelector.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    //Used to set the display for the average wind
    public void setAvgWindDisplay(Double avgWind){
        avgWindDisplay.setText("");
        avgWindDisplay.setText(Double.toString(avgWind));
    }

    //Used to set the max wind display
    public void setMaxWindDisplay(Double maxWind){
        maxWindDisplay.setText("");
        maxWindDisplay.setText(Double.toString(maxWind));
    }

    //used to set the precipitation display
    public void setPrecipitationDisplay(Double precipitation){
        precipitationDisplay.setText("");
        precipitationDisplay.setText(Double.toString(precipitation));
    }

    //method to test each displays to make sure they are working properly
    public void testDisplay(){
        setAvgWindDisplay(10.0);
        setMaxWindDisplay(10.1);
        setPrecipitationDisplay(10.2);
    }






}