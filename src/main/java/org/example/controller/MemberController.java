package org.example.controller;

import org.example.Container;
import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MemberController extends Controller {

    public boolean getIsLoginStatus() {
        return loginStatus;
    }

    List<Member> members;
    private boolean loginStatus = false;
    int lastRegId = 3;
    String cmd;

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "login":
                login();
                break;
            case "logout":
                logout();
                break;
            case "join":
                doJoin();
                break;

            default:
                System.out.println("Unknown action method");
                break;
        }

    }


    public MemberController() {
        this.members = new ArrayList<>();
    }


    public void logout() {
        System.out.println("로그아웃되었습니다.");
        loginStatus = false;
    }


    public void login() {
        String loginId;
        String loginPw;
        String loginPwById = "";
        while (true) {
            System.out.print("loginId : ");
            loginId = Container.getSc().nextLine().trim();

            boolean isLoginId = false;

            for (Member member : members) {
                if (loginId.equals(member.getLoginId())) {
                    loginPwById = member.getLoginPw();
                    isLoginId = true;
                    break;
                }
            }

            if (!isLoginId) {
                System.out.println("회원가입 되어있지 않은 ID 입니다.");
                continue;
            }
            System.out.print("loginPw : ");
            loginPw = Container.getSc().nextLine().trim();
            if (loginPw.equals(loginPwById)) {
                break;
            } else {
                System.out.println("PW가 틀렸습니다.");
            }
        }
        loginStatus = true;
        System.out.println("로그인 되었습니다.");
    }


    public void doJoin() {

        int id = lastRegId + 1;
        String loginPw;
        String loginId;

        if (!members.isEmpty()) {
            while (true) {
                boolean isNewMemberId = true;

                System.out.print("loginId : ");
                loginId = Container.getSc().nextLine().trim();


                for (Member member : members) {
                    if (loginId.equals(member.getLoginId())) {
                        System.out.println("이미 사용된 ID 입니다.");
                        isNewMemberId = false;
                        break;
                    }
                }

                if (isNewMemberId) {
                    break;
                }
            }

        } else {
            System.out.print("loginId : ");
            loginId = Container.getSc().nextLine().trim();
        }

        while (true) {
            System.out.print("loginPw : ");
            loginPw = Container.getSc().nextLine().trim();
            System.out.print("비밀번호 확인 : ");
            String loginCheckPw = Container.getSc().nextLine().trim();
            if (loginPw.equals(loginCheckPw)) {
                break;
            }
            System.out.println("비밀번호를 확인해주세요");
        }

        System.out.print("name : ");
        String name = Container.getSc().nextLine().trim();
        String regDate = Util.getNowStr();


        members.add(new Member(id, regDate, loginId, loginPw, name));
        lastRegId = id;
        System.out.println("회원가입 되었습니다");
    }

    public void makeTestData() {
        System.out.println("==테스트 데이터 생성==");
        members.add(new Member(1, Util.getNowStr(), "test1", "test1", "test1"));
        members.add(new Member(2, Util.getNowStr(), "test2", "test2", "test2"));
        members.add(new Member(3, Util.getNowStr(), "test3", "test3", "test3"));

    }


}
