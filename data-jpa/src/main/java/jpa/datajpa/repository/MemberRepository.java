package jpa.datajpa.repository;

import jpa.datajpa.dto.MemberDto;
import jpa.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, JpaSpecificationExecutor<Member> {
    //메서드 이름으 쿼리 생성
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    List<Member> findHelloBy();

    //네임드쿼리
    @Query(name = "Member.findByUsername")//이거 주석처리해도 알아서 찾아줌
    List<Member> findByUsername(@Param("username") String username);

    //리포지토리에 메서드 정의하기
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);
    //값을 조회한다.
    @Query("select m.username from Member m")
    List<String> findUsernameList();
    //dto projection
    @Query("select new jpa.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    //파라미터 바인딩
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    //반환 타입
    List<Member> findListByUsername(String username);//컬렉션
    Member findMemberByUsername(String username);//단건
    Optional<Member> findOptionalByUsername(String username);//단건 Optional

    //PAGE 및 countQuery 조정
    @Query(value = "select m from Member m left join m.team t", countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    //SLICE
    Slice<Member> findSliceByAge(int age, Pageable pageable);

    //BULK
    @Modifying(clearAutomatically = true)//꼭 넣어야 함.
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    //EntityGraph!!!!!
    @Override
    @EntityGraph(attributePaths = {"team"})//오버라이드도 EnyGraph 적용, jpql로 하기 싫다.
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})//JPQL 에도 EntityGraph 적용가능
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})//머세드 명으로 만드는 쿼리에도 EntityGraph 적용
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    //NamedEntityGraph
    @EntityGraph("Member.all")
    List<Member> findNamedEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly",value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    //프로젝션
    List<UsernameOnly> findProjectionByUsername(@Param("username") String username);
    List<UsernameOnlyDto> findClassProjectionByUsername(@Param("username") String username);
    <T> List<T> findGenericProjectionByUsername(@Param("username") String username, Class<T> type);
}
