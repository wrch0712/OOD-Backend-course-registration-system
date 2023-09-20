import db.MongoDBLogHelper;

import java.sql.SQLException;
import java.util.List;

/*
RegieStudentSystem represent for the functionality that RIGIE system should have for student page
The functions for students include showStudentCourse, showStudentSchedule... the methods are declared in RegieStudentSystemInterface
REGIE student system is a part of RIGIE system, so it should have methods that general RIGIE system have. RegieStudentSystem extend RegieSystem class
RegieStudentSystem implements RegieStudentSystemInterface, so it need to override all methods of RegieStudentSystemInterface
RegieStudentSystem class holds a student attribute and methods for REGIE system student page
Methods include logIn, changePassword, showAllCourses, showCourseInfo, studentLogin, showStudentMenu,
showStudentCourse, showStudentSchedule, showStudentGrade, showStudentTranscript,showStudentRestriction,
registerStudentCourse, dropStudentCourse
 */

public class RegieStudentSystem extends RegieSystem implements RegieStudentSystemInterface{
    private Student student; // initialized with null, will be changed if user log in successfully

    public Student getStudent() {
        return student;
    }

    @Override
    public void studentLogin(int inputUserId, String inputPassword) throws SQLException {
        // Since my project focus on Student's activity, I assume user is student here
        boolean loginRes = login(inputUserId, inputPassword);
        if (loginRes) {
            student = (Student) getUser();
            student.initialize();
            MongoDBLogHelper.writeLog(student.getUserId(), "student " + inputUserId + " log in");
        }
    }

    @Override
    public void showStudentMenu() {
        if (student == null) {
            return;
        }
        System.out.println("""
                Menu:
                1: show all courses in the university
                2: show info of a course
                3: show all courses you take
                4: get your schedule
                5: register for a course
                6: drop a course
                7: get your grade of a course
                8: get your transcript
                9: get your restriction
                10: change password
                11: exit""");
    }

    @Override
    public void showStudentCourse() {
        if (student == null) {
            return;
        }
        MongoDBLogHelper.writeLog(student.getUserId(), "show all courses the student take");
        List<Course> myCourses = student.getMyCourse();
        if (myCourses.isEmpty()) {
            System.out.println("You don't have any course.");
        } else {
            System.out.println("Your courses: ");
            for (Course myCourse : myCourses) {
                System.out.println(myCourse.getCourseId() + ": " + myCourse.getCourseName() + ", " + myCourse.getStatus());
            }
        }
    }

    @Override
    public void showStudentSchedule() {
        if (student == null) {
            return;
        }
        List<String> scheduleLines = student.getMySchedule();
        MongoDBLogHelper.writeLog(student.getUserId(), "get schedule");
        if (scheduleLines.isEmpty()) {
            System.out.println("You don't have class this week.");
        } else {
            System.out.println("Your schedule for this week: ");
            for (String scheduleLine : scheduleLines) {
                System.out.println(scheduleLine);
            }
        }
    }

    @Override
    public void showStudentGrade(int courseId) throws SQLException {
        if (student == null) {
            return;
        }
        String grade = student.getMyGrade(courseId);
        if (!grade.equals("")) {
            System.out.println(grade);
            MongoDBLogHelper.writeLog(student.getUserId(), "get grade of course " + grade);
        }
    }

    @Override
    public void showStudentTranscript() throws SQLException {
        if (student == null) {
            return;
        }
        List<String> transcriptLines = student.getMyTranscript();
        MongoDBLogHelper.writeLog(student.getUserId(), "get transcript");
        if (transcriptLines.isEmpty()) {
            System.out.println("Your transcript is empty!");
        } else {
            System.out.println("Your transcript: ");
            for (String transcriptLine : transcriptLines) {
                System.out.println(transcriptLine);
            }
        }
    }

    @Override
    public void showStudentRestriction() {
        if (student == null) {
            return;
        }
        List<Restriction> myRestrictions = student.getMyRestriction();
        MongoDBLogHelper.writeLog(student.getUserId(), "get restriction");
        if (myRestrictions.isEmpty()) {
            System.out.println("You don't have any restriction");
        } else {
            System.out.println("Your restrictions: ");
            for (Restriction myRestriction : myRestrictions) {
                System.out.println(myRestriction.getRestrictionName());
            }
        }
    }

    @Override
    public void registerStudentCourse(int inputCourseId) throws SQLException {
        if (student == null) {
            return;
        }
        boolean res = student.registerCourse(inputCourseId);
        if (res) {
            MongoDBLogHelper.writeLog(student.getUserId(), "register for course " + inputCourseId + ": success");
        } else {
            MongoDBLogHelper.writeLog(student.getUserId(), "register for course " + inputCourseId + ": fail");
        }
    }

    @Override
    public void dropStudentCourse(int inputCourseId) throws SQLException {
        if (student == null) {
            return;
        }
        boolean res = student.dropCourse(inputCourseId);
        if (res) {
            MongoDBLogHelper.writeLog(student.getUserId(), "drop course " + inputCourseId + ": success");
        } else {
            MongoDBLogHelper.writeLog(student.getUserId(), "drop course " + inputCourseId + ": fail");
        }
    }
}
