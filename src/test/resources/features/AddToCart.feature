Feature: TechMart Add to Cart & Orders Flow
  As a TechMart customer
  I want to add products to my shopping cart and verify orders
  So that I can review them before purchasing

  Background:
    Given I navigate to the login page "/login"
    And I login with credentials "client@example.com" and "!Aa123456"
    And I am redirected to the products page "/products"
    And I verify the client name is displayed in the top bar

  @CartPage @TM-TC-01
  Scenario: Add Single Product from TechMart
    Given I am on the products page with products fully loaded
    When I locate any product card on the products page
    And I click the "Add to Cart" button for that product
    Then I observe the cart icon in the top bar shows count "1" if cart was empty
    When I navigate to the cart page "/cart"
    Then I verify the newly added product appears at the top of the cart list
    And I check that product details "name, price, quantity" are correct
    And no UI or functional errors occur

  @CartPage @TM-TC-02
  Scenario: Add Multiple Different Products
    Given I am on the products page with at least two different products available
    When I locate the first product card and click "Add to Cart"
    Then I observe the cart icon count should increase by 1
    When I locate the second product card "different from the first" and click "Add to Cart"
    Then I observe the cart icon count should increase by 1 again
    When I navigate to the cart page "/cart"
    Then I verify both products appear in the cart with correct "names, prices, quantities"
    And I verify the most recently added product appears at the top
    And I verify the Grand Total reflects the sum of all added products

  @CartPage @TM-TC-03
  Scenario: Add Multiple Quantities of the Same Product
    Given I am on the products page with at least one product that allows multiple quantities
    When I locate a product card on the products page
    And I click "Add to Cart" on the product once
    Then I observe the cart icon count should increase by 1
    When I click "Add to Cart" again on the same product "2-3 more times"
    And I navigate to the cart page "/cart"
    Then I verify each addition appears as a separate entry in the cart
    And I verify the cart icon count reflects the total quantity of this product added
    And I verify the grand total equals "product price × total quantity"
    And I verify the most recently added product appears at the top of the cart

  @OrdersPage @TM-TC-04
  Scenario: Verify Latest Order
    When I navigate to the orders page "/orders"
    Then I verify that the latest order contains product "iPhone 14 Pro"
    And I verify that the latest order total is "999.99"
    And I verify that there are orders displayed
