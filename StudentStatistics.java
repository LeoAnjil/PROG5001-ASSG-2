/**
 * a simple program to compute statistics of student's marks in an assignment.
 * @author (Anjil Khadka)
 * @version (01/10/2023)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String lastName;
    String firstName;
    String studentID;
    double assg1Mark;
    double assg2Mark;
    double assg3Mark;
    double totalMark;
    
    // F1: reads the unit name and students' marks from a given text file.
    public Student(String lastName, String firstName, String studentID, double a1Mark, double a2Mark, double a3Mark) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.assg1Mark = assg1Mark;
        this.assg2Mark = assg2Mark;
        this.assg3Mark = assg3Mark;
        calculateTotalMark();
    }

    private void calculateTotalMark() {
        totalMark = assg1Mark + assg2Mark + assg3Mark;
    }
}
  //F5:Create a simple menu system to allow users to select and execute each function.

public class StudentStatistics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********************************************");
        System.out.println("     Welcome to the Student Statistics       ");
        System.out.println("*********************************************");
        System.out.print("Please Enter the file name with Extension: ");
        String fileName = scanner.nextLine();

        ArrayList<Student> students = readStudentDataFromFile(fileName);

        if (students != null) {
            while (true) {
                System.out.println("\n*********************************************");
                System.out.println("                   Menu                      ");
                System.out.println("*********************************************");
                System.out.println("1. Calculate and Print Total Marks");
                System.out.println("2. Print Students Below a Threshold Marks");
                System.out.println("3. Print Top 5 Students with Highest Total Marks");
                System.out.println("4. Print Top 5 Students with Lowest Total Marks");
                System.out.println("5. Exit");
                System.out.print("Select an option (1 | 2 | 3 | 4 | 5): ");

                int choice = scanner.nextInt();
                    // F3: prints the list of students with total marks less than a certain threshold.
                switch (choice) {
                    case 1:
                        calculateTotalMarks(students);
                        break;
                    case 2:
                        System.out.print("Enter the threshold Marks: ");
                        double threshold = scanner.nextDouble();
                        studentsBelowThreshold(students, threshold);
                        break;
                    case 3:
                        printTopNStudents(students, 5, false);
                        break;
                    case 4:
                        printTopNStudents(students, 5, true);
                        break;
                    case 5:
                        System.out.println("Exiting the program.");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Error: Invalid option. Please select a valid option (1 | 2 | 3 | 4 | 5).");
                }
            }
        } else {
            System.out.println("Error: Unable to read student data from the file. Please check the file name and format.");
        }
    }

    private static ArrayList<Student> readStudentDataFromFile(String fileName) {
        ArrayList<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) {
                    // Skipping first line
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 5) { 
                    // Check for at least 5 columns (A1, A2, A3)
                    String lastName = parts[0].trim();
                    String firstName = parts[1].trim();
                    String studentID = parts[2].trim();
                    double assg1Mark = parseDouble(parts[3]);
                    double assg2Mark = parseDouble(parts[4]);
                    double assg3Mark = 0.0; // Default value if Assg3 is missing

                    if (parts.length >= 6) { 
                     // Check if Assg3 is present
                        assg3Mark = parseDouble(parts[5]);
                    }

                    // Check if studentID is a valid integer and all marks are valid (non-negative)
                    if (isInteger(studentID) && assg1Mark >= 0 && assg2Mark >= 0 && assg3Mark >= 0) {
                        students.add(new Student(lastName, firstName, studentID, assg1Mark, assg2Mark, assg3Mark));
                    }
                }
            }
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to read the file. Please check if the file exists and is accessible.");
            return null;
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double parseDouble(String value) {
        try {
            if (!value.isEmpty() && value.matches("[0-9.]+")) {
                return Double.parseDouble(value);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format . Please check the data format.");
        }
        return 0.0; // Default value if parsing fails or value is empty
    }
        //F2: calculates the total mark for each student from the assessment marks
    private static void calculateTotalMarks(ArrayList<Student> students) {
        System.out.println("List of Students with Assessment Marks and Total Marks:");
        for (Student student : students) {
            double totalMark = student.assg1Mark + student.assg2Mark + student.assg3Mark;
            System.out.println("Name: " + student.firstName + " " + student.lastName +
                ", Student ID: " + student.studentID +
                ", Assg1 Mark: " + student.assg1Mark +
                ", Assg2 Mark: " + student.assg2Mark +
                ", Assg3 Mark: " + student.assg3Mark +
                ", Total Mark: " + totalMark);
        }
    }

    private static void studentsBelowThreshold(ArrayList<Student> students, double threshold) {
        System.out.println("Students with total marks below " + threshold + ":");
        for (Student student : students) {
            double totalMark = student.assg1Mark + student.assg2Mark + student.assg3Mark;
            if (totalMark < threshold) {
                System.out.println("Name: " + student.firstName + " " + student.lastName +
                    ", Student ID: " + student.studentID +
                    ", Total Mark: " + totalMark);
            }
        }
    }
        //F4: prints the top 5 students with the highest total marks and the top 5 students with the lowest total marks. 
    private static void bubbleSortStudents(ArrayList<Student> students, boolean ascending) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                double mark1 = students.get(j).totalMark;
                double mark2 = students.get(j + 1).totalMark;

                // Compare based on sorting order (ascending or descending)
                boolean shouldSwap = ascending ? mark1 > mark2 : mark1 < mark2;

                if (shouldSwap) {

                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    private static void printTopNStudents(ArrayList<Student> students, int n, boolean highest) {
        // Create a copy of the students ArrayList to avoid modifying the original list
        ArrayList<Student> copyStudents = new ArrayList<>(students);

        bubbleSortStudents(copyStudents, highest); // Sort in the specified order

        String order = highest ? "highest" : "lowest";
        System.out.println("Top " + n + " students with " + order + " total marks:");

        // Find the actual number of students to print (up to n)
        int count = 0;
        for (int i = 0; i < copyStudents.size() && count < n; i++) {
            Student student = copyStudents.get(i);
            double totalMark = student.assg1Mark + student.assg2Mark + student.assg3Mark;
            if (totalMark > 0.0) { // Skip students with total marks of 0.0
                System.out.println("Name: " + student.firstName + " " + student.lastName +
                    ", Student ID: " + student.studentID +
                    ", Total Mark: " + totalMark);
                count++;
            }
        }
    }
}