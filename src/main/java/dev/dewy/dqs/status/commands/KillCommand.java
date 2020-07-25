package dev.dewy.dqs.status.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import dev.dewy.dqs.status.Main;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

@SuppressWarnings("all")
public class KillCommand extends Command
{
    public KillCommand()
    {
        this.name = "kill";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event)
    {
        try
        {
            if (!event.getAuthor().getId().equals(Main.CONFIG.status.ownerId))
            {
                event.reply(new EmbedBuilder()
                        .setTitle("**DQS Status** - Insufficient Permissions")
                        .setDescription("You do not have the required permissions to execute this command.")
                        .setColor(new Color(15221016))
                        .setAuthor("DQS Status " + Main.VERSION, null, "https://i.imgur.com/pcSOd3K.png")
                        .build());

                return;
            }

            if (event.getChannelType().isGuild())
            {
                event.reply(new EmbedBuilder()
                        .setTitle("**DQS Status** - Must Be Executed In DMs")
                        .setDescription("This command can not be executed in a guild channel.")
                        .setColor(new Color(15221016))
                        .setAuthor("DQS Status " + Main.VERSION, null, "https://i.imgur.com/pcSOd3K.png")
                        .build());

                return;
            }

            event.reply(new EmbedBuilder()
                    .setTitle("**DQS Status** - Kill")
                    .setDescription("Shutting down...")
                    .setColor(new Color(10144497))
                    .setAuthor("DQS Status " + Main.VERSION, null, "https://i.imgur.com/pcSOd3K.png")
                    .build());

            Thread.sleep(2000L);

            Runtime.getRuntime().exit(0);
        } catch (Exception e)
        {
            Main.LOGGER.error(e);
        }
    }
}
