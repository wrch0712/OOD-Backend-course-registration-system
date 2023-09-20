import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
Student are main actor of my project
Students are users of RIGIE system, so it should have methods that general users have. Student class extends User class
Student implements StudentInterface, so it need to override all methods of StudentInterface
Attributes include userId, lastName, firstName, password, myCourses, myRestrictions
Methods include logIn, changePassword, getCourseInfo, getMyCourse, getMyRestriction, getMySchedule,
getMyGrade, getMyTranscript, registerCourse, dropCourse
 */

public class Student extends User implements StudentInterface{
    // We assume that the ID of each user is different,
    // id of teachers, students, and administrators use one system, and there is no duplication between them
    // For students, studentId is same as userId
    private List<Course> myCourses;
    private List<Restriction> myRestrictions;

    public Student(int userId, String lastName, String firstName, String password) {
        super(userId, lastName, firstName, password);
        myCourses = new ArrayList<>();
        myRestrictions = new ArrayList<>();
    }

    /* insert data into myCourses and myRestrictions after log in*/
    public void initialize() throws SQLException {
        List<Course> allCourses = getAllCourse();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();

            // insert data into myCourses
            String query1 = String.format("select * from StudentCourse where student_id = '%d'", getUserId());
            ResultSet rs1 = stmt.executeQuery(query1);
            while (rs1.next()) {
                int myCourseId = rs1.getInt(3);
                Course myCourse = Helper.getCourseById(myCourseId, allCourses);
                myCourses.add(myCourse);
            }

            // insert data into myRestrictions
            String query2 = String.format("select b.* from StudentRestriction a " +
                    "left join Restriction b on a.restriction_id = b.restriction_id " +
                    "where a.student_id = '%d';", getUserId());
            ResultSet rs2 = stmt.executeQuery(query2);
            while (rs2.next()) {
                Restriction myRestriction = Factory.createRestriction(rs2.getInt(1), rs2.getString(2));
                myRestrictions.add(myRestriction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
    }

    @Override
    public List<Course> getMyCourse() {
        return myCourses;
    }

    @Override
    public List<Restriction> getMyRestriction() {
        return myRestrictions;
    }

    @Override
    public List<String> getMySchedule() {
        List<String> res = new ArrayList<>();
        for (Course myCourse: myCourses){
            if (myCourse.getStatus().equals("On Going")){
                res.add("course id: " + myCourse.getCourseId() +
                        ", course name: " + myCourse.getCourseName() +
                        ", building: " + myCourse.getRoom().getRoomBuilding().getBuildingName() +
                        ", floor: " + myCourse.getRoom().getFloor() +
                        ", room: " + myCourse.getRoom().getRoomName() +
                        ", time: " + myCourse.getDay() + " " + myCourse.getTime());
            }
        }
        return res;
    }

    @Override
    public String getMyGrade(int courseId) throws SQLException {
        // check if the course exists
        Course course = Helper.getCourseById(courseId, getAllCourse());
        if (course == null) {
            return "The course doesn't exist!";
        }
        String res = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            String query = String.format("select letter_grade from StudentCourse where student_id = '%s' and course_id ='%s';", getUserId(), courseId);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String grade = rs.getString(1);
                res = "Grade for course " + courseId + ": " + grade;
            } else {
                res = "No grade! You didn't take this class";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return res;
    }

    @Override
    public List<String> getMyTranscript () throws SQLException {
        List<String> res = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            String query = String.format("select a.course_id, b.course_name, a.letter_grade from StudentCourse a " +
                    "left join Course b on a.course_id = b.course_id " +
                    "where a.student_id = '%s';", getUserId());
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                res.add("course id: " + rs.getInt(1) +
                        ", course name: " + rs.getString(2) +
                        ", grade: " + rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return res;
    }

    @Override
    public boolean registerCourse (int inputCourseId) throws SQLException {
        // check if the student has restrictions
        if (!myRestrictions.isEmpty()) {
            System.out.println("Register failed! You have restrictions！");
            return false;
        }
        List<Course> allCourses = getAllCourse();
        // check if the course exist
        if (Helper.getCourseById(inputCourseId, allCourses) == null) {
            System.out.println("Register failed! input course doesn't exist!");
            return false;
        }
        // check if the student is already in the course
        if (Helper.getCourseById(inputCourseId, myCourses) != null) {
            System.out.println("Register failed! You have already registered for this course");
            return false;
        }
        // check if the course has already completed
        if (Helper.getCourseById(inputCourseId, allCourses).getStatus().equals("Completed")){
            System.out.println("Register failed! The course has already completed!");
            return false;
        }

        // register for course
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            String query = String.format("insert into StudentCourse (student_id, course_id) " +
                    "values ('%d', '%d');", getUserId(), inputCourseId);
            int rs = stmt.executeUpdate(query);
            if (rs == 1) {
                Course newCourse = Helper.getCourseById(inputCourseId, allCourses);
                myCourses.add(newCourse);
                System.out.println("Register for " + inputCourseId + " successfully!");
                stmt.close();
                conn.close();
                return true;
            } else {
                System.out.println("Register failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return false;
    }

    @Override
    public boolean dropCourse (int inputCourseId) throws SQLException {
        // check if the course exists
        Course course = Helper.getCourseById(inputCourseId, getAllCourse());
        if (course == null) {
            System.out.println("Drop course failed! The course doesn't exist!");
            return false;
        }
        // check if the student is already in the course
        boolean taken = false;
        for (Course myCourse: myCourses) {
            if (myCourse.getCourseId() == inputCourseId) {
                taken = true;
            }
        }
        if (!taken) {
            System.out.println("Drop course failed! You are not in this course!");
            return false;
        }

        // check if the course has already completed
        if (course.getStatus().equals("Completed")){
            System.out.println("Drop course failed! The course has already completed!");
            return false;
        }

        // drop course
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();

            String query = String.format("delete from StudentCourse where student_id = '%s' and course_id ='%s';", getUserId(), inputCourseId);
            int rs = stmt.executeUpdate(query);
            if (rs == 1) {
                myCourses.remove(course);
                System.out.println("Drop " + inputCourseId + " successfully!");
                stmt.close();
                conn.close();
                return true;
            } else {
                System.out.println("Drop course failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return false;
    }

}
