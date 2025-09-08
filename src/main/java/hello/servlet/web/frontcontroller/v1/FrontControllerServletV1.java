package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
//front-controller/v1/뒤에 어떤 URL이 들어와도 이 프론트 컨트롤러 서블릿실행
public class FrontControllerServletV1 extends HttpServlet {
    /*매핑 정보
     * URL 매핑 정보에서 컨트롤러 조회
     * */
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    //처음에 이 서블릿이 서블릿 컨테이너에 등록됨과 동시에 controllerMap 매핑,
    //"/front-controller/v1/*"-> URL키값으로 알맞게 매핑한뒤 해당 컨트롤러 조회
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        String requestURI = request.getRequestURI();//클라이언트의 요청 URI 꺼내기

        //클라이언트 요청 오류
        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
        }

        controller.process(request,response);
    }
}
