package com.vansh.sms;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Save & Exit");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 ->
                    addStudentFlow();
                case 2 ->
                    manager.listStudents();
                case 3 ->
                    searchStudentFlow();
                case 4 ->
                    updateStudentFlow();
                case 5 ->
                    deleteStudentFlow();
                case 6 -> {
                    manager.saveToFile();
                    System.out.println("Goodbye!");
                    return;
                }
                default ->
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addStudentFlow() {
        System.out.print("ID: ");
        int id = readInt();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = readInt();

        System.out.print("Course: ");
        String course = scanner.nextLine();

        System.out.print("Marks: ");
        double marks = readDouble();

        manager.addStudent(new Student(id, name, age, course, marks));
    }

    private static void searchStudentFlow() {
        System.out.print("Enter ID: ");
        int id = readInt();
        Student s = manager.findById(id);
        System.out.println(s == null ? "Student not found." : s);
    }

    private static void updateStudentFlow() {
        System.out.print("Enter ID to update: ");
        int id = readInt();

        System.out.print("New Name (or press Enter): ");
        String name = scanner.nextLine();
        if (name.isBlank()) {
            name = null;
        }

        System.out.print("New Age (or press Enter): ");
        String ageStr = scanner.nextLine();
        Integer age = ageStr.isBlank() ? null : Integer.parseInt(ageStr);

        System.out.print("New Course (or press Enter): ");
        String course = scanner.nextLine();
        if (course.isBlank()) {
            course = null;
        }

        System.out.print("New Marks (or press Enter): ");
        String marksStr = scanner.nextLine();
        Double marks = marksStr.isBlank() ? null : Double.parseDouble(marksStr);

        manager.updateStudent(id, name, age, course, marks);
    }

    private static void deleteStudentFlow() {
        System.out.print("Enter ID: ");
        int id = readInt();
        manager.removeStudent(id);
    }

    // Safe input methods
    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter number: ");
            scanner.next();
        }
        int n = scanner.nextInt();
        scanner.nextLine(); // clear newline
        return n;
    }

    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Enter number: ");
            scanner.next();
        }
        double n = scanner.nextDouble();
        scanner.nextLine();
        return n;
    }
}
