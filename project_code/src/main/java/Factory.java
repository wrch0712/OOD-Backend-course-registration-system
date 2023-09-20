/*
Factory class is used for create instance of various classes.
It embodies the factory pattern
All methods in Factory are static methods, so they can be called using class name without instantiation
 */

public class Factory {
    public static Student createStudent(int userId, String lastName, String firstName, String password) {
        return new Student(userId, lastName, firstName, password);
    }

    public static Instructor createInstructor(int userId, String lastName, String firstName, String password) {
        return new Instructor(userId, lastName, firstName, password);
    }

    public static Course createCourse(int courseId, String courseName, Instructor instructor, Room room, String day, String time, String status) {
        return new Course(courseId, courseName, instructor, room, day, time, status);
    }

    public static Room createRoom(int roomId, String roomName, Building roomBuilding, int floor) {
        return new Room(roomId, roomName, roomBuilding, floor);
    }

    public static Building createBuilding(int buildingId, String buildingName, String address, String postCode) {
        return new Building(buildingId, buildingName, address, postCode);
    }

    public static Restriction createRestriction(int restrictionId, String restrictionName) {
        return new Restriction(restrictionId, restrictionName);
    }
}
