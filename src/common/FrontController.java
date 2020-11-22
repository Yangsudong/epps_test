package common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		list.put("/boardInsert.do", new controller.BoardInsertController());
		list.put("/boardDelete.do", new controller.BoardDeleteController());
		list.put("/boardList.do", new controller.BoardListController());
		list.put("/boardUpdate.do", new controller.BoardUpdateController());
		list.put("/boardUpdatePage.do", new controller.BoardUpdatePageController());
		list.put("/boardSelect.do", new controller.BoardSelectController());
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(charset);		
		response.setContentType("text/html; charset=UTF-8");
		String uri = request.getRequestURI();				
		String contextPath = request.getContextPath();		
		String path = uri.substring(contextPath.length());	
		Controller subController = list.get(path);			 
		subController.execute(request, response);
	}

}
