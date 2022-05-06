import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public class baseGuiController {
    
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

    private void swapColorModes(){
        nightMode_Toggle.setOnAction((event) -> {
            
        });
    }
}
