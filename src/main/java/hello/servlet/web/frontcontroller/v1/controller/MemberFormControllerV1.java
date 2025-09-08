package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; //jsp 경로
        //WEB-INF 란 톰켓(Tomcat)같은 서블릿 컨테이너에서 특별취급되서 외부(client)에서는 접근할수 없고, 서버 내부에서만 접근 가능한 자원들을 두는 곳(접근 제한자 멘치로)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//Controller - > view 할때 사용
        dispatcher.forward(request, response); //servlet 에서 jsp 를 호출할수있음 다른 서블릿이나 JSP로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다.
    }
}
