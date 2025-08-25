package ku.shop;

public class Product {
    private double price;
    private String name;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void cutStock(int quantity) {
        stock -= quantity;
    }

    public void stockCheck(int quantity) throws InsufficientStockException {
        if (quantity > stock)
            throw new InsufficientStockException("Insufficient stock. " + name + "is not available.");
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
