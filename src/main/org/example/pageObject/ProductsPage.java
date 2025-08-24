package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.product-card")
    private List<WebElement> productCards;

    @FindBy(css = "h1.text-3xl.font-bold")
    private WebElement productNameDetails;

    @FindBy(css = "p.text-4xl.font-bold")
    private WebElement productPriceDetails;

    @FindBy(css = "div.error")
    private WebElement errorMessage;

    // --- Private Helper ---

    private WebElement getFirstProductCard() {
        if (productCards.isEmpty()) {
            throw new IllegalStateException("No products available on page");
        }
        return productCards.get(0);
    }

    // --- Methods ---

    public boolean isProductsPageLoaded() {
        return !productCards.isEmpty();
    }

    public int getProductCount() {
        return productCards.size();
    }

    public String getFirstProductName() {
        return getFirstProductCard().findElement(By.cssSelector("h3")).getText();
    }

    public String getFirstProductPrice() {
        return getFirstProductCard().findElement(By.cssSelector("p.price")).getText();
    }

    public void clickAddToCartFromList() {
        getFirstProductCard().findElement(By.xpath(".//button[text()='Add to Cart']")).click();
    }

    public String getProductNameFromDetails() {
        return productNameDetails.getText();
    }

    public String getProductPriceFromDetails() {
        return productPriceDetails.getText();
    }

    public void clickAddToCartFromDetails() {
        driver.findElement(By.xpath("//button[contains(text(),'Add to Cart')]")).click();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }
}
