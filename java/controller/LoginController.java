package controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;
import test.FileUtilMock;
import util.FileUtil;
import util.UserUtil;

//@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	UserDao usDao = new UserDao();
	
	@Override
	public void init() throws ServletException {
		usDao.setIsFileUtil(new FileUtil());
		// Test Junit by mock
		//	usDao.setIsFileUtil(new FileUtilMock());
		
		// init file data. run first time
		/*
		  try(OutputStream out = new FileOutputStream("user.txt");
				  BufferedWriter wt = new BufferedWriter(new OutputStreamWriter(out));) { 
			  
		  wt.write(new User("1234567890123456", "12345678").toString());
		  wt.write(new User("2345678901234567", "ngoc1234").toString());
		  wt.write(new User("3456789012345678", "ngocngoc").toString()); 
		  } catch (IOException e) {
			  e.printStackTrace(); 
		  }
		*/ 
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) 
			throws ServletException, IOException {
		
		// get data from form
		String id = request.getParameter("userId");
		String password = request.getParameter("password");
		String userClick = request.getParameter("login");
		
		switch(userClick) {
		case "Submit":
			actionSubmit(request, response, id, password);
			break;
		case "Clear":
			actionClear(request, response);
			break;
		default:
			
		}
	}

	private void actionSubmit(HttpServletRequest request,
			HttpServletResponse response, String id, String password)
			throws ServletException, IOException {
		
		int flagLogin = getFlagLogin(usDao, id, password);
		switch(flagLogin) {
		case 1:// id invalid
			idInvalid(request, response);
			break;
		case 2:// user not exists
			userInvalid(request, response);
			break;
		case 3:// user blocked
			userBlocked(request, response);
			break;
		case 4: // user login fail
			userInvalid(request, response);
			break;
		case 5: // user login first time
			loginFirstTime(request, response, id);
			break;
		case 6:// user login more than one
			redirecService(request, response);
			break;		
		}
	}

	private void actionClear(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("err", "");
		request.getRequestDispatcher("index.jsp")
               .include(request, response);
	}

	private void redirecService(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("service.jsp")
				.forward(request, response);
	}

	private void loginFirstTime(HttpServletRequest request,
			HttpServletResponse response, String id)
			throws ServletException, IOException {
		
		User user = usDao.getUser(id);
		HttpSession session = request.getSession();
		session.setAttribute("userdata", user);
		request.getRequestDispatcher("first_login.jsp")
				.forward(request, response);
	}

	private void userBlocked(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("err", "Your Account was blocked,"
				+ " Please contact Admin to help!");
		request.getRequestDispatcher("index.jsp")
			   .include(request, response);
	}

	private void userInvalid(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("err", "UserId/Password Invalid" );
		request.getRequestDispatcher("index.jsp")
		       .include(request, response);
	}

	private void idInvalid(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("err", "UserId Error");
		request.getRequestDispatcher("index.jsp")
			   .include(request, response);
	}
	
	public int getFlagLogin(UserDao usDao, String id, String password) {
		
		if(id == null || !UserUtil.checkUserId(id)) return 1;   // id invalid
		User user = usDao.getUser(id);
		if(user == null) return 2;	// user not exist in file
		if(UserUtil.isBlocked(user)) return 3; // user blocked
		if(password.equals(user.getUserPassword())) {
			if(!user.getState()) {
				setLoginFailToZero(usDao, user);
				return 5;	// user login first time
			} else {
				setLoginFailToZero(usDao, user);
				return 6; // user login more than one.
			}
		} else {
			updateLoginFail(usDao, user);
			if(UserUtil.isBlocked(user)) return 3;
			return 4; // user login fail.
		}
	}

	private void setLoginFailToZero(UserDao usDao, User user) {
		user.setCountLoginFail(0);
		usDao.updateUser(user);
	}

	private void updateLoginFail(UserDao usDao, User user) {
		int countLoginFail = user.getCountLoginFail() + 1;
		user.setCountLoginFail(countLoginFail);
		usDao.updateUser(user);
	}
}
