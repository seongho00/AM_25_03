package org.example;

public class App {
    MemberController memberController;
    ArticleController articleController;

    public App() {
        memberController = new MemberController();
        articleController = new ArticleController();
    }

    public void run() {


        System.out.println("==프로그램 시작==");

        articleController.makeTestData();
        memberController.makeTestData();


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

            if (cmd.equals("login")) {
                memberController.login();

            } else if (cmd.equals("member join")) {
                memberController.doJoin();


            } else if (cmd.equals("logout")) {
                memberController.logout();

            } else if (memberController.getIsLoginStatus()) {
                if (cmd.equals("article write")) {
                    articleController.doWrite();

                } else if (cmd.startsWith("article list")) {
                    articleController.showList(cmd);


                } else if (cmd.startsWith("article detail")) {
                    articleController.showDetail(cmd);


                } else if (cmd.startsWith("article delete")) {
                    articleController.doDelete(cmd);


                } else if (cmd.startsWith("article modify")) {
                    articleController.doModify(cmd);

                } else {
                    System.out.println("사용할 수 없는 명령어입니다.");
                }
            } else {
                System.out.println("로그인이 필요한 명령어입니다.");
            }
        }


        System.out.println("==프로그램 끝==");
    }


}

