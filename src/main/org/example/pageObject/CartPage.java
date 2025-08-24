package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[text()='Shopping Cart']")
    private WebElement cartHeader;

    @FindBy(css = "div.rounded-lg.border.bg-card")
    private List<WebElement> cartItems;

    @FindBy(css = "div.rounded-lg.border.bg-card h3")
    private List<WebElement> productNames;

    @FindBy(css = "div.rounded-lg.border.bg-card p.text-2xl")
    private List<WebElement> productPrices;

    @FindBy(css = "div.rounded-lg.border.bg-card span.w-12.text-center.font-medium")
    private List<WebElement> productQuantities;

    @FindBy(css = "div.flex.justify-between.text-xl.font-bold span:nth-child(2)")
    private WebElement grandTotalElement;

    // --- Methods ---

    public boolean isCartPageLoaded() {
        return cartHeader.isDisplayed();
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public List<String> getAllProductNames() {
        return productNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getAllProductPrices() {
        return productPrices.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getAllQuantities() {
        return productQuantities.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean containsProduct(String productName) {
        return getAllProductNames().stream().anyMatch(name -> name.contains(productName));
    }

    public int getCartItemCount() {
        return getAllProductNames().size();
    }

    public boolean validateCartSubtotal() {
        double sum = productPrices.stream()
                .map(WebElement::getText)
                .mapToDouble(common.utils.Utils::parsePrice)
                .sum();

        double grandTotal = common.utils.Utils.parsePrice(grandTotalElement.getText());
        return Math.abs(sum - grandTotal) < 0.01;
    }
}
