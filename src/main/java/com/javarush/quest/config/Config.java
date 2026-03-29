package com.javarush.quest.config;

import com.javarush.quest.entity.GameType;
import com.javarush.quest.exception.ConfigReadingException;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Log
@UtilityClass
public final class Config {
    private static final String CONFIG_PATH = "/config/";
    private static final String SETTINGS_FILE = "settings.yaml";

    private static Map<String, Object> data;

    public static Resource resource;

    public static void load() {
        try (final InputStream input = Config.class.getResourceAsStream(CONFIG_PATH + SETTINGS_FILE)) {
            if (input == null) {
                log.severe("Config file not found!");
                return;
            }
            final Yaml yaml = new Yaml();
            data = yaml.load(input);
            loadConfig();
        } catch (IOException e) {
            throw new ConfigReadingException(e.getMessage());
        }
    }

    private static void loadConfig() {
        final Map<String, Object> section = (Map<String, Object>) data.get("resources");
        if (section == null) {
            log.severe("Resources section not found in config.");
            return;
        }
        String gamePath = (String) section.get("game_path");
        String defUsername = (String) section.get("default_username");
        GameType defGameType = GameType.valueOf((String) section.get("default_gametype"));
        resource = new Resource(gamePath, defUsername, defGameType);
    }

    public record Resource(String gamePath,
                           String defaultUsername,
                           GameType defaultGameType) {
    }
}
