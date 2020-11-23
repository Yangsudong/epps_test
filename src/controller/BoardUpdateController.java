package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import common.Controller;
import dao.BoardDAO;
import model.BoardVO;

public class BoardUpdateController implements Controller {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터를 VO에 담기
		String board_id = request.getParameter("board_id");
		String company_name = request.getParameter("company_name");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String password = request.getParameter("password");
		String upload_file = request.getParameter("upload_file");
		
		//유효성 체크
		BoardVO board = new BoardVO();
		board.setBoard_id(board_id);
		board.setCompany_name(company_name);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);;
		board.setPassword(password);
		board.setUpload_file(upload_file);
		
		BoardDAO dao = new BoardDAO();
		dao.update(board);
		
		//목록으로 이동
		response.sendRedirect("boardList.do");
	}
}
