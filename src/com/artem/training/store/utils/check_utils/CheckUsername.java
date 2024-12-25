package com.artem.training.store.utils.check_utils;

import com.artem.training.store.dao.BuyerDao;
import com.artem.training.store.entity.Buyer;
import com.artem.training.store.utils.conect_utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CheckUsername {

    public static boolean checkToUnique(String userName){

        BuyerDao buyerDao = BuyerDao.getInstance();

        Optional<Buyer> maybeBuyer = buyerDao.findByName(userName);

        return maybeBuyer.isEmpty();




    }
}
