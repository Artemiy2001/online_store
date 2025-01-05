package com.artem.training.store.utils.db_utils;


import com.artem.training.store.dao.BuyerDao;
import com.artem.training.store.entity.Buyer;

import java.sql.SQLException;

public final class AddBuyer {



    public static void addToDbBuyer(String buyerName, String buyerAddress, String password) throws SQLException {

        BuyerDao buyerDao = BuyerDao.getInstance();

        Buyer buyer = new Buyer();
        buyer.setName(buyerName);
        buyer.setAddress(buyerAddress);
        buyer.setPassword(password);

        buyerDao.saveBuyer(buyer);



    }
}
