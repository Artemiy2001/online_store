package com.artem.training.store.utils.check_utils;

import com.artem.training.store.dao.BuyerDao;
import com.artem.training.store.entity.Buyer;

import java.util.Optional;

public class CheckBuyerLogin {

    public static Optional<Buyer> checkLogin(String user_name, String password) {


        BuyerDao buyerDao = BuyerDao.getInstance();

        Optional<Buyer> mayBeBuyer = buyerDao.findByName(user_name);
        if (mayBeBuyer.isEmpty()) {
            System.out.println("Такого пользователя не существует");
            return mayBeBuyer;
        }else {
            if (mayBeBuyer.get().getPassword().equals(password)) {
                return mayBeBuyer;
            }else {
                System.out.println("Неверный пароль");
                return mayBeBuyer;
            }

        }

    }
}
