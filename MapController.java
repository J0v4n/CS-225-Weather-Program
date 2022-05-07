/**
 * @author Christian
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;


/**
 * Responsible for controlling the main UI elements of the Simulation
 */
public class MapController implements Initializable{
    

    @FXML
    AnchorPane mapContainer;

  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
	}

   
 
    public String stationSelected(ActionEvent event) {
        System.out.println(((RadioButton) event.getSource()).getText());
        return ((RadioButton) event.getSource()).getText();
    }
    
}
