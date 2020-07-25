package dev.dewy.dqs.status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import dev.dewy.dqs.status.commands.KillCommand;
import dev.dewy.dqs.status.commands.SetCommand;
import net.daporkchop.lib.binary.oio.reader.UTF8FileReader;
import net.daporkchop.lib.common.misc.file.PFiles;
import net.daporkchop.lib.logging.Logging;
import net.daporkchop.lib.logging.impl.DefaultLogger;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

public class Main
{
    public static final String VERSION = "1.0.0";

    public static JDA discord;

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final File CONIFG_FILE = new File(System.getProperty("user.dir") + "/config.json");

    public static final DefaultLogger LOGGER = Logging.logger;

    public static Config CONFIG;

    @SuppressWarnings("all")
    public static void main(String[] args)
    {
        LOGGER.info("Starting DQS Status v%s...", VERSION);

        loadConfig();

        if (CONFIG.status.token.equals("default") || CONFIG.status.ownerId.equals("default"))
        {
            LOGGER.error("Please enter your configuration values properly.");

            Runtime.getRuntime().exit(-1);
        }

        CommandClientBuilder commandClient = new CommandClientBuilder();

        commandClient.setPrefix("&");
        commandClient.setActivity(Activity.watching("DQS"));
        commandClient.setOwnerId(CONFIG.status.ownerId);

        commandClient.addCommands(
                new SetCommand(),
                new KillCommand()
        );

        commandClient.setHelpWord("JBjEFIWHB213&£YRf8ucjfhu8Y£YRH8usah");

        try
        {
            LOGGER.info("Starting Discord bot...");

            discord = new JDABuilder(AccountType.BOT)
                    .setToken(CONFIG.status.token)
                    .addEventListeners(commandClient.build())
                    .build();
        } catch (LoginException e)
        {
            LOGGER.error(e);

            Runtime.getRuntime().exit(-1);
        }
    }

    private static void loadConfig()
    {
        Config config;

        if (PFiles.checkFileExists(CONIFG_FILE))
        {
            try (Reader reader = new UTF8FileReader(CONIFG_FILE))
            {
                config = GSON.fromJson(reader, Config.class);
            } catch (IOException e)
            {
                throw new RuntimeException("Unable to load config!", e);
            }
        } else
        {
            LOGGER.error(CONIFG_FILE.getAbsolutePath());

            config = new Config();
        }

        CONFIG = config.doPostLoad();
    }
}
