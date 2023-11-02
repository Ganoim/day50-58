package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.testDto;
import service.TestService;

/**
 * Servlet implementation class TestController
 */
@WebServlet( {"/boardList", "/join", "/joinForm", "/loginForm", "/mainPage"} )
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String url = request.getServletPath();
		System.out.println("url : " + url);
		String path = request.getContextPath(); // /JSP_project
		request.setCharacterEncoding("UTF-8"); //인코딩
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		TestService tsvc = new TestService();
		
		//response.sendRedirect(path+"/resultPage.jsp"); // JSP_project/resultPage/jsp
		
		switch(url) {
		case "/boardList":
			System.out.println("게시판 페이지 이동 요청");
			//BoardList.jsp 파일로 응답
			//response.sendRedirect(path+"/BoardList.jsp");
			// 1. 게시판 페이지에 출력할 데이터를 조회
			ArrayList<testDto> list = tsvc.getBoardList();
			request.setAttribute("tList", list);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardList.jsp");
			dispatcher.forward(request, response);
			
			
			break;
		
		case "/joinForm":
			// 회원가입페이지 이동 요청
			System.out.println("회원가입 페이지 이동요청");
			response.sendRedirect(path+"/joinForm.jsp");
			break;
		case "/loginForm":
			System.out.println("로그인 페이지 이동요청");
			response.sendRedirect(path+"/loginForm.jsp");
			break;
		case "/mainPage":
			System.out.println("메인 페이지 이동요청");
			response.sendRedirect(path+"/mainPage.jsp");
			break;
		case "/join":
			System.out.println("회원가입 요청");
			// 1. 파라메터 확인
			String id = request.getParameter("joinId");
			System.out.println("입력한 아이디 : " + id);
			
			String pw = request.getParameter("joinPw");
			System.out.println("입력한 비밀번호 : " + pw);
			
			String birth = request.getParameter("joinBirth");
			System.out.println("입력한 생년월일 : " + birth);
			
			int joinResult = 1;
			if(joinResult > 0) {
				System.out.println("회원가입 성공!");
				//메인페이지
//				response.sendRedirect(path+"mainPage");
//				response.sendRedirect(path+"mainPage.jsp");
//				response.sendRedirect(path+"/joinSuccess.jsp");
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원가입성공');");
				out.println("location.href='"+path+"/mainPage.jsp'");
				out.println("</script>");
				
			} else {
				System.out.println("회원가입 실패!");
				//회원가입페이지
				//response.sendRedirect(path+"/joinForm.jsp");
				//response.sendRedirect(path+"/joinFail.jsp");
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('fail');");
				out.println("history.back();");
				out.println("</script>");
			}
			break;
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 실행");
		doGet(request, response);
	}

}
