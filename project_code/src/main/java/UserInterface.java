import java.sql.SQLException;
import java.util.List;

/*
UserInterface declares methods should be implemented in the User class
 */

public interface UserInterface {
    boolean changePassword(String newPassword)  throws SQLException;
    List<Course> getAllCourse() throws SQLException;
    String getCourseInfo(int inputCourseId) throws SQLException;
}
