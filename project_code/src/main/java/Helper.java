import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Helper Class provide some useful tools, including getting a Course Object given the course id,
getting a list of course objects given the data in database.
It's a tool class, functions are static, so they can be called using class name without instantiation
 */

public class Helper {

    /* get a Course Object given the course id */
    public static Course getCourseById(int courseId, List<Course> courseList) {
        for (Course course: courseList){
            if (course.getCourseId() == courseId){
                return course;
            }
        }
        return null;
    }

    /*
     Retrieve course data from database and create Course objects
     return a list of Course object representing all courses
    */
    public static List<Course> getCourseList() throws SQLException {
        List<Course> courseList = new ArrayList<>();
        List<Room> roomList = getRoomList();
        List<Instructor> instructorList = getInstructorList();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            String query = "select * from Course";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int instructorId = rs.getInt(3);
                Instructor instructor = getInstructorById(instructorId, instructorList);
                int roomId = rs.getInt(4);
                Room room = getRoomById(roomId, roomList);
                Course course = Factory.createCourse(rs.getInt(1), rs.getString(2), instructor,
                        room, rs.getString(5), rs.getString(6), rs.getString(7));
                courseList.add(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return courseList;
    }

    /*
     Retrieve instructor data from database and create instructor objects
     return a list of Instructor objects
     In bounded contexts I chose, this method are only used in getInstructorById() and getCourseList(), so I made it a private method
    */
    private static List<Instructor> getInstructorList() throws SQLException {
        List<Instructor> instructorList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            String query = "select * from User where type = 'instructor'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Instructor instructor = Factory.createInstructor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                instructorList.add(instructor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return instructorList;
    }

    /*
     Retrieve room data from database and create Room object
     return a list of Room objects
     In bounded contexts I chose, this method are only used in getRoomById() and getCourseList(), so I made it a private method
    */
    private static List<Room> getRoomList() throws SQLException {
        List<Room> roomList = new ArrayList<>();
        List<Building> buildingList = getBuildingList();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            String query = "select * from Room";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int buildingId = rs.getInt(3);
                Building building = getBuildingById(buildingId, buildingList);
                Room room = Factory.createRoom(rs.getInt(1), rs.getString(2), building, rs.getInt(4));
                roomList.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return roomList;
    }

    /*
     Retrieve building data from database and create Building object
     return a list of Building objects
     In bounded contexts I chose, this method are only used in getBuildingById() and getRoomList(), so I made it a private method
    */
    private static List<Building> getBuildingList() throws SQLException {
        List<Building> buildingList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/regie";
            String dbUserName = "root";
            String dbPassword = "20000712";
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = conn.createStatement();
            String query = "select * from Building";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Building building = Factory.createBuilding(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                buildingList.add(building);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return buildingList;
    }

    /*
    Get an Instructor Object given the instructor id
    In bounded contexts I chose, this method are only used in getCourseList(), so I made it a private method
    */
    private static Instructor getInstructorById(int instructorId, List<Instructor> instructorList) {
        for (Instructor instructor: instructorList){
            if (instructor.getUserId() == instructorId){
                return instructor;
            }
        }
        return null;
    }

    /*
    Get a Room Object given the room id
    In bounded contexts I chose, this method are only used in getCourseList(), so I made it a private method
    */
    private static Room getRoomById(int roomId, List<Room> roomList){
        for (Room room: roomList){
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    /*
    Get a Building Object given the building id
    In bounded contexts I chose, this method are only used in getRoomList(), so I made it a private method
    */
    private static Building getBuildingById(int buildingId, List<Building> buildingList) {
        for (Building building: buildingList) {
            if (building.getBuildingId() == buildingId) {
                return building;
            }
        }
        return null;
    }
}
