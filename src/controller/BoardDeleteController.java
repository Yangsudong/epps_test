package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Controller;
import dao.BoardDAO;
import model.BoardVO;

public class BoardDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//파라미터 변수에 저장
			String board_id = request.getParameter("board_id");
			
			
			//VO에 담기
			BoardVO boardVO = new BoardVO();
			boardVO.setBoard_id(board_id);
			
			//서비스
			BoardDAO.getInstance().delete(boardVO);
			
			//결과페이지로 포워드
			request.getRequestDispatcher("/boardList.do").forward(request, response);
	}

}
