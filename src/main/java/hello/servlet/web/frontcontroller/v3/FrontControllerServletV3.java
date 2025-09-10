package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();//클라이언트의 요청 URI 꺼내기

        //클라이언트 요청 오류
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        /**
         * 컨트롤러가 Modelview를 반환
         * Modelview는 렌더링에 필요한 Model, 뷰의 논리이름을 가지고 있다
         * 뷰의 논리 이름 -> 뷰의 실제 물리 이름 으로 전환해주기위해 viewResolver를 사용한다
         * 뿐만 아니라 뷰의 실제 물리 이름이 들어간 MyView 를 리턴해주고 이 객체를 렌더링한다
         */
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(),request,response);
    }
    //컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경한다. 그리고 실제 물리 경로가 있는 MyView 객체를 반환한다
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName +".jsp");
    }

    /**
     * Controller 에 Model을 넘겨 주려면 request에 서블릿 컨테이너 저장소에 있는 값을
     * Map<String,String> paramMap 이라는 형태로 바꿔주는 과정이다
     */
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        //paramMap
        Map<String,String> paramMap = new HashMap<>();
        //모든 ParamerterName 을 다 가져온다
        // (paramName,request.getParameter(ParamName)) key ,value 로 paramMap 에 추가
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
