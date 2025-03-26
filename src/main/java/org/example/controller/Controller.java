package org.example.controller;

import org.example.dto.Member;

import java.util.List;

public class Controller {

    protected static Member loginedMember;
    protected List<Member> members;

    public Member getLoginedMember() {
        return loginedMember;
    }

    public List<Member> getMembers() {
        return members;
    }

    public static boolean isLogined() {
        return loginedMember != null;
    }

    public void doAction(String cmd, String actionMethodName) {

    }
}
