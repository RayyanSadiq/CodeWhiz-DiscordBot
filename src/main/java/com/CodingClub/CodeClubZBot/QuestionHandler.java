package com.CodingClub.CodeClubZBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class QuestionHandler {

    public static MultipleChoiceQuestion getMultipleChoiceQuestion(int number){
        File file = new File("src/main/resources/QuestionAndAnswers.txt");
        try(Scanner scanner = new Scanner(file)) {

            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.startsWith("Q"+number)){
                    var MCQ = new MultipleChoiceQuestion();
                    MCQ.setQuestion(line);
                    MCQ.setAnswerA(scanner.nextLine());
                    MCQ.setAnswerB(scanner.nextLine());
                    MCQ.setAnswerC(scanner.nextLine());
                    MCQ.setAnswerD(scanner.nextLine());
                    return MCQ;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
