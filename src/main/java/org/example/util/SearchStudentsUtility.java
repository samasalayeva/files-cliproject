package org.example.util;

import org.example.model.Student;

import java.util.List;

public class SearchStudentsUtility {
    public static void searchBySpeciality(List<Student> students, String speciality){
        students.stream().filter(s -> s.getSpeciality().equals(speciality)).forEach(System.out::println);
    }
}
