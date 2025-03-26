package org.example.articleManager;

import org.example.Container;
import org.example.controller.Controller;
import org.example.controller.ArticleController;
import org.example.controller.MemberController;

public class App {
    MemberController memberController;
    ArticleController articleController;
    Controller controller = null;

    public App() {
        memberController = new MemberController();
        articleController = new ArticleController();

    }

    public void run() {
        System.out.println("==프로그램 시작==");

        memberController.makeTestData();
        articleController.makeTestData();


        while (true) {
            System.out.print("명령어) ");
            String cmd = Container.getSc().nextLine().trim();

            if (cmd.isEmpty()) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }

            String[] cmdBits = cmd.split(" ");

            if (cmdBits.length == 1) {
                System.out.println("명령어 확인 필요");
                continue;
            }

            String controllerName = cmdBits[0].trim();
            String actionMethodName = cmdBits[1].trim();

            String forLoginCheck = controllerName + "/" + actionMethodName;

            switch (forLoginCheck) {
                case "article/write":
                case "article/delete":
                case "article/modify":
                case "member/logout":
                    if (Controller.isLogined() == false) {
                        System.out.println("로그인 필요해");
                        continue;
                    }
                    break;
            }

            switch (forLoginCheck) {
                case "member/login":
                case "member/join":
                    if (Controller.isLogined()) {
                        System.out.println("로그아웃 필요해");
                        continue;
                    }
                    break;
            }



            switch (controllerName) {
                case "article":
                    controller = articleController;
                    break;

                case "member":
                    controller = memberController;
                    break;
                default:
                    System.out.println("올바른 명령어가 아닙니다.");
            }


            controller.doAction(cmd, actionMethodName);

            System.out.println("==프로그램 끝==");
        }
    }
}

