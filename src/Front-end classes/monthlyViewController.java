import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

//Controller class for the monthlyView.fxml -Justin Lamberson
public class monthlyViewController implements Initializable {

    private String currentUnit = "FAHRENHEIT";

    private String years[];

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

    @FXML
    private ChoiceBox yearSelector;

    TemperatureConverter tempConverter = new TemperatureConverter();

    public monthlyViewController(){
        years = new String[]{"2020", "2021", "2022"};
    }

    public monthlyViewController(String years[]){
        this.years = years;
    }


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


        //Adds an action that auto converts the temperature to the unit
        temperatureSelector.setOnAction((event -> {
            String conversion = getTempType();
            if(currentUnit.equals("FAHRENHEIT")){
                if(conversion.equals("KELVIN")){
                    Double minTemp = Double.parseDouble(minTempDisplay.getText());
                    Double maxTemp = Double.parseDouble(maxTempDisplay.getText());
                    Double avgTemp = Double.parseDouble(avgTempDisplay.getText());

                    minTemp = tempConverter.fahrenheitToKelvin(minTemp);
                    maxTemp = tempConverter.fahrenheitToKelvin(maxTemp);
                    avgTemp = tempConverter.fahrenheitToKelvin(avgTemp);

                    setAvgTempDisplay(avgTemp);
                    setMaxTempDisplay(maxTemp);
                    setMinTempDisplay(minTemp);

                    currentUnit = getTempType();


                } else if(conversion.equals("CELSIUS")){
                    Double minTemp = Double.parseDouble(minTempDisplay.getText());
                    Double maxTemp = Double.parseDouble(maxTempDisplay.getText());
                    Double avgTemp = Double.parseDouble(avgTempDisplay.getText());

                    minTemp = tempConverter.fahrenheitToCelsius(minTemp);
                    maxTemp = tempConverter.fahrenheitToCelsius(maxTemp);
                    avgTemp = tempConverter.fahrenheitToCelsius(avgTemp);

                    setAvgTempDisplay(avgTemp);
                    setMaxTempDisplay(maxTemp);
                    setMinTempDisplay(minTemp);

                    currentUnit = getTempType();


                }
            } else if(currentUnit.equals("KELVIN")){
                if(conversion.equals("FAHRENHEIT")){
                    Double minTemp = Double.parseDouble(minTempDisplay.getText());
                    Double maxTemp = Double.parseDouble(maxTempDisplay.getText());
                    Double avgTemp = Double.parseDouble(avgTempDisplay.getText());

                    minTemp = tempConverter.kelvinToFahrenheit(minTemp);
                    maxTemp = tempConverter.kelvinToFahrenheit(maxTemp);
                    avgTemp = tempConverter.kelvinToFahrenheit(avgTemp);

                    setAvgTempDisplay(avgTemp);
                    setMaxTempDisplay(maxTemp);
                    setMinTempDisplay(minTemp);

                    currentUnit = getTempType();
                } else if(conversion.equals("CELSIUS")){
                    Double minTemp = Double.parseDouble(minTempDisplay.getText());
                    Double maxTemp = Double.parseDouble(maxTempDisplay.getText());
                    Double avgTemp = Double.parseDouble(avgTempDisplay.getText());

                    minTemp = tempConverter.kelvinToCelsius(minTemp);
                    maxTemp = tempConverter.kelvinToCelsius(maxTemp);
                    avgTemp = tempConverter.kelvinToCelsius(avgTemp);

                    setAvgTempDisplay(avgTemp);
                    setMaxTempDisplay(maxTemp);
                    setMinTempDisplay(minTemp);

                    currentUnit = getTempType();
                }
            } else if(currentUnit.equals("CELSIUS")){
                if(conversion.equals("FAHRENHEIT")){
                    Double minTemp = Double.parseDouble(minTempDisplay.getText());
                    Double maxTemp = Double.parseDouble(maxTempDisplay.getText());
                    Double avgTemp = Double.parseDouble(avgTempDisplay.getText());

                    minTemp = tempConverter.celsiusToFahrenheit(minTemp);
                    maxTemp = tempConverter.celsiusToFahrenheit(maxTemp);
                    avgTemp = tempConverter.celsiusToFahrenheit(avgTemp);

                    setAvgTempDisplay(avgTemp);
                    setMaxTempDisplay(maxTemp);
                    setMinTempDisplay(minTemp);

                    currentUnit = getTempType();
                } else if(conversion.equals("KELVIN")){
                    Double minTemp = Double.parseDouble(minTempDisplay.getText());
                    Double maxTemp = Double.parseDouble(maxTempDisplay.getText());
                    Double avgTemp = Double.parseDouble(avgTempDisplay.getText());

                    minTemp = tempConverter.celsiusToKelvin(minTemp);
                    maxTemp = tempConverter.celsiusToKelvin(maxTemp);
                    avgTemp = tempConverter.celsiusToKelvin(avgTemp);

                    setAvgTempDisplay(avgTemp);
                    setMaxTempDisplay(maxTemp);
                    setMinTempDisplay(minTemp);

                    currentUnit = getTempType();
                }
            }

        }) );


        yearSelectorInitialization(years);

        //Used for testing purposes
        //testDisplays();
        resetTempType();


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
        setMaxTempDisplay(32.0);
        setMinTempDisplay(32.0);
        setAvgTempDisplay(32.0);
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

    public void yearSelectorInitialization(String years[]){
        String a[] = years;
        for(int i = 0; i < a.length; i++){
            yearSelector.getItems().add(a[i]);
        }
    }

    //methods to get the choiceBox selection
    public String getMonth(){
        return monthSelector.getSelectionModel().getSelectedItem().toString().toUpperCase();
    }

    public String getTempType(){
        return temperatureSelector.getSelectionModel().getSelectedItem().toString().toUpperCase();
    }

    public String getYear(){
        return yearSelector.getSelectionModel().getSelectedItem().toString();
    }

    //Used to reset the temp type when the data is changed for the month
    public void resetTempType(){
        currentUnit = "FAHRENHEIT";
        temperatureSelector.getSelectionModel().selectFirst();
    }









}