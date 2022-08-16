package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class App {

	private static List<Article> articles;
	private static List<Member> members;

	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
	
	public void run() {
	
	System.out.println("==프로그램 시작==");

	makeTestData();

	Scanner sc = new Scanner(System.in);

	while(true)
	{
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
				System.out.printf("날짜 : %s\n번호 : %d\n제목 : %s\n내용 : %s\n조회수 : %d\n", foundArticle.regDate, foundArticle.id,
						foundArticle.title, foundArticle.content, foundArticle.hit);
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

			String loginId = null;

			while (true) {
				System.out.println("아이디를 입력해주세요");
				loginId = sc.nextLine();

				if (isJoinableLoginId(loginId) == false) {
					System.out.printf("%s 는(은) 이미 사용중인 아이디입니다\n", loginId);
					continue;
				}

				break;
			}

			String loginPw = null;
			String doubleCheak = null;

			while (true) {
				System.out.println("비밀번호를 입력해주세요");
				loginPw = sc.nextLine();
				System.out.println("비밀번호 확인을 위해 다시 한 번 입력해주세요");
				doubleCheak = sc.nextLine();

				if (loginPw.equals(doubleCheak) == false) {
					System.out.println("비밀번호를 다시 입력해주세요\n");
					continue;
				}
				break;

			}

			Member member = new Member(id, regDate, name, loginId, loginPw);
			members.add(member);

			System.out.printf("%s 회원님의 회원가입이 완료되었습니다.\n", name);
			continue;

		}

		else if (cmd.equals("log in")) {

		}

		else {
			System.out.println("존재하지 않는 명령어입니다");
			continue;

		}

	}

	sc.close();System.out.println("==프로그램 종료==");
	
	}

	private static boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private static int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return i;
			}

			i++;
		}
		return -1;

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

