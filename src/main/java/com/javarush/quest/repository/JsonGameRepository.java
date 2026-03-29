package com.javarush.quest.repository;

import com.javarush.quest.config.Config;
import com.javarush.quest.entity.Answer;
import com.javarush.quest.entity.EndingType;
import com.javarush.quest.entity.GameType;
import com.javarush.quest.entity.Question;
import com.javarush.quest.exception.JsonReadingException;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.net.URL;

public final class JsonGameRepository implements GameRepository {

    @Override
    public Question read(@NotNull GameType type, long id) {
        try {
            final URL jsonURL = GameRepository.class.getResource(Config.resource.gamePath() + type.getJsonGameFile());
            if (jsonURL == null)
                throw new JsonReadingException("Error couldn't find jsonURL: " + type.getJsonGameFile());

            final JSONParser jsonParser = new JSONParser();

            Object parsed = jsonParser.parse(new FileReader(jsonURL.getPath()));
            JSONObject json = (JSONObject) parsed;
            return this.readQuestionFromJson(json, id);
        } catch (Exception e) {
            throw new JsonReadingException(e.getMessage());
        }
    }

    private Question readQuestionFromJson(@NotNull JSONObject json, long id) {
        final JSONArray questions = (JSONArray) json.get("questions");
        for (Object o : questions) {
            final JSONObject qJson = (JSONObject) o;

            long qId = (Long) qJson.get("id");
            if (qId == id) {
                String text = (String) qJson.get("text");

                EndingType ending = null;
                if (qJson.containsKey("endingType"))
                    ending = EndingType.valueOf(((String) qJson.get("endingType")).toUpperCase());

                final JSONArray answersJson = (JSONArray) qJson.get("answers");

                Answer[] answers = new Answer[answersJson.size()];
                for (int i = 0; i < answersJson.size(); i++) {
                    final JSONObject aJson = (JSONObject) answersJson.get(i);
                    long aId = (Long) aJson.get("id");
                    String aText = (String) aJson.get("text");
                    long next = (Long) aJson.get("next");

                    answers[i] = new Answer(aId, aText, next);
                }
                return new Question(qId, text, answers, ending);
            }
        }
        throw new JsonReadingException("Question not found with id: " + id);
    }
}
