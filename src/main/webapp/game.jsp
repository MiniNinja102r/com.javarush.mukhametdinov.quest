<%@ page import="com.javarush.quest.entity.Question" %>
<%@ page import="com.javarush.quest.entity.Answer" %>
<%@ page import="com.javarush.quest.entity.EndingType" %>
<%@ page import="com.javarush.quest.util.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    final Question question = (Question) request.getAttribute(Constants.QUESTION);
    final String username = (String) session.getAttribute(Constants.USERNAME);
    final Integer gamesCount = (Integer) session.getAttribute(Constants.GAMES_COUNT);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Квест</title>
    <style>
        body {
            background-color: #111;
            color: #eee;
            font-family: Arial;
            text-align: center;
            padding: 40px;
        }

        .box {
            max-width: 600px;
            margin: auto;
            background: #1e1e1e;
            padding: 25px;
            border-radius: 10px;
        }

        button {
            margin-top: 10px;
            padding: 10px 20px;
            background: #ff4444;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background: #cc0000;
        }
        .good { color: #00ff88; }
        .bad { color: #ff4444; }
    </style>
</head>

<body>
<div class="box">
    <h2><%= question.getText() %></h2>
    <% if (question.getEndingType() == null) { %>
    <form method="POST" action="quest-page">
        <% for (Answer a : question.getAnswers()) { %>
        <button type="submit" name="answer" value="<%= a.getId() %>">
            <%= a.getText() %>
        </button>
        <br/>
        <% } %>
    </form>

    <% } else { %>
    <h3 class="<%= question.getEndingType() == EndingType.GOOD ? "good" : "bad" %>">
        <%= question.getEndingType() == EndingType.GOOD ? "Победа! Ты выбрался!" : "Поражение. Маньяк тебя нашёл." %>
    </h3>
    <form method="POST" action="quest-page">
        <button>Начать заново</button>
    </form>
    <% } %>

    <hr>
    <p>Пользователь: <%= username %></p>
    <p>Игр сыграно: <%= gamesCount %></p>
    <p>IP: <%= request.getRemoteAddr() %></p>
</div>
</body>
</html>
