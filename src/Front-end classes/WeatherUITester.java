
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

/**
 * Tester class responsible for running the game
 */
public class WeatherUITester extends Application {
	
 	@Override
	public void start(Stage stage) {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("WeatherUI.fxml"));
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
