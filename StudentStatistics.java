/**
 * a simple program to compute statistics of student's marks in an assignment.
 * @author (Anjil Khadka)
 * @version (01/10/2023)
 */

     return null;
        }
    }

    private static double parseDouble(String value) {
        try {
            if (!value.isEmpty() && value.matches("[0-9.]+")) {
                return Double.parseDouble(value);
            }
        } catch (NumberFormatException e) {
            // exception handling
        }
        return 0.0; // default value
    }
    private static void studentTotalMarkCalculation(Student[] students) {
        System.out.println("Student list with their name, student ID, assessment marks and the total mark:");
        for (Student student : students) {
@@ -113,4 +124,19 @@ private static void studentTotalMarkCalculation(Student[] students) {
            }
        }
    }

    private static void studentsBelowThreshold(Student[] students, double threshold) {
        System.out.println("Total marks below  " + threshold + ":");
        for (Student student : students) {
            if (student != null) { 
                // null check
                double totalMark = student.ass1Mark + student.ass2Mark + student.ass3Mark;
                if (totalMark < threshold) {
                    System.out.println("Name: " + student.firstName + " " + student.lastName +
                        ", Student ID: " + student.studentID +
                        ", Total Mark: " + totalMark);
                }
            }
        }
    }
}
