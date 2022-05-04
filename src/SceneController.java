/**
 * @author Christian
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.*;


/**
 * Responsible for controlling the main UI elements of the Simulation
 */
public class SceneController implements Initializable{


    

    @FXML
    Button pressButton;

    @FXML
    Button screen1Button;

    @FXML
    Button screen2Button;

    @FXML
    FlowPane mainScreen;

  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
	}

   
 
    public void helloWorld() {
        System.out.println("Hello World");
    }

    public void setScreen1(){
        FxmlLoader sceneLoader = new FxmlLoader();
        Pane view = sceneLoader.getPage("Screen1");
        mainScreen = (FlowPane) view;
    }

    public void setScreen2(){

    }
    
}
