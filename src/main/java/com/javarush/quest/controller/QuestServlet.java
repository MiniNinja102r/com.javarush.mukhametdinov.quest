package com.javarush.quest.controller;

import java.io.*;

import com.javarush.quest.entity.GameType;
import com.javarush.quest.util.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "questPage", value = "/quest-page")
public final class QuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        final PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> BRRR </h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        GameType game;
        try {
            String raw = request.getParameter("game");
            game = raw != null ? GameType.valueOf(raw.toUpperCase()) : Constants.DEFAULT_GAME_TYPE;
        } catch (Exception e) {
            game = Constants.DEFAULT_GAME_TYPE;
        }

        final HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("game", game);

        response.setContentType("text/html");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("OK");
        response.getWriter().println(session.getAttribute("username"));
        response.getWriter().println(session.getAttribute("game"));
    }
}