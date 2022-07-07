package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employees {
	
	private StringProperty employeeid;
	private StringProperty firstname;
	private StringProperty middlename;
	private StringProperty lastname;
	private StringProperty position;

	public Employees(String employeeid, String firstname, String middlename, String lastname, String position) {
	    this.employeeid = new SimpleStringProperty(employeeid);
	    this.firstname = new SimpleStringProperty(firstname);
	    this.middlename = new SimpleStringProperty(middlename);
	    this.lastname = new SimpleStringProperty(lastname);
	    this.position = new SimpleStringProperty(position);
	}
	
	public StringProperty employeeidProperty() {
	    return employeeid;
	}

	public StringProperty firstnameProperty() {
	    return firstname;
	}
	
	public StringProperty middlenameProperty() {
	    return middlename;
	}
	
	public StringProperty lastnameProperty() {
	    return lastname;
	}
	
	public StringProperty positionProperty() {
	    return position;
	}
	
	public String getEmployeeId() {
		return employeeid.get();
	}

	public String getFirstName() {
		return firstname.get();
	}
	
	public String getMiddleName() {
		return middlename.get();
	}
	
	public String getLastName() {
		return lastname.get();
	}

	public String getPosition() {
		return position.get();
	}

}

