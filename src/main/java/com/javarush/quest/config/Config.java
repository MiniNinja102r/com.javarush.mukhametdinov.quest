package com.javarush.quest.config;

import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Log
@UtilityClass
public final class Config {
    private static final String CONFIG_PATH = "/config/";
    private static final String SETTINGS_FILE = "settings.yaml";

    private static Map<String, Object> data;

    public static void load() {
        try (final InputStream input = Config.class.getResourceAsStream(CONFIG_PATH + SETTINGS_FILE)) {
            if (input == null) {
                log.severe("Config file not found!");
                return;
            }
            final Yaml yaml = new Yaml();
            data = yaml.load(input);
            loadConfig();
        } catch (Exception e) {
            //
        }
    }

    private static void loadConfig() {

    }
}
