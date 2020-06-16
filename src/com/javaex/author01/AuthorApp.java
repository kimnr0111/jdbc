package com.javaex.author01;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		
		AuthorDao authorDao = new AuthorDao();
		
		//authorDao.authorInsert();
		
		List<AuthorVo> authorList = authorDao.getAuthorList();
		
		//출력
		for(AuthorVo vo : authorList) {
			System.out.println("========================");
			System.out.println(vo.getAuthorId() + " " + vo.getAuthorName() + " " + vo.getAuthorDesc());
		}
		
		System.out.println("========================");

	}

}
