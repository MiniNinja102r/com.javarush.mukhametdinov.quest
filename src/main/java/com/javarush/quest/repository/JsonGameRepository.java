package com.javarush.quest.repository;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.GameType;
import com.javarush.quest.entity.Question;
import com.javarush.quest.exception.JsonReadingException;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.net.URL;

public final class JsonGameRepository implements GameRepository {

    @Override
    public Question read(@NotNull GameType type, int id) {
        try {
            final URL jsonURL = GameRepository.class.getResource(Config.resource.gamePath() + type.getJsonGameFile());
            if (jsonURL == null)
                throw new JsonReadingException("Error couldn't find jsonURL: " + type.getJsonGameFile());

            final JSONParser jsonParser = new JSONParser();

            Object parsed = jsonParser.parse(new FileReader(jsonURL.getPath()));
            JSONObject json = (JSONObject) parsed;

            String name = (String) json.get("Name");
            String at = (String) json.get("Posted at");

            System.out.println(name + " : " + at);
        } catch (Exception e) {
            throw new JsonReadingException(e.getMessage());
        }
        return null;
    }
}
