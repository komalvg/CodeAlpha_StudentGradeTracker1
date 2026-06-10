import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker1 {
    
    private static final ArrayList<String> studentNames = new ArrayList<>();
    private static final ArrayList<Integer> studentGrades = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    addStudents();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    generateSummaryReport();
                    break;
                case 4:
                    System.out.println("Exiting application. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1‑4.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== Student Grade Tracker Menu ===");
        System.out.println("1. Add Students");
        System.out.println("2. View Students");
        System.out.println("3. Generate Summary Report");
        System.out.println("4. Exit");
    }

    private static void addStudents() {
        int numStudents = readInt("Enter number of students to add: ");
        if (numStudents <= 0) {
            System.out.println("Number of students must be positive.");
            return;
        }
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            String name = scanner.nextLine().trim();
            int grade = readIntWithValidation("Enter grade (0 - 100) for " + name + ": ");
            studentNames.add(name);
            studentGrades.add(grade);
            System.out.print("\n");
            
        }
        System.out.println(numStudents + " student(s) added successfully.");
    }

    private static void displayStudents() {
        if (studentNames.isEmpty()) {
            System.out.println("No students available. Please add students first.");
            return;
        }
        System.out.println("\n=== Student List ===");
        System.out.printf("%-20s %s\n", "Student Name", "Grade");
        System.out.println("-------------------------------");
        for (int i = 0; i < studentNames.size(); i++) {
            System.out.printf("%-20s %d\n", studentNames.get(i), studentGrades.get(i));
        }
    }

    private static double calculateAverage() {
        if (studentGrades.isEmpty()) return 0.0;
        int sum = 0;
        for (int grade : studentGrades) {
            sum += grade;
        }
        return (double) sum / studentGrades.size();
    }

    private static int[] findHighestScore() {
        if (studentGrades.isEmpty()) return new int[]{-1, -1};
        int maxGrade = studentGrades.get(0);
        int maxIndex = 0;
        for (int i = 1; i < studentGrades.size(); i++) {
            if (studentGrades.get(i) > maxGrade) {
                maxGrade = studentGrades.get(i);
                maxIndex = i;
            }
        }
        return new int[]{maxGrade, maxIndex};
    }

    private static int[] findLowestScore() {
        if (studentGrades.isEmpty()) return new int[]{-1, -1};
        int minGrade = studentGrades.get(0);
        int minIndex = 0;
        for (int i = 1; i < studentGrades.size(); i++) {
            if (studentGrades.get(i) < minGrade) {
                minGrade = studentGrades.get(i);
                minIndex = i;
            }
        }
        return new int[]{minGrade, minIndex};
    }

    private static void generateSummaryReport() {
        if (studentNames.isEmpty()) {
            System.out.println("No data to generate report. Add students first.");
            return;
        }
        System.out.println("\n=================================");
        System.out.println("STUDENT GRADE TRACKER");
        System.out.println("=================================");
        System.out.println("## Student Name      Grade");
        System.out.println();
        for (int i = 0; i < studentNames.size(); i++) {
            System.out.printf("%-20s %d\n", studentNames.get(i), studentGrades.get(i));
        }
        System.out.println("---");
        double avg = calculateAverage();
        int[] highest = findHighestScore();
        int[] lowest = findLowestScore();
        System.out.printf("Average Score : %.2f\n", avg);
        System.out.printf("Highest Score : %d (%s)\n", highest[0], studentNames.get(highest[1]));
        System.out.printf("Lowest Score  : %d (%s)\n", lowest[0], studentNames.get(lowest[1]));
        System.out.println("=========================");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    private static int readIntWithValidation(String prompt) {
        while (true) {
            int grade = readInt(prompt);
            if (grade >= 0 && grade <= 100) {
                return grade;
            } else {
                System.out.println("Grade must be between 0 and 100.");
            }
        }
    }
}
