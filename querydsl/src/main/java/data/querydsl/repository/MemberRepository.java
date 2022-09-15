package data.querydsl.repository;

import data.querydsl.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom,
        QuerydslPredicateExecutor<Member>, MemberRepositorySupport {
    List<Member> findByUsername(String username);
}
