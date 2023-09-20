/*
The instructor class holds several attribute: userId, lastName, firstName, password
It is used in Course class
The getter methods in Room class help to get building information easily

In my project, I focus on student's activity in REGIE, so Instructor is just an attribute of Course class
In the whole REGIE system, instructor is am important actor
it should have a lot of methods like give grades, approve for courses....
Since instructor is not my target actor, I omitted a lot of functions which has nothing to do with my student's activity
 */

public class Instructor extends User{
    public Instructor(int userId, String lastName, String firstName, String password) {
        super(userId, lastName, firstName, password);
    }
}
