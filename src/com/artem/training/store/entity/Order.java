package com.artem.training.store.entity;

public class Order {

    private int id;
    private int buyerId;
    private int productId;
    private int quantity;

    public Order(int id, int buyerId, int productId, int quantity) {
        this.id = id;
        this.buyerId = buyerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", buyerId=" + buyerId +
               ", productId=" + productId +
               ", quantity=" + quantity +
               '}';
    }
}
