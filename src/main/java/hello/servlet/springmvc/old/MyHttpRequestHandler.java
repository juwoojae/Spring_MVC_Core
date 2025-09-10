package hello.servlet.springmvc.old;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import java.io.IOException;

/**
 * 1. 핸들러 매핑으로 핸들러 조회
 * BeanNameUrlHandlerMaping 가 실행에 성공하고 핸들러인 MyHttpRequestHandler 를 반환한다
 * 2. 핸들러 어뎁터 조회
 * HandlerAdapter 의 support() 를 순서대로 호출한다
 *     public class HttpRequestHandlerAdapter implements HandlerAdapter {
 *
 * 	    @Override
 *    public boolean supports(Object handler) {
 * 		return (handler instanceof HttpRequestHandler);
 *    }
 *
 *    @Override
 *    @Nullable
 *    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
 * 			throws Exception {
 *
 * 		((HttpRequestHandler) handler).handleRequest(request, response);
 * 		return null;
 *    }
 *  HttpRequestHandlerAdapter 를 반환한다
 * 3. 핸들러 어뎁터 실행
 *
 */
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
