package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		try {
		
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			
			Scene scene = new Scene(root);
			
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			
			Image icon = new Image("icon.png");
			
			stage.getIcons().add(icon);
			stage.setTitle("Employee Management System");
//			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
			
			stage.setOnCloseRequest(event -> {
				event.consume();
				closeConfirmDialog(stage);
			
			});
			
		} catch(Exception  e) {
			e.printStackTrace();
		}
	}
	
	public void closeConfirmDialog(Stage stage) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Closing Dialog");
		alert.setHeaderText("Are you sure you want to close this application?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
			Platform.exit();
		}
		
	}
}
