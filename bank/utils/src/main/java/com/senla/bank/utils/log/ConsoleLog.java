package com.senla.bank.utils.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLog {

    public static void log(String s, Object...params) {
        System.out.println(
                String.format(s, params)
        );
    }

    public static void trace(String s, Object...params) {
        System.out.println(
                String.format("[TRACE] " + getCurrentDate() + "| " + s, params)
        );
    }

    public static void debug(String s, Object...params) {
        System.out.println(
                String.format("[DEBUG] " + getCurrentDate() + "| " + s, params)
        );
    }

    public static void info(String s, Object...params) {
        System.out.println(
                String.format("[INFO] " + getCurrentDate() + "| " + s, params)
        );
    }

    public static void warn(String s, Object...params) {
        System.out.println(
                String.format("[WARNING] " + getCurrentDate() + "| " + s, params)
        );
    }

    public static void error(String s, Object...params) {
        System.out.println(
                String.format("[ERROR] " + getCurrentDate() + "| " + s, params)
        );
    }

    public static void fatal(String s, Object...params) {
        System.out.println(
                String.format("[FATAL] " + getCurrentDate() + "| " + s, params)
        );
    }

    private static String getCurrentDate() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss.SSS");
        return localDate.format(formatter);
    }

}
