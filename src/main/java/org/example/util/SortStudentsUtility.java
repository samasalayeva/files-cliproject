package org.example.util;

import org.example.model.Student;

import java.util.Comparator;
import java.util.List;

public class SortStudentsUtility {
    public static void sort(List<Student> students){
       students.sort(Comparator.comparing(Student::getName));
    }
}
