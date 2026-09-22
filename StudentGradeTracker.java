
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        int[] marks = new int[5];
        int total = 0;
        boolean allPassed = true;

        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter marks for subject "
                    + (i + 1) + ": ");

            while (true) {
    marks[i] = sc.nextInt();

    if (marks[i] >= 0 && marks[i] <= 100) {
        break;
    }

    System.out.print("Invalid marks! Enter marks between 0 and 100: ");
}

        total = total + marks[i];
        if (marks[i] < 40) {
    allPassed = false;
}
        }

        double average = total / 5.0;

        System.out.println("\n----- REPORT CARD -----");
        System.out.println("Student: " + name);
        System.out.println("Total Marks: " + total);
        System.out.println("Average: " + average);

        if (average >= 90) {
            System.out.println("Grade: A");
        } else if (average >= 75) {
            System.out.println("Grade: B");
        } else if (average >= 60) {
            System.out.println("Grade: C");
        } else if (average >= 40) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }
        if (allPassed) {
    System.out.println("Result: PASS");
} else {
    System.out.println("Result: FAIL");
}
        sc.close();
    }
}
