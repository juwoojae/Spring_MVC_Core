package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        /*Model에 데이터를 보관한다 -> HttpServletRequest 에 데이터를 보관한다
        * request 객체는 클라이언트의 요청 정보를 담고있는 동시에, 추가 데이터를 임시로 보관할수있는 저장소의 역할을 한다
        * */
        request.setAttribute("member",member);  //저장소로써의 역할 담당 (Key, value) 의 형태로 저장된다 value는 자바 객체
        //이 부분 중요!!  Controller 인 MvcMemberSaveServlet 의 service 로 jsp (View)와 연결하기
        String viewPath ="/WEB-INF/views/save-result.jsp"; //jsp 경로
        //WEB-INF 란 톰켓(Tomcat)같은 서블릿 컨테이너에서 특별취급되서 외부(client)에서는 접근할수 없고, 서버 내부에서만 접근 가능한 자원들을 두는 곳(접근 제한자 멘치로)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//Controller - > view 할때 사용
        dispatcher.forward(request,response); //servlet 에서 jsp 를 호출할수있음 다른 서블릿이나 JSP로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다.
        /*redirect vs forward
        * 리다이렉트는 실제 클라이언트(웹 브라우저) 에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청한다.
        * 따라서 클라이언트가 인지할수있고 실제 URL경로도 변경된다.
        * 반면에, 포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다
        * */


    }
}
