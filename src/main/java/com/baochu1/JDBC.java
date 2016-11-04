package com.baochu1;

import java.sql.*;

/**
 * Created by baotc on 10/24/2016.
 */
public class JDBC {
    public static void main(String[] argv) {
        try {

            insertRecordIntoTable();
            updateRecordIntoTable();
            deleteRecordInTable();
            selectRecordInTable();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // Method Insert record into table
    private static void insertRecordIntoTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO STOCK"
                + "(Stock_Id, Stock_Code, Stock_Name) VALUES" + "(?,?,?)";
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, 20);
            preparedStatement.setString(2, "MISC");
            preparedStatement.setString(3, "Computer");
            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into Stock table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
    // Metod update record in table
    private static void updateRecordIntoTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateTableSQL = "UPDATE STOCK SET Stock_Code=?, Stock_Name=? WHERE Stock_Id =?";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, "MCS1");
            preparedStatement.setString(2, "MySQ");
            preparedStatement.setInt(3, 9);

            // execute update SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is updated into Stock table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
    // Method Delete record in table
    private static void deleteRecordInTable() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String deleteTableSQL = "DELETE FROM STOCK WHERE Stock_Id =?";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(deleteTableSQL);
            preparedStatement.setInt(1, 8);

            // execute delete SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
    // Method Select record in table
    private static void selectRecordInTable() throws SQLException {
        String url ="jdbc:mysql://localhost:3306/Baochu"; //update connection string
        String user = "root";//add your db user id here
        String password = "root";//add your db password here
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Stock");
        while (rs.next())
            System.out.println("Stock_Id : " + rs.getInt("Stock_Id") + ", Stock_Code: " + rs.getString("Stock_Code") + ", Stock_Name: " + rs.getString("Stock_Name"));
    }

    public static Connection getDBConnection(){
        System.out.println("-------- MySQL JDBC Connection Testing ------------");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return null;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/baochu", "root", "root");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }
}


