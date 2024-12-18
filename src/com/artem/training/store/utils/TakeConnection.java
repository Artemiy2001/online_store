package com.artem.training.store.utils;

import java.sql.Connection;

public final class TakeConnection {

    public static Connection connection;

    static {
        connection = ConnectionManager.open();
    }
}
