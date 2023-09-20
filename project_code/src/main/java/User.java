/*
User is an abstract class and represent for all users for REGIE system
User is the super class for Student, Instructor, Administrator
User class cannot be instantiated directly, it should be instantiated through a subclass (polymorphism)
My project focus on Student's activity, I omitted Administrator class and several function in Instructor class
User class holds attributes and methods for all users of REGIE system
User implements UserInterface, so it need to override all methods of UserInterface
Attributes include userId, lastName, firstName, password
Methods include logIn, changePassword, getCourseInfo
 */

import java.sql.*;
import java.util.List;

public abstract class User implements UserInterface{
    // We assume that the ID of each user is different,
    // id of teachers, students, and administrators use one system, and there is no duplication between them
    private int userId;
    private String lastName;
    private String firstName;
    private String password;

    public User(int userId, String lastName, String firstName, String password) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean changePassword(String newPassword) throws SQLException {
        if (newPassword.equals(password)) {
            System.out.println("Update password failed. New password is same as old password.");
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            int res = 0;
            String query = String.format("update User set password = '%s' where user_id = '%s'", newPassword, userId);
            res = stmt.executeUpdate(query);
            if (res == 1) {
                password = newPassword;
                System.out.println("Password updated successfully.");
                stmt.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        System.out.println("Update password failed.");
        return false;
    }

    @Override
    public List<Course> getAllCourse() throws SQLException {
        return Helper.getCourseList();
    }

    @Override
    public String getCourseInfo(int inputCourseId) throws SQLException {
        List<Course> allCourses =  Helper.getCourseList();
        Course course = Helper.getCourseById(inputCourseId, allCourses);
        if (course != null) {
            return "course id: " + course.getCourseId() +
                    "\ncourse name: " + course.getCourseName() +
                    "\ninstructor: " + course.getInstructor().getFirstName() + " " + course.getInstructor().getLastName() +
                    "\nbuilding: " + course.getRoom().getRoomBuilding().getBuildingName() +
                    "\nfloor: " + course.getRoom().getFloor() +
                    "\nroom: " + course.getRoom().getRoomName() +
                    "\ntime: " + course.getDay() + " " + course.getTime() +
                    "\nstatus: " +course.getStatus();
        } else {
            return "The course doesn't exist";
        }
    }
}
