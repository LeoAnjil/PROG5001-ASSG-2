/**
 * a simple program to compute statistics of student's marks in an assignment.
 * @author (Anjil Khadka)
 * @version (01/10/2023)
 */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //allow user to enter the File Name
        System.out.print("Please Enter the file name with Extension: ");
        System.out.println(" \n **** \t Student Statistics \t ****");
        System.out.print("Enter the File Name with Extension: ");
        String fileName = scanner.nextLine();


        try {
            BufferedReader buff = new BufferedReader(new FileReader(fileName));
        ArrayList<Student> students = readStudentDataFromFile(fileName);

            String textLine;

            while ((textLine = buff.readLine()) != null) {
                System.out.println(textLine);
            }
        if (students != null) {
            while (true) {
                System.out.println("\n****** \t Menu \t ******");
                System.out.println("1: Calculate the Total Marks and Student Mark Details");
                System.out.println("2: Print the student name with total marks less than a threshold");
                System.out.println("3: Print the top 5 student with the highest total marks");
                System.out.println("4: Print the top 5 student with the lowest total marks");
                System.out.println("5: Exit the Program");
                System.out.print("Select an option (1 | 2 | 3 | 4 | 5): ");

            buff.close();
        } catch (IOException e) {
            System.err.println("Error reading student data from the file.");
        }
                int select = scanner.nextInt();

        scanner.close();
                switch (select) {
                    case 1:
                        studentTotalMarkCalculation(students);
                        break;
                    case 2:
                        System.out.print("Please enter the threshold Marks: ");
                        double threshold = scanner.nextDouble();
                        studentsBelowThreshold(students, threshold);
                        break;
                    case 3:
                        topNumStudentPrint(students, 5, false);
                        break;
                    case 4:
                        topNumStudentPrint(students, 5, true);
                        break;
                    case 5:
                        System.out.println("Program Exit");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("File Error: Select a valid option (1 | 2 | 3 | 4 | 5).");
                }
            }
        } else {
            System.out.println("File Error: Please check correct the file name and format.");
        }
    }

    private static Student[] readStudentDataFromFile(String fileName) {
    private static ArrayList<Student> readStudentDataFromFile(String fileName) {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            Student[] students = new Student[70]; 

            while ((line = reader.readLine()) != null) {
                lineNumber++;
@@ -79,37 +102,52 @@ private static Student[] readStudentDataFromFile(String fileName) {
                    String lastName = segment[0].trim();
                    String firstName = segment[1].trim();
                    String studentID = segment[2].trim();
                    double assg1Mark = Double.parseDouble(segment[3]);
                    double assg2Mark = Double.parseDouble(segment[4]);
                    double assg1Mark = parseDouble(segment[3]);
                    double assg2Mark = parseDouble(segment[4]);
                    double assg3Mark = 0.0; 
                    // Default value for ass 3 if missing

                    if (segment.length >= 6) { 
                        // Checking A3 if present
                        assg3Mark = Double.parseDouble(segment[5]);
                        assg3Mark = parseDouble(segment[5]);
                    }

                    students[lineNumber - 2] = new Student(lastName, firstName, studentID, assg1Mark, assg2Mark, assg3Mark);
                    // Check if studentID is a valid integer and all marks are valid (non-negative)
                    if (integerCheck (studentID) && assg1Mark >= 0 && assg2Mark >= 0 && assg3Mark >= 0) {
                        students.add(new Student(lastName, firstName, studentID, assg1Mark, assg2Mark, assg3Mark));
                }
            }
        }
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File Error: Please check the File");
            return null;
        }
    }

    private static boolean integerCheck(String str) {
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
            System.out.println("File Error: Please check the File Number Format.");
            // exception handling
        }
        return 0.0; // default value
    }
    private static void studentTotalMarkCalculation(Student[] students) {

    private static void studentTotalMarkCalculation(ArrayList<Student> students) {
        System.out.println("Student list with their name, student ID, assessment marks and the total mark:");
        for (Student student : students) {
            if (student != null) { 
@@ -125,35 +163,57 @@ private static void studentTotalMarkCalculation(Student[] students) {
        }
    }

    private static void studentsBelowThreshold(Student[] students, double threshold) {
    private static void studentsBelowThreshold(ArrayList<Student> students, double threshold) {
        System.out.println("Total marks below  " + threshold + ":");
        for (Student student : students) {
            if (student != null) { 
                // null check
                double totalMark = student.assg1Mark + student.assg2Mark + student.assg3Mark;
                if (totalMark < threshold) {
                    System.out.println("Name: " + student.firstName + " " + student.lastName +
                        ", Student ID: " + student.studentID +
                        ", Total Mark: " + totalMark);
                }
        for (Student student : students) { 
            double totalMark = student.assg1Mark + student.assg2Mark + student.assg3Mark;
            if (totalMark < threshold) {
               System.out.println("Name: " + student.firstName + " " + student.lastName +
                ", Student ID: " + student.studentID +
                ", Total Mark: " + totalMark);
            }
        }
    }
    private static void sortStudentUsingBubbleSort(Student[] students, boolean ascending) {
        int num = students.length;

    private static void sortStudentUsingBubbleSort(ArrayList<Student> students, boolean ascending) {
        int num = students.size();
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

                double mark1 = students.get(k).totalMark;
                double mark2 = students.get(k + 1).totalMark;
                //sorting order checking    
                boolean shouldSwap = ascending ? mark1 > mark2 : mark1 < mark2;

                if ((ascending && mark1 > mark2) || (!ascending && mark1 < mark2)) {
                    Student temp = students.get(k);
                    students.set(k, students.get(k + 1));
                    students.set(k + 1, temp);
                }
            }
        }
    }

    private static void topNumStudentPrint(ArrayList<Student> students, int num, boolean highest) {
        // Create a copy of the students ArrayList to avoid modifying the original list
        ArrayList<Student> copyStudents = new ArrayList<>(students);

        sortStudentUsingBubbleSort(copyStudents, highest); // Sort in the specified order

        String order = highest ? "highest" : "lowest";
        System.out.println("Top " + num + " students with " + order + " total marks:");

        // Actual number of students to print (up to num)
        int count = 0;
        for (int i = 0; i < copyStudents.size() && count < num; i++) {
            Student student = copyStudents.get(i);
            double totalMark = student.assg1Mark + student.assg2Mark + student.assg3Mark;
            if (totalMark > 0.0) { 
                // Skip students with total marks of 0.0
                System.out.println("Name: " + student.firstName + " " + student.lastName +
                    ", Student ID: " + student.studentID +
                    ", Total Mark: " + totalMark);
                count++;
            }
        }
    }
}