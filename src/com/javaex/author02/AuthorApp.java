package com.javaex.author02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		
		AuthorDao authorDao = new AuthorDao();
		
		//등록
		//AuthorVo vo01 = new AuthorVo("이문열", "경북 영양");
		//authorDao.authorInsert(vo01);
		
		//수정
		//AuthorVo vo03 = new AuthorVo(3, "이문얼", "경상북도 영양");
		//authorDao.authorUpdate(vo03);
		
		//삭제
		AuthorVo vo04 = new AuthorVo(3);
		authorDao.authorDelete(vo04);
		
		//리스트 가져오기
		List<AuthorVo> authorList = authorDao.getAuthorList();
		
		//출력
		for(AuthorVo vo : authorList) {
			System.out.println("========================");
			System.out.println(vo.getAuthorId() + " " + vo.getAuthorName() + " " + vo.getAuthorDesc());
		}
		
		System.out.println("========================");
		

	}

}
