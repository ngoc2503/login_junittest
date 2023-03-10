package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.Password;
import model.User;
import model.UserAnswer;
import test.FileUtilMock;
import util.FileUtil;
import util.IsFileUtil;
import util.UserUtil;

/**
 * Servlet implementation class FirstLoginController
 */
@WebServlet("/FirstLoginController")
public class FirstLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDao usDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstLoginController() {
        super();
        usDao = new UserDao();
		// usDao work with file.
		usDao.setIsFileUtil(new FileUtil());
		//usDao test Junit
		//usDao.setIsFileUtil(new FileUtilMock());
		
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) 
			throws ServletException, IOException {
		
		// get data from form
		UserAnswer answer = getAnswer(request);
		Password pas = getPasswordInfo(request);
		// get user action
		String action = request.getParameter("send");
		switch (action) {
		case "Submit":
			if(ansIsEmpty(answer)) {
				actionEmptyAnswer(request, response);
				break;
			}
			changePassword(request, response, pas);
			break;
		case "Cancel":
			redirecLogin(request, response);
			break;
		case "Clear":
			actionClear(request, response);
			break;
		}	
	}

	private void changePassword(HttpServletRequest request,
			HttpServletResponse response, Password pas)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User us = (User) session.getAttribute("userdata");
		int controlFlow = controlFlowFirstLogin(pas, us);
		switch(controlFlow) {
		case 1:
			isPasswordInvalid(request, response);
			break;
		case 2:
			newPasswordErr(request, response);
			break;
		case 3:
			confirmPasswordErr(request, response);
			break;
		case 4:
			redirecLogin(request, response);
			break;
		}
	}

	private void actionClear(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("err", null);
		request.getRequestDispatcher("first_login.jsp")
			   .include(request, response);
	}

	private void redirecLogin(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// update user
		request.getRequestDispatcher("index.jsp")
				.forward(request, response);
	}

	private void confirmPasswordErr(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("err", "Confirm password not match");
		request.getRequestDispatcher("first_login.jsp")
				.include(request, response);
	}

	private void newPasswordErr(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("err", ""
				+ "New Password Invalid. Password includes 8 characrers[a-zA-Z]");
		request.getRequestDispatcher("first_login.jsp")
				.include(request, response);
	}

	private void isPasswordInvalid(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("err", "Password Invalid");
		request.getRequestDispatcher("first_login.jsp")
				.include(request, response);
	}

	private void actionEmptyAnswer(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("err", "Please answer at least a question.");
		request.getRequestDispatcher("first_login.jsp")
				.include(request, response);
	}
	
	// answer of user
	private UserAnswer getAnswer(HttpServletRequest req) {
		
		String ans1 = req.getParameter("ans1");
		String ans2 = req.getParameter("ans2");
		String ans3 = req.getParameter("ans3");
		
		return new UserAnswer(ans1, ans2, ans3);
	}
	
	// password info
	private Password getPasswordInfo(HttpServletRequest req) {
		
		String oldPas = req.getParameter("oldPas");
		String newPas = req.getParameter("newPas");
		String conPas = req.getParameter("conPas");
		
		return new Password(oldPas, newPas, conPas);
	}
	
	// check user answer empty
	public boolean ansIsEmpty(UserAnswer ans) {
		
		if(!stringIsBlank(ans.getAns1())
				|| !stringIsBlank(ans.getAns2())
				|| !stringIsBlank(ans.getAns3())) {
			return false;
		}
		return true;
	}
	
	// check String is blank
	private boolean stringIsBlank(String s) {
		
		return (s == null || s.trim().equals(""));
	}

	public int controlFlowFirstLogin(Password pas, User us) {
		String oldPas = pas.getOldPas();
		String newPas = pas.getNewPas();
		String conPas = pas.getConPas();
		// old password null or not match user password
		if(oldPas == null || !oldPas.equals(us.getUserPassword())) {
			return 1;
		}
		// new password invalid or new password match old password
		if(!UserUtil.checkPassword(newPas) 
				|| newPas.equals(us.getUserPassword())) {
			return 2;
		}
		// confirm password not match new password
		if(!conPas.equals(newPas)) return 3;
		// change password
		us.setState(true);
		us.setUserPassword(pas.getNewPas());
		usDao.updateUser(us);
		return 4;
	}
}
