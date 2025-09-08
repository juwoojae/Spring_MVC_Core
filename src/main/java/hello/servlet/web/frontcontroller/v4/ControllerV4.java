package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {
    //ParamMap 과 model을 동시에 넘겨줌, 물론 model은 empty 한 상태
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
