import db.MongoDBLogHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*
RegieSystem is an abstract class and represent for the functionality that the general RIGIE system should have
RegieSystem is the super class for RegieStudentSystem, RegieInstructorSystem, RegieAdministratorSystem
RegieSystem class cannot be instantiated directly, it should be instantiated through a subclass (polymorphism)
My project focus on Student's activity, so I omitted RegieInstructorSystem, RegieAdministratorSystem
RegieSystem implements RegieSystemInterface, so it need to override all methods of RegieSystemInterface
RegieSystem class holds a user attribute and methods for general REGIE system
Methods include logIn, changePassword, showAllCourses, showCourseInfo
*/


public abstract class RegieSystem implements RegisSystemInterface{
    private User user = null; // initialized with null, will be changed if user log in successfully

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public boolean login(int inputUserId, String inputPassword) throws SQLException {
        user = LoginEngine.login(inputUserId, inputPassword);
        if (user == null) {
            System.out.println("Log in failed.");
            return false;
        } else {
            System.out.println("Log in successfully.");
            System.out.println("Welcome! " + user.getLastName() + " " + user.getFirstName());
            return true;
        }
    }

    @Override
    public boolean changePassword(String newPassword) throws SQLException {
        MongoDBLogHelper.writeLog(user.getUserId(), "change password");
        if (user != null) {
            return user.changePassword(newPassword);
        }
        return false;
    }

    @Override
    public void showAllCourses() throws SQLException {
        MongoDBLogHelper.writeLog(user.getUserId(), "show all courses in the university");
        if (user == null) {
            return;
        }
        List<Course> allCourses = user.getAllCourse();
        for (Course course : allCourses) {
            System.out.println(course.getCourseId() + " " + course.getCourseName() + ", " + course.getStatus());
        }
    }

    @Override
    public void showCourseInfo(int inputCourseId) throws SQLException {
        if (user == null) {
            return;
        }
        try {
            System.out.println(user.getCourseInfo(inputCourseId));
            MongoDBLogHelper.writeLog(user.getUserId(), "show info of course" + inputCourseId);
        }catch (Exception e) {
            System.out.println("Your input course is incorrect!");
        }

    }

}
