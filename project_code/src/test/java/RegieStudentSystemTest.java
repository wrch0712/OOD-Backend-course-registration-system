import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RegieStudentSystemTest {

    private RegieStudentSystem regieStudentSystemUnderTest;

    @BeforeEach
    void setUp() {
        regieStudentSystemUnderTest = new RegieStudentSystem();
    }

    @Test
    void testDropStudentCourse1() throws Exception {
        regieStudentSystemUnderTest.dropStudentCourse(0);
    }

    @Test
    void testDropStudentCourse_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.dropStudentCourse(0));
    }

    @Test
    void testRegisterStudentCourse1() throws Exception {
        regieStudentSystemUnderTest.registerStudentCourse(0);
    }

    @Test
    void testRegisterStudentCourse_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.registerStudentCourse(0));
    }

    @Test
    void testShowStudentCourse1() throws Exception {
        regieStudentSystemUnderTest.showStudentCourse();
    }

    @Test
    void testShowStudentCourse_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.showStudentCourse());
    }

    @Test
    void testShowStudentGrade1() throws Exception {
        regieStudentSystemUnderTest.showStudentGrade(0);
    }

    @Test
    void testShowStudentGrade_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.showStudentGrade(0));
    }

    @Test
    void testShowStudentMenu1() {
        regieStudentSystemUnderTest.showStudentMenu();
    }

    @Test
    void testShowStudentRestriction1() {
        regieStudentSystemUnderTest.showStudentRestriction();
    }

    @Test
    void testShowStudentSchedule1() throws Exception {
        regieStudentSystemUnderTest.showStudentSchedule();
    }

    @Test
    void testShowStudentSchedule_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.showStudentSchedule());
    }

    @Test
    void testShowStudentTranscript1() throws Exception {
        regieStudentSystemUnderTest.showStudentTranscript();
    }

    @Test
    void testShowStudentTranscript_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.showStudentTranscript());
    }

    @Test
    void testStudentLogin1() throws Exception {
        regieStudentSystemUnderTest.studentLogin(0, "inputPassword");
    }

    @Test
    void testStudentLogin_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.studentLogin(0, "inputPassword"));
    }

    @Test
    void testStudentLogin() throws Exception {
        regieStudentSystemUnderTest.studentLogin(0, "inputPassword");
    }

    @Test
    void testStudentLogin_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.studentLogin(0, "inputPassword"));
    }

    @Test
    void testShowStudentMenu() {
        regieStudentSystemUnderTest.showStudentMenu();
    }

    @Test
    void testShowStudentCourse() throws Exception {
        regieStudentSystemUnderTest.showStudentCourse();
    }

    @Test
    void testShowStudentCourse_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.showStudentCourse());
    }

    @Test
    void testShowStudentSchedule() throws Exception {
        regieStudentSystemUnderTest.showStudentSchedule();
    }

    @Test
    void testShowStudentSchedule_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.showStudentSchedule());
    }

    @Test
    void testShowStudentGrade() throws Exception {
        regieStudentSystemUnderTest.showStudentGrade(0);
    }

    @Test
    void testShowStudentGrade_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.showStudentGrade(0));
    }

    @Test
    void testShowStudentTranscript() throws Exception {
        regieStudentSystemUnderTest.showStudentTranscript();
    }

    @Test
    void testShowStudentTranscript_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.showStudentTranscript());
    }

    @Test
    void testShowStudentRestriction() {
        regieStudentSystemUnderTest.showStudentRestriction();
    }

    @Test
    void testRegisterStudentCourse() throws Exception {
        regieStudentSystemUnderTest.registerStudentCourse(0);
    }

    @Test
    void testRegisterStudentCourse_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.registerStudentCourse(0));
    }

    @Test
    void testDropStudentCourse() throws Exception {
        regieStudentSystemUnderTest.dropStudentCourse(0);
    }

    @Test
    void testDropStudentCourse_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> regieStudentSystemUnderTest.dropStudentCourse(0));
    }
}
