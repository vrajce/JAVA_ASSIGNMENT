import java.util.Scanner;

class Course {
    private String courseId;
    private String courseName;
    private int credits;

    public Course(String courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Course Name: " + courseName + ", Credits: " + credits;
    }
}

class Enrollment {
    private int studentId;
    private String courseId;

    public Enrollment(int studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }
}

public class CourseEnrollmentSystem {
    private Course[] courses;
    private Enrollment[] enrollments;
    private int courseCount;
    private int enrollmentCount;
    private static final int MAX_COURSES = 100;
    private static final int MAX_ENROLLMENTS = 500;

    public CourseEnrollmentSystem() {
        courses = new Course[MAX_COURSES];
        enrollments = new Enrollment[MAX_ENROLLMENTS];
        courseCount = 0;
        enrollmentCount = 0;
    }

    public void addCourse(String courseId, String courseName, int credits) {
        if (courseCount < MAX_COURSES) {
            courses[courseCount++] = new Course(courseId, courseName, credits);
            System.out.println("Course added successfully.");
        } else {
            System.out.println("Course limit reached. Cannot add more courses.");
        }
    }

    public void updateCourse(String courseId, String courseName, int credits) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseId().equals(courseId)) {
                courses[i].setCourseName(courseName);
                courses[i].setCredits(credits);
                System.out.println("Course updated successfully.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void deleteCourse(String courseId) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseId().equals(courseId)) {
                courses[i] = courses[--courseCount]; // Replace with last course and reduce count
                System.out.println("Course deleted successfully.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void enrollStudent(int studentId, String courseId) {
        if (enrollmentCount < MAX_ENROLLMENTS) {
            enrollments[enrollmentCount++] = new Enrollment(studentId, courseId);
            System.out.println("Student enrolled in course successfully.");
        } else {
            System.out.println("Enrollment limit reached. Cannot enroll more students.");
        }
    }

    public void dropCourse(int studentId, String courseId) {
        boolean found = false;
        for (int i = 0; i < enrollmentCount; i++) {
            if (enrollments[i].getStudentId() == studentId && enrollments[i].getCourseId().equals(courseId)) {
                enrollments[i] = enrollments[--enrollmentCount]; // Replace with last enrollment and reduce count
                found = true;
                System.out.println("Course dropped successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Enrollment not found.");
        }
    }

    public void viewEnrollments(int studentId) {
        boolean studentFound = false;
        System.out.println("Enrolled Courses for Student ID: " + studentId);
        for (int i = 0; i < enrollmentCount; i++) {
            if (enrollments[i].getStudentId() == studentId) {
                studentFound = true;
                for (int j = 0; j < courseCount; j++) {
                    if (courses[j].getCourseId().equals(enrollments[i].getCourseId())) {
                        System.out.println(courses[j]);
                    }
                }
            }
        }
        if (!studentFound) {
            System.out.println("No enrollments found for student ID: " + studentId);
        }
    }

    public static void main(String[] args) {
        CourseEnrollmentSystem system = new CourseEnrollmentSystem();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            System.out.println("\n--- Course Enrollment System Menu ---");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. Drop Course");
            System.out.println("6. View Enrollments");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Course ID: ");
                    String courseId = sc.next();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Course Name: ");
                    String courseName = sc.nextLine();
                    System.out.print("Enter Credits: ");
                    int credits = sc.nextInt();
                    system.addCourse(courseId, courseName, credits);
                    break;

                case 2:
                    System.out.print("Enter Course ID to Update: ");
                    courseId = sc.next();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter New Course Name: ");
                    courseName = sc.nextLine();
                    System.out.print("Enter New Credits: ");
                    credits = sc.nextInt();
                    system.updateCourse(courseId, courseName, credits);
                    break;

                case 3:
                    System.out.print("Enter Course ID to Delete: ");
                    courseId = sc.next();
                    system.deleteCourse(courseId);
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Course ID: ");
                    courseId = sc.nextLine();
                    system.enrollStudent(studentId, courseId);
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    studentId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Course ID: ");
                    courseId = sc.nextLine();
                    system.dropCourse(studentId, courseId);
                    break;

                case 6:
                    System.out.print("Enter Student ID: ");
                    studentId = sc.nextInt();
                    system.viewEnrollments(studentId);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}
