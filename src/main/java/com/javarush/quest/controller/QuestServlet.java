package com.javarush.quest.controller;

import java.io.*;

import com.javarush.quest.entity.GameType;
import com.javarush.quest.util.Constants;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE)
@WebServlet(name = "questPage", value = "/quest-page")
public final class QuestServlet extends HttpServlet {
    String username;
    GameType game;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        username = request.getParameter("username");
        try {
            String raw = request.getParameter("game");
            if (raw != null) game = GameType.valueOf(raw.toUpperCase());
        } catch (Exception e) {
            log.severe("Не удалось прочитать тип игры из запроса.");
            game = Constants.DEFAULT_GAME_TYPE;
        }


        response.setContentType("text/html");
        response.setContentType("text/html;charset=UTF-8");

        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");

    }
}