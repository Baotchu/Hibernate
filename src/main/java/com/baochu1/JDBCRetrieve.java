package com.baochu1;
import java.sql.*;

/**
 * Created by baotc on 10/27/2016.
 */
public class JDBCRetrieve {

    public static void main(String[] args) throws SQLException {
        String url ="jdbc:mysql://localhost:3306/Baochu"; //update connection string
        String user = "root";//add your db user id here
        String password = "root";//add your db password here
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * From Stock");
        while (rs.next())
            System.out.println("Stock_Id : " + rs.getInt("Stock_Id") + ", Stock_Code: " + rs.getString("Stock_Code") + ", Stock_Name: " + rs.getString("Stock_Name"));
    }
}