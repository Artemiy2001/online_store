package com.artem.training.store.utils.menu_utils;

import com.artem.training.store.utils.db_utils.AddBuyer;

import java.util.Scanner;

public final class StartMenu {

    static Scanner scanner = new Scanner(System.in);

    private static int countStart = 1;

    public static void startMenu() {

        if (countStart == 1) {
            System.out.println("Welcome to the Store");
            System.out.println();
        }
        countStart++;

        System.out.println("1. Войти как покупатель");
        System.out.println("2. Войти как менеджер");
        System.out.print("Выберите варинат входа: ");



        if (scanner.nextInt() == 1){
            entryAsBuyer();
        }else if (scanner.nextInt() == 2){


        }else {
            System.out.println("Такого варианта нет");
            System.out.println();
            startMenu();
        }

        System.out.println();


    }

    private static void entryAsBuyer() {
        System.out.println("1. Вход");
        System.out.println("2. Регистрация");
        System.out.print("Выберите варинат входа: ");


        if (scanner.nextInt() == 1){
            BuyerLogin.buyerLog();
        } else if (scanner.nextInt() == 2) {
            BuyerRegistration.buyerRegistration();
        }else {
            System.out.println();
            System.out.println("Такого варианте нет");
            System.out.println();
            entryAsBuyer();
        }


        System.out.println();

    }



}
