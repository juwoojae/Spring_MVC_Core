package hello.servlet.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

 /**
 * 컨트롤러 통합 예제
 * SpringMemberFromControllerV1
 * SpringMemberListControllerV1
 * SpringMemberSaveControllerV1
 * 1. 3개의 클래스 를 @Controller 에노테이션을 이용해서 하나의 클래스로 합치기
 * 2. 클래스 레벨에 RequestMapping 사용하여 공통URL/
  * 3. 클래스 레벨의 RequestMapping + 메서드 레벨의 RequestMapping 으로 조합된다
 */

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }

    @RequestMapping
    public ModelAndView members(HttpServletRequest request, HttpServletResponse response) {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);//ModelView 객체에 넣기
        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}
