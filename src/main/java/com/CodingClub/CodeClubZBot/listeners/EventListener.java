package com.CodingClub.CodeClubZBot.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().toLowerCase();
        if (message.contains("bot")) {
            event.getChannel().sendMessage("yes @" + event.getAuthor().getName()).queue();
        }

//        if (message.contains("teach")) {
//            event.getChannel().sendMessage("Hey , I appreciate your perspective, but our decision to have a " +
//                    "rule against racism in the server is aimed at creating a welcoming and inclusive community for " +
//                    "everyone. We believe in fostering positive interactions and discussions. If you have any concerns " +
//                    "or suggestions, feel free to share them. Let's work together to maintain a respectful environment " +
//                    "for all members.").queue();
//        }
    }
    
}
