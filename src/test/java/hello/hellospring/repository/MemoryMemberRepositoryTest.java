package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    /**
     * 테스트 시 유의사항
     * 테스트 되는 메소드의 순서는 보장 되지 않음.
     * 테스트는 서로 순서 및 의존관계 없이 설계가 되어야 함.
     * 따라서, 하나의 테스트가 다 수행되면, 저장소 및 공유 데이터를 깔끔하게 지워줘야함.
     * 테스트 코드 없이 개발하면 많은 사람 들과 협업 시 다양한 문제가 생김.
     */

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 해당 어노테이션이 명시된 메서드는 테스트 메소드 실행 후 무조건 실행됨.
    public void afterEach() {
        // 레포지토리 초기화
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
