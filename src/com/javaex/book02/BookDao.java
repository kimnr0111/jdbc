package com.javaex.book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDao {
	
	//필드
	
	//생성자
	public BookDao() {}
	
	//일반메소드
	
	//책추가
	public void bookInsert(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			System.out.println("접속성공");


		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "INSERT INTO book VALUES (seq_book_id.nextval, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(quary);
			
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());
			
			int count = pstmt.executeUpdate();		//insert, update, delete
		    
		    // 4.결과처리
			System.out.println(count + "건 처리되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
	    }
	}
	
	//책리스트 출력
	public List<BookVo> getBookList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> bookList = new ArrayList<BookVo>();


		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			System.out.println("접속성공");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "";
			quary += " select book_id,";
			quary += " bo.title,";
			quary += " bo.pubs,";
			quary += " bo.pub_date,";
			quary += " bo.author_id,";
			quary += " au.author_name,";
			quary += " au.author_desc";
			quary += " from book bo, author au";
			quary += " where bo.author_id = au.author_id";
			pstmt = conn.prepareStatement(quary);

			rs = pstmt.executeQuery();	//select

		    // 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				//리스트에 추가
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
				bookList.add(bookVo);
			}

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		    	if (rs != null) {
		            rs.close();
		        }                

		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
		
		return bookList;
	}
	
	//책수정
	public void bookUpdate(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			System.out.println("접속성공");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "";
			quary += "update book ";
			quary += "set title = ?, ";
			quary += "pubs = ?, ";
			quary += "pub_date = ?, ";
			quary += "author_id = ? ";
			quary += "where book_id = ? ";
			pstmt = conn.prepareStatement(quary);
			
			pstmt.setInt(5, bookVo.getBookId());
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());

			int count = pstmt.executeUpdate();		//insert, update, delete

		    
		    // 4.결과처리
			System.out.println(count + "건 처리되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
	}
	
	//책삭제
	public void bookDelete(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			System.out.println("접속성공");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "";
			quary += "delete from book ";
			quary += "where book_id = ? ";
			pstmt = conn.prepareStatement(quary);
			
			pstmt.setInt(1 , bookVo.getBookId());

			int count = pstmt.executeUpdate();		//insert, update, delete

		    
		    // 4.결과처리
			System.out.println(count + "건 처리되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
	}
	
	//검색
	public List<BookVo> searchBookList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> bookList = new ArrayList<BookVo>();


		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			System.out.println("접속성공");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "";
			quary += " select *";
			quary += " from book";
			pstmt = conn.prepareStatement(quary);

			rs = pstmt.executeQuery();	//select
			
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			System.out.println(str);

		    // 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				if(title.contains(str) || pubs.contains(str))
				{
					BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorId);
					bookList.add(bookVo);
				}
			}

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		    	if (rs != null) {
		            rs.close();
		        }                

		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
		
		return bookList;
	}
	
}
