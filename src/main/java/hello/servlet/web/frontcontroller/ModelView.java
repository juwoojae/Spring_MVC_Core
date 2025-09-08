package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String ViewName;
    //Model 을 저장하는 서블릿에서 getAttribute의 역할을 하는 저장소
    private Map<String, Object> model = new HashMap<>();
    //해당 URL에서 동작을 구분하는 핵심 워드
    public ModelView(String viewName) {
        ViewName = viewName;
    }

    public String getViewName() {
        return ViewName;
    }

    public void setViewName(String viewName) {
        ViewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
