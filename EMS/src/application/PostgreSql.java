package application;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;

public class PostgreSql {
	
    public static PreparedStatement getDbConnection(String query) {
    	
        String url = "jdbc:postgresql://localhost:5432/ems";
        String user = "postgres";
        String password = "janma";
        
        PreparedStatement pst = null;

        try {
        	
        	Connection con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            
            System.out.println("Sucessfully loaded database.");
            
            return pst;
               
           } catch (SQLException ex) {

               Logger lgr = Logger.getLogger(PostgreSql.class.getName());
               lgr.log(Level.SEVERE, ex.getMessage(), ex);
               
           }
        
        return pst;
        
    }
	
}
