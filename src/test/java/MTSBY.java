import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MTSBY {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL = "https://www.mts.by/";
    private static final String PHONE = "297777777";
    private static final String SUM = "10";

    private static final By PAY_SECTION = By.xpath("//div[@id='pay-section']");
    private static final By BLOCK_TITLE = By.xpath("//div[@id='pay-section']//*[contains(text(),'ОНЛАЙН ПОПОЛНЕНИЕ')]");
    private static final By MORE_ABOUT_SERVICE_LINK = By.xpath("//a[contains(text(),'Подробнее о сервисе')]");
    private static final By PHONE_INPUT = By.xpath("//input[@id='connection-phone']");
    private static final By SUM_INPUT = By.xpath("//input[@id='connection-sum']");
    private static final By CONTINUE_BUTTON = By.xpath("//div[@id='pay-section']//button[contains(.,'ПРОДОЛЖИТЬ')]");
    private static final By SERVICE_TYPE = By.xpath("//div[@id='pay-section']//span[contains(@class,'select__now')]");
    private static final By COOKIE_ACCEPT = By.xpath("//button[contains(text(),'ПРИНЯТЬ') or contains(text(),'Принять')]");
    private static final By EMAIL_INPUT = By.xpath("//input[@id='connection-email']");
    private static final By VISA_LOGO = By.xpath("//div[@id='pay-section']//img[contains(@alt,'Visa') or contains(@alt,'visa') or contains(@src,'visa')]");
    private static final By MASTERCARD_LOGO = By.xpath("//div[@id='pay-section']//img[contains(@alt,'MasterCard') or contains(@alt,'Mastercard') or contains(@src,'mastercard')]");
    private static final By BELKART_LOGO = By.xpath("//div[@id='pay-section']//img[contains(@alt,'Белкарт') or contains(@alt,'belkart') or contains(@src,'belkart')]");
    private static final By ALL_PAYMENT_LOGOS = By.xpath(
            "//div[@id='pay-section']//img[" +
                    "contains(@alt,'Visa') or contains(@alt,'visa') or contains(@src,'visa') or " +
                    "contains(@alt,'MasterCard') or contains(@alt,'Mastercard') or contains(@src,'mastercard') or " +
                    "contains(@alt,'Белкарт') or contains(@alt,'belkart') or contains(@src,'belkart')" +
                    "]"
    );

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danik\\Downloads\\yandexdriver-26.8.0.1788-win64\\yandexdriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-popup-blocking");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        closeExtraTabs();
        driver.get(BASE_URL);

        try {
            WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(COOKIE_ACCEPT));
            accept.click();
            Thread.sleep(600);
        } catch (Exception ignored) {
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(PAY_SECTION));
        WebElement section = driver.findElement(PAY_SECTION);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", section);

        try {
            Thread.sleep(800);
        } catch (InterruptedException ignored) {
        }
    }

    private void closeExtraTabs() {
        try {
            var handles = driver.getWindowHandles();
            if (handles.size() <= 1) {
                return;
            }
            String main = driver.getWindowHandle();
            for (String h : handles) {
                driver.switchTo().window(h);
                String url = driver.getCurrentUrl();
                if (url == null || url.isEmpty() || url.startsWith("data:")) {
                    driver.close();
                } else {
                    main = h;
                }
            }
            driver.switchTo().window(main);
        } catch (Exception ignored) {
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка названия блока «Онлайн пополнение без комиссии»")
    public void testBlockTitle() {
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_SECTION));
        String text = section.getText().toUpperCase().replace("\n", " ");

        assertTrue(text.contains("ПОПОЛНЕНИЕ"),
                "В блоке нет слова «ПОПОЛНЕНИЕ». Текст блока: " + text);
        assertTrue(text.contains("КОМИССИИ"),
                "В блоке нет слова «КОМИССИИ». Текст блока: " + text);
    }

    @Test
    @DisplayName("Проверка наличия логотипов платёжных систем")
    public void testPaymentLogos() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_SECTION));

        List<WebElement> logos = driver.findElements(ALL_PAYMENT_LOGOS);
        assertTrue(logos.size() >= 3,
                "Ожидалось 3 логотипа, найдено: " + logos.size());

        assertFalse(driver.findElements(VISA_LOGO).isEmpty(), "Логотип Visa не найден");
        assertFalse(driver.findElements(MASTERCARD_LOGO).isEmpty(), "Логотип Mastercard не найден");
        assertFalse(driver.findElements(BELKART_LOGO).isEmpty(), "Логотип Белкарт не найден");
    }

    @Test
    @DisplayName("Проверка работы ссылки «Подробнее о сервисе»")
    public void testMoreAboutServiceLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(MORE_ABOUT_SERVICE_LINK));

        String href = link.getAttribute("href");
        assertTrue(href != null && (href.contains("poryadok-oplaty") || href.contains("bezopasnost")),
                "Ссылка ведёт не туда: " + href);

        link.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("poryadok-oplaty"),
                ExpectedConditions.urlContains("bezopasnost")
        ));

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("poryadok-oplaty") || currentUrl.contains("bezopasnost"),
                "URL не изменился. Текущий URL: " + currentUrl);
    }

    @Test
    @DisplayName("Заполнение полей «Услуги связи» и проверка кнопки «Продолжить»")
    public void testFillFormAndContinue() {

        WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT));
        phone.clear();
        phone.sendKeys(PHONE);

        WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(SUM_INPUT));
        sum.clear();
        sum.sendKeys(SUM);

        try {
            WebElement email = driver.findElement(By.xpath("//input[@id='connection-email']"));
            email.clear();
            email.sendKeys("test@mts.by");
        } catch (Exception ignored) {
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }

        WebElement continueBtn = null;
        for (WebElement b : driver.findElements(By.xpath("//button[contains(@class,'button__default')]"))) {
            if (b.isDisplayed() && b.getText().toUpperCase().contains("ПРОДОЛЖИТЬ")) {
                continueBtn = b;
                break;
            }
        }
        assertNotNull(continueBtn, "Кнопка «ПРОДОЛЖИТЬ» не найдена");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", continueBtn);
        try {
            continueBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
        }
        boolean overlayAppeared = false;

        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src,'bepaid') or contains(@src,'pay') or contains(@src,'payment')]")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'modal') and (contains(@class,'show') or contains(@class,'open') or contains(@class,'active'))]")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'popup') and (contains(@class,'show') or contains(@class,'open') or contains(@class,'active'))]")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'overlay') and (contains(@class,'show') or contains(@class,'active'))]")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@role='dialog']")),
                    ExpectedConditions.numberOfWindowsToBe(2),
                    ExpectedConditions.urlContains("bepaid"),
                    ExpectedConditions.urlContains("payment")
            ));
            overlayAppeared = true;
        } catch (TimeoutException e) {
            overlayAppeared = false;
        }

        if (driver.getWindowHandles().size() > 1) {
            overlayAppeared = true;
        }

        assertTrue(overlayAppeared,
                "После нажатия «Продолжить» не появилось окно оплаты URL: "
                        + driver.getCurrentUrl());
    }
}