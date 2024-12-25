package com.artem.training.store.dao;

public class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();

    private OrderDao() {

    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
