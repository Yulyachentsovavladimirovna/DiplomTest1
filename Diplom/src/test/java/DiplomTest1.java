import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class DiplomTest1 {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final String TEST_URL = "https://shkola59saratov-r64.gosweb.gosuslugi.ru/";
    private static final String EXPECTED_TITLE = "Школа - Главная страница";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(TEST_URL);
    }

    @Test
    public void testMainPageTitle() {
        String actualTitle = driver.getTitle();
        assertEquals(EXPECTED_TITLE, actualTitle, "Заголовок главной страницы не соответствует ожидаемому.");
        System.out.println("Тест заголовка страницы пройден успешно. Фактический заголовок: \"" + actualTitle + "\"");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
