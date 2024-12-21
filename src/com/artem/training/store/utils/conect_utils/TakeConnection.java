package com.artem.training.store.utils.conect_utils;

import java.sql.Connection;

public final class TakeConnection {

    public static Connection connection;

    static {
        connection = ConnectionManager.open();
    }
}
