package data.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import data.querydsl.entity.Member;
import data.querydsl.entity.QMember;
import data.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static data.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class QuerydslResultTest { // 4 결과 조회
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

    @DisplayName("fetch() && LIST 결과 조회")
    @Test
    void listResultFetch() {
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();
    }

    @DisplayName("fetchOne() && 단건 결과 조회")
    @Test
    void oneResultFetch() {
        Member fetchOne = queryFactory
                .selectFrom(QMember.member)
                .fetchOne();
    }

    @DisplayName("fetchFirst() && 처음 결과 조회")
    @Test
    void firstResultFetch() {
        Member fetchFirst = queryFactory
                .selectFrom(QMember.member)
                .fetchFirst();
    }

    @DisplayName("fetchResults() 는 페이징 정보를 포함해서 total count 쿼리도 수행함")
    @Test
    void fetchResultsTest() {
        QueryResults<Member> fetchResults = queryFactory
                .selectFrom(member)
                .fetchResults();
        long total = fetchResults.getTotal();
        List<Member> content = fetchResults.getResults();
    }

    @DisplayName("fetchCount() 는 count 쿼리로 변경해서 count 수 조회")
    @Test
    void fetchCountTest() {
        long count = queryFactory
                .selectFrom(member)
                .fetchCount();
    }
}
