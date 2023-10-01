/**
 * a simple program to compute statistics of student's marks in an assignment.
 * @author (Anjil Khadka)
 * @version (01/10/2023)
 */

import java.io.*;
import java.util.*;

class Student {
  
    //Variable Declaration
    String lastName;
    String firstName;
    String studentID;
    double assg1Mark;
    double assg2Mark;
    double assg3Mark;
    double totalMark;

    public Student(String lastName, String firstName, String studentID, double ass1Mark, double ass2Mark, double ass3Mark) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.assg1Mark = assg1Mark;
        this.assg2Mark = assg2Mark;
        this.assg3Mark = assg3Mark;

    }


}
public class StudentStatistics
{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //allows user to enter the File Name
        System.out.print("Please Enter the file name with Extension: ");
        String fileName = scanner.nextLine();

        try {
            BufferedReader buff = new BufferedReader(new FileReader(fileName));

            String textLine;

            while ((textLine = buff.readLine()) != null) {
                System.out.println(textLine);
            }

            buff.close();
        } catch (IOException e) {
            System.err.println("Error reading student data from the file.");
        }

        scanner.close();
    }

    private static Student[] readStudentDataFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            Student[] std = new Student[70]; 

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) {
                    //Skipping first line
                    continue;
                }

                String[] segment = line.split(",");
                if (segment.length >= 5) { 
                    // Check for at least 5 columns for three assignment marks
                    String lastName = segment[0].trim();
                    String firstName = segment[1].trim();
                    String studentID = segment[2].trim();
                    double assg1Mark = Double.parseDouble(segment[3]);
                    double assg2Mark = Double.parseDouble(segment[4]);
                    double assg3Mark = 0.0; 
                    // Default value for assg 3 if missing

                    if (segment.length >= 6) { 
                        // Checking Assg3 if present
                        assg3Mark = Double.parseDouble(segment[5]);
                    }

                    std[lineNumber - 2] = new Student(lastName, firstName, studentID, assg1Mark, assg2Mark, assg3Mark);
                }
            }
            return std;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
