package com.artem.training.store.utils.menu_utils;

import com.artem.training.store.entity.Buyer;
import com.artem.training.store.utils.db_utils.AddBuyer;
import com.artem.training.store.utils.db_utils.AddProduct;

import java.util.Optional;
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


        int entryOption = scanner.nextInt();
        if (entryOption == 1){
            entryAsBuyer();
        }else if (entryOption == 2){
            entryAsManager();

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

        int entryOption = scanner.nextInt();
        if (entryOption == 1){
            Optional<Buyer> mayBeBuyer = BuyerLogin.buyerLog();
            if (mayBeBuyer.isPresent()) {
                buyerMenu(mayBeBuyer.get());
            }

        } else if (entryOption == 2) {
            BuyerRegistration.buyerRegistration();
        }else {
            System.out.println();
            System.out.println("Такого варианте нет");
            System.out.println();
            entryAsBuyer();
        }


        System.out.println();

    }

    private static void entryAsManager(){
        System.out.println("1. Добавить продукт");
        System.out.println("2. Подтвердить заказ");
        System.out.print("Выберите действие:");
        int entryOption = scanner.nextInt();
        if (entryOption == 1){
            AddProduct.productToStore();
            entryAsManager();
        }
    }

    private static void buyerMenu(Buyer buyer){
        System.out.println("1. Мои покупки");
        System.out.println("2. Заказы в обработке");
        System.out.println("3. Сделать заказ");

        int entryOption = scanner.nextInt();

        if (entryOption == 1){
            System.out.println("В разработке");
        }else if (entryOption == 2){
            System.out.println("В разработке");
        }else if (entryOption == 3){
            BuyerOrder.makeOrder(buyer);
        }else {
            System.out.println("Такого варианта нет");
            buyerMenu(buyer);
        }
    }



}
