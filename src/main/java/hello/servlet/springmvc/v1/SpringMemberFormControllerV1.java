package hello.servlet.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller
 * 1. 스프링이 자동으로 스프링 빈으로 등록한다(내부의 @Component)
 * 2. 스프링 MVC 에서 RequestMappingHandlerMapping 의 컨트롤러(handler) 로 인식한다
 *  RequestMappingHandlerMapping.getHandler() 는 스프링 빈중에서
 * @RequestMapping 또는 @Controller (클래스 레벨에 있어야 함!! )를 매핑정보로 인식한다
 * @RequestMapping("/springmvc/v1/members/new-form")
 * 요청 정보를 매핑한다 해당 URL 이 호출되면 이 메서드가 호출된다
 */
@Controller
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        System.out.println("SpringMemberFormControllerV1.process");
        /**
         * ViewResolver 가 논리 주소를 물리 주소로 반환후 DispacherServlet 으로 전달.
         * View 로 반환후 render()
         */
        return new ModelAndView("new-form");
    }
}
