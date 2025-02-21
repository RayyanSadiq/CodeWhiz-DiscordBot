package com.CodingClub.CodeClubZBot.Handlers;


import com.CodingClub.CodeClubZBot.Models.GradeReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GradeHandler {
    private static final String GRADES_FILE_PATH = "src/main/resources/Grades.txt";

    public static GradeReport getGradeReport(String username) {
        HashMap<String, HashMap<String, Integer>> grades = new HashMap<>();

        HashMap<String, Integer> project_grades = new HashMap<>();
        HashMap<String, Integer> assignment_grades = new HashMap<>();

        boolean isUserFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(GRADES_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username)) {
                    isUserFound = true;
                }
                else if (isUserFound) {
                    if (line.isEmpty()) {
                        break;
                    }

                    String[] parts = line.split(":");
                    if (parts.length == 3) {
                        if (parts[0].equals("Project")) {
                            project_grades.put(parts[1], Integer.parseInt(parts[2]));
                        } else if (parts[0].equals("Assignment")) {
                            assignment_grades.put(parts[1], Integer.parseInt(parts[2]));
                        }
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        grades.put("Projects", project_grades);
        grades.put("Assignments", assignment_grades);

        if (!isUserFound) {
            return null;
        } else {
            return new GradeReport(username, grades );
        }


    }
}
