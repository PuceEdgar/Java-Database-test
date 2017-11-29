package mystuff.database;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import  javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ShowDataBaseInfo{

    static Object[][] databaseInfo;
    static Object[] columns = {"ID", "Name", "Surname", "Age"};
    static ResultSet rows;
    static ResultSetMetaData metaData;


    JButton add, showInfo;
    JTextField firstName, lastName, age;
    Connection conn = null;
    JFrame mainFrame;
    String error = "";


    public static void main(String[] args) {

        new ShowDataBaseInfo();

    }

    public ShowDataBaseInfo(){
        mainFrame = new JFrame();
        mainFrame.setSize(400,400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        firstName = new JTextField("First Name", 10);
        panel.add(firstName);

        lastName = new JTextField("Last name", 10);
        panel.add(lastName);

        age = new JTextField("Age", 5);
        panel.add(age);

        add = new JButton("Add customer");
        ListenForButton lForButton = new ListenForButton();
        add.addActionListener(lForButton);
        panel.add(add);

        showInfo = new JButton("open table");
        ListenForButton listenForButton = new ListenForButton();
        showInfo.addActionListener(listenForButton);
        panel.add(showInfo);


        mainFrame.add(panel);
        mainFrame.setVisible(true);

    }

    public class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showInfo) {


                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
                DefaultTableModel defaultTableModel = new DefaultTableModel(databaseInfo, columns){
                    public Class getColumnClass(int column) {
                        Class returnValue;

                        if ((column >= 0) && (column < getColumnCount())) {
                            returnValue = getValueAt(0, column).getClass();
                        } else {
                            returnValue = Object.class;
                        }
                        return returnValue;
                    }
                };



                try {

                    Class.forName("com.mysql.jdbc.Driver");

                    conn = DriverManager.getConnection("jdbc:mysql://localhost/swing", "root", "");

                    Statement sqlState = conn.createStatement();

                    String selectStuff = "select * from customers";

                    rows = sqlState.executeQuery(selectStuff);

                    Object[] tempRow;

                    while (rows.next()){
                        tempRow = new Object[]{rows.getInt(1), rows.getString(2), rows.getString(3), rows.getDouble(4)};
                        defaultTableModel.addRow(tempRow);
                    }
                }
                catch (SQLException ee){
                    System.out.println(ee.getMessage());
                }

                catch (ClassNotFoundException ee){
                    System.out.println(ee.getMessage());
                }

                JTable table = new JTable(defaultTableModel);

                table.setRowHeight(table.getRowHeight() + 10);
                table.setFont(new Font("Serif", Font.PLAIN, 20));
                table.setAutoCreateRowSorter(true);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.getColumnCount();

                TableColumn col1 = table.getColumnModel().getColumn(0);
                col1.setPreferredWidth(100);

                JScrollPane scrollPane = new JScrollPane(table);
                frame.add(scrollPane, BorderLayout.CENTER);
                frame.setSize(400,400);
                frame.setVisible(true);

            }

            if (e.getSource() == add) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");

                    conn = DriverManager.getConnection("jdbc:mysql://localhost/swing", "root", "");

                    //String success = "Connected Successfully!";
                    //JOptionPane.showMessageDialog(AddCustomerToDatabase.this, success, "Information!", JOptionPane.INFORMATION_MESSAGE);
                    Statement sqlState = conn.createStatement();
                    String query = "insert into customers (first_name, last_name, age) values('" + firstName.getText() + "', '"
                            + lastName.getText() + "', " + age.getText() + ")";



                    sqlState.executeUpdate(query);
                    String message = firstName.getText() + " " + lastName.getText() + " " + " was added to database!";

                    JOptionPane.showMessageDialog(mainFrame, message, "Information", JOptionPane.INFORMATION_MESSAGE);

                }

                catch (SQLException ex) {
                    error += ex.getMessage();
                    JOptionPane.showMessageDialog(mainFrame, error, "Error", JOptionPane.INFORMATION_MESSAGE);
                    error = "";
                }

                catch(ClassNotFoundException ee){
                    error += ee.getMessage();
                    JOptionPane.showMessageDialog(mainFrame, error, "Error", JOptionPane.INFORMATION_MESSAGE);
                    error = "";
                }
            }
        }
    }

}

