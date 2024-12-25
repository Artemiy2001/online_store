package com.artem.training.store.dao;

import com.artem.training.store.entity.Buyer;
import com.artem.training.store.utils.conect_utils.ConnectionManager;
import com.artem.training.store.utils.conect_utils.TakeConnection;

import javax.annotation.processing.Generated;
import java.sql.*;

public class BuyerDao {

    private static final BuyerDao INSTANCE = new BuyerDao();

    private BuyerDao() {

    }

    private static final String DELETE_SQL = """
            DELETE FROM buyer
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO buyer(user_name, address, password)
            VALUES (?, ?, ?)
            
            """;


    public boolean deleteBuyer(int id) {

        try (Connection connection = TakeConnection.connection;
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Buyer saveBuyer(Buyer buyer) {

        try (Connection connection = TakeConnection.connection;
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, buyer.getName());
            preparedStatement.setString(2, buyer.getAddress());
            preparedStatement.setString(3, buyer.getPassword());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                buyer.setId(generatedKeys.getInt("id"));
            }
            return buyer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






    public static BuyerDao getInstance() {
        return INSTANCE;
    }
}
