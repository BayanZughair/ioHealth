package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.OrdersPage;
import common.driver.DriverManager;

public class OrdersSteps {
    private WebDriver driver = DriverManager.getDriver();
    private OrdersPage ordersPage = new OrdersPage(driver);

    @Then("I navigate to the orders page {string}")
    public void iNavigateToOrdersPage(String path) {
        driver.get(driver.getCurrentUrl().split("/")[0] + path);
        Assert.assertTrue("Orders page should be loaded", ordersPage.isOrdersPageLoaded());
    }

    @Then("I verify that the latest order contains product {string}")
    public void iVerifyLatestOrderContainsProduct(String productName) {
        Assert.assertTrue("Latest order should contain the product",
                ordersPage.verifyProductInOrder(productName));
    }

    @Then("I verify that the latest order total is {string}")
    public void iVerifyLatestOrderTotal(String total) {
        Assert.assertEquals("Latest order total should match",
                total, ordersPage.getLatestOrderTotal().replaceAll("[^\\d.]", ""));
    }

    @And("I verify that there are orders displayed")
    public void iVerifyOrdersDisplayed() {
        Assert.assertTrue("There should be at least one order", ordersPage.hasOrders());
    }

    @And("I verify that no orders message is displayed")
    public void iVerifyNoOrdersMessageDisplayed() {
        Assert.assertTrue("No orders message should be visible", ordersPage.isNoOrdersMessageDisplayed());
    }
}
