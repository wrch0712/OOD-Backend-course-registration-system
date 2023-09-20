/*
The Course class holds several attribute: courseId, courseName, instructor, room, day, time, status
It is used in Student class
The getter methods in Course class help to get course information easily

In my project, I focus on student's activity in REGIE
So I may omit some functions, such as administrator can cancel courses for courses less than 5 student
 */

public class Course {
    private int courseId;
    private String courseName;
    private Instructor instructor;
    private Room room;
    private String day;
    private String time;
    private String status;

    public Course(int courseId, String courseName, Instructor instructor, Room room, String day, String time, String status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.room = room;
        this.day = day;
        this.time = time;
        this.status = status;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Room getRoom() {
        return room;
    }
    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
}
