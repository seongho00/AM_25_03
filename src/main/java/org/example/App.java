package org.example;

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

            String[] cmdBits = cmd.split(" ");

            if (cmdBits.length == 1) {
                System.out.println("명령어 확인 필요");
            }

            String controllerName = cmdBits[0].trim();
            String actionMethodName = cmdBits[1].trim();

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

            if (memberController.getIsLoginStatus()) {
                System.out.println("로그인이 필요한 서비스입니다.");
                continue;
            }

            controller.doAction(cmd, actionMethodName);

            System.out.println("==프로그램 끝==");
        }
    }
}

