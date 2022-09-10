package jpa.datajpa.repository;

import jpa.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //메서드 이름으 쿼리 생성
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    List<Member> findHelloBy();

    //네임드쿼리
    @Query(name = "Member.findByUsername")//이거 주석처리해도 알아서 찾아줌
    List<Member> findByUsername(@Param("username") String username);


}
