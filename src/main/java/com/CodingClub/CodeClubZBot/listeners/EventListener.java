package com.CodingClub.CodeClubZBot.listeners;

import com.CodingClub.CodeClubZBot.QuestionHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class EventListener extends ListenerAdapter {

    private final String slashCommand = "\\";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().toLowerCase();
        if (message.contains(slashCommand + "bot")) {
            event.getChannel().sendMessage("yes " + event.getAuthor().getName()).queue();
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


    }
    
}
