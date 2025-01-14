package com.artem.training.store.dao;

import com.artem.training.store.entity.Order;
import com.artem.training.store.utils.conect_utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();

    private OrderDao() {

    }

    private static final String FIND_ALL = """
            SELECT id, id, id_product, id_buyer, num_products, status
            FROM orders
            """;

    private static final String DELETE_SQL = """
            DELETE FROM orders
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO orders(id_product, id_buyer, num_products)
            VALUES (?, ?, ?)
            
            """;

    private static final String UPDATE_SQL = """
            UPDATE orders
            SET id_product = ?,
                id_buyer = ?,
                num_products = ?,
                status = ?
     
            WHERE id = ?
            
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT id, id, id_product, id_buyer, num_products, status
            FROM orders
            WHERE id = ?
            """;


    public static OrderDao getInstance() {
        return INSTANCE;
    }

    public List<Order> findAll() {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {

            List<Order> orders = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_buyer"),
                        resultSet.getInt("id_product"),
                        resultSet.getInt("num_products"),
                        resultSet.getString("status")
                );
                orders.add(order);
            }

            return orders;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Order> findById(int id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Order order = null;

            if (resultSet.next()) {
                order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_product"),
                        resultSet.getInt("id_buyer"),
                        resultSet.getInt("num_products"),
                        resultSet.getString("status")
                );
            }

            return Optional.ofNullable(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Order order) {

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setInt(1, order.getProductId());
            preparedStatement.setInt(2, order.getBuyerId());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setLong(5, order.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean delete(int id) {

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Order save(Order order) {

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, order.getProductId());
            preparedStatement.setInt(2, order.getBuyerId());
            preparedStatement.setInt(3, order.getQuantity());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt("id"));
            }
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
