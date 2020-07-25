package dev.dewy.dqs.status.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import dev.dewy.dqs.status.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;

import java.awt.*;

@SuppressWarnings("all")
public class SetCommand extends Command
{
    public SetCommand()
    {
        this.name = "set";
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

            String[] args = event.getArgs().split("\\s+");

            if (args.length != 1)
            {
                event.reply(new EmbedBuilder()
                        .setTitle("**DQS** - Invalid Command Arguments")
                        .setDescription("You have entered invalid arguments for this command. Sample usage can be found below.\n\n`&set online`\n`&set idle`\n`&set dnd`")
                        .setColor(new Color(15221016))
                        .setAuthor("DQS Status " + Main.VERSION, null, "https://i.imgur.com/pcSOd3K.png")
                        .build());

                return;
            }

            OnlineStatus status = OnlineStatus.fromKey(args[0]);

            if(status == OnlineStatus.UNKNOWN)
            {
                event.reply(new EmbedBuilder()
                        .setTitle("**DQS** - Invalid Command Arguments")
                        .setDescription("You have entered invalid arguments for this command. Sample usage can be found below.\n\n`&set online`\n`&set idle`\n`&set dnd`")
                        .setColor(new Color(15221016))
                        .setAuthor("DQS Status " + Main.VERSION, null, "https://i.imgur.com/pcSOd3K.png")
                        .build());
            }
            else
            {
                event.getJDA().getPresence().setStatus(status);

                event.reply(new EmbedBuilder()
                        .setTitle("**DQS** - Status Set")
                        .setDescription("The status has been set to **" + args[0] + "**.")
                        .setColor(new Color(10144497))
                        .setAuthor("DQS Status " + Main.VERSION, null, "https://i.imgur.com/pcSOd3K.png")
                        .build());
            }
        } catch (Exception e)
        {
            Main.LOGGER.error(e);
        }
    }
}
