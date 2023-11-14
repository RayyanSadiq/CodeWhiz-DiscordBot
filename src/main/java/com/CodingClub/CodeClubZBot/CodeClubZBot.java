package com.CodingClub.CodeClubZBot;

import com.CodingClub.CodeClubZBot.listeners.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

// This is the main class of the bot. It is responsible for starting the bot and loading the config.
public class CodeClubZBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    public CodeClubZBot() {
        // Load the config from the .env file.
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        // Create the shard manager.
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("with code"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);

        // Build the shard manager.
        this.shardManager = builder.build();

        shardManager.addEventListener(new EventListener());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public Dotenv getConfig() {
        return config;
    }
}
