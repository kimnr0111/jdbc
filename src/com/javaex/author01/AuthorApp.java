package com.javaex.author01;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		
		AuthorDao authorDao = new AuthorDao();
		
		//authorDao.authorInsert("이문열", "경북 영양");
		//authorDao.authorInsert("박경리", "경상남도 통영");
		//authorDao.authorUpdate(2, "수정-박경리", "수정-경남 통영");
		//authorDao.authorDelete(2);
		
		
		List<AuthorVo> authorList = authorDao.getAuthorList();
		
		//출력
		for(AuthorVo vo : authorList) {
			System.out.println("========================");
			System.out.println(vo.getAuthorId() + " " + vo.getAuthorName() + " " + vo.getAuthorDesc());
		}
		
		System.out.println("========================");
		

	}

}
