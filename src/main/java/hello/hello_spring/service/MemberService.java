package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*

    회원가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
        // 위의 방식은 Optional로 감싸서 반환하는 방식이다. 이는 비추전하므로 result를 만들지 않고 바로 ifPresent를 사용하는 것이 좋다.
        // result.orElseGet() -> result가 있으면 가져오고 없으면 뒤에 있는 것을 가져온다.
        // 바로 꺼내고 싶으면 result.get()을 사용하면 된다. 권장하는 않음
//        result.ifPresent(m -> { // result가 있으면 ex) if null과 같음
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 추천 방식
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
