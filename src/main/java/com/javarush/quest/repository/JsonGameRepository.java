package com.javarush.quest.repository;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.GameType;
import com.javarush.quest.entity.Question;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public final class JsonGameRepository implements GameRepository {

    @Override
    public Question read(@NotNull GameType type, int id) {
        try {
            final String jsonPath = Config.resource.gamePath() + type.getJsonGameFile();
            final JSONParser jsonParser = new JSONParser();

            Object parsed = jsonParser.parse(new FileReader(jsonPath));
            JSONObject json = (JSONObject) parsed;

            String name = (String) json.get("Name");
            String at = (String) json.get("Posted at");

            System.out.println(name + " : " + at);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
