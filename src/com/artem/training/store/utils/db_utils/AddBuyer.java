package com.artem.training.store.utils.db_utils;


import com.artem.training.store.utils.menu_utils.StartMenu;
import com.artem.training.store.utils.conect_utils.TakeConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public final class AddBuyer {



    public static void addToDbBuyer(String buyerName, String buyerAddress, String password) throws SQLException {

        String sql = """
                insert into buyer (user_name, address, password)(
                    values (?, ?, ?)
                )
                """;

        try (Connection connection = TakeConnection.connection;
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, buyerName);
            preparedStatement.setString(2, buyerAddress);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();

        }

    }
}
