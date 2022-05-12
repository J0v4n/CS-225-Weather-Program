/**
 * @author Christian Rudder
 * modified by Yuliia Synytska
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


/**
 * Responsible for controlling the main UI elements of the Simulation
 */


/**
 * ArrayList of Strings station names by Yuliia
 */
public class SceneController implements Initializable{
	static ArrayList<String> words = new ArrayList<>(
			Arrays.asList("ANDOVER 0.6 E, MA, US\n 2021-03-21\n", "WORCESTER 0.6 E, MA, US\n 2021-03-21\n", "BOSTON 0.6 E, MA, US\n 2021-03-21\n")
	);
	@FXML
	private Pane cards_Pane;
	ListView<String> list = new ListView<>();

	@FXML
	private MenuItem celsius_Option;

	@FXML
	private MenuItem fahrenheit_Option;

	@FXML
	private MenuItem kelvin_Option;

	@FXML
	private Pane map_Pane;

	@FXML
	private Separator separator_Dash;

	@FXML
	private MenuItem sortBy_Date;

	@FXML
	private MenuItem sortBy_Date1;

	@FXML
	private SplitMenuButton sortBy_DropDown;

	@FXML
	private Label sortBy_Label;
	Label l=new Label("heis");
	Label l1=new Label("jeis");
	Label l2=new Label("hes");

	@FXML
	private MenuItem sortBy_Month;

	@FXML
	private MenuItem sortBy_Month1;

	@FXML
	private SplitMenuButton sortedBy_DropDown;

	@FXML
	private Label sorted_Label;

	@FXML
	private Pane spane;

	@FXML
	private Label static_StationLabel;

	@FXML
	private ScrollPane stationName_Display;

	@FXML
	private ScrollBar stationName_ScrollBar;

	@FXML
	private Label station_Name;

	@FXML
	private TextField station_SearchBar;

	@FXML
	private SplitMenuButton unit_DropDown;

	@FXML
	private Label unit_Label;

	 /**
	  * display stationNames by Yuliia Synytska
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1){

		list.getItems().addAll(words);
		stationName_Display.setContent(list);
		Queries.w=words;

	}

	/**
	 * searching stations by Yuliia Synytska
	 */
	@FXML
	void search(ActionEvent event) throws Exception {
		list.getItems().clear();
		list.getItems().addAll(searchList(station_SearchBar.getText(),words));
		System.out.println(searchList(station_SearchBar.getText(),words));
	}
	@FXML
	public List<String> searchList(String searchWords, List<String> listOfStrings) throws Exception{

		List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

		return listOfStrings.stream().filter(input -> {
			return searchWordsArray.stream().allMatch(word ->
					input.toLowerCase().contains(word.toLowerCase()));
		}).collect(Collectors.toList());
	}



}
