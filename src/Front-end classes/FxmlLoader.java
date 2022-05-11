import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

    /**
     * FxmlLoader.Tester class responsible for running the game
     */
    public static class Tester extends Application {

         @Override
        public void start(Stage stage) {
            try {
            Parent root = FXMLLoader.load(getClass().getResource("Map.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

                    stage.setOnCloseRequest((EventHandler<WindowEvent>) e -> {
                    Platform.exit();
                    System.exit(0);
                    });

            } catch(Exception e) {
             e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            launch(args);

        }
    }
}
