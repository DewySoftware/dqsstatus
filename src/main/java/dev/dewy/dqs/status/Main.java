package dev.dewy.dqs.status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.daporkchop.lib.binary.oio.reader.UTF8FileReader;
import net.daporkchop.lib.common.misc.file.PFiles;
import net.daporkchop.lib.logging.Logging;
import net.daporkchop.lib.logging.impl.DefaultLogger;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

public class Main
{
    public static final String VERSION = "1.0.0-dev";

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final File CONIFG_FILE = new File(System.getProperty("user.dir") + "/config.json");
    public static final DefaultLogger LOGGER = Logging.logger;
    public static Config CONFIG;

    public static void main(String[] args)
    {
        LOGGER.info("Starting DQS Status v%s...", VERSION);

        loadConfig();
    }

    public static void loadConfig()
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
