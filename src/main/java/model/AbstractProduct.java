package model;

public abstract class AbstractProduct {

    private long id;
    private String name;
    private double price;
    private int quantity;

    public AbstractProduct(long id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    public double getTotalValue() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity;
    }
}
