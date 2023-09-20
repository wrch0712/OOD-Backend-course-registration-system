import java.sql.SQLException;

/*
RegisSystemInterface declares methods should be implemented in the RegisSystem class
*/

public interface RegisSystemInterface {
    User getUser();
    boolean login(int inputUserId, String inputPassword) throws SQLException;
    boolean changePassword(String newPassword) throws SQLException;
    void showAllCourses() throws SQLException;
    void showCourseInfo(int inputCourseId) throws SQLException;

}
