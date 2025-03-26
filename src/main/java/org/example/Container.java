package org.example;

import java.util.Scanner;

public class Container {
    static Scanner sc;


    public static void init() {
        sc = new Scanner(System.in);
    }

    public static Scanner getSc() {
        return sc;
    }

    public static void close() {
        sc.close();
    }

}
