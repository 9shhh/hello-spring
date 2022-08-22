package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 회원 도메인과 리포지토리를 활용한 회원 비즈니스 로직을 정의하는 클래스
@Service // 스프링 빈에 등록(컴포넌트 스캔 방식)
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired // 의존 관계 연결을 위한 어노테이션 정의
    public MemberService(MemberRepository memberRepository) {
        // 생성자를 통한 memberRepository Dependency Injection
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 중복 회원 검증
        validationDuplicateMember(member);
        return memberRepository
                .save(member)
                .getId();
    }

    private void validationDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
