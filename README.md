# Student Grade Tracker (Java)

A menu-driven Java console application that manages student marks, calculates grades, and generates class performance summaries. Student records are saved to a file so they can be accessed even after restarting the program.

## Features

- Add student records with name and marks in 5 subjects
- Validate marks (0–100) and prevent empty names
- Calculate total, average, grade, and PASS/FAIL result
- Generate individual student marksheets
- Display all saved student marksheets
- Show class performance summary, including pass/fail count, class average, and topper
- Store student records permanently in students.txt
- Load previously saved records when the program starts
- Support up to 100 student records

## Grading System

| Average | Grade |
|---|---|
| 90–100 | A+ |
| 80–89.99 | A |
| 70–79.99 | B |
| 60–69.99 | C |
| 50–59.99 | D |
| Below 50 | E |

A student must score at least 40 in every subject to pass. If any subject mark is below 40, the student receives an F grade and FAIL result.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Arrays
- File Handling (BufferedReader, BufferedWriter)
- Exception Handling

## How to Run

1. Install the Java JDK.
2. Download or clone this repository.
3. Open the project folder in VS Code or a terminal.
4. Compile the Java file:

   ```bash
   javac StudentGradeTracker.java
   ```

5. Run the application:

   ```bash
   java StudentGradeTracker
   ```

## Data Storage

Student records are stored in `students.txt` in the program's working directory. The application loads existing records at startup and saves records after adding a student.

Keep `students.txt` if you want to retain your records. If it is deleted, the saved student data will no longer be available.

## Future Improvements

- Search for a student by name
- Update or delete student records
- Export marksheets to a file

## Author

**Mohammed Tasleem**

GitHub: [MDTasleem-tech](https://github.com/MDTasleem-tech)
