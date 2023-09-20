import db.MongoDBLogHelper;

import java.sql.SQLException;

/*
Entrance of the program
How to test :
You can try ID: 123105, password: password5 to log in (student without register restriction)
You can try ID: 123103, password: password3 to log in (student with register restriction)
You can also try some wrong IDs and passwords to test whether the system can handle wrong inputs.
Then, follow the instruction printed in the console.
Note that the valid course IDs are integers from 1 to 17 in my sample database.
You can also try some wrong course IDs to test whether the system can handle wrong inputs.
 */
public class Main {
    public static void main(String[] args) throws SQLException {

        // run REGIE student system
        RegieStudentSystemRunEngine.run();

        // if you want to view the 5 latest log
        System.out.println("\n5 latest log: ");
        MongoDBLogHelper.showLog(5);
    }
}

/*
How does my implementation adhere to SOLID principles:
1.	Single Responsibility Principle: Each class in my implementation has a single responsibility,
and each method within the class is responsible for a single task.
For example, the User class is responsible for managing user information and authentication,
while the Student class is responsible for managing student-specific actions: getMyCourse, getMyRestriction,
getMySchedule, getMyGrade, getMyTranscript, registerCourse, dropCourse.
2.	Open/Closed Principle: my implementation adheres to the OCP by using inheritance and polymorphism.
For example, the User class is an abstract class that is extended by the Student class.
This allows adding new types of users in the future without modifying the existing code.
Similarly, the RegieSystem class is an abstract class that is extended by the RegieStudentSystem class.
3.	Liskov Substitution Principle: my implementation adheres to the LSP by ensuring that the
subclasses (Student, RegieStudentSystem) can be substituted for their parent class (User, RegieSystem)
without affecting the correctness of the program. This means that any method that expects a User object
can also accept a Student object, which allows to add specific functionality to the system without modifying the existing code.
4.	Interface Segregation Principle: my implementation adheres to the ISP by defining interfaces (UserInterface, StudentInterface, RegieStudentSystemInterface, and RegieStudentSystemInterface)
that contain only the methods that are relevant to their users.
For example, the RegieStudentSystem only contains methods that are relevant to students.
5.	Dependency Inversion Principle: my implementation adheres to the DIP by using abstraction to decouple high-level modules
from low-level modules. For example, the RegieSystem class depends on the abstract User class,
rather than on specific implementations of the User class.
 */
