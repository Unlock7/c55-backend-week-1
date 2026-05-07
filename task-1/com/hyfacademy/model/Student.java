package com.hyfacademy.model;

import com.hyfacademy.util.GradeUtils;

public class Student {

    private  String name;
    private String studentId;
    private int[] grades;

    private static int totalStudents = 0;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.grades = new int[5];
        totalStudents++;
    }

    public String getName() {

        return name;
    }
    public String getStudentId() {
        return studentId;
    }
    public int[] getGrades() {

        return grades;
    }
    public static int getTotalStudents() {

        return totalStudents;
    }

    public void setGrade(int moduleIndex, int grade) {
        if (moduleIndex < 0 || moduleIndex >= GradeUtils.MODULE_COUNT) {
            System.out.println("Invalid module index: " + moduleIndex);
            return;
        }
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade: " + grade);
            return;
        }
        grades[moduleIndex] = grade;
    }
    public String toString() {
        double avg = GradeUtils.calculateAverage(grades);
        String letter = GradeUtils.getLetterGrade(avg);
        String status = GradeUtils.isPassing(avg) ? "PASS" : "FAIL";

        return String.format("[%s] %s - Avg: %.2f - %s",
                studentId, name, avg, status);
    }
}