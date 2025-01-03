package com.artem.training.store.utils.menu_utils;

import com.artem.training.store.dao.ProductDao;
import com.artem.training.store.entity.Buyer;
import com.artem.training.store.entity.Product;
import com.artem.training.store.utils.db_utils.AddOrder;

import java.util.List;
import java.util.Scanner;

public class BuyerOrder {

    public static void makeOrder(Buyer buyer) {

        ProductDao productDao = ProductDao.getInstance();

        int limit = 3;
        int offset = 0;
        int total = productDao.getCountProduct();

        while (true) {
            
            displayListProduct(productDao, limit, offset);

            System.out.println();

            if (offset > 0) {
                System.out.println("8) Назад");
            }

            if (offset + limit < total) {
                System.out.println("9) Далее");
            }

            System.out.print("Выберите действие: ");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if (input == 8) {
                offset -= limit;
            }else if (input == 9) {
                offset += limit;
            } else if (input > 0 && input <= limit) {
                int idProduct = offset + input;
                AddOrder.makeOrder(idProduct, buyer);
            }

        }
    }

    private static void displayListProduct(ProductDao productDao, int limit, int offset) {
        List<Product> allProducts = productDao.getAllProducts(limit, offset);

        System.out.println();
        for (int i = 0; i < allProducts.size(); i++) {
            System.out.println((i + 1) + ") " +
                               allProducts.get(i).getName() + " - " +
                               allProducts.get(i).getPrice() + " руб");
        }
    }
}
