package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach//테스트가 하나 끝나면 초기화 하기
    //@Test 는 순서 보장을 하지 않는다
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given 값이 이런 세팅이고
        Member member = new Member("hello",20);
        //when 이러한 상황일때
        Member savedMember = memberRepository.save(member);
        //thae 이렇게 동작해야 한다
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }
    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
