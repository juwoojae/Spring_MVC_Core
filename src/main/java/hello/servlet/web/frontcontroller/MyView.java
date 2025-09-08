package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }
    //Controller 에서 JSP 로 이동
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }
    /*
       뷰 객체를 통해서 HTML 화면을 랜더링 한다
       뷰 객체의 render()는 모델 정보도 함께 받는다
       JSP는 request.getAttribute()로 데이터를 조회하기 때문에, 모델의 데이터를 꺼내서
       request.setAttribute()로 담아둔다.
       JSP로 포워드 해서 JSP를 렌더링한다
     * */
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }
    //model 에 있는 값을 다 꺼내서 request.setAttribute 에 추가
    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value)-> request.setAttribute(key,value));
        //JSP 는 request.setAttribute 안에 넣어줘야 한다 JSP 의 표현식을 써줄수 있다
    }
}
