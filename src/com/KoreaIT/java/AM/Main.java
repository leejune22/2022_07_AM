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
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}

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
					continue;

				}

			} else if (cmd.equals("article write") || cmd.equals("t")) {

				int id = lastArticleId + 1;

				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");

				String content = sc.nextLine();

				String time = formatter.format(date);
				lastArticleId++;
				Article article = new Article(id, title, content, time);
				articles.add(article);
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				continue;

			}

			else if (cmd.startsWith("article detail ")) {
				String[] numEx = cmd.split("article detail ");
				int articleNum = Integer.parseInt(numEx[1]);
				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == articleNum) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다\n", articleNum);

				}

				else {

					System.out.printf("날짜 : %s\n번호 : %d\n제목 : %s\n내용 : %s\n", foundArticle.time, foundArticle.id,
							foundArticle.title, foundArticle.content);
					continue;
				}

			}

			else if (cmd.startsWith("article delete ")) {
				String[] numEx = cmd.split("article delete ");
				int articleNum = Integer.parseInt(numEx[1]);
				
				int foundindex = -1;
				
				for(int i=0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if (article.id == articleNum) {
						foundindex = i;
						break;
					}
					
				}
				
				if(foundindex == -1) {
					System.out.printf("%d번 게시글은 존재하지 않습니다\n" , articleNum);
					continue;
				}
				
				articles.remove(foundindex);
				System.out.printf("%d번 글을 삭제했습니다.\n", articleNum);
				continue;
			}
			

			else if (cmd.startsWith("article modify ")) {
				String[] numEx = cmd.split("article modify ");
				int articleNum = Integer.parseInt(numEx[1]);
				
				Article foundArticle = null;
				
				for(int i=0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if (article.id == articleNum) {
						foundArticle = article;
						break;
					}
					
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n" , articleNum);
					continue;
				}
				
				System.out.printf("새 제목 : ");
				String title = sc.nextLine();
				System.out.printf("새 내용 : ");
				String content = sc.nextLine();

				foundArticle.title = title;
				foundArticle.content = content;

				System.out.printf("%d번 글을 수정했습니다.\n", articleNum);
				continue;
			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;

			}

			sc.close();

		}

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
