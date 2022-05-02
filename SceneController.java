/**
 * @author Christian
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


/**
 * Responsible for controlling the main UI elements of the Simulation
 */
public class SceneController implements Initializable{
    

    @FXML
    Button pressButton;

  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
	}

   
 
    public void helloWorld() {
        System.out.println("Hello World");
    }
    
}
