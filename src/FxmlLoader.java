import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//Class is used in order to load multiple FXML files into a single UI container
public class FxmlLoader {

    private Pane view;

    public FxmlLoader(){
        view = null;
    }


    public Pane getPage(String filename) {

        try {
            view = FXMLLoader.load(getClass().getResource(filename));
        } catch (Exception e){
            System.out.println("Error, fxml file " + filename + " does not exist");
        }



        //System.out.println("Error, method not complete");
        return view;
    }

}
