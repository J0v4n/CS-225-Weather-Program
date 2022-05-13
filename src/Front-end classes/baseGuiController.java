
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane scroller;

    ListView<String> list = new ListView<>();

    private boolean isLightMode = true;
    
    /**
     * @author: Carlos Rodriguez
     * Global reference of type Queries */
    private Queries queries = new Queries();

    //Modified by Carlos Rodriguez
    List<String> words = this.queries.allStationsNames();


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

        scroller.setContent(list);
    }

    public baseGuiController(){
        stationController = new StationController();
        mapController = new MapController();
        station_SearchBar = new TextField();
        station_SearchBar.setText("test");
        
        /**
         * @author: Carlos Rodriguez
         * In a method (for a cleaner version)
         * - Would prolly need a variable as selectedCurrentStation
         *  (whole name as LAWRENCE MUNICIPAL AIRPORT, not LAWRENCE ONLY)
         * - Get that station and pass it.
         * - getDate() for dailyViewController to get Date form datePicker
         * - update visual aspects
         * - Null aRep object if no report was found (best to set N/A all textfields)
        DailyReport aRep = queries.getSpecificDailyReport("LAWRENCE MUNICIPAL AIRPORT", dView.getDate());
        dView.setAvgWindDisplay(aRep.getDailyAverageWindSpeed());
        dView.setMaxWindDisplay(aRep.getDailyMaxWindSpeed());
        dView.setPrecipitationDisplay(aRep.getDailyPrecipitation()); */
        
        /**
         * In a method (for a cleaner version) as well
         * - Would prolly need a variable as selectedCurrentStation
         *  (whole name as LAWRENCE MUNICIPAL AIRPORT, not LAWRENCE ONLY)
         * - Get that station and pass it along with month name and year.
         * - update visual aspects 
         * - Null aRep object if no report was found (best to set N/A all textfields)
        MonthlyReport aRep = queries.getSpecificMonthlyReport("HYANNIS MUNICIPAL AIRPORT", "DECEMBER", "2021");
        mView.setAvgTempDisplay(aRep.getmonthlymonthlyAvgTemp());
        mView.setMaxTempDisplay(aRep.getmonthlymonthlyMaxTemp());
        mView.setMinTempDisplay(aRep.getmonthlymonthlyMinTemp());
        mView.setPrecipitationDisplay(aRep.getmonthlymonthlyTotalPrecipitation());*/
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
        String station = ((RadioButton) event.getSource()).getText();
        System.out.println(station);
        expSet(station);
    }

}
