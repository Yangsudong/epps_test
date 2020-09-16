package com.dev.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.controller.MemberDeleteController;
import com.dev.controller.MemberInsertController;
import com.dev.controller.MemberListController;
import com.dev.controller.MemberSearchController;
import com.dev.controller.MemberSearchPageController;
import com.dev.controller.MemberUpdateController;
import com.dev.controller.users.ChartData;
import com.dev.controller.users.DeleteUsers;
import com.dev.controller.users.GetUsers;
import com.dev.controller.users.GetUsersList;
import com.dev.controller.users.InsertUsers;
import com.dev.controller.users.UpdateUsers;

/*
@WebServlet( name = "front", 
			 urlPatterns = "*.do", 
			 initParams = { @WebInitParam(name = "charset", value = "UTF-8") }
			)
*/
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String charset = null;
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		list = new HashMap<String, Controller>();
		list.put("/memberUpdate.do", new MemberUpdateController());
		
		list.put("/admin/memberSearchPage.do", new MemberSearchPageController());
		list.put("/memberSearch.do", new MemberSearchController());
		
		list.put("/memberInsert.do", new MemberInsertController());
		list.put("/memberDelete.do", new MemberDeleteController());
		list.put("/memberList.do", new MemberListController());
		
		//user ajax처리
		list.put("/ajax/DeleteUsers.do", new DeleteUsers());
		list.put("/ajax/GetUsers.do", new GetUsers());
		list.put("/ajax/GetUsersList.do", new GetUsersList());
		list.put("/ajax/InsertUsers.do", new InsertUsers());
		list.put("/ajax/UpdateUsers.do", new UpdateUsers());
		
		//chart data ajax
		list.put("/ajax/ChartData.do", new ChartData());
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(charset);		
		response.setContentType("text/html; charset=UTF-8");
		String uri = request.getRequestURI();				// frontweb/memberInsert.do
		String contextPath = request.getContextPath();		// frontWeb
		String path = uri.substring(contextPath.length());	// /memberInsert.do 
		Controller subController = list.get(path);			 
		subController.execute(request, response);
	}

}
