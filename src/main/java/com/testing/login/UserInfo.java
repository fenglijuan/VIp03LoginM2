package com.testing.login;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testing.login.ConnectMysql;


@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	
		response.setContentType("text/html;charset=UTF-8");
	
		request.setCharacterEncoding("UTF-8");
		Map<String, String> userinfo;
		String information="{";
		
		String user=request.getSession().getAttribute("loginname").toString();
		if(user!=null) {
			
			ConnectMysql connSql = new ConnectMysql();
			UseMysql mySql = new UseMysql(connSql.conn);
			userinfo=mySql.getUserInfo(user);
		
			for(String key:userinfo.keySet()) {
				information+="\""+key+"\":\""+userinfo.get(key)+"\",";
			}
			information+="}";
			information=information.replace(",}", "}");
		}
		response.getWriter().append(information);
	}

}
