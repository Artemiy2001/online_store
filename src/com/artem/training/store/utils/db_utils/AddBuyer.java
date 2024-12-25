package com.artem.training.store.utils.db_utils;


import com.artem.training.store.dao.BuyerDao;
import com.artem.training.store.entity.Buyer;
import com.artem.training.store.utils.menu_utils.StartMenu;
import com.artem.training.store.utils.conect_utils.TakeConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

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
