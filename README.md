
import java.util.Scanner;
import java.io.*;

public class StudentGradeTracker {

    static final int SUBJECTS = 5;
    static final int MAX_STUDENTS = 100;
    static final String FILE_NAME = "students.txt";

    // Student class
    static class Student {
        String name;
        int[] marks;
        int total;
        double average;
        String grade;
        String result;

        Student(String name, int[] marks) {
            this.name = name;
            this.marks = marks;

            for (int mark : marks) {
                total += mark;
            }

            average = total / (double) SUBJECTS;
            calculateGrade();
        }

        void calculateGrade() {
            boolean passed = true;

            for (int mark : marks) {
                if (mark < 40) {
                    passed = false;
                    break;
                }
            }

            if (!passed) {
                grade = "F";
                result = "FAIL";
            } else {
                result = "PASS";

                if (average >= 90) {
                    grade = "A+";
                } else if (average >= 80) {
                    grade = "A";
                } else if (average >= 70) {
                    grade = "B";
                } else if (average >= 60) {
                    grade = "C";
                } else if (average >= 50) {
                    grade = "D";
                } else {
                    grade = "E";
                }
            }
        }

        void displayMarksheet() {
            System.out.println("\n================================");
            System.out.println("        STUDENT MARKSHEET");
            System.out.println("================================");
            System.out.println("Student Name: " + name);

            for (int i = 0; i < SUBJECTS; i++) {
                System.out.println(
                    "Subject " + (i + 1) + ": " + marks[i]
                );
            }

            System.out.println("--------------------------------");
            System.out.println("Total: " + total + " / 500");
            System.out.printf("Average: %.2f%%\n", average);
            System.out.println("Grade: " + grade);
            System.out.println("Result: " + result);
            System.out.println("================================");
        }
    }

    // Read integer safely
    static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } else {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }
    }

    // Save all student records to file
    static boolean saveStudents(Student[] students, int count) {
        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (int i = 0; i < count; i++) {
                Student s = students[i];

                // Store name and marks separated by commas
                writer.write(s.name.replace(",", " ") );

                for (int mark : s.marks) {
                    writer.write("," + mark);
                }

                writer.newLine();
            }

            return true;

        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
            return false;
        }
    }

    // Load student records from file
    static int loadStudents(Student[] students) {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No previous records found. Starting fresh.");
            return 0;
        }

        int count = 0;

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null
                    && count < MAX_STUDENTS) {

                String[] data = line.split(",");

                // Name + 5 marks = 6 values
                if (data.length != SUBJECTS + 1) {
                    System.out.println("Skipping invalid record.");
                    continue;
                }

                String name = data[0];
                int[] marks = new int[SUBJECTS];

                boolean valid = true;

                try {
                    for (int i = 0; i < SUBJECTS; i++) {
                        marks[i] = Integer.parseInt(data[i + 1]);

                        if (marks[i] < 0 || marks[i] > 100) {
                            valid = false;
                        }
                    }
                } catch (NumberFormatException e) {
                    valid = false;
                }

                if (valid) {
                    students[count] = new Student(name, marks);
                    count++;
                } else {
                    System.out.println("Skipping invalid record.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }

        return count;
    }

    // Add a new student
    static Student addStudent(Scanner sc) {
        System.out.println("\n--- ADD NEW STUDENT ---");

        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter name: ");
            name = sc.nextLine().trim();
        }

        int[] marks = new int[SUBJECTS];

        for (int i = 0; i < SUBJECTS; i++) {
            while (true) {
                int mark = readInt(
                    sc,
                    "Enter marks for Subject " + (i + 1) + " (0-100): "
                );

                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                    break;
                } else {
                    System.out.println("Marks must be between 0 and 100.");
                }
            }
        }

        return new Student(name, marks);
    }

    // View all marksheets
    static void displayAll(Student[] students, int count) {
        if (count == 0) {
            System.out.println("\nNo student records available.");
            return;
        }

        for (int i = 0; i < count; i++) {
            students[i].displayMarksheet();
        }
    }

    // Class performance summary
    static void displaySummary(Student[] students, int count) {
        if (count == 0) {
            System.out.println("\nNo records available for summary.");
            return;
        }

        int passed = 0;
        int failed = 0;
        double totalAverage = 0;

        Student topper = students[0];

        for (int i = 0; i < count; i++) {
            Student s = students[i];

            totalAverage += s.average;

            if (s.result.equals("PASS")) {
                passed++;
            } else {
                failed++;
            }

            if (s.average > topper.average) {
                topper = s;
            }
        }

        System.out.println("\n======= CLASS SUMMARY =======");
        System.out.println("Total Students: " + count);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);

        System.out.printf(
            "Class Average: %.2f%%\n", totalAverage / count
        );

        System.out.println(
            "Topper: " + topper.name +
            " (" + String.format("%.2f", topper.average) + "%)"
        );

        System.out.println("=============================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Student[] students = new Student[MAX_STUDENTS];

        // Load previously saved records
        int count = loadStudents(students);

        System.out.println("Welcome to Student Grade Tracker!");
        System.out.println("Loaded student records: " + count);

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Marksheets");
            System.out.println("3. Class Performance Summary");
            System.out.println("4. Exit");

            int choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    if (count < MAX_STUDENTS) {
                        Student newStudent = addStudent(sc);

                        students[count] = newStudent;
                        count++;

                        // Save immediately after adding
                        if (saveStudents(students, count)) {
                            System.out.println("Student added successfully!");
                            System.out.println("Records saved to " + FILE_NAME);
                        }
                    } else {
                        System.out.println("Student limit reached!");
                    }
                    break;

                case 2:
                    displayAll(students, count);
                    break;

                case 3:
                    displaySummary(students, count);
                    break;

                case 4:
                    // Save again before exiting
                    saveStudents(students, count);

                    System.out.println("Records saved. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select 1-4.");
            }
        }
    }
}
