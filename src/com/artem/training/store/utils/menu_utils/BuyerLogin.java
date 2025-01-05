package com.artem.training.store.utils.menu_utils;

import com.artem.training.store.entity.Buyer;
import com.artem.training.store.utils.check_utils.CheckBuyerLogin;
import com.artem.training.store.utils.check_utils.CheckInput;

import java.util.Optional;
import java.util.Scanner;

public class BuyerLogin {

    private static String user_name;
    private static String password;

    public static Optional<Buyer> buyerLog(){

        System.out.println();
        System.out.print("Введите ваш логин: ");
        user_name = CheckInput.StringInput();

        System.out.print("Введите ваш пароль: ");
        password = CheckInput.StringInput();

        return CheckBuyerLogin.checkLogin(user_name, password);

    }
}
