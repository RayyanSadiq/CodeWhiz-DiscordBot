package com.CodingClub.CodeClubZBot.Models;

import java.util.HashMap;

public class GradeReport {

    private final String username;
    private final HashMap<String, HashMap<String,Integer>> grades;
    private String date;

    public GradeReport(String username, HashMap<String, HashMap<String,Integer>> grades ) {
        this.username = username;
        this.grades = grades;

    }

    public String getUsername() {
        return username;
    }

    public HashMap<String, HashMap<String, Integer>> getGrades() {
        return grades;
    }

    public int getAverageSubjectGrade(String subject) {
        int total = 0;
        int count = 0;

        for (int grade : grades.get(subject).values()) {
            total += grade;
            count++;
        }

        return count == 0 ? 0 : total / count;

    }

    public int getAverageGrade() {
        int total = 0;
        int count = 0;

        for (HashMap<String, Integer> subjectGrades : grades.values()) {
            for (int grade : subjectGrades.values()) {
                total += grade;
                count++;
            }
        }

        return count == 0 ? 0 : total / count;
    }



}
