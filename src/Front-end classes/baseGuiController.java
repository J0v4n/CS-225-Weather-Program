/**
 * @author Alland Timas
 *This class controls the overall weatherUI
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class baseGuiController implements Initializable {
	//Alland Timas create variable references to fxml components
    @FXML
    private Label station_Name;

    @FXML
    private SplitMenuButton sortedBy_DropDown;

    @FXML
    private SplitMenuButton unit_Menu, year_Menu;

    @FXML
    private Separator separator_Dash;
    
    @FXML
    private MenuItem year2021;
    
    @FXML
    private MenuItem year2022;

    @FXML
    private ScrollPane stationName_Display;

    @FXML
    private ScrollBar stationName_ScrollBar;

    @FXML
    private TextField station_SearchBar;

    @FXML
    private Pane card_Pane, map_Pane;

    @FXML
    private ToggleButton nightMode_Toggle;

    @FXML
    private VBox parentPane;

    //christian
    @FXML
    private MapController mapController;

    @FXML
    private StationController stationController;

    //
    //alland timas
    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane scroller;
    
    private TemperatureConverter converter;

    @FXML
    private DatePicker dateSelector;

    @FXML
    private TextArea avgTempDisplay, maxTempDisplay, minTempDisplay, precipitationDisplayMonthly, avgWindDisplay, maxWindDisplay, precipitationDisplayDaily;

    ListView<String> list = new ListView<>();

    private boolean isLightMode = true;

    TemperatureConverter tempConverter = new TemperatureConverter();
    
    //used to verify what the current unit would be
    private String currentUnit;    
    /**
     * @author: Carlos Rodriguez
     * Global reference of type Queries */
    private Queries queries = new Queries();

    //Modified by Carlos Rodriguez
    List<String> words = this.queries.allStationsNames();
    private String selectedMonth, selectedStation = "";
    private String selectedYear = "";

    //Methods from stationController added by Jovan
    
    //yuliia
    public void expSearch(ActionEvent event) throws Exception {
        list.getItems().clear();
        list.getItems().addAll(searchList(searchBar.getText(),words));
    }

    //christian
    public void expSet(String station) throws Exception {
        list.getItems().clear();
        searchBar.setText(station);
        list.getItems().addAll(searchList(station,words));
    }

    public List<String> searchList(String searchWords, List<String> listOfStrings) throws Exception{

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stationController = new StationController();
        mapController = new MapController();
        this.converter = new TemperatureConverter();
        this.currentUnit = "";
        //Modified by Carlos Rodriguez

        //
        list.getItems().addAll(words);
        //this.stationName_Display.setContent(list);
        scroller.setContent(list);

        //modified by Justin Lamberson
        //default temp type is set
    }


    public baseGuiController(){
        stationController = new StationController();
        mapController = new MapController();
        station_SearchBar = new TextField();
        dateSelector = new DatePicker();
        
        this.avgTempDisplay = new TextArea();
        this.avgTempDisplay.setEditable(false);
        this.maxTempDisplay = new TextArea();
        this.maxTempDisplay.setEditable(false); 
        this.minTempDisplay = new TextArea();
        this.minTempDisplay.setEditable(false); 
        this.precipitationDisplayMonthly = new TextArea();
        this.precipitationDisplayMonthly.setEditable(false); 
        this.avgWindDisplay = new TextArea();
        this.avgWindDisplay.setEditable(false); 
        this.maxWindDisplay = new TextArea();
        this.maxWindDisplay.setEditable(false);
        this.precipitationDisplayDaily = new TextArea();
        this.precipitationDisplayDaily.setEditable(false);
    }
  //Created by alland timas, uses darkMode.css to swap between the light mode and dark mode.
    public void swapColorModes(ActionEvent event){
      isLightMode = !isLightMode;
       if(isLightMode){
        removeDarkMode();
       }
       else{
           setDarkMode();
       }
    }
    
    public void setDarkMode(){
        parentPane.getStylesheets().add("css/darkMode.css");
        nightMode_Toggle.setText("Light Mode");
    }
    public void removeDarkMode(){
        parentPane.getStylesheets().remove("css/darkMode.css");
        nightMode_Toggle.setText("Dark Mode");
    }

    public void stationSelected(ActionEvent event) throws Exception{
        selectedStation = ((RadioButton) event.getSource()).getText();
        List<String> fullStationName = searchList(selectedStation, words);
        this.station_Name.setText(fullStationName.get(0));
        selectedStation = fullStationName.get(0);
        expSet(selectedStation);
    }
 
    //SortedBy Split Menu month methods
    public void setMonthJan() {this.selectedMonth = "JANUARY";}
    public void setMonthFeb() {this.selectedMonth = "FEBRUARY";}
    public void setMonthMar() {this.selectedMonth = "MARCH";}
    public void setMonthApr() {this.selectedMonth = "APRIL";}
    public void setMonthMay() {this.selectedMonth = "MAY";}
    public void setMonthJun() {this.selectedMonth = "JUNE";}
    public void setMonthJul() {this.selectedMonth = "JULY";}
    public void setMonthAug() {this.selectedMonth = "AUGUST";}
    public void setMonthSep() {this.selectedMonth = "SEPTEMBER";}
    public void setMonthOct() {this.selectedMonth = "OCTOBER";}
    public void setMonthNov() {this.selectedMonth = "NOVEMBER";}
    public void setMonthDec() {this.selectedMonth = "DECEMBER";}
    
    @FXML
    public void setYear2021() {this.selectedYear = "2021";}
    
    @FXML
    public void setYear2022() {this.selectedYear = "2022";}
    
    //Temp setters
    public void setKelvin() {
    	String foundAvg = this.avgTempDisplay.getText();
    	String foundMaxTemp = this.maxTempDisplay.getText();
    	String foundMinTemp = this.minTempDisplay.getText();
    	if(!foundAvg.isEmpty() && !foundMaxTemp.isEmpty() && !foundMinTemp.isEmpty()) {
    		if(this.currentUnit.isEmpty() || this.currentUnit.equals("Fahrenheit")) {
        		this.avgTempDisplay.setText("" + this.converter.fahrenheitToKelvin(Double.parseDouble(foundAvg)));
        		this.maxTempDisplay.setText("" + this.converter.fahrenheitToKelvin(Double.parseDouble(foundMaxTemp)));
        		this.minTempDisplay.setText("" + this.converter.fahrenheitToKelvin(Double.parseDouble(foundMinTemp)));
        	}
    		if(this.currentUnit.equals("Celsius")) {
    			this.avgTempDisplay.setText("" + this.converter.celsiusToKelvin(Double.parseDouble(foundAvg)));
        		this.maxTempDisplay.setText("" + this.converter.celsiusToKelvin(Double.parseDouble(foundMaxTemp)));
        		this.minTempDisplay.setText("" + this.converter.celsiusToKelvin(Double.parseDouble(foundMinTemp)));
        	}
    	}
    	this.currentUnit = "Kelvin";
    }
    
    public void setCelsius() {
    	String foundAvg = this.avgTempDisplay.getText();
    	String foundMaxTemp = this.maxTempDisplay.getText();
    	String foundMinTemp = this.minTempDisplay.getText();
    	if(!foundAvg.isEmpty() && !foundMaxTemp.isEmpty() && !foundMinTemp.isEmpty()) {
    		if(this.currentUnit.equals("Fahrenheit")) {
        		this.avgTempDisplay.setText("" + this.converter.fahrenheitToCelsius(Double.parseDouble(foundAvg)));
        		this.maxTempDisplay.setText("" + this.converter.fahrenheitToCelsius(Double.parseDouble(foundMaxTemp)));
        		this.minTempDisplay.setText("" + this.converter.fahrenheitToCelsius(Double.parseDouble(foundMinTemp)));
        	}
    		if(this.currentUnit.equals("Kelvin")) {
    			this.avgTempDisplay.setText("" + this.converter.kelvinToCelsius(Double.parseDouble(foundAvg)));
        		this.maxTempDisplay.setText("" + this.converter.kelvinToCelsius(Double.parseDouble(foundMaxTemp)));
        		this.minTempDisplay.setText("" + this.converter.kelvinToCelsius(Double.parseDouble(foundMinTemp)));
        	}
    	}
    	this.currentUnit = "Celsius";
    }
    
    public void setFahrenheit() {
    	String foundAvg = this.avgTempDisplay.getText();
    	String foundMaxTemp = this.maxTempDisplay.getText();
    	String foundMinTemp = this.minTempDisplay.getText();
    	if(!foundAvg.isEmpty() && !foundMaxTemp.isEmpty() && !foundMinTemp.isEmpty()) {
    		if(this.currentUnit.equals("Celsius")) {
        		this.avgTempDisplay.setText("" + this.converter.celsiusToFahrenheit(Double.parseDouble(foundAvg)));
        		this.maxTempDisplay.setText("" + this.converter.celsiusToFahrenheit(Double.parseDouble(foundMaxTemp)));
        		this.minTempDisplay.setText("" + this.converter.celsiusToFahrenheit(Double.parseDouble(foundMinTemp)));
        	}
    		if(this.currentUnit.equals("Kelvin")) {
    			this.avgTempDisplay.setText("" + this.converter.kelvinToFahrenheit(Double.parseDouble(foundAvg)));
        		this.maxTempDisplay.setText("" + this.converter.kelvinToFahrenheit(Double.parseDouble(foundMaxTemp)));
        		this.minTempDisplay.setText("" + this.converter.kelvinToFahrenheit(Double.parseDouble(foundMinTemp)));
        	}
    	}
    	this.currentUnit = "Fahrenheit";
    }
    
    public void searchMonthlyReports(){
    	if(this.selectedMonth.isEmpty() && this.selectedStation.isEmpty()
    			&& this.selectedYear.isEmpty()) {
    		Alert warning = new Alert(AlertType.INFORMATION);
			warning.setContentText("Change your input, missing information");
			warning.show();
    	}
    	else {
    		MonthlyReport aRep = this.queries.getSpecificMonthlyReport(selectedStation, selectedMonth, selectedYear);
    		if(aRep == null) {
    			Alert warning = new Alert(AlertType.INFORMATION);
    			warning.setContentText("No report matched given criteria.");
    			warning.show();
    		}
    		else {
    			setAvgTempDisplay(aRep.getmonthlymonthlyAvgTemp());
    			setMaxTempDisplay(aRep.getmonthlymonthlyMaxTemp());
    			setMinTempDisplay(aRep.getmonthlymonthlyMinTemp());
    			setPrecipitationDisplayMonthly(aRep.getmonthlymonthlyTotalPrecipitation());
    		}
    	}
    }

    public void searchDailyReports(){
    	String[] givenDate = dateSelector.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).split("/");
    	if(givenDate[0].charAt(0) == '0') {
    		String anotherDate[] = givenDate[0].split("0");
    		for(int i=0; i<anotherDate.length; i++) {
    			givenDate[0] = anotherDate[i];
    		}
    	}
    	if(givenDate[1].charAt(0) == '0') {
    		String anotherDate[] = givenDate[1].split("0");
    		for(int i=0; i<anotherDate.length; i++) {
    			givenDate[1] = anotherDate[i];
    		}
    	}
    	String depured = "";
    	for(int i=0; i<givenDate.length-1;i++) {
    		depured = depured + givenDate[i] +"/";
    	}
    	depured = depured.concat(givenDate[givenDate.length - 1]);
    
    	DailyReport mRep = this.queries.getSpecificDailyReport(selectedStation, depured);
		if(mRep == null) {
			Alert warning = new Alert(AlertType.INFORMATION);
			warning.setContentText("No report matching, change your input.");
			warning.show();
		}
		else {
			setAvgWindDisplay(mRep.getDailyAverageWindSpeed());
			setPrecipitationDisplayDaily(mRep.getDailyPrecipitation());
			setMaxWindDisplay(mRep.getDailyMaxWindSpeed());
		}
    }
    
    //Used to set all of the displays for monthly and daily views
    public void setAvgTempDisplay(Double avgTemp){
    	this.avgTempDisplay.setText("");
        this.avgTempDisplay.setText(Double.toString(avgTemp));
    }

    public void setMaxTempDisplay(Double maxTemp){
        maxTempDisplay.setText("");
        maxTempDisplay.setText(Double.toString(maxTemp));
    }

    public void setMinTempDisplay(Double minTemp){
        this.minTempDisplay.setText("");
        this.minTempDisplay.setText(Double.toString(minTemp));
    }

    public void setPrecipitationDisplayMonthly(Double precipitation){
        precipitationDisplayMonthly.setText("");
        precipitationDisplayMonthly.setText(Double.toString(precipitation));
    }

    public void setPrecipitationDisplayDaily(Double precipitation){
        precipitationDisplayDaily.setText("");
        precipitationDisplayDaily.setText(Double.toString(precipitation));
    }

    public void setAvgWindDisplay(Double avgWind){
        avgWindDisplay.setText("");
        avgWindDisplay.setText(Double.toString(avgWind));
    }

    public void setMaxWindDisplay(Double maxWind){
        maxWindDisplay.setText("");
        maxWindDisplay.setText(Double.toString(maxWind));
    }

    //Used to get the formatted date from the date picker
    public String getDate(){
        //System.out.println(dailyDateSelector.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))); used to test what the output was for the date
        return dateSelector.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    
}
