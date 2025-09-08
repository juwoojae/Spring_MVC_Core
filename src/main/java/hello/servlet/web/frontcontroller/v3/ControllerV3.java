package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    //v2 는 서블릿 request와 response 가 모두 들어갔다. request 가 Model의 역할을 했기 때문이다
    //ControllerV3 의 모든 구현체들은 ModelView 를 반환해야한다
    ModelView process(Map<String, String> paramMap);
}
