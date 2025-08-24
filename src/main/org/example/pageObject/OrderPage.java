package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersPage {
    private WebDriver driver;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[text()='My Orders']")
    private WebElement ordersHeader;

    @FindBy(css = "div.rounded-lg.border.bg-card")
    private List<WebElement> orderCards;

    @FindBy(css = "div.no-orders")
    private WebElement noOrdersMessage;

  

    public boolean isOrdersPageLoaded() {
        return ordersHeader.isDisplayed();
    }

    public boolean hasOrders() {
        return !orderCards.isEmpty();
    }

    public boolean isNoOrdersMessageDisplayed() {
        return noOrdersMessage.isDisplayed();
    }

    public int getOrderCount() {
        return orderCards.size();
    }

    public List<String> getAllOrderNumbers() {
        return orderCards.stream()
                .map(card -> card.findElement(By.cssSelector("h3")).getText().replace("Order #", "").trim())
                .collect(Collectors.toList());
    }

    public List<String> getAllOrderTotals() {
        return orderCards.stream()
                .map(card -> card.findElement(By.cssSelector("div.flex.justify-between span.font-bold")).getText())
                .map(text -> text.replaceAll("[^\\d.]", ""))
                .collect(Collectors.toList());
    }

    public boolean verifyOrderExists(String expectedTotal) {
        return getAllOrderTotals().stream().anyMatch(total -> total.equals(expectedTotal));
    }

    public boolean verifyProductInOrder(String productName) {
        throw new UnsupportedOperationException("Product details not available in current page HTML.");
    }

    public String getLatestOrderTotal() {
        return orderCards.isEmpty() ? "" :
               orderCards.get(0).findElement(By.cssSelector("div.flex.justify-between span.font-bold")).getText().replaceAll("[^\\d.]", "");
    }

    public String getLatestOrderNumber() {
        return orderCards.isEmpty() ? "" :
               orderCards.get(0).findElement(By.cssSelector("h3")).getText().replace("Order #", "").trim();
    }
}
