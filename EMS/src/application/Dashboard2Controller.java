package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Dashboard2Controller implements Initializable{
    
    @FXML
    private TextField fName;

    @FXML
    private TextField mName;

    @FXML
    private TextField lName;
    
    @FXML
    private TextField pos;
    
    @FXML
    private Button saveBtn;

    @FXML
    public TableView<Employees> tvEmployees;
    
    @FXML
    public TableColumn<Employees, String> colEmpId;
    @FXML
    public TableColumn<Employees, String> colFname;
    @FXML
    public TableColumn<Employees, String> colMname;
    @FXML
    public TableColumn<Employees, String> colLname;
    @FXML
    public TableColumn<Employees, String> colPosition;
    
    String employeeId;
    String firstName;
    String middleName;
    String lastName;
    String position;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			showEmployees();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public ObservableList<Employees> getEmployeesList() throws SQLException{
        ObservableList<Employees> employeeList = FXCollections.observableArrayList();
        
        // query
        String query = "SELECT * FROM employees";
        
        PreparedStatement pst = PostgreSql.getDbConnection(query);

    	ResultSet result =  pst.executeQuery();
        
        Employees employees;
        
        while(result.next()){
        	
        	employees = new Employees(result.getString("emp_id"),result.getString("f_name"),result.getString("m_name"),result.getString("l_name"),result.getString("position"));
            
            employeeList.add(employees);
        }

        return employeeList;
    }
    
    public void showEmployees() throws SQLException{
        ObservableList<Employees> emplist = getEmployeesList();
        
        colEmpId.setCellValueFactory(cellData -> cellData.getValue().employeeidProperty());
        colFname.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
        colMname.setCellValueFactory(cellData -> cellData.getValue().middlenameProperty());
        colLname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        colPosition.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        
        tvEmployees.setItems(emplist);
    }
    
    public void saveData() throws SQLException {
    	
	    //this.employeeId = empId.getText();
	    this.firstName = fName.getText();
	    this.middleName = mName.getText();
	    this.lastName = lName.getText();
	    this.position = pos.getText();
	    this.employeeId = "" + this.firstName.charAt(0) + this.lastName.charAt(0);
	    
        // query
	    //INSERT INTO employees(emp_id, f_name, m_name, l_name, position) VALUES(concat('JD0000',nextval('employees_sequence')),'John','Joe','Doe','Manager');

	    String query = "INSERT INTO employees(emp_id, f_name, m_name, l_name, position) VALUES(concat('"+this.employeeId+"',lpad(''||nextval('employees_sequence'),8,'0')), ?, ?, ?, ?)";
	    
        //String query = "INSERT INTO employees(emp_id, f_name, m_name, l_name, position) VALUES(?, ?, ?, ?, ?)";
        
        PreparedStatement pst = PostgreSql.getDbConnection(query);

        //pst.setString(1, this.employeeId);
        pst.setString(1, this.firstName);
        pst.setString(2, this.middleName);
        pst.setString(3, this.lastName);
        pst.setString(4, this.position);
        pst.executeUpdate();
        
        System.out.println("Sucessfully saved to database.");

        showEmployees();
        clearInputData();

    }
    
    public void clearInputData() {
    	fName.clear();
    	mName.clear();
    	lName.clear();
    	pos.clear();
    }
    
    @FXML
    public void handleMouseActionEvent(MouseEvent event) throws IOException {
    	
    	Employees employee =  tvEmployees.getSelectionModel().getSelectedItem();
    	
        Node node = (Node) event.getSource();
        Stage dashboardWindow = (Stage) node.getScene().getWindow();
    	
    	FXMLLoader dashboardLoader =  (FXMLLoader) dashboardWindow.getUserData();
    	
    	//Create Stage
    	Stage newWindow = new Stage();
    	
    	newWindow.setUserData(dashboardLoader.getController());

    	newWindow.setTitle("Choose action");
    	//Create view from FXML
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("EmployeeActionDialog.fxml"));
    	
    	Parent parent = loader.load();
    	
    	EmployeeActionDialogController employeeActionDialog = loader.getController();
    	
    	newWindow.setUserData(dashboardLoader);
    	
    	//Set view in window
    	newWindow.setScene(new Scene(parent));
    	
    	employeeActionDialog.actionEmpId.setText(employee.getEmployeeId());
    	employeeActionDialog.actionFname.setText(employee.getFirstName());
    	employeeActionDialog.actionMname.setText(employee.getMiddleName());
    	employeeActionDialog.actionLname.setText(employee.getLastName());
    	employeeActionDialog.actionPosition.setText(employee.getPosition());
    	
    	employeeActionDialog.filterClicks(event);
    	
    	//event.consume();
    	
    	newWindow.show();
    	
//    	newWindow.setOnCloseRequest(e -> {
//    		e.consume();
//    		newWindow.close();
//		
//		});
    	

    }
    

}
