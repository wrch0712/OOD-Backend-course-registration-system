import java.sql.SQLException;

/*
RegieStudentSystemInterface declares methods should be implemented in the RegieStudentSystem class
*/

public interface RegieStudentSystemInterface {
    void studentLogin(int inputUserId, String inputPassword) throws SQLException;
    void showStudentMenu();
    void showStudentCourse();
    void showStudentSchedule();
    void showStudentGrade(int courseId) throws SQLException;
    void showStudentTranscript() throws SQLException;
    void showStudentRestriction();
    void registerStudentCourse(int inputCourseId) throws SQLException;
    void dropStudentCourse(int inputCourseId) throws SQLException;
}

