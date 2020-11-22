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
		//수정처리
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BoardVO boardVO = new BoardVO();
		
		try {
			BeanUtils.copyProperties(boardVO, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		BoardDAO dao = new BoardDAO();
		dao.update(boardVO);
		
		//목록으로 이동
		request.getRequestDispatcher("/boardList.do").forward(request, response);
	}
}
