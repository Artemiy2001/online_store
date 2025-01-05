package com.artem.training.store.utils.db_utils;

import com.artem.training.store.dao.OrderDao;
import com.artem.training.store.entity.Buyer;
import com.artem.training.store.entity.Order;
import com.artem.training.store.utils.check_utils.CheckInput;

import java.util.Scanner;

public class AddOrder {

    public static void makeOrder(int idProduct, Buyer buyer) {

        Order order = new Order();

        System.out.print("Выберите количество товара: ");

        int numProduct = CheckInput.IntInput();

        if (numProduct < 0 || numProduct > 100) {
            if (numProduct != -1){
                System.out.println("Недопутимое количество");
            }
        }

        order.setBuyerId(buyer.getId());
        order.setProductId(idProduct);
        order.setQuantity(numProduct);

        OrderDao orderDao = OrderDao.getInstance();
        orderDao.save(order);


    }
}
