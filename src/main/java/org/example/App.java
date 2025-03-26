package org.example;

import java.util.Scanner;

public class App {

    public App() {
    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);


        System.out.println("==프로그램 시작==");


        articleController.makeTestData();
        memberController.makeTestData();


        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.equals("login")) {
                memberController.login();

            } else if (cmd.equals("member join")) {
                memberController.join();


            } else if (cmd.equals("logout")) {
                memberController.logout();

            }


            if (memberController.getIsLoginStatus() && !cmd.equals("login")) {
                if (cmd.equals("article write")) {
                    articleController.write();

                } else if (cmd.startsWith("article list")) {
                    articleController.list(cmd);


                } else if (cmd.startsWith("article detail")) {
                    articleController.detail(cmd);


                } else if (cmd.startsWith("article delete")) {
                    articleController.delete(cmd);


                } else if (cmd.startsWith("article modify")) {
                    articleController.modify(cmd);

                } else {
                    System.out.println("사용할 수 없는 명령어입니다");
                }
            } else {
                System.out.println("로그인이 필요한 서비스입니다.");
            }
        }


        System.out.println("==프로그램 끝==");
        sc.close();
    }


}

