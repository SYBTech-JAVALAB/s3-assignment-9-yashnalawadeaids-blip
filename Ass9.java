import java.io.*;
import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private String grade;

    public Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + grade;
    }
}

public class StudentFileManager {
    private static final String FILE_NAME = "students.txt";

    // Ensure file exists
    private static void ensureFileExists() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("New file created: " + FILE_NAME);
        }
    }

    // Write student data (overwrite or append)
    private static void writeStudent(Student student, boolean append) throws IOException {
        ensureFileExists();
        try (FileWriter fw = new FileWriter(FILE_NAME, append);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(student.toString());
            bw.newLine();
        }
        System.out.println("Student record saved successfully.");
    }

    // Read and display all student data
    private static void readStudents() throws IOException {
        ensureFileExists();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Stored Student Records ---");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    System.out.println("ID: " + parts[0] + ", Name: " + parts[1] + ", Grade: " + parts[2]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\n--- Student Management Menu ---");
                System.out.println("1. Add Student (Append)");
                System.out.println("2. Add Student (Overwrite)");
                System.out.println("3. View All Students");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        String id1 = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name1 = sc.nextLine();
                        System.out.print("Enter Grade: ");
                        String grade1 = sc.nextLine();
                        writeStudent(new Student(id1, name1, grade1), true);
                        break;

                    case 2:
                        System.out.print("Enter ID: ");
                        String id2 = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name2 = sc.nextLine();
                        System.out.print("Enter Grade: ");
                        String grade2 = sc.nextLine();
                        writeStudent(new Student(id2, name2, grade2), false);
                        break;

                    case 3:
                        readStudents();
                        break;

                    case 4:
                        System.out.println("Exiting program...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (IOException e) {
            System.out.println("File handling error: " + e.getMessage());
        }
    }
}
