package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Controller;
import dao.BoardDAO;
import model.BoardVO;

public class BoardUpdatePageController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정페이지로 이동
		BoardVO paramVO = new BoardVO();
		String board_id = request.getParameter("board_id");
		paramVO.setBoard_id(board_id);
		
		BoardDAO dao = new BoardDAO();
		BoardVO board = dao.selectOne(paramVO);
		
		request.setAttribute("board", board);
		
		request.getRequestDispatcher("board/boardUpdate.jsp").forward(request, response);

	
	}

}
