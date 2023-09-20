import java.sql.*;

/*
I made LoginEngine a segregate class and not include logIn method in User class,
because everyone can try log in, regardless he/she is a user of REGIE
(If he/she doesn't have correct input id and password are wrong, we don't think he/she is a user of REGIE)
The logIn method will return a User instance if the person log in successfully (he is a user of REGIE in this case)
 */

public class LoginEngine {

    // The logIn method will return a User instance if the person log in successfully (he is a user of REGIE in this case)
    public static User login(int inputUserId, String inputPassword) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            // In my project
            String query = String.format("select * from User where user_id = '%s' and password ='%s';", inputUserId, inputPassword);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                User loggedInUser = Factory.createStudent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                stmt.close();
                conn.close();
                return loggedInUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return null;
    }
}
