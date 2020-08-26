package com.testing.login;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	
		response.setContentType("text/html;charset=UTF-8");

		request.setCharacterEncoding("UTF-8");
		String ln = request.getParameter("loginname");
		String pw = request.getParameter("password");
		ConnectMysql coml = new ConnectMysql();
		UseMysql ul = new UseMysql(coml.conn);
		boolean reslut=ul.Login(ln, pw);

		String reslutmag = "{";
		if (reslut) {
			reslutmag += "\"mag\":\"登录成功\"}";
		} else {
			reslutmag += "\"mag\":\"登录失败\"}";
		}

		response.getWriter().append(reslutmag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	
		response.setContentType("text/html;charset=UTF-8");
	
		request.setCharacterEncoding("UTF-8");
		String sid = request.getSession().getId();
		System.out.println(sid);
		request.getSession().setMaxInactiveInterval(1800);
		String ln = request.getParameter("loginname");
		String pw = request.getParameter("password");
		String regex = "[^a-zA-Z0-9_]";
		Pattern p = Pattern.compile(regex);
		Matcher mu = p.matcher(ln);
		Matcher mp = p.matcher(pw);
		String reslutmag = "{";
		if (!ln.equals("") && !pw.equals("")) {
			System.out.println(ln.length());
			System.out.println(pw.length());
			if (ln.length() >= 2 && ln.length() <= 8 && pw.length() >= 2 && pw.length() <= 8) {
				if (!mu.find() && !mp.find()) {
					if (request.getSession().getAttribute("loginname") == null) {
						ConnectMysql coml = new ConnectMysql();
						UseMysql ul = new UseMysql(coml.conn);
						
						boolean reslut = ul.Plogin(ln, pw);
						if (reslut) {
							reslutmag += "\"mag\":\"登录成功2\"}";
							request.getSession().setAttribute("loginname", ln);
							Cookie ssid=new Cookie("JSESSIONID",sid);
							ssid.setMaxAge(180);
							response.addCookie(ssid);
						} else {
							reslutmag += "\"mag\":\"已经登录\"}";
						}
					} else {
						if (request.getSession().getAttribute("loginname").equals(ln)) {
							reslutmag += "\"mag\":\"登录成功3\"}";
						} else {
							reslutmag += "\"mag\":\"用户名密码不能为空\"}";
						}
					}
				} else {

					reslutmag += "\"mag\":\"登录成功4\"}";

				}
			} else {
				reslutmag += "\"mag\":\"用户名密码不能少于3位或大于8位\"}";
			}
		} else {
			reslutmag += "\"mag\":\"用户名密码不能为空\"}";
		}

		response.getWriter().append(reslutmag);

	}

}
