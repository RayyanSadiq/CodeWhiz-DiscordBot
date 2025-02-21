package com.CodingClub.CodeClubZBot.EventListeners;

import com.CodingClub.CodeClubZBot.CodeClubZBot;
import com.CodingClub.CodeClubZBot.Handlers.GradeHandler;
import com.CodingClub.CodeClubZBot.Handlers.QuestionHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class EventListener extends ListenerAdapter {

    private final String slashCommand = "\\";


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().toLowerCase();
        String username = event.getAuthor().getName();

        if (message.trim().contains(slashCommand) && CodeClubZBot.is_dev_mode && !username.equals("dragonxdroid") ){
            event.getChannel().sendMessage("Sorry, Im currently unavailable due to being in Development mode, try again later").queue();
            return;
        }

        if (message.trim().contains(slashCommand + "bot")) {
            event.getChannel().sendMessage("Doing internal logging " + username).queue();

        }

        if (message.contains(slashCommand + "q")){
            var randomNumber = new Random().nextInt(20)+1;
            System.out.println(randomNumber);
            var question = QuestionHandler.getMultipleChoiceQuestion(randomNumber);
            event.getChannel().sendMessage(
                       question.getQuestion()+"\n"+
                            question.getAnswerA()+"\n"+
                            question.getAnswerB()+"\n"+
                            question.getAnswerC()+"\n"+
                            question.getAnswerD()
            ).queue();
        }



        // * Grade command
        if (message.contains(slashCommand + "grade")) {
            var gradeReport = GradeHandler.getGradeReport(username);
            if (gradeReport == null){
                event.getChannel().sendMessage(username + " You are not a registered member of coding club.").queue();
            }
            else {
                System.out.println(gradeReport.getGrades());


                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor(username + "'s Grade Book", event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl());
                builder.setTitle("Overall Grade: " + gradeReport.getAverageGrade() + "%");
                builder.setThumbnail(event.getAuthor().getAvatarUrl());


                builder.setDescription(
                        "This is your grade book, below is your average grade for each category of assignments " +
                        "\n\n\n----------------------------------------------------------"
                );

                gradeReport.getGrades().forEach((subject, subject_grades) ->
                        builder.addField(
                                "Overall " + subject + " Grade: " + gradeReport.getAverageSubjectGrade(subject) + "%",
                                "Type '\\" + subject + "' to see your grades for all " + subject + "s" +
                                "\n\n\n----------------------------------------------------------",
                                false
                        )
                );

                // ? null pointer exception may happen here
                builder.setColor(event.getMember().getColor());

                event.getChannel().sendMessageEmbeds(builder.build()).queue();


            }
        }

        // * Assignment Command
        if (message.contains(slashCommand + "assignment")) {
            var gradeReport = GradeHandler.getGradeReport(username);
            if (gradeReport == null){
                event.getChannel().sendMessage(username + " You are not a registered member of coding club.").queue();
            }

            else {
                var assignments = gradeReport.getGrades().get("Assignments");

                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor(username + "'s Assignments", event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl());
                builder.setTitle("Overall Assignment Grade: " + gradeReport.getAverageSubjectGrade("Assignments") + "%");

                builder.setDescription(
                        "These are all of your Assignments" +
                                "\n----------------------------------------------------------"
                );

                assignments.forEach((name, grade) ->
                        builder.addField(
                                "> " + name + ": " + grade + "%",
                                "",
                                false
                        )
                );

                // ? null pointer exception may happen here
                builder.setColor(event.getMember().getColor());

                event.getChannel().sendMessageEmbeds(builder.build()).queue();


            }
        }

        // * Project Command
        if (message.contains(slashCommand + "project")) {
            var gradeReport = GradeHandler.getGradeReport(username);
            if (gradeReport == null){
                event.getChannel().sendMessage(username + " You are not a registered member of coding club.").queue();
            }

            else {
                var projects = gradeReport.getGrades().get("Projects");

                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor(username + "'s Projects", event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl());
                builder.setTitle("Overall Projects Grade: " + gradeReport.getAverageSubjectGrade("Projects") + "%");

                builder.setDescription(
                        "These are all of your Projects" +
                                "\n----------------------------------------------------------"
                );

                projects.forEach((name, grade) ->
                        builder.addField(
                                "> " + name + ": " + grade + "%",
                                "",
                                false
                        )
                );

                // ? null pointer exception may happen here
                builder.setColor(event.getMember().getColor());

                event.getChannel().sendMessageEmbeds(builder.build()).queue();


            }
        }
    }
    
}
