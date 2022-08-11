package com.KoreaIT.java.AM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

				String time = formatter.format(date);

				int id = lastArticleId + 1;

				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");

				String content = sc.nextLine();

				lastArticleId++;
				Article article = new Article(id, title, content, time);
				articles.add(article);
				System.out.printf("%d번 글이 생성되었습니다.\n", id);

			}

			else if (cmd.startsWith("article detail ")) {
				String numEx = cmd;
				String[] result = numEx.split("article detail ");
				int articleNum = Integer.parseInt(result[1]);
				Article article = articles.get(articleNum - 1);
				System.out.printf("날짜 : %s\n번호 : %d\n제목 : %s\n내용 : %s\n", article.time, article.id, article.title,
						article.content);

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
	String time;

	Article(int id, String title, String content, String time) {

		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;

	}

}