

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


/**
 * Responsible for controlling the main UI elements of the Simulation
 */
public class StationController implements Initializable{
	 ArrayList<String> words = new ArrayList<>(
	            Arrays.asList("ANDOVER 0.6 E, MA, US\n 2021-03-21\n", "WORCESTER 0.6 E, MA, US\n 2021-03-21\n", "BOSTON 0.6 E, MA, US\n 2021-03-21\n")
	    );
	  @FXML
	    private Pane cards_Pane;
	  ListView<String> list = new ListView<>();

	    

	    @FXML
	    private Label sortBy_Label;
	    Label l=new Label("heis");
	    Label l1=new Label("jeis");
	    Label l2=new Label("hes");
	    
	   

	    @FXML
	    private Pane spane;


	    @FXML
	    private ScrollPane stationName_Display;

	    @FXML
	    private ScrollBar stationName_ScrollBar;

	    @FXML
	    private TextField station_SearchBar;
	    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){


    	 list.getItems().addAll(words);


    	    stationName_Display.setContent(list);
	}

	public StationController(){
    	station_SearchBar = new TextField();
	}


    @FXML
    public void search(ActionEvent event) throws Exception {
        list.getItems().clear();
        list.getItems().addAll(searchList(station_SearchBar.getText(),words));
    }

	//christian
	public void setSearch(String station) throws Exception {
		list.getItems().clear();
		station_SearchBar.setText(station);
        list.getItems().addAll(searchList(station,words));
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
