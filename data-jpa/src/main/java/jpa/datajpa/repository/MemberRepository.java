package jpa.datajpa.repository;

import jpa.datajpa.dto.MemberDto;
import jpa.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
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

    List<Member> findListByUsername(String username);//컬렉션
    Member findMemberByUsername(String username);//단건
    Optional<Member> findOptionalByUsername(String username);//단건 Optional
}
