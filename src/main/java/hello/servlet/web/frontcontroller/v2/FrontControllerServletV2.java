package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
//front-controller/v1/뒤에 어떤 URL이 들어와도 이 프론트 컨트롤러 서블릿실행
public class FrontControllerServletV2 extends HttpServlet {
    /**
     * URL 매핑 정보
     * get.(URL) = 컨트롤러 조회 가능
     */
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    //처음에 이 서블릿이 서블릿 컨테이너에 등록됨과 동시에 controllerMap 매핑
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();//클라이언트의 요청 URI 꺼내기

        //클라이언트 요청 오류
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
        }
        /**
         * 클라이언트의 요청(Request)에 해당하는 controller ->View 로 Model(request) 를 넘겨주고
         * 뷰 가 Controller.process() 의 리턴값인 viewPath 와 Model을 이용해서 랜더링을 한다
         */
        MyView view = controller.process(request, response);
        view.render(request,response);
    }
}
