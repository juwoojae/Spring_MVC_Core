package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*  동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
* */
public class MemberRepository {
    //(id, Member) 해시 맵
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence =0L;

    //싱글톤으로 사용하려면 private 생성자로 생성을 막는다
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }
    //private 생성자 -> 외부에서 생성 불가능
    private MemberRepository() {
    }
    //Member 를 받아서 store 에 저장
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }
    //id 로 Member 찾기
    public Member findById(Long id){
        return store.get(id);
    }
    //모든 store 에있는 value 를 ArrayList 의 형태로 반환
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
