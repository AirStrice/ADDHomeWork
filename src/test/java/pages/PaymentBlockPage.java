package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentBlockPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String BASE_URL = "https://www.mts.by/";

    private final By paySection = By.id("pay-section");
    private final By moreAboutLink = By.xpath("//a[contains(.,'Подробнее о сервисе')]");
    private final By cookieAccept = By.xpath(
            "//button[contains(.,'ПРИНЯТЬ') or contains(.,'Принять') or contains(.,'Согласен')]");
    private final By paymentLogos = By.xpath(
            "//div[@id='pay-section']//img[contains(@src,'visa') or contains(@src,'mastercard') "
                    + "or contains(@src,'belkart') or contains(@alt,'Visa') or contains(@alt,'Master') "
                    + "or contains(@alt,'Белкарт') or contains(@alt,'belkart')]");
    private final By paymentIframe = By.cssSelector("iframe.payment-widget-iframe");

    public PaymentBlockPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public PaymentBlockPage open() {
        driver.get(BASE_URL);
        acceptCookiesIfPresent();
        scrollToPaySection();
        return this;
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cookieAccept));
            btn.click();
            Thread.sleep(500);
        } catch (Exception ignored) {
        }
    }

    public void scrollToPaySection() {
        WebElement section = wait.until(ExpectedConditions.presenceOfElementLocated(paySection));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', behavior:'instant'});", section);
        try {
            Thread.sleep(600);
        } catch (InterruptedException ignored) {
        }
    }

    public String getBlockText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paySection))
                .getText().toUpperCase().replace("\n", " ");
    }

    public int countPaymentLogos() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(paySection));
        return driver.findElements(paymentLogos).size();
    }

    public void clickMoreAboutService() {
        wait.until(ExpectedConditions.elementToBeClickable(moreAboutLink)).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void selectPaymentType(String optionText) {
        WebElement header = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='pay-section']//*[contains(@class,'select__header') "
                        + "or contains(@class,'select__now')]")));
        header.click();
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {
        }

        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='pay-section']//li[contains(@class,'select__item')]"
                        + "//*[contains(text(),'" + optionText + "')] "
                        + "| //div[@id='pay-section']//li[contains(@class,'select__item') "
                        + "and contains(.,'" + optionText + "')]")));
        item.click();
        try {
            Thread.sleep(400);
        } catch (InterruptedException ignored) {
        }
    }

    public Map<String, String> getPlaceholdersForForm(String formId) {
        Map<String, String> result = new HashMap<>();
        List<WebElement> inputs = driver.findElements(
                By.xpath("//form[@id='" + formId + "']//input[@placeholder]"));
        for (WebElement input : inputs) {
            String ph = input.getAttribute("placeholder");
            String id = input.getAttribute("id");
            if (ph != null && !ph.isEmpty()) {
                result.put(id, ph);
            }
        }
        return result;
    }

    public void fillConnectionPhone(String phone) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
        field.clear();
        field.sendKeys(phone);
    }

    public void fillConnectionSum(String sum) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
        field.clear();
        field.sendKeys(sum);
    }

    public void fillConnectionEmail(String email) {
        try {
            WebElement field = driver.findElement(By.id("connection-email"));
            field.clear();
            field.sendKeys(email);
        } catch (Exception ignored) {
        }
    }

    public void clickContinue() {
        WebElement continueBtn = null;
        for (WebElement b : driver.findElements(By.xpath("//button[contains(@class,'button__default')]"))) {
            if (b.isDisplayed() && b.getText().toUpperCase().contains("ПРОДОЛЖ")) {
                continueBtn = b;
                break;
            }
        }
        if (continueBtn == null) {
            throw new NoSuchElementException("Кнопка «ПРОДОЛЖИТЬ» не найдена");
        }
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", continueBtn);
        try {
            continueBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
        }
    }

    public PaymentWidgetPage waitForPaymentWidget() {
        wait.until(ExpectedConditions.presenceOfElementLocated(paymentIframe));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentIframe));

        wait.until(d -> {
            WebElement f = d.findElement(paymentIframe);
            return f.getSize().getHeight() > 100 && f.getSize().getWidth() > 100;
        });

        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {
        }

        driver.switchTo().frame(iframe);
        return new PaymentWidgetPage(driver, wait);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}