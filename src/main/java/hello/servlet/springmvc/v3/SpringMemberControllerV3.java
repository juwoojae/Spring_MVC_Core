package hello.servlet.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 실용적인 방식 예제
 * 스프링의 에노테이션 기반 컨트롤러는
 * 1. ModelView 를 반환
 * 2. String 을 반환
 * 3. @RequestParam 에노테이션으로 직접 HTTP 요청파라메터 처리 + 타입 변환도 자동으로 지원한다
 * 4. @RequestParam (method = ) 로 http 메서드를 지정해줄수 있다 (default 는 어떤 메서드가 와도 매핑이 됨)
 * 5. GetMapping 과 PostMapping 으로 메서드 지정을 requestMapping 에 포함시켜줄수 있다
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form",method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
    //@RequestMapping(value = "/save" , method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(@RequestParam("username") String username,
                       @RequestParam("age") int age,
                       Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);
        /**
         * ModelAndView mv = new ModelAndView("save-result");
         *         mv.addObject("member", member);
         */
        model.addAttribute("member", member);
        return "save-result";
    }
}
