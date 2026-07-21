import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class DiplomTest2 {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final String TEST_URL = "https://shkola59saratov-r64.gosweb.gosuslugi.ru/";
    private static final List<String> EXPECTED_MENU_ITEMS = List.of(
            "Главная",
            "Сведения об образовательной организации",
            "О школе",
            "Расписание",
            "Услуги и сервисы",
            "Полезная информация",
            "Новости",
            "Мероприятия",
            "Контакты"
    );
        private static final By MENU_LOCATOR = By.cssSelector(".gw-top-nav .gw-top-nav__item-link");

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(TEST_URL);
    }

    @Test
    void testPresenceOfMenuLinks() {
        wait.until(ExpectedConditions.presenceOfElementLocated(MENU_LOCATOR));
        List<WebElement> menuItems = driver.findElements(MENU_LOCATOR);

        assertFalse(menuItems.isEmpty(), "Меню не найдено. Локатор: " + MENU_LOCATOR);

        List<String> actualTexts = menuItems.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());

        System.out.println("Найденные пункты меню: " + actualTexts);

        for (String expected : EXPECTED_MENU_ITEMS) {
            assertTrue(actualTexts.contains(expected),
                    "Ожидаемый пункт '" + expected + "' отсутствует. Актуальное меню: " + actualTexts);
        }

        System.out.println("Тест пройден: все ожидаемые пункты меню присутствуют.");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}