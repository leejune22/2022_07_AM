package com.KoreaIT.java.AM.dto;

public class Member extends dto {

	public String loginId;
	public String loginPw;
	public String name;

	public Member(int id, String regDate, String name, String loginId, String loginPw) {
		this.id = id;
		this.regDate = regDate;
		this.name = name;
		this.loginId = loginId;
		this.loginPw = loginPw;

	}

}
