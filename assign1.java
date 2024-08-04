
import java.util.Scanner;

class Student {
    private String name;
    private int studentId;
    private int age;
    private String department;

    Scanner sc = new Scanner(System.in);

    public void getData() {
        System.out.print("Enter your name : ");
        name = sc.nextLine();
        System.out.print("Enter your student id :");
        studentId = sc.nextInt();
        System.out.print("Enter the age of student : ");
        age = sc.nextInt();
        System.out.print("Enter the department : ");
        sc.nextLine();
        department = sc.nextLine();
    }

    public void Display() {
        System.out.println("Name : " + name);
        System.out.println("Student Id : " + studentId);
        System.out.println("Age :" + age);
        System.out.println("Department : " + department);
        System.out.println();
    }

    public int getstudentId() {
        return studentId;
    }
}

public class assign1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Student[] s = new Student[100];
        int n = 1; 
         int studentCount = 0;
        int choice = 0;
        while (choice != 3) {
            System.out.println("1.Add Student");
            System.out.println("2.Display Student");
            System.out.println("3.Exit");
            System.out.print("Enter your choice : ");
            
            choice = sc.nextInt();
           
            switch (choice) {
                case 1:
                    s[studentCount] = new Student();
                    s[studentCount].getData();
                    studentCount++;
                    System.out.println(studentCount);
                    break;
                case 2:
                    System.out.println("Enter the id to retrive : ");
                    int id = sc.nextInt();
                    System.out.println(studentCount);
                    for (int i = 0; i < studentCount; i++) {
                        if (s[i].getstudentId() == id) {
                            s[i].Display();
                        }
                    }
                    break;
                case 3:
                    break;
                default:
                    throw new AssertionError();
            }

        }
    }
}