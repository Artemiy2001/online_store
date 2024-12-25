package com.artem.training.store.dao;

public class ProductDao {

    private static final ProductDao INSTANCE = new ProductDao();

    private ProductDao() {

    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
