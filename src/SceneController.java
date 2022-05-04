/**
 * @author Christian
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    Pane mainScreen;

  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
	}

   
 
    public void helloWorld() {
        System.out.println("Hello World");
    }

    public void setScreen1(){
        FxmlLoader sceneLoader = new FxmlLoader();
        Node node;
        node = (Node) sceneLoader.getPage("Screen1.fxml");
        mainScreen.getChildren().setAll(node);

        //mainScreen = sceneLoader.getPage("Screen1");

    }

    public void setScreen2(){
        FxmlLoader sceneLoader = new FxmlLoader();
        Node node;
        node = (Node) sceneLoader.getPage("Screen2.fxml");
        mainScreen.getChildren().setAll(node);
    }
    
}
