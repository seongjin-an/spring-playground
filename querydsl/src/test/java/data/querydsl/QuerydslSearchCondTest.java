package data.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import data.querydsl.entity.Member;
import data.querydsl.entity.QMember;
import data.querydsl.entity.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static data.querydsl.entity.QMember.member;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class QuerydslSearchCondTest { // 3 검색 조건
    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @DisplayName("기본적인 조건 검색 테스트")
    @Test
    void searchTest() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1").and(member.age.eq(10)))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @DisplayName("BETWEEN 테스트")
    @Test
    void searchBetweenTest() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1").and(member.age.between(10, 30)))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @DisplayName("검색 AND PARAM 테스트")
    @Test
    void searchAndParam() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(
                        member.username.eq("member1"),// ,(쉼표) 로 구분하면 AND 로 연결된다. null 은 무시가된다.
                        member.age.between(10, 30)
                )
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
}
