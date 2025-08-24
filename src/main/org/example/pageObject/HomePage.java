package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span.text-sm.text-gray-700")
    private WebElement clientName;

    @FindBy(xpath = "//a[contains(@href,'/cart')]//span")
    private List<WebElement> cartCountElements;

    @FindBy(xpath = "//a[contains(@href,'/cart')]")
    private WebElement cartLink;


    public boolean isClientNameDisplayed() {
        return clientName.isDisplayed();
    }

    public String getCartCount() {
        return cartCountElements.isEmpty() ? "0" : cartCountElements.get(0).getText();
    }

    public boolean isCartCountEqualTo(String expectedCount) {
        return getCartCount().equals(expectedCount);
    }

    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }
}
