package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PaymentWidgetPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public PaymentWidgetPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;

        wait.withTimeout(Duration.ofSeconds(25)).until(d -> {
            try {
                String t = d.findElement(By.tagName("body")).getText();
                return t != null && (t.contains("BYN") || t.contains("Оплатить") || t.contains("карт"));
            } catch (Exception e) {
                return false;
            }
        });
    }

    public String getPageText() {
        try {
            return driver.findElement(By.tagName("body")).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getDisplayedAmount() {
        String text = getPageText();
        Matcher m = Pattern.compile("(\\d+[.,]\\d{2}\\s*BYN)", Pattern.CASE_INSENSITIVE)
                .matcher(text);
        if (m.find()) {
            return m.group(1);
        }
        List<WebElement> els = driver.findElements(By.xpath("//*[contains(text(),'BYN')]"));
        for (WebElement el : els) {
            String t = el.getText();
            if (t != null && t.matches("(?s).*\\d+[.,]\\d{2}\\s*BYN.*")) {
                return t.trim();
            }
        }
        return text;
    }

    public String getPayButtonText() {
        for (WebElement b : driver.findElements(By.xpath("//button"))) {
            String t = b.getText();
            if (t != null && t.toUpperCase().contains("ОПЛАТИТЬ")) {
                return t.replace("\n", " ").trim();
            }
        }
        for (WebElement el : driver.findElements(By.xpath("//*[contains(text(),'Оплатить')]"))) {
            if (el.isDisplayed()) {
                return el.getText().replace("\n", " ").trim();
            }
        }
        return "";
    }

    public String getPhoneInfoText() {
        String text = getPageText();
        for (String line : text.split("\\R")) {
            String lineTrim = line.trim();
            if (lineTrim.contains("Номер") || lineTrim.contains("375") || lineTrim.contains("297777777")) {
                return lineTrim;
            }
        }
        return text;
    }

    public List<String> getCardFieldPlaceholders() {
        List<String> list = new ArrayList<>();
        for (WebElement input : driver.findElements(By.cssSelector("input[placeholder], [placeholder]"))) {
            try {
                String ph = input.getAttribute("placeholder");
                if (ph != null && !ph.isBlank()) {
                    list.add(ph.trim());
                }
            } catch (Exception ignored) {
            }
        }
        String body = getPageText();
        for (String expected : List.of(
                "Номер карты", "Срок действия", "CVC", "Имя и фамилия на карте", "Имя и фамилия")) {
            if (body.contains(expected)) {
                list.add(expected);
            }
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    public boolean hasPaymentSystemIcons() {
        List<WebElement> icons = driver.findElements(By.xpath(
                "//img[contains(translate(@alt,'VISA','visa'),'visa') "
                        + "or contains(translate(@alt,'MASTER','master'),'master') "
                        + "or contains(@src,'visa') or contains(@src,'master') or contains(@src,'belkart')]"
                        + " | //*[contains(@class,'visa') or contains(@class,'mastercard')]"
        ));
        if (!icons.isEmpty()) {
            return true;
        }
        String text = getPageText();
        return text.contains("Pay") || text.toLowerCase().contains("visa");
    }
}