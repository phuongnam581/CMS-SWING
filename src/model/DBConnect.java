package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public static Connection getConnection() {
        try {
        	//Class.forName("com.mysql.jdbc.Driver");
            Connection cons = null;
            cons = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "root", "1234");
            return cons;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) throws SQLException {
        Connection c = getConnection();
        System.out.println(c.toString());
        c.close();
    }


}
