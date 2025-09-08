package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    //v1 에서는 Controller 마다 forward 가 있어서 Controller -> JSP 이동을 해주었다면
    //v2 에서는 Controller 가 MyView 를 반환하고, MyView 내부메서드인 render 를 호출하여 Controller ->JSP(View) 를 조회해준다
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
