
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

    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane scroller;

    ListView<String> list = new ListView<>();

    private boolean isLightMode = true;

    ArrayList<String> words = new ArrayList<>(
            Arrays.asList("ANDOVER\n", "WORCESTER\n", "BOSTON\n", "BEDFORD\n",
                    "BEVERLY\n", "BLUE HILL LCD\n", "CHATHAM\n", "CHICOPEE\n",
                    "FALMOUTH\n", "FITCHBURG\n", "HYANNIS\n", "LAWRENCE\n",
                    "MARSHFIELD\n", "NANTUCKET\n", "NEW BEDFORD\n",
                    "NORTH ADAMS HARRIMAN\n", "NORWOOD\n", "ORANGE\n", "PITTSFIELD\n",
                    "PLYMOUTH\n", "PROVINCETOWN\n", "TAUNTON\n", "VINEYARD\n",
                    "WESTFIELD\n")
    );


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
        list.getItems().addAll(words);


        scroller.setContent(list);
    }

    public baseGuiController(){
        stationController = new StationController();
        mapController = new MapController();
        station_SearchBar = new TextField();
        station_SearchBar.setText("test");
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
