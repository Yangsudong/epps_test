package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.ConnectionManager;
import model.BoardVO;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//싱글톤
	static BoardDAO instance;
	public static BoardDAO getInstance() {
		if(instance==null)
			instance=new BoardDAO();
			return instance;
	}
	
	public int count(BoardVO boardVO) {
		int cnt = 0;
		try {
			conn = ConnectionManager.getConnnect();
			String where = "where 1=1";
			if(boardVO.getCompany_name() !=null) {
				where += "and company_name like '%' || ? ||  '%'";
			}
			
			String sql = "select count(*) from NOTICE_YANGSUDONG " + where;
			pstmt = conn.prepareStatement(sql);
			
			int pos = 1;
			if(boardVO.getCompany_name() !=null) {
				pstmt.setString(pos++, boardVO.getCompany_name());
			}
			
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1); 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(conn);
		}
		return cnt;
	}
	
	//전체조회
	public ArrayList<BoardVO> selectAll(BoardVO boardVO) {
		BoardVO resultVO = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>(); 
		
		try {
			conn = ConnectionManager.getConnnect();
			String where = " where 1=1 ";
			if(boardVO.getCompany_name() !=null) {
				where += " and title like '%' || ? ||  '%' ";
			}
			String sql =" SELECT A.* FROM(SELECT B.*, ROWNUM RM FROM ( " 
					   +" SELECT BOARD_ID, COMPANY_NAME, WRITE_DATE, TITLE, WRITER, CONTENT, PASSWORD, VIEWS, UPLOAD_FILE "
					   +" FROM NOTICE_YANGSUDONG "
					   + where
					   +" ORDER BY BOARD_ID "
					   +" ) B) A WHERE RM BETWEEN ? AND ? ";
			
			pstmt = conn.prepareStatement(sql);		
			int pos = 1;
			if(boardVO.getCompany_name() != null) {
				pstmt.setString(pos++, boardVO.getCompany_name());
			}
			pstmt.setInt(pos++, boardVO.getFirst());
			pstmt.setInt(pos++, boardVO.getLast());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultVO = new BoardVO();
				resultVO.setBoard_id(rs.getString(1));
				resultVO.setCompany_name(rs.getString(2));
				resultVO.setWrite_date(rs.getString(3));
				resultVO.setTitle(rs.getString(4));
				resultVO.setWriter(rs.getString(5));
				resultVO.setContent(rs.getString(6));
				resultVO.setPassword(rs.getString(7));
				resultVO.setViews(rs.getString(8));
				resultVO.setUpload_file(rs.getString(9));
				list.add(resultVO);
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public ArrayList<BoardVO> selectCompany(BoardVO boardVO) {
		BoardVO resultVO = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>(); 
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql =" SELECT DISTINCT COMPANY_NAME FROM NOTICE_YANGSUDONG";
			pstmt = conn.prepareStatement(sql);		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultVO = new BoardVO();
				resultVO.setCompany_name(rs.getString(1));
				list.add(resultVO);
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public ArrayList<BoardVO> selectCompanyView(BoardVO boardVO) {
		BoardVO resultVO = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>(); 
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql =" SELECT BOARD_ID, COMPANY_NAME, WRITE_DATE, TITLE, WRITER, CONTENT, PASSWORD, VIEWS, UPLOAD_FILE "
					+ "	FROM NOTICE_YANGSUDONG "
					+ "	WHERE COMPANY_NAME = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getCompany_name());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultVO = new BoardVO();
				resultVO.setBoard_id(rs.getString(1));
				resultVO.setCompany_name(rs.getString(2));
				resultVO.setWrite_date(rs.getString(3));
				resultVO.setTitle(rs.getString(4));
				resultVO.setWriter(rs.getString(5));
				resultVO.setContent(rs.getString(6));
				resultVO.setPassword(rs.getString(7));
				resultVO.setViews(rs.getString(8));
				resultVO.setUpload_file(rs.getString(9));
				list.add(resultVO);
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return list;
	}
	public BoardVO selectOne(BoardVO boardVO) {
		BoardVO resultVO = null;
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " SELECT BOARD_ID, COMPANY_NAME, WRITE_DATE, TITLE, WRITER, CONTENT, PASSWORD, VIEWS, UPLOAD_FILE"
					   + " FROM NOTICE_YANGSUDONG"
					   + " WHERE BOARD_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getBoard_id());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultVO = new BoardVO();
				resultVO.setBoard_id(rs.getString(1));
				resultVO.setCompany_name(rs.getString(2));
				resultVO.setWrite_date(rs.getString(3));
				resultVO.setTitle(rs.getString(4));
				resultVO.setWriter(rs.getString(5));
				resultVO.setContent(rs.getString(6));
				resultVO.setPassword(rs.getString(7));
				resultVO.setViews(rs.getString(8));
				resultVO.setUpload_file(rs.getString(9));
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return resultVO;
	}
	
	public void delete(BoardVO boardVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "DELETE FROM NOTICE_YANGSUDONG WHERE BOARD_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getBoard_id());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 삭제됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	public void update(BoardVO boardVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "UPDATE NOTICE_YANGSUDONG SET COMPANY_NAME = ?,Title = ?, Content = ?, Upload_file = ? WHERE BOARD_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getCompany_name());
			pstmt.setString(2, boardVO.getTitle());
			pstmt.setString(3, boardVO.getContent());
			pstmt.setString(4, boardVO.getUpload_file());
			pstmt.setString(5, boardVO.getBoard_id());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 수정됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	
	public int insert(BoardVO boardVO) {
		int r=0;
		try {
			//1.DB연결
		 conn = ConnectionManager.getConnnect();
			
			//2.sql 구문 실행
		 	String sql = "INSERT INTO NOTICE_YANGSUDONG ( "
		 				+ " BOARD_ID, WRITE_DATE, COMPANY_NAME, TITLE, WRITER, CONTENT, PASSWORD, UPLOAD_FILE) "
		 				+ " VALUES(BOARD_SEQ.NEXTVAL,SYSDATE,?,?,?,?,?,?) ";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardVO.getCompany_name());
			pstmt.setString(2, boardVO.getTitle());
			pstmt.setString(3, boardVO.getWriter());
			pstmt.setString(4, boardVO.getContent());
			pstmt.setString(5, boardVO.getPassword());
			pstmt.setString(6, boardVO.getUpload_file());
			r = pstmt.executeUpdate();
			
			//3.결과처리
			System.out.println(r + "건이 처리됨");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4.연결해제
			ConnectionManager.close(null, pstmt, conn);
		}
		return r;
	}
	
}
