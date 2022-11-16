package com.senla.bank.utils.scaners;

import java.util.Scanner;

public class ConsoleScanner {

    private final static Scanner scanner = new Scanner(System.in);

    public static String scanString() {
        return scanner.next();
    }

    public static Integer scanInt() {
        return scanner.nextInt();
    }

    public static Long scanLong() {
        return scanner.nextLong();
    }

    public static Double scanDouble() {
        return scanner.nextDouble();
    }

    public static Float scanFloat() {
        return scanner.nextFloat();
    }



}
