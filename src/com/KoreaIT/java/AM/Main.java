package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		
		int writenum = 0;

		while (true) {
			
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			} else if (cmd.equals("article list")) {
				System.out.println("게시글이 없습니다");
			} else if (cmd.equals("article write")) {
				
				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");
				String content = sc.nextLine();
				writenum++;
				System.out.printf("%d번 글이 저장되었습니다./n" , writenum);

			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}

		sc.close();

		System.out.println("==프로그램 종료==");
	}
}
