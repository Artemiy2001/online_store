package com.artem.training.store.entity;

public class Order {

    private int orderId;
    private int buyerId;
    private int productId;
    private int quantity;

    public Order(int orderId, int buyerId, int productId, int quantity) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
                "orderId=" + orderId +
                ", buyerId=" + buyerId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
