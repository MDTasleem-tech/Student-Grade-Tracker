
import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    static class Student {
        String name;
        int[] marks = new int[5];

        Student(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }

        int getTotal() {
            int total = 0;
            for (int mark : marks) {
                total += mark;
            }
            return total;
        }

        double getAverage() {
            return getTotal() / 5.0;
        }

        int getHighest() {
            int highest = marks[0];
            for (int mark : marks) {
                if (mark > highest) highest = mark;
            }
            return highest;
        }

        int getLowest() {
            int lowest = marks[0];
            for (int mark : marks) {
                if (mark < lowest) lowest = mark;
            }
            return lowest;
        }

        String getGrade() {
            double avg = getAverage();
            if (avg >= 90) return "A+";
            if (avg >= 80) return "A";
            if (avg >= 70) return "B";
            if (avg >= 60) return "C";
            if (avg >= 50) return "D";
            return "F";
        }

        void display() {
            System.out.println("\nStudent: " + name);
            System.out.println("Total: " + getTotal());
            System.out.printf("Average: %.2f%n", getAverage());
            System.out.println("Highest mark: " + getHighest());
            System.out.println("Lowest mark: " + getLowest());
            System.out.println("Grade: " + getGrade());
        }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static String readName() {
        while (true) {
            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();
            if (!name.isEmpty()) return name;
            System.out.println("Name cannot be empty.");
        }
    }

    static int readMark(int subject) {
        while (true) {
            System.out.print("Enter marks for subject " + subject
                    + " (0-100): ");
            try {
                int mark = Integer.parseInt(sc.nextLine().trim());
                if (mark >= 0 && mark <= 100) return mark;
            } catch (NumberFormatException e) {
                // Ask again if the input is not a whole number.
            }
            System.out.println("Please enter a whole number from 0 to 100.");
        }
    }

    static void addStudent() {
        if (students.size() >= 100) {
            System.out.println("Maximum of 100 students reached.");
            return;
        }

        String name = readName();
        int[] marks = new int[5];

        for (int i = 0; i < marks.length; i++) {
            marks[i] = readMark(i + 1);
        }

        students.add(new Student(name, marks));
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records yet.");
            return;
        }

        for (Student student : students) {
            student.display();
            System.out.println("-------------------------");
        }
    }

    static void showSummary() {
        if (students.isEmpty()) {
            System.out.println("No student records to summarize.");
            return;
        }

        int totalMarks = 0;
        int highest = 0;
        int lowest = 100;
        Student topper = students.get(0);

        for (Student student : students) {
            totalMarks += student.getTotal();

            highest = Math.max(highest, student.getHighest());
            lowest = Math.min(lowest, student.getLowest());

            if (student.getAverage() > topper.getAverage()) {
                topper = student;
            }
        }

        double classAverage =
                totalMarks / (students.size() * 5.0);

        System.out.println("\n===== CLASS SUMMARY =====");
        System.out.println("Total students: " + students.size());
        System.out.printf("Class average: %.2f%n", classAverage);
        System.out.println("Highest subject mark: " + highest);
        System.out.println("Lowest subject mark: " + lowest);
        System.out.println("Topper: " + topper.name);
        System.out.printf("Topper average: %.2f%n",
                topper.getAverage());
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== STUDENT GRADE TRACKER =====");
            System.out.println("1. Add student");
            System.out.println("2. View all student reports");
            System.out.println("3. Class summary");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    viewStudents();
                    break;
                case "3":
                    showSummary();
                    break;
                case "4":
                    System.out.println("Thank you for using the tracker!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Enter 1, 2, 3 or 4.");
            }
        }
    }
}
