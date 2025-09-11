package hello.servlet.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SpringMemberSaveControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        //이 부분의 로직은 프론트 컨트롤러에서 paramMap에 미리 서블릿 request를 이용해서 username,age를 추출한후 controller 에 파라메터로 넘긴상황
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);
        //Model에 데이터를 보관한다
        ModelAndView mv = new ModelAndView("save-result");
        //mv.getModel().put("member", member);
        mv.addObject("member", member);
        return mv;
    }
}
