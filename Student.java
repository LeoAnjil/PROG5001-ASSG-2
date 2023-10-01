/**
 * a simple program to compute statistics of student's marks in an assignment.
 * @author (Anjil Khadka)
 * @version (01/10/2023)
 */

    class Student {
    //Variable Declaration
    String lastName;
    String firstName;
    String lastName;
    String studentID;
    double ass1Mark;
    double ass2Mark;
    double ass3Mark;
    double totalMark;

    public Student(String lastName, String firstName, String studentID, double ass1Mark, double ass2Mark, double ass3Mark) {
        this.lastName = lastName;
    public Student(String firstName, String lastName, String studentID, double ass1Mark, double ass2Mark, double ass3Mark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.ass1Mark = ass1Mark;
        this.ass2Mark = ass2Mark;
@@ -139,4 +139,21 @@ private static void studentsBelowThreshold(Student[] students, double threshold)
            }
        }
    }
    private static void sortStudentUsingBubbleSort(Student[] students, boolean ascending) {
        int num = students.length;
        for (int j = 0; j < num - 1; j++) {
            for (int k = 0; k < num - j - 1; k++) {
                if (students[k] != null && students[k + 1] != null) {
                    double mark1 = students[k].totalMark;
                    double mark2 = students[k + 1].totalMark;

                    if ((ascending && mark1 > mark2) || (!ascending && mark1 < mark2)) {
                        Student temp = students[k];
                        students[k] = students[k + 1];
                        students[k + 1] = temp;
                    }
                }
            }
        }
    }
}