package com.artem.training.store.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private ConnectionManager(){

    }

    private static final String URL_KYE = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";

    public static Connection open(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KYE),
                    PropertiesUtil.get(USERNAME),
                    PropertiesUtil.get(PASSWORD)
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
