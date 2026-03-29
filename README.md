## Web Quest: Escape from Maniac

Небольшая текстовая квест-игра.
Реализована на Java с использованием Servlet + JSP/JSTL

---

## Запуск проекта

### 1. Требования
* Установлен **Apache Tomcat 9**
* Java 21
* Maven

---

### 2. Сборка проекта
В терминале вводим: `mvn clean package`

После сборки появится файл: `target/Quest-1.0.war`

---

### 3. Деплой в Tomcat 9
Скопируем `.war`-файл в папку: `tomcat/webapps/`

Далее запустим наш Tomcat:
```yaml
bin/startup.sh   # Linux / MacOS
bin/startup.bat  # Windows
```

После осталось лишь открыть в браузере `http://localhost:8080/Quest-1.0`
