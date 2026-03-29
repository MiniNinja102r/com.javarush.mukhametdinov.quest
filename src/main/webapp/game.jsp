<%@ page import="com.javarush.quest.entity.Question" %>
<%@ page import="com.javarush.quest.entity.Answer" %>
<%@ page import="com.javarush.quest.util.Constants" %>
<%@ page import="com.javarush.quest.entity.EndingType" %>

<%
    final Question question = (Question) request.getAttribute(Constants.QUESTION);
    final String username = (String) session.getAttribute(Constants.USERNAME);
    final Integer gamesCount = (Integer) session.getAttribute(Constants.GAMES_COUNT);
    final String userIp = request.getRemoteAddr();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Квест</title>
</head>
<body>

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
<h3>
    <%= question.getEndingType() == EndingType.GOOD ? "Ты выжил" : "Тебя нашли" %>
</h3>
<form action="index.jsp">
    <button type="submit">Начать заново</button>
</form>
<% } %>

<hr>

<p>Игрок: <%= username %></p>
<p>Игр сыграно: <%= gamesCount %></p>
<p>IP: <%= userIp %></p>

</body>
</html>