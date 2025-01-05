package com.artem.training.store.utils.menu_utils;

import com.artem.training.store.dao.ProductDao;
import com.artem.training.store.entity.Buyer;
import com.artem.training.store.entity.Product;
import com.artem.training.store.utils.check_utils.CheckInput;
import com.artem.training.store.utils.db_utils.AddOrder;

import java.util.List;

public class BuyerOrder {

    public static void makeOrder(Buyer buyer) {

        ProductDao productDao = ProductDao.getInstance();

        int limit = 3;
        int offset = 0;
        int total = productDao.getCountProduct();

        while (true) {

            List<Product> productList = displayListProduct(productDao, limit, offset);

            System.out.println();


            if (offset > 0) {
                System.out.println("8) Назад");
            }

            if (offset + limit < total) {
                System.out.println("9) Далее");
            }

            System.out.println("0) Выход");

            System.out.print("Выберите действие: ");

            int input = CheckInput.IntInput();
            if (input == 8) {
                offset -= limit;
            } else if (input == 9) {
                offset += limit;
            } else if (input == 0) {
                break;
            } else if (input > 0 && input <= limit) {
                int idProduct = productList.get(input - 1).getId();
                AddOrder.makeOrder(idProduct, buyer);
            }


        }
    }

    private static List<Product> displayListProduct(ProductDao productDao, int limit, int offset) {
        List<Product> ListProducts = productDao.getAllProducts(limit, offset);

        System.out.println();
        for (int i = 0; i < ListProducts.size(); i++) {
            System.out.println((i + 1) + ") " +
                               ListProducts.get(i).getName() + " - " +
                               ListProducts.get(i).getPrice() + " руб");
        }
        return ListProducts;
    }
}
