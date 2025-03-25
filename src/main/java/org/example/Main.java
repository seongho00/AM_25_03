package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Article> articles = new ArrayList<>();
    static List<Member> members = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==프로그램 시작==");

        int lastRegId = 0;
        int lastArticleId = 3;

        makeTestData();

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

            if (cmd.equals("article write")) {
                System.out.println("==게시글 작성==");
                int id = lastArticleId + 1;
                String regDate = Util.getNowStr();
                String updateDate = Util.getNowStr();
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();

                Article article = new Article(id, regDate, updateDate, title, body);
                articles.add(article);

                System.out.println(id + "번 글이 작성되었습니다");
                lastArticleId++;
            } else if (cmd.startsWith("article list")) {
                if (articles.isEmpty()) {
                    System.out.println("아무것도 없어");
                } else if (!cmd.equals("article list")) {
                    List<String> input = new ArrayList<>(Arrays.asList(cmd.split(" ")));
                    String keyWord = input.get(2).trim();
                    System.out.println("==게시글 목록==");
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);
                        if (article.getTitle().contains(keyWord)) {
                            System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                        }
                    }

                } else {
                    System.out.println("==게시글 목록==");

                    System.out.println("   번호    /     날짜       /   제목     /    내용   ");
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);
                        if (Util.getNowStr().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                            System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                        } else {
                            System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                        }

                    }
                }

            } else if (cmd.startsWith("article detail")) {
                System.out.println("==게시글 상세보기==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = Util.findArticleById(id, articles);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }

                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("작성날짜 : " + foundArticle.getRegDate());
                System.out.println("수정날짜 : " + foundArticle.getUpdateDate());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());

            } else if (cmd.startsWith("article delete")) {
                System.out.println("==게시글 삭제==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = Util.findArticleById(id, articles);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }

                articles.remove(foundArticle);
                System.out.println(id + "번 게시글이 삭제되었습니다");
            } else if (cmd.startsWith("article modify")) {
                System.out.println("==게시글 수정==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = Util.findArticleById(id, articles);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }

                System.out.println("기존 제목 : " + foundArticle.getTitle());
                System.out.println("기존 내용 : " + foundArticle.getBody());
                System.out.print("새 제목 : ");
                String newTitle = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String newBody = sc.nextLine().trim();

                foundArticle.setTitle(newTitle);
                foundArticle.setBody(newBody);

                foundArticle.setUpdateDate(Util.getNowStr());

                System.out.println(id + "번 게시글이 수정되었습니다");
            } else if (cmd.equals("article reg")) {
                int id = lastRegId + 1;
                String loginPw;
                String loginId;

                if (!members.isEmpty()) {
                    while (true) {
                        int count = 0;
                        System.out.print("loginId : ");
                        loginId = sc.nextLine().trim();
                        for (Member member : members) {
                            if (loginId.equals(member.getLoginId())) {
                                System.out.println("이미 사용된 ID 입니다.");
                                count = 1;
                            }
                        }
                        if (count == 1) {
                            continue;
                        }
                        break;
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
                    if (!loginPw.equals(loginCheckPw)) {
                        System.out.println("비밀번호를 확인해주세요");
                        continue;
                    }
                    break;
                }

                System.out.print("name : ");
                String name = sc.nextLine().trim();
                String regDate = Util.getNowStr();


                members.add(new Member(id, regDate, loginId, loginPw, name));
                lastRegId = id;
                System.out.println("회원가입 되었습니다");


            } else if (cmd.equals("members list")) {
                for (Member member : members) {
                    System.out.println(member);
                }

            } else {
                System.out.println("사용할 수 없는 명령어입니다");
            }

        }

        System.out.println("==프로그램 끝==");
        sc.close();
    }

    /**
     * 테스트 데이터 생성 함수
     **/
    private static void makeTestData() {
        System.out.println("==테스트 데이터 생성==");
        articles.add(new Article(1, "2024-12-12 12:12:12", "2024-12-12 12:12:12", "제목1", "내용1"));
        articles.add(new Article(2, Util.getNowStr(), Util.getNowStr(), "제목2", "내용2"));
        articles.add(new Article(3, Util.getNowStr(), Util.getNowStr(), "제목3", "내용3"));
    }
}

class Member {
    private int id;
    private String regDate;
    private String loginId;
    private String loginPw;
    private String name;

    Member(int id, String regDate, String loginId, String loginPw, String name) {
        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Article {
    private int id;
    private String regDate;
    private String updateDate;
    private String title;
    private String body;

    public Article(int id, String regDate, String updateDate, String title, String body) {
        this.id = id;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.title = title;
        this.body = body;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}