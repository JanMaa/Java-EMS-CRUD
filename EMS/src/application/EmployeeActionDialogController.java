package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EmployeeActionDialogController {

    @FXML
    public TextField actionEmpId;
    
    @FXML
    public TextField actionFname;

    @FXML
    public TextField actionMname;

    @FXML
    public TextField actionLname;
    
    @FXML
    public TextField actionPosition;
    
    @FXML
    public Button actionUpdateBtn;
    
    @FXML
    public Button actionDeleteBtn;
    
    String employeeId;
    String firstName;
    String middleName;
    String lastName;
    String position;
    
    public void actionUpdate(ActionEvent event) throws SQLException, IOException {
    	
	    this.employeeId = actionEmpId.getText();
	    this.firstName = actionFname.getText();
	    this.middleName = actionMname.getText();
	    this.lastName = actionLname.getText();
	    this.position = actionPosition.getText();
	    
        String query = "UPDATE employees SET f_name = ?, m_name = ?, l_name = ?, position = ? WHERE emp_Id = ?";
        
        PreparedStatement pst = PostgreSql.getDbConnection(query);
        
        pst.setString(1, this.firstName);
        pst.setString(2, this.middleName);
        pst.setString(3, this.lastName);
        pst.setString(4, this.position);
        pst.setString(5, this.employeeId);
        pst.executeUpdate();
        
        Node node = (Node) event.getSource();
        Stage newWindow = (Stage) node.getScene().getWindow();
        
        FXMLLoader dashboardLoader = (FXMLLoader) newWindow.getUserData();
        
        Dashboard2Controller dashboard2Controller = dashboardLoader.getController();
        
        dashboard2Controller.showEmployees();
    	
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success dialog");
		alert.setHeaderText("Employee data updated successfully!");
		
		Stage thisStage = (Stage) actionEmpId.getScene().getWindow();
		thisStage.close();
		
		alert.show();
		
		
//		if(alert.showAndWait().get() == ButtonType.OK) {
//			
//			Stage thisStage = (Stage) actionEmpId.getScene().getWindow();
//			thisStage.close();
//			
//		}
		
		
    }
    
    public void actionDelete(ActionEvent event) throws SQLException{
    	
		Alert delAlert = new Alert(AlertType.CONFIRMATION);
		delAlert.setTitle("Deleting");
		delAlert.setHeaderText("Are you sure you want to delete this employee data?");
		
		if(delAlert.showAndWait().get() == ButtonType.OK) {
			
	    	this.employeeId = actionEmpId.getText();
	    	
	        String query = "DELETE FROM employees WHERE emp_id = ?";
	        
	        PreparedStatement pst = PostgreSql.getDbConnection(query);
	        
	        pst.setString(1, this.employeeId);
	        pst.executeUpdate();
	        
	        Node node = (Node) event.getSource();
	        Stage newWindow = (Stage) node.getScene().getWindow();
	        
	        //event.consume();
	        
	        FXMLLoader dashboardLoader = (FXMLLoader) newWindow.getUserData();
	        
	        Dashboard2Controller dashboard2Controller = dashboardLoader.getController();
	        
	        dashboard2Controller.showEmployees();
	        
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success dialog");
			alert.setHeaderText("Employee data deleted successfully!");
			
			Stage thisStage = (Stage) actionEmpId.getScene().getWindow();
			thisStage.close();
			
			alert.show();
			
		}

    }

	public void filterClicks(MouseEvent event) {
		Node node = (Node) event.getSource();
        //Stage newWindow = (Stage) node.getScene().getWindow();
        
        node.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED,evt -> {
			Stage thisStage = (Stage) actionEmpId.getScene().getWindow();
			thisStage.close();
        });
		
	}
 
}
