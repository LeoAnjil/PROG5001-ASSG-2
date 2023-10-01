/**
 * a simple program to compute statistics of student's marks in an assignment.
 * @author (Anjil Khadka)
 * @version (01/10/2023)
 */

        this.assg1Mark = assg1Mark;
        this.assg2Mark = assg2Mark;
        this.ass3Mark = assg3Mark;
        totalMarkCalculation();

    }


    private void TotalMarkCalculation() {
        TotalMark = assg1Mark + assg2Mark + assg3Mark;
    }
}

public class StudentStatistics
{

@@ -37,6 +41,7 @@ public static void main(String[] args) {
        //allow user to enter the File Name
        System.out.print("Please Enter the file name with valid Extension: ");
        String fileName = scanner.nextLine();


        try {
            BufferedReader buff = new BufferedReader(new FileReader(fileName));
@@ -59,7 +64,7 @@ private static Student[] readStudentDataFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            Student[] std = new Student[70]; 
            Student[] students = new Student[70]; 

            while ((line = reader.readLine()) != null) {
                lineNumber++;
@@ -84,13 +89,28 @@ private static Student[] readStudentDataFromFile(String fileName) {
                        assg3Mark = Double.parseDouble(segment[5]);
                    }

                    std[lineNumber - 2] = new Student(lastName, firstName, studentID, ass1Mark, ass2Mark, ass3Mark);
                    students[lineNumber - 2] = new Student(lastName, firstName, studentID, assg1Mark, assg2Mark, assg3Mark);
                }
            }
            return std;
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static void studentTotalMarkCalculation(Student[] students) {
        System.out.println("Student list with their name, student ID, assessment marks and the total mark:");
        for (Student student : students) {
            if (student != null) { 
                // Checking if student is null
                double totalMark = student.ass1Mark + student.ass2Mark + student.ass3Mark;
                System.out.println("Student Name: " + student.firstName + " " + student.lastName +
                    ", Student ID: " + student.studentID +
                    ", Assignment 1 Mark: " + student.ass1Mark +
                    ", Assignment 2 Mark: " + student.ass2Mark +
                    ", Assignment 3 Mark: " + student.ass3Mark +
                    ", Total Mark: " + totalMark);
            }
        }
    }

}
