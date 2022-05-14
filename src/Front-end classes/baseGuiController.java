
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class baseGuiController implements Initializable {
    
    @FXML
    private Label sorted_Label, unit_Label, sortBy_Label,static_StationLabel, station_Name;

    @FXML
    private SplitMenuButton sortBy_DropDown, sortedBy_DropDown, unit_DropDown;

    @FXML
    private Separator separator_Dash;

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
    
    //Modified by Carlos Rodriguez
    @FXML
    private monthlyViewController mView;
    @FXML
    private dailyViewController dView;
    
    //
    
    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane scroller;

    @FXML
    private AnchorPane dataPane;

    ListView<String> list = new ListView<>();

    private boolean isLightMode = true;
    
    /**
     * @author: Carlos Rodriguez
     * Global reference of type Queries */
    private Queries queries = new Queries();

    //Modified by Carlos Rodriguez
    List<String> words = this.queries.allStationsNames();
    private String sortBy, selectedMonth, selectedStation, selectedYear = "";

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
        //Modified by Carlos Rodriguez
        mView = new monthlyViewController();
        dView = new dailyViewController();
        //
        list.getItems().addAll(words);
        //this.stationName_Display.setContent(list);
        scroller.setContent(list);
    }

    public baseGuiController(){
        stationController = new StationController();
        mapController = new MapController();
        station_SearchBar = new TextField();
    }

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
        expSet(selectedStation);
    }
    
    //Search Report button
    public void searchReport(ActionEvent evt) {
    	if(!this.selectedMonth.equals("") && !this.selectedStation.equals("")) {
    		if(this.sortBy.equals("MONTHLY")) {
    			if(!this.selectedYear.equals("")) {
    				MonthlyReport aRep = queries.getSpecificMonthlyReport(this.selectedStation, this.selectedMonth, this.selectedYear);
        	        if(aRep == null) {
        	        	Alert notFound = new Alert(AlertType.INFORMATION);
        	    		notFound.setContentText("No report found for given date.");
        	        }
        	        else {
        	        	mView.setAvgTempDisplay(aRep.getmonthlymonthlyAvgTemp());
            	        mView.setMaxTempDisplay(aRep.getmonthlymonthlyMaxTemp());
            	        mView.setMinTempDisplay(aRep.getmonthlymonthlyMinTemp());
            	        mView.setPrecipitationDisplay(aRep.getmonthlymonthlyTotalPrecipitation());
        	        }
    			}
    		}
    		if(this.sortBy.equals("DAILY")) {
    			DailyReport aRep = queries.getSpecificDailyReport(this.selectedStation, dView.getDate());
    			 if(aRep == null) {
     	        	Alert notFound = new Alert(AlertType.INFORMATION);
     	    		notFound.setContentText("No report found for given date.");
     	        }
    			else {
    				dView.setAvgWindDisplay(aRep.getDailyAverageWindSpeed());
        	        dView.setMaxWindDisplay(aRep.getDailyMaxWindSpeed());
        	        dView.setPrecipitationDisplay(aRep.getDailyPrecipitation());
    			}
    		}
    	}
    	else {
    		Alert notFound = new Alert(AlertType.WARNING);
    		notFound.setContentText("Report can't be found.\nPlease check the criteria entered!");
    	}
    }
    
    //SortBy Split Menu search for methods
    public void searchMonthly() throws IOException {
        this.sortBy = "MONTHLY";


        try {
            Node node;
            node = (Node)FXMLLoader.load(getClass().getResource("monthlyView.fxml"));
            dataPane.getChildren().setAll(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchDaily(ActionEvent event) {
        this.sortBy = "DAILY";

        try {
            Node node;
            node = (Node)FXMLLoader.load(getClass().getResource("dailyView.fxml"));
            dataPane.getChildren().setAll(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
    
    
}
