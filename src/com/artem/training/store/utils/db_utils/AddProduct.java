package com.artem.training.store.utils.db_utils;

import com.artem.training.store.dao.ProductDao;
import com.artem.training.store.entity.Product;
import com.artem.training.store.utils.conect_utils.TakeConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class AddProduct {

    public static void productToStore(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Название продукта: ");
        String nameProduct = scanner.nextLine();
        System.out.print("Цена продукта: ");
        BigDecimal priceProduct = scanner.nextBigDecimal();

        ProductDao productDao = ProductDao.getInstance();

        Product product = new Product();
        product.setName(nameProduct);
        product.setPrice(priceProduct);

        productDao.save(product);



    }
}
