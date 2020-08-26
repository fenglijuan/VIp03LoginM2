package com.testing.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");
		// 鏀跺埌鐨勫弬鏁扮紪鐮�
		request.setCharacterEncoding("UTF-8");
		//浣垮綋鍓峴ession杩囨湡澶辨晥
		System.out.println(request.getSession().getAttribute("loginname"));
		
		if(request.getSession().getAttribute("loginname")!=null){
			//閫氳繃璁﹕ession澶辨晥锛岃揪鍒版敞閿�鐨勭洰鐨勩��
			request.getSession().invalidate();
			response.getWriter().append("{\"status\":0,\"msg\":\"用户已登出!\"}");
		}else{
			request.getSession().invalidate();
			response.getWriter().append("{\"status\":0,\"msg\":\"登出成功!\"}");
		}
//		重定向
//		response.sendRedirect("index.html");
	}

}
