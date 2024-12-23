package com.artem.training.store.utils.check_utils;

import com.artem.training.store.utils.conect_utils.TakeConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBuyerLogin {

    public static boolean checkLogin(String user_name, String password) {


        String sql = """
                select user_name, password from buyer
                where user_name = ?
                
                """;


        try {
            Connection connection = TakeConnection.connection;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user_name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("password").equals(password)){
                    return true;
                }else {
                    System.out.println("Неверный пароль!");
                    return false;
                }
            }else {
                System.out.println("Такого пользователя не существует");
                return false;
            }



        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
