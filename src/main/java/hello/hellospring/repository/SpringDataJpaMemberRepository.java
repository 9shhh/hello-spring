package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// spring-data-jpa가 SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JpaRepository 의 공통으로 사용하는 메소드 범주에 벗어난 커스텀 함수는 내부적으로 자동으로 JPQL 문으로 만들어줌.
    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
