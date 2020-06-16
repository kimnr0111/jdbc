package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsertTest2 {

	public static void main(String[] args) {
		//0. import java.sql.*;
		//1. JDBC 드라이버 (Oracle) 로딩
		//2. Connection 얻어오기
		//3. SQL문 준비 / 바인딩 / 실행
		// 4.결과처리
		//5. 자원정리

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
			
			// 2-1 commit 설정 변경
			conn.setAutoCommit(false);


		    // 3. SQL문 준비 / 바인딩 / 실행
			
			// -정보 등록
			String quary = "INSERT INTO book VALUES (seq_book_id.nextval, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(quary);
			System.out.println(quary);
			
			pstmt.setString(1 , "우리들의 일그러진 영웅");
			pstmt.setString(2 , "다림");
			pstmt.setString(3 , "1998-02-22");
			pstmt.setInt(4 , 1);

			int count = pstmt.executeUpdate();		//insert, update, delete
			System.out.println(count);
			//////////////////////////////////////////////////////////////////
			// +정보 등록			
			pstmt.setString(1 , "우리들의 일그러진 영웅");
			pstmt.setString(2 , "다림");
			pstmt.setString(3 , "1998-02-22");
			pstmt.setInt(4 , 20000);

			int count2 = pstmt.executeUpdate();		//insert, update, delete
			System.out.println(count2);
			conn.commit();
		    // 4.결과처리
			//db에서 확인
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		    try {
				System.out.println("롤백처리되었습니다.");
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
