package org.example.articleManager;

import org.example.Container;

public class Main {

    public static void main(String[] args) {
        Container.init();
        new App().run();
        Container.close();
    }
}



