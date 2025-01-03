package com.artem.training.store.utils.db_utils;

import com.artem.training.store.dao.BuyerDao;
import com.artem.training.store.dao.OrderDao;
import com.artem.training.store.dao.ProductDao;
import com.artem.training.store.entity.Buyer;
import com.artem.training.store.entity.Order;

import java.util.*;

public class ChangeProductStatus {

    public static void changeStatus(){

        LinkedHashMap<Integer, List<Order>> buyerListHashMap = displayBuyer();

        displayProduct(buyerListHashMap);
    }

    private static void displayProduct(LinkedHashMap<Integer, List<Order>> buyerListHashMap) {
        ProductDao productDao = ProductDao.getInstance();
        System.out.println();
        System.out.print("Веберите покупателя для подтверждения заказов: ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        if (input > 0 && input <= buyerListHashMap.size() + 1) {
            List<Order> orderList = buyerListHashMap.get(input);
            int counter = 1;
            for (Order order : orderList) {
                if (productDao.findById(order.getProductId()).isPresent()){
                    System.out.println(counter + ". " + productDao.findById(order.getProductId()).get().getName());
                    counter++;
                }

            }
        }
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
