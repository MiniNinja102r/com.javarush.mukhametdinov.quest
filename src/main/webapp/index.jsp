<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Квест: Побег от маньяка</title>
    <style>
        body {
            background-color: #111;
            color: #eee;
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background: #1e1e1e;
            padding: 30px;
            border-radius: 10px;
        }

        h1 { color: #ff4444; }

        input[type="text"] {
            padding: 10px;
            width: 80%;
            margin-top: 15px;
            border-radius: 5px;
            border: none;
        }

        button {
            margin-top: 15px;
            padding: 10px 20px;
            background-color: #ff4444;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover { background-color: #cc0000; }
    </style>
</head>

<body>
<div class="container">
    <h1>Квест: Побег от маньяка</h1>

    <p>
        Ты очнулся в тёмном доме. Голова раскалывается, вокруг - тишина... слишком подозрительная.
        Последнее, что ты помнишь - странный человек, наблюдающий за тобой в переулке.
    </p>
    <p>
        Где-то в доме скрипнула дверь. Он здесь.
        И у тебя есть только один шанс выбраться отсюда.
    </p>

    <p><b>Представься, чтобы начать игру:</b></p>

    <form action="${pageContext.request.contextPath}/quest-page" method="POST">
        <input type="text" name="username" placeholder="Введи свой ник..." required>
        <br/>
        <button type="submit">Представиться</button>
    </form>
</div>
</body>
</html>
