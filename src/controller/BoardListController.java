package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Controller;
import common.Paging;
import dao.BoardDAO;
import model.BoardVO;

public class BoardListController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("p");
		String company_name = request.getParameter("company_name");
		
		//유효성 체크
		int page = 1;
		if(p != null) {
			page = Integer.parseInt(p);
		}
		
		Paging paging = new Paging();
		paging.setPageUnit(5);
		paging.setPageSize(3);
		paging.setPage(page);
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO boardVO = new BoardVO();
		
		boardVO.setCompany_name(company_name);
		paging.setTotalRecord(boardDAO.count(boardVO));
		boardVO.setFirst(paging.getFirst());
		boardVO.setLast(paging.getLast());
		
		ArrayList<BoardVO> list = boardDAO.selectAll(boardVO);
		ArrayList<BoardVO> company_list = boardDAO.selectCompany(boardVO);
		
		request.setAttribute("company_list", company_list);
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.getRequestDispatcher("/board/boardAll.jsp").forward(request, response);
	}

}
