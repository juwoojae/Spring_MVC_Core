package hello.servlet.basic.request;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라메터 전송기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
//매핑된 URL 이 호출 되면 서블릿 컨테이너는 다음 메서드를 실행한다
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");
        //request.getParamerterName() 하면 Enumeration<String> 인 열거형 객체가 리턴된다
        /*
        while(parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            System.out.println("param name = " + name);
        } 열거형 객체를 모두 출력하는 방식 요즘은 iterator() 를 주로 사용한다*/
        System.out.println("[parameter start]");
        request.getParameterNames().asIterator()//getParameterNames()하면 쿼리 파라메터의 name 이 나온다 search?name=woojae 여기서 name 에 해당
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName))); //getparameter(paramName) 하면 paramName 를 키값으로 꺼냄
        System.out.println("[parameter end]");
        System.out.println();

        System.out.println("[solo parameter refer]");//getparameter(parameterName) 파라메터 이름이 key값으로 꺼낸다
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);  //우선순위는 앞에있는 순서대로
        System.out.println("age = " + age);
        System.out.println();

        //만약 http://localhost:8080/request-param?username=hello&age=20&username=hello2
        //http 쿼리 파라메터는 하나의 key 가 여러 value 를 가질수 있다
        System.out.println("[same name parameters refer]");
        String[] usernames = request.getParameterValues("username");
        for(String name : usernames){
            System.out.println("username = " + name);
        }
        response.getWriter().write("OK");
    }
}
