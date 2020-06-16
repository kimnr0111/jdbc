package com.javaex.author01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	
	//필드
	
	//생성자
	public AuthorDao() {}
	
	//일반메소드
	
	//작가추가
	public void authorInsert() {
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
			String quary = "INSERT INTO author VALUES (seq_author_id.nextval, ?, ? )";
			pstmt = conn.prepareStatement(quary);
			System.out.println(quary);
			
			pstmt.setString(1 , "김영하");	//?(물음표) 첫번째
			pstmt.setString(2 , "알쓸신잡");	//?(물음표) 두번째

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
	public List<AuthorVo> getAuthorList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AuthorVo> authorList = new ArrayList();


		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			System.out.println("접속성공");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "";
			quary += " select author_id,";
			quary += " 		  author_name,";
			quary += " 		  author_desc";
			quary += " from author";
			pstmt = conn.prepareStatement(quary);

			rs = pstmt.executeQuery();	//select

		    // 4.결과처리
			while(rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				//리스트에 추가
				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(authorVo);
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
		
		return authorList;
	}
	

}
