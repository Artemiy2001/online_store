package com.artem.training.store.utils.menu_utils;

import com.artem.training.store.utils.check_utils.CheckBuyerLogin;

import java.util.Scanner;

public class BuyerLogin {

    private static String user_name;
    private static String password;

    public static void  buyerLog(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ваш логин: ");
        user_name = scanner.nextLine();

        System.out.print("Введите ваш пароль: ");
        password = scanner.nextLine();

        boolean res = CheckBuyerLogin.checkLogin(user_name, password);
        System.out.println(res);
    }
}
