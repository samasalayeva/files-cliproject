package org.example.util;

import org.example.model.Student;

import java.util.Comparator;
import java.util.List;

public class CompareStudentsUtility {
    public static void compareToAge(List<Student> students){
        students.sort(Comparator.comparing(Student::getAge));
        students.stream().forEach(System.out::println);
    }
}
