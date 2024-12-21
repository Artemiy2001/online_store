package com.artem.training.store.utils.menu_utils;

import com.artem.training.store.utils.check_utils.CheckUsername;
import com.artem.training.store.utils.db_utils.AddBuyer;

import java.sql.SQLException;
import java.util.Scanner;

public class BuyerRegistration {

    static Scanner sc = new Scanner(System.in);

    public static void buyerRegistration() {
        String buyerName;
        String buyerAddress;
        String buyerPassword;


        while (true){
            System.out.print("Введите ваш логин: ");
            buyerName = sc.nextLine();

            if (CheckUsername.checkToUnique(buyerName)){
                break;
            }else {
                System.out.println();
                System.out.println("Такой логин уже используется!");
                System.out.println();
            }


        }

        System.out.print("Введите ваш адрес: ");
        buyerAddress = sc.nextLine();

        System.out.print("Введите ваш пароль: ");
        buyerPassword = sc.nextLine();


        try {
            AddBuyer.addToDbBuyer(buyerName, buyerAddress, buyerPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Покупатель зарегистрирован");
    }


}
