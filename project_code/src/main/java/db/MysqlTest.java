package db;

import java.sql.*;

/*
This is a test for connecting MySQL
*/
public class MysqlTest {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            String query = "select * from User";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(1) + ", "
                        + rs.getString(2) + " "
                        + rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
    }
}
