package com.artem.training.store.utils.check_utils;

import com.artem.training.store.utils.conect_utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUsername {

    public static boolean checkToUnique(String userName){

        String sql = """
            select * from buyer
            where user_name = ?
        """;


        try {
            try (Connection connection = ConnectionManager.open()) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userName);
                ResultSet resultSet = preparedStatement.executeQuery();


                if (resultSet.next()) {
                    return false;
                }else {
                    return true;
                }


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
