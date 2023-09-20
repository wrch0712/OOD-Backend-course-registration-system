import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student studentUnderTest;

    @BeforeEach
    void setUp() {
        studentUnderTest = new Student(0, "lastName", "firstName", "password");
    }

    @Test
    void testInitialize() throws Exception {
        studentUnderTest.initialize();
    }

    @Test
    void testInitialize_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> studentUnderTest.initialize());
    }

    @Test
    void testGetMyCourse() {
        final List<Course> result = studentUnderTest.getMyCourse();
    }

    @Test
    void testGetMyRestriction() {
        final List<Restriction> result = studentUnderTest.getMyRestriction();
    }

    @Test
    void testDropCourse1() throws Exception {
        final boolean result = studentUnderTest.dropCourse(0);
        assertFalse(result);
    }

    @Test
    void testDropCourse_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> studentUnderTest.dropCourse(0));
    }

    @Test
    void testGetMyCourse1() {
        final List<Course> result = studentUnderTest.getMyCourse();
    }

    @Test
    void testGetMyGrade1() throws Exception {
        assertEquals("", studentUnderTest.getMyGrade(0));
        assertThrows(SQLException.class, () -> studentUnderTest.getMyGrade(0));
    }

    @Test
    void testGetMyRestriction1() {
        final List<Restriction> result = studentUnderTest.getMyRestriction();
    }

    @Test
    void testGetMySchedule1() {
        assertEquals(List.of("value"), studentUnderTest.getMySchedule());
    }

    @Test
    void testGetMyTranscript1() throws Exception {
        assertEquals(List.of("value"), studentUnderTest.getMyTranscript());
    }

    @Test
    void testInitialize1() throws Exception {
        studentUnderTest.initialize();
    }

    @Test
    void testInitialize_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> studentUnderTest.initialize());
    }

    @Test
    void testRegisterCourse1() throws Exception {
        final boolean result = studentUnderTest.registerCourse(0);
        assertFalse(result);
    }

    @Test
    void testRegisterCourse_ThrowsSQLException1() {
        assertThrows(SQLException.class, () -> studentUnderTest.registerCourse(0));
    }

    @Test
    void testGetMySchedule() {
        assertEquals(List.of("value"), studentUnderTest.getMySchedule());
    }

    @Test
    void testGetMyGrade() throws Exception {
        assertEquals("", studentUnderTest.getMyGrade(0));
        assertThrows(SQLException.class, () -> studentUnderTest.getMyGrade(0));
    }

    @Test
    void testGetMyTranscript() throws Exception {
        assertEquals(List.of("value"), studentUnderTest.getMyTranscript());
    }

    @Test
    void testRegisterCourse() throws Exception {
        final boolean result = studentUnderTest.registerCourse(0);
        assertFalse(result);
    }

    @Test
    void testRegisterCourse_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> studentUnderTest.registerCourse(0));
    }

    @Test
    void testDropCourse() throws Exception {
        final boolean result = studentUnderTest.dropCourse(0);
        assertFalse(result);
    }

    @Test
    void testDropCourse_ThrowsSQLException() {
        assertThrows(SQLException.class, () -> studentUnderTest.dropCourse(0));
    }
}
