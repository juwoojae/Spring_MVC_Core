package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {
    //각 컨트롤러들은 이 인터페이스를 구현하면 된다.
    //서블릿과 비슷한 모양의 컨트롤러 인터페이스
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
