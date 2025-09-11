package hello.servlet;

import hello.servlet.springmvc.v1.SpringMemberFormControllerV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ServletComponentScan //서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

//   @Bean
//    ViewResolver internalResourceViewResolver(){
//        return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
//    }

    ///RequestMappingHandlerMapping.getHandler() 는 스프링 빈중에서
    ///  * @RequestMapping 또는 @Controller (클래스 레벨에 있어야 함!! )를 매핑정보로 인식
//    @Bean
//    SpringMemberFormControllerV1 springMemberFormControllerV1(){
//        return new SpringMemberFormControllerV1();
//    }
}
