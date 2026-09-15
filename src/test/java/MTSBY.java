import pages.PaymentBlockPage;
import pages.PaymentWidgetPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@Epic("MTS.by")
@Feature("Онлайн пополнение без комиссии")
public class MTSBY {

    private WebDriver driver;
    private PaymentBlockPage paymentPage;
    private static final String PHONE = "297777777";
    private static final String SUM = "10";

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().browserVersion("150").setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");

        driver = new ChromeDriver(options);
        paymentPage = new PaymentBlockPage(driver).open();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Story("Заголовок блока")
    @DisplayName("Проверка названия блока «Онлайн пополнение без комиссии»")
    @Severity(SeverityLevel.NORMAL)
    public void testBlockTitle() {
        String text = paymentPage.getBlockText();
        assertTrue(text.contains("ПОПОЛНЕНИЕ"), "Нет «ПОПОЛНЕНИЕ»: " + text);
        assertTrue(text.contains("КОМИССИ"), "Нет «КОМИССИИ»: " + text);
    }

    @Test
    @Story("Логотипы")
    @DisplayName("Проверка наличия логотипов платёжных систем")
    @Severity(SeverityLevel.NORMAL)
    public void testPaymentLogos() {
        assertTrue(paymentPage.countPaymentLogos() >= 3, "Логотипов меньше 3");
    }

    @Test
    @Story("Ссылка «Подробнее о сервисе»")
    @DisplayName("Проверка работы ссылки «Подробнее о сервисе»")
    @Severity(SeverityLevel.NORMAL)
    public void testMoreAboutServiceLink() {
        paymentPage.clickMoreAboutService();
        String url = paymentPage.getCurrentUrl();
        assertTrue(url.contains("poryadok-oplaty") || url.contains("bezopasnost"),
                "Неверный URL: " + url);
    }

    @Test
    @Story("Placeholders форм")
    @DisplayName("Placeholders: Услуги связи")
    @Severity(SeverityLevel.NORMAL)
    public void testPlaceholdersConnection() {
        paymentPage.selectPaymentType("Услуги связи");
        Map<String, String> ph = paymentPage.getPlaceholdersForForm("pay-connection");

        assertEquals("Номер телефона", ph.get("connection-phone"));
        assertEquals("Сумма", ph.get("connection-sum"));
        assertEquals("E-mail для отправки чека", ph.get("connection-email"));
    }

    @Test
    @Story("Placeholders форм")
    @DisplayName("Placeholders: Домашний интернет")
    @Severity(SeverityLevel.NORMAL)
    public void testPlaceholdersInternet() {
        paymentPage.selectPaymentType("Домашний интернет");
        Map<String, String> ph = paymentPage.getPlaceholdersForForm("pay-internet");

        assertEquals("Номер абонента", ph.get("internet-phone"));
        assertEquals("Сумма", ph.get("internet-sum"));
        assertEquals("E-mail для отправки чека", ph.get("internet-email"));
    }

    @Test
    @Story("Placeholders форм")
    @DisplayName("Placeholders: Рассрочка")
    @Severity(SeverityLevel.NORMAL)
    public void testPlaceholdersInstalment() {
        paymentPage.selectPaymentType("Рассрочка");
        Map<String, String> ph = paymentPage.getPlaceholdersForForm("pay-instalment");

        assertEquals("Номер счета на 44", ph.get("score-instalment"));
        assertEquals("Сумма", ph.get("instalment-sum"));
        assertEquals("E-mail для отправки чека", ph.get("instalment-email"));
    }

    @Test
    @Story("Placeholders форм")
    @DisplayName("Placeholders: Задолженность")
    @Severity(SeverityLevel.NORMAL)
    public void testPlaceholdersArrears() {
        paymentPage.selectPaymentType("Задолженность");
        Map<String, String> ph = paymentPage.getPlaceholdersForForm("pay-arrears");

        assertEquals("Номер счета на 2073", ph.get("score-arrears"));
        assertEquals("Сумма", ph.get("arrears-sum"));
        assertEquals("E-mail для отправки чека", ph.get("arrears-email"));
    }

    @Test
    @Story("Окно оплаты bePaid")
    @DisplayName("Услуги связи: заполнение и проверка окна оплаты")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Номер 297777777, сумма 10 руб. → в виджете 10.00 BYN, номер, поля карты, иконки")
    public void testConnectionPaymentWidget() {
        paymentPage.selectPaymentType("Услуги связи");
        paymentPage.fillConnectionPhone(PHONE);
        paymentPage.fillConnectionSum(SUM);
        paymentPage.fillConnectionEmail("test@mts.by");
        paymentPage.clickContinue();

        PaymentWidgetPage widget = paymentPage.waitForPaymentWidget();
        String pageText = widget.getPageText();

        assertTrue(pageText.contains("10") && pageText.toUpperCase().contains("BYN"),
                "Сумма 10 BYN не найдена. Текст: "
                        + pageText.substring(0, Math.min(400, pageText.length())));

        String payBtn = widget.getPayButtonText();
        assertTrue(payBtn.isEmpty()
                        || (payBtn.contains("10") && payBtn.toUpperCase().contains("ОПЛАТИТЬ")),
                "Кнопка оплаты некорректна: [" + payBtn + "]");

        assertTrue(pageText.contains("297777777") || pageText.contains("375297777777"),
                "Номер не найден. Текст: "
                        + pageText.substring(0, Math.min(400, pageText.length())));

        String lower = pageText.toLowerCase();
        assertTrue(lower.contains("номер карты") || lower.contains("карт"),
                "Нет поля «Номер карты»");
        assertTrue(lower.contains("срок") || lower.contains("действ"),
                "Нет поля «Срок действия»");
        assertTrue(lower.contains("cvc") || lower.contains("cvv"),
                "Нет поля CVC");
        assertTrue(lower.contains("имя") || lower.contains("фамилия"),
                "Нет поля имени на карте");

        assertTrue(widget.hasPaymentSystemIcons()
                        || pageText.contains("Pay")
                        || lower.contains("visa"),
                "Нет признаков платёжных систем");

        paymentPage.switchToDefaultContent();
    }
}