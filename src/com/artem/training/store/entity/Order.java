package com.artem.training.store.entity;

public class Order {

    private int id;
    private int buyerId;
    private int productId;
    private int quantity;
    private String status;

    public Order(int id, int buyerId, int productId, int quantity, String status) {
        this.id = id;
        this.buyerId = buyerId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", buyerId=" + buyerId +
               ", productId=" + productId +
               ", quantity=" + quantity +
                ", status='" + status +
               '}';
    }
}
