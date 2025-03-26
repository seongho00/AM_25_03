package org.example;

import javax.swing.plaf.metal.MetalRadioButtonUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    List<Member> members;
    Scanner sc;
    boolean loginStatus = false;

    public boolean getIsLoginStatus() {
        return loginStatus;
    }

    public MemberController(Scanner sc) {
        this.members = new ArrayList<>();
        this.sc = sc;
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
            loginId = sc.nextLine().trim();

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
            loginPw = sc.nextLine().trim();
            if (loginPw.equals(loginPwById)) {
                break;
            } else {
                System.out.println("PW가 틀렸습니다.");
            }
        }
        loginStatus = true;
        System.out.println("로그인 되었습니다.");
    }

    public void join() {
        int lastRegId = 3;

        int id = lastRegId + 1;
        String loginPw;
        String loginId;

        if (!members.isEmpty()) {
            while (true) {
                boolean isNewMemberId = true;

                System.out.print("loginId : ");
                loginId = sc.nextLine().trim();


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
            loginId = sc.nextLine().trim();
        }

        while (true) {
            System.out.print("loginPw : ");
            loginPw = sc.nextLine().trim();
            System.out.print("비밀번호 확인 : ");
            String loginCheckPw = sc.nextLine().trim();
            if (loginPw.equals(loginCheckPw)) {
                break;
            }
            System.out.println("비밀번호를 확인해주세요");
        }

        System.out.print("name : ");
        String name = sc.nextLine().trim();
        String regDate = Util.getNowStr();


        members.add(new Member(id, regDate, loginId, loginPw, name));
        lastRegId = id;
        System.out.println("회원가입 되었습니다");
    }

    void makeTestData() {
        System.out.println("==테스트 데이터 생성==");
        members.add(new Member(1, "2024-12-12 12:12:12", "test1", "test1", "test1"));
        members.add(new Member(2, Util.getNowStr(), "test2", "test2", "test2"));
        members.add(new Member(3, Util.getNowStr(), "test3", "test3", "test3"));

    }

}
