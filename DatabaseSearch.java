package mystuff.database;


import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DatabaseSearch extends JFrame {

    JButton button;
    JTextField searchPerson;
    String personName = "";
    String person = "";
    ResultSet finalResult;
    String personFound = "";
    Connection conn = null;

    public static void main(String[] args) {

        new DatabaseSearch();


    }

    public  DatabaseSearch(){

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("search");

        JPanel mainPanel = new JPanel();

        searchPerson = new JTextField(10);
        mainPanel.add(searchPerson);

        button = new JButton("search");
        ListenForButton listenForButton = new ListenForButton();
        button.addActionListener(listenForButton);

        mainPanel.add(button);


        this.add(mainPanel);
        this.setVisible(true);
    }

    public class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                personName += searchPerson.getText();

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/employees", "root", "");

                    Statement sqlState2 = conn.createStatement();
                    person = "Select * from employees where first_name = " + "'" + personName + "'";
                    finalResult = sqlState2.executeQuery(person);



                    if (finalResult.next()) {

                        personFound += finalResult.getString("first_name") + " " + finalResult.getString("last_name") + " " + finalResult.getString("hire_date");
                    }

                    if (personFound.length() > 0) {
                        JOptionPane.showMessageDialog(DatabaseSearch.this, personFound, "Information", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(DatabaseSearch.this, "person not found!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }

                    personFound = "";
                    personName = "";

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
    }
}
