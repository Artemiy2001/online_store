package com.artem.training.store.utils.db_utils;

import com.artem.training.store.utils.conect_utils.TakeConnection;

import java.sql.*;
import java.util.Scanner;

public class AddProduct {

    public static void addMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Название продукта: ");
        String nameProduct = scanner.nextLine();
        System.out.print("Цена продукта: ");
        int priceProduct = scanner.nextInt();

        String sql = """
                insert into product(name, cost) (
                    values (?, ?)
                )
                """;

        try {
            Connection connection = TakeConnection.connection;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameProduct);
            preparedStatement.setInt(2, priceProduct);



            int result = preparedStatement.executeUpdate();
            System.out.println(result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
