package com.vansh.sms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.txt";

    public StudentManager() {
        loadFromFile();
    }

    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student added!");
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        students.forEach(System.out::println);
    }

    public Student findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean removeStudent(int id) {
        Student s = findById(id);
        if (s != null) {
            students.remove(s);
            System.out.println("Student removed.");
            return true;
        }
        System.out.println("Student not found.");
        return false;
    }

    public boolean updateStudent(int id, String name, Integer age,
            String course, Double marks) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }

        if (name != null) {
            s.setName(name);
        }
        if (age != null) {
            s.setAge(age);
        }
        if (course != null) {
            s.setCourse(course);
        }
        if (marks != null) {
            s.setMarks(marks);
        }

        return true;
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.getId() + "," + s.getName() + ","
                        + s.getAge() + "," + s.getCourse() + "," + s.getMarks());
            }
            System.out.println("Data saved!");
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length != 5) {
                    continue;
                }

                int id = Integer.parseInt(p[0]);
                String name = p[1];
                int age = Integer.parseInt(p[2]);
                String course = p[3];
                double marks = Double.parseDouble(p[4]);

                students.add(new Student(id, name, age, course, marks));
            }

        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }
}
