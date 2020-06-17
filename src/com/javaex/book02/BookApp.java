package com.javaex.book02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		BookDao bookDao = new BookDao();
		
		//책추가
//		BookVo vo01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
//		bookDao.bookInsert(vo01);
		
		//책수정
//		BookVo vo02 = new BookVo(3, "우리들의 1그러진 영웅", "다림", "1998-02-22", 1);
//		bookDao.bookUpdate(vo02);

		//책삭제
//		BookVo vo03 = new BookVo(3);
//		bookDao.bookDelete(vo03);
		//bookDao.bookDelete(1);
		
		List<BookVo> bookList = bookDao.getBookList();
		//List<BookVo> bookList = bookDao.searchBookList();
		
		for(BookVo vo: bookList) {
			System.out.println("========================");
			System.out.println(vo.getBookId() + " " + vo.getTitle() + " " + vo.getPubs() + 
					" " + vo.getPubDate() + " " + vo.getAuthorId() + " " + vo.getAuthorName() +
					" " + vo.getAuthorDesc());
		}
		System.out.println("========================");

	}

}