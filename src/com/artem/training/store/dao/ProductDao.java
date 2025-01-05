package com.artem.training.store.dao;

import com.artem.training.store.entity.Product;
import com.artem.training.store.utils.conect_utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao {

    private static final ProductDao INSTANCE = new ProductDao();

    private ProductDao() {

    }

    private static final String GET_COUNT_PRODUCT_SQL = """
            SELECT COUNT(*)
            FROM product
            """;

    private static final String GET_ALL_PRODUCTS_SQL = """
            SELECT id, name, cost
            FROM product
            
            """;

    private static final String DELETE_SQL = """
            DELETE FROM product
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO product(name, cost)
            VALUES (?, ?)
            
            """;

    private static final String UPDATE_SQL = """
            UPDATE product
            SET name = ?,
                cost = ?
     
            WHERE id = ?
            
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT id, name, cost
            FROM product
            WHERE id = ?
            """;

    private static final String FIND_BY_NAME_SQL = """
            
            SELECT id, name, cost
            FROM product
            WHERE name = ?
            """;

    public int getCountProduct() {

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_PRODUCT_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }else {
                return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts(int limit, int offset) {

        String sql = GET_ALL_PRODUCTS_SQL + """
                LIMIT ?
                OFFSET ?
                """;

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            List<Product> products = new ArrayList<>();

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("cost")
                ));
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Optional<Product> findByName(String name) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_SQL)) {

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            Product product = null;

            if (resultSet.next()) {
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("cost")
                );
            }

            return Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Product> findById(int id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Product product = null;

            if (resultSet.next()) {
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("cost")

                );
            }

            return Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Product product) {

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setLong(4, product.getId());

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

    public Product save(Product product) {

        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());


            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt("id"));
            }
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
