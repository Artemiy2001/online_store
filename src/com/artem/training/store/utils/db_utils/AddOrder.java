package com.artem.training.store.utils.db_utils;

import com.artem.training.store.dao.OrderDao;
import com.artem.training.store.entity.Buyer;
import com.artem.training.store.entity.Order;

import java.util.Scanner;

public class AddOrder {

    public static void makeOrder(int idProduct, Buyer buyer) {

        Order order = new Order();

        System.out.print("Выберите количество товара: ");
        Scanner scanner = new Scanner(System.in);
        int numProduct = scanner.nextInt();

        order.setBuyerId(buyer.getId());
        order.setProductId(idProduct);
        order.setQuantity(numProduct);

        OrderDao orderDao = OrderDao.getInstance();
        orderDao.save(order);


    }
}
