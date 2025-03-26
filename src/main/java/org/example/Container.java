package org.example;

import java.util.Scanner;

public class Container {
    static Scanner sc;


    static void init() {
        sc = new Scanner(System.in);
    }

    public static Scanner getSc() {
        return sc;
    }

    static void close() {
        sc.close();
    }

}
