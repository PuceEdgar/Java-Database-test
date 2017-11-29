package mystuff.swing;
import mystuff.database.DatabaseSearch;

import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {

    Connection conn = null;
    String output = "";
    String labelOutput = "";



    public Database() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/employees", "root", "");

            Statement sqlStatement = conn.createStatement();
            String sqlQuery = "Select * from employees";
            ResultSet result = sqlStatement.executeQuery(sqlQuery);

            List<String> persons = new ArrayList<>();

            while (result.next()){
                persons.add(result.getString("first_name")) ;

            }

            for (int i = 0; i < persons.size(); i++) {
                output +=  persons.get(i) + "<br />";
            }

            labelOutput = "<html><b>" + output + "</b></html>";

        }

        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        }
    }








}
