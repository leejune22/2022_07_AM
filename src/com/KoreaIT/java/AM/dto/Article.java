package com.KoreaIT.java.AM.dto;

public class Article extends dto {

		public String title;
		public String content;
		public int hit;

		public Article(int id, String title, String content, String time) {
			this(id, title, content, time, 0);

		}

		public Article(int id, String title, String content, String time, int hit) {

			this.id = id;
			this.title = title;
			this.content = content;
			this.regDate = time;
			this.hit = hit;

		}

		public void increaseHit() {
			hit++;
		}

	

}
