import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.net.URL;

//Class is used in order to load multiple FXML files into a single UI container
public class FxmlLoader {

    private Pane view;

    public FxmlLoader(){
        view = null;
    }


    public Pane getPage(String filename){

        try{
            URL fileUrl = Tester.class.getResource("/tester/" + filename + ".fxml");
            if(fileUrl == null){
                throw new java.io.FileNotFoundException("FXML file cannot be found");
            }

            view = FXMLLoader.load(fileUrl);

        } catch(Exception e){
            System.out.println("Error, fxml file " + filename + ".fxml does not exist");
        }


        //System.out.println("Error, method not complete");
        return view;
    }

}
