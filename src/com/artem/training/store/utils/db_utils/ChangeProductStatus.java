package com.artem.training.store.utils.db_utils;

import com.artem.training.store.dao.BuyerDao;
import com.artem.training.store.dao.OrderDao;
import com.artem.training.store.dao.ProductDao;
import com.artem.training.store.entity.Buyer;
import com.artem.training.store.entity.Order;
import com.artem.training.store.utils.check_utils.CheckInput;

import java.util.*;

public class ChangeProductStatus {

    public static void changeStatus(){

        LinkedHashMap<Integer, List<Order>> buyerListHashMap = displayBuyer();

        List<Order> orders = displayProduct(buyerListHashMap);

        if (orders != null){
            confirmProduct(orders);
        }

    }

    private static void confirmProduct(List<Order> orders) {
        OrderDao orderDao = OrderDao.getInstance();

        System.out.println();
        System.out.println("1) Подтвердить все заказы");
        System.out.println("2) Отклонить все заказы");
        System.out.print("Выебрите действие: ");

        int input = CheckInput.IntInput();

        if(input == 1){
            for (Order order : orders) {
                order.setStatus("confirmed");
                orderDao.update(order);
            }
        } else if (input == 2) {
            for (Order order : orders) {
                order.setStatus("refused");
                orderDao.update(order);
            }
        } else {
            System.out.println("Такого варианта нет");
            confirmProduct(orders);
        }
    }

    private static List<Order> displayProduct(LinkedHashMap<Integer, List<Order>> buyerListHashMap) {
        ProductDao productDao = ProductDao.getInstance();
        System.out.println();
        System.out.println("0. Выход");
        System.out.println();
        System.out.print("Веберите покупателя для подтверждения заказов: ");

        int input = CheckInput.IntInput();

        if (input == 0) {
            return null;
        }

        if (input > 0 && input <= buyerListHashMap.size() + 1) {
            List<Order> orderList = buyerListHashMap.get(input);
            int counter = 1;
            for (Order order : orderList) {
                if (productDao.findById(order.getProductId()).isPresent()){
                    System.out.println(counter + ". " + productDao.findById(order.getProductId()).get().getName() +
                                        " - " + order.getQuantity() + "шт.");
                    counter++;
                }

            }
        }

        return buyerListHashMap.get(input);
    }

    private static LinkedHashMap<Integer, List<Order>> displayBuyer() {
        int counter = 1;

        LinkedHashMap<Integer, List<Order>> buyerOrders = new LinkedHashMap<>();

        System.out.println();
        System.out.println("Покупатели с неподтвержденными заказами:");

        BuyerDao buyerDao = BuyerDao.getInstance();
        OrderDao orderDao = OrderDao.getInstance();
        List<Buyer> allBuyers = buyerDao.findAll();
        List<Order> allOrders = orderDao.findAll();

        for (Buyer buyer : allBuyers) {
            int counterProcessingOrders = 0;
            List<Order> orders = new ArrayList<>();
            for (Order order : allOrders) {
                if (buyer.getId() == order.getBuyerId() && order.getStatus().equals("processing")) {
                    orders.add(order);
                    counterProcessingOrders++;
                }
            }
            if (counterProcessingOrders > 0) {
                buyerOrders.put(counter, orders);
                System.out.println(counter + ". " + buyer.getName() + ": " + counterProcessingOrders + " неподтверженных заказов");
                counter++;
            }
        }
        return buyerOrders;
    }
}
