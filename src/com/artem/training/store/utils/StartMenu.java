package com.artem.training.store.utils;

import java.util.Scanner;

public final class StartMenu {



    public static void startMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Store");
        System.out.println("1. Войти как покупатель");
        System.out.println("2. Войти как менеджер");
        System.out.print("Выберите варинат входа: ");

        if (scanner.nextInt() == 2){
            managerIn();
        }

    }

    private static void managerIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Управление товарами");
        System.out.println("2. Отчеты");

        if (scanner.nextInt() == 1){
            productManagement();
        }

    }

    private static void productManagement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Добавить товар");
        System.out.println("2. Удалить товар");

        AddProduct.addMenu();
    }


}
