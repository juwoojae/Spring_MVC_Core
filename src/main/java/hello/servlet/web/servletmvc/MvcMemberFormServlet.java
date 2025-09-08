package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * 1. Servlet 으로 Form 을 받아서 Servlet service 내부에서 HTML 코드 브라우저에 전달하기
        * 2. JSP 를 이용하여 JSP 내부안에 비즈니스 로직과 HTML (View)영역을 다 집어 넣기
        * 3. (현재) servlet 의 service 가 Controller 의 역할을 하고, View 를 JSP가 담당한다 그리고 HTTPServletRequest 객체를 이용하여 데이터를 보관및 조회기능을 한다
        * */
        //이 부분 중요!!  Controller 인 mvcMemberFormServlet 의 service 로 jsp (View)와 연결하기
        String viewPath ="/WEB-INF/views/new-form.jsp"; //jsp 경로
        //WEB-INF 란 톰켓(Tomcat)같은 서블릿 컨테이너에서 특별취급되서 외부(client)에서는 접근할수 없고, 서버 내부에서만 접근 가능한 자원들을 두는 곳(접근 제한자 멘치로)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//Controller - > view 할때 사용
        dispatcher.forward(request,response); //servlet 에서 jsp 를 호출할수있음 다른 서블릿이나 JSP로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다.
    }
}
