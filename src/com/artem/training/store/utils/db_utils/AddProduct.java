package com.artem.training.store.utils.db_utils;

import com.artem.training.store.dao.ProductDao;
import com.artem.training.store.entity.Product;
import com.artem.training.store.utils.check_utils.CheckInput;

import java.util.Scanner;

public class AddProduct {

    public static void productToStore(){

        System.out.println();
        System.out.print("Название продукта: ");
        String nameProduct = CheckInput.StringInput();
        System.out.println();
        System.out.print("Цена продукта: ");
        double priceProduct = CheckInput.DoubleInput();

        ProductDao productDao = ProductDao.getInstance();

        Product product = new Product();
        product.setName(nameProduct);
        product.setPrice(priceProduct);

        productDao.save(product);



    }
}
