import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Shkola59Test1 {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final String TEST_URL = "https://shkola59saratov-r64.gosweb.gosuslugi.ru/";
    private static final String EXPECTED_TEXT = "МОУ СОШ № 59 С УГЛУБЛЕННЫМ ИЗУЧЕНИЕМ ПРЕДМЕТОВ, г. САРАТОВ";
    private static final String LINK_TEXT = "Контакты";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(TEST_URL);
    }

    @Test
    void clickShkola59Test1() {
        WebElement contactsLink = wait.until(ExpectedConditions
                .elementToBeClickable(By.linkText(LINK_TEXT)));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                contactsLink
        );

        contactsLink.click();
        wait.until(ExpectedConditions.titleContains("МОУ СОШ № 59"));
        String actualTitle = driver.getTitle();
        System.out.println("Actual Title: " + actualTitle);
        assertEquals(EXPECTED_TEXT, actualTitle, "Заголовок не совпал");
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://shkola59saratov-r64.gosweb.gosuslugi.ru/контакты", currentUrl, "URL не соответствует ожидаемому");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
