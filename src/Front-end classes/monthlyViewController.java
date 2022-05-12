import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

//Controller class for the monthlyView.fxml -Justin Lamberson
public class monthlyViewController implements Initializable {

    @FXML
    private TextArea avgTempDisplay;

    @FXML
    private TextArea maxTempDisplay;

    @FXML
    private TextArea minTempDisplay;

    @FXML
    private TextArea precipitationDisplay;

    @FXML
    private ChoiceBox monthSelector;

    @FXML
    private ChoiceBox temperatureSelector;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avgTempDisplay.setEditable(false);
        maxTempDisplay.setEditable(false);
        minTempDisplay.setEditable(false);
        precipitationDisplay.setEditable(false);

        avgTempDisplay.setStyle("-fx-font-size: 2.5em;");
        maxTempDisplay.setStyle("-fx-font-size: 2.5em;");
        minTempDisplay.setStyle("-fx-font-size: 2.5em;");
        precipitationDisplay.setStyle("-fx-font-size: 2.5em;");

        monthBoxInitialization();
        temperatureBoxInitialization();




    }


    //Setter methods to change the text in the text areas
    public void setPrecipitationDisplay(Double precipitation){
        precipitationDisplay.setText("");
        precipitationDisplay.setText(Double.toString(precipitation));
    }

    public void setAvgTempDisplay(Double avgTemp){
        avgTempDisplay.setText("");
        avgTempDisplay.setText(Double.toString(avgTemp));
    }

    public void setMaxTempDisplay(Double maxTemp){
        maxTempDisplay.setText("");
        maxTempDisplay.setText(Double.toString(maxTemp));
    }

    public void setMinTempDisplay(Double minTemp){
        minTempDisplay.setText("");
        minTempDisplay.setText(Double.toString(minTemp));
    }

    //tester method to see if the displays were working
    public void testDisplays(){
        setPrecipitationDisplay(10.0);
        setMaxTempDisplay(10.1);
        setMinTempDisplay(10.2);
        setAvgTempDisplay(10.3);
    }


    //used to set up the choiceBox for months
    public void monthBoxInitialization(){
        String list[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for(int i = 0; i < list.length; i++){
            monthSelector.getItems().add(list[i]);
        }
    }

    public void temperatureBoxInitialization(){
        String list[] = {"Fahrenheit", "Kelvin", "Celsius"};
        for(int i = 0; i < list.length; i++){
            temperatureSelector.getItems().add(list[i]);
        }
    }

    //methods to get the choiceBox selection
    public String getMonth(){
        return monthSelector.getSelectionModel().getSelectedItem().toString().toUpperCase();
    }

    public String getTempType(){
        return temperatureSelector.getSelectionModel().getSelectedItem().toString().toUpperCase();
    }









}
