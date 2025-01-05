package com.artem.training.store.utils.db_utils;

import com.artem.training.store.dao.OrderDao;
import com.artem.training.store.dao.ProductDao;
import com.artem.training.store.entity.Buyer;
import com.artem.training.store.entity.Order;

import java.util.List;

public class BuyerProductStatus {

    public static void productToReady(Buyer buyer) {
        statusProduct(buyer, "confirmed");
    }

    public static void productToProcessing(Buyer buyer) {
        statusProduct(buyer, "processing");
    }

    private static void statusProduct(Buyer buyer, String status) {
        OrderDao orderDao = OrderDao.getInstance();
        ProductDao productDao = ProductDao.getInstance();

        List<Order> allOrders = orderDao.findAll();
        int counter = 1;

        System.out.println();
        for (Order order : allOrders) {
            if (order.getBuyerId() == buyer.getId()) {
                int idProduct = order.getProductId();
                if (productDao.findById(idProduct).isPresent()) {
                    if (order.getStatus().equals(status)){
                        String nameProduct = productDao.findById(idProduct).get().getName();
                        System.out.println(counter + ". " + nameProduct + " - " + order.getQuantity() + "шт: " + order.getStatus());
                        counter++;
                    }
                }

            }
        }
    }


}
