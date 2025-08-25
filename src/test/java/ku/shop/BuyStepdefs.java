package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private InsufficientStockException lastError;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
        lastError = null;
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) throws InsufficientStockException {
        Product prod = catalog.getProduct(name);
        try {
            order.addItem(prod, quantity);
            lastError = null;
        } catch (InsufficientStockException e) {
            lastError = e;
        }
    }

    @Then("you cannot buy {string}")
    public void a_product_is_not_available(String name) {
        if (lastError == null) {
            throw new AssertionError("Expected an InsufficientStockException, but no error occurred.");
        }
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }
}

