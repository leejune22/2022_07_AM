package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static List<Article> articles;
	private static List<Member> members;

	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		makeTestData();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("article list") || cmd.equals("l")) {

				if (articles.size() == 0) {

					System.out.println("게시글이 없습니다");
					continue;
				}

				else {

					System.out.println("번호/제목/조회수");

					for (int i = articles.size() - 1; i >= 0; i--) {

						Article article = articles.get(i);
						System.out.printf("%d / %s / %d\n", article.id, article.title, article.hit);

					}
					continue;

				}

			} else if (cmd.equals("article write") || cmd.equals("t")) {

				int id = articles.size() + 1;

				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");

				String content = sc.nextLine();

				String time = Util.getDateStr();
				Article article = new Article(id, title, content, time);
				articles.add(article);
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				continue;

			}

			else if (cmd.startsWith("article detail ")) {
				String[] numEx = cmd.split("article detail ");
				int id = Integer.parseInt(numEx[1]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다\n", id);
					continue;

				}

				else {
					foundArticle.increaseHit();
					System.out.printf("날짜 : %s\n번호 : %d\n제목 : %s\n내용 : %s\n조회수 : %d\n", foundArticle.time,
							foundArticle.id, foundArticle.title, foundArticle.content, foundArticle.hit);
					continue;

				}

			}

			else if (cmd.startsWith("article delete ")) {
				String[] numEx = cmd.split("article delete ");
				int id = Integer.parseInt(numEx[1]);

				int foundindex = getArticleByIndex(id);

				if (foundindex == -1) {
					System.out.printf("%d번 게시글은 존재하지 않습니다\n", id);
					continue;
				}

				articles.remove(foundindex);
				System.out.printf("%d번 글을 삭제했습니다.\n", id);
				continue;
			}

			else if (cmd.startsWith("article modify ")) {
				String[] numEx = cmd.split("article modify ");
				int id = Integer.parseInt(numEx[1]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("새 제목 : ");
				String title = sc.nextLine();
				System.out.printf("새 내용 : ");
				String content = sc.nextLine();

				foundArticle.title = title;
				foundArticle.content = content;

				System.out.printf("%d번 글을 수정했습니다.\n", id);
				continue;
			}

			else if (cmd.equals("member join")) {
				int id = articles.size() + 1;
				String regDate = Util.getDateStr();

				System.out.println("이름을 입력해주세요");
				String name = sc.nextLine();
				System.out.println("아이디를 입력해주세요");
				String loginId = sc.nextLine();
				System.out.println("비밀번호를 입력해주세요");
				String loginPw = sc.nextLine();

				Member member = new Member(id, regDate, name, loginId, loginPw);
				members.add(member);

				System.out.printf("%s 회원님의 회원가입이 완료되었습니다.\n", name);
				continue;

			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;

			}

		}

		sc.close();
		System.out.println("==프로그램 종료==");

	}

	private static int getArticleByIndex(int id) {
		int a = 0;
		for (int i1 = 0; i1 < articles.size(); i1++) {
			Article article = articles.get(i1);

			if (article.id == id) {
				return a;
			}
			a++;

		}
		return -1;
	}

	private static Article getArticleById(int id) {
		int index = getArticleByIndex(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	private static void makeTestData() {
		System.out.println("테스트 데이터를 생성했습니다");
		articles.add(new Article(1, "제목1", "내용1", Util.getDateStr(), 10));
		articles.add(new Article(2, "제목2", "내용2", Util.getDateStr(), 20));
		articles.add(new Article(3, "제목3", "내용3", Util.getDateStr(), 30));
	}

}

class Article {
	int id;
	String title;
	String content;
	String time;
	int hit;

	Article(int id, String title, String content, String time) {
		this(id, title, content, time, 0);

	}

	Article(int id, String title, String content, String time, int hit) {

		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.hit = hit;

	}

	public void increaseHit() {
		hit++;
	}

}

class Member {
	int id;
	String regDate;
	String loginId;
	String loginPw;
	String name;

	Member(int id, String regDate, String name, String loginId, String loginPw) {
		this.id = id;
		this.regDate = regDate;
		this.name = name;
		this.loginId = loginId;
		this.loginPw = loginPw;

	}

}