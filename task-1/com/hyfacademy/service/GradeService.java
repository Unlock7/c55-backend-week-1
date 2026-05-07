package com.hyfacademy.service;

import com.hyfacademy.model.Student;
import com.hyfacademy.util.GradeUtils;
import java.util.Scanner;

public class GradeService {

    private static final int MAX_STUDENTS = 20;

    private Student[] students = new Student[MAX_STUDENTS];
    private int studentCount = 0;

    private Scanner scanner = new Scanner(System.in);


    private String generateStudentId() {
        int next = studentCount + 1;
        return String.format("HYF-%03d", next);
    }


    public void addStudent() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println(" Cannot add more students (max 20).");
            return;
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        String id = generateStudentId();

        Student s = new Student(name, id);
        students[studentCount] = s;
        studentCount++;

        System.out.println("Student added: " + s.getStudentId() + " — " + s.getName());
    }
    public void enterGrades() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Entering grades for: " + student.getName());

        for (int i = 0; i < GradeUtils.MODULE_COUNT; i++) {
            String moduleName = GradeUtils.MODULE_NAMES[i];

            while (true) {
                System.out.print("Enter grade for " + moduleName + ": ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                    continue;
                }

                int grade = scanner.nextInt();
                scanner.nextLine();

                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100.");
                    continue;
                }

                student.setGrade(i, grade);
                break;
            }
        }

        System.out.println("Grades saved successfully!");
    }

    public void viewAllStudents() {

        if (studentCount == 0) {
            System.out.println("No students have been added yet.");
            return;
        }

        System.out.println("══════════════════════════════════════════════════════════════");
        System.out.printf("  %-10s %-20s %-9s %-9s %-8s%n",
                "ID", "NAME", "AVERAGE", "GRADE", "STATUS");
        System.out.println("══════════════════════════════════════════════════════════════");

        int passing = 0;
        int failing = 0;

        for (int i = 0; i < studentCount; i++) {
            Student s = students[i];

            double avg = GradeUtils.calculateAverage(s.getGrades());
            String letter = GradeUtils.getLetterGrade(avg);
            boolean pass = GradeUtils.isPassing(avg);

            if (pass) passing++;
            else failing++;

            System.out.printf("  %-10s %-20s %-10.2f %-9s %-8s%n",
                    s.getStudentId(),
                    s.getName(),
                    avg,
                    letter,
                    pass ? "PASS" : "FAIL"
            );
        }

        System.out.println("══════════════════════════════════════════════════════════════");
        System.out.printf("  Total students: %d   Passing: %d   Failing: %d%n",
                studentCount, passing, failing);
        System.out.println("══════════════════════════════════════════════════════════════");
    }

    public void viewStudentReport() {

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("══════════════════════════════════════");
        System.out.println("  STUDENT REPORT");
        System.out.println("══════════════════════════════════════");
        System.out.println("  ID      : " + student.getStudentId());
        System.out.println("  Name    : " + student.getName());
        System.out.println("──────────────────────────────────────");
        System.out.println("  MODULE GRADES");
        System.out.println("──────────────────────────────────────");

        int[] grades = student.getGrades();

        for (int i = 0; i < GradeUtils.MODULE_COUNT; i++) {
            String moduleName = GradeUtils.MODULE_NAMES[i];
            int grade = grades[i];
            boolean pass = GradeUtils.isModulePassing(grade);

            System.out.printf("  %-22s : %3d   %s%n",
                    moduleName,
                    grade,
                    pass ? "PASS" : "FAIL"
            );
        }

        System.out.println("──────────────────────────────────────");

        double avg = GradeUtils.calculateAverage(grades);
        String letter = GradeUtils.getLetterGrade(avg);
        boolean trackPass = GradeUtils.isPassing(avg);

        System.out.printf("  Average  :  %.2f%n", avg);
        System.out.println("  Grade    :  " + letter);
        System.out.println("  Status   :  " + (trackPass ? "✓ PASS" : "✗ FAIL"));

        System.out.println("══════════════════════════════════════");
    }
    private Student findStudentById(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equalsIgnoreCase(id)) {
                return students[i];
            }
        }
        return null;
    }

    public void run() {

        while (true) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         HYF ACADEMY — GRADE MGR      ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("  1. Add student");
            System.out.println("  2. Enter grades");
            System.out.println("  3. View all students");
            System.out.println("  4. View student report");
            System.out.println("  5. Exit");
            System.out.println("══════════════════════════════════════");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addStudent();
                    break;

                case "2":
                    enterGrades();
                    break;

                case "3":
                    viewAllStudents();
                    break;

                case "4":
                    viewStudentReport();
                    break;

                case "5":
                    System.out.println("Exiting program... Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Please choose 1–5.");
            }

            System.out.println();
        }
    }



}


