# Описание установки Selenium
Шаг 1. Подготовка окружения

JDK (Java Development Kit) версии 11 или 17.

Проверка в терминале: java -version и javac -version.
IDE: IntelliJ IDEA (Community Edition подойдёт) или Eclipse.
Система сборки: Maven или Gradle.

Шаг 2. Подключение Selenium в проекте
Если используешь Maven
Открой файл pom.xml и добавь зависимость:
```java
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.25.0</version> <!-- версия может быть новее -->
</dependency>
```
После этого Maven сам скачает библиотеку.

Если используешь Gradle
В build.gradle:
```implementation 'org.seleniumhq.selenium:selenium-java:4.25.0'```

Шаг 3. Драйвер браузера

Начиная с Selenium 4.6+, драйвер подтягивает Selenium Manager автоматически. 
```import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
```
Шаг 4. Минимальный рабочий тест на Java

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearchTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver(); // Selenium Manager сам найдёт драйвер
        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        // Явное ожидание (Explicit Wait) 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        searchBox.sendKeys("Selenium Java exam");
        searchBox.submit(); // или найти кнопку и кликнуть

        System.out.println("Current URL: " + driver.getCurrentUrl());

        driver.quit();
    }
}
```
# Описание установки IntelliJ IDEA
Шаг 1. Какую версию качать

Нужна IntelliJ IDEA Community Edition — она бесплатная и полностью покрывает учебные задачи.

Официальный сайт JetBrains — jetbrains.com/idea/download.

Шаг 2. Установка на Windows

Скачал установщик (ideaIC-*.exe).

Запустил, нажал «Next».

Выбрал папку установки (можно оставить по умолчанию).

На этапе выбора ярлыков поставь галочки, чтобы был ярлык на рабочем столе и в меню «Пуск».

На шаге «Additional Tasks» можно поставить галочку «Add “Open Folder as Project”». Это удобно: потом в проводнике правой кнопкой по папке проекта → «Open in IntelliJ IDEA».
Нажимаешь «Install», ждёшь, в конце — «Finish».

Шаг 3. Первый запуск и базовая настройка

При первом запуске предложат импортировать настройки — выбери «Do not import settings».

Выберете тему оформления: Light или Dark.

Примите лицензионное соглашение.

(Опционально) Можно пропустить экраны с плагинами и туториалами. 

Шаг 4. Проверка, что Java «видит»

IntelliJ IDEA не содержит в себе Java. Ей нужен JDK (Java Development Kit).

Заходишь в настройки: File → Project Structure (или Ctrl+Alt+Shift+S).

Слева выбираешь SDKs.
Если список пустой — нажимаешь «+», выбираешь JDK, указываешь путь к папке, где установлен JDK (например, C:\Program Files\Java\jdk-17).

Потом в том же окне Project Structure переходишь на вкладку Project и проверяешь, что в поле SDK выбран этот JDK.

Шаг 5. Создание первого проекта

На экране приветствия: New Project.

Тип проекта: Java.

Build system: Maven или Gradle.

JDK: выбираешь тот, который добавил в шаге 4.

GroupId: например, com.example.

ArtifactId: имя проекта, например my-first-project.

Нажимаешь Create.

После этого у тебя появится папка src, внутри неё main/java, и можно создавать классы.

Шаг 6. Как запустить код

Создаёшь класс с методом public static void main(String[] args).

Справа от строки с main появляется зелёная стрелочка → нажимаешь её → Run.

Внизу открывается панель Run, где виден результат работы программы.

Можно создать одиночный Java-файл, но тогда нужно убедиться, что у файла есть main, иначе запускаться не будет.

# Описание установки Maven

Maven это часть IntelliJ IDEA 

В IntelliJ IDEA при создании проекта выбираешь тип 
```New Project → Java.```
В поле Build system выбираешь Maven.
IDEA сама создаст правильную структуру папок ```(src/main/java, src/test/java, pom.xml)``` и подключит Maven.

Файл pom.xml — это «сердце» проекта. В нём ты описываешь зависимости (те самые библиотеки).

В Maven всё крутится вокруг файла pom.xml (Project Object Model). Это XML-файл, который лежит в корне проекта.

Что там обязательно есть:

```<groupId>``` — уникальный идентификатор группы (часто домен компании наоборот, например com.example).

```<artifactId>``` — имя самого проекта (например selenium-tests).

```<version>``` — версия проекта (например 1.0-SNAPSHOT).

```<dependencies>``` — список библиотек, которые нужны проекту.

# Описание установки Jmeter

JMeter написан на ``Java```, поэтому первым делом должен стоять JDK (версии 11 или 17).
Проверка в терминале: ```java -version.```

Шаг 1. Скачивание

Заходишь на официальный сайт: ```jmeter.apache.org.```

Переходишь в раздел ```Download.```

Скачиваешь архив ```Binary Downloads (не Source).``` Выбираешь версию под свою ОС (Windows, macOS, Linux).

Для Windows:``` .zip архив.```

Для macOS/Linux: тоже ```.tar.gz или .zip.```

Шаг 2. Распаковка

Распаковываешь архив в удобную папку.
Примеры путей:

```Windows: C:\apache-jmeter-5.x```

```macOS/Linux: /opt/apache-jmeter или в домашнюю папку ~/apache-jmeter.```

Важно: В папке после распаковки ты увидишь:

```bin/``` — здесь лежат файлы для запуска.

```lib/``` — библиотеки.

```extras/``` — примеры и утилиты.

Шаг 3. Запуск

Для Windows
В папке bin запускаешь файл ```jmeter.bat.```

Не путай с ```jmeter.cmd``` 

Для ```macOS / Linux```

```В папке ```bin``` в терминале запускаешь:
./jmeter```

Не забудь дать права на выполнение, если нужно: ```chmod +x jmeter).```

JMeter запускается как отдельное GUI-приложение (графический интерфейс). Это не плагин к IDEA, это самостоятельная программа.


