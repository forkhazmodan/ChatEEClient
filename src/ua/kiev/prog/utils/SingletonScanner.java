package ua.kiev.prog.utils;

import java.util.Scanner;

public class SingletonScanner {

    private static SingletonScanner instance;
    private final Scanner scanner;

    private SingletonScanner() {
        this.scanner = new Scanner(System.in);
    }

    public static SingletonScanner getInstance() {
        if (instance == null) {
            instance = new SingletonScanner();
        }
        return instance;
    }

    public static Scanner scanner() {
        return SingletonScanner.getInstance().scanner;
    }

    public static void close() {
        SingletonScanner.getInstance().scanner.close();
    }
}
