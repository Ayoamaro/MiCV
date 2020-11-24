package dad.javafx.micv;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Ayoze Amaro
 *
 */
public class App extends Application {

	private static Stage primaryStage;
	
	private Controller controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;
		
		controller = new Controller();
		
		Scene escena = new Scene(controller.getView());
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("MiCV");
		primaryStage.getIcons().add(new Image("/images/cv64x64.png"));
		primaryStage.show();
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
