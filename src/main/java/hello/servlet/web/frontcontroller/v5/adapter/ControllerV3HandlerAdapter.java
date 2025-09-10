package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//컨트롤러 V3 를 지원하는 어뎁터
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); //handler 가 ControllerV3 의 인스턴스인가 true/false
    }

    /**
     * 1. controller 로 캐스팅 한후
     * 2. 요청데이터들을 request 에서 꺼내온뒤(createParamMap)
     * 3. contrllor 내부 process(paramMap) 메서드 실행 -> ModelView (model 컨트롤러) 반환
     */

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler; //Casting

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }

    /**
     * Controller 에 Model을 넘겨 주려면 request에 서블릿 컨테이너 저장소에 있는 값을
     * Map<String,String> paramMap 이라는 형태로 바꿔주는 과정이다
     */
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        //paramMap
        Map<String, String> paramMap = new HashMap<>();
        //모든 ParamerterName 을 다 가져온다
        // (paramName,request.getParameter(ParamName)) key ,value 로 paramMap 에 추가
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
