import java.util.Scanner;

class Student {
    private String name;
    private int studentId;
    private String department;

    public Student(int studentId, String name, String department) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\nName: " + name + "\nDepartment: " + department;
    }
}

class Grade {
    private int studentId;
    private String courseId;
    private double grade;

    public Grade(int studentId, String courseId, double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public double getGrade() {
        return grade;
    }
}

public class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int studentCount;
    private int gradeCount;
    private static final int MAX_STUDENTS = 100;
    private static final int MAX_GRADES = 500;

    public GradingSystem() {
        students = new Student[MAX_STUDENTS];
        grades = new Grade[MAX_GRADES];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(int studentId, String name, String department) {
        if (studentCount < MAX_STUDENTS) {
            students[studentCount++] = new Student(studentId, name, department);
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student limit reached. Cannot add more students.");
        }
    }

    public void addGrade(int studentId, String courseId, double grade) {
        if (gradeCount < MAX_GRADES) {
            grades[gradeCount++] = new Grade(studentId, courseId, grade);
            System.out.println("Grade added successfully.");
        } else {
            System.out.println("Grade limit reached. Cannot add more grades.");
        }
    }

    public double calculateGPA(int studentId) {
        double totalGrades = 0;
        int count = 0;
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentId() == studentId) {
                totalGrades += grades[i].getGrade();
                count++;
            }
        }
        return count > 0 ? totalGrades / count : 0;
    }

    public void generateGradeReport(int studentId) {
        Student student = null;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId() == studentId) {
                student = students[i];
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println(student);
        System.out.println("Grades:");
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentId() == studentId) {
                System.out.println("Course ID: " + grades[i].getCourseId() + ", Grade: " + grades[i].getGrade());
            }
        }

        double gpa = calculateGPA(studentId);
        System.out.println("GPA: " + gpa);
    }

    public static void main(String[] args) {
        GradingSystem system = new GradingSystem();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Generate Grade Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String department = sc.nextLine();
                    system.addStudent(studentId, name, department);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    studentId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Course ID: ");
                    String courseId = sc.nextLine();
                    System.out.print("Enter Grade: ");
                    double grade = sc.nextDouble();
                    system.addGrade(studentId, courseId, grade);
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = sc.nextInt();
                    system.generateGradeReport(studentId);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}

