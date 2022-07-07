package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String css = getClass().getResource("application.css").toExternalForm();
	
	private static String USERNAME = "ADMIN";
	private static String PASSWORD = "ADMIN";
	
	@FXML
	TextField usernameField;
	@FXML
	PasswordField passwordField;
	
	
	public void login(ActionEvent event) throws IOException {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("MALI YUNG PASSWORD BOBO!");
		
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		if (username.equals(USERNAME) && password.equals(PASSWORD)) {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashbord2.fxml"));
			root = loader.load();
			
//			DashboardController dashboardController = loader.getController();
//			dashboardController.displayWelcomeMessage(username);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setUserData(loader);
//			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();
			
		} else {
			alert.show();
		}
		
	}
}
