import java.sql.SQLException;
import java.util.List;

/*
StudentInterface declares methods should be implemented in the Student class
 */

public interface StudentInterface{
    List<Course> getMyCourse();
    List<Restriction> getMyRestriction();
    List<String> getMySchedule();
    String getMyGrade(int courseId) throws SQLException;
    List<String> getMyTranscript () throws SQLException;
    boolean registerCourse (int courseId) throws SQLException;
    boolean dropCourse (int courseId) throws SQLException;
}
