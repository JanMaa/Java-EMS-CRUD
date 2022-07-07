package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String css = getClass().getResource("application.css").toExternalForm();
	
	@FXML
	Label welcomeLabel;
	
	public void displayWelcomeMessage(String username) {
		welcomeLabel.setText("Welcome "+username+"!");
	}
	
	public void logOut(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

}
