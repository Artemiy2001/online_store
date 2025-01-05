package com.artem.training.store.utils.check_utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckInput {

    static Scanner scanner = new Scanner(System.in);

    public static int IntInput(){
        try {
            return scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Вы ввели не число!");
            scanner.nextLine();
        }
        return -1;
    }

    public static double DoubleInput(){
        try {
            return scanner.nextDouble();
        }catch (InputMismatchException e){
            System.out.println("Вы ввели не число!");
            scanner.nextLine();
        }
        return -1;
    }

    public static String StringInput(){
        String input = scanner.nextLine();
        if (input.isEmpty()){
            return scanner.nextLine();
        }else {
            return input;
        }


    }


}
