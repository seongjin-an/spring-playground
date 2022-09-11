package jpa.datajpa.repository;

import jpa.datajpa.dto.MemberDto;
import jpa.datajpa.entity.Member;
import jpa.datajpa.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("회원 테스트(SRPING-DATA-JPA)")
    public void testMember() {
        System.out.println("memberRepository = " + memberRepository.getClass());
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @DisplayName("기본 CRUD 테스트")
    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        //단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @DisplayName("이름과 나이 조건 테스트")
    @Test
    public void findByUsernameAndAgeGreaterThanTest(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @DisplayName("find 다음은 설명을 적을 수 있다.")
    @Test
    public void findHelloByTest() {
        List<Member> helloBy = memberRepository.findHelloBy();
        System.out.println("=============================");
        memberRepository.findAll();
    }

    @DisplayName("네임드 쿼리 테스트")
    @Test
    public void testNamedQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    @DisplayName("리포지토리 정의 메서드")
    @Test
    public void testQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA", 10);
        assertThat(result.get(0)).isEqualTo(m1);
    }

    @DisplayName("값을 조회하는 메서드")
    @Test
    public void findUsernameListTest() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        usernameList.forEach(System.out::println);
        assertThat(usernameList).contains("AAA", "BBB");
    }

    @DisplayName("DTO 조회하는 메서드")
    @Test
    public void findMemberDtoTest() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();

        memberDto.forEach(System.out::println);
    }

    @DisplayName("파라미터 바인딩")
    @Test
    public void findByNames(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }

    @DisplayName("반환타입 테스트")
    @Test
    public void returnTypeTest() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> list = memberRepository.findListByUsername("AAA");
        list.forEach(System.out::println);
        System.out.println("=============================");

        Member member = memberRepository.findMemberByUsername("AAA");
        System.out.println("member = " + member);
        System.out.println("=============================");

        Optional<Member> result = memberRepository.findOptionalByUsername("AAA");
        result.ifPresent(o -> System.out.println(o));
    }

    @DisplayName("페이징 테스트")
    @Test
    public void paging() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

        //then
        List<Member> content = page.getContent();
        long totalCount = page.getTotalElements();
        for(Member member: content) {
            System.out.println("member = " + member);
        }
        System.out.println("totalCount = " + totalCount);

        assertThat(content.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);//페이지 번호를 가져온다.
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }

    @DisplayName("슬라이스 테스트")
    @Test
    public void sliceTest() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        //when
        Slice<Member> page = memberRepository.findSliceByAge(age, pageRequest);

        //then
        List<Member> content = page.getContent();
//        long totalCount = page.getTotalElements();
        for(Member member: content) {
            System.out.println("member = " + member);
        }
//        System.out.println("totalCount = " + totalCount);

        assertThat(content.size()).isEqualTo(3);
//        assertThat(totalCount).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);//페이지 번호를 가져온다.
//        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }

    @DisplayName("벌크 수정 처리")
    @Test
    public void bulkUpdate() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));

        //when
        int resultCount = memberRepository.bulkAgePlus(20);//bulk 연산은 곧바로 DB 로 반영하고 영속석 컨텍스트에는 반영이 안됨.
        em.flush();
        em.clear();//또는 @Modifying 애노테이션 clearAutomatically = true 처리를 한다.

        List<Member> member5 = memberRepository.findByUsername("member5");
        member5.forEach(System.out::println);

        //then
        assertThat(resultCount).isEqualTo(3);
    }

    @DisplayName("lazy test")
    @Test
    public void findMemberLazy() {
        //given
        //member1 -> teamA
        //member2 -> teamB

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        //when
//        List<Member> members = memberRepository.findMemberFetchJoin();
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member = " + member.getTeam().getClass());
            System.out.println("member.team = " + member.getTeam().getName());
        }
    }

    @DisplayName("엔티티 그래프 TEST")
    @Test
    public void findEntityGraphByUsernameTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findEntityGraphByUsername("member1");
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member = " + member.getTeam().getClass());
            System.out.println("member.team = " + member.getTeam().getName());
        }
        System.out.println("=====================================+");
        List<Member> members2 = memberRepository.findNamedEntityGraphByUsername("member2");
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member = " + member.getTeam().getClass());
            System.out.println("member.team = " + member.getTeam().getName());
        }
    }

    @DisplayName("JPA HINT !! READ ONLY")
    @Test
    public void queryHint() {
        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        //when, 다음과 같이 하면 원본을 따로 유지하는 상황이 발생함 (default)
//        Member findMember = memberRepository.findById(member1.getId()).get();
//        findMember.setUsername("member2");
//        em.flush();

        System.out.println("==========================================================");

        //when, 스냅샷을 따로유지하지 않음.
        Member findMember2 = memberRepository.findReadOnlyByUsername("member1");
        findMember2.setUsername("member3");
        em.flush();
    }

    @DisplayName("LOCK TEST")
    @Test
    public void lock() {
        //given
        Member member1 = new Member("member1",10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");
    }

    @DisplayName("사용자 정의 리포지토리 구현")
    @Test
    public void callCustom() {
        List<Member> members = memberRepository.findMemberCustom();
    }

    @DisplayName("SPECIFICATION 테스트!!")
    @Test
    public void specBasic() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        Specification<Member> spec = MemberSpec.username("m1").and(MemberSpec.teamName("teamA"));
        List<Member> result = memberRepository.findAll(spec);

        assertThat(result.size()).isEqualTo(1);
    }

    @DisplayName("Query By Example!!!!1")
    @Test
    public void queryByExample() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        //Probe
        Member member = new Member("m1");//도메인 객체로 검색조건을 만들어 버린다고 생각하자.
        Team team = new Team("teamA");
        member.setTeam(team);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age"); //primitive type 은 무시해주자

        Example<Member> example = Example.of(member, matcher);

        List<Member> result = memberRepository.findAll(example);

        assertThat(result.get(0).getUsername()).isEqualTo("m1");
    }//JOIN 이 되기는 하는데 OUTER 조인이 잘 안됨..


    @DisplayName("Projection TEST")
    @Test
    public void projections() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        List<UsernameOnly> result = memberRepository.findProjectionByUsername("m1");
        for (UsernameOnly usernameOnly : result) {
            System.out.println("usernameOnly = " + usernameOnly.getUsername());//interface만 만들어 두면 spring이 구현체를 만들어줌
        }
    }

    @DisplayName("class projection test")
    @Test
    public void classProjections() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        List<UsernameOnlyDto> result = memberRepository.findClassProjectionByUsername("m1");//단 생성자의 파라미터명과 일치해야함
        for (UsernameOnlyDto usernameOnly : result) {
            System.out.println("usernameOnly = " + usernameOnly.getUsername());
        }
    }

    @DisplayName("generic projection test")
    @Test
    public void genericProjections() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        List<UsernameOnlyDto> result = memberRepository.findGenericProjectionByUsername("m1", UsernameOnlyDto.class);
        for (UsernameOnlyDto usernameOnly : result) {
            System.out.println("usernameOnly = " + usernameOnly.getUsername());
        }
    }

    @DisplayName("중첩구조처리 test")
    @Test
    public void nestedProjections() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        //when
        List<NestedClosedProjections> result = memberRepository.findGenericProjectionByUsername("m1", NestedClosedProjections.class);
        for (NestedClosedProjections usernameOnly : result) {
            System.out.println("usernameOnly = " + usernameOnly.getUsername());
        }
    }
}