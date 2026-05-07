package com.hyfacademy.util;



public class GradeUtils {

    private GradeUtils() {}

    public static final int MODULE_PASS_MARK =  55;
    public static final double TRACK_PASS_AVERAGE = 60.0;
    public  static final int MODULE_COUNT = 5;

    public static final String[] MODULE_NAMES = {
            "Java Basics",
            "Control Flow",
            "OOP Fundamentals",
            "Arrays & Collection",
            "Input & Output"
    };

    public  static double calculateAverage (int[] grades) {
        int sum = 0;
        for (int g : grades) {
            sum+= g;
        }
        return sum / (double) MODULE_COUNT;
    }
        public  static boolean isPassing(double average) {
        return average >= TRACK_PASS_AVERAGE;
        }
    public static boolean isModulePassing(int grade) {
        return grade >= MODULE_PASS_MARK;
    }
        public static String getLetterGrade(double average) {
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return  "C";
        if (average >= 60) return  "D";
        return "F";
        }
        public  static String formatGrade(int grade) {

        return String.format("%3d", grade);
        }
}