package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		List<Article> articles = new ArrayList<>();

		int lastArticleId = 0;

		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}

				else {
					System.out.println("번호/제목");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("%d / %s\n", article.id, article.title);
					}
				}
			} else if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");
				String content = sc.nextLine();
				lastArticleId++;

				Article article = new Article(id, title, content);
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);

			}

			else if (cmd.startsWith("article detail ")) {
				System.out.println("");
			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
			}

			if (cmd.equals("exit")) {
				break;
			}
		}

		sc.close();

		System.out.println("==프로그램 종료==");
	}
}

class Article {
	int id;
	String title;
	String content;

	Article(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

}