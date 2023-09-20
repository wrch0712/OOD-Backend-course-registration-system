import java.sql.SQLException;
import java.util.Scanner;

/*
RegieStudentSystemRunEngine provides run method, which can run REGIE student system as a whole
 */
public class RegieStudentSystemRunEngine {
    public static void main(String[] args) throws SQLException {
        run();
    }
    public static void run() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to REGIE student system!");

        // log in
        RegieStudentSystem rss = new RegieStudentSystem();
        while (rss.getStudent() == null) {
            System.out.println("Please enter ID and password to log in. (enter 'exit' in ID to exit)");
            System.out.print("ID: ");
            String inputStudentIdString = sc.next();
            if (inputStudentIdString.equals("exit")) {
                System.out.println("Thank for using REGIE student system! Bye!");
                return;
            }
            try {
                Integer.parseInt(inputStudentIdString);
            } catch (Exception e) {
                System.out.println("ID should be an integer!");
                continue;
            }
            System.out.print("Password: ");
            String inputPassword = sc.next();
            rss.studentLogin(Integer.parseInt(inputStudentIdString), inputPassword);
        }


        // run REGIE student system for logged in student
        label:
        while (true){
            System.out.println();
            rss.showStudentMenu();
            System.out.print("Please choose what you want to do (enter the number): ");
            String choiceString = sc.next();
            System.out.println();
            int choiceNum;
            try {
                choiceNum = Integer.parseInt(choiceString);
            } catch (Exception e) {
                System.out.println("You should in put a number from 1 to 11!");
                continue;
            }
            if (choiceNum < 1 || choiceNum > 11) {
                System.out.println("You should in put a number from 1 to 11!");
                continue;
            }
            switch (choiceNum) {
                case 1 -> rss.showAllCourses();
                case 2 -> {
                    System.out.print("Enter the course id want to inquire: ");
                    try {
                        int inputCourseId = Integer.parseInt(sc.next());
                        rss.showCourseInfo(inputCourseId);
                    } catch (Exception e) {
                        System.out.println("Your input course is incorrect!");
                    }
                }
                case 3 -> rss.showStudentCourse();
                case 4 -> rss.showStudentSchedule();
                case 5 -> {
                    System.out.print("Enter the course id want to register: ");
                    try {
                        int inputCourseId = Integer.parseInt(sc.next());
                        rss.registerStudentCourse(inputCourseId);
                    } catch (Exception e) {
                        System.out.println("Your input course is incorrect!");
                    }
                }
                case 6 -> {
                    System.out.print("Enter the course id you want to drop: ");
                    try {
                        int inputCourseId = Integer.parseInt(sc.next());
                        rss.dropStudentCourse(inputCourseId);
                    } catch (Exception e) {
                        System.out.println("Your input course is incorrect!");
                    }
                }
                case 7 -> {
                    System.out.print("Enter the course id want to get grade: ");
                    try {
                        int inputCourseId = Integer.parseInt(sc.next());
                        rss.showStudentGrade(inputCourseId);
                    } catch (Exception e) {
                        System.out.println("Your input course is incorrect!");
                    }
                }
                case 8 -> rss.showStudentTranscript();
                case 9 -> rss.showStudentRestriction();
                case 10 -> {
                    System.out.print("New password: ");
                    String newPassword = sc.next();
                    rss.changePassword(newPassword);
                }
                case 11 -> {
                    System.out.println("Thank for using REGIE student system! Bye!");
                    return;
                }
            }
        }
    }
}
