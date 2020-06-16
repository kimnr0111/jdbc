package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdateTest {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		// 3. SQL문 준비 / 바인딩 / 실행
		// 4. 결과처리
		// 5. 자원정리
		
		// 0. import java.sql.*;
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
			String query = ""; //쿼리문 문자열만들기, ? 주의
			query +=" update author ";
			query +=" set author_desc = ? ";
			query +=" where author_id = ? ";
			pstmt = conn.prepareStatement(query); //쿼리로 만들기
			//가정......
			String desc ="서울특별시"; //화면에서 받은값이라고 가정
			int no = 5; //화면에서 받은값이라고 가정
			pstmt.setString(1, desc);
			pstmt.setInt(2, no);
			int count = pstmt.executeUpdate();  //쿼리문 실행
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

}