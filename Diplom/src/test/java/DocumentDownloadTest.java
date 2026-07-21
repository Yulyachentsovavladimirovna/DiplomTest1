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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentDownloadTest {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final String ABOUT_SCHOOL_URL = "https://shkola59saratov-r64.gosweb.gosuslugi.ru/nasha-shkola/o-shkole/";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(ABOUT_SCHOOL_URL);
    }

    @Test
    void testDocumentLinkLeadsToPdf() throws IOException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tpl-block-129"))
        );
        WebElement docLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '.pdf') and (contains(., 'Программа развития') or contains(., 'программа развития'))]"))
        );
        String href = docLink.getAttribute("href");

        assertNotNull(href, "Ссылка на документ отсутствует");
        assertTrue(href.endsWith(".pdf"), "Документ не является PDF: " + href);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(href).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            assertEquals(200, responseCode, "Документ недоступен, код ответа: " + responseCode);

            String contentType = connection.getContentType();
            assertNotNull(contentType, "Content-Type не определён");
            assertTrue(contentType.toLowerCase().contains("pdf"), "Неверный MIME-тип: " + contentType);
        } finally {
            if (connection != null) connection.disconnect();
        }

        System.out.println("Документ найден и доступен: " + href);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}