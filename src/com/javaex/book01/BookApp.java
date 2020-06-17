package com.javaex.book01;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		BookDao bookDao = new BookDao();
		
		//bookDao.bookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		//bookDao.bookUpdate("우리들의 1그러진 영웅", 1);
		bookDao.bookDelete(1);
		
		List<BookVo> bookList = bookDao.getBookList();
		
		for(BookVo vo: bookList) {
			System.out.println("========================");
			System.out.println(vo.getBookId() + " " + vo.getTitle() + " " + vo.getPubs() + " " + vo.getPubDate() + " " + vo.getAuthorId());
		}
		System.out.println("========================");

	}

}
